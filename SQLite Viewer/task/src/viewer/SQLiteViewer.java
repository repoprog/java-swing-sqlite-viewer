package viewer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SQLiteViewer extends JFrame {
    private static JComboBox tablesCombo;
    private static JButton executeButton;
    private static JTextArea queryTextArea;

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

        queryTextArea = new JTextArea();
        queryTextArea.setName("QueryTextArea");
        queryTextArea.setBounds(50, 100, 470, 80);
        queryTextArea.setEnabled(false);
        add(queryTextArea);

        executeButton = new JButton("Execute");
        executeButton.setName("ExecuteQueryButton");
        executeButton.setBounds(530, 100, 120, 35);
        executeButton.setEnabled(false);
        add(executeButton);

        JTable jTable = new JTable();
        jTable.setName("Table");
        JScrollPane jTablePane = new JScrollPane(jTable); //Remember about JScrollPane to set table visible!
        jTablePane.setBounds(50, 195, 600, 300);
        jTablePane.setVisible(true);
        add(jTablePane);

        // ACTIONS
        // open button
        openButton.addActionListener(e -> {
            tablesCombo.removeAllItems();
            String fileName = textField.getText().trim();

            if (new File(fileName).exists()) {
                enableJComponents(true);
                DataBaseManager.setDataBase(fileName);
                DataBaseManager.selectTablesNames();
            } else {
                JOptionPane.showMessageDialog(new Frame(), "Wrong file name!");
                enableJComponents(false);
            }
        });

        // chose from combo
        tablesCombo.addActionListener(e ->
                queryTextArea.setText("SELECT * FROM " + tablesCombo.getSelectedItem() + ";"));

        // Execute button
        executeButton.addActionListener(e ->
                jTable.setModel(DataBaseManager.fillJTable(queryTextArea.getText())));

    }

    public static void fillCombo() {
        for (String table : DataBaseManager.getComboElements()) {
            tablesCombo.addItem(table);
        }
    }

    public static void enableJComponents(Boolean flag) {
        executeButton.setEnabled(flag);
        queryTextArea.setEnabled(flag);
    }

}