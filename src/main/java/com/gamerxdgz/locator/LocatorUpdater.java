package com.gamerxdgz.locator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public final class LocatorUpdater {

    private final GamerXDGZLocatorForMinecraft plugin;

    private final LocatorDisplay display;

    private BukkitTask task;

    public LocatorUpdater(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        this.plugin = plugin;
        this.display = new ActionBarLocatorDisplay();
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
                this::update,
                interval,
                interval
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
                display.clear(player);
                continue;
            }

            List<LocatorData> data =
                    manager.getLocatorData(player);

            display.update(
                    player,
                    data
            );
        }
    }

    public void stop() {

        if (task != null) {
            task.cancel();
            task = null;
        }

        for (Player player :
                Bukkit.getOnlinePlayers()) {

            display.clear(player);
        }
    }
}