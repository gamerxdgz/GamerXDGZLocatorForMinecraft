package com.gamerxdgz.locator;

import org.bukkit.entity.Player;

public final class EaglerIntegrationManager {

    private final EaglerIntegration integration;

    public EaglerIntegrationManager(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        /*
         * EaglerXServer integration is intentionally isolated.
         *
         * The plugin remains fully functional on normal
         * Minecraft servers when EaglerXServer is absent.
         */
        this.integration = new NoEaglerIntegration();
    }

    public EaglerIntegration getIntegration() {
        return integration;
    }

    public boolean isAvailable() {
        return integration.isAvailable();
    }

    public boolean isEaglerPlayer(Player player) {
        return integration.isEaglerPlayer(player);
    }

    public String getPlatformName() {
        return integration.getPlatformName();
    }
}
