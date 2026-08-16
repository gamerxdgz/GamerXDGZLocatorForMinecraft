package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

import java.util.List;

public final class LocatorBar {

    private static final int BAR_WIDTH = 41;

    private LocatorBar() {
    }

    public static String create(
            Player viewer,
            List<LocatorData> players
    ) {
        char[] bar = new char[BAR_WIDTH];

        for (int i = 0; i < BAR_WIDTH; i++) {
            bar[i] = '·';
        }

        int center = BAR_WIDTH / 2;

        bar[center] = '▲';

        if (players != null) {

            for (LocatorData data : players) {

                if (data == null) {
                    continue;
                }

                Player target = data.getPlayer();

                if (target == null ||
                        !target.isOnline()) {
                    continue;
                }

                double angle = data.getAngle();

                if (angle < -180) {
                    angle = -180;
                }

                if (angle > 180) {
                    angle = 180;
                }

                int position =
                        center +
                        (int) Math.round(
                                (angle / 180.0)
                                        * center
                        );

                if (position < 0) {
                    position = 0;
                }

                if (position >= BAR_WIDTH) {
                    position = BAR_WIDTH - 1;
                }

                if (position == center) {
                    bar[position] = '●';
                } else {
                    bar[position] = '●';
                }
            }
        }

        return new String(bar);
    }
}
