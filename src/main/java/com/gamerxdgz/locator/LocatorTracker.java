package com.gamerxdgz.locator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class LocatorTracker {

    private final GamerXDGZLocatorForMinecraft plugin;

    public LocatorTracker(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        this.plugin = plugin;
    }

    public List<LocatorData> findNearbyPlayers(
            Player viewer
    ) {

        List<LocatorData> results =
                new ArrayList<>();

        if (viewer == null ||
                !viewer.isOnline()) {
            return results;
        }

        Location viewerLocation =
                viewer.getLocation();

        World viewerWorld =
                viewer.getWorld();

      double range =
        plugin.getConfig().getDouble(
                "locator.range",
                128.0
        );

double maximumRange =
        plugin.getConfig().getDouble(
                "performance.maximum-range",
                256.0
        );

if (maximumRange < 1.0) {
    maximumRange = 256.0;
}

range = Math.min(
        range,
        maximumRange
);

        boolean sameWorldOnly =
                plugin.getConfig().getBoolean(
                        "performance.same-world-only",
                        true
                );

        for (Player target :
                viewer.getServer().getOnlinePlayers()) {

            if (target.equals(viewer)) {
                continue;
            }

            if (!target.isOnline()) {
                continue;
            }

            if (sameWorldOnly &&
                    !target.getWorld().equals(viewerWorld)) {
                continue;
            }

            Location targetLocation =
                    target.getLocation();

            double distanceSquared =
                    viewerLocation.distanceSquared(
                            targetLocation
                    );

            if (distanceSquared >
                    range * range) {
                continue;
            }

            double distance =
                    Math.sqrt(distanceSquared);

            double angle =
                    calculateRelativeAngle(
                            viewerLocation,
                            targetLocation
                    );

            results.add(
                    new LocatorData(
                            target,
                            distance,
                            angle
                    )
            );
        }

        results.sort(
                Comparator.comparingDouble(
                        LocatorData::getDistance
                )
        );

        int maximum =
                plugin.getConfig().getInt(
                        "locator.max-players",
                        20
                );

        if (maximum < 1) {
            maximum = 1;
        }

        if (results.size() > maximum) {

            return new ArrayList<>(
                    results.subList(
                            0,
                            maximum
                    )
            );
        }

        return results;
    }

    private double calculateRelativeAngle(
            Location viewer,
            Location target
    ) {

        double dx =
                target.getX() -
                viewer.getX();

        double dz =
                target.getZ() -
                viewer.getZ();

        double targetAngle =
                Math.toDegrees(
                        Math.atan2(
                                -dx,
                                dz
                        )
                );

        double viewerYaw =
                viewer.getYaw();

        double relative =
                targetAngle -
                viewerYaw;

        while (relative <= -180) {
            relative += 360;
        }

        while (relative > 180) {
            relative -= 360;
        }

        return relative;
    }
}
