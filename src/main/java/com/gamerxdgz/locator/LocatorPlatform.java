package com.gamerxdgz.locator;

public interface LocatorPlatform {

    String getPlatformName();

    boolean supportsActionBar();

    boolean supportsLegacyClients();
}
