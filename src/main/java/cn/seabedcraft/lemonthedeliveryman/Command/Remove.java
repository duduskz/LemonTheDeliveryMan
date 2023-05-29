package cn.seabedcraft.lemonthedeliveryman.Command;

import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Remove {
    public static void remove(Player player, String name, Plugin plugin) {
        FileConfiguration config = plugin.getConfig();
        if (config.get("Map."+name) != null) {
            CitizensAPI.removeNamedNPCRegistry(name);
            player.sendMessage("§a删除了一个礼包使者: §e" + name);
            config.set("Map."+name, null);
            plugin.saveConfig();
        } else {
            player.sendMessage("§c不存在这个礼包使者: §e" + name);
        }

    }
}
