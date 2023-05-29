package cn.seabedcraft.lemonthedeliveryman;

import java.sql.*;
import java.util.TimerTask;

public class UpdateTask extends TimerTask {
    public void run() {
        try {
            Statement statement = LemonTheDeliveryMan.dataSource.getConnection().createStatement();
            String sql = "UPDATE player_data SET Day = false";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}