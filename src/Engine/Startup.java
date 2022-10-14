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
            String sqlString;
            if(!driver.isDbScaffolded())
            {
                try
                {
                    sqlString = SqlUtilityTool.extractSqlStringFromFile(new File("sql\\Startup\\ResetQuery.sql"));
                    System.out.println(sqlString);
                    sql.executeUpdate(sqlString);
                    System.out.println("Line one ran");

                    sqlString = SqlUtilityTool.extractSqlStringFromFile(new File("sql\\Startup\\TeamStartup.sql"));
                    System.out.println(sqlString);
                    sql.executeUpdate(sqlString);
                    System.out.println("Line two ran");

                    driver.scaffoldDb();
                }
                catch(Exception ex)
                {
                    System.out.println("Exception handled \n" + ex.getMessage());
                }
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
