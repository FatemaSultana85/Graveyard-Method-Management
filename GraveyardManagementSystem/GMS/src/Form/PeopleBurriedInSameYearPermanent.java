
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


public class PeopleBurriedInSameYearPermanent extends javax.swing.JFrame {

    public PeopleBurriedInSameYearPermanent() {
        initComponents();
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        //table1.setAutoResizeMode(table1.AUTO_RESIZE_OFF);
        for(int i=0;i<1;i++){
        TableColumn col = table1.getColumnModel().getColumn(i);
        col.setPreferredWidth(100);}
        show_table();
    }
     public ArrayList<peopleBurriedInSameYearPer>corpseList(){
        ArrayList<peopleBurriedInSameYearPer> corpseList = new ArrayList<>();
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
         
            String query1 = " select Year,sum(t.NumberOfPerson) as NumberOfPerson from  \n" +
                            " ((SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY DATEPART(YEAR,burriedDate))\n" +
                            " union all\n" +
                            "(SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM GeneralGrave1 GROUP BY DATEPART(YEAR,burriedDate))) t\n" +
                            " GROUP BY Year";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            peopleBurriedInSameYearPer corpse;
            while(rs.next())
            {
               corpse = new peopleBurriedInSameYearPer(rs.getString("Year"),rs.getInt("NumberOfPerson"));
                      
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
    ArrayList<peopleBurriedInSameYearPer> list = corpseList();
    DefaultTableModel model = (DefaultTableModel)table1.getModel();
    Object[] row = new Object[2];
    for(int i = 0; i<list.size(); i++)
    {
        row[0] = list.get(i).getYear();
        row[1] = list.get(i).getNumberOfPerson();
        
                                
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

        main = new javax.swing.JPanel();
        burriedDate1 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        save1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setForeground(new java.awt.Color(255, 255, 255));

        burriedDate1.setBackground(new java.awt.Color(255, 255, 255));
        burriedDate1.setText("Show in chart");
        burriedDate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        burriedDate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burriedDate1ActionPerformed(evt);
            }
        });

        table1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Year", "Numbe of People"
            }
        ));
        table1.setFocusable(false);
        table1.setRowHeight(20);
        table1.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane12.setViewportView(table1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Number of People Burried in Same Year");

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
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
            .addGroup(mainLayout.createSequentialGroup()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel1))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(burriedDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(save1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(burriedDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back)
                .addContainerGap(41, Short.MAX_VALUE))
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

    private void burriedDate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burriedDate1ActionPerformed
        try {
            String searchQuery=" select Year,sum(t.NumberOfPerson) as NumberOfPerson from  \n" +
                                " ((SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY DATEPART(YEAR,burriedDate))\n" +
                                " union all\n" +
                                "(SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM GeneralGrave1 GROUP BY DATEPART(YEAR,burriedDate))) t\n" +
                                " GROUP BY Year";
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, searchQuery);
            JFreeChart chart=ChartFactory.createBarChart("People burried in every year","year","Number of Person",dataset, PlotOrientation.VERTICAL,false, true, true);
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
    }//GEN-LAST:event_burriedDate1ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        this.dispose();
        MainMenu b = new MainMenu();
        b.setVisible(true);
        b.openStatisticsPanel();
        b.openmenuPanel();
    }//GEN-LAST:event_backActionPerformed

    private void save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save1ActionPerformed
        try {
             String searchQuery=" select Year,sum(t.NumberOfPerson) as NumberOfPerson from  \n" +
                                " ((SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM Permanent_CorpseInfo GROUP BY DATEPART(YEAR,burriedDate))\n" +
                                " union all\n" +
                                "(SELECT DATEPART(YEAR,burriedDate) As Year ,COUNT(DATEPART(YEAR,burriedDate)) AS NumberOfPerson FROM GeneralGrave1 GROUP BY DATEPART(YEAR,burriedDate))) t\n" +
                                " GROUP BY Year";
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Graveyard;selectMethod=cursor", "sa", "123456");
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, searchQuery);
            JFreeChart chart=ChartFactory.createBarChart("People burried in every year","year","Number of Person",dataset, PlotOrientation.VERTICAL,false, true, true);
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
                final File file1=new File("People burried in every year.png");
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
            java.util.logging.Logger.getLogger(PeopleBurriedInSameYearPermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PeopleBurriedInSameYearPermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PeopleBurriedInSameYearPermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PeopleBurriedInSameYearPermanent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PeopleBurriedInSameYearPermanent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton burriedDate1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JPanel main;
    private javax.swing.JButton save1;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
