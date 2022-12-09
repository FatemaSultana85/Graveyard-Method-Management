
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

public class GeneralGraveTable extends javax.swing.JFrame {

    String gender;
    
    public GeneralGraveTable() {
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
        
       // jTable1.getTableHeader().setDefaultRenderer(new update());
    }
    public ArrayList<Corpse>corpseList(){
        ArrayList<Corpse> corpseList = new ArrayList<>();
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
         
            String query1 = "SELECT * FROM GeneralGrave1";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Corpse corpse;
            while(rs.next())
            {
              corpse = new Corpse(rs.getInt("generalGraveId"),rs.getString("generalGraveSize"),rs.getInt("generalCorpseId"),
                      rs.getString("corpseType"),rs.getString("corpseName"),rs.getString("corpseGender"),
                      rs.getString("corpseFatherName"),rs.getString("corpseMotherName"),rs.getString("corpseDateOfBirth"),
                      rs.getString("corpseDateOfDeath"),rs.getString("corpseTimeOfDeath"),rs.getString("corpseCauseOfDeath"),
                      rs.getString("guardianContactNo"),rs.getString("guardianRelation"),rs.getString("guardianAddress"),
                      rs.getString("guardianName"),rs.getString("corpseNidNo"),rs.getString("corpseBirthCertificateNo"),
                      rs.getString("burriedDate"),rs.getString("RecycleDate"));
                      
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
    ArrayList<Corpse> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    Object[] row = new Object[20];
    for(int i = 0; i<list.size(); i++)
    {
        row[0] = list.get(i).getgeneralGraveId();
        row[1] = list.get(i).getgeneralGraveSize();
        row[2] = list.get(i).getgeneralCorpseId();
        row[3] = list.get(i).getcorpseType();
        row[4] = list.get(i).getcorpseName();
        row[5] = list.get(i).getcorpseGender();
        row[6] = list.get(i).getcorpseFatherName();
        row[7] = list.get(i).getcorpseMotherName();
        row[8] = list.get(i).getcorpseDateOfBirth(); 
        row[9] = list.get(i).getcorpseDateOfDeath();
        row[10] = list.get(i).getcorpseTimeOfDeath();
        row[11] = list.get(i).getcorpseCauseOfDeath();
        row[12] = list.get(i).getguardianName();
        row[13] = list.get(i).getguardianRelation();
        row[14] = list.get(i).getguardianAddress();
        row[15] = list.get(i).getguardianContactNo();
        row[16] = list.get(i).getcorpseNidNo();
        row[17] = list.get(i).getcorpseBirthCertificateNo();
        row[18] = list.get(i).getburriedDate();
        row[19] = list.get(i).getRecycleDate();
        model.addRow(row);
    }
  
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        upPanel.setBackground(new java.awt.Color(255, 255, 255));

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
                "Grave ID", "Size", "Corpse ID", "Type", "Name", "Gender", "Father's name", "Mother's name", "Date of birth", "Date of death", "Time of death", "Cause of death", "Contact No", "Relation", "Address", "guardian Name", "NID no", "Birth Certificate No", "Burried date", "Recycle date"
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("General Grave Table");

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout upPanelLayout = new javax.swing.GroupLayout(upPanel);
        upPanel.setLayout(upPanelLayout);
        upPanelLayout.setHorizontalGroup(
            upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upPanelLayout.createSequentialGroup()
                .addGroup(upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(upPanelLayout.createSequentialGroup()
                        .addGroup(upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upPanelLayout.createSequentialGroup()
                                .addGap(599, 599, 599)
                                .addComponent(Back))
                            .addGroup(upPanelLayout.createSequentialGroup()
                                .addGap(515, 515, 515)
                                .addComponent(jLabel1)))
                        .addGap(0, 604, Short.MAX_VALUE)))
                .addContainerGap())
        );
        upPanelLayout.setVerticalGroup(
            upPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(Back)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(upPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
       this.dispose();
        MainMenu b = new MainMenu();
        b.setVisible(true);
        b.openTablePanel();
        b.openmenuPanel();
    }//GEN-LAST:event_BackActionPerformed

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
            java.util.logging.Logger.getLogger(GeneralGraveTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeneralGraveTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeneralGraveTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeneralGraveTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralGraveTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel upPanel;
    // End of variables declaration//GEN-END:variables
}
