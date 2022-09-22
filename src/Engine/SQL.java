package Engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQL 
{
//  jdbc:sqlserver://localhost;encrypt=true;databaseName=AdventureWorks;integratedSecurity=true;
    // url = "jdbc:sqlserver://" +serverName + ":1433;DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;
    private final String connectionString =  
    "jdbc:sqlserver://localhost; encrypt=true; DatabaseName=NS.baseball.manager; trustServerCertificate = true;  integratedSecurity=true;";

    private Connection conn ;

    public SQL()
    {
        try
        {
            conn = DriverManager.getConnection(connectionString);
            System.out.println("Connection established\nhostname resolved\nport access granted\n");
        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG>: EXCEPTION THROWN\n\n\n" + ex.getLocalizedMessage());
        }
    }

    public String[] getTeams()
    {
        try
        {
            Statement selectStatement = conn.createStatement();
            List<String> res = new ArrayList<String>();

            ResultSet set = selectStatement.executeQuery("SELECT * FROM Teams");

            while(set.next())
                res.add(set.getString("Team"));

            String[] resArr = new String[res.size()];

            for(int i =0; i < res.size(); i += 1)
                resArr[i] = res.get(i);
            
                return resArr;

        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG>: EXCEPTION THROWN\n\n\n" + ex.getLocalizedMessage());
            return null;
        }
    }

    public void makeTeam(String teamName, int rank)
    {

        String query = "INSERT INTO Teams (Team, Ranking) VALUES ('" + teamName +"', " + rank +");";

        try
        {

            Statement addStatement = conn.createStatement();
            ResultSet set = addStatement.executeQuery(query);

            System.out.println("Query ran");

        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG>: EXCEPTION THROWN\n\n\n" + ex.getLocalizedMessage());
        }
    }
}
