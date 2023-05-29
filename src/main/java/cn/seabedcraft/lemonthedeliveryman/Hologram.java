package cn.seabedcraft.lemonthedeliveryman;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Hologram implements Listener {
    public static HashMap<Player, ArrayList<Integer>> holograms = new HashMap<>();



    public static void removeHologram(Player player, int hologramEntityId) {
        PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(hologramEntityId);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroyPacket);
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        holograms.put(event.getPlayer(), new ArrayList<>());
        create(event.getPlayer(), true);
    }
    @EventHandler
    public void quit(PlayerQuitEvent event) {
        holograms.remove(event.getPlayer());
    }

    public static void create(Player player, Boolean All) {
        ArrayList<String> lore = new ArrayList<>();
        String lang = LemonTheDeliveryMan.getPlayerLang(player);
        if (lang.equalsIgnoreCase("zhcn")) {
            lore.add("§b礼包使者");
            lore.add("§e§l右键点击");
        } else if (lang.equalsIgnoreCase("en")) {
            lore.add("§bThe Delivery Man");
            lore.add("§e§lRIGHT CLICK");
        }
        if (All) {
            Plugin plugin = LemonTheDeliveryMan.getPlugin(LemonTheDeliveryMan.class);
            if (plugin.getConfig().getConfigurationSection("Map") != null) {
                for (String npc : plugin.getConfig().getConfigurationSection("Map").getKeys(false)) {
                    String[] spawn = LocationUtil.getStringLocation(plugin.getConfig().getString("Map." + npc + ".Location"));
                    createHologram(player, lore.toArray(new String[0]), new Location(Bukkit.getWorld(plugin.getConfig().getString("Map." + npc + ".World")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2])));
                }
            }
        } else {
            createHologram(player, lore.toArray(new String[0]), player.getLocation());
        }
        }
    public static void createHologram(Player player, String[] lines, Location location) {


        ArrayList<Integer> hologramslist = holograms.get(player);
        // 设置全息文本的内容
        double y = location.getY() + 0.5;
        for (String line : lines) {

            WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();
            EntityArmorStand armorStand = new EntityArmorStand(worldServer);
            y = y - 0.3;
            // 设置全息文本的位置
            armorStand.setLocation(location.getX(), y, location.getZ(), 0, 0);
            armorStand.setCustomNameVisible(true);
            armorStand.setInvisible(true);
            armorStand.setCustomName(line);
            hologramslist.add(armorStand.getId());

            holograms.replace(player, hologramslist);
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armorStand);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }


        // 发送SpawnEntityLiving包给玩家

    }
}
