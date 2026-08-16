package com.gamerxdgz.locator;

public final class ModernLocatorPlatform
        implements LocatorPlatform {

    @Override
    public String getPlatformName() {
        return "Modern Minecraft";
    }

    @Override
    public boolean supportsActionBar() {
        return true;
    }

    @Override
    public boolean supportsLegacyClients() {
        return false;
    }
}
