package com.gamerxdgz.locator;

import org.bukkit.plugin.java.JavaPlugin;

public final class GamerXDGZLocatorForMinecraft extends JavaPlugin {

    private LocatorManager locatorManager;
    private LocatorUpdater locatorUpdater;
    private LocatorPlatformManager platformManager;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        platformManager =
                new LocatorPlatformManager(this);

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
    }

    @Override
    public void onDisable() {

        if (locatorUpdater != null) {
            locatorUpdater.stop();
        }

        locatorUpdater = null;
        locatorManager = null;
        platformManager = null;

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
}
