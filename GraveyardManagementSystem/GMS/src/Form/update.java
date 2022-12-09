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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class update extends javax.swing.JFrame {
String gender;
    /**
     * Creates new form update
     */
    public update() {
        initComponents();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
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
    public void clearAllFields(){
           plotSize.setSelectedIndex(0);
           corpseType.setSelectedIndex(0);
           //buttonGroup1.clearSelection();
           male.setSelected(false);
           female.setSelected(false);
           other.setSelected(false);
           plotNo.setText("");
           timeOfDeath.setText("");
           name.setText("");
           fathersName.setText("");
           mothersName.setText("");
           causeOfDeath.setText("");
           nid.setText("");
           birthCertificateNo.setText("");
           GuardianName.setText("");
           relation.setText("");
           guardianContactNo.setText("");
           guardianAddress.setText("");
           JDateChooser dateChooser = new JDateChooser();
           burriedDate.setCalendar(null);
           recycleDate.setCalendar(null);
           dateOfBirth.setCalendar(null);
           dateOfDeath.setCalendar(null);
    }
  
    public void openDeletePanel()
    {
        updatePanel.setVisible(false);
        deletePanel.setVisible(true);
    }
    public void openUpdatePanel()
    {
         deletePanel.setVisible(false);
         updatePanel.setVisible(true);
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        timeSelector = new cambodia.raven.Time();
        mainPanel = new javax.swing.JPanel();
        upPanel = new javax.swing.JPanel();
        updatePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        corpseType = new javax.swing.JComboBox<>();
        plotSize = new javax.swing.JComboBox<>();
        plotNo = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        fathersName = new javax.swing.JTextField();
        mothersName = new javax.swing.JTextField();
        causeOfDeath = new javax.swing.JTextField();
        nid = new javax.swing.JTextField();
        guardianAddress = new javax.swing.JTextField();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        birthCertificateNo = new javax.swing.JTextField();
        timeOfDeath = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        relation = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        GuardianName = new javax.swing.JTextField();
        guardianContactNo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        burriedDate = new com.toedter.calendar.JDateChooser();
        recycleDate = new com.toedter.calendar.JDateChooser();
        dateOfDeath = new com.toedter.calendar.JDateChooser();
        dateOfBirth = new com.toedter.calendar.JDateChooser();
        deletePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        signinBackLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        timeSelector.setTextRefernce(timeOfDeath);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        upPanel.setLayout(new java.awt.CardLayout());

        updatePanel.setBackground(new java.awt.Color(255, 255, 255));
        updatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Plot no:");
        updatePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel4.setText("Name:");
        updatePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        jLabel5.setText("Gender:");
        updatePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 60, 20));

        jLabel6.setText("Father's name:");
        updatePanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

        jLabel7.setText("Mother's name:");
        updatePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, -1));

        jLabel8.setText("Date of birth:");
        updatePanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, -1, -1));

        jLabel13.setText("Corpse type:");
        updatePanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel9.setText("Date of death:");
        updatePanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, -1, -1));

        jLabel10.setText("Time of death:");
        updatePanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 200, -1, -1));

        jLabel11.setText("Cause of death:");
        updatePanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, -1, -1));

        jLabel12.setText("Plot size:");
        updatePanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 20));

        jLabel14.setText("NID:");
        updatePanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 80, -1));

        jLabel15.setText("Address:");
        updatePanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 160, -1, -1));

        jLabel16.setText("Burried date:");
        updatePanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 70, 20));

        jLabel17.setText("Recycle date:");
        updatePanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 80, 20));

        update.setText("update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        updatePanel.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

        corpseType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Identified", "Unidentified" }));
        corpseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corpseTypeActionPerformed(evt);
            }
        });
        updatePanel.add(corpseType, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 110, -1));

        plotSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Small", "Medium", "Large" }));
        updatePanel.add(plotSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 110, -1));
        updatePanel.add(plotNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 110, 20));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        updatePanel.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 240, -1));
        updatePanel.add(fathersName, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 240, -1));
        updatePanel.add(mothersName, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 240, -1));
        updatePanel.add(causeOfDeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 150, -1));
        updatePanel.add(nid, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 230, 150, -1));
        updatePanel.add(guardianAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 160, 160, -1));

        male.setText("Male");
        updatePanel.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 70, -1));

        female.setText("Female");
        updatePanel.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 70, -1));

        other.setText("Other");
        updatePanel.add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 60, -1));

        jLabel18.setText("Birth Certificate no:");
        updatePanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, -1, -1));
        updatePanel.add(birthCertificateNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 240, -1));
        updatePanel.add(timeOfDeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 190, 130, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        updatePanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 190, 20, -1));

        jLabel27.setText("Relation:");
        updatePanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 120, -1, -1));
        updatePanel.add(relation, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 120, 160, -1));

        jLabel28.setText(" guardian name");
        updatePanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 80, -1, -1));

        jLabel29.setText("Contact no:");
        updatePanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 200, -1, -1));
        updatePanel.add(GuardianName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 80, 160, -1));
        updatePanel.add(guardianContactNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 200, 160, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("SELECT A ROW FROM THE TABLE BELOW TO UPDATE");
        updatePanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 500, 30));
        updatePanel.add(burriedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 110, -1));
        updatePanel.add(recycleDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 110, -1));
        updatePanel.add(dateOfDeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 150, -1));
        updatePanel.add(dateOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, 150, -1));

        upPanel.add(updatePanel, "card2");

        deletePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("SELECT A ROW FROM THE TABLE TO DELETE");
        deletePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 320, 40));

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        deletePanel.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        signinBackLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        signinBackLabel.setForeground(new java.awt.Color(0, 51, 51));
        signinBackLabel.setText("<<");
        signinBackLabel.setToolTipText("<<");
        signinBackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signinBackLabelMouseClicked(evt);
            }
        });
        deletePanel.add(signinBackLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 4, 40, -1));

        upPanel.add(deletePanel, "card3");

        mainPanel.add(upPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 330));

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
                "Grave ID", "Size", "Corpse ID", "Type", "Name", "Gender", "Father's name", "Mother's name", "Date of birth", "Date of death", "Time of death", "Cause of death", "Guardian's name", "Relation", "Address", "Contact no.", "NID no", "Birth Cert. No.", "Burried date", "Recycle date"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        mainPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1310, 300));

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        mainPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 680, -1, -1));

        getContentPane().add(mainPanel);
        mainPanel.setBounds(0, 0, 1360, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
       
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        clearAllFields();
        int i = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        int assign = -1;
        String type = model.getValueAt(i, 3).toString();
        switch(type){
            case "Identified":
                assign = 1;
                break;
            case "Unidentified":
               assign = 0;
                break;
        }
        
        if(assign==1)
        { 
        plotNo.setText(model.getValueAt(i, 0).toString());
        String size = model.getValueAt(i, 1).toString();
        switch(size){
            case "Small":
                plotSize.setSelectedIndex(0);
                break;
            case "Medium":
                plotSize.setSelectedIndex(1);
                break;
            case "Large":
                plotSize.setSelectedIndex(2);
                break;
        }
       
        corpseType.setSelectedIndex(0);
        name.setText(model.getValueAt(i, 4).toString());
        String sex = model.getValueAt(i, 5).toString();
        if(sex.equals("male"))
        {
            male.setSelected(true);
        }
        else if(sex.equals("female"))
        {
            female.setSelected(true);
        }
        else if(sex.equals("other"))
        {
            other.setSelected(true);
        }
        fathersName.setText(model.getValueAt(i, 6).toString());
        mothersName.setText(model.getValueAt(i, 7).toString());
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,8));
            dateOfBirth.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,9));
            dateOfDeath.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       
        timeOfDeath.setText(model.getValueAt(i, 10).toString());
        causeOfDeath.setText(model.getValueAt(i, 11).toString());
        GuardianName.setText(model.getValueAt(i, 12).toString());
        relation.setText(model.getValueAt(i, 13).toString());
        guardianAddress.setText(model.getValueAt(i, 14).toString());
        guardianContactNo.setText(model.getValueAt(i, 15).toString());
        nid.setText(model.getValueAt(i, 16).toString());
        birthCertificateNo.setText(model.getValueAt(i, 17).toString());
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,18));
            burriedDate.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,19));
            recycleDate.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       
    } 
        else if (assign == 0){
            plotNo.setText(model.getValueAt(i, 0).toString());
        String size = model.getValueAt(i, 1).toString();
        switch(size){
            case "Small":
                plotSize.setSelectedIndex(0);
                break;
            case "Medium":
                plotSize.setSelectedIndex(1);
                break;
            case "Large":
                plotSize.setSelectedIndex(2);
                break;
        }
       
        corpseType.setSelectedIndex(1);
        name.setText("null");
        String sex = model.getValueAt(i, 5).toString();
        if(sex.equals("male"))
        {
            male.setSelected(true);
        }
        else if(sex.equals("female"))
        {
            female.setSelected(true);
        }
        else if(sex.equals("other"))
        {
            other.setSelected(true);
        }
        fathersName.setText("null");
        mothersName.setText("null");
        try{
            //Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,8));
            dateOfBirth.setCalendar(null);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            //Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,9));
            dateOfDeath.setCalendar(null);;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       
        timeOfDeath.setText("null");
        causeOfDeath.setText("null");
        GuardianName.setText("null");
        relation.setText("null");
        guardianAddress.setText("null");
        guardianContactNo.setText("null");
        nid.setText("null");
        birthCertificateNo.setText("null");
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,18));
            burriedDate.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,19));
            recycleDate.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       
            
        }
       
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int opt = JOptionPane.showConfirmDialog(null,"Are you sure to Delete","Delete",JOptionPane.YES_NO_OPTION);
        if(opt==0){
        try {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(upPanel, "Database not connected successfully");
            }
            Connection connection = DriverManager .getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");
            int row = jTable1.getSelectedRow();
            String value = (jTable1.getModel().getValueAt(row,2).toString());
            String sql = "DELETE FROM GeneralGrave1 where generalCorpseId="+value;

            PreparedStatement pst = connection.prepareStatement(sql);
            
            pst.executeUpdate();

            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            
            model.setRowCount(0);
            show_table();
            JOptionPane.showMessageDialog(upPanel,"Deleted successfully");

            connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(upPanel, "Failed to delete the selected row");
           // Logger.getLogger(update.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(upPanel,"Failed to delete the selected row");
            //Logger.getLogger(update.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }//GEN-LAST:event_deleteActionPerformed

    private void signinBackLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signinBackLabelMouseClicked
        this.setVisible(false);
      MainMenu mm = new MainMenu();
      mm.setVisible(true);
      mm.openGravePanel();
      mm.openmenuPanel();
    }//GEN-LAST:event_signinBackLabelMouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

        try {
            System.out.println("hi");

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(upPanel, "Database not connected successfully");
            }
            Connection connection = DriverManager .getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");
            int row = jTable1.getSelectedRow();
            String value = (jTable1.getModel().getValueAt(row,2).toString());
            String sql = "UPDATE GeneralGrave1 SET corpseType=?,generalGraveId=?,generalGraveSize=?,corpseGender=?,burriedDate=?,"
            +"RecycleDate=?,corpseTimeOfDeath=?,corpseName=?,corpseFatherName=?,corpseMotherName=?,corpseDateOfBirth=?,corpseDateOfDeath=?,"
            +"corpseCauseOfDeath=?,corpseNidNo=?,corpseBirthCertificateNo=?,guardianName=?,guardianRelation=?,guardianContactNo=?"
            + ",guardianAddress=? where generalCorpseId="+value;

            String sql1 = "UPDATE GeneralGrave1 SET corpseType=?,generalGraveId=?,generalGraveSize=?,corpseGender=?,burriedDate=?,"
            +"RecycleDate=? where generalCorpseId="+value;

            PreparedStatement pst = connection.prepareStatement(sql);
            PreparedStatement pst1 = connection.prepareStatement(sql1);

            String type;
            type = corpseType.getSelectedItem().toString();

            if(type=="Unidentified"){
                pst1.setString(1,type);

                pst1.setString(2,plotNo.getText());

                String size;
                size = plotSize.getSelectedItem().toString();
                pst1.setString(3,size);
                if(male.isSelected()){
                    gender="male";
                }
                if(female.isSelected()){
                    gender="female";
                }
                if(other.isSelected()){
                    gender="other";
                }
                pst1.setString(4,gender);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String bdate = sdf.format(burriedDate.getDate());
                pst1.setString(5,bdate);
                String rdate = sdf.format(recycleDate.getDate());
                pst1.setString(6,rdate);
                pst1.executeUpdate();
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();

                model.setRowCount(0);
                show_table();
                JOptionPane.showMessageDialog(upPanel,"Updated successfully");

                connection.close();
            }
            else if(type=="Identified"){
                pst.setString(1,type);

                pst.setString(2,plotNo.getText());

                String size;
                size = plotSize.getSelectedItem().toString();
                pst.setString(3,size);
                if(male.isSelected()){
                    gender="male";
                }
                if(female.isSelected()){
                    gender="female";
                }
                if(other.isSelected()){
                    gender="other";
                }
                pst.setString(4,gender);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String bdate = sdf.format(burriedDate.getDate());
                pst.setString(5,bdate);
                String rdate = sdf.format(recycleDate.getDate());
                pst.setString(6,rdate);
                pst.setString(7,timeOfDeath.getText());
                pst.setString(8,name.getText());
                pst.setString(9,fathersName.getText());
                pst.setString(10,mothersName.getText());
                String birthdate = sdf.format(dateOfBirth.getDate());
                pst.setString(11,birthdate);
                String deathdate = sdf.format(dateOfDeath.getDate());
                pst.setString(12,deathdate);
                pst.setString(13,causeOfDeath.getText());
                pst.setString(14,nid.getText());
                pst.setString(15,birthCertificateNo.getText());
                pst.setString(16,GuardianName.getText());
                pst.setString(17,relation.getText());
                pst.setString(18,guardianContactNo.getText());
                pst.setString(19,guardianAddress.getText());
                pst.executeUpdate();

                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();

                model.setRowCount(0);
                show_table();
                JOptionPane.showMessageDialog(upPanel,"Updated successfully");

                connection.close();
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(upPanel, "Failed to update the selected row");
            // Logger.getLogger(update.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(upPanel,"Failed to update the selected row");
            //Logger.getLogger(update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateActionPerformed

    private void corpseTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corpseTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_corpseTypeActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        timeSelector.showPopup();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           this.setVisible(false);
      MainMenu mm = new MainMenu();
      mm.setVisible(true);
      mm.openGravePanel();
      mm.openmenuPanel();
    }//GEN-LAST:event_jButton2ActionPerformed

 
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
            java.util.logging.Logger.getLogger(update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField GuardianName;
    private javax.swing.JTextField birthCertificateNo;
    private com.toedter.calendar.JDateChooser burriedDate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField causeOfDeath;
    private javax.swing.JComboBox<String> corpseType;
    private com.toedter.calendar.JDateChooser dateOfBirth;
    private com.toedter.calendar.JDateChooser dateOfDeath;
    private javax.swing.JButton delete;
    private javax.swing.JPanel deletePanel;
    private javax.swing.JTextField fathersName;
    private javax.swing.JRadioButton female;
    private javax.swing.JTextField guardianAddress;
    private javax.swing.JTextField guardianContactNo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton male;
    private javax.swing.JTextField mothersName;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nid;
    private javax.swing.JRadioButton other;
    private javax.swing.JTextField plotNo;
    private javax.swing.JComboBox<String> plotSize;
    private com.toedter.calendar.JDateChooser recycleDate;
    private javax.swing.JTextField relation;
    private javax.swing.JLabel signinBackLabel;
    private javax.swing.JTextField timeOfDeath;
    private cambodia.raven.Time timeSelector;
    private javax.swing.JPanel upPanel;
    private javax.swing.JButton update;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables

    void SetVisible(boolean b) {
     new update().setVisible(true);   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
