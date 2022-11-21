package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import DTOs.Catcher;
import DTOs.Infielder;
import DTOs.Outfielder;
import DTOs.Pitcher;
import Engine.ErrorHandler.ErrorHandler;

public class SqlController 
{
    private final String connectionString =  
    "jdbc:sqlserver://localhost; encrypt=true; DatabaseName=NS.baseball.manager; trustServerCertificate = true;  integratedSecurity=true;";
    // "jdbc:mariadb://csclab.murraystate.edu:3306/CSC_325?user=CSC_325&password=Java";

    private Connection conn ;

    /**
     * Serve as a middle man between the API and SQL
     */
    public SqlController()
    {
        try
        {
            conn = DriverManager.getConnection(connectionString);
        }
        catch(Exception ex)
        {
            ErrorHandler.handle("<DEBUG>: EXCEPTION THROWN\n\n\n" + ex.getLocalizedMessage());
        }
    }

    /**
     * Create a team in the database
     * @param teamName Team name to add
     * @param rank Rank of the team
     */
    public void makeTeam(String teamName, int rank)
    {

        String query = "INSERT INTO Teams (Name, Rank) VALUES ('" + teamName +"', " + rank +");";

        try
        {
            ErrorHandler.handle("Beginning query");
            Statement addStatement = conn.createStatement();
            addStatement.executeUpdate(query);

            ErrorHandler.handle("Query ran");

        }
        catch(Exception ex)
        {
            ErrorHandler.handle("<DEBUG>: EXCEPTION THROWN\n\n\n" + ex.getLocalizedMessage());
        }
    }
    
    /**
     * Fetch all pitchers from the database
     * @param dataNames
     * @return All pitchers
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
            ErrorHandler.handle("<DEBUG> : EXCPETION HANDLED : " + ex.getMessage());
            return null;
        }
    }

    /**
     * Fetch all catchers from the database
     * @param teamName Team to fetch all catchers from
     * @return All catchers
     */
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
            ErrorHandler.handle("Error in getCatchers:\n\n" + ex.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Get all infielders
     * @param teamName The team to pull infielders from
     * @return All infielders
     */
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
            ErrorHandler.handle("Exception handled in getInfielders:\n\n" + ex.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Get all outfielders
     * @param teamName Team to pull outfielders from
     * @return All outfielders
     */
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
            ErrorHandler.handle("Exception handled in getInfielders:\n\n" + ex.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Create a pitcher
     * @param pitcher Pitcher DTO to mock for creation
     */
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

        ErrorHandler.handle(sqlString);

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            ErrorHandler.handle("Exception making new pitcher\n" + ex.getMessage());
        }
    }

    /**
     * Create a catcher
     * @param catcher Catcher DTO to mock for creation
     */
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

        ErrorHandler.handle(sqlString);

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            ErrorHandler.handle("Exception making new Catcher\n" + ex.getMessage());
        }
    }

    /**
     * Create an infielder
     * @param infielder Infielder DTO to mock for creation
     */
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

        ErrorHandler.handle(sqlString);

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            ErrorHandler.handle("Exception making new Catcher\n" + ex.getMessage());
        }
    }

    /**
     * Create an outfielder
     * @param outfielder Outfielder DTO to mock for creation
     */
    public void makeOutfielder(Outfielder outfielder)
    {
        final String sqlString = "INSERT INTO Outfielders VALUES('"
        + outfielder.name + "', '" + outfielder.team + "', " + outfielder.gamesPlayed
        + ", " + outfielder.gamesStarted + ", " + outfielder.inningsPlayed 
        + ", " + outfielder.totalChances + ", " + outfielder.putOuts + ", " + outfielder.assists
        + ", " + outfielder.errors + ", " + outfielder.doublePlaysTurned + ", " + outfielder.isInjured
        + ", '" + outfielder.injury + "', " + outfielder.isSuspended + ", '" + outfielder.suspension 
        + "', '" + outfielder.position + "', " + outfielder.number
        + ");";

        ErrorHandler.handle(sqlString);

        try(Statement statement = conn.createStatement())
        {
            statement.executeUpdate(sqlString);
        }
        catch(Exception ex)
        {
            ErrorHandler.handle("Exception making new Catcher\n" + ex.getMessage());
        }
    }

    /**
     * Fetch the current team from the database
     * @return The current team
     */
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
            ErrorHandler.handle("Exception getting team");
            return null;
        }
    }   

    /**
     * Get all past games
     * @return all past games
     */
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
            ErrorHandler.handle("Exception handled in SqlController.GetPastGames\n" + ex.getMessage());
            return null;
        }
    }

    /**
     * Get all upcoming games
     * @return all upcoming games
     */
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
            ErrorHandler.handle("Exception handled in SqlController.GetPastGames\n" + ex.getMessage());
            return null;
        }
    }

    /**
     * Schedule a game
     * @param versus who to play against
     * @param date when to play the game
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
            ErrorHandler.handle("Exception making new Game\n" + ex.getMessage());
        }
    }

    /**
     * Record a game as complete
     * @param versus opponent
     * @param date who they played against
     */
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
            ErrorHandler.handle("Exception making new Game\n" + ex.getMessage());
        }
    }

    /**
     * Clear all data
     * @param positionToClear what position to clear data from
     */
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
            ErrorHandler.handle("Exception in clearData(*)");
        }
    }

    public void applyUpdate(ArrayList<Object[]> data, String team, String pos)
    {
        //start at 1 to avoid column names

        switch(pos)
                {
                    case "Pitchers":
                        for(int i = 1; i < data.size(); i += 1)
                        {
                            try(Statement statement = conn.createStatement())
                            {
                                String sqlString = "UPDATE PitchingStaff "
                                + " SET Player = \'" + data.get(i)[0]
                                + "\', Team = \'" + team
                                + "\', InningsPitched = " + data.get(i)[2]
                                + ", Hits = " + data.get(i)[3]
                                + ", Runs = " + data.get(i)[4]
                                + ", EarnedRuns = " + data.get(i)[5]
                                + ", Walks = " + data.get(i)[6]
                                + ", Strikeouts = " + data.get(i)[7]
                                + ", Homeruns = " + data.get(i)[8]
                                + ", Saves = " + data.get(i)[9]
                                + ", ERA = " + data.get(i)[10]
                                + ", WHIP = " + data.get(i)[11]
                                + ", IsInjured = " + (data.get(i)[12].equals(false) ? 0 : 1)  
                                + ", Injury = \'" + data.get(i)[13]
                                + "\', IsSuspended = " + (data.get(i)[14].equals(false) ? 0 : 1)
                                + ", Suspension = \'" + data.get(i)[15]
                                + "\',Number = " + data.get(i)[16]
                                + " WHERE RefID = " + i;

                                statement.execute(sqlString);
                                // System.out.println(sqlString);
                            }
                            catch(Exception ex)
                            {
                                System.out.println(ex.getLocalizedMessage());
                            }
                        }
                        
                    break;

                    case("Catchers"):
                        for(int i = 1; i < data.size(); i += 1)
                        {
                            try(Statement statement = conn.createStatement())
                            {
                                String sqlString = "UPDATE CatchingStaff "
                                + " SET Name = \'" + data.get(i)[0]
                                + "\', Team = \'" + team
                                + "\', GamesPlayed = " + data.get(i)[2]
                                + ", GamesStarted = " + data.get(i)[3]
                                + ", InningsPlayed = " + data.get(i)[4]
                                + ", TotalChances = " + data.get(i)[5]
                                + ", PutOuts = " + data.get(i)[6]
                                + ", Assists = " + data.get(i)[7]
                                + ", Errors = " + data.get(i)[8]
                                + ", DoublePlays = " + data.get(i)[9]
                                + ", IsInjured = " + (data.get(i)[10].equals(false) ? 0 : 1)  
                                + ", Injury = \'" + data.get(i)[11]
                                + "\', IsSuspended = " + (data.get(i)[12].equals(false) ? 0 : 1)
                                + ", Suspension = \'" + data.get(i)[13]
                                + "\', Position = \'" + data.get(i)[14]
                                + "\', Number = " + data.get(i)[15]
                                + " WHERE RefID = " + i;

                                statement.execute(sqlString);
                                // System.out.println(sqlString);
                            }
                            catch(Exception ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                        }
                    break;

                    case("Infielders"):
                    for(int i = 1; i < data.size(); i += 1)
                    {
                        try(Statement statement = conn.createStatement())
                        {
                            String sqlString = "UPDATE Infielders "
                            + " SET Name = \'" + data.get(i)[0]
                            + "\', Team = \'" + team
                            + "\', GamesPlayed = " + data.get(i)[2]
                            + ", GamesStarted = " + data.get(i)[3]
                            + ", InningsPlayed = " + data.get(i)[4]
                            + ", TotalChances = " + data.get(i)[5]
                            + ", PutOuts = " + data.get(i)[6]
                            + ", Assists = " + data.get(i)[7]
                            + ", Errors = " + data.get(i)[8]
                            + ", DoublePlays = " + data.get(i)[9]
                            + ", IsInjured = " + (data.get(i)[10].equals(false) ? 0 : 1)  
                            + ", Injury = \'" + data.get(i)[11]
                            + "\', IsSuspended = " + (data.get(i)[12].equals(false) ? 0 : 1)
                            + ", Suspension = \'" + data.get(i)[13]
                            + "\', Position = \'" + data.get(i)[14]
                            + "\', Number = " + data.get(i)[15]
                            + " WHERE RefID = " + i;

                            statement.execute(sqlString);
                            // System.out.println(sqlString);
                        }
                        catch(Exception ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                    }

                    break;

                    case("Outfielders"):
                    for(int i = 1; i < data.size(); i += 1)
                    {
                        try(Statement statement = conn.createStatement())
                        {
                            String sqlString = "UPDATE Outfielders "
                            + " SET Name = \'" + data.get(i)[0]
                            + "\', Team = \'" + team
                            + "\', GamesPlayed = " + data.get(i)[2]
                            + ", GamesStarted = " + data.get(i)[3]
                            + ", InningsPlayed = " + data.get(i)[4]
                            + ", TotalChances = " + data.get(i)[5]
                            + ", PutOuts = " + data.get(i)[6]
                            + ", Assists = " + data.get(i)[7]
                            + ", Errors = " + data.get(i)[8]
                            + ", DoublePlays = " + data.get(i)[9]
                            + ", IsInjured = " + (data.get(i)[10].equals(false) ? 0 : 1)  
                            + ", Injury = \'" + data.get(i)[11]
                            + "\', IsSuspended = " + (data.get(i)[12].equals(false) ? 0 : 1)
                            + ", Suspension = \'" + data.get(i)[13]
                            + "\', Position = \'" + data.get(i)[14]
                            + "\', Number = " + data.get(i)[15]
                            + " WHERE RefID = " + i;

                            statement.execute(sqlString);
                            // System.out.println(sqlString);
                        }
                        catch(Exception ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;
                }
        

    }
}
