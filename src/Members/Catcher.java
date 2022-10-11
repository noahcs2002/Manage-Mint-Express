package Members;

import Interfaces.IPlayer;
import java.util.List;
import Misc.Stat;

public class Catcher implements IPlayer
{
    public String name;
    public String team;
    public double gamesPlayed;
    public double gamesStarted;
    public double inningsPlayed;
    public double totalChances;
    public double putOuts;
    public double assists;
    public double doublePlays;
    public boolean isInjured;
    public String injury;
    public boolean isSuspended;
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
        double doublePlays,
        boolean isInjured,
        String injury,
        boolean isSuspended,
        String suspension,
        String position,
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
        this.doublePlays = doublePlays;
        this.isInjured = isInjured;
        this.injury = injury;
        this.isSuspended = isSuspended;
        this.suspension = suspension;
        this.position = position;
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
