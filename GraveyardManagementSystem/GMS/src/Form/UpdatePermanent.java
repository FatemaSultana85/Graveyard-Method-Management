
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


public class UpdatePermanent extends javax.swing.JFrame {

    String gender;
 
    public UpdatePermanent() {
        initComponents();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       // setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
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
        update = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        ownerName = new javax.swing.JTextField();
        ownerPhoneNo = new javax.swing.JTextField();
        ownerId = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        ownerAddress = new javax.swing.JTextField();
        ownerNid = new javax.swing.JTextField();
        motherName = new javax.swing.JTextField();
        plotNo = new javax.swing.JTextField();
        fee = new javax.swing.JTextField();
        availability = new javax.swing.JTextField();
        sellingStatus = new javax.swing.JTextField();
        fatherName = new javax.swing.JTextField();
        CorpseNid = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        burriedDate = new com.toedter.calendar.JDateChooser();
        dateOfBirth = new com.toedter.calendar.JDateChooser();
        dateOfDeath = new com.toedter.calendar.JDateChooser();
        causeofdeath = new javax.swing.JComboBox<>();
        recycleDate = new com.toedter.calendar.JDateChooser();
        timeOfDeath = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        relationWithOwner = new javax.swing.JComboBox<>();
        plotSize = new javax.swing.JComboBox<>();
        female = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        male = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        deletePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        signinBackLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        timeSelector.setBackground(new java.awt.Color(255, 255, 255));
        timeSelector.setTextRefernce(timeOfDeath);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        upPanel.setLayout(new java.awt.CardLayout());

        updatePanel.setBackground(new java.awt.Color(255, 255, 255));
        updatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        update.setText("update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        updatePanel.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, -1));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setText("Owner Id:");
        updatePanel.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel63.setText("Owner Name:");
        updatePanel.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 10));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel64.setText("Owner Contact No:");
        updatePanel.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText("Owner NID/Birth Certificate:");
        updatePanel.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel66.setText("Owner Address:");
        updatePanel.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        ownerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ownerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(ownerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 120, -1));

        ownerPhoneNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ownerPhoneNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(ownerPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 120, -1));

        ownerId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ownerId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(ownerId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 120, -1));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel67.setText("Selling Status:");
        updatePanel.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel68.setText("Availability:");
        updatePanel.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel70.setText("Fee:");
        updatePanel.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel71.setText("Plot No:");
        updatePanel.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel72.setText("Plot Size:");
        updatePanel.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, -1));

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel73.setText("Relation With Owner:");
        updatePanel.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setText("Corpse Name:");
        updatePanel.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel75.setText("Corpse Gender:");
        updatePanel.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, -1, -1));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel76.setText("Corpse Father Name:");
        updatePanel.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel77.setText("Corpse Mother Name:");
        updatePanel.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, -1, -1));

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel78.setText("Date of Birth:");
        updatePanel.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, -1, -1));

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel79.setText("Date of Death:");
        updatePanel.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, -1, -1));

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel80.setText("Time of Death:");
        updatePanel.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, -1, -1));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText("Cause of Death:");
        updatePanel.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, -1, -1));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel82.setText("Corpse NID/Birth Certificate:");
        updatePanel.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 80, -1, -1));

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel83.setText("Burried Date:");
        updatePanel.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, -1, -1));

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel84.setText("Recycle Date:");
        updatePanel.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 140, -1, -1));

        ownerAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ownerAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(ownerAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 120, -1));

        ownerNid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ownerNid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(ownerNid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 120, -1));

        motherName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        motherName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(motherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 120, -1));

        plotNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        plotNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(plotNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 120, -1));

        fee.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(fee, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 120, -1));

        availability.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        availability.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(availability, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 120, -1));

        sellingStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sellingStatus.setText("Sold");
        sellingStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(sellingStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 120, -1));

        fatherName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fatherName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(fatherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 120, -1));

        CorpseNid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CorpseNid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(CorpseNid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 80, 120, -1));

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatePanel.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 120, -1));
        updatePanel.add(burriedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, 120, -1));
        updatePanel.add(dateOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, 120, -1));
        updatePanel.add(dateOfDeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 120, -1));

        causeofdeath.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Natural", "Accidental", "Disease", "Homicide", "Suicide", "Pandemic" }));
        updatePanel.add(causeofdeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 50, 120, 20));
        updatePanel.add(recycleDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 140, 120, -1));

        timeOfDeath.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        timeOfDeath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeOfDeathActionPerformed(evt);
            }
        });
        updatePanel.add(timeOfDeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, 80, 20));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        updatePanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, 30, 20));

        relationWithOwner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Father", "Mother", "Brother", "Sister", "Husband", "Wife", "Daughter", "Son" }));
        updatePanel.add(relationWithOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 120, 20));

        plotSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Small", "Medium", "Large" }));
        updatePanel.add(plotSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 120, 20));

        female.setText("Female");
        updatePanel.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 170, -1, -1));

        other.setText("other");
        updatePanel.add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 170, -1, -1));

        male.setText("Male");
        updatePanel.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("SELECT A ROW FROM THE TABLE BELOW TO UPDATE");
        updatePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        upPanel.add(updatePanel, "card2");

        deletePanel.setBackground(new java.awt.Color(255, 255, 255));
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
        deletePanel.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, -1, -1));

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("SELECT A ROW FROM THE TABLE TO DELETE");
        deletePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, -1, -1));

        upPanel.add(deletePanel, "card3");

        mainPanel.add(upPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -40, 1360, 250));

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
        jTable1.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(10).setResizable(false);
        }

        mainPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1340, 370));

        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        mainPanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 610, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            String value = (jTable1.getModel().getValueAt(row,0).toString());
            String corpseId = (jTable1.getModel().getValueAt(row,8).toString());
            String sql = "BEGIN TRANSACTION\n" +
                            "update A\n" +
                            "set A.ownerId =?,\n" +
                            "A.ownerName=?,\n" +
                            "A.ownerPhoneNumber=?,\n" +
                            "A.ownerNidOrBirthCertificate=?,\n" +
                            "A.ownerAddress=?,\n" +
                            "A.SellingStatus=?,\n" +
                            "A.availablility=?\n" +
                            "from OwnerInfo A inner join paymentInfo B\n" +
                            "on B.serialNo = A.serialNo\n" +
                            "and A.serialNo = "+value+"\n" +
                            "update B\n" +
                            "set B.fee =?\n" +
                            "from paymentInfo B inner join OwnerInfo A\n" +
                            " on B.serialNo = A.serialNo\n" +
                            "and A.serialNo = "+value+"\n" +
                            "update C\n" +
                            "set C.permanentGraveId =?,\n" +
                            "C.permanentGraveSize=?,\n" +
                            "C.RelationWithOwner=?,\n" +
                            "C.corpseName=?,\n" +
                            "C.corpseGender=?,\n" +
                            "C.corpseFatherName=?,\n" +
                            "C.corpseMotherName=?,\n" +
                            "C.corpseDateOfBirth=?,\n" +
                            "C.corpseDateOfDeath=?,\n" +
                            "C.corpseTimeOfDeath=?,\n" +
                            "C.corpseCauseOfDeath=?,\n" +
                            "C.corpseNidOrBirthCertificate=?,\n" +
                            "C.burriedDate=?,\n" +
                            "C.RecycleDate=?\n" +
                            "from Permanent_CorpseInfo C inner join OwnerInfo A\n" +
                            " on C.serialNo = A.serialNo\n" +
                            " and C.serialNo = "+value+"\n" +
                            "and C.permanentCorpseId="+corpseId+"\n" +
                            "COMMIT";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,ownerId.getText());
            pst.setString(2,ownerName.getText());
            pst.setString(3,ownerPhoneNo.getText());
            pst.setString(4,ownerNid.getText());
            pst.setString(5,ownerAddress.getText());
            pst.setString(6,sellingStatus.getText());
            pst.setString(7,availability.getText());
            pst.setString(8,fee.getText());
            pst.setString(9,plotNo.getText());
            String size;
            size = plotSize.getSelectedItem().toString();
            pst.setString(10,size);
            
            String relation;
            relation = relationWithOwner.getSelectedItem().toString();
            pst.setString(11,relation);            
            pst.setString(12,name.getText());
            if(male.isSelected()){
                gender="male";
            }
            if(female.isSelected()){
                gender="female";
            }
            if(other.isSelected()){
                gender="other";
            }
            pst.setString(13,gender);
            
            pst.setString(14,fatherName.getText());
            pst.setString(15,motherName.getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthdate = sdf.format(dateOfBirth.getDate());
            pst.setString(16,birthdate);
            String deathdate = sdf.format(dateOfDeath.getDate());
            pst.setString(17,deathdate);
            pst.setString(18,timeOfDeath.getText());
            
            String cause;
            cause = causeofdeath.getSelectedItem().toString();
            pst.setString(19,cause);
            
            pst.setString(20,CorpseNid.getText());
            String bdate = sdf.format(burriedDate.getDate());
            pst.setString(21,bdate);
            String rdate = sdf.format(recycleDate.getDate());
            pst.setString(22,rdate);
            pst.executeUpdate();
            //updating permanent_corpseInfo(ownerId and availability
             String sql2="UPDATE t2 SET t2.ownerId = t1.ownerId, t2.availablility = t1.availablility FROM Permanent_CorpseInfo  t2 INNER JOIN OwnerInfo t1 ON t2.serialNo=t1.serialNo";
                 PreparedStatement pst2 = connection.prepareStatement(sql2);
                 pst2.executeUpdate(); 
                 
            //updating OwnerInfo(permanentGraveId)
             String sql3="UPDATE t2 SET t2.generalGraveId = t1.permanentGraveId FROM OwnerInfo t2 INNER JOIN  Permanent_CorpseInfo  t1 ON t2.serialNo=t1.serialNo ";
             PreparedStatement pst3 = connection.prepareStatement(sql3);
             pst3.executeUpdate(); 
             
             //updating paymentInfo(owneid and permanentgraveId)
             String sql4=" UPDATE t2 SET t2.ownerId = t1.ownerId,t2.generalGraveId = t1.generalGraveId FROM paymentInfo t2 INNER JOIN OwnerInfo t1 ON t2.serialNo=t1.serialNo ";
             PreparedStatement pst4 = connection.prepareStatement(sql4);
             pst4.executeUpdate(); 
            
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();

            model.setRowCount(0);
            show_table();
            JOptionPane.showMessageDialog(upPanel,"Updated successfully");

            connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(upPanel, "Failed to update the selected row");
            // Logger.getLogger(update.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(upPanel,ex);
            //Logger.getLogger(update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateActionPerformed

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
                String purchase = (jTable1.getModel().getValueAt(row,0).toString());
                String corpseId=(jTable1.getModel().getValueAt(row,8).toString());
                String sql = "BEGIN TRANSACTION DELETE FROM Permanent_CorpseInfo WHERE   serialNo = "+purchase+" AND permanentCorpseId="+corpseId+"; DELETE FROM    paymentInfo WHERE   serialNo ="+purchase+" ; DELETE FROM    OwnerInfo WHERE   serialNo ="+purchase+"; COMMIT;";

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
    }//GEN-LAST:event_signinBackLabelMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
     try {
       ownerId.setText("");
       ownerName.setText("");
       ownerPhoneNo.setText("");
       ownerNid.setText("");
       ownerAddress.setText("");
       sellingStatus.setText("");
       availability.setText("");
       plotNo.setText("");
       //String size=null;
       //String relation=null;
       name.setText("");
       
        male.setSelected(false);
        female.setSelected(false);
       other.setSelected(false);
       
       fatherName.setText("");
        motherName.setText("");
     
        //dateOfBirth.setCalendar(null);
        //dateOfDeath.setCalendar(null);
        timeOfDeath.setText("");
        //String cause =null;
        CorpseNid.setText("");
        //burriedDate.setCalendar(null);
        //recycleDate.setCalendar(null);
        fee.setText("");
       
      
       
        int i = jTable1.getSelectedRow();
        
        TableModel model = jTable1.getModel();
        
        ownerId.setText(model.getValueAt(i, 1).toString());
        ownerName.setText(model.getValueAt(i, 2).toString());
        ownerPhoneNo.setText(model.getValueAt(i, 3).toString());
        ownerNid.setText(model.getValueAt(i, 4).toString());
        ownerAddress.setText(model.getValueAt(i, 5).toString());
        sellingStatus.setText(model.getValueAt(i, 6).toString());
        availability.setText(model.getValueAt(i, 7).toString());
        
        plotNo.setText(model.getValueAt(i, 9).toString());
       
        String size = model.getValueAt(i, 10).toString();
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
       String relation = model.getValueAt(i, 11).toString();
        switch(relation){
            case "Father":
            relationWithOwner.setSelectedIndex(0);
            break;
            case "Mother":
            relationWithOwner.setSelectedIndex(1);
            break;
            case "Brother":
            relationWithOwner.setSelectedIndex(2);
            break;
            case "Sister":
            relationWithOwner.setSelectedIndex(3);
            break;
            case "Husband":
            relationWithOwner.setSelectedIndex(4);
            break;
            case "Wife":
            relationWithOwner.setSelectedIndex(5);
            break;
            case "Daughter":
            relationWithOwner.setSelectedIndex(6);
            break;
            case "Son":
            relationWithOwner.setSelectedIndex(7);
            break;
        }
        
        name.setText(model.getValueAt(i, 12 ).toString());
        String sex = model.getValueAt(i, 13 ).toString();
        if(sex.equals("male"))
        {
            male.setSelected(true);
        }
        else if(sex.equals("female"))
        {
            female.setSelected(true);
        }
        else if(other.equals("other"))
        {
            other.setSelected(true);
        }
        
        fatherName.setText(model.getValueAt(i, 14).toString());
        motherName.setText(model.getValueAt(i, 15).toString());
       
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,16));
            dateOfBirth.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,17));
            dateOfDeath.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        timeOfDeath.setText(model.getValueAt(i, 18).toString());
        String cause = model.getValueAt(i, 19).toString();
        switch(cause){
            case "Natural":
            causeofdeath.setSelectedIndex(0);
            break;
            case "Accidental":
            causeofdeath.setSelectedIndex(1);
            break;
            case "Disease":
            causeofdeath.setSelectedIndex(2);
            break;
            case "Homicide":
            causeofdeath.setSelectedIndex(3);
            break;
            case "Suicide":
            causeofdeath.setSelectedIndex(4);
            break;
            case "Pandemic":
            causeofdeath.setSelectedIndex(5);
            break;
        }
        CorpseNid.setText(model.getValueAt(i, 20).toString());
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,21));
            burriedDate.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,22));
            recycleDate.setDate(date);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        fee.setText(model.getValueAt(i, 23).toString());
         
        
} catch (Exception ignore) { }
       

    }//GEN-LAST:event_jTable1MouseClicked

    
    
    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void timeOfDeathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeOfDeathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeOfDeathActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        timeSelector.showPopup();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      this.setVisible(false);
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        mm.openGravePanel();
        mm.openmenuPanel();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(UpdatePermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdatePermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdatePermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdatePermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdatePermanent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CorpseNid;
    private javax.swing.JTextField availability;
    private com.toedter.calendar.JDateChooser burriedDate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> causeofdeath;
    private com.toedter.calendar.JDateChooser dateOfBirth;
    private com.toedter.calendar.JDateChooser dateOfDeath;
    private javax.swing.JButton delete;
    private javax.swing.JPanel deletePanel;
    private javax.swing.JTextField fatherName;
    private javax.swing.JTextField fee;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton male;
    private javax.swing.JTextField motherName;
    private javax.swing.JTextField name;
    private javax.swing.JRadioButton other;
    private javax.swing.JTextField ownerAddress;
    private javax.swing.JTextField ownerId;
    private javax.swing.JTextField ownerName;
    private javax.swing.JTextField ownerNid;
    private javax.swing.JTextField ownerPhoneNo;
    private javax.swing.JTextField plotNo;
    private javax.swing.JComboBox<String> plotSize;
    private com.toedter.calendar.JDateChooser recycleDate;
    private javax.swing.JComboBox<String> relationWithOwner;
    private javax.swing.JTextField sellingStatus;
    private javax.swing.JLabel signinBackLabel;
    private javax.swing.JTextField timeOfDeath;
    private cambodia.raven.Time timeSelector;
    private javax.swing.JPanel upPanel;
    private javax.swing.JButton update;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables
}
