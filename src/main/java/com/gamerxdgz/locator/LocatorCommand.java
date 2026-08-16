package com.gamerxdgz.locator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class LocatorCommand implements CommandExecutor {

    private final GamerXDGZLocatorForMinecraft plugin;

    public LocatorCommand(
            GamerXDGZLocatorForMinecraft plugin
    ) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        // /locator info
        if (args.length > 0 &&
                args[0].equalsIgnoreCase("info")) {

            sender.sendMessage(
                    ChatColor.AQUA +
                    "GamerXDGZLocatorForMinecraft"
            );

            sender.sendMessage(
                    ChatColor.GRAY +
                    "Platform: " +
                    ChatColor.WHITE +
                    plugin.getPlatformManager()
                            .getPlatformName()
            );

            sender.sendMessage(
                    ChatColor.GRAY +
                    "ActionBar: " +
                    ChatColor.WHITE +
                    plugin.getPlatformManager()
                            .supportsActionBar()
            );

            sender.sendMessage(
                    ChatColor.GRAY +
                    "Legacy clients: " +
                    ChatColor.WHITE +
                    plugin.getPlatformManager()
                            .supportsLegacyClients()
            );

            sender.sendMessage(
                    ChatColor.GRAY +
                    "Eagler integration: " +
                    ChatColor.WHITE +
                    plugin.getEaglerIntegrationManager()
                            .getPlatformName()
            );

            sender.sendMessage(
                    ChatColor.GRAY +
                    "EaglerXServer detected: " +
                    ChatColor.WHITE +
                    plugin.getEaglerIntegrationManager()
                            .isAvailable()
            );

            return true;
        }

        // Commands below this point require a player.
        if (!(sender instanceof Player)) {

            sender.sendMessage(
                    ChatColor.RED +
                    "This command can only be used by a player."
            );

            return true;
        }

        Player player = (Player) sender;

        // Permission check.
        if (!player.hasPermission(
                "gamerxdgzlocator.use"
        )) {

            player.sendMessage(
                    ChatColor.RED +
                    "You don't have permission to use the locator."
            );

            return true;
        }

        // /locator
        // /locator toggle
        if (args.length == 0 ||
                args[0].equalsIgnoreCase("toggle")) {

            boolean enabled =
                    plugin.getLocatorManager()
                            .toggleLocator(player);

            if (enabled) {

                player.sendMessage(
                        ChatColor.GREEN +
                        "Locator enabled."
                );

            } else {

                player.sendMessage(
                        ChatColor.YELLOW +
                        "Locator disabled."
                );
            }

            return true;
        }

        // /locator on
        if (args[0].equalsIgnoreCase("on")) {

            plugin.getLocatorManager()
                    .setLocatorEnabled(
                            player,
                            true
                    );

            player.sendMessage(
                    ChatColor.GREEN +
                    "Locator enabled."
            );

            return true;
        }

        // /locator off
        if (args[0].equalsIgnoreCase("off")) {

            plugin.getLocatorManager()
                    .setLocatorEnabled(
                            player,
                            false
                    );

            player.sendMessage(
                    ChatColor.YELLOW +
                    "Locator disabled."
            );

            return true;
        }

        // /locator reload
        if (args[0].equalsIgnoreCase("reload")) {

            if (!player.hasPermission(
                    "gamerxdgzlocator.reload"
            )) {

                player.sendMessage(
                        ChatColor.RED +
                        "You don't have permission to reload the locator."
                );

                return true;
            }

            plugin.reloadConfig();

            player.sendMessage(
                    ChatColor.GREEN +
                    "Configuration reloaded."
            );

            return true;
        }

        // Unknown command.
        player.sendMessage(
                ChatColor.RED +
                "Usage: /locator [on|off|toggle|reload|info]"
        );

        return true;
    }
}
