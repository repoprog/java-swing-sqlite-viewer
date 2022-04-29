package viewer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SQLiteViewer extends JFrame {
    private static JComboBox tablesCombo;
    private String chosenTable;
    public SQLiteViewer() {
        super("SQLite Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        initComponents();

    }

    private void initComponents() {
        JTextField textField = new JTextField();
        textField.setName("FileNameTextField");
        textField.setBounds(50, 20, 480, 30);
        add(textField);

        JButton openButton = new JButton("Open");
        openButton.setName("OpenFileButton");
        openButton.setBounds(550, 20, 100, 30);
        add(openButton);

        tablesCombo = new JComboBox();
        tablesCombo.setName("TablesComboBox");
        tablesCombo.setBounds(50, 60, 600, 30);
        add(tablesCombo);

        JTextArea queryArea = new JTextArea();
        queryArea.setName("QueryTextArea");
        queryArea.setBounds(50, 100, 470, 80);
        add(queryArea);

        JButton executeButton = new JButton("Execute");
        executeButton.setName("ExecuteQueryButton");
        executeButton.setBounds(530, 100, 120, 35);
        add(executeButton);

        JTable jTable = new JTable();
        jTable.setName("Table");
        JScrollPane jTablePane = new JScrollPane(jTable); //Remember about JScrollPane to set table visible!
        jTablePane.setBounds(50, 195,600, 300);
        jTablePane.setVisible(true);
        add(jTablePane);

        // ACTIONS
        // open button
        openButton.addActionListener(e -> {
            tablesCombo.removeAllItems();
            DataBaseManager.setFileName(textField.getText().trim());
            DataBaseManager.selectTablesNames();
        });
        // chose from combo
        tablesCombo.addActionListener(e -> {
            chosenTable = (String) tablesCombo.getSelectedItem();
            queryArea.setText("SELECT * FROM " + chosenTable + ";");
        });
        // Execute button
        executeButton.addActionListener(e -> jTable.setModel(DataBaseManager.fillJTable(queryArea.getText())));
    }

    public static void fillCombo() {
        for (String table : DataBaseManager.getComboElements()) {
            tablesCombo.addItem(table);
        }

    }
}