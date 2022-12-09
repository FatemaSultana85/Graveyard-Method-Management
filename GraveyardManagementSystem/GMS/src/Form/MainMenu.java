package Form;
import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author r.kumbara
 */
public class MainMenu extends javax.swing.JFrame {
int notificationQueryExecuteVariable;
String code;
    public MainMenu() {
        initComponents();
        //setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
        GeneralView();
        PermanentView();
        soldView();
        
       
    }
    //for login signup
    public void sendMail(String recepient) throws Exception{
                Properties properties = new Properties();
              
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true"); 
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");
                
                String sender = "tahiyaahmedchowdhury@gmail.com";
                String sender_password = "10710108";
                
                Session session = Session.getInstance(properties, new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(sender,sender_password);
                    }
            });
            Message message = prepareMessage(session,sender,recepient);
            Transport.send(message);
            }
            public  Message prepareMessage(Session session,String sender,String recepient)
            {
                try{
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(sender));
                    message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
                    message.setSubject("Confirmation code");
                    int c = ThreadLocalRandom.current().nextInt();
                    code = Integer.toString(c);
                    message.setText(code);
                    return message;
                } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
            }

    ////////////for login signup
     public void opengmsPanel()
    {
        
        gmsPanel.setVisible(true);
        menuPanel.setVisible(false);
    }
     public void openmenuPanel()
    { 
        
        gmsPanel.setVisible(false);
        menuPanel.setVisible(true);
    }
     public void opensigninPanel(){
        
         tablePanel.setVisible(false);
        statisticsPanel.setVisible(false);
         signinPanel.setVisible(true);
        signupPanel.setVisible(false);
         welcomePanel.setVisible(false);
         gravePanel.setVisible(false);
     }
     public void opensignupPanel(){
         
         tablePanel.setVisible(false);
        statisticsPanel.setVisible(false);
         signinPanel.setVisible(false);
        signupPanel.setVisible(true);
         welcomePanel.setVisible(false);
         gravePanel.setVisible(false);
     }
     
            
    public void openGravePanel()
    {
        
        tablePanel.setVisible(false);
        statisticsPanel.setVisible(false);
         signinPanel.setVisible(false);
        signupPanel.setVisible(false);
         welcomePanel.setVisible(false);
         gravePanel.setVisible(true);
        
    }
    public void openWelcomePanel()
    {   
        tablePanel.setVisible(false);
        statisticsPanel.setVisible(false);
        signinPanel.setVisible(false);
        signupPanel.setVisible(false);
         gravePanel.setVisible(false);
         welcomePanel.setVisible(true);
        
    }
    public void openTablePanel() 
    { 
        tablePanel.setVisible(true);
        statisticsPanel.setVisible(false);
        signinPanel.setVisible(false);
        signupPanel.setVisible(false);
         gravePanel.setVisible(false);
         welcomePanel.setVisible(false);
        
    }
    public void openStatisticsPanel()
    {
       
        tablePanel.setVisible(false);
        statisticsPanel.setVisible(true);
        signinPanel.setVisible(false);
        signupPanel.setVisible(false);
         gravePanel.setVisible(false);
         welcomePanel.setVisible(false);
        
    }
    public void GeneralView(){
    String searchQuery = "SELECT SUM(t.available) from ((SELECT(2000-COUNT(DISTINCT generalGraveId)) as available FROM GeneralGrave1 where RecycleDate>GETDATE())\n" +
                            "union all\n" +
                            "(SELECT(2000-COUNT(DISTINCT permanentCorpseId)) as available FROM Permanent_CorpseInfo where RecycleDate>GETDATE())) t";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
           String ss = rs.getString(1);
          available.setText(ss);
           }
            total.setText("4000");
          
 
            
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
        }
    }
    public void soldView(){
        String searchQuery = "SELECT(2000-COUNT(DISTINCT generalGraveId)) as available FROM OwnerInfo;";

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
           String ss = rs.getString(1);
          sold.setText(ss);
           }
          
          
 
            
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
        }
    }
    public void PermanentView()
    {
        /*String searchQuery = "SELECT (2000-COUNT (DISTINCT permanentGraveId)) FROM Permanent_CorpseInfo;";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
           String ss = rs.getString(1);
       
           }
            
          
 
            
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); }
        */
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        setRecycleDate = new com.toedter.calendar.JDateChooser();
        mainPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        gmsPanel = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        about = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        logout = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        home1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        tables = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        statistics = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        signinPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        LoginUserName = new javax.swing.JTextField();
        LoginPassword = new javax.swing.JPasswordField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        signin = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        signup = new javax.swing.JButton();
        background = new javax.swing.JLabel();
        gravePanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        owner = new javax.swing.JButton();
        total = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        available = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        sold = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        permanentCombo = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        welcomePanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        titleLabel1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        titleLabel2 = new javax.swing.JLabel();
        signupPanel = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        sigupConfirm = new javax.swing.JButton();
        fullname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        contactno = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        confirmpassword = new javax.swing.JPasswordField();
        password = new javax.swing.JPasswordField();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        statisticsPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        burriedInOneDate = new javax.swing.JButton();
        diedInSameCause = new javax.swing.JButton();
        peopleInSameGender = new javax.swing.JButton();
        burriedInSamePlotSize = new javax.swing.JButton();
        burriedInOnePlot = new javax.swing.JButton();
        boughtByOneOwner = new javax.swing.JButton();
        burriedInOneDate1 = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        wholeTable = new javax.swing.JButton();
        permanentTable = new javax.swing.JButton();
        generalTable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        mainPanel.setBackground(new java.awt.Color(13, 13, 54));
        mainPanel.setMinimumSize(new java.awt.Dimension(1000, 625));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        leftPanel.setLayout(new java.awt.CardLayout());

        gmsPanel.setBackground(new java.awt.Color(13, 13, 54));
        gmsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(204, 204, 204));
        jLabel44.setText("GRAVEYARD");
        gmsPanel.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 210, 80));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("MANAGEMENT");
        gmsPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 240, 100));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("SYSTEM");
        gmsPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 170, 90));

        leftPanel.add(gmsPanel, "card3");

        menuPanel.setBackground(new java.awt.Color(13, 13, 54));
        menuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(13, 13, 54));

        about.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        about.setForeground(new java.awt.Color(204, 204, 204));
        about.setText("ABOUT");
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(about, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(about, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 240, 70));

        jPanel8.setBackground(new java.awt.Color(13, 13, 54));

        logout.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        logout.setForeground(new java.awt.Color(204, 204, 204));
        logout.setText("LOGOUT");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(104, 104, 104))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuPanel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 240, 80));

        jPanel9.setBackground(new java.awt.Color(13, 13, 54));

        home1.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        home1.setForeground(new java.awt.Color(204, 204, 204));
        home1.setText("HOME");
        home1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        menuPanel.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 240, 70));

        jPanel16.setBackground(new java.awt.Color(13, 13, 54));

        tables.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        tables.setForeground(new java.awt.Color(204, 204, 204));
        tables.setText("TABLES");
        tables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tables, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tables, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuPanel.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 240, 70));

        jPanel17.setBackground(new java.awt.Color(13, 13, 54));

        statistics.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        statistics.setForeground(new java.awt.Color(204, 204, 204));
        statistics.setText("STATISTICS");
        statistics.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statisticsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statistics)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statistics, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        menuPanel.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 240, 80));

        leftPanel.add(menuPanel, "card2");

        mainPanel.add(leftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 550));

        rightPanel.setLayout(new java.awt.CardLayout());

        signinPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel6AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("SIGN IN");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 149, 40));

        jPanel7.setBackground(new java.awt.Color(0, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 5));

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/username.jpg"))); // NOI18N
        jLabel8.setText("jLabel3");
        jPanel14.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        jPanel6.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 30, 30));

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/password.jpg"))); // NOI18N
        jLabel21.setText("jLabel4");
        jPanel15.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        jPanel6.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 30, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 51));
        jLabel23.setText("Username :");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 124, 80, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 51));
        jLabel24.setText("Password :");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 194, 80, 20));

        LoginUserName.setBackground(new java.awt.Color(204, 204, 204));
        LoginUserName.setBorder(null);
        jPanel6.add(LoginUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 160, 30));

        LoginPassword.setBackground(new java.awt.Color(204, 204, 204));
        LoginPassword.setBorder(null);
        LoginPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginPasswordActionPerformed(evt);
            }
        });
        jPanel6.add(LoginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 160, 30));
        jPanel6.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        jSeparator5.setForeground(new java.awt.Color(0, 51, 51));
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 142, 160, 20));

        jSeparator6.setForeground(new java.awt.Color(0, 51, 51));
        jPanel6.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 212, 160, 30));

        signin.setBackground(new java.awt.Color(204, 204, 204));
        signin.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        signin.setForeground(new java.awt.Color(0, 51, 51));
        signin.setText("Continue");
        signin.setBorder(null);
        signin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signinActionPerformed(evt);
            }
        });
        jPanel6.add(signin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 80, 30));

        signinPanel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 320, 320));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 51, 51));
        jLabel32.setText("Don't have an account?");
        signinPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 150, 30));

        signup.setBackground(new java.awt.Color(204, 204, 204));
        signup.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        signup.setForeground(new java.awt.Color(0, 51, 51));
        signup.setText("Sign up now!");
        signup.setBorder(null);
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });
        signinPanel.add(signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 110, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/login&signupbg.png"))); // NOI18N
        background.setText("jLabel17");
        signinPanel.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 490));

        rightPanel.add(signinPanel, "card5");

        gravePanel.setBackground(new java.awt.Color(255, 255, 255));
        gravePanel.setPreferredSize(new java.awt.Dimension(500, 550));
        gravePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 102));
        jLabel19.setText(" HOME");
        gravePanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 120, 50));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/back (1).png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        gravePanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 90, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Search in General Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gravePanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 260, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Search in Permanent Table");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gravePanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 260, 40));

        owner.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        owner.setText("Add New Owner");
        owner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerActionPerformed(evt);
            }
        });
        gravePanel.add(owner, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 260, 40));

        total.setEditable(false);
        gravePanel.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 100, 60));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel31.setText("Total:");
        gravePanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 100, 50));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel30.setText("Available:");
        gravePanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, 50));

        available.setBackground(new java.awt.Color(240, 240, 240));
        available.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availableActionPerformed(evt);
            }
        });
        gravePanel.add(available, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 100, 60));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Sold:");
        gravePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 100, 50));

        sold.setBackground(new java.awt.Color(240, 240, 240));
        sold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soldActionPerformed(evt);
            }
        });
        gravePanel.add(sold, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 100, 60));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add", "Update", "Delete" }));
        jComboBox2.setBorder(null);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("Edit General Table");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        gravePanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 240, 110));

        permanentCombo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        permanentCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Add", "Update", "Delete", "" }));
        permanentCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permanentComboActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel25.setText("Edit Permanent Table");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(permanentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(permanentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gravePanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, -1, 110));

        rightPanel.add(gravePanel, "card3");

        welcomePanel.setBackground(new java.awt.Color(255, 255, 255));
        welcomePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(240, 240, 212));
        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 0));
        jLabel9.setText("of grave plots");

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/asterisk.gif"))); // NOI18N
        jLabel28.setText("jLabel14");

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setBackground(new java.awt.Color(240, 240, 212));
        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 0, 0));
        jLabel13.setText("of grave plots");

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/asterisk.gif"))); // NOI18N
        jLabel33.setText("jLabel14");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel33)
                .addGap(17, 17, 17)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Tahiya Ahmed Chowdhury");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("ID : 170204048");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(36, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jLabel28)
                .addGap(17, 17, 17)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(141, 141, 141))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(148, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(138, Short.MAX_VALUE)))
        );

        welcomePanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 170, 100));

        jLabel11.setBackground(new java.awt.Color(240, 240, 212));
        jLabel11.setFont(new java.awt.Font("Sitka Heading", 0, 18)); // NOI18N
        jLabel11.setText("Graveyard Management System has been developed with a view to");

        jLabel27.setBackground(new java.awt.Color(240, 240, 212));
        jLabel27.setFont(new java.awt.Font("Sitka Heading", 0, 18)); // NOI18N
        jLabel27.setText("store records of relevant informations and to ease the access of");

        jLabel26.setBackground(new java.awt.Color(240, 240, 212));
        jLabel26.setFont(new java.awt.Font("Sitka Heading", 0, 18)); // NOI18N
        jLabel26.setText("data and to simplify the maintainenece of graveyards.");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        welcomePanel.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 510, 110));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Junaed Younus Khan");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Lecturer,AUST");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel45)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        welcomePanel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 240, 70));

        titleLabel.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel.setFont(new java.awt.Font("SimSun-ExtB", 0, 24)); // NOI18N
        titleLabel.setText("For guidance and inspiration :)");
        welcomePanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 390, 30));

        jLabel5.setBackground(new java.awt.Color(240, 240, 212));
        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        jLabel5.setText("Intro :");
        welcomePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 100, 40));

        titleLabel1.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        titleLabel1.setText("Developer's info:");
        welcomePanel.add(titleLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 280, 40));

        jLabel10.setBackground(new java.awt.Color(240, 240, 212));
        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 0));
        jLabel10.setText("of grave plots");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/asterisk.gif"))); // NOI18N
        jLabel29.setText("jLabel14");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Takia Maliha");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("ID : 170204037");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193))))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(jLabel29)
                .addGap(17, 17, 17)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(141, 141, 141))
        );

        welcomePanel.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 160, 100));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Tamanna Nazmin");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("ID : 170204052");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel17))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        welcomePanel.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 150, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Nazmus Sakib");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Assistant Professor,AUST");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        welcomePanel.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 230, 70));

        titleLabel2.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        titleLabel2.setText("Cordial thanks to respected teachers:");
        welcomePanel.add(titleLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 420, 40));

        rightPanel.add(welcomePanel, "card2");

        signupPanel.setBackground(new java.awt.Color(204, 204, 255));
        signupPanel.setForeground(new java.awt.Color(0, 51, 51));
        signupPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 51, 51));
        jLabel34.setText("Sign-Up");
        signupPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 180, 50));

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 51, 51));
        jLabel35.setText("Full Name :");
        signupPanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 110, 40));

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 51, 51));
        jLabel36.setText("Confirm Password :");
        signupPanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 170, 40));

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 51, 51));
        jLabel37.setText("E-mail :");
        signupPanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 70, 40));

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 51, 51));
        jLabel38.setText("Contact no :");
        signupPanel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 110, 30));

        jLabel39.setBackground(new java.awt.Color(255, 255, 255));
        jLabel39.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 51, 51));
        jLabel39.setText("Username :");
        signupPanel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 100, 40));

        jLabel40.setBackground(new java.awt.Color(255, 255, 255));
        jLabel40.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 51, 51));
        jLabel40.setText("Password :");
        signupPanel.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 100, 40));

        sigupConfirm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sigupConfirm.setForeground(new java.awt.Color(0, 51, 51));
        sigupConfirm.setText("CONFIRM");
        sigupConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sigupConfirmMouseClicked(evt);
            }
        });
        sigupConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigupConfirmActionPerformed(evt);
            }
        });
        signupPanel.add(sigupConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 390, -1, 40));

        fullname.setBackground(new java.awt.Color(226, 236, 236));
        fullname.setBorder(null);
        fullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullnameActionPerformed(evt);
            }
        });
        signupPanel.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 390, 40));

        email.setBackground(new java.awt.Color(226, 236, 236));
        email.setBorder(null);
        signupPanel.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 390, 40));

        contactno.setBackground(new java.awt.Color(226, 236, 236));
        contactno.setBorder(null);
        signupPanel.add(contactno, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 390, 40));

        username.setBackground(new java.awt.Color(226, 236, 236));
        username.setBorder(null);
        signupPanel.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 390, 40));

        confirmpassword.setBackground(new java.awt.Color(226, 236, 236));
        confirmpassword.setBorder(null);
        signupPanel.add(confirmpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 390, 40));

        password.setBackground(new java.awt.Color(226, 236, 236));
        password.setBorder(null);
        signupPanel.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 390, 40));

        jSeparator7.setBackground(new java.awt.Color(0, 51, 51));
        jSeparator7.setForeground(new java.awt.Color(0, 51, 51));
        signupPanel.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 390, 10));

        jSeparator8.setBackground(new java.awt.Color(0, 51, 51));
        jSeparator8.setForeground(new java.awt.Color(0, 51, 51));
        signupPanel.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 390, 20));

        jSeparator9.setBackground(new java.awt.Color(0, 51, 51));
        jSeparator9.setForeground(new java.awt.Color(0, 51, 51));
        signupPanel.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 390, 10));

        jSeparator10.setBackground(new java.awt.Color(0, 51, 51));
        jSeparator10.setForeground(new java.awt.Color(0, 51, 51));
        signupPanel.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 390, 10));

        jSeparator11.setBackground(new java.awt.Color(0, 51, 51));
        jSeparator11.setForeground(new java.awt.Color(0, 51, 51));
        signupPanel.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 390, 20));

        jSeparator12.setBackground(new java.awt.Color(0, 51, 51));
        jSeparator12.setForeground(new java.awt.Color(0, 51, 51));
        signupPanel.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 390, 10));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 51, 51));
        jButton5.setText("GO BACK");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        signupPanel.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, -1, 40));

        jLabel41.setBackground(new java.awt.Color(226, 236, 236));
        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 51, 51));
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/login&signupbg.png"))); // NOI18N
        jLabel41.setText("<<");
        signupPanel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 490));

        rightPanel.add(signupPanel, "card6");

        statisticsPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("View stats of");

        burriedInOneDate.setBackground(new java.awt.Color(255, 255, 255));
        burriedInOneDate.setText("Frequency of people burried in same date");
        burriedInOneDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        burriedInOneDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burriedInOneDateActionPerformed(evt);
            }
        });

        diedInSameCause.setBackground(new java.awt.Color(255, 255, 255));
        diedInSameCause.setText("Frequency of people died of same cause");
        diedInSameCause.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        diedInSameCause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diedInSameCauseActionPerformed(evt);
            }
        });

        peopleInSameGender.setBackground(new java.awt.Color(255, 255, 255));
        peopleInSameGender.setText("Frequency of people of same gender");
        peopleInSameGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        peopleInSameGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peopleInSameGenderActionPerformed(evt);
            }
        });

        burriedInSamePlotSize.setBackground(new java.awt.Color(255, 255, 255));
        burriedInSamePlotSize.setText("Plots usage frequency on the basis of Plot size");
        burriedInSamePlotSize.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        burriedInSamePlotSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burriedInSamePlotSizeActionPerformed(evt);
            }
        });

        burriedInOnePlot.setBackground(new java.awt.Color(255, 255, 255));
        burriedInOnePlot.setText("Plots usage frequency");
        burriedInOnePlot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        burriedInOnePlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burriedInOnePlotActionPerformed(evt);
            }
        });

        boughtByOneOwner.setBackground(new java.awt.Color(255, 255, 255));
        boughtByOneOwner.setText("No of plots bought by Same owner");
        boughtByOneOwner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        boughtByOneOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boughtByOneOwnerActionPerformed(evt);
            }
        });

        burriedInOneDate1.setBackground(new java.awt.Color(255, 255, 255));
        burriedInOneDate1.setText("People burried per year");
        burriedInOneDate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        burriedInOneDate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burriedInOneDate1ActionPerformed(evt);
            }
        });

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statisticsPanelLayout = new javax.swing.GroupLayout(statisticsPanel);
        statisticsPanel.setLayout(statisticsPanelLayout);
        statisticsPanelLayout.setHorizontalGroup(
            statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsPanelLayout.createSequentialGroup()
                .addGroup(statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statisticsPanelLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(burriedInOneDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(burriedInOneDate, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(diedInSameCause, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(peopleInSameGender, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(burriedInSamePlotSize, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boughtByOneOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(burriedInOnePlot, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(statisticsPanelLayout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(Back)))
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statisticsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
        );
        statisticsPanelLayout.setVerticalGroup(
            statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(burriedInOneDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(burriedInOneDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(diedInSameCause, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(peopleInSameGender, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(burriedInSamePlotSize, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boughtByOneOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(burriedInOnePlot, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(Back)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightPanel.add(statisticsPanel, "card8");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("View");

        wholeTable.setText("Whole Database");
        wholeTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        wholeTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wholeTableActionPerformed(evt);
            }
        });

        permanentTable.setText("General Grave Table");
        permanentTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        permanentTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permanentTableActionPerformed(evt);
            }
        });

        generalTable.setText("Permanent Grave Table");
        generalTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        generalTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wholeTable, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(permanentTable, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generalTable, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(194, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addGap(42, 42, 42)
                .addComponent(wholeTable, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(permanentTable, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(generalTable, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rightPanel.add(tablePanel, "card7");

        mainPanel.add(rightPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 640, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 988, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        opengmsPanel();
        opensigninPanel();
    }//GEN-LAST:event_logoutMouseClicked

    private void aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMouseClicked
           openWelcomePanel();
    }//GEN-LAST:event_aboutMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        this.setVisible(false);
        MainMenu r=new MainMenu();
        r.SetVisible(true);
        r.pack();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        this.setVisible(false);
        EditPlots ep = new EditPlots();
        String s = jComboBox2.getSelectedItem().toString();
        if(s.equals("Add"))
        {
            this.setVisible(false);
            new EditPlots().setVisible(true);
        }
        if(s.equals("Update"))
        {
            this.setVisible(false);
            update u = new update();
            u.setVisible(true);
            u.openUpdatePanel();
        }
        if(s.equals("Delete"))
        {
            this.setVisible(false);
            update u = new update();
            u.setVisible(true);
            u.openDeletePanel();
        }
   

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void permanentComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permanentComboActionPerformed
         this.setVisible(false);
        Permanent_CorpseInfo ep = new Permanent_CorpseInfo();
        String s = permanentCombo.getSelectedItem().toString();
        if(s.equals("Add"))
        {
            this.setVisible(false);
            new Permanent_CorpseInfo().setVisible(true);
        }
        if(s.equals("Update"))
        {
            this.setVisible(false);
            UpdatePermanent u = new UpdatePermanent();
            u.setVisible(true);
            u.openUpdatePanel();
        }
        if(s.equals("Delete"))
        {
            this.setVisible(false);
            UpdatePermanent u = new UpdatePermanent();
            u.setVisible(true);
            u.openDeletePanel();
        }
   
    }//GEN-LAST:event_permanentComboActionPerformed

    private void availableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_availableActionPerformed
   
        
    }//GEN-LAST:event_availableActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        SearchGen sg = new SearchGen();
        sg.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        SearchPer sp = new SearchPer();
        sp.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerActionPerformed
        OwnerInfo oi = new OwnerInfo();
        this.setVisible(false);
        oi.setVisible(true);
    }//GEN-LAST:event_ownerActionPerformed

    private void LoginPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoginPasswordActionPerformed

    private void signinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signinActionPerformed
        // TODO add your handling code here:

        try {
            System.out.println("hi");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            System.out.println("connected sucessfully");

            String sql;
            sql = "Select * from login where username=? and password1=?";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, LoginUserName.getText());
            pst.setString(2, LoginPassword.getText());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username and Password Matched "  );//+rs.getString("username") + " " + rs.getString("password"));
            openmenuPanel();
            openGravePanel();

        } else {
            //JOptionPane.showMessageDialog(null, "Username and password not Correct ,Signup NOW!!!!");
            if( LoginUserName.getText().trim().isEmpty() &&  LoginPassword.getText().trim().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter value of all fields");

            }

            else if  (LoginUserName.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter username");
            }
            else if (LoginPassword.getText().trim().isEmpty()){

                JOptionPane.showMessageDialog(null, "Please enter password");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Username and password not matched");
            }
            LoginUserName.setText("");
            LoginPassword.setText("");
        }

        connection.close();
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_signinActionPerformed

    private void jPanel6AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel6AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6AncestorAdded

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
        // TODO add your handling code here:
        opensignupPanel();

    }//GEN-LAST:event_signupActionPerformed

    private void sigupConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sigupConfirmMouseClicked

    }//GEN-LAST:event_sigupConfirmMouseClicked

    private void sigupConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigupConfirmActionPerformed
        if(email.getText().equals("")||contactno.getText().equals("")||fullname.getText().equals("")||password.getText().equals("")||confirmpassword.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Enter all fields");
        }
        else{
            if (!(Pattern.matches("^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$", email.getText())))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(!(password.getText().equals(confirmpassword.getText())))
            {
                JOptionPane.showMessageDialog(null,"Please enter same value for password");
            }
            else
            {
                try {
                    sendMail(email.getText());
                    String check;
                    check = JOptionPane.showInputDialog("Enter sent code");
                    if(check.equals(code)){
                        try {
                            System.out.println("hi");

                            try {
                                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            }
                            catch (ClassNotFoundException ex) {
                                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Connection connection = DriverManager .getConnection(
                                "jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
                            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

                            System.out.println("connected sucessfully");

                            String sql = "INSERT INTO login(fullname,username,password1,email,contactno) values(?,?,?,?,?)";

                            PreparedStatement pst = connection.prepareStatement(sql);
                            pst.setString(1, fullname.getText());
                            pst.setString(2, username.getText());
                            pst.setString(3, password.getText());
                            pst.setString(4, email.getText());
                            pst.setString(5, contactno.getText());

                            pst.executeUpdate();

                            connection.close();
                        } catch (HeadlessException e) {
                            JOptionPane.showMessageDialog(null, e);
                        } catch (SQLException ex) {
                            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        opensigninPanel();

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Please enter the exact code sent to you");
                    }
                }
                catch (Exception ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

                //JOptionPane.showMessageDialog(null, "The email is valid", "Good!", JOptionPane.INFORMATION_MESSAGE);

            }
        }

    }//GEN-LAST:event_sigupConfirmActionPerformed

    private void fullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullnameActionPerformed

    private void home1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home1MouseClicked
        openGravePanel();
    }//GEN-LAST:event_home1MouseClicked

    private void burriedInOneDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burriedInOneDateActionPerformed
        this.dispose();
        burriedInOneDatePermanent b = new burriedInOneDatePermanent();
        b.setVisible(true);
    }//GEN-LAST:event_burriedInOneDateActionPerformed

    private void diedInSameCauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diedInSameCauseActionPerformed
        this.dispose();
        diedInSameCausePermanent b = new diedInSameCausePermanent();
        b.setVisible(true);
    }//GEN-LAST:event_diedInSameCauseActionPerformed

    private void peopleInSameGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peopleInSameGenderActionPerformed
        this.dispose();
        peopleInSameGender b = new peopleInSameGender();
        b.setVisible(true);
    }//GEN-LAST:event_peopleInSameGenderActionPerformed

    private void burriedInSamePlotSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burriedInSamePlotSizeActionPerformed
        this.dispose();
        InSamePlotSizePermanent b = new InSamePlotSizePermanent();
        b.setVisible(true);
    }//GEN-LAST:event_burriedInSamePlotSizeActionPerformed

    private void burriedInOnePlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burriedInOnePlotActionPerformed
        this.dispose();
        burriedInOnePlotPermanent b = new burriedInOnePlotPermanent();
        b.setVisible(true);
    }//GEN-LAST:event_burriedInOnePlotActionPerformed

    private void boughtByOneOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boughtByOneOwnerActionPerformed
        this.dispose();
        boughtByOneOwnerPermanent b = new boughtByOneOwnerPermanent();
        b.setVisible(true);
    }//GEN-LAST:event_boughtByOneOwnerActionPerformed

    private void burriedInOneDate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burriedInOneDate1ActionPerformed
        this.dispose();
        PeopleBurriedInSameYearPermanent b = new PeopleBurriedInSameYearPermanent();
        b.setVisible(true);
    }//GEN-LAST:event_burriedInOneDate1ActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        this.dispose();
        MainMenu b = new MainMenu();
        b.setVisible(true);
    }//GEN-LAST:event_BackActionPerformed

    private void wholeTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wholeTableActionPerformed
        this.dispose();
        ShowWholeTable b = new ShowWholeTable();
        b.setVisible(true);
    }//GEN-LAST:event_wholeTableActionPerformed

    private void permanentTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permanentTableActionPerformed
        this.dispose();
        GeneralGraveTable b = new GeneralGraveTable();
        b.setVisible(true);
    }//GEN-LAST:event_permanentTableActionPerformed

    private void generalTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalTableActionPerformed
        this.dispose();
        permanentGraveTable b = new permanentGraveTable();
        b.setVisible(true);
    }//GEN-LAST:event_generalTableActionPerformed

    private void tablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablesMouseClicked
        openTablePanel();
    }//GEN-LAST:event_tablesMouseClicked

    private void statisticsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statisticsMouseClicked
        openStatisticsPanel();
    }//GEN-LAST:event_statisticsMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        opensigninPanel();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void soldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soldActionPerformed
       
    }//GEN-LAST:event_soldActionPerformed
    int x = 210;
    int a = 0;
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JPasswordField LoginPassword;
    private javax.swing.JTextField LoginUserName;
    public javax.swing.JLabel about;
    private javax.swing.JTextField available;
    private javax.swing.JLabel background;
    private javax.swing.JButton boughtByOneOwner;
    private javax.swing.JButton burriedInOneDate;
    private javax.swing.JButton burriedInOneDate1;
    private javax.swing.JButton burriedInOnePlot;
    private javax.swing.JButton burriedInSamePlotSize;
    public javax.swing.JPasswordField confirmpassword;
    public javax.swing.JTextField contactno;
    private javax.swing.JButton diedInSameCause;
    public javax.swing.JTextField email;
    public javax.swing.JTextField fullname;
    private javax.swing.JButton generalTable;
    private javax.swing.JPanel gmsPanel;
    private javax.swing.JPanel gravePanel;
    private javax.swing.JLabel home1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    public javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    public javax.swing.JPanel leftPanel;
    private javax.swing.JLabel logout;
    public javax.swing.JPanel mainPanel;
    public javax.swing.JPanel menuPanel;
    private javax.swing.JButton owner;
    public javax.swing.JPasswordField password;
    private javax.swing.JButton peopleInSameGender;
    private javax.swing.JComboBox<String> permanentCombo;
    private javax.swing.JButton permanentTable;
    public javax.swing.JPanel rightPanel;
    private com.toedter.calendar.JDateChooser setRecycleDate;
    private javax.swing.JButton signin;
    public javax.swing.JPanel signinPanel;
    private javax.swing.JButton signup;
    public javax.swing.JPanel signupPanel;
    private javax.swing.JButton sigupConfirm;
    private javax.swing.JTextField sold;
    private javax.swing.JLabel statistics;
    private javax.swing.JPanel statisticsPanel;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel tables;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JLabel titleLabel2;
    private javax.swing.JTextField total;
    public javax.swing.JTextField username;
    public javax.swing.JPanel welcomePanel;
    private javax.swing.JButton wholeTable;
    // End of variables declaration//GEN-END:variables
    private void labelcolor(JLabel label){
        label.setBackground(new java.awt.Color(53,162,107));
    }
    
    private void resetlabelcolor(JLabel label){
        label.setBackground(new java.awt.Color(54,70,78));
    }

    void SetVisible(boolean b) {
       new MainMenu().setVisible(true);// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class User {

        public User() {
        }

        private void SetVisible(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void SetData(MainMenu aThis) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void pack() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void SetLocationRelativeTo(Object object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class JFrame {

        public JFrame() {
        }
    }
}
