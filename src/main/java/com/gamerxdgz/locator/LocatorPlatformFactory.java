package com.gamerxdgz.locator;

public final class LocatorPlatformFactory {

    private LocatorPlatformFactory() {
    }

    public static LocatorPlatform create(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        return new ModernLocatorPlatform();
    }
}
