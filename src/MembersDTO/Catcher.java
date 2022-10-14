package MembersDTO;

import Interfaces.Pitcher;
import java.util.List;
import Misc.Stat;

public class Catcher implements Pitcher
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
    public double doublePlays;
    public int isInjured;
    public String injury;
    public int isSuspended;
    public String suspension;
    public String position;
    public String number;

    public Catcher(
        String name,
        String team,
        double gamesPlayed,
        double gamesStarted,
        double inningsPlayed,
        double totalChances,
        double putOuts,
        double assists,
        double errors,
        double doublePlays,
        int isInjured,
        String injury,
        int isSuspended,
        String suspension,
        String number
    )
    {

        this.name = name;
        this.team = team;
        this.gamesPlayed = gamesPlayed;
        this.gamesStarted = gamesStarted;
        this.inningsPlayed = inningsPlayed;
        this.totalChances = totalChances;
        this.putOuts = putOuts;
        this.assists = assists;
        this.errors = errors;
        this.doublePlays = doublePlays;
        this.isInjured = isInjured;
        this.injury = injury;
        this.isSuspended = isSuspended;
        this.suspension = suspension;
        this.number = number;
    }

    @Override
    public void recordGame(List<Stat> stats) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setup() 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void putOnInjuredList(String message) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void putOnSuspensionList(String message) 
    {
        // TODO Auto-generated method stub
        
    }
    
}
