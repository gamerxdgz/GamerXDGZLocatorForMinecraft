package com.gamerxdgz.locator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public final class FormattedLocatorDisplay
        implements LocatorDisplay {

    @Override
    public void update(
            Player viewer,
            List<LocatorData> data
    ) {

        if (viewer == null || !viewer.isOnline()) {
            return;
        }

        String bar = LocatorBar.create(
                viewer,
                data
        );

        String message =
                ChatColor.WHITE +
                bar;

        viewer.sendActionBar(message);
    }

    @Override
    public void clear(Player viewer) {

        if (viewer == null || !viewer.isOnline()) {
            return;
        }

        viewer.sendActionBar("");
    }
}
