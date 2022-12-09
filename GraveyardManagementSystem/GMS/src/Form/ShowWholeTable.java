
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
import net.proteanit.sql.DbUtils;


public class ShowWholeTable extends javax.swing.JFrame {

    public ShowWholeTable() {
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
        show_table();
    }
     public ArrayList<WholeTable>corpseList(){
        ArrayList<WholeTable> corpseList = new ArrayList<>();
     try {

     try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     } 
     catch (ClassNotFoundException ex) 
     {
         JOptionPane.showMessageDialog(main, "Error connecting to database");
     }
            Connection connection = DriverManager .getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");
         
            String query1 = "(select \n" +
                                "generalGraveId as plotNo,\n" +
                                "generalGraveSize as plotSize,\n" +
                                "generalCorpseId as corpseId,\n" +
                                "corpseType as type,\n" +
                                "corpseName as name,\n" +
                                "corpseGender as gender,\n" +
                                "corpseFatherName as father,\n" +
                                "corpseMotherName as mother,\n" +
                                "corpseDateOfBirth as dob,\n" +
                                "corpseDateOfDeath as dod,\n" +
                                "corpseTimeOfDeath as tod,\n" +
                                "corpseCauseOfDeath as cod,\n" +
                                "guardianContactNo as phn,\n" +
                                "guardianRelation as relation,\n" +
                                "guardianAddress as address,\n" +
                                "guardianName as relativeName,\n" +
                                "corpseNidNo as nid,\n" +
                                "burriedDate as burried,\n" +
                                "RecycleDate as recylce,\n" +
                                "fee as fee\n" +
                                " from GeneralGrave1)\n" +
                                "UNION all\n" +
                                "(SELECT\n" +
                                "Permanent_CorpseInfo.permanentGraveId as plotNo,\n" +
                                "Permanent_CorpseInfo.permanentGraveSize as plotSize,\n" +
                                "Permanent_CorpseInfo.permanentCorpseId as corpseId,\n" +
                                "Permanent_CorpseInfo.corpseType as type,\n" +
                                "Permanent_CorpseInfo.corpseName as name,\n" +
                                "Permanent_CorpseInfo.corpseGender as gender,\n" +
                                "Permanent_CorpseInfo.corpseFatherName as father,\n" +
                                "Permanent_CorpseInfo.corpseMotherName as mother,\n" +
                                "Permanent_CorpseInfo.corpseDateOfBirth as dob,\n" +
                                "Permanent_CorpseInfo.corpseDateOfDeath as dod,\n" +
                                "Permanent_CorpseInfo.corpseTimeOfDeath as tod,\n" +
                                "Permanent_CorpseInfo.corpseCauseOfDeath as cod,\n" +
                                "OwnerInfo.ownerPhoneNumber as phn,\n" +
                                "Permanent_CorpseInfo.RelationWithOwner as relation,\n" +
                                "OwnerInfo.ownerAddress as address,\n" +
                                "OwnerInfo.ownerName relativeName,\n" +
                                "Permanent_CorpseInfo.corpseNidOrBirthCertificate as nid,\n" +
                                "Permanent_CorpseInfo.burriedDate as burried,\n" +
                                "Permanent_CorpseInfo.RecycleDate as recylce,\n" +
                                "paymentInfo.fee as fee\n" +
                                "FROM OwnerInfo\n" +
                                "INNER JOIN  Permanent_CorpseInfo\n" +
                                "ON Permanent_CorpseInfo.permanentGraveId=OwnerInfo.generalGraveId\n" +
                                "INNER JOIN paymentInfo\n" +
                                "ON OwnerInfo.generalGraveId=paymentInfo.generalGraveId\n" +
                                " )";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            WholeTable corpse;
            while(rs.next())
            {
               corpse = new WholeTable(rs.getInt("plotNo"),
                                        rs.getString("plotSize"),
                                        rs.getInt("corpseId"),
                                        rs.getString("type"),
                                        rs.getString("name"),
                                        rs.getString("gender"),
                                        rs.getString("father"),
                                        rs.getString("mother"),
                                        rs.getString("dob"),
                                        rs.getString("dod"),
                                        rs.getString("tod"),
                                        rs.getString("cod"),
                                        rs.getString("phn"),
                                        rs.getString("relation"),
                                        rs.getString("address"),
                                        rs.getString("relativeName"),
                                        rs.getString("nid"),
                                        rs.getString("burried"),
                                        rs.getString("recylce"),
                                        rs.getInt("fee"));
                      
              corpseList.add(corpse);
            }
            //connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(main, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(main,ex);
        }
     return corpseList;
           
    }
     
    public void show_table(){
    ArrayList<WholeTable> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    Object[] row = new Object[20];
    for(int i = 0; i<list.size(); i++)
    {
        row[0] = list.get(i).getplotNo();
        row[1] = list.get(i).getplotSize();
        row[2] = list.get(i).getcorpseId();
        row[3] = list.get(i).gettype();
        row[4] = list.get(i).getname();
        row[5] = list.get(i).getgender();
        row[6] = list.get(i).getfather();
        row[7] = list.get(i).getmother(); 
        row[8] = list.get(i).getdob();
        row[9] = list.get(i).getdod();
        row[10] = list.get(i).gettod();
        row[11] = list.get(i).getcod();
        row[12] = list.get(i).getphn();
        row[13] = list.get(i).getrelation();
        row[14] = list.get(i).getaddress();
        row[15] = list.get(i).getrelativeName();
        row[16] = list.get(i).getnid();
        row[17] = list.get(i).getburried();
        row[18] = list.get(i).getrecylce();
        row[19] = list.get(i).getfee();
        
                                
        model.addRow(row);
        
    }
   
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
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
                "Plot No", "plot Size", "Corpse Id", "Corpse Type", "Corpse Name", "Corpse Gender", "Corpse Father Name", "Corpse Mother Name", "Date Of Birth", "Date Of Death", "Time of Death", "Cause of Death", "owner/guardian contact No", "owner/guardian relation", "owner/guardian address", "owner/guardian Name", "Corpse  NID/Birth Certificate", "Burried Date", "Recycle Date", "fee"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Graveyard Database");

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap(564, Short.MAX_VALUE)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(619, 619, 619))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(551, 551, 551))))
            .addComponent(jScrollPane2)
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(ShowWholeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowWholeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowWholeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowWholeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowWholeTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main;
    // End of variables declaration//GEN-END:variables
}
