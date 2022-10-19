package Engine;

import java.sql.Statement;

import Engine.Config.ConfigurationDriver;
import Engine.ErrorHandler.ErrorHandler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;


public class Startup 
{
    ConfigurationDriver driver = new ConfigurationDriver();

    private final String connectionString =  
    "jdbc:sqlserver://localhost; encrypt=true; DatabaseName=NS.baseball.manager; trustServerCertificate = true;  integratedSecurity=true;";

    private Connection conn ;

    private String teamOpt ;

    public Startup(String teamName)
    {
        teamOpt = teamName;
        try
        {
            conn = DriverManager.getConnection(connectionString);
        }
        catch(Exception ex)
        {
            ErrorHandler.handle("<DEBUG>: EXCEPTION THROWN\n" + ex.getLocalizedMessage());
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
                    sql.executeUpdate(sqlString);

                    sqlString = "drop table Teams;" 
                    + "create table Teams (Name nvarchar(MAX), Rank decimal(18,0) );"
                    + "insert into Teams values ('" + teamOpt + "', 1);";

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

    public void dispose()
    {
        try 
        {
            conn.close();
        } 
        catch (Exception e) 
        {
            ErrorHandler.handle("Exception thrown on dispose:\n" + e.getMessage());
        }
    }
}