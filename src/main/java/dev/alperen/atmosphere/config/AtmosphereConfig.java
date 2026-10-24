package dev.alperen.atmosphere.config;

public final class AtmosphereConfig {

    public boolean cloudsEnabled = true;

    public int cloudHeight = 192;
    public int cloudLayers = 4;
    public int cloudLayerSpacing = 4;

    public int cloudOpacityPercent = 65;
    public int cloudSpeedPercent = 100;

    public boolean mushroomParticlesEnabled = true;
    public boolean shootingStarsEnabled = true;
    public boolean auroraEnabled = true;

    public int particleDensityPercent = 25;

    public void validate() {
        cloudHeight = clamp(cloudHeight, 64, 384);
        cloudLayers = clamp(cloudLayers, 1, 8);
        cloudLayerSpacing = clamp(cloudLayerSpacing, 1, 12);
        cloudOpacityPercent = clamp(cloudOpacityPercent, 0, 100);
        cloudSpeedPercent = clamp(cloudSpeedPercent, 0, 400);
        particleDensityPercent = clamp(particleDensityPercent, 0, 100);
    }

    public AtmosphereConfig copy() {
        AtmosphereConfig result = new AtmosphereConfig();
        result.cloudsEnabled = cloudsEnabled;
        result.cloudHeight = cloudHeight;
        result.cloudLayers = cloudLayers;
        result.cloudLayerSpacing = cloudLayerSpacing;
        result.cloudOpacityPercent = cloudOpacityPercent;
        result.cloudSpeedPercent = cloudSpeedPercent;
        result.mushroomParticlesEnabled = mushroomParticlesEnabled;
        result.shootingStarsEnabled = shootingStarsEnabled;
        result.auroraEnabled = auroraEnabled;
        result.particleDensityPercent = particleDensityPercent;
        return result;
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
