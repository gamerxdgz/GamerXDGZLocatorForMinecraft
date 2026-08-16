package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

public interface EaglerIntegration {

    boolean isAvailable();

    boolean isEaglerPlayer(Player player);

    String getPlatformName();
}
