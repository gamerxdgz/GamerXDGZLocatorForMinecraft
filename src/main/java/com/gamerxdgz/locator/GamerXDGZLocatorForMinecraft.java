package com.gamerxdgz.locator;

import org.bukkit.plugin.java.JavaPlugin;

public final class GamerXDGZLocatorForMinecraft extends JavaPlugin {

    private LocatorManager locatorManager;
    private LocatorUpdater locatorUpdater;
    private LocatorPlatformManager platformManager;
    private EaglerIntegrationManager eaglerIntegrationManager;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        platformManager =
                new LocatorPlatformManager(this);

        eaglerIntegrationManager =
                new EaglerIntegrationManager(this);

        locatorManager =
                new LocatorManager(this);

        if (getCommand("locator") != null) {

            getCommand("locator").setExecutor(
                    new LocatorCommand(this)
            );

        } else {

            getLogger().severe(
                    "The /locator command is missing from plugin.yml!"
            );
        }

        locatorUpdater =
                new LocatorUpdater(this);

        locatorUpdater.start();

        getLogger().info(
                "GamerXDGZLocatorForMinecraft enabled."
        );

        getLogger().info(
                "Platform: "
                        + platformManager.getPlatformName()
        );

        getLogger().info(
                "Eagler integration: "
                        + eaglerIntegrationManager
                        .getPlatformName()
        );
    }

    @Override
    public void onDisable() {

        if (locatorUpdater != null) {
            locatorUpdater.stop();
        }

        locatorUpdater = null;
        locatorManager = null;
        platformManager = null;
        eaglerIntegrationManager = null;

        getLogger().info(
                "GamerXDGZLocatorForMinecraft disabled."
        );
    }

    public LocatorManager getLocatorManager() {
        return locatorManager;
    }

    public LocatorUpdater getLocatorUpdater() {
        return locatorUpdater;
    }

    public LocatorPlatformManager getPlatformManager() {
        return platformManager;
    }

    public EaglerIntegrationManager
    getEaglerIntegrationManager() {
        return eaglerIntegrationManager;
    }
}
