
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





public class SearchPer extends javax.swing.JFrame {

   String gender;
    public SearchPer() {
        initComponents();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       // setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
        jTable1.getTableHeader().setBackground(new Color(32,134,203));
        jTable1.getTableHeader().setForeground(new Color(255,255,255));
        jTable1.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.setRowHeight(25);
        
        show_table();
        
        
       // jTable1.getTableHeader().setDefaultRenderer(new update());
    }
      public void setTableRowWidth(){
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        TableColumnModel col = jTable1.getColumnModel();
        for(int i=0;i<20;i++)
        {
            col.getColumn(i).setPreferredWidth(150);
        }
    }
    public void clearAllFields(){
           plotSize2.setSelectedIndex(0);
           availability.setSelectedIndex(0);
           male.setSelected(false);
           female.setSelected(false);
           other.setSelected(false);
           textId.setText("");
           text.setText("");
           searchOwnerName.setText("");
           JDateChooser dateChooser = new JDateChooser();
           burriedDate.setCalendar(null);
           ownerId.setText("");
           causeOfDeath.setSelectedIndex(0);
           relationWithOwner.setSelectedIndex(0);
           clearRows();
           show_table();
           
           
    }
    public void clearRows(){
        jTable1.setModel(new DefaultTableModel(null,new String[]{"Purchase Id","Owner Id","Owner Name","Owner Contact No","Owner NID/Birth Certificate","Owner Address","Selling Status","Availability","Owner Name","Recycle Date","Burried Date","Corpse NID/Birth Certificate","Cause of death","Time of Death","Date of Death","Corpse Id","Plot No","Plot Size","Relation with owner","Corpse Name","Corpse Gender","Corpse Father Name","Corpse Mother Name","Date of Birth","Fee","Payment Id"}));
    }
  
        public ArrayList<UpdateAll>corpseList(){
        ArrayList<UpdateAll> corpseList = new ArrayList<>();
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
            JOptionPane.showMessageDialog(main, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(main,ex);
        }
     return corpseList;
           
    }
     
    public void show_table(){
    ArrayList<UpdateAll> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    setTableRowWidth();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        text = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textId = new javax.swing.JTextField();
        searchdate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        plotSize2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        searchOwnerName = new javax.swing.JTextField();
        male = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        relationWithOwner = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        causeOfDeath = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        availability = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        ownerId = new javax.swing.JTextField();
        reset = new javax.swing.JButton();
        burriedDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Plot Id:");
        main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 150, 30));

        text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textActionPerformed(evt);
            }
        });
        main.add(text, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 210, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Corpse name:");
        main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 180, 38));

        textId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdActionPerformed(evt);
            }
        });
        main.add(textId, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 200, 30));

        searchdate.setText("Search");
        searchdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchdateActionPerformed(evt);
            }
        });
        main.add(searchdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 30, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Burried Date:");
        main.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, 38));

        plotSize2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Small", "Medium", "Large" }));
        plotSize2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotSize2ActionPerformed(evt);
            }
        });
        main.add(plotSize2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 200, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Gender:");
        main.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 132, 38));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Plot Size:");
        main.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, 40));

        searchOwnerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchOwnerNameActionPerformed(evt);
            }
        });
        main.add(searchOwnerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 30, 180, 30));

        male.setText("Male");
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });
        main.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 60, 30));

        other.setText("Other");
        other.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherActionPerformed(evt);
            }
        });
        main.add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, -1, 30));

        female.setText("Female");
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });
        main.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Owner name:");
        main.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 30, 150, 38));

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        main.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 670, -1, -1));

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

        main.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1360, 437));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Plot's Availability:");
        main.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 207, 30));

        relationWithOwner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Father", "Mother", "Brother", "Sister", "Husband", "Wife", "Daughter", "Son" }));
        relationWithOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationWithOwnerActionPerformed(evt);
            }
        });
        main.add(relationWithOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 90, 180, 26));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Relation:");
        main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 80, 120, 30));

        causeOfDeath.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Natural", "Accidental", "Disease", "Homicide", "Suicide", "Pandemic" }));
        causeOfDeath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                causeOfDeathActionPerformed(evt);
            }
        });
        main.add(causeOfDeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 210, 26));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Cause of Death:");
        main.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 160, 30));

        availability.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "empty", "Occupied" }));
        availability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availabilityActionPerformed(evt);
            }
        });
        main.add(availability, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 200, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("Owner Id:");
        main.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 156, 30));

        ownerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerIdActionPerformed(evt);
            }
        });
        main.add(ownerId, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 210, 30));

        reset.setText("Reset Values");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        main.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 150, 310, 40));
        main.add(burriedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 150, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textActionPerformed
         String value = text.getText();
        String searchQuery = "SELECT a.serialNo,\n" +
                            "a.ownerId,\n" +
                            "a.ownerName,\n" +
                            "a.ownerPhoneNumber,\n" +
                            "a.ownerNidOrBirthCertificate,\n" +
                            "a.ownerAddress,\n" +
                            "a.SellingStatus,\n" +
                            "a.availablility,\n" +
                            "b.permanentCorpseId,\n" +
                            "b.permanentGraveId,\n" +
                            "b.permanentGraveSize,\n" +
                            "b.RelationWithOwner,\n" +
                            "b.corpseName,\n" +
                            "b.corpseGender,\n" +
                            "b.corpseFatherName,\n" +
                            "b.corpseMotherName,\n" +
                            "b.corpseDateOfBirth,\n" +
                            "b.corpseDateOfDeath,\n" +
                            "b.corpseTimeOfDeath,\n" +
                            "b.corpseCauseOfDeath,\n" +
                            "b.corpseNidOrBirthCertificate,\n" +
                            "b.burriedDate,\n" +
                            "b.RecycleDate,\n" +
                            "c.fee,\n" +
                            "c.paymentid  \n" +
                            "FROM OwnerInfo AS a  \n" +
                            "INNER JOIN Permanent_CorpseInfo AS b  \n" +
                            "ON b.permanentGraveId=a.generalGraveId \n" +
                            "AND corpseName LIKE '%"+value+"%'\n" +
                            "INNER JOIN paymentInfo AS c  \n" +
                            "ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    }      
        
    }//GEN-LAST:event_textActionPerformed

    private void textIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdActionPerformed
             String value = textId.getText();
        String searchQuery = " SELECT a.serialNo,\n" +
                                "a.ownerId,\n" +
                                "a.ownerName,\n" +
                                "a.ownerPhoneNumber,\n" +
                                "a.ownerNidOrBirthCertificate,\n" +
                                "a.ownerAddress,\n" +
                                "a.SellingStatus,\n" +
                                "a.availablility,\n" +
                                "b.permanentCorpseId,\n" +
                                "b.permanentGraveId,\n" +
                                "b.permanentGraveSize,\n" +
                                "b.RelationWithOwner,\n" +
                                "b.corpseName,\n" +
                                "b.corpseGender,\n" +
                                "b.corpseFatherName,\n" +
                                "b.corpseMotherName,\n" +
                                "b.corpseDateOfBirth,\n" +
                                "b.corpseDateOfDeath,\n" +
                                "b.corpseTimeOfDeath,\n" +
                                "b.corpseCauseOfDeath,\n" +
                                "b.corpseNidOrBirthCertificate,\n" +
                                "b.burriedDate,\n" +
                                "b.RecycleDate,\n" +
                                "c.fee,\n" +
                                "c.paymentid  \n" +
                                "FROM OwnerInfo AS a  \n" +
                                "INNER JOIN Permanent_CorpseInfo AS b  \n" +
                                "ON b.permanentGraveId=a.generalGraveId \n" +
                                "AND permanentGraveId ="+value+"\n" +
                                "INNER JOIN paymentInfo AS c  \n" +
                                "ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_textIdActionPerformed

    private void searchdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchdateActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String burried_date = sdf.format(burriedDate.getDate());
       
        String searchQuery = " SELECT a.serialNo,\n" +
"a.ownerId,\n" +
"a.ownerName,\n" +
"a.ownerPhoneNumber,\n" +
"a.ownerNidOrBirthCertificate,\n" +
"a.ownerAddress,\n" +
"a.SellingStatus,\n" +
"a.availablility,\n" +
"b.permanentCorpseId,\n" +
"b.permanentGraveId,\n" +
"b.permanentGraveSize,\n" +
"b.RelationWithOwner,\n" +
"b.corpseName,\n" +
"b.corpseGender,\n" +
"b.corpseFatherName,\n" +
"b.corpseMotherName,\n" +
"b.corpseDateOfBirth,\n" +
"b.corpseDateOfDeath,\n" +
"b.corpseTimeOfDeath,\n" +
"b.corpseCauseOfDeath,\n" +
"b.corpseNidOrBirthCertificate,\n" +
"b.burriedDate,\n" +
"b.RecycleDate,\n" +
"c.fee,\n" +
"c.paymentid  \n" +
"FROM OwnerInfo AS a  \n" +
"INNER JOIN Permanent_CorpseInfo AS b  \n" +
"ON b.permanentGraveId=a.generalGraveId \n" +
"AND burriedDate ='"+burried_date+"'\n" +
"INNER JOIN paymentInfo AS c  \n" +
"ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_searchdateActionPerformed

    private void burriedDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burriedDateMouseClicked
      
    }//GEN-LAST:event_burriedDateMouseClicked

    private void burriedDateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burriedDateMousePressed
       
    }//GEN-LAST:event_burriedDateMousePressed

    private void plotSize2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotSize2ActionPerformed
        String size = plotSize2.getSelectedItem().toString();  
        
         String searchQuery = " SELECT a.serialNo,\n" +
                                "a.ownerId,\n" +
                                "a.ownerName,\n" +
                                "a.ownerPhoneNumber,\n" +
                                "a.ownerNidOrBirthCertificate,\n" +
                                "a.ownerAddress,\n" +
                                "a.SellingStatus,\n" +
                                "a.availablility,\n" +
                                "b.permanentCorpseId,\n" +
                                "b.permanentGraveId,\n" +
                                "b.permanentGraveSize,\n" +
                                "b.RelationWithOwner,\n" +
                                "b.corpseName,\n" +
                                "b.corpseGender,\n" +
                                "b.corpseFatherName,\n" +
                                "b.corpseMotherName,\n" +
                                "b.corpseDateOfBirth,\n" +
                                "b.corpseDateOfDeath,\n" +
                                "b.corpseTimeOfDeath,\n" +
                                "b.corpseCauseOfDeath,\n" +
                                "b.corpseNidOrBirthCertificate,\n" +
                                "b.burriedDate,\n" +
                                "b.RecycleDate,\n" +
                                "c.fee,\n" +
                                "c.paymentid  \n" +
                                "FROM OwnerInfo AS a  \n" +
                                "INNER JOIN Permanent_CorpseInfo AS b  \n" +
                                "ON b.permanentGraveId=a.generalGraveId \n" +
                                "AND permanentGraveSize ='"+size+"'\n" +
                                "INNER JOIN paymentInfo AS c  \n" +
                                "ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_plotSize2ActionPerformed

    private void searchOwnerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchOwnerNameActionPerformed
        String value = searchOwnerName.getText();
        
          String searchQuery = " SELECT a.serialNo,\n" +
                                "a.ownerId,\n" +
                                "a.ownerName,\n" +
                                "a.ownerPhoneNumber,\n" +
                                "a.ownerNidOrBirthCertificate,\n" +
                                "a.ownerAddress,\n" +
                                "a.SellingStatus,\n" +
                                "a.availablility,\n" +
                                "b.permanentCorpseId,\n" +
                                "b.permanentGraveId,\n" +
                                "b.permanentGraveSize,\n" +
                                "b.RelationWithOwner,\n" +
                                "b.corpseName,\n" +
                                "b.corpseGender,\n" +
                                "b.corpseFatherName,\n" +
                                "b.corpseMotherName,\n" +
                                "b.corpseDateOfBirth,\n" +
                                "b.corpseDateOfDeath,\n" +
                                "b.corpseTimeOfDeath,\n" +
                                "b.corpseCauseOfDeath,\n" +
                                "b.corpseNidOrBirthCertificate,\n" +
                                "b.burriedDate,\n" +
                                "b.RecycleDate,\n" +
                                "c.fee,\n" +
                                "c.paymentid  \n" +
                                "FROM OwnerInfo AS a  \n" +
                                "INNER JOIN Permanent_CorpseInfo AS b  \n" +
                                "ON b.permanentGraveId=a.generalGraveId \n" +
                                "AND ownerName LIKE '%"+value+"%'\n" +
                                "INNER JOIN paymentInfo AS c  \n" +
                                "ON c.generalGraveId = a.generalGraveId";

        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_searchOwnerNameActionPerformed

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
       
        if(male.isSelected()){
                gender="male";
                
                 String value = gender;
        String searchQuery = " SELECT a.serialNo,\n" +
"a.ownerId,\n" +
"a.ownerName,\n" +
"a.ownerPhoneNumber,\n" +
"a.ownerNidOrBirthCertificate,\n" +
"a.ownerAddress,\n" +
"a.SellingStatus,\n" +
"a.availablility,\n" +
"b.permanentCorpseId,\n" +
"b.permanentGraveId,\n" +
"b.permanentGraveSize,\n" +
"b.RelationWithOwner,\n" +
"b.corpseName,\n" +
"b.corpseGender,\n" +
"b.corpseFatherName,\n" +
"b.corpseMotherName,\n" +
"b.corpseDateOfBirth,\n" +
"b.corpseDateOfDeath,\n" +
"b.corpseTimeOfDeath,\n" +
"b.corpseCauseOfDeath,\n" +
"b.corpseNidOrBirthCertificate,\n" +
"b.burriedDate,\n" +
"b.RecycleDate,\n" +
"c.fee,\n" +
"c.paymentid  \n" +
"FROM OwnerInfo AS a  \n" +
"INNER JOIN Permanent_CorpseInfo AS b  \n" +
"ON b.permanentGraveId=a.generalGraveId \n" +
"AND corpseGender ='"+value+"'\n" +
"INNER JOIN paymentInfo AS c  \n" +
"ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
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
        String searchQuery = " SELECT a.serialNo,\n" +
"a.ownerId,\n" +
"a.ownerName,\n" +
"a.ownerPhoneNumber,\n" +
"a.ownerNidOrBirthCertificate,\n" +
"a.ownerAddress,\n" +
"a.SellingStatus,\n" +
"a.availablility,\n" +
"b.permanentCorpseId,\n" +
"b.permanentGraveId,\n" +
"b.permanentGraveSize,\n" +
"b.RelationWithOwner,\n" +
"b.corpseName,\n" +
"b.corpseGender,\n" +
"b.corpseFatherName,\n" +
"b.corpseMotherName,\n" +
"b.corpseDateOfBirth,\n" +
"b.corpseDateOfDeath,\n" +
"b.corpseTimeOfDeath,\n" +
"b.corpseCauseOfDeath,\n" +
"b.corpseNidOrBirthCertificate,\n" +
"b.burriedDate,\n" +
"b.RecycleDate,\n" +
"c.fee,\n" +
"c.paymentid  \n" +
"FROM OwnerInfo AS a  \n" +
"INNER JOIN Permanent_CorpseInfo AS b  \n" +
"ON b.permanentGraveId=a.generalGraveId \n" +
"AND corpseGender ='"+value+"'\n" +
"INNER JOIN paymentInfo AS c  \n" +
"ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
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
        String searchQuery =  " SELECT a.serialNo,\n" +
"a.ownerId,\n" +
"a.ownerName,\n" +
"a.ownerPhoneNumber,\n" +
"a.ownerNidOrBirthCertificate,\n" +
"a.ownerAddress,\n" +
"a.SellingStatus,\n" +
"a.availablility,\n" +
"b.permanentCorpseId,\n" +
"b.permanentGraveId,\n" +
"b.permanentGraveSize,\n" +
"b.RelationWithOwner,\n" +
"b.corpseName,\n" +
"b.corpseGender,\n" +
"b.corpseFatherName,\n" +
"b.corpseMotherName,\n" +
"b.corpseDateOfBirth,\n" +
"b.corpseDateOfDeath,\n" +
"b.corpseTimeOfDeath,\n" +
"b.corpseCauseOfDeath,\n" +
"b.corpseNidOrBirthCertificate,\n" +
"b.burriedDate,\n" +
"b.RecycleDate,\n" +
"c.fee,\n" +
"c.paymentid  \n" +
"FROM OwnerInfo AS a  \n" +
"INNER JOIN Permanent_CorpseInfo AS b  \n" +
"ON b.permanentGraveId=a.generalGraveId \n" +
"AND corpseGender ='"+value+"'\n" +
"INNER JOIN paymentInfo AS c  \n" +
"ON c.generalGraveId = a.generalGraveId";
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }
    }//GEN-LAST:event_otherActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       

    }//GEN-LAST:event_jTable1MouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void relationWithOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationWithOwnerActionPerformed
       String relation = relationWithOwner.getSelectedItem().toString();        
         String searchQuery = " SELECT a.serialNo,\n" +
                                "a.ownerId,\n" +
                                "a.ownerName,\n" +
                                "a.ownerPhoneNumber,\n" +
                                "a.ownerNidOrBirthCertificate,\n" +
                                "a.ownerAddress,\n" +
                                "a.SellingStatus,\n" +
                                "a.availablility,\n" +
                                "b.permanentCorpseId,\n" +
                                "b.permanentGraveId,\n" +
                                "b.permanentGraveSize,\n" +
                                "b.RelationWithOwner,\n" +
                                "b.corpseName,\n" +
                                "b.corpseGender,\n" +
                                "b.corpseFatherName,\n" +
                                "b.corpseMotherName,\n" +
                                "b.corpseDateOfBirth,\n" +
                                "b.corpseDateOfDeath,\n" +
                                "b.corpseTimeOfDeath,\n" +
                                "b.corpseCauseOfDeath,\n" +
                                "b.corpseNidOrBirthCertificate,\n" +
                                "b.burriedDate,\n" +
                                "b.RecycleDate,\n" +
                                "c.fee,\n" +
                                "c.paymentid  \n" +
                                "FROM OwnerInfo AS a  \n" +
                                "INNER JOIN Permanent_CorpseInfo AS b  \n" +
                                "ON b.permanentGraveId=a.generalGraveId \n" +
                                "AND RelationWithOwner ='"+relation+"'\n" +
                                "INNER JOIN paymentInfo AS c  \n" +
                                "ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_relationWithOwnerActionPerformed

    private void causeOfDeathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_causeOfDeathActionPerformed
          String cause = causeOfDeath.getSelectedItem().toString();        
         String searchQuery = " SELECT a.serialNo,\n" +
                                "a.ownerId,\n" +
                                "a.ownerName,\n" +
                                "a.ownerPhoneNumber,\n" +
                                "a.ownerNidOrBirthCertificate,\n" +
                                "a.ownerAddress,\n" +
                                "a.SellingStatus,\n" +
                                "a.availablility,\n" +
                                "b.permanentCorpseId,\n" +
                                "b.permanentGraveId,\n" +
                                "b.permanentGraveSize,\n" +
                                "b.RelationWithOwner,\n" +
                                "b.corpseName,\n" +
                                "b.corpseGender,\n" +
                                "b.corpseFatherName,\n" +
                                "b.corpseMotherName,\n" +
                                "b.corpseDateOfBirth,\n" +
                                "b.corpseDateOfDeath,\n" +
                                "b.corpseTimeOfDeath,\n" +
                                "b.corpseCauseOfDeath,\n" +
                                "b.corpseNidOrBirthCertificate,\n" +
                                "b.burriedDate,\n" +
                                "b.RecycleDate,\n" +
                                "c.fee,\n" +
                                "c.paymentid  \n" +
                                "FROM OwnerInfo AS a  \n" +
                                "INNER JOIN Permanent_CorpseInfo AS b  \n" +
                                "ON b.permanentGraveId=a.generalGraveId \n" +
                                "AND corpseCauseOfDeath ='"+cause+"'\n" +
                                "INNER JOIN paymentInfo AS c  \n" +
                                "ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_causeOfDeathActionPerformed

    private void availabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_availabilityActionPerformed
       String available = availability.getSelectedItem().toString();        
         String searchQuery = " SELECT a.serialNo,\n" +
                                "a.ownerId,\n" +
                                "a.ownerName,\n" +
                                "a.ownerPhoneNumber,\n" +
                                "a.ownerNidOrBirthCertificate,\n" +
                                "a.ownerAddress,\n" +
                                "a.SellingStatus,\n" +
                                "a.availablility,\n" +
                                "b.permanentCorpseId,\n" +
                                "b.permanentGraveId,\n" +
                                "b.permanentGraveSize,\n" +
                                "b.RelationWithOwner,\n" +
                                "b.corpseName,\n" +
                                "b.corpseGender,\n" +
                                "b.corpseFatherName,\n" +
                                "b.corpseMotherName,\n" +
                                "b.corpseDateOfBirth,\n" +
                                "b.corpseDateOfDeath,\n" +
                                "b.corpseTimeOfDeath,\n" +
                                "b.corpseCauseOfDeath,\n" +
                                "b.corpseNidOrBirthCertificate,\n" +
                                "b.burriedDate,\n" +
                                "b.RecycleDate,\n" +
                                "c.fee,\n" +
                                "c.paymentid  \n" +
                                "FROM OwnerInfo AS a  \n" +
                                "INNER JOIN Permanent_CorpseInfo AS b  \n" +
                                "ON b.permanentGraveId=a.generalGraveId \n" +
                                "AND a.availablility ='"+available+"'\n" +
                                "INNER JOIN paymentInfo AS c  \n" +
                                "ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_availabilityActionPerformed

    private void ownerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerIdActionPerformed
          String value = ownerId.getText();
        String searchQuery = " SELECT a.serialNo,\n" +
                                "a.ownerId,\n" +
                                "a.ownerName,\n" +
                                "a.ownerPhoneNumber,\n" +
                                "a.ownerNidOrBirthCertificate,\n" +
                                "a.ownerAddress,\n" +
                                "a.SellingStatus,\n" +
                                "a.availablility,\n" +
                                "b.permanentCorpseId,\n" +
                                "b.permanentGraveId,\n" +
                                "b.permanentGraveSize,\n" +
                                "b.RelationWithOwner,\n" +
                                "b.corpseName,\n" +
                                "b.corpseGender,\n" +
                                "b.corpseFatherName,\n" +
                                "b.corpseMotherName,\n" +
                                "b.corpseDateOfBirth,\n" +
                                "b.corpseDateOfDeath,\n" +
                                "b.corpseTimeOfDeath,\n" +
                                "b.corpseCauseOfDeath,\n" +
                                "b.corpseNidOrBirthCertificate,\n" +
                                "b.burriedDate,\n" +
                                "b.RecycleDate,\n" +
                                "c.fee,\n" +
                                "c.paymentid  \n" +
                                "FROM OwnerInfo AS a  \n" +
                                "INNER JOIN Permanent_CorpseInfo AS b  \n" +
                                "ON b.permanentGraveId=a.generalGraveId \n" +
                                "AND a.ownerId ="+value+"\n" +
                                "INNER JOIN paymentInfo AS c  \n" +
                                "ON c.generalGraveId = a.generalGraveId";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            setTableRowWidth();
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_ownerIdActionPerformed

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
            java.util.logging.Logger.getLogger(SearchPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchPer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> availability;
    private com.toedter.calendar.JDateChooser burriedDate;
    private javax.swing.JComboBox<String> causeOfDeath;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main;
    private javax.swing.JRadioButton male;
    private javax.swing.JRadioButton other;
    private javax.swing.JTextField ownerId;
    private javax.swing.JComboBox<String> plotSize2;
    private javax.swing.JComboBox<String> relationWithOwner;
    private javax.swing.JButton reset;
    private javax.swing.JTextField searchOwnerName;
    private javax.swing.JButton searchdate;
    private javax.swing.JTextField text;
    private javax.swing.JTextField textId;
    // End of variables declaration//GEN-END:variables
}
