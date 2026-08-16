package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

public final class LocatorData {

    private final Player player;
    private final double distance;
    private final double angle;

    public LocatorData(Player player, double distance, double angle) {
        this.player = player;
        this.distance = distance;
        this.angle = angle;
    }

    public Player getPlayer() {
        return player;
    }

    public double getDistance() {
        return distance;
    }

    public double getAngle() {
        return angle;
    }
}