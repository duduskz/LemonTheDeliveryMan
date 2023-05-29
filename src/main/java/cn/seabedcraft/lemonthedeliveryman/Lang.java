package cn.seabedcraft.lemonthedeliveryman;

import cn.SeabedCraft.duduskz.Events.PlayerChangeLang;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class Lang implements Listener {
    @EventHandler
    public void change(PlayerChangeLang event) {
        ArrayList<Integer> hologramList = Hologram.holograms.get(event.getPlayer());
        LemonTheDeliveryMan.PlayerLang.replace(event.getPlayer(), event.getLang());
        for (int hologramEntityId : hologramList) {
            Hologram.removeHologram(event.getPlayer(), hologramEntityId);
        }
        hologramList.clear();

        Hologram.holograms.replace(event.getPlayer(), hologramList);

        Hologram.create(event.getPlayer(), true);
    }
}
