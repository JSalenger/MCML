package com.jbsalenger.p2p;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawTask extends BukkitRunnable {

    private final JavaPlugin PLUGIN;
    private int x;
    private int z;
    private final Player player;


    DrawTask(JavaPlugin _plugin, int _x, int _z, Player _player) {
        PLUGIN = _plugin;
        x = _x;
        z = _z;
        player = _player;
    }

    @Override
    public void run() {
        // create image with width and height of 244 because CNNs like multiples of 7
        BufferedImage bf_img = new BufferedImage(244, 244, BufferedImage.TYPE_INT_ARGB);

        Graphics g = bf_img.getGraphics();

        // set background to black
        for(int i = 0; i < 30; i++) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 244, 244);

            g.setColor(Color.BLUE);
            // c stands for current
            int cX = player.getLocation().getBlockX();
            int cZ = player.getLocation().getBlockZ();

            g.drawLine(x, z, cX, cZ);

            player.sendMessage("oX: " + x + " | oY: " + z + " || x: " + cX + " | y: " + cZ);

            // reset origin values
            x = cX;
            z = cZ;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // create the output file
        File of = new File("saved.png");

        try {
            ImageIO.write(bf_img, "png", of);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
