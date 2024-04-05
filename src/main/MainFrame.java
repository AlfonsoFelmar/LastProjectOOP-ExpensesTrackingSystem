/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;



import incomeCategories.Category1;
import java.sql.*;


import java.util.Locale;

import java.util.logging.Logger;
import static javax.lang.model.type.TypeKind.NULL;
import javax.swing.JFrame;



import main.QueryInterfce;
import main.User;
import expenseCategories.Category;
import java.awt.Color;
import report.Report;
import JDBC.DBConn;
import expenseCategories.ExpenseCategoryForm;
import expenses.Expense;
import income.Income;
import income.IncomeForm;
import incomeCategories.IncomeCategoryForm;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import report.ReportForm;

/**
 *
 * @author felmaralfonso
 */
public class MainFrame extends javax.swing.JFrame implements QueryInterfce{

    
    DBConn conn=new DBConn();
    Connection con=conn.getConnection();
    Statement stmt1 = null;
    ResultSet rs1 = null;
    
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

    
    QueryInterfce sqlinterfce;
    private TreeMap <Integer, String> categoryMap = new TreeMap<Integer, String>();
    
    
    public MainFrame() {
        initComponents();
        showData();
        populateCombo();
        showData1();
        showDataECat();
        populateCombo1();
        showDataE();
        
        showDataIncomeCategory();
     
        setExtendedState(MainFrame.MAXIMIZED_BOTH);
        ID.setVisible(false);
        ID1.setVisible(false);  
        
    }
        public ArrayList<Category> getCategoryListt(){
        ArrayList<Category> categoryList=new ArrayList<Category>();
        
        String query="select * from categories"; 
       
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            Category category;
            while(rs.next()){
                category=new Category(rs.getInt("id"),rs.getString("name"),rs.getString("description"));
                categoryList.add(category);
            }          
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, "error while loading data from database");
        }
        return categoryList;
    }
        
        
        
        
        public void showDataECat(){
        ArrayList<Category> list =getCategoryListt();
        DefaultTableModel model=(DefaultTableModel) jTable_categories.getModel();
        Object[] row=new Object[3];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getId();             
            row[1]=list.get(i).getName(); 

            row[2]=list.get(i).getDescription();
            model.addRow(row);

        }
        
    }
        
        
                public void executeSQLQuery102(String query, String message) {
        
        try{
            stmt=con.createStatement();
            
            if(stmt.executeUpdate(query)==1){
                JOptionPane.showMessageDialog(null, message);

        
                
                        
            }else{
                 JOptionPane.showMessageDialog(null, "Could not execute query");
            }
            
        }catch(HeadlessException | SQLException error){
            error.printStackTrace();
            sqlinterfce.onError(error.getMessage());
        }
    }
    
        private void executeSQLQuery1(String query, String message) {
        

        try {
            stmt=con.createStatement();
            if(stmt.executeUpdate(query)==1){
                DefaultTableModel model=(DefaultTableModel)jTable_categories.getModel();
                model.setRowCount(0);
                showDataECat();
                JOptionPane.showMessageDialog(null, message);
               
            }else{
                JOptionPane.showMessageDialog(null,"Data not inserted ");
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
        
        
                private void executeSQLQuery100(String query, String message) {
        try {
            stmt=con.createStatement();
            if(stmt.executeUpdate(query)==1){
                DefaultTableModel model=(DefaultTableModel)jTable_categoriess.getModel();
                model.setRowCount(0);
                showDataIncomeCategory();
                JOptionPane.showMessageDialog(null, message);
               
            }else{
                JOptionPane.showMessageDialog(null,"Data not inserted ");
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void showDataIncomeCategory(){
     ArrayList<Category1> list =getCategoryList();
        DefaultTableModel model=(DefaultTableModel) jTable_categoriess.getModel();
        Object[] row=new Object[3];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getId();             
            row[1]=list.get(i).getName(); 

            row[2]=list.get(i).getDescription();
            model.addRow(row);

        }
        
    }
        
            public ArrayList<incomeCategories.Category1> getCategoryList(){
        ArrayList<incomeCategories.Category1> categoryList=new ArrayList<incomeCategories.Category1>();
        
        String query="select * from income_categories"; 
       
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            incomeCategories.Category1 category;
            while(rs.next()){
                category=new incomeCategories.Category1(rs.getInt("id"),rs.getString("name"),rs.getString("description"));
                categoryList.add(category);
            }          
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, "error while loading data from database");
        }
        return categoryList;
    }
    
    
    
    
    
    
        private void executeSQLQuery(String query, String message) {
        

        try {
            stmt=con.createStatement();
            if(stmt.executeUpdate(query)==1){
                DefaultTableModel model=(DefaultTableModel)jTableIncome.getModel();
                model.setRowCount(0);
                showData1();
                JOptionPane.showMessageDialog(null, message);
               
            }else{
                JOptionPane.showMessageDialog(null,"Data not inserted ");
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
         private void executeSQLQuery2(String query, String message) {
        

        try {
            stmt=con.createStatement();
            if(stmt.executeUpdate(query)==1){
                DefaultTableModel model=(DefaultTableModel)jTableExpenses.getModel();
                model.setRowCount(0);
                showDataE();
                JOptionPane.showMessageDialog(null, message);
                
            }else{
                JOptionPane.showMessageDialog(null,"Data not inserted ");
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(IncomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
        

    public HashMap<String, Integer> populateCombo1(){
        
            HashMap<String, Integer> map=new HashMap<String, Integer>();
            
           
           
            try {
            String query="select id, name from categories";
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
//            Category category;

            while(rs.next()){
                 int id = rs.getInt("id");
                 String name = rs.getString("name");
                 System.out.println(name);
                 
                categories1.addItem(name);
                //categoryMap.put(rs.getInt("id"), name);

//                categories.addItem(rs.getInt("id"));
//                category=new Category(rs.getInt(0),rs.getString(1));
//                map.put(category.getName(), category.getId());
            }
          
            } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(expenses.MainFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        return map;
    } 
    
            
            
    public ArrayList<Expense> getExpenseList(){
        ArrayList<Expense> expenseList=new ArrayList<Expense>();
       
        String query="SELECT expenses.id, expenses.user_id,expenses.name, expenses.category_id, categories.name as category,expenses.description, expenses.quantity,expenses.cost,expenses.date,expenses.user_id FROM expenses LEFT JOIN categories ON expenses.category_id=categories.id where expenses.user_id='"+User.getUser_session_id()+"' "; 
       
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            
            Expense expense;
           
            while(rs.next()){
               
                expense=new Expense(rs.getInt("id"),rs.getInt("quantity"),rs.getString("category"),rs.getInt("cost"),rs.getString("name"),rs.getString("description"),rs.getDate("date"));
                System.out.println(rs.getString("date"));
                expenseList.add(expense);
            }          
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
         
        return expenseList;
    }
    
            
    public void showDataE(){
        ArrayList<Expense> list =getExpenseList();
      
        DefaultTableModel model=(DefaultTableModel) jTableExpenses.getModel();
        Object[] row=new Object[7];
        for(int i=0;i<list.size();i++){
            
            
            row[0]=list.get(i).getId();             
            row[1]=list.get(i).getTitle(); 
            row[2]=list.get(i).getDescription(); 
            row[3]=list.get(i).getCategory();
            row[4]=list.get(i).getQuantity();
            row[5]=list.get(i).getCost();
            row[6]=list.get(i).getDate();
            model.addRow(row);

        }
        
    }
    
        public void showData1(){
        
        ArrayList<Income> list =getIncomeList();
      
        DefaultTableModel model=(DefaultTableModel) jTableIncome.getModel();
        Object[] row=new Object[7];
        for(int i=0;i<list.size();i++){
            
            
            row[0]=list.get(i).getId();             
            row[1]=list.get(i).getTitle(); 
            row[2]=list.get(i).getDescription(); 
            row[3]=list.get(i).getCategory();
            row[4]=list.get(i).getQuantity();
            row[5]=list.get(i).getCost();
            row[6]=list.get(i).getDate();
            model.addRow(row);

        }
        
    }
    
    
    
        public ArrayList<Income> getIncomeList(){
    
        ArrayList<Income> incomeList=new ArrayList<Income>();
       //     sql query for displaying data of income in table joining category table

        String query="SELECT incomes.id,incomes.name, incomes.category_id, income_categories.name as category,incomes.description, incomes.quantity,incomes.cost,incomes.date,incomes.user_id FROM incomes LEFT JOIN income_categories ON incomes.category_id=income_categories.id where incomes.user_id='"+User.getUser_session_id()+"'"; 
        
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            
            Income incomes;
           
            while(rs.next()){   
                incomes=new Income(rs.getInt("id"),rs.getInt("quantity"),rs.getString("category"),rs.getInt("cost"),rs.getString("name"),rs.getString("description"),rs.getDate("date"));
                System.out.println(rs.getString("date"));
                incomeList.add(incomes);
            }          
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
         
        return incomeList;
    }
    
    
    
    
    
    public HashMap<String, Integer> populateCombo(){
        
            HashMap<String, Integer> map=new HashMap<String, Integer>();
            
           
            try {
            String query="select id, name from income_categories";
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
//            Category category;
            while(rs.next()){
                               
                categories.addItem(rs.getString("name"));
                
//                category=new Category(rs.getInt(0),rs.getString(1));
//                map.put(category.getName(), category.getId());
            }
          
            } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        return map;
    }
    
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        chartDisplay = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        label_total_income = new javax.swing.JLabel();
        label_total_expense = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        label_loss = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        label_profit = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbl_income_monthly = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        label_total_income1 = new javax.swing.JLabel();
        label_total_expense1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        label_loss1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        label_profit1 = new javax.swing.JLabel();
        chartDisplay1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbl_income_monthly1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        label_total_income2 = new javax.swing.JLabel();
        label_total_expense2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        label_loss2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        label_profit2 = new javax.swing.JLabel();
        chartDisplay2 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        time = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        title = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        description = new javax.swing.JTextField();
        categories = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        lbl_session_user_id = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        clear = new javax.swing.JButton();
        date1 = new com.toedter.calendar.JDateChooser();
        ID = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableIncome = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        title2 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        description1 = new javax.swing.JTextField();
        categories1 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        quantity1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        amount1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        lbl_session_user_id1 = new javax.swing.JLabel();
        update1 = new javax.swing.JButton();
        delete1 = new javax.swing.JButton();
        ID1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        clear1 = new javax.swing.JButton();
        date2 = new com.toedter.calendar.JDateChooser();
        jPanel16 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableExpenses = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jPanel2000 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        lbl_title = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        description22 = new javax.swing.JTextArea();
        lbl_description = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        update2 = new javax.swing.JButton();
        delete2 = new javax.swing.JButton();
        clear2 = new javax.swing.JButton();
        id10 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_categories = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        nameee = new javax.swing.JTextField();
        lbl_title1 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lbl_description1 = new javax.swing.JLabel();
        idddddd = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        descriptionnn = new javax.swing.JTextArea();
        jPanel21 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_categoriess = new javax.swing.JTable();
        submit1 = new javax.swing.JButton();
        update3 = new javax.swing.JButton();
        delete3 = new javax.swing.JButton();
        clear3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(128, 137, 92));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel1.setText("Expenses  ");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel2.setText("System");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(128, 137, 92));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jButton1.setText("Report");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(128, 137, 92));
        jButton2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jButton2.setText("Income");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(128, 137, 92));
        jButton3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jButton3.setText("Expenses");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(128, 137, 92));
        jButton4.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jButton4.setText("Expenses Category");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(128, 137, 92));
        jButton5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jButton5.setText("Income Category");
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(128, 137, 92));
        jButton6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jButton6.setText("Sign-out");
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel3.setText("Tracking");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jToggleButton1.setBackground(new java.awt.Color(128, 137, 92));
        jToggleButton1.setText("Update");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(247, 225, 211));

        jLabel4.setBackground(new java.awt.Color(153, 51, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/63481-removebg-preview (1) (1).png")); // NOI18N

        jLabel5.setBackground(new java.awt.Color(153, 51, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        jLabel9.setFont(new java.awt.Font("Anydore", 0, 80)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Hi, welcome!");

        jLabel10.setFont(new java.awt.Font("Anydore", 0, 80)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Let's track your expenses...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(295, 295, 295))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel10.setBackground(new java.awt.Color(247, 225, 211));

        jPanel3.setBackground(new java.awt.Color(179, 160, 145));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 80)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Report Form");

        jTabbedPane2.setBackground(new java.awt.Color(247, 225, 211));

        jPanel4.setBackground(new java.awt.Color(247, 225, 211));

        jPanel5.setBackground(new java.awt.Color(247, 225, 211));

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

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(55, 61, 63));
        jLabel12.setText("Total Income");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(55, 61, 63));
        jLabel13.setText("Total Expense");

        label_total_income.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_income.setForeground(new java.awt.Color(55, 61, 63));
        label_total_income.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_income.setText("0");

        label_total_expense.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_expense.setForeground(new java.awt.Color(55, 61, 63));
        label_total_expense.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_expense.setText("0");

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(55, 61, 63));
        jLabel14.setText("Profit");

        label_loss.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_loss.setForeground(new java.awt.Color(55, 61, 63));
        label_loss.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_loss.setText("0");

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(55, 61, 63));
        jLabel15.setText("Loss");

        label_profit.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_profit.setForeground(new java.awt.Color(55, 61, 63));
        label_profit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_profit.setText("0");

        jLabel16.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_total_income, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_profit, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_loss, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_total_expense, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 573, Short.MAX_VALUE))
                    .addComponent(chartDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel16)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_total_expense, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)
                        .addComponent(label_total_income)))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(label_loss)
                    .addComponent(label_profit))
                .addGap(42, 42, 42)
                .addComponent(chartDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(302, 302, 302)
                    .addComponent(jLabel16)
                    .addContainerGap(302, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Daily", jPanel4);

        lbl_income_monthly.setBackground(new java.awt.Color(247, 225, 211));

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(55, 61, 63));
        jLabel17.setText("Total Income");

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(55, 61, 63));
        jLabel18.setText("Total Expense");

        label_total_income1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_income1.setForeground(new java.awt.Color(55, 61, 63));
        label_total_income1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_income1.setText("0");

        label_total_expense1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_expense1.setForeground(new java.awt.Color(55, 61, 63));
        label_total_expense1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_expense1.setText("0");

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(55, 61, 63));
        jLabel19.setText("Profit");

        label_loss1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_loss1.setForeground(new java.awt.Color(55, 61, 63));
        label_loss1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_loss1.setText("0");

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(55, 61, 63));
        jLabel20.setText("Loss");

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

        jLabel21.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

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
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_total_income1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_profit1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_loss1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_total_expense1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 579, Short.MAX_VALUE))))
            .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel21)
                    .addContainerGap(396, Short.MAX_VALUE)))
            .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel22)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        lbl_income_monthlyLayout.setVerticalGroup(
            lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(label_total_income1)
                    .addComponent(label_total_expense1))
                .addGap(28, 28, 28)
                .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(label_loss1)
                    .addComponent(label_profit1))
                .addGap(42, 42, 42)
                .addComponent(chartDisplay1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                    .addGap(318, 318, 318)
                    .addComponent(jLabel21)
                    .addContainerGap(320, Short.MAX_VALUE)))
            .addGroup(lbl_income_monthlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthlyLayout.createSequentialGroup()
                    .addGap(319, 319, 319)
                    .addComponent(jLabel22)
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
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
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

        jTabbedPane2.addTab("Monthly", jPanel6);

        lbl_income_monthly1.setBackground(new java.awt.Color(247, 225, 211));

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(55, 61, 63));
        jLabel23.setText("Total Income");

        jLabel24.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(55, 61, 63));
        jLabel24.setText("Total Expense");

        label_total_income2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_income2.setForeground(new java.awt.Color(55, 61, 63));
        label_total_income2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_income2.setText("0");

        label_total_expense2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_total_expense2.setForeground(new java.awt.Color(55, 61, 63));
        label_total_expense2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_total_expense2.setText("0");

        jLabel25.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(55, 61, 63));
        jLabel25.setText("Profit");

        label_loss2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        label_loss2.setForeground(new java.awt.Color(55, 61, 63));
        label_loss2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_loss2.setText("0");

        jLabel26.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(55, 61, 63));
        jLabel26.setText("Loss");

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

        jLabel27.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

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
                            .addComponent(jLabel23)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_total_income2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_profit2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_total_expense2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_loss2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 560, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthly1Layout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel27)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        lbl_income_monthly1Layout.setVerticalGroup(
            lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_income_monthly1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(label_total_income2)
                    .addComponent(label_total_expense2))
                .addGap(28, 28, 28)
                .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(label_loss2)
                    .addComponent(label_profit2))
                .addGap(42, 42, 42)
                .addComponent(chartDisplay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(lbl_income_monthly1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lbl_income_monthly1Layout.createSequentialGroup()
                    .addGap(318, 318, 318)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(239, Short.MAX_VALUE)))
        );

        jLabel28.setIcon(new javax.swing.ImageIcon("/Users/felmaralfonso/Downloads/pictures/coins-f1.png")); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_income_monthly1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(395, 395, 395)
                    .addComponent(jLabel28)
                    .addContainerGap(390, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_income_monthly1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(318, 318, 318)
                    .addComponent(jLabel28)
                    .addContainerGap(319, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Yearly", jPanel9);

        jTextArea1.setBackground(new java.awt.Color(195, 207, 160));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Kannada MN", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Take Note!");
        jTextArea1.setSize(new java.awt.Dimension(220, 89));
        jScrollPane1.setViewportView(jTextArea1);

        jButton9.setText("Save Text");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        time.setFont(new java.awt.Font("Wide Latin", 0, 18)); // NOI18N
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        date.setFont(new java.awt.Font("Wide Latin", 0, 18)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(635, 635, 635)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(444, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 999, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(546, 546, 546)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(407, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", jPanel10);

        jPanel11.setBackground(new java.awt.Color(247, 225, 211));

        jPanel12.setBackground(new java.awt.Color(247, 225, 211));

        jLabel29.setBackground(new java.awt.Color(55, 61, 63));
        jLabel29.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel29.setText("Title");

        jLabel30.setBackground(new java.awt.Color(55, 61, 63));
        jLabel30.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel30.setText("Description ");

        description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionActionPerformed(evt);
            }
        });

        categories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriesActionPerformed(evt);
            }
        });

        jLabel31.setBackground(new java.awt.Color(55, 61, 63));
        jLabel31.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel31.setText("Categories");

        jLabel32.setBackground(new java.awt.Color(55, 61, 63));
        jLabel32.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel32.setText("Quantity");

        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        jLabel33.setBackground(new java.awt.Color(55, 61, 63));
        jLabel33.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel33.setText("Amount");

        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel34.setBackground(new java.awt.Color(55, 61, 63));
        jLabel34.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel34.setText("Date");

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        ID.setEditable(false);
        ID.setBackground(new java.awt.Color(247, 225, 211));
        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(description, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(categories, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quantity, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(amount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(date1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(title))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(364, 364, 364)
                        .addComponent(lbl_session_user_id)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbl_session_user_id)
                .addGap(0, 0, 0)
                .addComponent(jLabel29)
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel31)
                .addGap(20, 20, 20)
                .addComponent(categories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel32)
                .addGap(20, 20, 20)
                .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel33)
                .addGap(20, 20, 20)
                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel34)
                .addGap(20, 20, 20)
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(update)
                    .addComponent(delete)
                    .addComponent(clear))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(179, 160, 145));

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 80)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Income Form");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel35)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTableIncome.setBackground(new java.awt.Color(195, 207, 160));
        jTableIncome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Description", "Category", "Quantity", "Amount", "Date"
            }
        ));
        jTableIncome.setGridColor(new java.awt.Color(195, 207, 160));
        jTableIncome.setSelectionBackground(new java.awt.Color(195, 207, 160));
        jTableIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableIncomeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableIncome);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                .addGap(15, 15, 15))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 778, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab3", jPanel11);

        jPanel14.setBackground(new java.awt.Color(247, 225, 211));

        jPanel15.setBackground(new java.awt.Color(247, 225, 211));

        jLabel36.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(55, 61, 63));
        jLabel36.setText("Title");

        jLabel37.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(55, 61, 63));
        jLabel37.setText("Description ");

        description1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                description1ActionPerformed(evt);
            }
        });

        categories1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categories1ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(55, 61, 63));
        jLabel38.setText("Categories");

        jLabel39.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(55, 61, 63));
        jLabel39.setText("Quantity");

        quantity1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity1ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(55, 61, 63));
        jLabel40.setText("Amount");

        amount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amount1ActionPerformed(evt);
            }
        });

        jButton8.setText("Add");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        update1.setText("Update");
        update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update1ActionPerformed(evt);
            }
        });

        delete1.setText("Delete");
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete1ActionPerformed(evt);
            }
        });

        ID1.setEditable(false);
        ID1.setBackground(new java.awt.Color(247, 225, 211));

        jLabel41.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(55, 61, 63));
        jLabel41.setText("Date");

        clear1.setText("Clear");
        clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(459, 459, 459)
                                .addComponent(lbl_session_user_id1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amount1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(description1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(categories1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(quantity1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(date2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(update1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(clear1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(title2)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbl_session_user_id1)
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(ID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(description1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel38)
                .addGap(20, 20, 20)
                .addComponent(categories1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel39)
                .addGap(20, 20, 20)
                .addComponent(quantity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addGap(20, 20, 20)
                .addComponent(amount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel41)
                .addGap(20, 20, 20)
                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(update1)
                    .addComponent(delete1)
                    .addComponent(clear1))
                .addGap(37, 37, 37))
        );

        jPanel16.setBackground(new java.awt.Color(179, 160, 145));

        jLabel42.setBackground(new java.awt.Color(179, 160, 145));
        jLabel42.setFont(new java.awt.Font("SansSerif", 1, 80)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Expenses Form");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel42)
                .addGap(61, 61, 61))
        );

        jTableExpenses.setBackground(new java.awt.Color(195, 207, 160));
        jTableExpenses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Description", "Category", "Quantity", "Amount", "Date"
            }
        ));
        jTableExpenses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableExpensesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableExpenses);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                .addGap(16, 16, 16))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                .addGap(0, 0, 0))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(778, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab4", jPanel14);

        jPanel17.setBackground(new java.awt.Color(247, 225, 211));

        jPanel2000.setBackground(new java.awt.Color(247, 225, 211));

        jLabel43.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(55, 61, 63));
        jLabel43.setText("Title:");

        lbl_title.setForeground(new java.awt.Color(204, 0, 0));

        jLabel44.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(55, 61, 63));
        jLabel44.setText("Description:");

        description22.setColumns(20);
        description22.setRows(5);
        jScrollPane4.setViewportView(description22);

        lbl_description.setForeground(new java.awt.Color(255, 0, 0));

        submit.setText("Add");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        update2.setText("Update");
        update2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update2ActionPerformed(evt);
            }
        });

        delete2.setText("Delete");
        delete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete2ActionPerformed(evt);
            }
        });

        clear2.setText("Clear");
        clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear2ActionPerformed(evt);
            }
        });

        id10.setEditable(false);
        id10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id10ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(55, 61, 63));
        jLabel45.setText("ID:");

        javax.swing.GroupLayout jPanel2000Layout = new javax.swing.GroupLayout(jPanel2000);
        jPanel2000.setLayout(jPanel2000Layout);
        jPanel2000Layout.setHorizontalGroup(
            jPanel2000Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2000Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2000Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(id10)
                    .addComponent(name)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2000Layout.createSequentialGroup()
                        .addGroup(jPanel2000Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_title)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_description)
                            .addGroup(jPanel2000Layout.createSequentialGroup()
                                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(99, 99, 99))
                            .addGroup(jPanel2000Layout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(77, 77, 77)))
                        .addGap(197, 197, 197))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2000Layout.createSequentialGroup()
                        .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(update2, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(delete2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(clear2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2000Layout.setVerticalGroup(
            jPanel2000Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2000Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(id10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(lbl_title)
                .addGap(2, 2, 2)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(lbl_description)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(jPanel2000Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clear2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );

        jPanel18.setBackground(new java.awt.Color(179, 160, 145));

        jLabel46.setFont(new java.awt.Font("SansSerif", 1, 80)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Expenses Category");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel46)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTable_categories.setBackground(new java.awt.Color(195, 207, 160));
        jTable_categories.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Description"
            }
        ));
        jTable_categories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_categoriesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable_categories);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jPanel2000, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 779, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab5", jPanel17);

        jPanel19.setBackground(new java.awt.Color(247, 225, 211));

        jPanel20.setBackground(new java.awt.Color(247, 225, 211));

        jLabel47.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(55, 61, 63));
        jLabel47.setText("Title:");

        nameee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameeeActionPerformed(evt);
            }
        });

        lbl_title1.setForeground(new java.awt.Color(204, 0, 0));

        jLabel48.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(55, 61, 63));
        jLabel48.setText("Description:");

        lbl_description1.setForeground(new java.awt.Color(255, 0, 0));

        idddddd.setEditable(false);
        idddddd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iddddddActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(55, 61, 63));
        jLabel49.setText("ID:");

        descriptionnn.setColumns(20);
        descriptionnn.setRows(5);
        jScrollPane6.setViewportView(descriptionnn);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addComponent(lbl_description1)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel49)
                                    .addComponent(lbl_title1)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(idddddd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameee, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(lbl_title1)
                .addGap(0, 0, 0)
                .addComponent(idddddd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_description1)
                .addGap(18, 18, 18)
                .addComponent(nameee, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel21.setBackground(new java.awt.Color(179, 160, 145));

        jLabel50.setFont(new java.awt.Font("SansSerif", 1, 80)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Income Category");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                .addGap(313, 313, 313))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel50)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTable_categoriess.setBackground(new java.awt.Color(195, 207, 160));
        jTable_categoriess.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Description"
            }
        ));
        jTable_categoriess.setGridColor(new java.awt.Color(147, 166, 97));
        jTable_categoriess.setSelectionBackground(new java.awt.Color(147, 166, 97));
        jTable_categoriess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_categoriessMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable_categoriess);

        submit1.setText("Add");
        submit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit1ActionPerformed(evt);
            }
        });

        update3.setText("Update");
        update3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update3ActionPerformed(evt);
            }
        });

        delete3.setText("Delete");
        delete3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete3ActionPerformed(evt);
            }
        });

        clear3.setText("Clear");
        clear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(submit1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(update3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(delete3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(clear3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
                .addGap(16, 16, 16))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clear3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jScrollPane7))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 779, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab6", jPanel19);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, -37, 1440, 1030));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        ReportForm jframe = new ReportForm();
//        jframe.setVisible(true);
//        jframe.setLocation(275, 53);
//        jframe.setSize(1480, 1000);     
//      
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        IncomeForm jframe = new IncomeForm();
//        jframe.setVisible(true);
//        jframe.setLocation(275,53);
//        jframe.setSize(1480,1000);
//        
//        //delete existing panels that pop up
//        new ReportForm().setVisible(false);
        jTabbedPane1.setSelectedIndex(2);
  
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTabbedPane1.setSelectedIndex(5);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed


        LoginForm jframe = new LoginForm();
        jframe.setVisible(true);
        User.setUser_session_id(0);
        dispose();
        
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionActionPerformed

    private void categoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriesActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //        Integer users=User.user_session_id;
        //        lbl_session_user_id.setText(String.valueOf(User.getUser_session_id()));
        //        String session_user_id=lbl_session_user_id.getText();
        Integer session_user_id=User.getUser_session_id();
        SimpleDateFormat dd=new SimpleDateFormat("Y-MM-dd");
       
        String ddd=dd.format(date1.getCalendar().getTime());
        //            sql query for geting the specific item that we have selected
        try {
            String query=" SELECT id from income_categories where name = '"+categories.getSelectedItem().toString()+"' ";
            stmt1=con.createStatement();
            rs1=stmt1.executeQuery(query);
            rs1.next();
            //            get the selected id
            Integer sum=rs1.getInt(1);
            
            System.out.println("result "+sum);
            //          Converstion of int to string
            String convertSum = Integer.toString(sum);
            // sql query for inserting expenses data
            String query1="insert into incomes(name,description,category_id,quantity,cost,date,user_id) values('"+title.getText()+"','"+description.getText()+"','"+convertSum+"','"+Integer.parseInt(quantity.getText())+"','"+Float.parseFloat(amount.getText())+"','"+ddd+"','"+User.getUser_session_id()+"')";
            String message="Successfully inserted data";
            executeSQLQuery(query1, message);
            System.out.println(query1);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_jButton7ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        if(title.getText().trim().isEmpty()){

        }else{
  
            SimpleDateFormat dd=new SimpleDateFormat("Y-MM-dd");
            String dates=dd.format(date1.getCalendar().getTime());
            System.out.println(dates);
            String query="update incomes set name='"+title.getText()+"', description='"+description.getText()+"' , category_id='"+categories.getSelectedIndex()+"', quantity='"+Integer.parseInt(quantity.getText())+"',cost='"+Float.parseFloat(amount.getText())+"',date='"+dates+"'  where id= '"+ID.getText()+"' ";
            System.out.println(query);
            String message = "Update succesfully";
            executeSQLQuery(query, message);

        }
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        String query="delete from incomes where id= '"+ID.getText()+"' "  ;
        executeSQLQuery(query, "Successfully deleted");
    }//GEN-LAST:event_deleteActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:

        
        jTabbedPane1.setSelectedIndex(2);
        title.setText("");
        description.setText("");
        quantity.setText("");
        amount.setText("");
        date1.setCalendar(null);
        
    }//GEN-LAST:event_clearActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDActionPerformed

    private void jTableIncomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableIncomeMouseClicked
        // TODO add your handling code here:
        int i = jTableIncome.getSelectedRow();
        TableModel model=jTableIncome.getModel();
        ID.setText(model.getValueAt(i,0).toString());
        title.setText(model.getValueAt(i,1).toString());
        description.setText(model.getValueAt(i,2).toString());
        categories.setSelectedItem(model.getValueAt(i,3));
        quantity.setText(model.getValueAt(i,4).toString());
        amount.setText(model.getValueAt(i,5).toString());
        date1.setDate((java.util.Date) model.getValueAt(i,6));

    }//GEN-LAST:event_jTableIncomeMouseClicked

    private void categories1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categories1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categories1ActionPerformed

    private void quantity1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantity1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantity1ActionPerformed

    private void amount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amount1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amount1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //        Integer users=User.user_session_id;
        //        lbl_session_user_id.setText(String.valueOf(User.getUser_session_id()));
        //        String session_user_id=lbl_session_user_id.getText();
        Integer session_user_id=User.getUser_session_id();
        SimpleDateFormat dd=new SimpleDateFormat("Y-MM-dd");
        String ddd=dd.format(date2.getCalendar().getTime());
        //        System.out.println("which id"+categories.getSelectedItem().categories.id);

        try {
            //            sql query for geting the specific item that we have selected
            String query=" SELECT id from categories where name = '"+categories1.getSelectedItem().toString()+"' ";
            stmt1=con.createStatement();
            rs1=stmt1.executeQuery(query);
            rs1.next();
            //            get the selected id
            Integer sum=rs1.getInt(1);
            System.out.println("result "+sum);
            //          Converstion of int to string
            String convertSum = Integer.toString(sum);
            // sql query for inserting expenses data
            String query1 ="insert into expenses(name,description,category_id,quantity,cost,date,user_id) values('"+title2.getText()+"','"+description1.getText()+"','"+convertSum+"','"+Integer.parseInt(quantity1.getText())+"','"+Float.parseFloat(amount1.getText())+"','"+ddd+"','"+User.getUser_session_id()+"')";

            String message="Successfully inserted data";
            executeSQLQuery2(query1, message);
            
        } catch (SQLException ex) {
            Logger.getLogger(expenses.MainFrame1.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        //
        //
        //

    }//GEN-LAST:event_jButton8ActionPerformed

    private void update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update1ActionPerformed
        // TODO add your handling code here:
//                if(title.getText().trim().isEmpty()){
//
//        }else{
//  
//            SimpleDateFormat dd=new SimpleDateFormat("Y-MM-dd");
//            String dates=dd.format(date1.getCalendar().getTime());
//            System.out.println(dates);
//            String query="update incomes set name='"+title.getText()+"', description='"+description.getText()+"' , category_id='"+categories.getSelectedIndex()+"', quantity='"+Integer.parseInt(quantity.getText())+"',cost='"+Float.parseFloat(amount.getText())+"',date='"+dates+"'  where id= '"+ID.getText()+"' ";
//            System.out.println(query);
//            String message = "Update succesfully";
//            executeSQLQuery(query, message);
//
//        }
        
        
        if(title2.getText().trim().isEmpty()){

        }
        else{
            SimpleDateFormat dd=new SimpleDateFormat("Y-MM-dd");
            String dates=dd.format(date2.getCalendar().getTime());
            System.out.println(dates);
            String query="update expenses set name='"+title2.getText()+"', description='"+description1.getText()+"' , category_id='"+categories1.getSelectedIndex()+"', quantity='"+Integer.parseInt(quantity1.getText())+"',cost='"+Float.parseFloat(amount1.getText())+"',date='"+dates+"'  where id= '"+ID1.getText()+"' ";
            System.out.println(query);
            String message = "Update succesfully";
            executeSQLQuery2(query, message);

        }
        
        
    }//GEN-LAST:event_update1ActionPerformed

    private void delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete1ActionPerformed
        // TODO add your handling code here:
        String query="delete from expenses where id= '"+ID1.getText()+"' "  ;
        executeSQLQuery2(query, "Successfully deleted");
    }//GEN-LAST:event_delete1ActionPerformed

    private void clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        title.setText("");
        description.setText("");
        quantity.setText("");
        amount.setText("");
        date1.setCalendar(null);
    }//GEN-LAST:event_clear1ActionPerformed

    private void jTableExpensesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableExpensesMouseClicked
        // TODO add your handling code here:
        int i=jTableExpenses.getSelectedRow();
        TableModel model=jTableExpenses.getModel();
        ID1.setText(model.getValueAt(i,0).toString());
        title2.setText(model.getValueAt(i,1).toString());
        description1.setText(model.getValueAt(i,2).toString());
        categories1.setSelectedItem(model.getValueAt(i,3));
        quantity1.setText(model.getValueAt(i,4).toString());
        amount1.setText(model.getValueAt(i,5).toString());
        date2.setDate((java.util.Date) model.getValueAt(i,6));

    }//GEN-LAST:event_jTableExpensesMouseClicked

    private void description1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_description1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_description1ActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        //validating if the field is empty
        if(name.getText().trim().isEmpty() ){
            lbl_title.setText(" Title is empty");

        }else{
            String query="insert into categories(name, description) values ('"+name.getText()+"','"+description22.getText()+"') ";
            executeSQLQuery1(query,"Data has been inserted successfully");
        }
    }//GEN-LAST:event_submitActionPerformed

    private void update2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update2ActionPerformed
        // TODO add your handling code here:
        if(name.getText().trim().isEmpty()){
            lbl_title.setText("Title is empty");
        }else{
            String query="update categories set name='"+name.getText()+"', description='"+description22.getText()+"' where id= '"+id10.getText()+"' ";
            executeSQLQuery1(query, "Updated successfully");
        }
    }//GEN-LAST:event_update2ActionPerformed

    private void delete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete2ActionPerformed
        // TODO add your handling code here:
        String query="delete from categories where id= '"+id10.getText()+"' "  ;
        executeSQLQuery1(query, "Successfully deleted");
    }//GEN-LAST:event_delete2ActionPerformed

    private void clear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear2ActionPerformed
        id10.setText("");        // TODO add your handling code here:
        name.setText("");
        description22.setText("");
    }//GEN-LAST:event_clear2ActionPerformed

    private void jTable_categoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_categoriesMouseClicked
        // get the data in the form after the item is selected from table
        int i=jTable_categories.getSelectedRow();
        TableModel model=jTable_categories.getModel();
        id10.setText(model.getValueAt(i,0).toString());
        name.setText(model.getValueAt(i,1).toString());
        description22.setText(model.getValueAt(i,2).toString());

    }//GEN-LAST:event_jTable_categoriesMouseClicked

    private void id10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id10ActionPerformed

    private void nameeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameeeActionPerformed

    private void iddddddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iddddddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iddddddActionPerformed

    private void jTable_categoriessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_categoriessMouseClicked
        // TODO add your handling code here:
        int i=jTable_categoriess.getSelectedRow();
        TableModel model=jTable_categoriess.getModel();
        idddddd.setText(model.getValueAt(i,0).toString());
        nameee.setText(model.getValueAt(i,1).toString());
        descriptionnn.setText(model.getValueAt(i,2).toString());

    }//GEN-LAST:event_jTable_categoriessMouseClicked

    private void submit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit1ActionPerformed
        // TODO add your handling code here:
        if(nameee.getText().trim().isEmpty() ){
            lbl_title.setText(" Title is empty");

        }else{
            String query="insert into income_categories(name, description) values ('"+nameee.getText()+"','"+descriptionnn.getText()+"') ";
            executeSQLQuery100(query,"Data has been inserted successfully");
        }
    }//GEN-LAST:event_submit1ActionPerformed

    private void update3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update3ActionPerformed
        // TODO add your handling code here:
        if(nameee.getText().trim().isEmpty()){
            lbl_title.setText("Title is empty");
        }else{
            String query="update income_categories set name='"+nameee.getText()+"', description='"+descriptionnn.getText()+"' where id= '"+idddddd.getText()+"' ";
            executeSQLQuery100(query, "Updated successfully");
        }
    }//GEN-LAST:event_update3ActionPerformed

    private void delete3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete3ActionPerformed
        // TODO add your handling code here:
        String query="delete from income_categories where id= '"+idddddd.getText()+"' "  ;
        executeSQLQuery100(query, "Successfully deleted");
    }//GEN-LAST:event_delete3ActionPerformed

    private void clear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear3ActionPerformed
        idddddd.setText("");
        nameee.setText("");
        descriptionnn.setText("");
    }//GEN-LAST:event_clear3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         try {
                
            String text = jTextArea1.getText();
            String query="insert into Text(text) values ('"+text+"') ";
            executeSQLQuery102(query, "Notes Updated!");         
            String sql = "SELECT * FROM Text";
            
            jTextArea1.setText(sql);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        dispose();
        new MainFrame().setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.JTextField ID1;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField amount1;
    private javax.swing.JComboBox<String> categories;
    private javax.swing.JComboBox<String> categories1;
    private javax.swing.JPanel chartDisplay;
    private javax.swing.JPanel chartDisplay1;
    private javax.swing.JPanel chartDisplay2;
    private javax.swing.JButton clear;
    private javax.swing.JButton clear1;
    private javax.swing.JButton clear2;
    private javax.swing.JButton clear3;
    private javax.swing.JLabel date;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete1;
    private javax.swing.JButton delete2;
    private javax.swing.JButton delete3;
    private javax.swing.JTextField description;
    private javax.swing.JTextField description1;
    private javax.swing.JTextArea description22;
    private javax.swing.JTextArea descriptionnn;
    private javax.swing.JTextField id10;
    private javax.swing.JTextField idddddd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
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
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel2000;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableExpenses;
    private javax.swing.JTable jTableIncome;
    private javax.swing.JTable jTable_categories;
    private javax.swing.JTable jTable_categoriess;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
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
    private javax.swing.JLabel lbl_description;
    private javax.swing.JLabel lbl_description1;
    private javax.swing.JPanel lbl_income_monthly;
    private javax.swing.JPanel lbl_income_monthly1;
    private javax.swing.JLabel lbl_session_user_id;
    private javax.swing.JLabel lbl_session_user_id1;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JLabel lbl_title1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nameee;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField quantity1;
    private javax.swing.JButton submit;
    private javax.swing.JButton submit1;
    private javax.swing.JLabel time;
    private javax.swing.JTextField title;
    private javax.swing.JTextField title2;
    private javax.swing.JButton update;
    private javax.swing.JButton update1;
    private javax.swing.JButton update2;
    private javax.swing.JButton update3;
    // End of variables declaration//GEN-END:variables

    public void onQueryExec(String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void onError(String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
