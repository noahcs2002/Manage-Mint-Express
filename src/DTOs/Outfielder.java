package DTOs;

/**
 * Outfielder DTO
 */
public class Outfielder
{
    public String name;
    public String team;
    public double gamesPlayed;
    public double gamesStarted;
    public double inningsPlayed;
    public double totalChances;
    public double putOuts;
    public double assists;
    public double errors;
    public double doublePlaysTurned;
    public int isInjured;
    public String injury;
    public int isSuspended;
    public String suspension;
    public String position;
    public String number;

    public Outfielder(
        String name, String team, double gamesPlayed, double gamesStarted,
        double inningsPlayed, double totalChances, double putOuts,
        double assists, double errors, double doublePlaysTurned, 
        int isInjured, String injury, int isSuspended, String suspension, String position, String number
    )
    {
        this.team = team;
        this.name = name ;
        this.gamesPlayed = gamesPlayed ;
        this.gamesStarted = gamesStarted ;
        this.inningsPlayed = inningsPlayed ;
        this.totalChances = totalChances ;
        this.putOuts = putOuts ;
        this.assists = assists ;
        this.errors = errors ;
        this.doublePlaysTurned = doublePlaysTurned ;
        this.isInjured = isInjured ;
        this.injury = injury ;
        this.isSuspended = isSuspended ;
        this.suspension = suspension ;
        this.position = position ;
        this.number = number ;
    }
}
