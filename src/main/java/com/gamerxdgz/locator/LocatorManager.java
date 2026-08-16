package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class LocatorManager {

    private final GamerXDGZLocatorForMinecraft plugin;

    private final Set<UUID> disabledPlayers =
            new HashSet<>();

    private final LocatorDisplay display;

    public LocatorManager(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        this.plugin = plugin;

        this.display =
                LocatorDisplayFactory.create(plugin);
    }

    public boolean isLocatorEnabled(Player player) {

        if (player == null) {
            return false;
        }

        if (!plugin.getConfig().getBoolean(
                "locator.enabled",
                true
        )) {
            return false;
        }

        return !disabledPlayers.contains(
                player.getUniqueId()
        );
    }

    public boolean toggleLocator(Player player) {

        if (player == null) {
            return false;
        }

        UUID uuid = player.getUniqueId();

        if (disabledPlayers.contains(uuid)) {

            disabledPlayers.remove(uuid);

            return true;
        }

        disabledPlayers.add(uuid);

        display.clear(player);

        return false;
    }

    public void setLocatorEnabled(
            Player player,
            boolean enabled
    ) {

        if (player == null) {
            return;
        }

        UUID uuid = player.getUniqueId();

        if (enabled) {

            disabledPlayers.remove(uuid);

        } else {

            disabledPlayers.add(uuid);

            display.clear(player);
        }
    }

    public void updateLocator(
            Player player,
            List<LocatorData> data
    ) {

        if (player == null ||
                !player.isOnline()) {
            return;
        }

        if (!isLocatorEnabled(player)) {
            return;
        }

        display.update(
                player,
                data
        );
    }

    public void clearLocator(Player player) {

        if (player == null) {
            return;
        }

        display.clear(player);
    }

    public void clearAll() {

        for (Player player :
                plugin.getServer().getOnlinePlayers()) {

            display.clear(player);
        }

        disabledPlayers.clear();
    }
}
