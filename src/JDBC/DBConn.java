
package JDBC;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.QueryInterfce;

public class DBConn {
    String url="jdbc:mysql://localhost:3306/ExpenseTrackerSystem?serverTimezone=Australia/Sydney";
    String user="root";
    String password="Fe@362al";
    Connection con = null;
    
    Statement stmt = null;
    ResultSet rs = null;
    QueryInterfce sqlinterfce;
	
     public Connection getConnection(){
       
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
            return con;
        }catch(Exception error){
            error.printStackTrace();
            throw new RuntimeException();
        }
    }
     
    public void executeSQLQuery1(QueryInterfce intrfce,String query, String message) {
        sqlinterfce = intrfce;
        try{
            stmt=con.createStatement();
            
            if(stmt.executeUpdate(query)==1){
                sqlinterfce.onQueryExec(message);
            }else{
                sqlinterfce.onError("Could not execute query");
            }
            
        }catch(HeadlessException | SQLException error){
            error.printStackTrace();
            sqlinterfce.onError(error.getMessage());
        }
    }
	
    
    
}
