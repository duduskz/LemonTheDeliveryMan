package cn.seabedcraft.lemonthedeliveryman.Command;

import cn.seabedcraft.lemonthedeliveryman.LemonTheDeliveryMan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MainCommand implements CommandExecutor  {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Plugin plugin = LemonTheDeliveryMan.getPlugin(LemonTheDeliveryMan.class);
        if (commandSender.hasPermission("TheDeliveryMan.admin")) {
            if (strings.length == 1) {
                String commandargs1 = strings[0];
                if (commandargs1.equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    commandSender.sendMessage("§a插件配置已重载！");
                    return true;
                }
            }
            if (strings.length == 2) {
                String commandargs1 = strings[0];
                String commandargs2 = strings[1];
                if (commandargs1.equalsIgnoreCase("add")) {
                    Add.add((Player) commandSender, commandargs2, plugin);
                    return true;
                }
                if (commandargs1.equalsIgnoreCase("remove")) {
                    Remove.remove((Player) commandSender, commandargs2, plugin);
                    return true;
                }
            }
            Help.Help((Player) commandSender);
        } else {
            commandSender.sendMessage("§c此服务器运行着 v1.0 版本的 LemonTheDeliveryMan");
            commandSender.sendMessage("§aby duduskz(LemonNetwork)");
        }
        return false;
    }
}
