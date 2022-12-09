
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.util.Date;
import javax.swing.table.TableColumnModel;

public class Permanent_CorpseInfo extends javax.swing.JFrame {
String gender;
    
  public void clearAllFields(){
           plotSize2.setSelectedIndex(0);
          
           //buttonGroup1.clearSelection();
           male.setSelected(false);
           female.setSelected(false);
           other.setSelected(false);
           
           timeOfDeath.setText("");
           name.setText("");
           fatherName.setText("");
           motherName.setText("");
           causeOfDeath.setSelectedIndex(0);
           
           BirthCertificateOrNid.setText("");
           ownerId.setText("");
           relationWithOwner.setSelectedIndex(0);
           purchaseId.setText("");
         
           JDateChooser dateChooser = new JDateChooser();
           burriedDate.setCalendar(null);
           recycleDate.setCalendar(null);
           dateOfBirth.setCalendar(null);
           dateOfDeath.setCalendar(null);
    }
    public Permanent_CorpseInfo() {
       initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       informationOfGrave.getTableHeader().setBackground(new Color(32,134,203));
       informationOfGrave.getTableHeader().setForeground(new Color(255,255,255));
      informationOfGrave.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
       informationOfGrave.getTableHeader().setOpaque(false);
       informationOfGrave.setRowHeight(25);
        
       informationOfGrave.setAutoResizeMode(informationOfGrave.AUTO_RESIZE_OFF);
        TableColumnModel col = informationOfGrave.getColumnModel();
        for(int i=0;i<6;i++)
        {
            col.getColumn(i).setPreferredWidth(150);
        }
        informationOfCorpse.getTableHeader().setBackground(new Color(32,134,203));
        informationOfCorpse.getTableHeader().setForeground(new Color(255,255,255));
     informationOfCorpse.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        informationOfCorpse.getTableHeader().setOpaque(false);
      informationOfCorpse.setRowHeight(25);
        
       informationOfCorpse.setAutoResizeMode(informationOfCorpse.AUTO_RESIZE_OFF);
        TableColumnModel col1 = informationOfCorpse.getColumnModel();
        for(int i=0;i<11;i++)
        {
            col1.getColumn(i).setPreferredWidth(150);
        }
        deathRelatedInformation.getTableHeader().setBackground(new Color(32,134,203));
        deathRelatedInformation.getTableHeader().setForeground(new Color(255,255,255));
      deathRelatedInformation.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
       deathRelatedInformation.getTableHeader().setOpaque(false);
       deathRelatedInformation.setRowHeight(25);
        
       //deathRelatedInformation.setAutoResizeMode(deathRelatedInformation.AUTO_RESIZE_OFF);
        TableColumnModel col2 = deathRelatedInformation.getColumnModel();
        for(int i=0;i<5;i++)
        {
            col2.getColumn(i).setPreferredWidth(150);
        }
        //setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
        show_corpse();
    }
    public ArrayList<permanent>permanentCorpseList(){
        ArrayList<permanent> permanentCorpseList = new ArrayList<>();
     try {

     try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     } 
     catch (ClassNotFoundException ex) 
     {
         JOptionPane.showMessageDialog(left, "Database not connected successfully");
     }
            Connection connection = DriverManager .getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");
         
            String query1 = "SELECT * FROM Permanent_CorpseInfo";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            permanent per;
            while(rs.next())
            {
              per = new permanent(rs.getInt("permanentGraveId"),rs.getString("permanentGraveSize"),
                      rs.getInt("serialNo"),rs.getInt("ownerId"),rs.getString("RelationWithOwner"),
                      rs.getString("availablility"),rs.getInt("permanentCorpseId"),
                      rs.getString("corpseName"),rs.getString("corpseGender"),
                      rs.getString("corpseFatherName"),rs.getString("corpseMotherName"),
                      rs.getString("corpseDateOfBirth"),
                      rs.getString("corpseDateOfDeath"),rs.getString("corpseTimeOfDeath"),
                      rs.getString("corpseCauseOfDeath"),rs.getString("corpseNidOrBirthCertificate"),rs.getString("burriedDate"),
                      rs.getString("RecycleDate"));
                      
              permanentCorpseList.add(per);
            }
            //connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(left, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(left,ex);
        }
     return permanentCorpseList;
           
    }
 public void show_corpse(){
    ArrayList<permanent> list = permanentCorpseList();
    DefaultTableModel model1 = (DefaultTableModel)informationOfGrave.getModel();
    Object[] row1 = new Object[6];
    for(int i = 0; i<list.size(); i++)
    {
        row1[0] = list.get(i).getpermanentGraveId();
        row1[1] = list.get(i).getpermanentGraveSize();
        row1[2] = list.get(i).getpermanentCorpseId();
        row1[3] = list.get(i).getburriedDate();
        row1[4] = list.get(i).getRecycleDate();
        row1[5] = list.get(i).getavailablility();
   
        model1.addRow(row1);
    }
    DefaultTableModel model2 = (DefaultTableModel)informationOfCorpse.getModel();
    Object[] row2 = new Object[11];
    for(int i = 0; i<list.size(); i++)
    {
       
        row2[0] = list.get(i).getpermanentCorpseId();
        row2[1] = list.get(i).getpermanentGraveId();
        row2[2] = list.get(i).getserialNo();
        row2[3] = list.get(i).getownerId();
        row2[4] = list.get(i).getcorpseName();
        row2[5] = list.get(i).getcorpseFatherName();
        row2[6] = list.get(i).getcorpseMotherName();
        row2[7] = list.get(i).getcorpseDateOfBirth();
        row2[8] = list.get(i).getRelationWithOwner();
        row2[9] = list.get(i).getcorpseNidOrBirthCertificate();
        row2[10] = list.get(i).getcorpseGender();
         
        model2.addRow(row2);
    }
    DefaultTableModel model3 = (DefaultTableModel)deathRelatedInformation.getModel();
    Object[] row3 = new Object[5];
    for(int i = 0; i<list.size(); i++)
    {
        row3[0] = list.get(i).getpermanentCorpseId();
        row3[1] = list.get(i).getpermanentGraveId();
        row3[2] = list.get(i).getcorpseDateOfDeath();
        row3[3] = list.get(i).getcorpseCauseOfDeath();
        row3[4] = list.get(i).getcorpseTimeOfDeath();
        model3.addRow(row3);
       
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timeSelector = new cambodia.raven.Time();
        buttonGroup1 = new javax.swing.ButtonGroup();
        left = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        motherName = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        purchaseId = new javax.swing.JTextField();
        ownerId = new javax.swing.JTextField();
        BirthCertificateOrNid = new javax.swing.JTextField();
        fatherName = new javax.swing.JTextField();
        burriedDate = new com.toedter.calendar.JDateChooser();
        recycleDate = new com.toedter.calendar.JDateChooser();
        plotSize2 = new javax.swing.JComboBox<>();
        dateOfBirth = new com.toedter.calendar.JDateChooser();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        other = new javax.swing.JRadioButton();
        dateOfDeath = new com.toedter.calendar.JDateChooser();
        jLabel86 = new javax.swing.JLabel();
        availability = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        informationOfGrave = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        informationOfCorpse = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        deathRelatedInformation = new javax.swing.JTable();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        timeOfDeath = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        back = new javax.swing.JButton();
        causeOfDeath = new javax.swing.JComboBox<>();
        relationWithOwner = new javax.swing.JComboBox<>();
        plotId = new javax.swing.JComboBox<>();

        timeSelector.setBackground(new java.awt.Color(255, 255, 255));
        timeSelector.setTextRefernce(timeOfDeath);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        left.setBackground(new java.awt.Color(255, 255, 255));
        left.setForeground(new java.awt.Color(255, 255, 255));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel61.setText("Plot No:");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel62.setText("Permanent Grave Corpse Info");

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel63.setText("Plot Size:");

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel64.setText("Time of Death: ");

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel65.setText("Cause of Death:");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel66.setText("Name:");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel67.setText("Gender:");

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel68.setText("Father Name:");

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel69.setText("Mother Name:");

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel70.setText("Date of Birth:");

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel71.setText("Date of Death:");

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel73.setText("Burried Date: ");

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel74.setText("Birth Certificate/NID:");

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel75.setText("Recycle Date:");

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel76.setText("Owner Id:");

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel77.setText("Purchase Id:");

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel81.setText("Relation With Owner:");

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel82.setText("Grave Information");

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel83.setText("Information of Corpse");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel84.setText("Death Related Information");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel85.setText("Owner Information");

        motherName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        purchaseId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        purchaseId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseIdActionPerformed(evt);
            }
        });

        ownerId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        ownerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerIdActionPerformed(evt);
            }
        });

        BirthCertificateOrNid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        fatherName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        burriedDate.setForeground(new java.awt.Color(255, 255, 255));

        plotSize2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Small", "Medium", "Large" }));

        male.setText("Male");

        female.setText("Female");

        other.setText("Other");

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel86.setText("Availability:");

        availability.setText("Occupied");
        availability.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel87.setText("Information of Grave ");

        informationOfGrave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        informationOfGrave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plot  no", "Plot Size", "Corpse Id", "Burried Date", "Recycle Date", "Availabillity"
            }
        ));
        informationOfGrave.setFocusable(false);
        informationOfGrave.setGridColor(new java.awt.Color(255, 255, 255));
        informationOfGrave.setIntercellSpacing(new java.awt.Dimension(0, 0));
        informationOfGrave.setRowHeight(20);
        informationOfGrave.setSelectionBackground(new java.awt.Color(102, 102, 255));
        informationOfGrave.setShowVerticalLines(false);
        jScrollPane9.setViewportView(informationOfGrave);

        informationOfCorpse.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        informationOfCorpse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Corpse Id", "Plot  No", "Purchase Id", "Owner Id", "Name", "Father Name", "Mother Name", "Date of Birth", "Relation with Owner", "Birth Certificate/NID", "Gender"
            }
        ));
        informationOfCorpse.setFocusable(false);
        informationOfCorpse.setGridColor(new java.awt.Color(255, 255, 255));
        informationOfCorpse.setRowHeight(20);
        informationOfCorpse.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane10.setViewportView(informationOfCorpse);

        deathRelatedInformation.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deathRelatedInformation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Corpse Id", "Plot No", "Date of Death", "Cause of Death", "Time of Death"
            }
        ));
        deathRelatedInformation.setFocusable(false);
        deathRelatedInformation.setGridColor(new java.awt.Color(255, 255, 255));
        deathRelatedInformation.setRowHeight(20);
        deathRelatedInformation.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane11.setViewportView(deathRelatedInformation);

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel88.setText("Information of Corpse ");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel89.setText("Death related information ");

        add.setBackground(new java.awt.Color(255, 255, 255));
        add.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        timeOfDeath.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        timeOfDeath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeOfDeathActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(255, 255, 255));
        back.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        causeOfDeath.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Natural", "Accidental", "Disease", "Homicide", "Suicide", "Pandemic" }));

        relationWithOwner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Father", "Mother", "Brother", "Sister", "Husband", "Wife", "Daughter", "Son" }));

        plotId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(jLabel62))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82)
                            .addComponent(jLabel61))
                        .addGap(1, 1, 1)
                        .addComponent(plotId, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel63)
                        .addGap(72, 72, 72)
                        .addComponent(plotSize2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(310, 310, 310)
                        .addComponent(jLabel87))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(jLabel73)
                                .addGap(70, 70, 70)
                                .addComponent(burriedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel83)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addGap(112, 112, 112)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addGap(63, 63, 63)
                                .addComponent(fatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel75))
                            .addComponent(jLabel70)
                            .addComponent(jLabel74))
                        .addGap(4, 4, 4)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(recycleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BirthCertificateOrNid, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(jLabel69)
                                .addGap(58, 58, 58)
                                .addComponent(motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addGap(61, 61, 61)
                                .addComponent(male)
                                .addGap(35, 35, 35)
                                .addComponent(female)
                                .addGap(41, 41, 41)
                                .addComponent(other))
                            .addComponent(jLabel84)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addGap(66, 66, 66)
                                .addComponent(dateOfDeath, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel65)))
                        .addGap(39, 39, 39)
                        .addComponent(causeOfDeath, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(299, 299, 299)
                                .addComponent(jLabel88))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(jLabel64)
                        .addGap(40, 40, 40)
                        .addComponent(timeOfDeath, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288)
                        .addComponent(jLabel89))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel85))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(leftLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel76))
                                    .addComponent(jLabel81))
                                .addGap(10, 10, 10)
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ownerId, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(relationWithOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel86))
                                .addGap(59, 59, 59)
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(purchaseId, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(availability, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel62)
                .addGap(13, 13, 13)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel61))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(plotId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel63))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(plotSize2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel87)))
                .addGap(5, 5, 5)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(burriedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel83)
                                .addGap(18, 18, 18)
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(leftLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel66))
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(leftLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel68))
                                    .addComponent(fatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addGap(68, 68, 68)
                                .addComponent(jLabel70)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel74))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(recycleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(dateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(BirthCertificateOrNid, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(2, 2, 2)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69)
                            .addComponent(motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(male))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(female))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(other)))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel84)
                        .addGap(16, 16, 16)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(dateOfDeath, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel65))))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(causeOfDeath, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel64))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(timeOfDeath, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jButton1))
                            .addComponent(jLabel89))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel85)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel76)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel81))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(ownerId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(relationWithOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel77)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel86))
                            .addGroup(leftLayout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(purchaseId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(availability, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(back)
                            .addComponent(add))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        try {
            System.out.println("hi");

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(left, "Database not connected successfully");
            }
            Connection connection = DriverManager .getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");

            String sql = "INSERT INTO Permanent_CorpseInfo(permanentGraveId,permanentGraveSize,serialNo,"
                    + "ownerId,RelationWithOwner,availablility,corpseName,corpseGender,corpseFatherName,"
                    + "corpseMotherName,corpseDateOfBirth,corpseDateOfDeath,corpseTimeOfDeath,"
                    + "corpseCauseOfDeath,corpseNidOrBirthCertificate,burriedDate,RecycleDate)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            

            PreparedStatement pst = connection.prepareStatement(sql);
            
            String plot;
            plot = plotId.getSelectedItem().toString();
            pst.setString(1,plot);
            

            String size;
            size = plotSize2.getSelectedItem().toString();
            pst.setString(2,size);
             pst.setString(3,purchaseId.getText());
            pst.setString(4,ownerId.getText());
            
            String relation;
            relation = relationWithOwner.getSelectedItem().toString();
            pst.setString(5,relation);
            
             pst.setString(6,availability.getText());
             
             pst.setString(7,name.getText());
             if(male.isSelected()){
                gender="male";
            }
            if(female.isSelected()){
                gender="female";
            }
            if(other.isSelected()){
                gender="other";
            }
            pst.setString(8,gender);
            pst.setString(9,fatherName.getText());
            pst.setString(10,motherName.getText());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthdate = sdf.format(dateOfBirth.getDate());
            pst.setString(11,birthdate);
            String deathdate = sdf.format(dateOfDeath.getDate());
            pst.setString(12,deathdate);

            pst.setString(13,timeOfDeath.getText());
            
            String cause;
            cause = causeOfDeath.getSelectedItem().toString();
            pst.setString(14,cause);
            
            pst.setString(15,BirthCertificateOrNid.getText());

            
            String bdate = sdf.format(burriedDate.getDate());
            pst.setString(16,bdate);
            String rdate = sdf.format(recycleDate.getDate());
            pst.setString(17,rdate);
            pst.executeUpdate();
           //checking and updating availability for OwnerInfo if permanent_CorpseInfo is occupaied
            
                 String sql1="UPDATE t1   SET t1.availablility = t2.availablility   FROM Permanent_CorpseInfo t2 INNER JOIN OwnerInfo t1 ON  t2.availablility = 'Occupied' AND t2.serialNo=t1.serialNo AND t2.RecycleDate >= CAST(CURRENT_TIMESTAMP AS DATE)";
                 PreparedStatement pst1 = connection.prepareStatement(sql1);
                 pst1.executeUpdate(); 
                 
           //checking and updating availability for OwnerInfo if Recycle date is passed today    
                String sql2="UPDATE t1 SET t1.availablility ='empty' FROM Permanent_CorpseInfo  t2 INNER JOIN OwnerInfo t1 ON t2.RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE) AND t2.serialNo=t1.serialNo";
                 PreparedStatement pst2 = connection.prepareStatement(sql2);
                 pst2.executeUpdate();
                 
              //checking and updating permanent_corpseInfo if recycle date is passed
                String sql3="UPDATE Permanent_CorpseInfo SET availablility ='empty' WHERE RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE);";
                 PreparedStatement pst3 = connection.prepareStatement(sql3);
                 pst3.executeUpdate();
            
        clearAllFields();
          /*
            Date date = recycleDate.getDate();
            Date today = new Date();    
            if(today.after(date))
            {
                
                String sql2="UPDATE t1 SET t1.availablility ='empty' FROM Permanent_CorpseInfo  t2 INNER JOIN OwnerInfo t1 ON t2.RecycleDate <= CAST(CURRENT_TIMESTAMP AS DATE) AND t2.serialNo=t1.serialNo";
                 PreparedStatement pst2 = connection.prepareStatement(sql2);
                 pst2.executeUpdate();
            }
            else if(!today.after(date))
         
                 String sql1="UPDATE t1   SET t1.availablility = t2.availablility   FROM Permanent_CorpseInfo t2 INNER JOIN OwnerInfo t1 ON  t2.availablility = 'Occupied' AND t2.serialNo=t1.serialNo AND t2.RecycleDate >= CAST(CURRENT_TIMESTAMP AS DATE)";
                 PreparedStatement pst1 = connection.prepareStatement(sql1);
                 pst1.executeUpdate();
            }*/
   
                 
            DefaultTableModel model1= (DefaultTableModel)informationOfGrave.getModel();
            DefaultTableModel model2 = (DefaultTableModel)informationOfCorpse.getModel();
            DefaultTableModel model3 = (DefaultTableModel)deathRelatedInformation.getModel();
           
            model1.setRowCount(0);
            model2.setRowCount(0);
            model3.setRowCount(0);
           
            show_corpse();
            JOptionPane.showMessageDialog(left,"Inserted successfully");

            connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(left, e);
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(left,ex);
        }

    }//GEN-LAST:event_addActionPerformed

    private void timeOfDeathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeOfDeathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeOfDeathActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        timeSelector.showPopup();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        this.setVisible(false);
      MainMenu mm = new MainMenu();
      mm.setVisible(true);
      mm.openGravePanel();
      mm.openmenuPanel();
       
    }//GEN-LAST:event_backActionPerformed

    private void ownerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerIdActionPerformed
         //plotId.removeAllItems();
         try{
             if(plotId.getItemCount() != 0){
                             plotId.removeAllItems();
                      }
         } catch (Exception ignore) { }
         
        String value =ownerId.getText();
        String searchQuery = "select generalGraveId from OwnerInfo where ownerId="+value+" and availablility='empty'";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String plot=rs.getString("generalGraveId");           
                    plotId.addItem(plot);           
            }
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_ownerIdActionPerformed

    private void plotIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotIdActionPerformed
              try{
             if(!purchaseId.getText().isEmpty()){
                       purchaseId.setText("");
                }
             else{
                  String value = plotId.getSelectedItem().toString();
       
        String searchQuery = "select serialNo from OwnerInfo where generalGraveId="+value+" and availablility='empty'";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String purchase=rs.getString("serialNo");
                purchaseId.setText(purchase);
            }
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
             }
         } catch (Exception ignore) { }
           
       
      
    }//GEN-LAST:event_plotIdActionPerformed

    private void purchaseIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseIdActionPerformed

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
            java.util.logging.Logger.getLogger(Permanent_CorpseInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Permanent_CorpseInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Permanent_CorpseInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Permanent_CorpseInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Permanent_CorpseInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BirthCertificateOrNid;
    private javax.swing.JButton add;
    private javax.swing.JTextField availability;
    private javax.swing.JButton back;
    private com.toedter.calendar.JDateChooser burriedDate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> causeOfDeath;
    private com.toedter.calendar.JDateChooser dateOfBirth;
    private com.toedter.calendar.JDateChooser dateOfDeath;
    private javax.swing.JTable deathRelatedInformation;
    private javax.swing.JTextField fatherName;
    private javax.swing.JRadioButton female;
    private javax.swing.JTable informationOfCorpse;
    private javax.swing.JTable informationOfGrave;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel left;
    private javax.swing.JRadioButton male;
    private javax.swing.JTextField motherName;
    private javax.swing.JTextField name;
    private javax.swing.JRadioButton other;
    private javax.swing.JTextField ownerId;
    private javax.swing.JComboBox<String> plotId;
    private javax.swing.JComboBox<String> plotSize2;
    private javax.swing.JTextField purchaseId;
    private com.toedter.calendar.JDateChooser recycleDate;
    private javax.swing.JComboBox<String> relationWithOwner;
    private javax.swing.JTextField timeOfDeath;
    private cambodia.raven.Time timeSelector;
    // End of variables declaration//GEN-END:variables
}
