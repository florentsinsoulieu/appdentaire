package org.example;

import javax.swing.*;

public class Horaire extends JFrame{


    public Horaire() {
        setTitle("Horaire d'ouverture");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel infoLabel = new JLabel("<html><p><strong>Horaire Ouverture: 9h-12h30 / 14h30-18h. </strong> " +
                "<p><strong>Jours Ouverture : Lundi/Mardi/Jeudi/Vendredi. ");

        panel.add(infoLabel);

        add(panel);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter les horaires d'ouverture ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Horaire.this.dispose();
                    Accueil accueil= new Accueil();
                    accueil.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        new Horaire();
    }
}



