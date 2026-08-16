package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

import java.util.List;

public interface LocatorDisplay {

    void update(
            Player viewer,
            List<LocatorData> data
    );

    void clear(Player viewer);
}