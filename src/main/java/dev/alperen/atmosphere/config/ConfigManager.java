package dev.alperen.atmosphere.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.alperen.atmosphere.AtmosphereClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class ConfigManager {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path FILE = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("atmosphere.json");

    private static volatile AtmosphereConfig current =
            new AtmosphereConfig();

    private ConfigManager() {
    }

    public static AtmosphereConfig get() {
        return current;
    }

    public static synchronized void load() {
        if (!Files.exists(FILE)) {
            save(new AtmosphereConfig());
            return;
        }

        try (Reader reader = Files.newBufferedReader(
                FILE, StandardCharsets.UTF_8
        )) {
            AtmosphereConfig loaded = GSON.fromJson(
                    reader, AtmosphereConfig.class
            );

            if (loaded == null) {
                throw new IOException("Config root is null.");
            }

            loaded.validate();
            current = loaded;
        } catch (IOException | RuntimeException exception) {
            current = new AtmosphereConfig();

            AtmosphereClient.LOGGER.error(
                    "Configuration could not be loaded. "
                            + "Using defaults; existing file preserved.",
                    exception
            );
        }
    }

    public static synchronized void save() {
        save(current);
    }

    public static synchronized void save(AtmosphereConfig edited) {
        AtmosphereConfig validated = edited.copy();
        validated.validate();

        Path temporary = null;

        try {
            Files.createDirectories(FILE.getParent());

            temporary = Files.createTempFile(
                    FILE.getParent(),
                    "atmosphere-",
                    ".tmp"
            );

            try (Writer writer = Files.newBufferedWriter(
                    temporary, StandardCharsets.UTF_8
            )) {
                GSON.toJson(validated, writer);
            }

            try {
                Files.move(
                        temporary,
                        FILE,
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.ATOMIC_MOVE
                );
            } catch (AtomicMoveNotSupportedException exception) {
                Files.move(
                        temporary,
                        FILE,
                        StandardCopyOption.REPLACE_EXISTING
                );
            }

            current = validated;
        } catch (IOException | RuntimeException exception) {
            AtmosphereClient.LOGGER.error(
                    "Configuration could not be saved. "
                            + "Previous settings retained.",
                    exception
            );
        } finally {
            if (temporary != null) {
                try {
                    Files.deleteIfExists(temporary);
                } catch (IOException exception) {
                    AtmosphereClient.LOGGER.warn(
                            "Temporary config cleanup failed.",
                            exception
                    );
                }
            }
        }
    }
}
