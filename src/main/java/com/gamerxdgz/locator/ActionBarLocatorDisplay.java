package com.gamerxdgz.locator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public final class ActionBarLocatorDisplay implements LocatorDisplay {

    @Override
    public void update(
            Player viewer,
            List<LocatorData> data
    ) {

        if (viewer == null || !viewer.isOnline()) {
            return;
        }

        if (data == null || data.isEmpty()) {
            viewer.sendActionBar(
                    ChatColor.GRAY + "No nearby players"
            );
            return;
        }

        StringBuilder bar = new StringBuilder();

        for (LocatorData locator : data) {

            Player target = locator.getPlayer();

            if (target == null || !target.isOnline()) {
                continue;
            }

            double angle = locator.getAngle();
            double distance = locator.getDistance();

            String marker;

            if (Math.abs(angle) <= 15) {
                marker = "▲";
            } else if (angle > 0) {
                marker = "▶";
            } else {
                marker = "◀";
            }

            bar.append(ChatColor.AQUA)
                    .append(marker);

            if (bar.length() > 0) {
                bar.append(ChatColor.WHITE)
                        .append(" ")
                        .append(target.getName());
            }

            if (locator.getDistance() >= 0) {
                bar.append(ChatColor.GRAY)
                        .append(" ")
                        .append((int) distance)
                        .append("m");
            }

            bar.append("  ");
        }

        viewer.sendActionBar(bar.toString());
    }

    @Override
    public void clear(Player viewer) {

        if (viewer == null || !viewer.isOnline()) {
            return;
        }

        viewer.sendActionBar("");
    }
}