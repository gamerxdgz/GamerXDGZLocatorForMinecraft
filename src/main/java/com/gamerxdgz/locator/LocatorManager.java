package com.gamerxdgz.locator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LocatorManager {

    private final GamerXDGZLocatorForMinecraft plugin;

    public LocatorManager(GamerXDGZLocatorForMinecraft plugin) {
        this.plugin = plugin;
    }

    /**
     * Gets players that are within the configured locator range.
     *
     * @param viewer the player using the locator
     * @return a safe list of nearby players
     */
    public List<Player> getNearbyPlayers(Player viewer) {

        if (viewer == null || !viewer.isOnline()) {
            return Collections.emptyList();
        }

        if (!viewer.hasPermission(
                plugin.getConfig().getString(
                        "security.permission",
                        "gamerxdgzlocator.use"
                ))) {
            return Collections.emptyList();
        }

        Location viewerLocation = viewer.getLocation();
        World viewerWorld = viewer.getWorld();

        if (viewerLocation == null || viewerWorld == null) {
            return Collections.emptyList();
        }

        boolean sameWorldOnly = plugin.getConfig()
                .getBoolean("performance.same-world-only", true);

        int range = plugin.getConfig()
                .getInt("locator.range", 128);

        int maxPlayers = plugin.getConfig()
                .getInt("locator.max-players", 20);

        if (maxPlayers < 1) {
            return Collections.emptyList();
        }

        List<PlayerDistance> candidates = new ArrayList<>();

        for (Player target : viewer.getServer().getOnlinePlayers()) {

            if (target.equals(viewer)) {
                continue;
            }

            if (!target.isOnline()) {
                continue;
            }

            if (sameWorldOnly && target.getWorld() != viewerWorld) {
                continue;
            }

            if (plugin.getConfig()
                    .getBoolean("security.respect-vanish", true)
                    && target.hasMetadata("vanished")) {
                continue;
            }

            Location targetLocation = target.getLocation();

            if (targetLocation == null) {
                continue;
            }

            double distanceSquared =
                    viewerLocation.distanceSquared(targetLocation);

            if (range > 0 && distanceSquared > (double) range * range) {
                continue;
            }

            candidates.add(
                    new PlayerDistance(target, distanceSquared)
            );
        }

        Collections.sort(
                candidates,
                (first, second) ->
                        Double.compare(
                                first.distanceSquared,
                                second.distanceSquared
                        )
        );

        List<Player> result = new ArrayList<>();

        int limit = Math.min(maxPlayers, candidates.size());

        for (int i = 0; i < limit; i++) {
            result.add(candidates.get(i).player);
        }

        return result;
    }

    private static final class PlayerDistance {

        private final Player player;
        private final double distanceSquared;

        private PlayerDistance(
                Player player,
                double distanceSquared
        ) {
            this.player = player;
            this.distanceSquared = distanceSquared;
        }
    }
}