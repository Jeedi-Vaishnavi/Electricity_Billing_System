
package electricity.billing.system;
import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    
    Conn(){
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs","root","vaishu1101");
            s = c.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
