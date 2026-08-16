package com.gamerxdgz.locator;

public final class LocatorDisplayFactory {

    private LocatorDisplayFactory() {
    }

    public static LocatorDisplay create(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        return new FormattedLocatorDisplay();
    }
}
