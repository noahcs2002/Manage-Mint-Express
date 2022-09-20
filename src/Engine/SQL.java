package Engine;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQL 
{
 
    private final String connectionString =  "jdbc:sqlserver://localhost; encrypt=true; integratedSecurity=true;";
    
    public SQL()
    {
        try(Connection conn = DriverManager.getConnection(connectionString))
        {
            System.out.println("Connection Made");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getLocalizedMessage());
        }
    }


}
