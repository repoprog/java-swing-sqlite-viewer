package viewer;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static String dataBase; //= " D:\\database\\test.db";
    private static List<String> comboElements;

    public static void setDataBase(String fileName) {
        DataBaseManager.dataBase = fileName;
    }

    public static List<String> getComboElements() {
        return comboElements;
    }

    public static void selectTablesNames() {
        comboElements = new ArrayList<>();
        String selectTablesSQL = "SELECT name FROM sqlite_master WHERE type = ? AND name NOT LIKE ?";
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + dataBase);
             PreparedStatement selectTables = con.prepareStatement(selectTablesSQL)) {
            selectTables.setString(1, "table");
            selectTables.setString(2, "sqlite_" + "%");
            ResultSet rs = selectTables.executeQuery();
            while (rs.next()) {
                comboElements.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(new Frame(), "Wrong SQL query.");
        }
        SQLiteViewer.fillCombo();
    }

    public static DefaultTableModel fillJTable(String query) {
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + dataBase);
             Statement stat = con.createStatement()) {
            ResultSet rs = stat.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = rs.getMetaData();

            // take table columns names and add them to jTable model
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                model.addColumn(metaData.getColumnName(i + 1));
            }
            // take table rows and add them to jTable model
            while (rs.next()) {
                Object[] row = new Object[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }
            return model;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}