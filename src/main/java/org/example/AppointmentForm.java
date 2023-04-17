package org.example;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentForm extends JFrame implements ActionListener {
    private Connection conn;
    private PreparedStatement pstmt;
    private JLabel nameLabel, phoneLabel, dateLabel, timeLabel;
    private JTextField nameField, phoneField, dateField, timeField;
    private JButton submitButton, cancelButton;
    private static final String ACCOUNT_SID = "ACe4b623ecfa730a698c0ca05a544b1791";
    private static final String AUTH_TOKEN = "5335c4b78ace0cbf4fc86831a50d6a87";

    public AppointmentForm() {
        // Configurer la fenêtre
        setTitle("Prendre un rendez-vous");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(5, 2));

        // Création des labels
        nameLabel = new JLabel("Nom:");
        phoneLabel = new JLabel("Téléphone:");
        dateLabel = new JLabel("Date (jj/mm/aaaa):");
        timeLabel = new JLabel("Heure (hh:mm):");

        // Create the text fields
        nameField = new JTextField();
        phoneField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();

        // Create the buttons
        submitButton = new JButton("Enregistrer");
        cancelButton = new JButton("Annuler");
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Add the components to the window
        add(nameLabel);
        add(nameField);
        add(phoneLabel);
        add(phoneField);
        add(dateLabel);
        add(dateField);
        add(timeLabel);
        add(timeField);
        add(submitButton);
        add(cancelButton);

        // Display the window
        setVisible(true);
        setLocationRelativeTo(null);

        // Ajout du listener pour gérer la fermeture de la fenêtre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Affichage de la boîte de dialogue de confirmation
                int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter le formulaire de prise de rendez-vous ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Fermeture de la fenêtre
                    dispose();
                    // Retour à la page d'accueil
                    Accueil accueil = new Accueil();
                    accueil.setVisible(true);
                }
            }
        });
    }

    private void saveAppointment(String name, String phone, String date, String time) {
        // Vérifier que tous les champs ont été remplis
        if (name.isEmpty() || phone == null || date == null || time == null) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        // Insérer le rendez-vous dans la base de données
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/appdentaire?user=root&password=");
            String query = "INSERT INTO appointments (name, phone, date, time) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'insertion du rendez-vous dans la base de données.");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Ignore les erreurs de fermeture de connexion
            }
        }

        // Envoyer un SMS en utilisant l'API Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(phone.toString()), new PhoneNumber("+14344236569"),
                "Rendez-vous enregistré pour " + name + " le " + date.toString() + " à " + time.toString() + ".").create();

        // Afficher un message à l'utilisateur
        JOptionPane.showMessageDialog(this, "Rendez-vous enregistré pour " + name + " le " + date.toString() + " à " + time.toString() + ".");
    }

    public static void main(String[] args) {
        new AppointmentForm();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String date = dateField.getText();
            String time = timeField.getText();
            saveAppointment(name, phone, date, time);
        }
    }
}