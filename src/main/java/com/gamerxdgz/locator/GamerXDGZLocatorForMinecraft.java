package com.gamerxdgz.locator;

import org.bukkit.plugin.java.JavaPlugin;

public final class GamerXDGZLocatorForMinecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("GamerXDGZLocatorForMinecraft has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GamerXDGZLocatorForMinecraft has been disabled!");
    }
}