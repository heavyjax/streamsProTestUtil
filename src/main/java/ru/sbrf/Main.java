package ru.sbrf;


import ru.sbrf.util.*;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = Util.getProperties("/Users/heavyjax/GoogleDrive/Dev/StreamsProTestUtil/config.properties");

        try (Connection conn = DriverManager.getConnection(
                props.getProperty("db_connection_string"), props.getProperty("db_user"), props.getProperty("db_passwd"))) {

            if (conn != null) {
                System.out.println("Connected to the database!");
                Statement stmt=conn.createStatement();
                stmt.executeQuery(getInsertQuery(props));

                ResultSet rs=stmt.executeQuery("select * from W4S.EVNT_MSG where id = " + props.getProperty("ID"));

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

    private static String getInsertQuery(Properties props) {
        StringBuilder str = new StringBuilder();
        str.append("insert into W4S.EVNT_MSG values (");
        str.append("null".equals(props.getProperty("ID")) ? "null," : "'" + props.getProperty("ID") + "',");
        str.append("null".equals(props.getProperty("USAGE_ACTION_OID")) ? "null," : "'" + props.getProperty("USAGE_ACTION_OID") + "',");
        str.append("null".equals(props.getProperty("ADDRESS_DATA")) ? "null," : "'" + props.getProperty("ADDRESS_DATA") + "',");
        str.append("null".equals(props.getProperty("DELIVERY_CHANNEL")) ? "null," : "'" + props.getProperty("DELIVERY_CHANNEL") + "',");
        str.append("null".equals(props.getProperty("CODE")) ? "null," : "'" + props.getProperty("CODE") + "',");
        str.append("null".equals(props.getProperty("DATE_FROM")) ? "null," : "to_date('" + props.getProperty("DATE_FROM") + "','DD.MM.RR'),");
        str.append("null".equals(props.getProperty("DATE_TO")) ? "null," : "to_date('" + props.getProperty("DATE_TO") + "','DD.MM.RR'),");
        str.append("null".equals(props.getProperty("MESSAGE_DETAILS")) ? "null," : "'" + props.getProperty("MESSAGE_DETAILS") + "',");
        str.append("null".equals(props.getProperty("MESSAGE_STRING_1")) ? "null," : "'" + props.getProperty("MESSAGE_STRING_1") + "',");
        str.append("null".equals(props.getProperty("MESSAGE_STRING_2")) ? "null," : "'" + props.getProperty("MESSAGE_STRING_2") + "',");
        str.append("null".equals(props.getProperty("MESSAGE_STRING_3")) ? "null," : "'" + props.getProperty("MESSAGE_STRING_3") + "',");
        str.append("null".equals(props.getProperty("MESSAGE_STRING_4")) ? "null," : "'" + props.getProperty("MESSAGE_STRING_4") + "',");
        str.append("null".equals(props.getProperty("MESSAGE_STRING_5")) ? "null," : "'" + props.getProperty("MESSAGE_STRING_5") + "',");
        str.append("null".equals(props.getProperty("MESSAGE_STRING_6")) ? "null," : "'" + props.getProperty("MESSAGE_STRING_6") + "',");
        str.append("null".equals(props.getProperty("MSG_TEMPLATE")) ? "null," : "'" + props.getProperty("MSG_TEMPLATE") + "',");
        str.append("null".equals(props.getProperty("STATUS")) ? "null," : "'" + props.getProperty("STATUS") + "',");
        str.append("null".equals(props.getProperty("SENDING_CHANNEL")) ? "null," : "'" + props.getProperty("SENDING_CHANNEL") + "',");
        str.append("null".equals(props.getProperty("SENDING_DATE")) ? "null," : "to_date('" + props.getProperty("SENDING_DATE") + "','DD.MM.RR'),");
        str.append("null".equals(props.getProperty("SENDING_DETAILS")) ? "null," : "'" + props.getProperty("SENDING_DETAILS") + "',");
        str.append("null".equals(props.getProperty("REF_NUMBER")) ? "null," : "'" + props.getProperty("REF_NUMBER") + "',");
        str.append("null".equals(props.getProperty("SUBJECT")) ? "null," : "'" + props.getProperty("SUBJECT") + "',");
        str.append("null".equals(props.getProperty("PRIORITY")) ? "null," : "'" + props.getProperty("PRIORITY") + "',");
        str.append("null".equals(props.getProperty("GROUP_NUMBER")) ? "null," : "'" + props.getProperty("GROUP_NUMBER") + "',");
        str.append("null".equals(props.getProperty("PARTITION_KEY")) ? "null" : "'" + props.getProperty("PARTITION_KEY") + "'");
        str.append(")");
        return str.toString();
    }
}
