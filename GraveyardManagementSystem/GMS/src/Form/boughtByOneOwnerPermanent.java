/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.ui.RefineryUtilities;
public class boughtByOneOwnerPermanent extends javax.swing.JFrame {

 public boughtByOneOwnerPermanent(){
     initComponents(); 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //table1.setAutoResizeMode(table1.AUTO_RESIZE_OFF);
        for(int i=0;i<2;i++){
        TableColumn col = table1.getColumnModel().getColumn(i);
        col.setPreferredWidth(100);}
        show_table();
    }
public ArrayList<boughtByOneOwnerPer>corpseList(){
        ArrayList<boughtByOneOwnerPer> corpseList = new ArrayList<>();
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
         
            String query1 = "SELECT ownerId ,ownerName,COUNT (ownerId) AS NumberOfPerson FROM OwnerInfo GROUP BY ownerId,ownerName ORDER BY ownerId;";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            boughtByOneOwnerPer corpse;
            while(rs.next())
            {
               corpse = new boughtByOneOwnerPer(rs.getInt("ownerId"),rs.getString("ownerName"),rs.getInt("NumberOfPerson"));
                      
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
    ArrayList<boughtByOneOwnerPer> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)table1.getModel();
    Object[] row = new Object[3];
    for(int i = 0; i<list.size(); i++)
    {
        row[0] = list.get(i).getownerId();
        row[1] = list.get(i).getownerName();
        row[2] = list.get(i).getNumberOfPerson();
        
                                
        model.addRow(row);
        
    }
   
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        ownerplot = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        save = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(255, 255, 255));

        table1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OwnerId", "Owner Name", "Number of plot"
            }
        ));
        table1.setRowHeight(20);
        table1.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane12.setViewportView(table1);

        ownerplot.setBackground(new java.awt.Color(255, 255, 255));
        ownerplot.setText("Show in Chart");
        ownerplot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        ownerplot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerplotActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Number of Plot Bought by Same Owner");

        back.setBackground(new java.awt.Color(255, 255, 255));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        save.setBackground(new java.awt.Color(255, 255, 255));
        save.setText("Save chart");
        save.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(ownerplot, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel1)))
                .addContainerGap(203, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ownerplot, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(back)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ownerplotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerplotActionPerformed
       try {
            String searchQuery="SELECT ownerId,COUNT (ownerId) AS NumberOfPerson FROM OwnerInfo GROUP BY ownerId ORDER BY ownerId;";
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, searchQuery);
            JFreeChart chart=ChartFactory.createBarChart("Number Of plots bought by one Owner","owner Id","Number of plot",dataset, PlotOrientation.VERTICAL,false, true, true);
            BarRenderer renderer=null;
            CategoryPlot plot=null;
            renderer=new BarRenderer();
            ChartFrame frame= new ChartFrame("Query",chart);

            CategoryPlot p = chart.getCategoryPlot();
            final NumberAxis rangeAxis = (NumberAxis) p.getRangeAxis();
            rangeAxis.setTickUnit(new NumberTickUnit(1));
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            frame.setVisible(true);
            frame.setSize(1300,700);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_ownerplotActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
       this.dispose();
        MainMenu b = new MainMenu();
        b.setVisible(true);
        b.openStatisticsPanel();
        b.openmenuPanel();
    }//GEN-LAST:event_backActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        try {
            String searchQuery="SELECT ownerId,COUNT (ownerId) AS NumberOfPerson FROM OwnerInfo GROUP BY ownerId ORDER BY ownerId;";
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, searchQuery);
            JFreeChart chart=ChartFactory.createBarChart("Number Of plots bought by one Owner","owner Id","Number of plot",dataset, PlotOrientation.VERTICAL,false, true, true);
            BarRenderer renderer=null;
            CategoryPlot plot=null;
            renderer=new BarRenderer();
            ChartFrame frame= new ChartFrame("Query",chart);

            CategoryPlot p = chart.getCategoryPlot();
            final NumberAxis rangeAxis = (NumberAxis) p.getRangeAxis();
            rangeAxis.setTickUnit(new NumberTickUnit(1));
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());


            try {
                final ChartRenderingInfo info=new ChartRenderingInfo(new StandardEntityCollection());
                final File file1=new File("Number Of plots bought by one Owner.png");
                ChartUtilities.saveChartAsPNG(file1,chart, 1300, 700, info);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Got an exception!");
                System.err.println(e.getMessage());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }

    }//GEN-LAST:event_saveActionPerformed

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
            java.util.logging.Logger.getLogger(boughtByOneOwnerPermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(boughtByOneOwnerPermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(boughtByOneOwnerPermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(boughtByOneOwnerPermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new boughtByOneOwnerPermanent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JPanel main;
    private javax.swing.JButton ownerplot;
    private javax.swing.JButton save;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
