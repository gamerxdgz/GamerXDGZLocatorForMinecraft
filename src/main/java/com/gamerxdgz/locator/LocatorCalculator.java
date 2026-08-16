package com.gamerxdgz.locator;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class LocatorCalculator {

    private LocatorCalculator() {
    }

    public static LocatorData calculate(
            Player viewer,
            Player target
    ) {
        Location viewerLocation = viewer.getLocation();
        Location targetLocation = target.getLocation();

        double dx =
                targetLocation.getX() - viewerLocation.getX();

        double dz =
                targetLocation.getZ() - viewerLocation.getZ();

        double distance =
                Math.sqrt((dx * dx) + (dz * dz));

        double targetAngle =
                Math.toDegrees(Math.atan2(-dx, dz));

        double viewerYaw =
                viewerLocation.getYaw();

        double relativeAngle =
                normalizeAngle(targetAngle - viewerYaw);

        return new LocatorData(
                target,
                distance,
                relativeAngle
        );
    }

    private static double normalizeAngle(double angle) {

        while (angle > 180) {
            angle -= 360;
        }

        while (angle < -180) {
            angle += 360;
        }

        return angle;
    }
}