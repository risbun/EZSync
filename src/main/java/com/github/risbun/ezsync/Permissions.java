package com.github.risbun.ezsync;

import net.milkbowl.vault.permission.Permission;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Permissions {
    private LuckPerms luckPerms;
    private Permission vault;

    public boolean initialize() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Permission> provider = Bukkit.getServicesManager().getRegistration(Permission.class);
            if (provider != null) {
                vault = provider.getProvider();
                return true;
            }
        } else if (Bukkit.getServer().getPluginManager().getPlugin("LuckPerms") != null) {
            RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
            if (provider != null) {
                luckPerms = provider.getProvider();
                return true;
            }
        }
        return false;
    }

    public boolean hasPermission(OfflinePlayer player, String perm) {
        if (luckPerms != null) {
            return luckPerms.getUserManager().loadUser(player.getUniqueId()).join().getCachedData().getPermissionData().checkPermission(perm).asBoolean();
        } else if (vault != null) {
            return vault.playerHas(Bukkit.getWorlds().get(0).getName(), player, perm);
        }
        return false;
    }
}
