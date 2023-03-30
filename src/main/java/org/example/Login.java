package org.example;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Login {
    private JFrame frame;
    private JTextField textUsername;
    private JPasswordField fieldPassword;
    private JButton buttonLogin;
    private Connection conn;
    private Statement stmt;

    public Login() {
        createForm();
        createDatabaseConnection();
        addActionListeners();
        displayForm();
    }

    private void createForm() {
        frame = new JFrame("Connection AppDentaire");
        frame.setLayout(new GridLayout(3, 2));
        frame.add(new JLabel("Mail du patient: "));
        textUsername = new JTextField(20);
        frame.add(textUsername);
        frame.add(new JLabel("Mot de passe : "));
        fieldPassword = new JPasswordField(20);
        frame.add(fieldPassword);
        buttonLogin = new JButton("Bienvenue ! ");
        frame.add(new Label());
        frame.add(buttonLogin);
    }

    private void createDatabaseConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/appdentaire", "root", "");
            stmt = conn.createStatement();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void addActionListeners() {
        buttonLogin.addActionListener(e -> {
            String username = textUsername.getText();
            char[] password = fieldPassword.getPassword();
            try {
                String query = "SELECT * FROM accounts WHERE mail = '" + username + "' AND password = '" + new String(password) + "'";
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Cliquez sur ok pour être rediriger    ");
                    frame.dispose();
                     Accueil accueil= new Accueil();
                     accueil.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed.");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        });
    }

    private void displayForm() {
        frame.setSize(400, 120);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}