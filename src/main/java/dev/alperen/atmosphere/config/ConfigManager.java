package dev.alperen.atmosphere.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.alperen.atmosphere.AtmosphereClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class ConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final File FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "atmosphere.json");

    private static AtmosphereConfig current = new AtmosphereConfig();

    public static AtmosphereConfig get() {
        return current;
    }

    public static void load() {
        if (FILE.exists()) {
            try (FileReader reader = new FileReader(FILE)) {
                AtmosphereConfig loaded = GSON.fromJson(reader, AtmosphereConfig.class);
                if (loaded != null) {
                    current = loaded;
                    current.validate();
                }
            } catch (IOException e) {
                AtmosphereClient.LOGGER.error("Failed to load configuration file", e);
            }
        } else {
            save();
        }
    }

    public static void save() {
        current.validate();
        try {
            File parent = FILE.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            try (FileWriter writer = new FileWriter(FILE)) {
                GSON.toJson(current, writer);
            }
        } catch (IOException e) {
            AtmosphereClient.LOGGER.error("Failed to save configuration file", e);
        }
    }
}
