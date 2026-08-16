package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

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

    @Override
    public void sendLocator(
            Player player,
            String message
    ) {

        if (player == null || !player.isOnline()) {
            return;
        }

        player.sendActionBar(message);
    }

    @Override
    public void clearLocator(Player player) {

        if (player == null || !player.isOnline()) {
            return;
        }

        player.sendActionBar("");
    }
}
