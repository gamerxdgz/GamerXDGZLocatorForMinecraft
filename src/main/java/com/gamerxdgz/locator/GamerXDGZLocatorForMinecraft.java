package com.gamerxdgz.locator;

import org.bukkit.plugin.java.JavaPlugin;

public final class GamerXDGZLocatorForMinecraft extends JavaPlugin {

    private LocatorManager locatorManager;
    private LocatorUpdater locatorUpdater;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        locatorManager =
                new LocatorManager(this);

        if (getCommand("locator") != null) {
            getCommand("locator").setExecutor(
                    new LocatorCommand(this)
            );
        }

        locatorUpdater =
                new LocatorUpdater(this);

        locatorUpdater.start();

        getLogger().info(
                "GamerXDGZLocatorForMinecraft has been enabled!"
        );
    }

    @Override
    public void onDisable() {

        if (locatorUpdater != null) {
            locatorUpdater.stop();
        }

        locatorUpdater = null;
        locatorManager = null;

        getLogger().info(
                "GamerXDGZLocatorForMinecraft has been disabled!"
        );
    }

    public LocatorManager getLocatorManager() {
        return locatorManager;
    }

    public LocatorUpdater getLocatorUpdater() {
        return locatorUpdater;
    }
}