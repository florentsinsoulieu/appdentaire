package org.example;

import javax.swing.*;

public class CarteDeVisite extends JFrame {

    public CarteDeVisite() {
        setTitle("Carte de visite");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel infoLabel = new JLabel("<html><p><strong>Nom du Médecin :</strong> Vladimir Maskoff.</p>" +
                "<p><strong>Entreprise :</strong> App Dentaire.</p>" +
                "<p><strong>Adresse :</strong> 2 Bis avenue Petit Fabron 06200 Nice.</p>" +
                "<p><strong>Ville :</strong> Nice, FR.</p>" +
                "<p><strong>Téléphone :</strong> 06-09-09-09-09.</p>" +
                "<p><strong>Email :</strong> appdentaire@info.fr</p></html>");

        panel.add(infoLabel);

        add(panel);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter la carte de visite ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                CarteDeVisite.this.dispose();
                Accueil accueil= new Accueil();
                accueil.setVisible(true);
            }
            }
        });
    }

    public static void main(String[] args) {
        new CarteDeVisite();
    }
}