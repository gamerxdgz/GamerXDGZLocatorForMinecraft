package com.gamerxdgz.locator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public final class LocatorUpdater {

    private final GamerXDGZLocatorForMinecraft plugin;

    private final LocatorTracker tracker;

    private BukkitTask task;

    public LocatorUpdater(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        this.plugin = plugin;
        this.tracker = new LocatorTracker(plugin);
    }

    public void start() {

        stop();

        long interval =
                plugin.getConfig().getLong(
                        "locator.update-interval-ticks",
                        10L
                );

        if (interval < 1L) {
            interval = 1L;
        }

        task = Bukkit.getScheduler().runTaskTimer(
                plugin,
                this::updatePlayers,
                1L,
                interval
        );
    }

    private void updatePlayers() {

        for (Player player :
                Bukkit.getOnlinePlayers()) {

            if (!player.isOnline()) {
                continue;
            }

            if (!plugin.getLocatorManager()
                    .isLocatorEnabled(player)) {
                continue;
            }

            List<LocatorData> nearbyPlayers =
                    tracker.findNearbyPlayers(player);

            plugin.getLocatorManager()
                    .updateLocator(
                            player,
                            nearbyPlayers
                    );
        }
    }

    public void stop() {

        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}
