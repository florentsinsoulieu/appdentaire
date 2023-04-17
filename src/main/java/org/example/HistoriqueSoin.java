
package org.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HistoriqueSoin extends JFrame {

    private JTable table;
    private String[] columnNames = {"Date", "Type de soin", "Dentiste", "Coût"};

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

        // Création du modèle de tableau
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Création de la table
        table = new JTable(model);

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajout du JScrollPane dans la fenêtre
        add(scrollPane, BorderLayout.CENTER);

        // Ajout du bouton facturation
        JButton facturationButton = new JButton("Facturation");
        facturationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String[] rowData = new String[columnNames.length];
                    for (int i = 0; i < columnNames.length; i++) {
                        rowData[i] = table.getValueAt(selectedRow, i).toString();
                    }
                    generatePDF(rowData);
                    JOptionPane.showMessageDialog(null, "Facture générée avec succès !");
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne.");
                }
            }
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(facturationButton);
        add(bottomPanel, BorderLayout.SOUTH);

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

    private void generatePDF(String[] rowData) {
        try {
            String dest = "facture-" + rowData[0].replaceAll("/", "-") + ".pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Paragraph title = new Paragraph("Facture");
            title.setFontSize(20);
            document.add(title);


            Table pdfTable = new Table(UnitValue.createPercentArray(columnNames.length));
            pdfTable.setWidth(UnitValue.createPercentValue(100));
            for (String columnName : columnNames) {
                pdfTable.addCell(columnName);
            }

            for (String cellData : rowData) {
                pdfTable.addCell(cellData);
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(null, "Facture générée avec succès dans le fichier : " + dest);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la génération de la facture.");
        }
    }

    public static void main(String[] args) {

        new HistoriqueSoin();
    }
}