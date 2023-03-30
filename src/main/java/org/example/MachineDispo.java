package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MachineDispo extends JFrame{
    private JTable table;

    public MachineDispo() {
        setTitle("Machine disponible au sein de notre cabinet");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tableau de données
        Object[][] data = {
                {"01/01/2023", "Empreinte numérique", "Dr Maskoff",},
                {"15/02/2023", "Radio panoramique numérique", "Dr Maskoff",},
                {"10/03/2023", "Radio numérique intra-orale", "Dr Maskoff",},
                {"22/03/2023", "Scanner dentaire numérique", "Dr Maskoff",},
                {"19/03/2023", "Salle dédiée à l’implantologie", "Dr Maskoff",},
                {"15/03/2023", "Four à céramique", "Dr Maskoff",},
                {"12/03/2023", "Aéropolisseur", "Dr Maskoff",},
                {"07/03/2023", "CEREC", "Dr Maskoff",},
                {"09/03/2023", "Microscope opératoire", "Dr Maskoff",},
                {"25/03/2023", "Laser dentaire", "Dr Maskoff",}

        };

        // Noms de colonnes
        String[] columnNames = {"Date du produit", "Nom du produit", "Dentiste",};

        // Création du modèle de tableau
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Création de la table
        table = new JTable(model);

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajout du JScrollPane dans la fenêtre
        add(scrollPane, BorderLayout.CENTER);

        // Affichage de la fenêtre
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'historique des machines disponible ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    MachineDispo.this.dispose();
                    Accueil accueil= new Accueil();
                    accueil.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {

        new MachineDispo();
    }
}
