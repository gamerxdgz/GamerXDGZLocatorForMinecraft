package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

import java.util.List;

public interface LegacyLocatorDisplay extends LocatorDisplay {

    boolean isSupported();

    String getDisplayName();

    @Override
    void update(Player viewer, List<LocatorData> data);

    @Override
    void clear(Player viewer);
}
