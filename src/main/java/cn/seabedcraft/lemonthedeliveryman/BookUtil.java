package cn.seabedcraft.lemonthedeliveryman;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaBook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class BookUtil {

    private static Plugin plugin = LemonTheDeliveryMan.getPlugin(LemonTheDeliveryMan.class);

    public static ItemStack buildBook(BaseComponent... components) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        List<IChatBaseComponent> pages;
        try {
            pages = (List<IChatBaseComponent>) CraftMetaBook.class.getDeclaredField("pages").get(meta);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }

        meta.setDisplayName("PLUGIN_BOOK");
        meta.setTitle("PLUGIN_BOOK");
        meta.setAuthor("PLUGIN_BOOK");

        BaseComponent base = new TextComponent("");
        for (BaseComponent component : components) {
            base.addExtra(component);
            base.addExtra(new TextComponent("\n"));
        }

        IChatBaseComponent page = IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(base));
        pages.add(page);
        book.setItemMeta(meta);

        return book;
    }

    public static void openBook(Player player, ItemStack book) {
        int slot = player.getInventory().getHeldItemSlot();
        ItemStack old = player.getInventory().getItem(slot);
        player.getInventory().setItem(slot, book);
        ByteBuf buf = Unpooled.buffer(256);
        buf.setByte(0, 0);
        buf.writerIndex(1);
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        player.getInventory().setItem(slot, old);
    }
}
