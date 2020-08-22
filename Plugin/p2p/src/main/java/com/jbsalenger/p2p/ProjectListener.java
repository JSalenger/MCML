package com.jbsalenger.p2p;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ProjectListener implements Listener {

    private final JavaPlugin PLUGIN;

    ProjectListener(JavaPlugin _plugin) {
        PLUGIN = _plugin;
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        event.getDamager().sendMessage("dab");
        if(event.getDamager() instanceof Player && P2pc.isRecording()) {
            P2p.attackLocations.add(event.getDamager().getLocation());
        }
    }
}
