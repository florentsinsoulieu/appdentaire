package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoriqueSoin extends JFrame {

    private JTable table;

    public HistoriqueSoin() {
        setTitle("Historique de soins dentaires");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tableau de données
        Object[][] data = {
                {"01/01/2023", "Détartrage", "Dr Maskoff", "80 €"},
                {"15/02/2023", "Carie soignée", "Dr Maskoff", "90 €"},
                {"10/03/2023", "Blanchiment", "Dr Maskoff", "450 €"},
                {"18/03/2023", "Extraction d'une dent", "Dr Maskoff", "75 €"},
                {"05/02/2023", "Pose d'une couronne dentaire", "Dr Maskoff", "650 €"},
                {"09/02/2023", "Pose d'un implant dentaire", "Dr Maskoff", "1500 €"}
        };

        // Noms de colonnes
        String[] columnNames = {"Date", "Type de soin", "Dentiste", "Coût"};

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
                int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'historique des soins ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    HistoriqueSoin.this.dispose();
                    Accueil accueil= new Accueil();
                    accueil.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {

        new HistoriqueSoin();
    }
}