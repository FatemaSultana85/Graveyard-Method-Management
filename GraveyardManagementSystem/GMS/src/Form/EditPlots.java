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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;



public class EditPlots extends javax.swing.JFrame {
String gender,plotno,causeOfDeath;
    
    public EditPlots() {
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        initComponents();
        //setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
        GravePlotTable.getTableHeader().setBackground(new Color(32,134,203));
        GravePlotTable.getTableHeader().setForeground(new Color(255,255,255));
        GravePlotTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        GravePlotTable.getTableHeader().setOpaque(false);
        GravePlotTable.setRowHeight(25);
        
        CorpseTable.getTableHeader().setBackground(new Color(32,134,203));
        CorpseTable.getTableHeader().setForeground(new Color(255,255,255));
        CorpseTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        CorpseTable.getTableHeader().setOpaque(false);
        CorpseTable.setRowHeight(25);
        
        GuardianTable.getTableHeader().setBackground(new Color(32,134,203));
        GuardianTable.getTableHeader().setForeground(new Color(255,255,255));
        GuardianTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        GuardianTable.getTableHeader().setOpaque(false);
        GuardianTable.setRowHeight(25);
        
        DeathTable.getTableHeader().setBackground(new Color(32,134,203));
        DeathTable.getTableHeader().setForeground(new Color(255,255,255));
        DeathTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        DeathTable.getTableHeader().setOpaque(false);
        DeathTable.setRowHeight(25);
        
        CorpseTable.setAutoResizeMode(CorpseTable.AUTO_RESIZE_OFF);
        TableColumnModel col = CorpseTable.getColumnModel();
        for(int i=0;i<10;i++)
        {
            col.getColumn(i).setPreferredWidth(150);
        }
        show_available();
        show_corpse();
       
    }
   
     public void clearAllFields(){
           plotSize.setSelectedIndex(0);
           corpseType.setSelectedIndex(0);
           //buttonGroup1.clearSelection();
           male.setSelected(false);
           female.setSelected(false);
           other.setSelected(false);
           availableCombo.setSelectedIndex(0);
           timeOfDeath.setText("");
           name.setText("");
           fathersName.setText("");
           mothersName.setText("");
           death.setSelectedIndex(0);
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
    public ArrayList<AvailableGraves>availableList(){
        ArrayList<AvailableGraves> availableList = new ArrayList<>();
     try {

     try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     } 
     catch (ClassNotFoundException ex) 
     {
         JOptionPane.showMessageDialog(null,"Database not connected successfully");
     }
            Connection connection = DriverManager .getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");
         
            //String query1 = "SELECT generalGraveId,Max(burriedDate) AS lastBurried FROM GeneralGrave1 where DATEDIFF(month,burriedDate,GETDATE())<=1 group by generalGraveId";
            String query1 = "SELECT generalGraveId,Max(recycleDate) AS recycleDate FROM GeneralGrave1 where recycleDate>GETDATE() group by generalGraveId";
                     
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            AvailableGraves available;
            while(rs.next())
            {
                //opposite has been done on 9/8/20
                //that is  availablegraves shows unavailable graves
              available = new AvailableGraves(rs.getInt("generalGraveId"),rs.getString("recycleDate"));
                      
              availableList.add(available);
            }
            //connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null,ex);
        }
     return availableList;
           
    }
    public void show_available(){
    ArrayList<AvailableGraves> list = availableList();
    int size = list.size();
    int totalNumberOfPlots = 2000;
    for(int j = 1;j<=totalNumberOfPlots; j++)
    {
        availableCombo.addItem(Integer.toString(j));     

    }
    for(int i = 0; i<size; i++)
    {
         availableCombo.removeItem(list.get(i).getgeneralGraveId());     
         
    }
   
    }
   
    public ArrayList<Corpse>corpseList(){
        ArrayList<Corpse> corpseList = new ArrayList<>();
     try {

     try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     } 
     catch (ClassNotFoundException ex) 
     {
         JOptionPane.showMessageDialog(null, "Database not connected successfully");
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
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null,ex);
        }
     return corpseList;
           
    }
    public void show_corpse(){
    ArrayList<Corpse> list = corpseList();
    DefaultTableModel model1 = (DefaultTableModel)GravePlotTable.getModel();
    Object[] row1 = new Object[6];
    for(int i = 0; i<list.size(); i++)
    {
        row1[0] = list.get(i).getgeneralGraveId();
        row1[1] = list.get(i).getgeneralGraveSize();
        row1[2] = list.get(i).getgeneralCorpseId();
        row1[3] = list.get(i).getcorpseType();
        row1[4] = list.get(i).getburriedDate();
        row1[5] = list.get(i).getRecycleDate();
        model1.addRow(row1);
    }
    DefaultTableModel model2 = (DefaultTableModel)CorpseTable.getModel();
    Object[] row2 = new Object[10];
    for(int i = 0; i<list.size(); i++)
    {
        row2[0] = list.get(i).getgeneralCorpseId();
        row2[1] = list.get(i).getgeneralGraveId();
        row2[2] = list.get(i).getcorpseType();
        row2[3] = list.get(i).getcorpseName();
        row2[4] = list.get(i).getcorpseFatherName();
        row2[5] = list.get(i).getcorpseMotherName();
        row2[6] = list.get(i).getcorpseDateOfBirth();
        row2[7] = list.get(i).getcorpseBirthCertificateNo();
        row2[8] = list.get(i).getcorpseNidNo();
        row2[9] = list.get(i).getcorpseGender();
        model2.addRow(row2);
    }
    DefaultTableModel model3 = (DefaultTableModel)GuardianTable.getModel();
    Object[] row3 = new Object[6];
    for(int i = 0; i<list.size(); i++)
    {
        row3[0] = list.get(i).getgeneralCorpseId();
        row3[1] = list.get(i).getgeneralGraveId();
        row3[2] = list.get(i).getguardianRelation();
        row3[3] = list.get(i).getguardianName();
        row3[4] = list.get(i).getguardianContactNo();
        row3[5] = list.get(i).getguardianAddress();
        model3.addRow(row3);
    }
    DefaultTableModel model4 = (DefaultTableModel)DeathTable.getModel();
    Object[] row4 = new Object[5];
    for(int i = 0; i<list.size(); i++)
    {
        row4[0] = list.get(i).getgeneralCorpseId();
        row4[1] = list.get(i).getgeneralGraveId();
        row4[2] = list.get(i).getcorpseDateOfDeath();
        row4[3] = list.get(i).getcorpseCauseOfDeath();
        row4[4] = list.get(i).getcorpseTimeOfDeath();
        model4.addRow(row4);
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        GravePlotTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        CorpseTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        GuardianTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        DeathTable = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        availableCombo = new javax.swing.JComboBox<>();
        plotSize = new javax.swing.JComboBox<>();
        burriedDate = new com.toedter.calendar.JDateChooser();
        recycleDate = new com.toedter.calendar.JDateChooser();
        corpseType = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        fathersName = new javax.swing.JTextField();
        mothersName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        other = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        male = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        dateOfBirth = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        nid = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        birthCertificateNo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        dateOfDeath = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        timeOfDeath = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        GuardianName = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        relation = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        guardianContactNo = new javax.swing.JTextField();
        guardianAddress = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        death = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        timeSelector.setBackground(new java.awt.Color(255, 255, 255));
        timeSelector.setTextRefernce(timeOfDeath);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GravePlotTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Size", "Corpse ID", "Corpse Type", "Burried date", "Recycle date"
            }
        ));
        GravePlotTable.setGridColor(new java.awt.Color(255, 255, 255));
        GravePlotTable.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jScrollPane2.setViewportView(GravePlotTable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 900, 113));

        CorpseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Plot no", "Type", "Name", "Father's name", "Mother's name", "Date of birth", "Birth Certicicate no", "NID", "Gender"
            }
        ));
        CorpseTable.setGridColor(new java.awt.Color(255, 255, 255));
        CorpseTable.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jScrollPane1.setViewportView(CorpseTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 900, 120));

        GuardianTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Corpse ID", "Plot no", "Relation", "Name", "Contact no", "Address"
            }
        ));
        GuardianTable.setGridColor(new java.awt.Color(255, 255, 255));
        GuardianTable.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jScrollPane4.setViewportView(GuardianTable);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 900, 120));

        DeathTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Corpse ID", "Plot no", "Date of death", "Cause of death", "Time of death"
            }
        ));
        DeathTable.setGridColor(new java.awt.Color(255, 255, 255));
        DeathTable.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jScrollPane3.setViewportView(DeathTable);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 530, 900, 120));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("Death related information:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, 330, 40));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Guardian/Relatives information:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 340, 50));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Information of corpse :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 250, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Grave Plot information:");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 300, 45));

        availableCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plot no" }));
        jPanel1.add(availableCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 170, -1));

        plotSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Small", "Medium", "Large" }));
        jPanel1.add(plotSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 169, -1));
        jPanel1.add(burriedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 169, -1));
        jPanel1.add(recycleDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 169, -1));

        corpseType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Identified", "Unidentified" }));
        corpseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corpseTypeActionPerformed(evt);
            }
        });
        jPanel1.add(corpseType, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 169, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Grave Plot no:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Plot size:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Burried date:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Recycle date:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 97, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Corpse type:");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Name:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Father's name:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 180, 30));
        jPanel1.add(fathersName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 177, -1));
        jPanel1.add(mothersName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 177, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Mother's name:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        buttonGroup1.add(other);
        other.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        other.setText("Other");
        jPanel1.add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, -1, -1));

        buttonGroup1.add(female);
        female.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        female.setText("Female");
        jPanel1.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        buttonGroup1.add(male);
        male.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        male.setText("Male");
        jPanel1.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Gender:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 95, 23));
        jPanel1.add(dateOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 177, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Date of birth:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 94, -1));
        jPanel1.add(nid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 177, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("NID:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 75, 20));
        jPanel1.add(birthCertificateNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 177, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Birth Certificate no:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));
        jPanel1.add(dateOfDeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 177, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Date of Death:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 104, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 27, -1));

        timeOfDeath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeOfDeathActionPerformed(evt);
            }
        });
        jPanel1.add(timeOfDeath, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 144, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Time of death:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Cause of death:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));
        jPanel1.add(GuardianName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 180, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Guardian's name:");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, -1));

        relation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationActionPerformed(evt);
            }
        });
        jPanel1.add(relation, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 180, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Relation:");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Contact no:");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Address:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));
        jPanel1.add(guardianContactNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 560, 180, -1));
        jPanel1.add(guardianAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 590, 180, -1));

        addButton.setText("add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 650, -1, -1));

        jButton2.setText("back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 650, -1, -1));

        death.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Natural", "Accidental", "Disease", "Homicide", "Suicide", "Pandemic" }));
        jPanel1.add(death, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 180, -1));

        jButton3.setText("jButton1");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 630, 40, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 710));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        try {
            System.out.println("hi");

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Database not connected successfully");
            }
            Connection connection = DriverManager .getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");

            String sql = "INSERT INTO GeneralGrave1(corpseType,generalGraveId,generalGraveSize,corpseGender,burriedDate,RecycleDate,corpseTimeOfDeath,corpseName,corpseFatherName,corpseMotherName,corpseDateOfBirth,corpseDateOfDeath,corpseCauseOfDeath,corpseNidNo,corpseBirthCertificateNo,guardianName,guardianRelation,guardianContactNo,guardianAddress) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String sql1 = "INSERT INTO GeneralGrave1(corpseType,generalGraveId,generalGraveSize,corpseGender,burriedDate,RecycleDate) values(?,?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            causeOfDeath = death.getSelectedItem().toString();
            plotno = availableCombo.getSelectedItem().toString();
            String type;
            type = corpseType.getSelectedItem().toString();

            if(type=="Unidentified"){

                pst1.setString(1,type);
                pst1.setString(2,plotno);

                String size1;
                size1 = plotSize.getSelectedItem().toString();
                pst1.setString(3,size1);

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
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                String bdate1 = sdf1.format(burriedDate.getDate());
                pst1.setString(5,bdate1);
                String rdate1 = sdf1.format(recycleDate.getDate());
                pst1.setString(6,rdate1);
                pst1.executeUpdate();
                DefaultTableModel model1= (DefaultTableModel)GravePlotTable.getModel();
                DefaultTableModel model2 = (DefaultTableModel)CorpseTable.getModel();
                DefaultTableModel model3 = (DefaultTableModel)GuardianTable.getModel();
                DefaultTableModel model4 = (DefaultTableModel)DeathTable.getModel();
                model1.setRowCount(0);
                model2.setRowCount(0);
                model3.setRowCount(0);
                model4.setRowCount(0);
                clearAllFields();
                show_corpse();
                show_available();
                JOptionPane.showMessageDialog(null,"Inserted successfully");

                connection.close();
            }
            else if(type=="Identified"){
                pst.setString(1,type);
                pst.setString(2,plotno);

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
                pst.setString(13,causeOfDeath);
                pst.setString(14,nid.getText());
                pst.setString(15,birthCertificateNo.getText());
                pst.setString(16,GuardianName.getText());
                pst.setString(17,relation.getText());
                pst.setString(18,guardianContactNo.getText());
                pst.setString(19,guardianAddress.getText());
                pst.executeUpdate();

                DefaultTableModel model1= (DefaultTableModel)GravePlotTable.getModel();
                DefaultTableModel model2 = (DefaultTableModel)CorpseTable.getModel();
                DefaultTableModel model3 = (DefaultTableModel)GuardianTable.getModel();
                DefaultTableModel model4 = (DefaultTableModel)DeathTable.getModel();
                model1.setRowCount(0);
                model2.setRowCount(0);
                model3.setRowCount(0);
                model4.setRowCount(0);
                clearAllFields();
                show_corpse();
                show_available();
                JOptionPane.showMessageDialog(null,"Inserted successfully");

                connection.close();
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null,ex);
        }

    }//GEN-LAST:event_addButtonActionPerformed

    private void corpseTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corpseTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_corpseTypeActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void timeOfDeathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeOfDeathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeOfDeathActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        timeSelector.showPopup();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void relationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_relationActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      this.setVisible(false);
      MainMenu mm = new MainMenu();
      mm.setVisible(true);
      mm.openGravePanel();
      mm.openmenuPanel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        timeSelector.showPopup();
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
            java.util.logging.Logger.getLogger(EditPlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditPlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditPlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditPlots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditPlots().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CorpseTable;
    private javax.swing.JTable DeathTable;
    private javax.swing.JTable GravePlotTable;
    private javax.swing.JTextField GuardianName;
    private javax.swing.JTable GuardianTable;
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> availableCombo;
    private javax.swing.JTextField birthCertificateNo;
    private com.toedter.calendar.JDateChooser burriedDate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> corpseType;
    private com.toedter.calendar.JDateChooser dateOfBirth;
    private com.toedter.calendar.JDateChooser dateOfDeath;
    private javax.swing.JComboBox<String> death;
    private javax.swing.JTextField fathersName;
    private javax.swing.JRadioButton female;
    private javax.swing.JTextField guardianAddress;
    private javax.swing.JTextField guardianContactNo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton male;
    private javax.swing.JTextField mothersName;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nid;
    private javax.swing.JRadioButton other;
    private javax.swing.JComboBox<String> plotSize;
    private com.toedter.calendar.JDateChooser recycleDate;
    private javax.swing.JTextField relation;
    private javax.swing.JTextField timeOfDeath;
    private cambodia.raven.Time timeSelector;
    // End of variables declaration//GEN-END:variables
}
