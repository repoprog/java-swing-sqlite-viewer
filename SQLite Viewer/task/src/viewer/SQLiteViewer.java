package viewer;

import javax.swing.*;

public class SQLiteViewer extends JFrame {

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
        JTextField nameTextField = new JTextField();
        nameTextField.setName("FileNameTextField");
        nameTextField.setBounds(50, 20, 400, 30);
        add(nameTextField);

        JButton openButton = new JButton("Open");
        openButton.setName("OpenFileButton");
        openButton.setBounds(550, 20, 100, 30);
        add(openButton);
    }
}
