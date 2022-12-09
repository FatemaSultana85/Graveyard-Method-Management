
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

public class PaymentInfo extends javax.swing.JFrame {

    /**
     * Creates new form PaymentInfo
     */
    public PaymentInfo() {
      initComponents();
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       paymentTable.getTableHeader().setBackground(new Color(32,134,203));
        paymentTable.getTableHeader().setForeground(new Color(255,255,255));
      paymentTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        paymentTable.getTableHeader().setOpaque(false);
       paymentTable.setRowHeight(25);
        
       //paymentTable.setAutoResizeMode(paymentTable.AUTO_RESIZE_OFF);
        TableColumnModel col = paymentTable.getColumnModel();
        for(int i=0;i<5;i++)
        {
            col.getColumn(i).setPreferredWidth(150);
        }
        //setExtendedState(getExtendedState()|MainMenu.MAXIMIZED_BOTH);  //sets to full screen
        show_payment();
    }
    public PaymentInfo(String id){   
        initComponents();
        ownerId.setText(id);
        
  
    }
public ArrayList<pay>payList(){
        ArrayList<pay> payList = new ArrayList<>();
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
         
            String query1 = "SELECT * FROM paymentInfo";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            pay pa;
            while(rs.next())
            {
              pa = new pay(rs.getInt("paymentid"),rs.getInt("serialNo"),
                      rs.getInt("ownerId"),rs.getInt("generalGraveId"),rs.getInt("fee"));
                      
              payList.add(pa);
            }
            //connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(left, e);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(left,ex);
        }
     return payList;
           
    }
      public void show_payment(){
    ArrayList<pay> list = payList();
    DefaultTableModel model1 = (DefaultTableModel)paymentTable.getModel();
    Object[] row1 = new Object[5];
    for(int i = 0; i<list.size(); i++)
    {
        row1[0] = list.get(i).getpaymentid();
        row1[1] = list.get(i).getserialNo();
        row1[2] = list.get(i).getownerId();
        row1[3] = list.get(i).getgeneralGraveId();
        row1[4] = list.get(i).getfee();
      
        model1.addRow(row1);
    }
    
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        left = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        plotId = new javax.swing.JTextField();
        ownerId = new javax.swing.JTextField();
        fee = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        next = new javax.swing.JButton();
        add = new javax.swing.JButton();
        purchase = new javax.swing.JComboBox<>();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        left.setBackground(new java.awt.Color(255, 255, 255));
        left.setForeground(new java.awt.Color(153, 153, 153));

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel121.setText("Payment Information");

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel111.setText("Purchase Id");

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel112.setText("Owner Id");

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel113.setText("Plot No:");

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel115.setText("Fee");

        plotId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        plotId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotIdActionPerformed(evt);
            }
        });

        ownerId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        ownerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerIdActionPerformed(evt);
            }
        });
        ownerId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ownerIdKeyReleased(evt);
            }
        });

        fee.setText("5000");
        fee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        paymentTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payment Id", "Serial Id", "Owner Id", "Plot Id", "Fee"
            }
        ));
        paymentTable.setFocusable(false);
        paymentTable.setGridColor(new java.awt.Color(255, 255, 255));
        paymentTable.setRowHeight(20);
        paymentTable.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jScrollPane12.setViewportView(paymentTable);

        next.setBackground(new java.awt.Color(255, 255, 255));
        next.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        add.setBackground(new java.awt.Color(255, 255, 255));
        add.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        purchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseActionPerformed(evt);
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

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                        .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(294, 294, 294))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(444, 444, 444))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel111)
                            .addComponent(jLabel112))
                        .addGap(33, 33, 33)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ownerId)
                            .addComponent(purchase, 0, 237, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel113))
                        .addGap(58, 58, 58)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fee, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(plotId))))
                .addGap(38, 38, 38))
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel121)
                .addGap(66, 66, 66)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(jLabel113)
                    .addComponent(plotId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchase, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(ownerId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fee, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel115))
                .addGap(18, 18, 18)
                .addComponent(add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(next))
                .addGap(25, 25, 25))
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
                .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
      
        String id=ownerId.getText();
        
        new ownerReceipt(id).setVisible(true);
    }//GEN-LAST:event_nextActionPerformed

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

            String sql = "INSERT INTO paymentInfo(serialNo,ownerId,generalGraveId,fee) values(?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            String PurchaseId;
            PurchaseId = purchase.getSelectedItem().toString();
            pst.setString(1,PurchaseId);
            //pst.setString(1,serialNo.getText());
            pst.setString(2,ownerId.getText());
            pst.setString(3,plotId.getText());
            pst.setString(4,fee.getText());
         

            pst.executeUpdate();

            DefaultTableModel model1= (DefaultTableModel)paymentTable.getModel();

            model1.setRowCount(0);

            show_payment();
            JOptionPane.showMessageDialog(left,"Inserted successfully");

            connection.close();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(left, e);
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(left,ex);
        }

    }//GEN-LAST:event_addActionPerformed

    private void purchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseActionPerformed
              try{
             if(!plotId.getText().isEmpty()){
                       plotId.setText("");
                }
             else{
                  String value = purchase.getSelectedItem().toString();
       
        String searchQuery = "select generalGraveId from OwnerInfo where serialNo="+value+" and availablility='empty'";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String purchase=rs.getString("generalGraveId");
                plotId.setText(purchase);
            }
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
             }
         } catch (Exception ignore) { }
           
       
    }//GEN-LAST:event_purchaseActionPerformed

    private void ownerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerIdActionPerformed
          try{
             if(purchase.getItemCount() != 0){
                             purchase.removeAllItems();
                      }
         } catch (Exception ignore) { } 
        String value = ownerId.getText();
        String searchQuery = "  select serialNo from OwnerInfo where ownerId="+value+" and availablility='empty'";
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            PreparedStatement pst = conn.prepareStatement(searchQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String purchaseId=rs.getString("serialNo");
                purchase.addItem(purchaseId);
            }
        } catch (Exception e) { 
        JOptionPane.showMessageDialog(null,"Got an exception!");
        System.err.println(e.getMessage()); 
    } 
    }//GEN-LAST:event_ownerIdActionPerformed

    private void plotIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotIdActionPerformed
       
    }//GEN-LAST:event_plotIdActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
      this.dispose();
        OwnerInfo pay = new OwnerInfo();
        pay.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void ownerIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ownerIdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ownerIdKeyReleased

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
            java.util.logging.Logger.getLogger(PaymentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton back;
    private javax.swing.JTextField fee;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JPanel left;
    private javax.swing.JButton next;
    private javax.swing.JTextField ownerId;
    private javax.swing.JTable paymentTable;
    private javax.swing.JTextField plotId;
    private javax.swing.JComboBox<String> purchase;
    // End of variables declaration//GEN-END:variables
}
