package cn.seabedcraft.lemonthedeliveryman;

import com.alonsoaliaga.alonsolevels.api.AlonsoLevelsAPI;
import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import com.yapzhenyie.GadgetsMenu.utils.mysteryboxes.MysteryBoxType;
import me.yic.xconomy.api.XConomyAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class OpenMenu implements Listener {
    private final Plugin plugin = LemonTheDeliveryMan.getPlugin(LemonTheDeliveryMan.class);

    @EventHandler
    public void open(NPCRightClickEvent event) {
        if (event.getNPC().getName().equalsIgnoreCase("§e")) {
            openMenu(event.getClicker());
        }
    }


    private ItemStack getMVPplusItem(Player player) {
        try {
            Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            ArrayList<String> lore = new ArrayList<>();
            String disname = "我不到哇";
            Material material;
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            rs.next();
            if (rs.getString("MVPplus").equalsIgnoreCase("false")) {
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&aMystery Box礼包";
                    lore.add("&7"+currentMonth+"月的每月神秘宝箱奖励&b5&7个");
                    lore.add("");
                    lore.add("&7需要 &bMVP&c+");
                    lore.add("");
                    if (player.hasPermission("TheDeliveryMan.MVP+")) {
                        lore.add("&e点击领取!");
                    } else {
                        lore.add("&c你需要 &bMVP&c+ &c才能领取此礼包!");
                    }
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&aMystery Box Delivery";
                    lore.add("&7Your free mothly &b"+currentMonth+" (Total of 5)");
                    lore.add("&7Mystery Boxes for May have arrived!");
                    lore.add("");
                    lore.add("&7Requires &bMVP&c+");
                    lore.add("");
                    if (player.hasPermission("TheDeliveryMan.MVP+")) {
                        lore.add("&eClick here to claim!");
                    } else {
                        lore.add("&cYou need &aMVP&c+ &cto receive this delivery!");
                    }
                }
                material = Material.ENDER_CHEST;
                lore.add("");
            } else {
                Calendar now = Calendar.getInstance();
                Calendar nextMonth = Calendar.getInstance();
                nextMonth.add(Calendar.MONTH, 1);
                nextMonth.set(Calendar.DAY_OF_MONTH, 1);
                nextMonth.set(Calendar.HOUR_OF_DAY, 0);
                nextMonth.set(Calendar.MINUTE, 0);
                nextMonth.set(Calendar.SECOND, 0);
                nextMonth.set(Calendar.MILLISECOND, 0);
                long remainingTime = nextMonth.getTimeInMillis() - now.getTimeInMillis();
                long remainingSeconds = remainingTime / 1000;
                long days = remainingSeconds / (24 * 3600); // Calculate remaining days
                long hours = (remainingSeconds % (24 * 3600)) / 3600; // Calculate remaining hours
                long minutes = (remainingSeconds % 3600) / 60; // Calculate remaining minutes
                long seconds = remainingSeconds % 60; // Calculate remaining seconds

                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&cMystery Box礼包";
                    lore.add("&7你已经领取了这个礼包，请过会再来!");
                    lore.add("");
                    lore.add("&7下一次礼包送达: " + days + "天 " + hours + "小时 " + minutes + "分钟 " + seconds + "秒!");
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&cMystery Box Delivery";
                    lore.add("&7You already picked up this");
                    lore.add("&7delivery, come back later!");
                    lore.add("");
                    lore.add("&7Next delivery in: " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds!");
                }
                material = Material.MINECART;
            }
            return ItemUtil.CreateItem(material, disname, lore);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private ItemStack getVIPplusItem(Player player) {
        try {
            Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            ArrayList<String> lore = new ArrayList<>();
            String disname = "我不到哇";
            Material material;
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            rs.next();
            if (rs.getString("VIPplus").equalsIgnoreCase("false")) {
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&aMystery Box礼包";
                    lore.add("&7"+currentMonth+"月的每月神秘宝箱奖励&b5&7个");
                    lore.add("");
                    lore.add("&7需要 &aVIP&6+");
                    lore.add("");
                    if (player.hasPermission("TheDeliveryMan.vip+")) {
                        lore.add("&e点击领取!");
                    } else {
                        lore.add("&c你需要 &aVIP&6+ &c才能领取此礼包!");
                    }
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&aMystery Box Delivery";
                    lore.add("&7Your free mothly &b"+currentMonth+" (Total of 5)");
                    lore.add("&7Mystery Boxes for May have arrived!");
                    lore.add("");
                    lore.add("&7Requires &aVIP&6+");
                    lore.add("");
                    if (player.hasPermission("TheDeliveryMan.vip+")) {
                        lore.add("&eClick here to claim!");
                    } else {
                        lore.add("&cYou need &aVIP&6+ &cto receive this delivery!");
                    }
                }
                material = Material.ENDER_CHEST;
                lore.add("");
            } else {
                Calendar now = Calendar.getInstance();
                Calendar nextMonth = Calendar.getInstance();
                nextMonth.add(Calendar.MONTH, 1);
                nextMonth.set(Calendar.DAY_OF_MONTH, 1);
                nextMonth.set(Calendar.HOUR_OF_DAY, 0);
                nextMonth.set(Calendar.MINUTE, 0);
                nextMonth.set(Calendar.SECOND, 0);
                nextMonth.set(Calendar.MILLISECOND, 0);
                long remainingTime = nextMonth.getTimeInMillis() - now.getTimeInMillis();
                long remainingSeconds = remainingTime / 1000;
                long days = remainingSeconds / (24 * 3600); // Calculate remaining days
                long hours = (remainingSeconds % (24 * 3600)) / 3600; // Calculate remaining hours
                long minutes = (remainingSeconds % 3600) / 60; // Calculate remaining minutes
                long seconds = remainingSeconds % 60; // Calculate remaining seconds

                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&cMystery Box礼包";
                    lore.add("&7你已经领取了这个礼包，请过会再来!");
                    lore.add("");
                    lore.add("&7下一次礼包送达: " + days + "天 " + hours + "小时 " + minutes + "分钟 " + seconds + "秒!");
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&cMystery Box Delivery";
                    lore.add("&7You already picked up this");
                    lore.add("&7delivery, come back later!");
                    lore.add("");
                    lore.add("&7Next delivery in: " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds!");
                }
                material = Material.MINECART;
            }
            return ItemUtil.CreateItem(material, disname, lore);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private ItemStack getVIPItem(Player player) {
        try {
            Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            ArrayList<String> lore = new ArrayList<>();
            String disname = "我不到哇";
            Material material;
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            rs.next();
            if (rs.getString("VIP").equalsIgnoreCase("false")) {
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&aMystery Box礼包";
                    lore.add("&7"+currentMonth+"月的每月神秘宝箱奖励&b5&7个");
                    lore.add("");
                    lore.add("&7需要 &aVIP");
                    lore.add("");
                    if (player.hasPermission("TheDeliveryMan.vip")) {
                        lore.add("&e点击领取!");
                    } else {
                        lore.add("&c你需要 &aVIP &c才能领取此礼包!");
                    }
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&aMystery Box Delivery";
                    lore.add("&7Your free mothly &b"+currentMonth+" (Total of 5)");
                    lore.add("&7Mystery Boxes for May have arrived!");
                    lore.add("");
                    lore.add("&7Requires &aVIP");
                    lore.add("");
                    if (player.hasPermission("TheDeliveryMan.vip")) {
                        lore.add("&eClick here to claim!");
                    } else {
                        lore.add("&cYou need &aVIP &cto receive this delivery!");
                    }
                }
                material = Material.ENDER_CHEST;
                lore.add("");
            } else {
                Calendar now = Calendar.getInstance();
                Calendar nextMonth = Calendar.getInstance();
                nextMonth.add(Calendar.MONTH, 1);
                nextMonth.set(Calendar.DAY_OF_MONTH, 1);
                nextMonth.set(Calendar.HOUR_OF_DAY, 0);
                nextMonth.set(Calendar.MINUTE, 0);
                nextMonth.set(Calendar.SECOND, 0);
                nextMonth.set(Calendar.MILLISECOND, 0);
                long remainingTime = nextMonth.getTimeInMillis() - now.getTimeInMillis();
                long remainingSeconds = remainingTime / 1000;
                long days = remainingSeconds / (24 * 3600); // Calculate remaining days
                long hours = (remainingSeconds % (24 * 3600)) / 3600; // Calculate remaining hours
                long minutes = (remainingSeconds % 3600) / 60; // Calculate remaining minutes
                long seconds = remainingSeconds % 60; // Calculate remaining seconds

                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&cMystery Box礼包";
                    lore.add("&7你已经领取了这个礼包，请过会再来!");
                    lore.add("");
                    lore.add("&7下一次礼包送达: " + days + "天 " + hours + "小时 " + minutes + "分钟 " + seconds + "秒!");
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&cMystery Box Delivery";
                    lore.add("&7You already picked up this");
                    lore.add("&7delivery, come back later!");
                    lore.add("");
                    lore.add("&7Next delivery in: " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds!");
                }
                material = Material.MINECART;
            }
            return ItemUtil.CreateItem(material, disname, lore);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    private ItemStack getMVPItem(Player player) {
        try {
            Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            ArrayList<String> lore = new ArrayList<>();
            String disname = "我不到哇";
            Material material;
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            rs.next();
            if (rs.getString("MVP").equalsIgnoreCase("false")) {
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&aMystery Box礼包";
                    lore.add("&7"+currentMonth+"月的每月神秘宝箱奖励&b5&7个");
                    lore.add("");
                    lore.add("&7需要 &bMVP");
                    lore.add("");
                    if (player.hasPermission("TheDeliveryMan.MVP")) {
                        lore.add("&e点击领取!");
                    } else {
                        lore.add("&c你需要 &bMVP &c才能领取此礼包!");
                    }
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&aMystery Box Delivery";
                    lore.add("&7Your free mothly &b"+currentMonth+" (Total of 5)");
                    lore.add("&7Mystery Boxes for May have arrived!");
                    lore.add("");
                    lore.add("&7Requires &bMVP");
                    lore.add("");
                    if (player.hasPermission("TheDeliveryMan.MVP")) {
                        lore.add("&eClick here to claim!");
                    } else {
                        lore.add("&cYou need &bMVP &cto receive this delivery!");
                    }
                }
                material = Material.ENDER_CHEST;
                lore.add("");
            } else {
                Calendar now = Calendar.getInstance();
                Calendar nextMonth = Calendar.getInstance();
                nextMonth.add(Calendar.MONTH, 1);
                nextMonth.set(Calendar.DAY_OF_MONTH, 1);
                nextMonth.set(Calendar.HOUR_OF_DAY, 0);
                nextMonth.set(Calendar.MINUTE, 0);
                nextMonth.set(Calendar.SECOND, 0);
                nextMonth.set(Calendar.MILLISECOND, 0);
                long remainingTime = nextMonth.getTimeInMillis() - now.getTimeInMillis();
                long remainingSeconds = remainingTime / 1000;
                long days = remainingSeconds / (24 * 3600); // Calculate remaining days
                long hours = (remainingSeconds % (24 * 3600)) / 3600; // Calculate remaining hours
                long minutes = (remainingSeconds % 3600) / 60; // Calculate remaining minutes
                long seconds = remainingSeconds % 60; // Calculate remaining seconds

                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&cMystery Box礼包";
                    lore.add("&7你已经领取了这个礼包，请过会再来!");
                    lore.add("");
                    lore.add("&7下一次礼包送达: " + days + "天 " + hours + "小时 " + minutes + "分钟 " + seconds + "秒!");
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&cMystery Box Delivery";
                    lore.add("&7You already picked up this");
                    lore.add("&7delivery, come back later!");
                    lore.add("");
                    lore.add("&7Next delivery in: " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds!");
                }
                material = Material.MINECART;
            }
            return ItemUtil.CreateItem(material, disname, lore);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    private ItemStack getNormalItem(Player player) {
        try {
            Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            ArrayList<String> lore = new ArrayList<>();
            String disname = "我不到哇";
            Material material;
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            rs.next();
            if (rs.getString("Normal").equalsIgnoreCase("false")) {
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&aMystery Box礼包";
                    lore.add("&7"+currentMonth+"月的每月神秘宝箱奖励&b5&7个");
                    lore.add("");
                    lore.add("&e点击领取!");
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&aMystery Box Delivery";
                    lore.add("&7Your free mothly &b"+currentMonth+" (Total of 5)");
                    lore.add("&7Mystery Boxes for May have arrived!");
                    lore.add("");
                    lore.add("&eClick here to claim!");
                }
                material = Material.ENDER_CHEST;
                lore.add("");
            } else {
                Calendar now = Calendar.getInstance();
                Calendar nextMonth = Calendar.getInstance();
                nextMonth.add(Calendar.MONTH, 1);
                nextMonth.set(Calendar.DAY_OF_MONTH, 1);
                nextMonth.set(Calendar.HOUR_OF_DAY, 0);
                nextMonth.set(Calendar.MINUTE, 0);
                nextMonth.set(Calendar.SECOND, 0);
                nextMonth.set(Calendar.MILLISECOND, 0);
                long remainingTime = nextMonth.getTimeInMillis() - now.getTimeInMillis();
                long remainingSeconds = remainingTime / 1000;
                long days = remainingSeconds / (24 * 3600); // Calculate remaining days
                long hours = (remainingSeconds % (24 * 3600)) / 3600; // Calculate remaining hours
                long minutes = (remainingSeconds % 3600) / 60; // Calculate remaining minutes
                long seconds = remainingSeconds % 60; // Calculate remaining seconds

                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&cMystery Box礼包";
                    lore.add("&7你已经领取了这个礼包，请过会再来!");
                    lore.add("");
                    lore.add("&7下一次礼包送达: " + days + "天 " + hours + "小时 " + minutes + "分钟 " + seconds + "秒!");
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&cMystery Box Delivery";
                    lore.add("&7You already picked up this");
                    lore.add("&7delivery, come back later!");
                    lore.add("");
                    lore.add("&7Next delivery in: " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds!");
                }
                material = Material.MINECART;
            }
            return ItemUtil.CreateItem(material, disname, lore);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    private ItemStack getDayItem(Player player) {
        try {
            Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            ArrayList<String> lore = new ArrayList<>();
            String disname = "我不到哇";
            Material material;
            rs.next();
            if (rs.getString("Normal").equalsIgnoreCase("false")) {
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&a每日奖励";
                    lore.add("&7免费的每日2,200SeabedCraft大厅经验");
                    lore.add("&7和3,000街机硬币!");
                    lore.add("");
                    lore.add("&e点击领取!");
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    disname = "&aDaily Reward";
                    lore.add("&7Free 2,200 SeabedCraft");
                    lore.add("&7Experience and 3,000 Arcade");
                    lore.add("&7Coins");
                    lore.add("");
                    lore.add("&eClick here to claim!");
                }
                material = Material.STORAGE_MINECART;
                lore.add("");
            } else {
                Calendar now = Calendar.getInstance();
                Calendar tomorrowStart = Calendar.getInstance();
                tomorrowStart.add(Calendar.DAY_OF_YEAR, 1);
                tomorrowStart.set(Calendar.HOUR_OF_DAY, 0);
                tomorrowStart.set(Calendar.MINUTE, 0);
                tomorrowStart.set(Calendar.SECOND, 0);
                tomorrowStart.set(Calendar.MILLISECOND, 0);
                long remainingTime = tomorrowStart.getTimeInMillis() - now.getTimeInMillis();
                long remainingSeconds = remainingTime / 1000;
                long hours = remainingSeconds / 3600;
                long minutes = (remainingSeconds % 3600) / 60;
                long seconds = remainingSeconds % 60;
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    disname = "&c每日奖励";
                    lore.add("&7你最近已经领取了这个奖励!");
                    lore.add("&7请在" + hours + "小时 " + minutes + "分钟 " + seconds + "秒后领取!");
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {

                    disname = "&cDaily Reward";
                    lore.add("&7You have claimed this reward recently!");
                    lore.add("&7Check back in " + hours + "h " + minutes + "m " + seconds + "s!");
                }
                material = Material.MINECART;
            }
            return ItemUtil.CreateItem(material, disname, lore);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void openMenu(Player player) {
        String lang = LemonTheDeliveryMan.getPlayerLang(player);
        String title = "我不到你啥语言哇";
        if (lang.equalsIgnoreCase("zhcn")) {
            title = "礼包使者";
        } else if (lang.equalsIgnoreCase("en")) {
            title = "The Delivery Man";
        }
        Inventory inventory = Bukkit.createInventory(null, 54, title);
        ArrayList<String> lore = new ArrayList();
        String name = "我不到啊";
        if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
            name = "LemonCloud 预告片";
            lore.add("&7来看看LemonNetwork旗下的网盘产品吧!");
        } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
            name = "Lemon Cloud Trailer";
            lore.add("&7Let's take a look at the network disk products of Lemon Network!");
        }
        inventory.setItem(4, ItemUtil.CreateItem(Material.RECORD_12, name, lore));
        inventory.setItem(20, getNormalItem(player));
        inventory.setItem(21, getVIPItem(player));
        inventory.setItem(22, getVIPplusItem(player));
        inventory.setItem(23, getMVPItem(player));
        inventory.setItem(24, getMVPplusItem(player));
        inventory.setItem(33, getDayItem(player));
        player.openInventory(inventory);

    }







    @EventHandler
    public void click(InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getTitle().equalsIgnoreCase("礼包使者") || event.getInventory().getTitle().equalsIgnoreCase("The Delivery Man")) {
                event.setCancelled(true);
                if (event.getSlot() == 4) {
                    lemonCloudEvent(player);
                }
                if (event.getSlot() == 33) {
                    dayReward(player);
                }
                if (event.getSlot() == 20) {
                    NormalReward(player);
                }
                if (event.getSlot() == 21) {
                    VIPReward(player);
                }
                if (event.getSlot() == 22) {
                    VIPplusReward(player);
                }
                if (event.getSlot() == 23) {
                    MVPReward(player);
                }
                if (event.getSlot() == 24) {
                    MVPplusReward(player);
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    private void NormalReward(Player player) {
        try {
            ResultSet rs = LemonTheDeliveryMan.dataSource.getConnection().createStatement().executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            rs.next();
            if (rs.getString("Normal").equalsIgnoreCase("false")) {
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你领取了每个月&b5&a个&e1⭐&a的神秘箱奖励!"));
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have received &b5 &e1⭐ &amystery box rewards every month!"));
                }
                PlayerManager playerManager = GadgetsMenuAPI.getPlayerManager(player);
                playerManager.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_1, (System.currentTimeMillis() + (24 * 3600 * 1000)), true, null, 5);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
                statement.executeUpdate("UPDATE player_data SET Normal = 'true' WHERE uuid = '" + player.getUniqueId().toString()+"'");
                player.getOpenInventory().setItem(20, getNormalItem(player));
            } else {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你最近已经领取这个奖励了，过会再来吧！"));
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have recently claimed this reward, come back later!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void VIPplusReward(Player player) {
        try {
            ResultSet rs = LemonTheDeliveryMan.dataSource.getConnection().createStatement().executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            rs.next();
            if (player.hasPermission("TheDeliveryMan.VIP+")) {
                if (rs.getString("VIPplus").equalsIgnoreCase("false")) {
                    if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你领取了每个月&b5&a个&e3⭐&a的神秘箱奖励!"));
                    } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have received &b5 &e3⭐ &amystery box rewards every month!"));
                    }
                    PlayerManager playerManager = GadgetsMenuAPI.getPlayerManager(player);
                    playerManager.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_3, (System.currentTimeMillis() + (24 * 3600 * 1000)), true, null, 5);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
                    statement.executeUpdate("UPDATE player_data SET VIPplus = 'true' WHERE uuid = '" + player.getUniqueId().toString() + "'");
                    player.getOpenInventory().setItem(22, getVIPplusItem(player));
                } else {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                    if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你最近已经领取这个奖励了，过会再来吧！"));
                    } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have recently claimed this reward, come back later!"));
                    }
                }
            } else {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你需要 &aVIP&6+ &c才能领取此礼包!"));
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou need &aVIP&6+ &c to receive this delivery!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void MVPplusReward(Player player) {
        try {
            ResultSet rs = LemonTheDeliveryMan.dataSource.getConnection().createStatement().executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            rs.next();
            if (player.hasPermission("TheDeliveryMan.MVP+")) {
                if (rs.getString("MVPplus").equalsIgnoreCase("false")) {
                    if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你领取了每个月&b5&a个&e5⭐&a的神秘箱奖励!"));
                    } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have received &b5 &e5⭐ &amystery box rewards every month!"));
                    }
                    PlayerManager playerManager = GadgetsMenuAPI.getPlayerManager(player);
                    playerManager.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_5, (System.currentTimeMillis() + (24 * 3600 * 1000)), true, null, 5);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
                    statement.executeUpdate("UPDATE player_data SET MVPplus = 'true' WHERE uuid = '" + player.getUniqueId().toString() + "'");
                    player.getOpenInventory().setItem(24, getMVPplusItem(player));
                } else {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                    if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你最近已经领取这个奖励了，过会再来吧！"));
                    } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have recently claimed this reward, come back later!"));
                    }
                }
            } else {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你需要 &bMVP&c+ &c才能领取此礼包!"));
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou need &bMVP&c+ &c to receive this delivery!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    private void MVPReward(Player player) {
        try {
            ResultSet rs = LemonTheDeliveryMan.dataSource.getConnection().createStatement().executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            rs.next();
            if (player.hasPermission("TheDeliveryMan.MVP")) {
                if (rs.getString("MVP").equalsIgnoreCase("false")) {
                    if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你领取了每个月&b5&a个&e4⭐&a的神秘箱奖励!"));
                    } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have received &b5 &e4⭐ &amystery box rewards every month!"));
                    }
                    PlayerManager playerManager = GadgetsMenuAPI.getPlayerManager(player);
                    playerManager.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_4, (System.currentTimeMillis() + (24 * 3600 * 1000)), true, null, 5);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
                    statement.executeUpdate("UPDATE player_data SET MVP = 'true' WHERE uuid = '" + player.getUniqueId().toString() + "'");
                    player.getOpenInventory().setItem(23, getMVPItem(player));
                } else {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                    if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你最近已经领取这个奖励了，过会再来吧！"));
                    } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have recently claimed this reward, come back later!"));
                    }
                }
            } else {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你需要 &aVIP &c才能领取此礼包!"));
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou need &aVIP &c to receive this delivery!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void VIPReward(Player player) {
        try {
            ResultSet rs = LemonTheDeliveryMan.dataSource.getConnection().createStatement().executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            rs.next();
            if (player.hasPermission("TheDeliveryMan.VIP")) {
                if (rs.getString("VIP").equalsIgnoreCase("false")) {
                    if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你领取了每个月&b5&a个&e2⭐&a的神秘箱奖励!"));
                    } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have received &b5 &e2⭐ &amystery box rewards every month!"));
                    }
                    PlayerManager playerManager = GadgetsMenuAPI.getPlayerManager(player);
                    playerManager.giveMysteryBoxes(MysteryBoxType.NORMAL_MYSTERY_BOX_2, (System.currentTimeMillis() + (24 * 3600 * 1000)), true, null, 5);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
                    statement.executeUpdate("UPDATE player_data SET VIP = 'true' WHERE uuid = '" + player.getUniqueId().toString() + "'");
                    player.getOpenInventory().setItem(21, getVIPItem(player));
                } else {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                    if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你最近已经领取这个奖励了，过会再来吧！"));
                    } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have recently claimed this reward, come back later!"));
                    }
                }
            } else {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你需要 &aVIP &c才能领取此礼包!"));
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou need &aVIP &c to receive this delivery!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void dayReward(Player player) {
        try {
            ResultSet rs = LemonTheDeliveryMan.dataSource.getConnection().createStatement().executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            rs.next();
            if (rs.getString("Day").equalsIgnoreCase("false")) {
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a你领取了免费的&32,200 SeabedCraft大厅经验&a和&63,000街机硬币"));
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou claimed free &32,200 SeabedCraft lobby experience&a and &63,000 arcade coins"));
                }
                AlonsoLevelsAPI.addExperience(player.getUniqueId(), 2200);
                XConomyAPI xConomyAPI = new XConomyAPI();
                xConomyAPI.changePlayerBalance(player.getUniqueId(), player.getName(), new BigDecimal(3000), true);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
                statement.executeUpdate("UPDATE player_data SET Day = 'true' WHERE uuid = '" + player.getUniqueId().toString()+"'");
                player.getOpenInventory().setItem(33, getDayItem(player));
            } else {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c你最近已经领取这个奖励了，过会再来吧！"));
                } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have recently claimed this reward, come back later!"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void lemonCloudEvent(Player player) {
        TextComponent tc = null;
        BaseComponent component = null;
        if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("zhcn")) {
            component = new TextComponent(ChatColor.BLUE + "Lemon Cloud\n\n" + ("LemonCloud是由LemonNetwork研发的网盘\n目前暂未开放，详细可以查看宣传片\n\n"));
            tc = new TextComponent("点击访问");
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6§l点击查看 LemonCloud 预告片").create()));
        } else if (LemonTheDeliveryMan.getPlayerLang(player).equalsIgnoreCase("en")) {
            component = new TextComponent(ChatColor.BLUE + "Lemon Cloud\n\n" + ("LemonCloud is a network disk developed by LemonNetwork\nCurrently not open, please check the promotional video for details\n\n"));
            tc = new TextComponent("Click to visit");
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6§lClick to view the LemonCloud trailer").create()));
        }
        tc.setBold(true);
        tc.setColor(net.md_5.bungee.api.ChatColor.GOLD);
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.bilibili.com/video/BV1xW4y1e72n"));
        component.addExtra(tc);
        BookUtil.openBook(player, BookUtil.buildBook(component));
    }
}