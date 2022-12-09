
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
public class InSamePlotSizePermanent extends javax.swing.JFrame {

public InSamePlotSizePermanent(){
     initComponents(); 
        //table1.setAutoResizeMode(table1.AUTO_RESIZE_OFF);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        for(int i=0;i<1;i++){
        TableColumn col = table1.getColumnModel().getColumn(i);
        col.setPreferredWidth(100);}
        show_table();
    }
public ArrayList<inSamePlotSizePer>corpseList(){
        ArrayList<inSamePlotSizePer> corpseList = new ArrayList<>();
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
         
            String query1 = "select graveSize,sum(t.NumberOfPerson) as NumberOfPerson from  ((SELECT permanentGraveSize as graveSize ,COUNT (permanentGraveSize) AS NumberOfPerson\n" +
                            " FROM Permanent_CorpseInfo GROUP BY permanentGraveSize)\n" +
                            " union all\n" +
                            "(SELECT generalGraveSize as graveSize ,COUNT (generalGraveSize) AS NumberOfPerson\n" +
                            " FROM GeneralGrave1 GROUP BY generalGraveSize )) t\n" +
                            " GROUP BY graveSize";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            inSamePlotSizePer corpse;
            while(rs.next())
            {
               corpse = new inSamePlotSizePer(rs.getString("graveSize"),rs.getInt("NumberOfPerson"));
                      
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
    ArrayList<inSamePlotSizePer> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)table1.getModel();
    Object[] row = new Object[2];
    for(int i = 0; i<list.size(); i++)
    {
        row[0] = list.get(i).getgraveSize();
        row[1] = list.get(i).getNumberOfPerson();
        
                                
        model.addRow(row);
        
    }
   
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        size = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        save1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(255, 255, 255));

        table1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plot Size", "Number of people"
            }
        ));
        table1.setFocusable(false);
        table1.setRowHeight(20);
        table1.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane12.setViewportView(table1);

        size.setBackground(new java.awt.Color(255, 255, 255));
        size.setText("Show in Chart");
        size.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Number of People Burried in Same Plot Size");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        save1.setBackground(new java.awt.Color(255, 255, 255));
        save1.setText("Save chart");
        save1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
            .addGroup(mainLayout.createSequentialGroup()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(size, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(save1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(jButton1))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(size, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButton1)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeActionPerformed
        try {
            String searchQuery="select graveSize,sum(t.NumberOfPerson) as NumberOfPerson from  ((SELECT permanentGraveSize as graveSize ,COUNT (permanentGraveSize) AS NumberOfPerson\n" +
                                " FROM Permanent_CorpseInfo GROUP BY permanentGraveSize)\n" +
                                " union all\n" +
                                "(SELECT generalGraveSize as graveSize ,COUNT (generalGraveSize) AS NumberOfPerson\n" +
                                " FROM GeneralGrave1 GROUP BY generalGraveSize )) t\n" +
                                " GROUP BY graveSize";
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, searchQuery);
            JFreeChart chart=ChartFactory.createBarChart("Number of People burried in same size","Grave Size","Number of person",dataset, PlotOrientation.VERTICAL,false, true, true);
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
    }//GEN-LAST:event_sizeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      this.dispose();
        MainMenu b = new MainMenu();
        b.setVisible(true);
        b.openStatisticsPanel();
        b.openmenuPanel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save1ActionPerformed
        try {
             String searchQuery="select graveSize,sum(t.NumberOfPerson) as NumberOfPerson from  ((SELECT permanentGraveSize as graveSize ,COUNT (permanentGraveSize) AS NumberOfPerson\n" +
                                " FROM Permanent_CorpseInfo GROUP BY permanentGraveSize)\n" +
                                " union all\n" +
                                "(SELECT generalGraveSize as graveSize ,COUNT (generalGraveSize) AS NumberOfPerson\n" +
                                " FROM GeneralGrave1 GROUP BY generalGraveSize )) t\n" +
                                " GROUP BY graveSize";
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, searchQuery);
            JFreeChart chart=ChartFactory.createBarChart("Number of People burried in same grave size","Grave Size","Number of person",dataset, PlotOrientation.VERTICAL,false, true, true);
            BarRenderer renderer=null;
            CategoryPlot plot=null;
            renderer=new BarRenderer();
            ChartFrame frame= new ChartFrame("Query",chart);

            CategoryPlot p = chart.getCategoryPlot();
            final NumberAxis rangeAxis = (NumberAxis) p.getRangeAxis();
            rangeAxis.setTickUnit(new NumberTickUnit(1));
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            //frame.setVisible(true);
            //frame.setSize(1300,700);

            try {
                final ChartRenderingInfo info=new ChartRenderingInfo(new StandardEntityCollection());
                final File file1=new File("Number of people died in same grave Size.png");
                ChartUtilities.saveChartAsPNG(file1,chart, 1300, 700, info);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Got an exception!");
                System.err.println(e.getMessage());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Got an exception!");
            System.err.println(e.getMessage());
        }

    }//GEN-LAST:event_save1ActionPerformed

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
            java.util.logging.Logger.getLogger(InSamePlotSizePermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InSamePlotSizePermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InSamePlotSizePermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InSamePlotSizePermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InSamePlotSizePermanent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JPanel main;
    private javax.swing.JButton save1;
    private javax.swing.JButton size;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
