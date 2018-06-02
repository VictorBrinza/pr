
package sistemdemanagement;
import java.sql.*;
import javax.swing.JOptionPane;


public class db {
    
    
    Connection conn = null;
    public static Connection java_db(){
        
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\vb_pc\\projects\\SistemDeManagement\\mydatabase.sqlite");
            return conn;
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    
    }
}
