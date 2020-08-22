package com.jbsalenger.p2p;

/*
 * P2p command
 * this is NOT the main class the main class is P2p
 * this is P2pc ( Player to picture command )
 */

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class P2pc implements CommandExecutor {

    private final JavaPlugin PLUGIN;
    static boolean isRecording;

    P2pc(JavaPlugin _plugin) {
        PLUGIN = _plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            Vector velocity = player.getVelocity();

            player.sendMessage(velocity.toString());

            // o stands for origin or original
            int oX = player.getLocation().getBlockX();
            int oZ = player.getLocation().getBlockZ();

            // add a one tick delay because bukkit prefers that for god knows what reason
            //BukkitTask task = new DrawTask(PLUGIN, oX, oZ, player).runTaskLater(PLUGIN, 20);


            Bukkit.getServer().getScheduler().runTaskAsynchronously(PLUGIN, new Runnable() {
                @Override
                public void run() {
                    isRecording = true;
                    // DO NOT modify synchronous vars inside async runnable
                    // it is safe to read them, however
                    int x = oX;
                    int z = oZ;

                    // create image with width and height of 244 because CNNs like multiples of 7
                    BufferedImage bf_img = new BufferedImage(98, 98, BufferedImage.TYPE_INT_ARGB);

                    Graphics g = bf_img.getGraphics();

                    // set background to black
                    g.setColor(Color.black);
                    g.fillRect(0, 0, 98, 98);

                    Color lineColor = Color.BLUE;

                    // run 300 times pause for 100 ms every time this runs for 30seconds
                    for (int i = 0; i < 300; i++) {


                        g.setColor(lineColor);
                        // c stands for current
                        int cX = player.getLocation().getBlockX();
                        int cZ = player.getLocation().getBlockZ();

                        g.drawLine(x, z, cX, cZ);

                        player.sendMessage("oX: " + x + " | oZ: " + z + " || x: " + cX + " | z: " + cZ);

                        // reset origin values
                        x = cX;
                        z = cZ;

                        if (lineColor.equals(Color.BLUE)) {
                            lineColor = Color.GREEN;
                        } else {
                            lineColor = Color.BLUE;
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    // add attack dots to photo
                    g.setColor(Color.RED);
                    P2p.attackLocations.forEach((location -> g.fillRect(location.getBlockX(), location.getBlockZ(), 1, 1)));

                    ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
                    String filename = player.getDisplayName() + "_" + utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".png";

                    // create the output file
                    File of = new File("images/" + filename);
                    player.sendMessage("Done");

                    try {
                        ImageIO.write(bf_img, "png", of);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // i'm pretty sure that you're not supposed to use this method as its very dangerous
                    // but it does what i need it to so... too bad!
                    P2p.attackLocations.clear();

                    isRecording = false;
                }
            });

            return true;
        }


        return false;
    }

    public static boolean isRecording() {
        return isRecording;
    }

}
