package Engine;

import java.sql.Statement;

import Engine.Configuration.ConfigurationDriver;
import Engine.ErrorHandler.ErrorHandler;
import Engine.SQL.SqlUtilityTool;
import java.sql.DriverManager;
import java.sql.Connection;
import java.io.File;


public class Startup 
{
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
            System.out.println("Connection Established");
        }
        catch(Exception ex)
        {
            ErrorHandler.handle(ex.getMessage());
        }

        this.scaffoldDb();
        this.dispose();
    }


    public void scaffoldDb()
    {
        try(Statement sql = conn.createStatement())
        {
            String sqlString;
            if(!ConfigurationDriver.isDbScaffolded())
            {
                try
                {
                    sqlString = SqlUtilityTool.extractSqlStringFromFile(new File("sql\\Startup\\ResetQuery.sql"));
                    sql.executeUpdate(sqlString);

                    sqlString = "drop table Teams;" 
                    + "create table Teams (Name nvarchar(MAX), Rank decimal(18,0) );"
                    + "insert into Teams values ('" + teamOpt + "', 1);";

                    sql.executeUpdate(sqlString);

                    ConfigurationDriver.scaffoldDb();
                }
                catch(Exception ex)
                {
                    ErrorHandler.handle(ex.getMessage());
                }
            }
        } 
        catch (Exception ex)
        {
            ErrorHandler.handle(ex.getMessage());
        }
    }

    public void dispose()
    {
        try 
        {
            conn.close();
        } 
        catch (Exception ex) 
        {
            ErrorHandler.handle(ex.getMessage());
        }
    }
}