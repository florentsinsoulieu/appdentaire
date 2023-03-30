package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {

        public Accueil() {

                setTitle("AppDentaire");
                setSize(500, 400);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel label = new JLabel("Bienvenue chez AppDentaire");
                label.setFont(new Font("Arial", Font.BOLD, 12));
                label.setHorizontalAlignment(SwingConstants.CENTER);

                JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5)); // GridLayout pour les boutons avec 2 lignes, 2 colonnes, 10px de marge horizontale et verticale
                JButton button1 = new JButton("Prendre rendez-vous");
                button1.setPreferredSize(new Dimension(100, 50));
                button1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                AppointmentForm appointmentForm = new AppointmentForm();
                                appointmentForm.setVisible(true);
                                dispose();
                        }
                });
                buttonPanel.add(button1);

                JButton button2 = new JButton("Carte de visite du Docteur");
                button2.setPreferredSize(new Dimension(100, 50));
                button2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                CarteDeVisite carteDeVisite = new CarteDeVisite();
                                carteDeVisite.setVisible(true);
                                dispose();
                        }
                });
                buttonPanel.add(button2);

                JButton button3 = new JButton("Historique des soins dentaire");
                button3.setPreferredSize(new Dimension(100, 50));
                button3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                HistoriqueSoin historiqueSoin = new HistoriqueSoin();
                                historiqueSoin.setVisible(true);
                                dispose();
                        }
                });
                buttonPanel.add(button3);


                JButton button4 = new JButton("Horaire d'ouverture");
                button4.setPreferredSize(new Dimension(100, 50));
                button4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                Horaire horaire = new Horaire();
                                horaire.setVisible(true);
                                dispose();
                        }
                });
                buttonPanel.add(button4);

                JButton button5 = new JButton("Nos engagements");
                button5.setPreferredSize(new Dimension(100, 50));
                button5.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                Engagement engagement = new Engagement();
                                engagement.setVisible(true);
                                dispose();
                        }
                });

                buttonPanel.add(button5);

                JButton button6 = new JButton("Machine disponible au cabinet");
                button6.setPreferredSize(new Dimension(100, 50));
                button6.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                MachineDispo machineDispo = new MachineDispo();
                                machineDispo.setVisible(true);
                                dispose();
                        }
                });

                buttonPanel.add(button6);

                JPanel panel = new JPanel(new BorderLayout());
                panel.add(label, BorderLayout.NORTH);
                panel.add(buttonPanel, BorderLayout.CENTER);


                add(panel);
                setVisible(true);
        }

        public static void main(String[] args) {

                new Accueil();
        }
}