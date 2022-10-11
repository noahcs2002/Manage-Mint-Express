package Engine;

import java.sql.Statement;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;


public class Startup 
{
    private final String[] paths = 
    {
        "sql\\Startup\\TeamStartup.sql",
        "sql\\Startup\\ResetQuery.sql"
    };

    ConfigurationDriver driver = new ConfigurationDriver();

    private final String connectionString =  
    "jdbc:sqlserver://localhost; encrypt=true; DatabaseName=NS.baseball.manager; trustServerCertificate = true;  integratedSecurity=true;";

    private Connection conn ;

    public Startup()
    {
        try
        {
            conn = DriverManager.getConnection(connectionString);
            System.out.println("Connection Established");
        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG>: EXCEPTION THROWN\n" + ex.getLocalizedMessage());
        }

        this.scaffoldDb();
        this.dispose();
    }

    public void scaffoldDb()
    {
        try(Statement sql = conn.createStatement())
        {
            if(!driver.isDbScaffolded())
            {
                for (String path : paths) 
                {
                    String sqlString = SqlUtilityTool.extractSqlStringFromFile(new File(path));
                    sql.executeUpdate(sqlString);
                }

                System.out.println("DB SCAFFOLDED");
                driver.scaffoldDb();
            }
        } 
        catch (Exception ex)
        {
            System.out.println("Exception handled: " + ex.getMessage());
        }
    }

    public void dispose()
    {
        try 
        {
            conn.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Exception thrown on dispose:\n" + e.getMessage());
        }
    }
}
