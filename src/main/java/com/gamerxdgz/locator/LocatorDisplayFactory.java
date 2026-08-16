package com.gamerxdgz.locator;

public final class LocatorDisplayFactory {

    private LocatorDisplayFactory() {
    }

    public static LocatorDisplay create(
            GamerXDGZLocatorForMinecraft plugin
    ) {

        LocatorPlatform platform =
                plugin.getPlatformManager().getPlatform();

        if (platform.supportsActionBar()) {
            return new FormattedLocatorDisplay();
        }

        return new FallbackLocatorDisplay();
    }
}
