package Controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTOs.Catcher;
import DTOs.Infielder;
import DTOs.Outfielder;
import DTOs.Pitcher;
import Engine.SqlUtilityTool;

public class SqlController 
{
    private final String connectionString =  
    "jdbc:sqlserver://localhost; encrypt=true; DatabaseName=NS.baseball.manager; trustServerCertificate = true;  integratedSecurity=true;";

    private Connection conn ;

    public SqlController()
    {
        try
        {
            conn = DriverManager.getConnection(connectionString);
            System.out.println("Connection Established");
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
                res.add(set.getString("Name"));

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

    /**
     * @apiNote Upper : Run executeUpdate
     * @param teamName Team name to add
     * @param rank Rank of the team
     */
    public void makeTeam(String teamName, int rank)
    {

        String query = "INSERT INTO Teams (Name, Rank) VALUES ('" + teamName +"', " + rank +");";

        try
        {
            System.out.println("Beginning query");
            Statement addStatement = conn.createStatement();
            addStatement.executeUpdate(query);

            System.out.println("Query ran");

        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG>: EXCEPTION THROWN\n\n\n" + ex.getLocalizedMessage());
        }
    }
    
    /**
     * @apiNote Downer : Run executeQuery
     * @param dataNames
     * @return result set of query results
     */
    public ArrayList<Object[]> getPitchers(String teamName)
    {
        try
        {
            String sql = "SELECT * FROM PitchingStaff WHERE Team = '" + teamName + "';";

            Statement statement = conn.createStatement();

            ResultSet set = statement.executeQuery(sql);
            ArrayList<Object[]> results = new ArrayList<>();

            while(set.next())
            {
                results.add(new Object[]
                {
                    set.getString("Player"),
                    set.getString("Team"),
                    set.getDouble("InningsPitched"),
                    set.getDouble("Hits"),
                    set.getDouble("Runs"),
                    set.getDouble("EarnedRuns"),
                    set.getDouble("Walks"),
                    set.getDouble("StrikeOuts"),
                    set.getDouble("Homeruns"),
                    set.getDouble("Saves"),
                    set.getDouble("ERA"),
                    set.getDouble("WHIP"),
                    set.getBoolean("IsInjured"),
                    set.getString("Injury"),
                    set.getBoolean("IsSuspended"),
                    set.getString("Suspension"),
                    set.getDouble("Number"),
                });
            }

            
            return results;
        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG> : EXCPETION HANDLED : " + ex.getMessage());
            return null;
        }
    }

    public ArrayList<Object[]> getCatchers(String teamName)
    {
        try
        {
            final String sql = "SELECT * FROM CatchingStaff WHERE Team = '" + teamName + "';";

            Statement statement = conn.createStatement();

            ResultSet set = statement.executeQuery(sql);
            ArrayList<Object[]> results = new ArrayList<>();

            while(set.next())
            {
                results.add(new Object[]
                {
                    set.getString("Name"),
                    set.getString("Team"),

                    set.getDouble("GamesPlayed"),
                    set.getDouble("GamesStarted"),
                    set.getDouble("InningsPlayed"),
                    set.getDouble("TotalChances"),
                    set.getDouble("Putouts"),
                    set.getDouble("Assists"),
                    set.getDouble("Errors"),
                    set.getDouble("DoublePlays"),
                
                    set.getBoolean("IsInjured"),
                    set.getString("Injury"),
                    set.getBoolean("IsSuspended"),
                    set.getString("Suspension"),
                    set.getString("Number")
                });
            }

        
            return results;
        }
        catch(Exception ex)
        {
            System.out.println("Error in getCatchers:\n\n" + ex.getLocalizedMessage());
            return null;
        }
    }

    public ArrayList<Object[]> getInfielders(String teamName)
    {
        try
        {
            final String sql = "SELECT * FROM Infielders WHERE Team = '" + teamName + "';";

            Statement statement = conn.createStatement();

            ResultSet set = statement.executeQuery(sql);
            ArrayList<Object[]> results = new ArrayList<>();

            while(set.next())
            {
                results.add(new Object[]
                {
                    set.getString("Name"),
                    set.getString("Team"),

                    set.getDouble("GamesPlayed"),
                    set.getDouble("GamesStarted"),
                    set.getDouble("InningsPlayed"),
                    set.getDouble("TotalChances"),
                    set.getDouble("PutOuts"),
                    set.getDouble("Assists"),
                    set.getDouble("Errors"),
                    set.getDouble("DoublePlays"),

                    set.getBoolean("IsInjured"),
                    set.getString("Injury"),
                    set.getBoolean("IsSuspended"),
                    set.getString("Suspension"),

                    set.getString("Position"),
                    set.getString("Number")

                });
            }

        
            return results;
        }
        catch(Exception ex)
        {
            System.out.println("Exception handled in getInfielders:\n\n" + ex.getLocalizedMessage());
            return null;
        }
    }

    public ArrayList<Object[]> getOutfielders(String teamName)
    {
        try
        {
            final String sql = "SELECT * FROM Outfielders WHERE Team = '" + teamName + "';";

            Statement statement = conn.createStatement();

            ResultSet set = statement.executeQuery(sql);
            ArrayList<Object[]> results = new ArrayList<>();

            while(set.next())
            {
                results.add(new Object[]
                {
                    set.getString("Name"),
                    set.getString("Team"),

                    set.getDouble("GamesPlayed"),
                    set.getDouble("GamesStarted"),
                    set.getDouble("InningsPlayed"),
                    set.getDouble("TotalChances"),
                    set.getDouble("Putouts"),
                    set.getDouble("Assists"),
                    set.getDouble("Errors"),
                    set.getDouble("DoublePlays"),

                    set.getBoolean("IsInjured"),
                    set.getString("Injury"),
                    set.getBoolean("IsSuspended"),
                    set.getString("Suspension"),
                    set.getString("Position"),
                    set.getString("Number"),
                });
            }

        
            return results;
        }
        catch(Exception ex)
        {
            System.out.println("Exception handled in getInfielders:\n\n" + ex.getLocalizedMessage());
            return null;
        }
    }

    public void makePitcher(Pitcher pitcher)
    {
        final String sqlString = "INSERT INTO PitchingStaff VALUES ('" 
            + pitcher.name + "', '" + pitcher.team + "', "
            + pitcher.InningsPitched + ", " + pitcher.Hits + ", "
            + pitcher.Runs + ", " + pitcher.EarnedRuns + ", "
            + pitcher.Walks + ", " + pitcher.StrikeOuts + ", "
            + pitcher.Homeruns + ", " + pitcher.Saves + ", "
            + pitcher.EarnedRunAverage + ", " + pitcher.WHIP + ", "
            + pitcher.isInjured + ", '" + pitcher.injuredReason + "', "
            + pitcher.isSuspended + ", '" + pitcher.suspendedReason + "', "
            + pitcher.number + ");";

        System.out.println(sqlString);

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            System.out.println("Exception making new pitcher\n" + ex.getMessage());
        }
    }

    public void makeCatcher(Catcher catcher)
    {
        final String sqlString = "INSERT INTO CatchingStaff ("
            + "Name, Team, GamesPlayed, GamesStarted,"
            + "InningsPlayed, TotalChances, PutOuts, Assists, Errors, DoublePlays,"
            + "IsInjured, Injury, IsSuspended, Suspension, Number )"
            + "VALUES ( '" 
            + catcher.name + "', '" + catcher.team + "', "
            + catcher.gamesPlayed+ ", " + catcher.gamesStarted + ", "
            + catcher.inningsPlayed + ", " + catcher.totalChances + ", "
            + catcher.putOuts + ", " + catcher.assists + ", " + catcher.errors + ", "
            + catcher.doublePlays + ", " + catcher.isInjured + ", '"
            + catcher.injury + "', "
            + catcher.isSuspended + ", '" + catcher.suspension + "', "
            + catcher.number + ");";

        System.out.println(sqlString);

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            System.out.println("Exception making new Catcher\n" + ex.getMessage());
        }
    }

    public void makeInfielder(Infielder infielder)
    {
        final String sqlString = "INSERT INTO Infielders VALUES('"
        + infielder.name + "', '" + infielder.team + "', " + infielder.gamesPlayed
        + ", " + infielder.gamesStarted + ", " + infielder.inningsPlayed 
        + ", " + infielder.totalChances + ", " + infielder.putOuts + ", " + infielder.assists
        + ", " + infielder.errors + ", " + infielder.doublePlaysTurned + ", " + infielder.isInjured
        + ", '" + infielder.injury + "', " + infielder.isSuspended + ", '" + infielder.suspension 
        + "', '" + infielder.position + "', " + infielder.number
        + ");";

        System.out.println(sqlString);

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            System.out.println("Exception making new Catcher\n" + ex.getMessage());
        }
    }

    public void makeOutfielder(Outfielder infielder)
    {
        final String sqlString = "INSERT INTO Outfielders VALUES('"
        + infielder.name + "', '" + infielder.team + "', " + infielder.gamesPlayed
        + ", " + infielder.gamesStarted + ", " + infielder.inningsPlayed 
        + ", " + infielder.totalChances + ", " + infielder.putOuts + ", " + infielder.assists
        + ", " + infielder.errors + ", " + infielder.doublePlaysTurned + ", " + infielder.isInjured
        + ", '" + infielder.injury + "', " + infielder.isSuspended + ", '" + infielder.suspension 
        + "', '" + infielder.position + "', " + infielder.number
        + ");";

        System.out.println(sqlString);

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            System.out.println("Exception making new Catcher\n" + ex.getMessage());
        }
    }

    public static String getTeam()
    {
        String connectionString = 
        "jdbc:sqlserver://localhost; encrypt=true; DatabaseName=NS.baseball.manager; trustServerCertificate = true;  integratedSecurity=true;";

        try(Connection conn = DriverManager.getConnection(connectionString);
            Statement statement = conn.createStatement();)
        {
            final String sqlString = "SELECT * FROM Teams";

            ResultSet set = statement.executeQuery(sqlString);

            String s = "";

            if(set.next())
                s = set.getString("Name");

            return s;
        }
        catch(Exception ex)
        {
            System.out.println("Exception getting team");
            return null;
        }
    }   

    public ArrayList<Object[]> getPastGames()
    {
        ArrayList<Object[]> list = new ArrayList<>();

        final String sqlString = "SELECT * FROM Games WHERE HasBeenPlayed = 1;";

        try
        {
            Statement statement = conn.createStatement();

            ResultSet set = statement.executeQuery(sqlString);

            while(set.next())
            {
                list.add(new Object[]
                {
                    set.getString("Time"),
                    set.getString("Versus"),
                });
            }

            return list;
        }
        catch(Exception ex)
        {
            System.out.println("Exception handled in SqlController.GetPastGames\n" + ex.getMessage());
            return null;
        }
    }

    //Sort these by date eventually
    public ArrayList<Object[]> getFutureGames()
    {
        ArrayList<Object[]> list = new ArrayList<>();

        final String sqlString = "SELECT * FROM Games WHERE HasBeenPlayed = 0;";

        try
        {
            Statement statement = conn.createStatement();

            ResultSet set = statement.executeQuery(sqlString);

            while(set.next())
            {
                list.add(new Object[]
                {
                    set.getString("Time"),
                    set.getString("Versus"),
                });
            }

            return list;
        }
        catch(Exception ex)
        {
            System.out.println("Exception handled in SqlController.GetPastGames\n" + ex.getMessage());
            return null;
        }
    }

    /**
     * Schedule a game
     * @param versus
     * @param date
     */
    public void scheduleGame(String versus, String date)
    {

        String sqlString = "Insert into Games (Time, Versus, HasBeenPlayed) VALUES('";
        sqlString += date + "','" + versus +"', 0);";

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            System.out.println("Exception making new Game\n" + ex.getMessage());
        }
    }

    public void recordGame(String versus, String date)
    {
        final String sqlString = "UPDATE Games SET HasBeenPlayed = 1"
        + " WHERE Time = '" + date +"';";

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            System.out.println("Exception making new Game\n" + ex.getMessage());
        }
    }

    public void clearData(String positionToClear)
    {
        try
        {
            String sqlString = "";

            switch(positionToClear)
            {
                case "Pitchers" :
                    sqlString = "DELETE FROM PitchingStaff;";
                break;

                case "Catchers" :
                    sqlString = "DELETE FROM CatchingStaff;";
                break;

                case "Infielders" :
                    sqlString = "DELETE FROM Infielders;";
                break;

                case "Outfielders" :
                    sqlString = "DELETE FROM Outfielders;";
                break;
            }

            try(Statement statement = conn.createStatement())
            {
                statement.executeUpdate(sqlString);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception in clearData(*)");
        }
    }


}
