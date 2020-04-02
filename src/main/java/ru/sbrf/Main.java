package ru.sbrf;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:streams", "w4s", "w4s")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery("select * from W4S.EVNT_INFO");

                while(rs.next()) {
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                }
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
