package cn.seabedcraft.lemonthedeliveryman.Command;

import cn.seabedcraft.lemonthedeliveryman.Hologram;
import cn.seabedcraft.lemonthedeliveryman.NPC;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.text.DecimalFormat;

public class Add {
    public static void add(Player player, String name, Plugin plugin) {
        FileConfiguration config = plugin.getConfig();
        if (config.get("Map."+name) == null) {
            player.sendMessage("§a添加了一个礼包使者: §e"+name);
        } else {
            player.sendMessage("§a修改了一个礼包使者位置: §e"+name);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        config.set("Map."+name+".Location", decimalFormat.format(player.getLocation().getX()) + "," + decimalFormat.format(player.getLocation().getY()) + "," + decimalFormat.format(player.getLocation().getZ()) + "," + (int) player.getLocation().getYaw() + "," + (int) player.getLocation().getPitch());
        config.set("Map."+name+".World", player.getLocation().getWorld().getName());
        plugin.saveConfig();
        Hologram.create(player, false);
        NPC.npc(name);
    }
}
