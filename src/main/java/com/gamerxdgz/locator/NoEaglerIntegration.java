package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

public final class NoEaglerIntegration
        implements EaglerIntegration {

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean isEaglerPlayer(Player player) {
        return false;
    }

    @Override
    public String getPlatformName() {
        return "EaglerXServer not detected";
    }
}
