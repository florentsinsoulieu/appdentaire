package org.example;

import javax.swing.*;

public class Engagement extends JFrame{

    public Engagement() {
        setTitle("Nos engagements");
        setSize(700, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel infoLabel = new JLabel("<html><p><strong>Sourire :</strong> Notre équipe souriante vous accueille toujours avec bienveillance au cabinet et par téléphone.</p>" +
                "<p><strong>Éthique : </strong>Nous prenons le temps d’échanger en toute transparence avec vous sur les soins à apporter. </p>" +
                "<p><strong>Écologie :</strong>Notre engagement écologique se traduit au quotidien par notre volonté de limiter notre empreinte carbone.</p>" +
                "<p><strong>High-tech :</strong>Nous utilisons du matériel de haute technologie pour prendre soin de vos dents.</p>");

        panel.add(infoLabel);

        add(panel);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter la présentation des engagements ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Engagement.this.dispose();
                    Accueil accueil= new Accueil();
                    accueil.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        new Engagement();
    }
}

