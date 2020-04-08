package ru.sbrf.db.dao;

import ru.sbrf.util.Util;

import java.sql.*;
import java.util.Properties;

public class MonitoringDAO {
    private final Properties props;

    public MonitoringDAO(final String configPath) {
        this.props = Util.getProperties(configPath);
    }

    public void selectEvntMetaInfo() {
        try (Connection conn = DriverManager.getConnection(
                props.getProperty("mon_db_connection_string"), props.getProperty("mon_db_user"), props.getProperty("mon_db_passwd"));
             Statement stmt=conn.createStatement()) {

            if (conn != null) {
                System.out.println("Connected to MONITORING database!");
                ResultSet rs=stmt.executeQuery("select * from W4S.EVNT_MSG where id = " + props.getProperty("ID"));

                while(rs.next()) {
                    System.out.println(
                                    rs.getString(1)+"  "+
                                    rs.getString(2)+"  "+
                                    rs.getString(3)+"  "+
                                    rs.getString(4)+"  "+
                                    rs.getString(5)+"  "+
                                    rs.getString(6)+"  "+
                                    rs.getString(7)+"  "+
                                    rs.getString(8)+"  "+
                                    rs.getString(9)+"  "+
                                    rs.getString(10)+"  "+
                                    rs.getString(11)+"  "+
                                    rs.getString(12)+"  "+
                                    rs.getString(13)+"  "+
                                    rs.getString(14)+"  "+
                                    rs.getString(15)+"  "+
                                    rs.getString(16)+"  "+
                                    rs.getString(17)+"  "+
                                    rs.getString(18)+"  "+
                                    rs.getString(19)+"  "+
                                    rs.getString(20)+"  "+
                                    rs.getString(21)+"  "+
                                    rs.getString(22)+"  "+
                                    rs.getString(23)+"  "+
                                    rs.getString(24)+"  "
                            );
                    System.out.println("=================================================================================");
                    System.out.println("Record was successfully found in monitoring db");
                    System.out.println("TEST SUCCESS...");
                    System.out.println("=================================================================================");
                }
            } else {
                System.out.println("Failed to make MONITORING connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
