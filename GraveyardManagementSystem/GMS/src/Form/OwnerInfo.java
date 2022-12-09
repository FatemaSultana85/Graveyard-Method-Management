
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class OwnerInfo extends javax.swing.JFrame {
    
    public OwnerInfo() {
        
       initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       informationOfOwner.getTableHeader().setBackground(new Color(32,134,203));
        informationOfOwner.getTableHeader().setForeground(new Color(255,255,255));
        informationOfOwner.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        informationOfOwner.getTableHeader().setOpaque(false);
       informationOfOwner.setRowHeight(25);
        
       informationOfOwner.setAutoResizeMode(informationOfOwner.AUTO_RESIZE_OFF);
        TableColumnModel col = informationOfOwner.getColumnModel();
        for(int i=0;i<9;i++)
        {
            col.getColumn(i).setPreferredWidth(150);
        }
       
       // setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
        show_owner();
        show_available();
    }
    public ArrayList<ownerId>availableList(){
        ArrayList<ownerId> availableList = new ArrayList<>();
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
            String query1 = "SELECT generalGraveId FROM OwnerInfo order by generalGraveId";
                     
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            ownerId available;
            while(rs.next())
            {
                //opposite has been done on 9/8/20
                //that is  availablegraves shows unavailable graves
              available = new ownerId(rs.getInt("generalGraveId"));
                      
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
    ArrayList<ownerId> list = availableList();
    int size = list.size();
    int totalNumberOfPlots = 4000;
    for(int j = 2001;j<=totalNumberOfPlots; j++)
    {
        availableCombo.addItem(Integer.toString(j));     

    }
    for(int i = 0; i<size; i++)
    {
         availableCombo.removeItem(list.get(i).getgeneralGraveId());     
         
    }
   
    }
   
      public ArrayList<Owner>OwnerList(){
        ArrayList<Owner> OwnerList = new ArrayList<>();
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
         
            String query1 = "SELECT * FROM OwnerInfo";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Owner owner;
            while(rs.next())
            {
              owner = new Owner(rs.getInt("serialNo"),rs.getInt("ownerId"),
                      rs.getString("ownerName"),rs.getInt("ownerPhoneNumber"),rs.getString("ownerNidOrBirthCertificate"),
                      rs.getString("ownerAddress"),rs.getString("sellingStatus"),rs.getInt("generalGraveId"),rs.getString("availablility"));
                      
              OwnerList.add(owner);
            }
            //connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(left, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(left,ex);
        }
     return OwnerList;
           
    }
      public void show_owner(){
    ArrayList<Owner> list = OwnerList();
    DefaultTableModel model1 = (DefaultTableModel)informationOfOwner.getModel();
    Object[] row1 = new Object[9];
    for(int i = 0; i<list.size(); i++)
    {
        row1[0] = list.get(i).getserialNo();
        row1[1] = list.get(i).getownerId();
        row1[2] = list.get(i).getownerName();
        row1[3] = list.get(i).getownerPhoneNumber();
        row1[4] = list.get(i).getownerNidOrBirthCertificate();
        row1[5] = list.get(i).getownerAddress();
        row1[6] = list.get(i).getsellingStatus();
        row1[7] = list.get(i).getgeneralGraveId(); 
        row1[8] = list.get(i).getavailablility();
        model1.addRow(row1);
    }
    
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        left = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        ownerNidOrBirthCertificate = new javax.swing.JTextField();
        ownerContactNo = new javax.swing.JTextField();
        ownerName = new javax.swing.JTextField();
        ownerAddress = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jLabel111 = new javax.swing.JLabel();
        ownerId = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        informationOfOwner = new javax.swing.JTable();
        next = new javax.swing.JButton();
        back = new javax.swing.JButton();
        status = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        availableCombo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        left.setBackground(new java.awt.Color(255, 255, 255));

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel121.setText("Owner Information");

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel105.setText("Owner Name:");

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel106.setText("Owner Contact No:");

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel107.setText("Owner Address:");

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel109.setText("Owner NID/Birth Certificate:");

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel110.setText("Plot Id:");

        ownerNidOrBirthCertificate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        ownerContactNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        ownerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        ownerAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        add.setBackground(new java.awt.Color(255, 255, 255));
        add.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel111.setText("ownerId");

        ownerId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        informationOfOwner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        informationOfOwner.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial Id", "Owner Id", "Owner Name", "Owner Contact No", "Owner NID/Birth Certificate", "Status", " Owner Address", "plot No", "Availability"
            }
        ));
        informationOfOwner.setFocusable(false);
        informationOfOwner.setGridColor(new java.awt.Color(255, 255, 255));
        informationOfOwner.setRowHeight(20);
        informationOfOwner.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane12.setViewportView(informationOfOwner);

        next.setBackground(new java.awt.Color(255, 255, 255));
        next.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
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

        status.setText("Sold");
        status.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel114.setText("Status:");

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel121)
                .addGap(340, 340, 340))
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106)
                            .addComponent(jLabel107)
                            .addComponent(jLabel111)
                            .addComponent(jLabel114))
                        .addGap(74, 74, 74)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ownerContactNo, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                .addComponent(ownerAddress)
                                .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                            .addComponent(ownerId, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(109, 109, 109)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel109)
                            .addComponent(jLabel110)
                            .addComponent(jLabel105))
                        .addGap(18, 18, 18)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ownerName, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(ownerNidOrBirthCertificate, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(availableCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(450, 450, 450))
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel121)
                .addGap(33, 33, 33)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel111)
                            .addComponent(ownerId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel105)
                            .addComponent(ownerName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel106)
                            .addComponent(ownerContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel109)
                            .addComponent(ownerNidOrBirthCertificate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel107)
                            .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ownerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel110)
                                .addComponent(availableCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel114))
                .addGap(40, 40, 40)
                .addComponent(add)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(next))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

            String sql = "INSERT INTO OwnerInfo(ownerId,ownerName,ownerPhoneNumber,ownerNidOrBirthCertificate,ownerAddress,sellingStatus,generalGraveId) values(?,?,?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,ownerId.getText());
            pst.setString(2,ownerName.getText());
            pst.setString(3,ownerContactNo.getText());
            pst.setString(4,ownerNidOrBirthCertificate.getText());
            pst.setString(5,status.getText());
            pst.setString(6,ownerAddress.getText());
           
           
            String graveId;
            graveId = availableCombo.getSelectedItem().toString();
            pst.setString(7,graveId);

            pst.executeUpdate();
            show_available();

            DefaultTableModel model1= (DefaultTableModel)informationOfOwner.getModel();
            
            model1.setRowCount(0);
            
            show_owner();
            JOptionPane.showMessageDialog(left,"Inserted successfully");

            connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(left, e);
        } catch (SQLException ex) {

          JOptionPane.showMessageDialog(left,ex);
        }
            
        
        
    }//GEN-LAST:event_addActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
       
      
         String id=ownerId.getText();
        
        new PaymentInfo(id).setVisible(true);
    }//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
          this.setVisible(false);
      MainMenu mm = new MainMenu();
      mm.setVisible(true);
      mm.openGravePanel();
      mm.openmenuPanel();
       
    }//GEN-LAST:event_backActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OwnerInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JComboBox<String> availableCombo;
    private javax.swing.JButton back;
    private javax.swing.JTable informationOfOwner;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JPanel left;
    private javax.swing.JButton next;
    private javax.swing.JTextField ownerAddress;
    private javax.swing.JTextField ownerContactNo;
    private javax.swing.JTextField ownerId;
    private javax.swing.JTextField ownerName;
    private javax.swing.JTextField ownerNidOrBirthCertificate;
    private javax.swing.JTextField status;
    // End of variables declaration//GEN-END:variables
}
