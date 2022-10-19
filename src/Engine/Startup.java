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
                    sqlString = SqlUtilityTool.extractSqlStringFromFile(new File("sql\\Startup\\ResetQuery.sql"));
                    sql.executeUpdate(sqlString);

                    sqlString = "drop table Teams;" 
                    + "create table Teams (Name nvarchar(MAX), Rank decimal(18,0) );"
                    + "insert into Teams values ('" + team + "', 1);";

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