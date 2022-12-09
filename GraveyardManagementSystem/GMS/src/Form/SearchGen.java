
package Form;


import com.toedter.calendar.JDateChooser;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tamanna
 */
public class SearchGen extends javax.swing.JFrame {
String gender;
    /**
     * Creates new form update
     */
    public SearchGen() {
        initComponents();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       //setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
        tableGen.getTableHeader().setBackground(new Color(32,134,203));
        tableGen.getTableHeader().setForeground(new Color(255,255,255));
        tableGen.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        tableGen.getTableHeader().setOpaque(false);
        tableGen.setRowHeight(25);
        
        show_table();
        
       // jTable1.getTableHeader().setDefaultRenderer(new update());
    }
    public void setTableRowWidth(){
        tableGen.setAutoResizeMode(tableGen.AUTO_RESIZE_OFF);
        TableColumnModel col = tableGen.getColumnModel();
        for(int i=0;i<20;i++)
        {
            col.getColumn(i).setPreferredWidth(150);
        }
    }
    public void clearAllFields(){
           plotSize2.setSelectedIndex(0);
           searchCorpseType.setSelectedIndex(0);
           //buttonGroup1.clearSelection();
           male.setSelected(false);
           female.setSelected(false);
           other.setSelected(false);
           plotNo.setText("");
           name.setText("");
           GuardianName.setText("");
           JDateChooser dateChooser = new JDateChooser();
           burriedDate.setCalendar(null);
           clearRows();
           show_table();
           
           
    }
    public void clearRows(){
        tableGen.setModel(new DefaultTableModel(null,new String[]{"Grave ID","Size","Corpse ID","Type","Name","Gender","Father's name","Mother's name","Date of birth","Date of death","Time of death","Cause of death","Guardian's name","Relation","Address","Contact no.","NID no","Birth Cert. No.","Burried date","Recycle date"}));
    }
  
    public ArrayList<Corpse>corpseList(){
        ArrayList<Corpse> corpseList = new ArrayList<>();
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
            JOptionPane.showMessageDialog(main, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(main,ex);
        }
     return corpseList;
           
    }
    public void show_table(){
    ArrayList<Corpse> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)tableGen.getModel();
    setTableRowWidth();
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

        main = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableGen = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        plotNo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        plotSize2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        burriedDate = new com.toedter.calendar.JDateChooser();
        searchdate = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        GuardianName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        searchCorpseType = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tableGen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableGen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grave ID", "Size", "Corpse ID", "Type", "Name", "Gender", "Father's name", "Mother's name", "Date of birth", "Date of death", "Time of death", "Cause of death", "Guardian's name", "Relation", "Address", "Contact no.", "NID no", "Birth Cert. No.", "Burried date", "Recycle date"
            }
        ));
        tableGen.setGridColor(new java.awt.Color(255, 255, 255));
        tableGen.setSelectionBackground(new java.awt.Color(204, 0, 51));
        tableGen.getTableHeader().setReorderingAllowed(false);
        tableGen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGenMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableGen);

        main.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 237, 1320, 400));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Corpse name:");
        main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, 40));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        main.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 240, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Plot Id:");
        main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 110, 40));

        plotNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotNoActionPerformed(evt);
            }
        });
        main.add(plotNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 230, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Plot Type:");
        main.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 40, 120, 38));

        plotSize2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plot Size", "Small", "Medium", "Large" }));
        plotSize2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotSize2ActionPerformed(evt);
            }
        });
        main.add(plotSize2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 230, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Burried Date:");
        main.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 110, 38));

        burriedDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                burriedDateMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                burriedDateMousePressed(evt);
            }
        });
        main.add(burriedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 160, 30));

        searchdate.setText("Set");
        searchdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchdateActionPerformed(evt);
            }
        });
        main.add(searchdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 70, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Guardian name:");
        main.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 104, 140, 38));

        GuardianName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardianNameActionPerformed(evt);
            }
        });
        main.add(GuardianName, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 240, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Gender:");
        main.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 132, 38));

        male.setText("Male");
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });
        main.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 60, 30));

        female.setText("Female");
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });
        main.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 70, 30));

        other.setText("Other");
        other.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherActionPerformed(evt);
            }
        });
        main.add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 70, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Plot Size:");
        main.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 100, 38));

        searchCorpseType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plot type", "Identified", "Unidentified" }));
        searchCorpseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCorpseTypeActionPerformed(evt);
            }
        });
        main.add(searchCorpseType, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 220, 30));

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        main.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 660, -1, -1));

        reset.setText("Reset values");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        main.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 130, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableGenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGenMouseClicked
        

    }//GEN-LAST:event_tableGenMouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        String value = name.getText();
        String searchQuery = "SELECT * FROM GeneralGrave1 WHERE corpseName LIKE '%" +value+"%'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            tableGen.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }

    }//GEN-LAST:event_nameActionPerformed

    private void plotNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotNoActionPerformed
        String value = plotNo.getText();
        String searchQuery = "SELECT * FROM GeneralGrave1 WHERE generalGraveId ='"+value+"'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            tableGen.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_plotNoActionPerformed

    private void plotSize2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotSize2ActionPerformed
        
        String size = plotSize2.getSelectedItem().toString();

        String searchQuery = "SELECT * FROM GeneralGrave1 WHERE generalGraveSize ='"+size+"'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            tableGen.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_plotSize2ActionPerformed

    private void burriedDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burriedDateMouseClicked

    }//GEN-LAST:event_burriedDateMouseClicked

    private void burriedDateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burriedDateMousePressed

    }//GEN-LAST:event_burriedDateMousePressed

    private void searchdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchdateActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String recycle_date = sdf.format(burriedDate.getDate());

        String searchQuery = "SELECT * FROM GeneralGrave1 WHERE burriedDate ='"+recycle_date+"'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            tableGen.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_searchdateActionPerformed

    private void GuardianNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardianNameActionPerformed
        String value = GuardianName.getText();

        String searchQuery = "SELECT * FROM GeneralGrave1 WHERE guardianName LIKE '%" +value+"%'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            tableGen.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_GuardianNameActionPerformed

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed

        if(male.isSelected()){
            gender="male";

            String value = gender;
            String searchQuery = "SELECT * FROM GeneralGrave1 WHERE corpseGender ='"+value+"'";

            try {
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
                PreparedStatement pst = conn.prepareStatement(searchQuery);
                ResultSet rs = pst.executeQuery();
                tableGen.setModel(DbUtils.resultSetToTableModel(rs));
                setTableRowWidth();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Got an exception!");
                System.err.println(e.getMessage());
            }
        }

    }//GEN-LAST:event_maleActionPerformed

    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        if(female.isSelected()){
            gender="female";

            String value = gender;
            String searchQuery = "SELECT * FROM GeneralGrave1 WHERE corpseGender ='"+value+"'";

            try {
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
                PreparedStatement pst = conn.prepareStatement(searchQuery);
                ResultSet rs = pst.executeQuery();
                tableGen.setModel(DbUtils.resultSetToTableModel(rs));
                setTableRowWidth();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Got an exception!");
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_femaleActionPerformed

    private void otherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherActionPerformed
      if(other.isSelected()){
                gender="other";
                
                 String value = gender;
        String searchQuery = "SELECT * FROM PermanentGrave WHERE corpseGender ='"+value+"'";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            tableGen.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }
    }//GEN-LAST:event_otherActionPerformed

    private void searchCorpseTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCorpseTypeActionPerformed
      String size = searchCorpseType.getSelectedItem().toString();

        String searchQuery = "SELECT * FROM GeneralGrave1 WHERE corpseType ='"+size+"'";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            tableGen.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_searchCorpseTypeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          this.setVisible(false);
      MainMenu mm = new MainMenu();
      mm.setVisible(true);
      mm.openGravePanel();
      mm.openmenuPanel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
      clearAllFields();
    }//GEN-LAST:event_resetActionPerformed

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
            java.util.logging.Logger.getLogger(SearchGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchGen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchGen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField GuardianName;
    private com.toedter.calendar.JDateChooser burriedDate;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel main;
    private javax.swing.JRadioButton male;
    private javax.swing.JTextField name;
    private javax.swing.JRadioButton other;
    private javax.swing.JTextField plotNo;
    private javax.swing.JComboBox<String> plotSize2;
    private javax.swing.JButton reset;
    private javax.swing.JComboBox<String> searchCorpseType;
    private javax.swing.JButton searchdate;
    private javax.swing.JTable tableGen;
    // End of variables declaration//GEN-END:variables
}
