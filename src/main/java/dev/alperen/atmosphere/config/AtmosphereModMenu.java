package dev.alperen.atmosphere.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public final class AtmosphereModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            AtmosphereConfig config = ConfigManager.get().copy();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("title.atmosphere.config"));

            builder.setSavingRunnable(config::save);

            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.atmosphere.general"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.atmosphere.clouds"), config.cloudsEnabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(value -> config.cloudsEnabled = value)
                    .build());

            general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.atmosphere.cloud_height"), config.cloudHeight, 64, 384)
                    .setDefaultValue(192)
                    .setSaveConsumer(value -> config.cloudHeight = value)
                    .build());

            general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.atmosphere.cloud_layers"), config.cloudLayers, 1, 8)
                    .setDefaultValue(4)
                    .setSaveConsumer(value -> config.cloudLayers = value)
                    .build());

            general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.atmosphere.cloud_opacity"), config.cloudOpacityPercent, 0, 100)
                    .setDefaultValue(65)
                    .setSaveConsumer(value -> config.cloudOpacityPercent = value)
                    .build());

            general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.atmosphere.cloud_speed"), config.cloudSpeedPercent, 0, 400)
                    .setDefaultValue(100)
                    .setSaveConsumer(value -> config.cloudSpeedPercent = value)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.atmosphere.mushroom_particles"), config.mushroomParticlesEnabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(value -> config.mushroomParticlesEnabled = value)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.atmosphere.shooting_stars"), config.shootingStarsEnabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(value -> config.shootingStarsEnabled = value)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.atmosphere.aurora"), config.auroraEnabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(value -> config.auroraEnabled = value)
                    .build());

            general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.atmosphere.particle_density"), config.particleDensityPercent, 0, 100)
                    .setDefaultValue(25)
                    .setSaveConsumer(value -> config.particleDensityPercent = value)
                    .build());

            return builder.build();
        };
    }
}
