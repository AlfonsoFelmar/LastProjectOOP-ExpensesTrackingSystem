/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;
import expenseCategories.ExpenseCategoryForm;
import com.orsoncharts.plot.PiePlot3D;
import java.awt.BorderLayout;
import JDBC.DBConn;
import main.DashboardForm;
import main.QueryInterfce;
import java.util.Date;
import javax.swing.Timer;
import expenses.MainFrame1;
import income.IncomeForm;
import incomeCategories.IncomeCategoryForm;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.LoginForm;
import main.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author sabinmaharjan
 */
public class ReportForm extends javax.swing.JFrame implements QueryInterfce{
    
    Statement stmt = null;    
    Statement stmtIncome = null;
    Statement stmtIncomeMonthly = null;
    Statement stmtExpenseMonthly = null;
    Statement stmtIncomeYearly = null;
    Statement stmtExpenseYearly = null;

    ResultSet rs = null;
    ResultSet rsIncome = null;
    ResultSet rsIncomeMonthly = null;
    ResultSet rsExpenseMonthly = null;
    ResultSet rsExpenseYearly = null;
    ResultSet rsIncomeYearly = null;

    DBConn conn=new DBConn();
    Connection con=conn.getConnection();
    /**
     * Creates new form CategoryForm
     */
    public ReportForm() {
        initComponents();
        showData();
        requestFocus();
//        jFreeChartDisplay();
//        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
 
        

    }

    
   
//    calculate profit and loss and show total expense and total income
    public void showData(){
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat current_date=new SimpleDateFormat("Y-MM-dd");
        System.out.println("now"+current_date.format(calendar.getTime()));
        String queryExpense="select sum(cost) from expenses where date='"+current_date.format(calendar.getTime())+"' and user_id='"+User.getUser_session_id()+"'"; 
        String queryIncome="select sum(cost) from incomes where date='"+current_date.format(calendar.getTime())+"' and user_id='"+User.getUser_session_id()+"'"; 
        
        String queryIncomeMonthly="select sum(cost) from incomes where month(date)='"+(calendar.get(Calendar.MONTH)+1)+"' and user_id='"+User.getUser_session_id()+"'"; 
        String queryExpenseMonthly="select sum(cost) from expenses where month(date)='"+(calendar.get(Calendar.MONTH)+1)+"' and user_id='"+User.getUser_session_id()+"'"; 
        
        String queryIncomeYearly="select sum(cost) from incomes where year(date)='"+(calendar.get(Calendar.YEAR))+"' and user_id='"+User.getUser_session_id()+"'"; 
        String queryExpenseYearly="select sum(cost) from expenses where year(date)='"+(calendar.get(Calendar.YEAR))+"' and user_id='"+User.getUser_session_id()+"'"; 
   
        System.out.println("current monthly "+calendar.get(Calendar.MONTH)+1);     
        System.out.println("current yearly "+calendar.get(Calendar.YEAR));

        System.out.println(queryIncomeMonthly);
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(queryExpense);
            
            stmtIncome=con.createStatement();
            rsIncome=stmtIncome.executeQuery(queryIncome);
            
            stmtIncomeMonthly=con.createStatement();
            rsIncomeMonthly=stmtIncomeMonthly.executeQuery(queryIncomeMonthly);
            
            stmtExpenseMonthly=con.createStatement();
            rsExpenseMonthly=stmtExpenseMonthly.executeQuery(queryExpenseMonthly);
            
            stmtExpenseYearly=con.createStatement();
            rsExpenseYearly=stmtExpenseYearly.executeQuery(queryExpenseYearly);
            
            stmtIncomeYearly=con.createStatement();
            rsIncomeYearly=stmtIncomeYearly.executeQuery(queryIncomeYearly);
            
            rs.next();
            Float sum=rs.getFloat(1);
            rsIncome.next();
            Float sumIncome=rsIncome.getFloat(1);
            System.out.println(sum);
            if(sum > sumIncome){
                Float loss=sum-sumIncome;
                label_loss.setText(loss.toString());
            }else{
                 Float profit=sumIncome-sum;
                label_profit.setText(profit.toString());
            }
            
            label_total_income.setText(sumIncome.toString());           
            label_total_expense.setText(sum.toString());
           
            
            rsIncomeMonthly.next();
            rsExpenseMonthly.next();
            Float sumMonthlyIncome=rsIncomeMonthly.getFloat(1);
            
            Float sumMonthlyExpense=rsExpenseMonthly.getFloat(1);
            System.out.println(sum);
            if(sumMonthlyExpense > sumMonthlyIncome){
                Float loss=sumMonthlyExpense-sumMonthlyIncome;
                label_loss1.setText(loss.toString());
            }else{
                 Float profit=sumMonthlyIncome-sumMonthlyExpense;
                label_profit1.setText(profit.toString());
            }
            System.out.println("monthllllly income"+sumMonthlyIncome);
            label_total_income1.setText(sumMonthlyIncome.toString());           
            label_total_expense1.setText(sumMonthlyExpense.toString());
            
            
            rsIncomeYearly.next();
            rsExpenseYearly.next();
            Float sumYearlyIncome=rsIncomeYearly.getFloat(1);
            Float sumYearlyExpense=rsExpenseYearly.getFloat(1);
            System.out.println(sum);
            if(sumYearlyExpense > sumYearlyIncome){
                Float loss=sumYearlyExpense-sumYearlyIncome;
                label_loss2.setText(loss.toString());
            }else{
                 Float profit=sumYearlyIncome-sumYearlyExpense;
                label_profit2.setText(profit.toString());
            }
            System.out.println("yearly income"+sumYearlyIncome);
            label_total_income2.setText(sumYearlyIncome.toString());           
            label_total_expense2.setText(sumYearlyExpense.toString());
            
            DefaultPieDataset pie=new DefaultPieDataset();
            pie.setValue("Income: "+ new Double(sumIncome), new Double(sumIncome));
            pie.setValue("Expense: "+  new Double(sum), new Double(sum));
            JFreeChart chart=ChartFactory.createPieChart("Pie Chart Daily", pie, true, true, false);
            chartDisplay.setLayout(new java.awt.BorderLayout());
            ChartPanel CP = new ChartPanel(chart);
            chartDisplay.add(CP,BorderLayout.CENTER);
            chartDisplay.validate();
            
            
            
            DefaultPieDataset pieMonthly=new DefaultPieDataset();
            pieMonthly.setValue("Income: " + sumMonthlyIncome, new Double(sumMonthlyIncome));
            pieMonthly.setValue("Expense: " + sumMonthlyExpense, new Double(sumMonthlyExpense));
            JFreeChart chartMonthly=ChartFactory.createPieChart("Pie Chart Monthly", pieMonthly, true, true, false);
            chartDisplay1.setLayout(new java.awt.BorderLayout());
            ChartPanel monthly = new ChartPanel(chartMonthly);
            chartDisplay1.add(monthly,BorderLayout.CENTER);
            chartDisplay1.validate();
            
            
            
            DefaultPieDataset pieYearly=new DefaultPieDataset();
            pieYearly.setValue("Income: " + sumYearlyIncome, new Double(sumYearlyIncome));
            pieYearly.setValue("Expense: " + sumYearlyExpense, new Double(sumYearlyExpense));
            JFreeChart chartYearly=ChartFactory.createPieChart("Pie Chart Yearly", pieYearly, true, true, false);
            chartDisplay2.setLayout(new java.awt.BorderLayout());
            ChartPanel yearly = new ChartPanel(chartYearly);
            chartDisplay2.add(yearly,BorderLayout.CENTER);
            chartDisplay2.validate();
            
            
            
        }catch(HeadlessException | SQLException error){
            error.printStackTrace();
            
        }
        
//        DefaultTableModel model=(DefaultTableModel) jTable_categories.getModel();
//        Object[] row=new Object[3];
//        for(int i=0;i<list.size();i++){
//            row[0]=list.get(i).getId();             
//            row[1]=list.get(i).getName(); 
//
//            row[2]=list.get(i).getDescription();
//            model.addRow(row);
//
//        }
        
    }
    
//    chart code
    public void jFreeChartDisplay(){
        DefaultPieDataset pie=new DefaultPieDataset();
        pie.setValue("one", Integer.valueOf(10));
        pie.setValue("two", Integer.valueOf(40));
        pie.setValue("three", Integer.valueOf(10));
        pie.setValue("four", Integer.valueOf(15));
        JFreeChart chart=ChartFactory.createPieChart("Pie Chart", pie, true, true, false);
//        
//        ChartPanel(chart);
//        //ChartFrame frame =new ChartFrame("Pie Chart", chart);
//        JPanel jPanel1 = new JPanel();
        chartDisplay.setLayout(new java.awt.BorderLayout());
//
        ChartPanel CP = new ChartPanel(chart);
//
        chartDisplay.add(CP,BorderLayout.CENTER);
        chartDisplay.validate();

        
    }
    
    /**
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        chartDisplay = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label_total_income = new javax.swing.JLabel();
        label_total_expense = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        label_loss = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        label_profit = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbl_income_monthly = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        label_total_income1 = new javax.swing.JLabel();
        label_total_expense1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        label_loss1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        label_profit1 = new javax.swing.JLabel();
        chartDisplay1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbl_income_monthly1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        label_total_income2 = new javax.swing.JLabel();
        label_total_expense2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        label_loss2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        label_profit2 = new javax.swing.JLabel();
        chartDisplay2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        time = new javax.swing.JLabel();
        date = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusable(false);
        setFocusableWindowState(false);
        setUndecorated(true);
        setResizable(false);

        jPanel10.setBackground(new java.awt.Color(247, 225, 211));

        jPanel1.setBackground(new java.awt.Color(179, 160, 145));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 80)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Report Form");

        jTabbedPane1.setBackground(new java.awt.Color(247, 225, 211));

        jPanel2.setBackground(new java.awt.Color(247, 225, 211));

        jPanel3.setBackground(new java.awt.Color(247, 225, 211));

        javax.swing.GroupLayout chartDisplayLayout = new javax.swing.GroupLayout(chartDisplay);
        chartDisplay.setLayout(chartDisplayLayout);
        chartDisplayLayout.setHorizontalGroup(
            chartDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartDisplayLayout.setVerticalGroup(
            chartDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 61, 63));
        jLabel1.setText("Total Income");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(55, 61, 63));
        jLabel2.setText("Total Expense");

        label_total_income.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_income.setForeground(new java.awt.Color(55, 61, 63));
        label_total_income.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_income.setText("0");

        label_total_expense.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_expense.setForeground(new java.awt.Color(55, 61, 63));
        label_total_expense.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_expense.setText("0");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(55, 61, 63));
        jLabel6.setText("Profit");

        label_loss.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_loss.setForeground(new java.awt.Color(55, 61, 63));
        label_loss.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_loss.setText("0");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(55, 61, 63));
        jLabel9.setText("Loss");

        label_profit.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_profit.setForeground(new java.awt.Color(55, 61, 63));
        label_profit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_profit.setText("0");

        jLabel5.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_total_income, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_profit, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_loss, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_total_expense, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 579, Short.MAX_VALUE))
                    .addComponent(chartDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel5)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_total_expense, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(label_total_income)))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(label_loss)
                    .addComponent(label_profit))
                .addGap(42, 42, 42)
                .addComponent(chartDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(302, 302, 302)
                    .addComponent(jLabel5)
                    .addContainerGap(302, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daily", jPanel2);

        lbl_income_monthly.setBackground(new java.awt.Color(247, 225, 211));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(55, 61, 63));
        jLabel3.setText("Total Income");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(55, 61, 63));
        jLabel4.setText("Total Expense");

        label_total_income1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_income1.setForeground(new java.awt.Color(55, 61, 63));
        label_total_income1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_income1.setText("0");

        label_total_expense1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_expense1.setForeground(new java.awt.Color(55, 61, 63));
        label_total_expense1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_expense1.setText("0");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(55, 61, 63));
        jLabel8.setText("Profit");

        label_loss1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_loss1.setForeground(new java.awt.Color(55, 61, 63));
        label_loss1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_loss1.setText("0");

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(55, 61, 63));
        jLabel10.setText("Loss");

        label_profit1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_profit1.setForeground(new java.awt.Color(55, 61, 63));
        label_profit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_profit1.setText("0");

        javax.swing.GroupLayout chartDisplay1Layout = new javax.swing.GroupLayout(chartDisplay1);
        chartDisplay1.setLayout(chartDisplay1Layout);
        chartDisplay1Layout.setHorizontalGroup(
            chartDisplay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartDisplay1Layout.setVerticalGroup(
            chartDisplay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        jLabel15.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        javax.swing.GroupLayout lbl_income_monthlyLayout = new javax.swing.GroupLayout(lbl_income_monthly);
        lbl_income_monthly.setLayout(lbl_income_monthlyLayout);
        lbl_income_monthlyLayout.setHorizontalGroup(
            lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                        .addComponent(chartDisplay1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                        .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_total_income1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_profit1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_loss1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_total_expense1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 585, Short.MAX_VALUE))))
            .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel15)
                    .addContainerGap(396, Short.MAX_VALUE)))
            .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel16)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        lbl_income_monthlyLayout.setVerticalGroup(
            lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(label_total_income1)
                    .addComponent(label_total_expense1))
                .addGap(28, 28, 28)
                .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(label_loss1)
                    .addComponent(label_profit1))
                .addGap(42, 42, 42)
                .addComponent(chartDisplay1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                    .addGap(318, 318, 318)
                    .addComponent(jLabel15)
                    .addContainerGap(320, Short.MAX_VALUE)))
            .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                    .addGap(319, 319, 319)
                    .addComponent(jLabel16)
                    .addContainerGap(319, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_income_monthly, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lbl_income_monthly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 771, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Monthly", jPanel6);

        lbl_income_monthly1.setBackground(new java.awt.Color(247, 225, 211));

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(55, 61, 63));
        jLabel11.setText("Total Income");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(55, 61, 63));
        jLabel12.setText("Total Expense");

        label_total_income2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_income2.setForeground(new java.awt.Color(55, 61, 63));
        label_total_income2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_income2.setText("0");

        label_total_expense2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_expense2.setForeground(new java.awt.Color(55, 61, 63));
        label_total_expense2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_expense2.setText("0");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(55, 61, 63));
        jLabel13.setText("Profit");

        label_loss2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_loss2.setForeground(new java.awt.Color(55, 61, 63));
        label_loss2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_loss2.setText("0");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(55, 61, 63));
        jLabel14.setText("Loss");

        label_profit2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_profit2.setForeground(new java.awt.Color(55, 61, 63));
        label_profit2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_profit2.setText("0");

        javax.swing.GroupLayout chartDisplay2Layout = new javax.swing.GroupLayout(chartDisplay2);
        chartDisplay2.setLayout(chartDisplay2Layout);
        chartDisplay2Layout.setHorizontalGroup(
            chartDisplay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartDisplay2Layout.setVerticalGroup(
            chartDisplay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
        );

        jLabel18.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        javax.swing.GroupLayout lbl_income_monthly1Layout = new javax.swing.GroupLayout(lbl_income_monthly1);
        lbl_income_monthly1.setLayout(lbl_income_monthly1Layout);
        lbl_income_monthly1Layout.setHorizontalGroup(
            lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_income_monthly1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartDisplay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lbl_income_monthly1Layout.createSequentialGroup()
                        .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_total_income2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_profit2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_total_expense2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_loss2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 566, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthly1Layout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel18)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        lbl_income_monthly1Layout.setVerticalGroup(
            lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_income_monthly1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(label_total_income2)
                    .addComponent(label_total_expense2))
                .addGap(28, 28, 28)
                .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(label_loss2)
                    .addComponent(label_profit2))
                .addGap(42, 42, 42)
                .addComponent(chartDisplay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthly1Layout.createSequentialGroup()
                    .addGap(318, 318, 318)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(239, Short.MAX_VALUE)))
        );

        jLabel17.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_income_monthly1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel17)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_income_monthly1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(318, 318, 318)
                    .addComponent(jLabel17)
                    .addContainerGap(319, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Yearly", jPanel9);

        jTextArea1.setBackground(new java.awt.Color(195, 207, 160));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Kannada MN", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Take Note!");
        jTextArea1.setSize(new java.awt.Dimension(220, 89));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1)))
                .addGap(0, 0, 0))
        );

        time.setFont(new java.awt.Font("Wide Latin", 0, 18)); // NOI18N
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        date.setFont(new java.awt.Font("Wide Latin", 0, 18)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(635, 635, 635)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(636, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(546, 546, 546)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(488, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ReportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartDisplay;
    private javax.swing.JPanel chartDisplay1;
    private javax.swing.JPanel chartDisplay2;
    private javax.swing.JLabel date;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel label_loss;
    private javax.swing.JLabel label_loss1;
    private javax.swing.JLabel label_loss2;
    private javax.swing.JLabel label_profit;
    private javax.swing.JLabel label_profit1;
    private javax.swing.JLabel label_profit2;
    private javax.swing.JLabel label_total_expense;
    private javax.swing.JLabel label_total_expense1;
    private javax.swing.JLabel label_total_expense2;
    private javax.swing.JLabel label_total_income;
    private javax.swing.JLabel label_total_income1;
    private javax.swing.JLabel label_total_income2;
    private javax.swing.JPanel lbl_income_monthly;
    private javax.swing.JPanel lbl_income_monthly1;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onQueryExec(String message) {
//        DefaultTableModel model=(DefaultTableModel)jTable_categories.getModel();
//        model.setRowCount(0);
//        showData();
//        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void onError(String message) {
        JOptionPane.showMessageDialog(null, message);

    }

    private void ChartPanel(JFreeChart chart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
