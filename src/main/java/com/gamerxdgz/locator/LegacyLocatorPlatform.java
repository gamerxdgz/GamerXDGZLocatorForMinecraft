package com.gamerxdgz.locator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class LegacyLocatorPlatform
        implements LocatorPlatform {

    @Override
    public String getPlatformName() {
        return "Legacy Minecraft";
    }

    @Override
    public boolean supportsActionBar() {
        return false;
    }

    @Override
    public boolean supportsLegacyClients() {
        return true;
    }

    @Override
    public void sendLocator(
            Player player,
            String message
    ) {

        if (player == null || !player.isOnline()) {
            return;
        }

        player.sendMessage(
                ChatColor.AQUA +
                "[Locator] " +
                ChatColor.WHITE +
                message
        );
    }

    @Override
    public void clearLocator(Player player) {
        // Legacy fallback has no persistent HUD.
    }
}
