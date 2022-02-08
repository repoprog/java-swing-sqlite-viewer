package viewer;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static String fileName; //  = "D:\\database\\test.db";
    private static List<String> tables;

    public static void setFileName(String fileName) {
        DataBaseManager.fileName = fileName;
    }

    public static List<String> getComboElements() {
        return tables;
    }

//    public static void makeConnection() {
//        String url = "jdbc:sqlite:" + fileName;
//        String sql = "CREATE TABLE IF NOT EXISTS contacts " +
//                "(contact_id INTEGER PRIMARY KEY, " +
//                "first_name TEXT NOT NULL, " +
//                "last_name TEXT NOT NULL, " +
//                "email TEXT NOT NULL UNIQUE, " +
//                "phone TEXT NOT NULL UNIQUE);";
//        try (Connection con = DriverManager.getConnection(url);
//             Statement statement = con.createStatement()) {
//
//            statement.execute(sql);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        String sql2 = "CREATE TABLE IF NOT EXISTS groups " +
//                "(group_id INTEGER PRIMARY KEY," +
//                " name TEXT NOT NULL);";
//        try (Connection con = DriverManager.getConnection(url);
//             Statement statement = con.createStatement()) {
//
//            statement.execute(sql2);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        String sql3 = "CREATE TABLE IF NOT EXISTS projects " +
//                "(id integer PRIMARY KEY," +
//                " name text NOT NULL, " +
//                "begin_date text," +
//                " end_date text);";
//        try (Connection con = DriverManager.getConnection(url);
//             Statement statement = con.createStatement()) {
//
//            statement.execute(sql3);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        selectTablesNames();
//    }

    public static void selectTablesNames() {
        tables = new ArrayList<>();
        String selectTablesSQL = "SELECT name FROM sqlite_master WHERE type = ? AND name NOT LIKE ?";
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + fileName);
             PreparedStatement selectTables = con.prepareStatement(selectTablesSQL)) {
            selectTables.setString(1, "table");
            selectTables.setString(2, "sqlite_" + "%");
            ResultSet rs = selectTables.executeQuery();
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
            System.out.println(String.join(", ", tables));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        SQLiteViewer.fillCombo();
    }

//    public static void selectTable() {
//
//    }
}