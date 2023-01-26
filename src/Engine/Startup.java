package Engine;

import java.sql.Statement;
import Engine.Config.ConfigurationDriver;
import Engine.ErrorHandler.ErrorHandler;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Start up procedure for MME.
 */
public class Startup 
{
    ConfigurationDriver driver = new ConfigurationDriver();

    private final String connectionString =  
    // prod database 
    // "jdbc:sqlserver://mme.cu8pbttixbup.us-east-2.rds.amazonaws.com:1433; DatabaseName = mme_prod; trustServerCertificate = true; user = admin; password = csc325finalproject";

    "jdbc:sqlserver://localhost; encrypt=true; DatabaseName=NS.baseball.manager; trustServerCertificate = true;  integratedSecurity=true;";

    private Connection conn ;
    private String team ;

    public Startup(String teamName)
    {
        team = teamName;
        
        try
        {
            conn = DriverManager.getConnection(connectionString);
        }
        catch(Exception ex)
        {
            ErrorHandler.handle("Exception thrown making connection in Startup\n" + ex.getLocalizedMessage());
        }

        this.scaffoldDb();
        this.dispose();
    }

    /**
     * Scaffold the database with appropriate tables.
     */
    public void scaffoldDb()
    {
        try(Statement sql = conn.createStatement())
        {
            String sqlString;
            if(!driver.isDbScaffolded())
            {
                try
                {
                    sqlString = SqlUtilityTool.extractSqlStringFromFile(new File("sql\\01_startup.sql"));
                    sql.executeUpdate(sqlString);

                    // this might not be neccessary anymore, but I'm afraid to touch it, so it stays.
                    sqlString = "DROP TABLE Teams;" 
                    + "CREATE TABLE Teams (Name NVARCHAR(MAX), Rank DECIMAL(18,0) );"
                    + "INSERT INTO Teams VALUES ('" + team + "', 1);";

                    sql.executeUpdate(sqlString);

                    driver.scaffoldDb();
                }
                catch(Exception ex)
                {
                    ErrorHandler.handle("Exception handled \n" + ex.getMessage());
                }
            }
        } 
        catch (Exception ex)
        {
            ErrorHandler.handle("Exception handled: " + ex.getMessage());
        }
    }

    /**
     * Terminate start up procedures.
     */
    public void dispose()
    {
        try 
        {
            conn.close();
        } 
        catch (Exception e) 
        {
            ErrorHandler.handle("Exception thrown disposing startup procs:\n" + e.getMessage());
        }
    }
}