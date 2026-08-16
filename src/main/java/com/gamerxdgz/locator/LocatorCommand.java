package com.gamerxdgz.locator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class LocatorCommand implements CommandExecutor {

    private final GamerXDGZLocatorForMinecraft plugin;

    public LocatorCommand(GamerXDGZLocatorForMinecraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(
                    ChatColor.RED +
                    "This command can only be used by a player."
            );
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("gamerxdgzlocator.use")) {
            player.sendMessage(
                    ChatColor.RED +
                    "You don't have permission to use the locator."
            );
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("toggle")) {

            boolean enabled = plugin.getLocatorManager()
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

        if (args[0].equalsIgnoreCase("on")) {

            plugin.getLocatorManager().setLocatorEnabled(
                    player,
                    true
            );

            player.sendMessage(
                    ChatColor.GREEN +
                    "Locator enabled."
            );

            return true;
        }

        if (args[0].equalsIgnoreCase("off")) {

            plugin.getLocatorManager().setLocatorEnabled(
                    player,
                    false
            );

            player.sendMessage(
                    ChatColor.YELLOW +
                    "Locator disabled."
            );

            return true;
        }

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
                    "GamerXDGZLocatorForMinecraft configuration reloaded."
            );

            return true;
        }

        player.sendMessage(
                ChatColor.RED +
                "Usage: /locator [on|off|toggle|reload]"
        );

        return true;
    }
}