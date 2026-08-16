package com.gamerxdgz.locator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public final class FallbackLocatorDisplay
        implements LegacyLocatorDisplay {

    @Override
    public boolean isSupported() {
        return true;
    }

    @Override
    public String getDisplayName() {
        return "Fallback";
    }

    @Override
    public void update(
            Player viewer,
            List<LocatorData> data
    ) {

        if (viewer == null || !viewer.isOnline()) {
            return;
        }

        if (data == null || data.isEmpty()) {
            viewer.sendMessage(
                    ChatColor.GRAY +
                    "[Locator] No nearby players."
            );
            return;
        }

        LocatorData closest = data.get(0);

        Player target = closest.getPlayer();

        if (target == null || !target.isOnline()) {
            return;
        }

        viewer.sendMessage(
                ChatColor.AQUA +
                "[Locator] " +
                ChatColor.WHITE +
                target.getName() +
                ChatColor.GRAY +
                " - " +
                (int) closest.getDistance() +
                " blocks"
        );
    }

    @Override
    public void clear(Player viewer) {
        // No persistent UI to clear.
    }
}
