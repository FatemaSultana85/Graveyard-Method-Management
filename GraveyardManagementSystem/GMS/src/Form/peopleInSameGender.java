
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
public class peopleInSameGender extends javax.swing.JFrame {
 public peopleInSameGender(){
     initComponents(); 
        
        //table1.setAutoResizeMode(table1.AUTO_RESIZE_OFF);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        for(int i=0;i<1;i++){
        TableColumn col = table1.getColumnModel().getColumn(i);
        col.setPreferredWidth(100);}
        show_table();
    }
public ArrayList<peopleInSameGenderPer>corpseList(){
        ArrayList<peopleInSameGenderPer> corpseList = new ArrayList<>();
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
         
            String query1 = " select gender,sum(t.NumberOfPerson) as NumberOfPerson from  \n" +
                            "((SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY corpseGender)\n" +
                            " union all\n" +
                            "(SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM GeneralGrave1 GROUP BY corpseGender)) t\n" +
                            " GROUP BY gender";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            peopleInSameGenderPer corpse;
            while(rs.next())
            {
               corpse = new peopleInSameGenderPer(rs.getString("gender"),rs.getInt("NumberOfPerson"));
                      
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
    ArrayList<peopleInSameGenderPer> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)table1.getModel();
    Object[] row = new Object[2];
    for(int i = 0; i<list.size(); i++)
    {
        row[0] = list.get(i).getgender();
        row[1] = list.get(i).getNumberOfPerson();
        
                                
        model.addRow(row);
        
    }
   
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        gender = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(255, 255, 255));

        gender.setBackground(new java.awt.Color(255, 255, 255));
        gender.setText("Show in chart");
        gender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Number of People in Same Gender");

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Gender", "Number of Person"
            }
        ));
        jScrollPane2.setViewportView(table1);

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
                        .addGap(173, 173, 173)
                        .addComponent(jLabel1))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(back)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderActionPerformed
        try {
            String searchQuery=" select gender,sum(t.NumberOfPerson) as NumberOfPerson from  \n" +
                                "((SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY corpseGender)\n" +
                                " union all\n" +
                                "(SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM GeneralGrave1 GROUP BY corpseGender)) t\n" +
                                " GROUP BY gender";
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, searchQuery);
            JFreeChart chart=ChartFactory.createBarChart("Number of People burried in same gender","corpseGender","NumberOfPerson",dataset, PlotOrientation.VERTICAL,false, true, true);
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
        
    }//GEN-LAST:event_genderActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
       this.dispose();
        MainMenu b = new MainMenu();
        b.setVisible(true);
        b.openStatisticsPanel();
        b.openmenuPanel();
    }//GEN-LAST:event_backActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        try {
            String searchQuery=" select gender,sum(t.NumberOfPerson) as NumberOfPerson from  \n" +
                                "((SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY corpseGender)\n" +
                                " union all\n" +
                                "(SELECT corpseGender as gender,COUNT (corpseGender) AS NumberOfPerson FROM GeneralGrave1 GROUP BY corpseGender)) t\n" +
                                " GROUP BY gender";
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, searchQuery);
            JFreeChart chart=ChartFactory.createBarChart("Number of People burried in same gender","corpseGender","NumberOfPerson",dataset, PlotOrientation.VERTICAL,false, true, true);
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
            final File file1=new File("Number of people died in same gender.png");
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
            java.util.logging.Logger.getLogger(peopleInSameGender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(peopleInSameGender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(peopleInSameGender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(peopleInSameGender.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new peopleInSameGender().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel main;
    private javax.swing.JButton save;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
