/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientrest;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Pipis
 */
public class ProfileGUI extends javax.swing.JPanel {

    MyRestFrame m;
    Leitourgies l1;
    ArrayList<String> list_friends, list_posts;
    String[] field = {"Username", "Realname", "Surname", "Birthdate", "City", "Country", "Gender", "Description"};

    //Αρχικοποίηση του ProfileGUI πάνω στο JFrame
    public ProfileGUI(MyRestFrame m) {
        initComponents();
        this.m = m;
        m.setTitle("Logged as : " + m.getLoggedUser());
        l1 = new Leitourgies();
        refresh();
        this.setVisible(true);

    }

    //Συνάρτηση ενημέρωσης αποτελεσμάτων
    public void refresh() {
        String logged = m.getLoggedUser();
        list_friends = (ArrayList<String>) l1.getFriends(logged);
        //Ανάκτηση δεδομένων για Posts,Friends,User's info
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return list_friends.size();
            }

            public String getElementAt(int i) {
                return list_friends.get(i);
            }
        });
        ArrayList<String> list2 = (ArrayList<String>) l1.getProfile(logged);
        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return list2.size();
            }

            public String getElementAt(int i) {
                return list2.get(i);
            }
        });

        list_posts = (ArrayList<String>) l1.getPosts(logged);
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return list_posts.size();
            }

            public String getElementAt(int i) {
                return list_posts.get(i);
            }
        });
        jLabel4.setText(" ");

        this.repaint();
    }
    
    //Συνάρτηση overload για μειωση επαναχρησιμοποίησης κώδικα
    public void draw(String label, JComboBox<String> combo, JPanel myPanel, JTextField yField) {
        myPanel.add(new JLabel(label));
        myPanel.add(combo);
        myPanel.add(yField);
        myPanel.add(Box.createVerticalStrut(15));
    }

    public void draw(String label, JTextField yField, JPanel myPanel) {
        myPanel.add(new JLabel(label));
        myPanel.add(yField);
        myPanel.add(Box.createVerticalStrut(15));
    }

    public void draw(String label, JComboBox combo, JPanel myPanel) {
        myPanel.add(new JLabel(label));
        myPanel.add(combo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jLabel1.setText("Posts");

        jLabel2.setText("Friends");

        jLabel3.setText("Profile Info");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        jButton1.setText("Edit Profile");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Add FRIEND");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Post ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Refresh");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Search friend's info");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Delete friend");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Delete Post");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Edit Post");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6)
                                    .addComponent(jButton5))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jButton8))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6)))
                .addGap(22, 45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Δημιουργία γραφικού για επεξεργασία προφίλ (άνοιγμα νέου παραθύρου)
        JTextField yField = new JTextField(50);
        JComboBox<String> combo = new JComboBox<String>(field);
        JPanel myPanel = new JPanel();
        draw("Choose edit field", combo, myPanel, yField);
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Edit Profile", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            jLabel4.setText(l1.editProfile(m.getLoggedUser(), combo.getSelectedItem().toString(), yField.getText()));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Δημιουργία γραφικού για post (άνοιγμα νέου παραθύρου)
        JTextField yField = new JTextField(100);
        list_friends.add(0, m.getLoggedUser());
        Vector v = new Vector(list_friends);
        JComboBox<String> combo = new JComboBox<String>(v);
        JPanel myPanel = new JPanel();
        draw("Choose friend to post:", combo, myPanel, yField);
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Write a post in your profile or to a friend!", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            jLabel4.setText(l1.addPost(m.getLoggedUser(), combo.getSelectedItem().toString(), yField.getText()));
        }
        //Αφαίρεση του εαυτού του συνδεδεμένου χρήστη απο τη λίστα φίλων 
        //ο οποίος προστίθεται σαυτήν την συνάρτηση γιανα υπάρχει δυνατότητα 
        //να ποστάρει στον τοίχο του
        list_friends.remove(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Δημιουργία γραφικού για προσθήκη φίλου (άνοιγμα νέου παραθύρου)
        JTextField yField = new JTextField(25);
        JPanel myPanel = new JPanel();
        draw("Write the username of your friend:", yField, myPanel);
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Add Friend", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            jLabel4.setText(l1.addFriend(m.getLoggedUser(), yField.getText()));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Δημιουργία γραφικού για προβολή πληροφωριών φίλου (άνοιγμα νέου παραθύρου)
        Vector v = new Vector(list_friends);
        JComboBox<String> combo = new JComboBox<String>(v);
        JPanel myPanel = new JPanel();
        ArrayList<String> info;
        draw("Friend :", combo, myPanel);
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Choose friend's info", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            JPanel panel = new JPanel();
            info = (ArrayList) l1.getProfile(combo.getSelectedItem().toString());
            for (String a : info) {
                panel.add(new JLabel(a));
            }
            JOptionPane.showMessageDialog(null, panel, "Friend's Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //Δημιουργία γραφικού για διαγραφή φίλου (άνοιγμα νέου παραθύρου)
        Vector v = new Vector(list_friends);
        JComboBox<String> combo = new JComboBox<String>(v);
        JPanel myPanel = new JPanel();
        draw("Friend :", combo, myPanel);
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Choose friend to delete", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            jLabel4.setText(l1.deleteFriend(m.getLoggedUser(), combo.getSelectedItem().toString()));
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //Δημιουργία γραφικού για διαγραφή post από το προφίλ (άνοιγμα νέου παραθύρου)
        Vector v = new Vector(list_posts);
        JComboBox<String> combo = new JComboBox<String>(v);
        JPanel myPanel = new JPanel();
        draw("Post :", combo, myPanel);
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Choose post to delete", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            jLabel4.setText(l1.deletePost(m.getLoggedUser(), combo.getSelectedItem().toString()));
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //Δημιουργία γραφικού για τροποποίηση post (άνοιγμα νέου παραθύρου)
        Vector v = new Vector(list_posts);
        JTextField yField = new JTextField(100);
        JComboBox<String> combo = new JComboBox<String>(v);
        JPanel myPanel = new JPanel();
        draw("Post :", combo, myPanel, yField);
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Choose post to edit", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            jLabel4.setText(l1.editPost(m.getLoggedUser(), combo.getSelectedItem().toString(), yField.getText()));
        }
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
