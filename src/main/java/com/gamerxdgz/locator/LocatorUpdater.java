package com.gamerxdgz.locator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public final class LocatorUpdater {

    private final GamerXDGZLocatorForMinecraft plugin;

    private BukkitTask task;

    public LocatorUpdater(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        this.plugin = plugin;
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

        final long updateInterval = interval;

        task = Bukkit.getScheduler().runTaskTimer(
                plugin,
                this::update,
                updateInterval,
                updateInterval
        );
    }

    private void update() {

        LocatorManager manager =
                plugin.getLocatorManager();

        if (manager == null) {
            return;
        }

        for (Player player :
                Bukkit.getOnlinePlayers()) {

            if (!manager.isLocatorEnabled(player)) {
                continue;
            }

            List<LocatorData> data =
                    manager.getLocatorData(player);

            /*
             * HUD/display integration will use
             * this data in a later step.
             */
            if (data.isEmpty()) {
                continue;
            }
        }
    }

    public void stop() {

        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}