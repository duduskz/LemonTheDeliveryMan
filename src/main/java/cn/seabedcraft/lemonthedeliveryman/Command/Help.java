package cn.seabedcraft.lemonthedeliveryman.Command;

import org.bukkit.entity.Player;

public class Help {
    
    public static void Help(Player player) {
        player.sendMessage("§bLemon§dTheDeliveryMan §a- §ev1.0");
        player.sendMessage("");
        player.sendMessage("§b/TheDeliveryMan reload §a- §e重载配置文件");
        player.sendMessage("§b/TheDeliveryMan add <名称> §a- §e生成一个礼包使者");
        player.sendMessage("§b/TheDeliveryMan reload <名称> §a- §e删除一个礼包使者");
    }
}
