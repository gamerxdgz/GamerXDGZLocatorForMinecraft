package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

public interface LocatorPlatform {

    String getPlatformName();

    boolean supportsActionBar();

    boolean supportsLegacyClients();

    void sendLocator(
            Player player,
            String message
    );

    void clearLocator(Player player);
}
