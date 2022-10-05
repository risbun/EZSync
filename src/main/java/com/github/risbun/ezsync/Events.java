package com.github.risbun.ezsync;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class Events implements Listener {

    private final Main plugin;

    public Events(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(event.getUniqueId());

        // bypass for OP and permission
        if (player.isOp() || (plugin.getPerms() != null && plugin.getPerms().hasPermission(player, "ezsync.bypass"))) {
            event.allow();
            plugin.getLogger().info(String.format("%s (%s) allowed: BYPASS", event.getName(), event.getUniqueId()));
            return;
        }

        // temp kick
        plugin.getLogger().info(String.format("%s (%s) denied: TEMP", event.getName(), event.getUniqueId()));
        event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "no");
    }

}
