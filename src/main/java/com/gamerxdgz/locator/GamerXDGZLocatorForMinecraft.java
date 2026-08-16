package com.gamerxdgz.locator;

import org.bukkit.plugin.java.JavaPlugin;

public final class GamerXDGZLocatorForMinecraft extends JavaPlugin {

    private LocatorManager locatorManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        locatorManager = new LocatorManager(this);

        if (getCommand("locator") != null) {
            getCommand("locator").setExecutor(
                    new LocatorCommand(this)
            );
        }

        getLogger().info(
                "GamerXDGZLocatorForMinecraft has been enabled!"
        );
    }

    @Override
    public void onDisable() {
        locatorManager = null;

        getLogger().info(
                "GamerXDGZLocatorForMinecraft has been disabled!"
        );
    }

    public LocatorManager getLocatorManager() {
        return locatorManager;
    }
}