package com.github.risbun.ezsync;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    private Permissions perms;

    @Override
    public void onEnable() {
        // Plugin startup logic

        perms = new Permissions();
        if (!perms.initialize()) {
            perms = null;
            getLogger().warning("Vault or LuckPerms is required for bypass permission, though OP can still bypass.");
        }

        getServer().getPluginManager().registerEvents(new Events(this), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public Logger getLogger() {
        return super.getLogger();
    }

    public Permissions getPerms() {
        return perms;
    }
}
