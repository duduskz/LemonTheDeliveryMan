package cn.seabedcraft.lemonthedeliveryman;

import cn.seabedcraft.lemonthedeliveryman.Command.MainCommand;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public final class LemonTheDeliveryMan extends JavaPlugin {

    public static HikariDataSource dataSource;

    public static HikariDataSource APIdataSource;

    public static HashMap<Player, String> PlayerLang = new HashMap<>();

    public static Boolean SeabedCraftLobbySystem = false;

    public static String getPlayerLang(Player player) {
        if (SeabedCraftLobbySystem) {
            if (PlayerLang.get(player) == null) {
                try {
                    Statement statement = APIdataSource.getConnection().createStatement();
                    String sql = "SELECT * FROM player_lang WHERE uuid = '" + player.getUniqueId().toString() + "'";
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.next()) {
                        PlayerLang.put(player, rs.getString("lang"));
                        return rs.getString("lang");
                    } else {
                        sql = "INSERT INTO player_lang (uuid, lang) VALUES ('" + player.getUniqueId().toString() + "', 'zhcn')";
                        statement.executeUpdate(sql);
                        PlayerLang.put(player, "zhcn");
                        return "zhcn";

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    return "zhcn";

                }

            } else {
                return PlayerLang.get(player);
            }
        } else {
            return "zhcn";
        }

    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        Bukkit.getConsoleSender().sendMessage("  §bLemon§dTheDeliveryMan");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§f作者：§bLemonNetwork");
        Bukkit.getConsoleSender().sendMessage("§f状态：§a已加载");
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        if (getServer().getPluginManager().isPluginEnabled("SeabedCraftSystem")) {
            Bukkit.getConsoleSender().sendMessage("§b检测到运行着SeabedCraftSystem，已开始SeabedCraftSystem梦幻联动模式");
            SeabedCraftLobbySystem = true;
        } else {
            Bukkit.getConsoleSender().sendMessage("§c没有检测到运行着SeabedCraftSystem，已禁用SeabedCraftSystem梦幻联动模式");
            SeabedCraftLobbySystem = false;
        }
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://"+getConfig().getString("MySQL.url")+":"+getConfig().getString("MySQL.port")+"/"+getConfig().getString("MySQL.db"));
        config.setUsername(getConfig().getString("MySQL.username"));
        config.setPassword(getConfig().getString("MySQL.password"));
        config.setMaximumPoolSize(1000);
        dataSource = new HikariDataSource(config);
        if (SeabedCraftLobbySystem) {
            HikariConfig APIconfig = new HikariConfig();
            APIconfig.setJdbcUrl("jdbc:mysql://" + getConfig().getString("APIMySQL.url") + ":" + getConfig().getString("APIMySQL.port") + "/" + getConfig().getString("APIMySQL.db"));
            APIconfig.setUsername(getConfig().getString("APIMySQL.username"));
            APIconfig.setPassword(getConfig().getString("APIMySQL.password"));
            APIconfig.setMaximumPoolSize(1000);
            APIdataSource = new HikariDataSource(APIconfig);
        }

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS player_data (" +
                    "uuid TEXT(200)," +
                    "Day TEXT(200), Normal TEXT(200), VIP TEXT(200), VIPplus TEXT(200), MVP TEXT(200), MVPplus TEXT(200));";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //NPC.npcs();
        getCommand("thedeliveryman").setExecutor(new MainCommand());
        getServer().getPluginManager().registerEvents(new Hologram(), this);
        getServer().getPluginManager().registerEvents(new OpenMenu(), this);
        getServer().getPluginManager().registerEvents(new JoinData(), this);
        Timer timer = new Timer();
        timer.schedule(new UpdateTask(), 0, 1000 * 60 * 60 * 24);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String sql = "UPDATE table_name SET Normal = false, VIP = false, VIPplus = false, MVP = false, MVPplus = false";
                    Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
                    statement.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, date, 30L * 24L * 60L * 60L * 1000L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
        Bukkit.getConsoleSender().sendMessage("  §bLemon§dTheDeliveryMan");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§f作者：§bLemonNetwork");
        Bukkit.getConsoleSender().sendMessage("§f状态：§c已卸载");
        Bukkit.getConsoleSender().sendMessage("——————————————————————————");
    }
}
