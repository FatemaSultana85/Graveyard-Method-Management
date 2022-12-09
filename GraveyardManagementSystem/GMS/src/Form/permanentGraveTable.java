/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
public class permanentGraveTable extends javax.swing.JFrame {
 String gender;
 
    public permanentGraveTable() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
        jTable1.getTableHeader().setBackground(new Color(32,134,203));
        jTable1.getTableHeader().setForeground(new Color(255,255,255));
        jTable1.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.setRowHeight(25);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        TableColumnModel col = jTable1.getColumnModel();
        for(int i=0;i<20;i++)
        {
            col.getColumn(i).setPreferredWidth(150);
        }
       // jTable1.getTableHeader().setDefaultRenderer(new update());
       show_table();
    }
   
     public ArrayList<UpdateAll>corpseList(){
        ArrayList<UpdateAll> corpseList = new ArrayList<>();
     try {

     try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     } 
     catch (ClassNotFoundException ex) 
     {
         JOptionPane.showMessageDialog(upPanel, "Error connecting to database");
     }
            Connection connection = DriverManager .getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");
         
            String query1 = "SELECT OwnerInfo.serialNo,\n" +
                                "OwnerInfo.ownerId,\n" +
                                "OwnerInfo.ownerName,\n" +
                                "OwnerInfo.ownerPhoneNumber,\n" +
                                "OwnerInfo.ownerNidOrBirthCertificate,\n" +
                                "OwnerInfo.ownerAddress,\n" +
                                "OwnerInfo.SellingStatus,\n" +
                                "OwnerInfo.availablility,\n" +
                                "Permanent_CorpseInfo.permanentCorpseId,\n" +
                                "Permanent_CorpseInfo.permanentGraveId,\n" +
                                "Permanent_CorpseInfo.permanentGraveSize,\n" +
                                "Permanent_CorpseInfo.RelationWithOwner,\n" +
                                "Permanent_CorpseInfo.corpseName,\n" +
                                "Permanent_CorpseInfo.corpseGender,\n" +
                                "Permanent_CorpseInfo.corpseFatherName,\n" +
                                "Permanent_CorpseInfo.corpseMotherName,\n" +
                                "Permanent_CorpseInfo.corpseDateOfBirth,\n" +
                                "Permanent_CorpseInfo.corpseDateOfDeath,\n" +
                                "Permanent_CorpseInfo.corpseTimeOfDeath,\n" +
                                "Permanent_CorpseInfo.corpseCauseOfDeath,\n" +
                                "Permanent_CorpseInfo.corpseNidOrBirthCertificate,\n" +
                                "Permanent_CorpseInfo.burriedDate,\n" +
                                "Permanent_CorpseInfo.RecycleDate,\n" +
                                "paymentInfo.fee,\n" +
                                "paymentInfo.paymentid\n" +
                                "FROM paymentInfo  \n" +
                                "INNER JOIN OwnerInfo \n" +
                                "ON paymentInfo.generalGraveId = OwnerInfo.generalGraveId\n" +
                                "LEFT JOIN Permanent_CorpseInfo\n" +
                                "ON Permanent_CorpseInfo.permanentGraveId=OwnerInfo.generalGraveId \n" +
                                ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            UpdateAll corpse;
            while(rs.next())
            {
               corpse = new UpdateAll(rs.getInt("serialNo"),rs.getInt("ownerId"),
                      rs.getString("ownerName"),rs.getInt("ownerPhoneNumber"),
                       rs.getString("ownerNidOrBirthCertificate"),
                      rs.getString("ownerAddress"),rs.getString("sellingStatus"),
                       rs.getString("availablility"),rs.getInt("permanentCorpseId"),rs.getInt("permanentGraveId"),
                       rs.getString("permanentGraveSize"),
                      rs.getString("RelationWithOwner"),
                        rs.getString("corpseName"),rs.getString("corpseGender"),
                      rs.getString("corpseFatherName"),rs.getString("corpseMotherName"),
                      rs.getString("corpseDateOfBirth"),
                      rs.getString("corpseDateOfDeath"),rs.getString("corpseTimeOfDeath"),
                      rs.getString("corpseCauseOfDeath"),rs.getString("corpseNidOrBirthCertificate"),rs.getString("burriedDate"),
                      rs.getString("RecycleDate"),rs.getInt("fee"),rs.getInt("paymentid"));
                      
              corpseList.add(corpse);
            }
            //connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(upPanel, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(upPanel,ex);
        }
     return corpseList;
           
    }
     
    public void show_table(){
    ArrayList<UpdateAll> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    Object[] row = new Object[25];
    for(int i = 0; i<list.size(); i++)
    {
        row[0] = list.get(i).getserialNo();
        row[1] = list.get(i).getownerId();
        row[2] = list.get(i).getownerName();
        row[3] = list.get(i).getownerPhoneNumber();
        row[4] = list.get(i).getownerNidOrBirthCertificate();
        row[5] = list.get(i).getownerAddress();
        row[6] = list.get(i).getSellingStatus();
        row[7] = list.get(i).getavailablility(); 
        row[8] = list.get(i).getpermanentCorpseId();
        row[9] = list.get(i).getpermanentGraveId();
        row[10] = list.get(i).getpermanentGraveSize();
        row[11] = list.get(i).getRelationWithOwner();
        row[12] = list.get(i).getcorpseName();
        row[13] = list.get(i).getcorpseGender();
        row[14] = list.get(i).getcorpseFatherName();
        row[15] = list.get(i).getcorpseMotherName();
        row[16] = list.get(i).getcorpseDateOfBirth();
        row[17] = list.get(i).getcorpseDateOfDeath();
        row[18] = list.get(i).getcorpseTimeOfDeath();
        row[19] = list.get(i).getcorpseCauseOfDeath();
        row[20] = list.get(i).getcorpseNidOrBirthCertificate();
        row[21] = list.get(i).getburriedDate();
        row[22] = list.get(i).getRecycleDate();
        row[23] = list.get(i).getfee();
        row[24] = list.get(i).getpaymentid();
                                
        model.addRow(row);
        
    }
   
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        upPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Permanent Grave Table");

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Purchase Id", "Owner Id", "Owner Name", "Owner Contact No", "Owner NID/Birth Certificate", "Owner Address", "Selling Status", "Availability", "Corpse Id", "Plot No", "Plot Size", "Relation with Owner", "Corpse Name", "Corpse Gender", "Corpse Father Name", "Corpse Mother Name", "Date Of Birth", "Date Of Death", "Time of Death", "Cause Of death", "Corpse NID/Birth Certificate", "Burried Date", "Recycle Date", "Fee", "Payment Id"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout upPanelLayout = new javax.swing.GroupLayout(upPanel);
        upPanel.setLayout(upPanelLayout);
        upPanelLayout.setHorizontalGroup(
            upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upPanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(539, 539, 539))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upPanelLayout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(646, 646, 646))))
        );
        upPanelLayout.setVerticalGroup(
            upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(back)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        this.dispose();
        MainMenu b = new MainMenu();
        b.setVisible(true);
        b.openTablePanel();
        b.openmenuPanel();
    }//GEN-LAST:event_backActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(permanentGraveTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(permanentGraveTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(permanentGraveTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(permanentGraveTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new permanentGraveTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel upPanel;
    // End of variables declaration//GEN-END:variables
}
