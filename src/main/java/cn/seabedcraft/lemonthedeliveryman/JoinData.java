package cn.seabedcraft.lemonthedeliveryman;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JoinData implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent event) {
        try {
            Player player = event.getPlayer();
            Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM player_data WHERE uuid = '" + player.getUniqueId().toString() + "'");
            if (!rs.next()) {
                statement.executeUpdate("INSERT INTO player_data (uuid, Day, Normal, VIP, VIPplus, MVP, MVPplus) VALUES ('"+event.getPlayer().getUniqueId().toString()+"','false', 'false', 'false', 'false', 'false', 'false')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
