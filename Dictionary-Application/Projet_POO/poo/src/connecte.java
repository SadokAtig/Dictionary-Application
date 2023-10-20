import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class connecte {
  
public static Connection connect () throws ClassNotFoundException{
    Connection con = null ;
    Statement stmt = null ;
    ResultSet rs = null ;
    try {
 
        Class.forName("oracle.jdbc.driver.OracleDriver");

          con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sadok");

            System.out.println("Connected with connection #1");


            stmt=con.createStatement();
             rs=stmt.executeQuery("select * from frensh");
            while (rs.next()){
                /*System.out.print(rs.getInt(1)+"|");
                System.out.println(rs.getString(2));
                System.out.print(rs.getString(3)+"\n");
                System.out.println(rs.getString(4));
                System.out.print(rs.getString(5)+"\n");
                System.out.println(rs.getString(6));*/
                
           
            }
        

        
    }catch (SQLException ex) {
        System.out.println("error");
        ex.printStackTrace();

    }
    return con; 
}
}

