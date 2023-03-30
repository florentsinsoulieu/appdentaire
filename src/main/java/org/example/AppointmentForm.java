package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class AppointmentForm extends JFrame implements ActionListener {
    private JLabel nameLabel, phoneLabel, dateLabel, timeLabel;
    private JTextField nameField, phoneField, dateField, timeField;
    private JButton submitButton, cancelButton;
    private static final String ACCOUNT_SID = "ACe4b623ecfa730a698c0ca05a544b1791";
    private static final String AUTH_TOKEN = "a2fab0462d949386f2855fc52e1ffde1";

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
        submitButton = new JButton("Enregistré");
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Get the values from the text fields
            String name = nameField.getText();
            String phone = phoneField.getText();
            String date = dateField.getText();
            String time = timeField.getText();

            // TODO: Validate the input and save the appointment to the database
            // ...

            // Send SMS using Twilio API
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber(phone), new PhoneNumber("+14344236569"),
                    "Rendez-vous enregistré pour " + name + " le " + date + " à " + time + ".").create();

            // Show a message to the user
            JOptionPane.showMessageDialog(this, "Rendez-vous enregistré pour " + name + " le " + date + " à " + time + ".");
        } else if (e.getSource() == cancelButton) {
            // Close the window
            dispose();
        }
    }

    public static void main(String[] args) {
        new AppointmentForm();
    }
}