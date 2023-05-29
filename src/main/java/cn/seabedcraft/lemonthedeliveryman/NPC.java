package cn.seabedcraft.lemonthedeliveryman;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class NPC {
    public static void npcs() {
        Plugin plugin = LemonTheDeliveryMan.getPlugin(LemonTheDeliveryMan.class);
        if (plugin.getConfig().getConfigurationSection("Map.") != null) {
            for (String npc : plugin.getConfig().getConfigurationSection("Map.").getKeys(false)) {
                net.citizensnpcs.api.npc.NPC thedeliveryman = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§e");
                String[] spawn = LocationUtil.getStringLocation(plugin.getConfig().getString("Map." + npc + ".Location"));
                SkinTrait skinTrait = thedeliveryman.getOrAddTrait(SkinTrait.class);
                skinTrait.setSkinName("DeliveryMan", true);
                //skinTrait.setSkinPersistent("a2f98472-b3cf-4bc3-936e-cbf0a4ff1729", "gUfH1WyhHhvhf7VDoNaRLtNuBpH5mNtbdFaySRcd6t6ydW+TJPKvALx8IVM9qHf7bZizzYNGZM8LHduwR2iQETaxRWhUY0q0AIto7QOjajU2A9Rb2supOnNMCQIsIsFbEuYEz7pBIJmcuoCcmTEXMH9AsV8TsHs5NrE2Bn4V9PjgvW3aMsogYw0HxKq0q5RNiiRhG+AptwkPImqcMkwE1pMMEyfk9Mou6x9fQb2cqXOlHvDBaqArN2nwbjph1vyx0K2Qh/c74NWD2oDJYtOXrceWzHQa+WzoJpWMAPuwvuRwYGTBElwiFrpqeY9Bl/8B2oX2sttXWmbsP1TAsIg14FLLc2HZm4wCSWvEnaK7zAdBAeJjWSo60xywis1WYwTbTpn9MPvU584XA8P2ovp0aqZ/CZqmXTMv9wWn9KGCqFCgCS77PUG6pviF9csZ0PmmCei8SCWcchWITsmb1rYT8cxUV0bARUsOk5Yu2XZmHHZPOhNwPzpNENyzoGJ3JNC5gnFcph5GhYZv8+8nnaLWsJCeXFdVEkmr1FZ4m6geUeytXJF6gNiaTiE3lltLFqcjwJj0OjgfJhkpd1+WHRDzpC5v8gOnNnwlhNh++FCObcGw90FPU+FA14mZbIjm80MZ/j/oiIOcuxcacRnLPN1Eevaj0UsoAUcI3vPnOKAJM70=", "gUfH1WyhHhvhf7VDoNaRLtNuBpH5mNtbdFaySRcd6t6ydW+TJPKvALx8IVM9qHf7bZizzYNGZM8LHduwR2iQETaxRWhUY0q0AIto7QOjajU2A9Rb2supOnNMCQIsIsFbEuYEz7pBIJmcuoCcmTEXMH9AsV8TsHs5NrE2Bn4V9PjgvW3aMsogYw0HxKq0q5RNiiRhG+AptwkPImqcMkwE1pMMEyfk9Mou6x9fQb2cqXOlHvDBaqArN2nwbjph1vyx0K2Qh/c74NWD2oDJYtOXrceWzHQa+WzoJpWMAPuwvuRwYGTBElwiFrpqeY9Bl/8B2oX2sttXWmbsP1TAsIg14FLLc2HZm4wCSWvEnaK7zAdBAeJjWSo60xywis1WYwTbTpn9MPvU584XA8P2ovp0aqZ/CZqmXTMv9wWn9KGCqFCgCS77PUG6pviF9csZ0PmmCei8SCWcchWITsmb1rYT8cxUV0bARUsOk5Yu2XZmHHZPOhNwPzpNENyzoGJ3JNC5gnFcph5GhYZv8+8nnaLWsJCeXFdVEkmr1FZ4m6geUeytXJF6gNiaTiE3lltLFqcjwJj0OjgfJhkpd1+WHRDzpC5v8gOnNnwlhNh++FCObcGw90FPU+FA14mZbIjm80MZ/j/oiIOcuxcacRnLPN1Eevaj0UsoAUcI3vPnOKAJM70=");

                thedeliveryman.addTrait(skinTrait);
                thedeliveryman.spawn(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map." + npc + ".World")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

            }
        }
    }

    public static void npc(String npc) {
        Plugin plugin = LemonTheDeliveryMan.getPlugin(LemonTheDeliveryMan.class);

        net.citizensnpcs.api.npc.NPC thedeliveryman = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§e");
        String[] spawn = LocationUtil.getStringLocation(plugin.getConfig().getString("Map." + npc + ".Location"));
        SkinTrait skinTrait = thedeliveryman.getOrAddTrait(SkinTrait.class);
        //skinTrait.setSkinPersistent("a2f98472-b3cf-4bc3-936e-cbf0a4ff1729", "gUfH1WyhHhvhf7VDoNaRLtNuBpH5mNtbdFaySRcd6t6ydW+TJPKvALx8IVM9qHf7bZizzYNGZM8LHduwR2iQETaxRWhUY0q0AIto7QOjajU2A9Rb2supOnNMCQIsIsFbEuYEz7pBIJmcuoCcmTEXMH9AsV8TsHs5NrE2Bn4V9PjgvW3aMsogYw0HxKq0q5RNiiRhG+AptwkPImqcMkwE1pMMEyfk9Mou6x9fQb2cqXOlHvDBaqArN2nwbjph1vyx0K2Qh/c74NWD2oDJYtOXrceWzHQa+WzoJpWMAPuwvuRwYGTBElwiFrpqeY9Bl/8B2oX2sttXWmbsP1TAsIg14FLLc2HZm4wCSWvEnaK7zAdBAeJjWSo60xywis1WYwTbTpn9MPvU584XA8P2ovp0aqZ/CZqmXTMv9wWn9KGCqFCgCS77PUG6pviF9csZ0PmmCei8SCWcchWITsmb1rYT8cxUV0bARUsOk5Yu2XZmHHZPOhNwPzpNENyzoGJ3JNC5gnFcph5GhYZv8+8nnaLWsJCeXFdVEkmr1FZ4m6geUeytXJF6gNiaTiE3lltLFqcjwJj0OjgfJhkpd1+WHRDzpC5v8gOnNnwlhNh++FCObcGw90FPU+FA14mZbIjm80MZ/j/oiIOcuxcacRnLPN1Eevaj0UsoAUcI3vPnOKAJM70=", "gUfH1WyhHhvhf7VDoNaRLtNuBpH5mNtbdFaySRcd6t6ydW+TJPKvALx8IVM9qHf7bZizzYNGZM8LHduwR2iQETaxRWhUY0q0AIto7QOjajU2A9Rb2supOnNMCQIsIsFbEuYEz7pBIJmcuoCcmTEXMH9AsV8TsHs5NrE2Bn4V9PjgvW3aMsogYw0HxKq0q5RNiiRhG+AptwkPImqcMkwE1pMMEyfk9Mou6x9fQb2cqXOlHvDBaqArN2nwbjph1vyx0K2Qh/c74NWD2oDJYtOXrceWzHQa+WzoJpWMAPuwvuRwYGTBElwiFrpqeY9Bl/8B2oX2sttXWmbsP1TAsIg14FLLc2HZm4wCSWvEnaK7zAdBAeJjWSo60xywis1WYwTbTpn9MPvU584XA8P2ovp0aqZ/CZqmXTMv9wWn9KGCqFCgCS77PUG6pviF9csZ0PmmCei8SCWcchWITsmb1rYT8cxUV0bARUsOk5Yu2XZmHHZPOhNwPzpNENyzoGJ3JNC5gnFcph5GhYZv8+8nnaLWsJCeXFdVEkmr1FZ4m6geUeytXJF6gNiaTiE3lltLFqcjwJj0OjgfJhkpd1+WHRDzpC5v8gOnNnwlhNh++FCObcGw90FPU+FA14mZbIjm80MZ/j/oiIOcuxcacRnLPN1Eevaj0UsoAUcI3vPnOKAJM70=");
        skinTrait.setSkinName("DeliveryMan", true);
        thedeliveryman.addTrait(skinTrait);
        thedeliveryman.spawn(new Location(Bukkit.getWorld(plugin.getConfig().getString("Map." + npc + ".World")), Double.parseDouble(spawn[0]), Double.parseDouble(spawn[1]), Double.parseDouble(spawn[2]), Integer.parseInt(spawn[3]), Integer.parseInt(spawn[4])));

    }
}
