package com.jbsalenger.p2p;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class P2p extends JavaPlugin {

    // yes i know its bad to create a public global variable... too bad!
    public static ArrayList<Location> attackLocations = new ArrayList<Location>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("Starting up.");

        this.getCommand("p2p").setExecutor(new P2pc(this));

        this.getServer().getPluginManager().registerEvents(new ProjectListener(this), this);

        Bukkit.getConsoleSender().sendMessage("Done!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getScheduler().cancelTasks(this);
        Bukkit.getConsoleSender().sendMessage("Goodbye.");
    }
}
