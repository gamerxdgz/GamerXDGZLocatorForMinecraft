package com.gamerxdgz.locator;

public final class LocatorPlatformManager {

    private final LocatorPlatform platform;

    public LocatorPlatformManager(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        this.platform =
                LocatorPlatformFactory.create(plugin);
    }

    public LocatorPlatform getPlatform() {
        return platform;
    }

    public String getPlatformName() {
        return platform.getPlatformName();
    }

    public boolean supportsActionBar() {
        return platform.supportsActionBar();
    }

    public boolean supportsLegacyClients() {
        return platform.supportsLegacyClients();
    }
}
