/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.miage.m2sid.agentClient.ui;

import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.miage.m2sid.agentClient.ClientAgent;


/**
 *
 * @author utilisateur
 */
public class Gui extends javax.swing.JFrame {

    
    ClientAgent clientAgent; 
    /**
     * Creates new form Produit
     */
    public Gui(Agent a ) {
        clientAgent=(ClientAgent)a;
        initComponents();
        
    }

    private Gui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduits = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCourses = new javax.swing.JTable();
        btAjouter = new javax.swing.JButton();
        btSupprimer = new javax.swing.JButton();
        btEnvoyer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Liste des produits"));

        tableProduits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", ""},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nom Produit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduits.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableProduits.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableProduits);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Liste des courses"));

        tableCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom Produit", "Quantité"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCourses.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jScrollPane2.setViewportView(tableCourses);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );

        btAjouter.setText(">");
        btAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAjouterActionPerformed(evt);
            }
        });

        btSupprimer.setText("<");
        btSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSupprimerActionPerformed(evt);
            }
        });

        btEnvoyer.setText("envoyer");
        btEnvoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnvoyerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btEnvoyer)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btAjouter)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btSupprimer))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btAjouter)
                        .addGap(35, 35, 35)
                        .addComponent(btSupprimer)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(btEnvoyer)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAjouterActionPerformed

        int selectedRow = tableProduits.getSelectedRow();
        if (selectedRow >= 0) {
            int id = Integer.valueOf(tableProduits.getValueAt(selectedRow, 0).toString());
            String nomProduit = tableProduits.getValueAt(selectedRow, 1).toString();
            DefaultTableModel modelTableCourses = (DefaultTableModel) tableCourses.getModel();
            String qte = JOptionPane.showInputDialog(this, "Pour quelle quantité ?", 1);
            if (null != qte) {
                if (qte.trim().length() > 0) {
                    try {
                        int quantite = Integer.valueOf(qte);
                        modelTableCourses.addRow(new Object[]{id, nomProduit, qte});
                        mapCourses.put(id, quantite);
//                        if (mapCourses.containsKey(id)) {
//                            int qteProd = mapCourses.get(id);
//                            mapCourses.replace(id, ++qteProd);
//                        } else {
//                            mapCourses.put(id, 1);
//                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Quantité non valide " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

//            if (!mapCourses.containsKey(id)) {
//                mapCourses.put(id, id);
//            }
            System.out.println("projagent1.Produit.btAjouterActionPerformed() " + mapCourses);

        }
    }//GEN-LAST:event_btAjouterActionPerformed

    private void btSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSupprimerActionPerformed

    }//GEN-LAST:event_btSupprimerActionPerformed

    private void btEnvoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnvoyerActionPerformed

        String adressIP = JOptionPane.showInputDialog("Donner l'adresse IP");
        
        try {

            jade.core.Runtime runtime = jade.core.Runtime.instance();
            ProfileImpl profileImpl = new ProfileImpl(false);
            profileImpl.setParameter(ProfileImpl.MAIN_HOST, adressIP);
            System.out.println("projagent1.Accueil.btLancerCliSellerActionPerformed() " + profileImpl);
            AgentContainer agentContainer = runtime.createAgentContainer(profileImpl);
            String className = ClientAgent.class.getName();
            String nickname = "seller " + Math.floor(100 * Math.random() % 1000);
            AgentController agentController = agentContainer.createNewAgent(nickname, className, new Object[]{});
            agentController.start();
            clientAgent.demandeProduit(mapCourses);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getClass(),
                    "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        
       
    }//GEN-LAST:event_btEnvoyerActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
                String title[] = new String[]{"Id", "Nom Produit"};
                Object data[][] = new Object[][]{
                    {"1", "Carotte"}, {"2", "Poireau"}, {"4", "Brie"}, {"5", "Comté"},
                    {"6", "Lait"}, {"7", "Bière"}, {"8", "Eau"}, {"9", "Coca"}, {"10", "Javel"}
                };

                tableProduits.setModel(new DefaultTableModel(data, title));
                
            }
        });
    }

    HashMap<Integer, Integer> mapCourses = new HashMap<>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAjouter;
    private javax.swing.JButton btEnvoyer;
    private javax.swing.JButton btSupprimer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableCourses;
    private static javax.swing.JTable tableProduits;
    // End of variables declaration//GEN-END:variables
}
