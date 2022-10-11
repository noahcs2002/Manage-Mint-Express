package Members;

import java.util.List;
import Interfaces.IPlayer;
import Misc.Stat;

@SuppressWarnings("unused")
public class Pitcher implements IPlayer
{
    public String name;
    public String team;
    public double InningsPitched;
    public double Hits;
    public double Runs;
    public double EarnedRuns;
    public double Walks;
    public double StrikeOuts;
    public double Homeruns;
    public double Saves;
    public double EarnedRunAverage;
    public double WHIP;
    public int isSuspended;
    public String suspendedReason;
    public int isInjured;
    public String injuredReason;
    public String number;


    public Pitcher( String playerName,
                    String teamName,
                    double inningsPitched,
                    double hits,
                    double runs, 
                    double earnedRuns,
                    double walks,
                    double strikeOuts,
                    double homeruns,
                    double saves,
                    double era,
                    double whip,
                    //0/1 for false and true
                    int isInjured,
                    int isSuspended,
                    String injury,
                    String suspension,
                    String number, 
                    String team)
    {
        this.name = playerName;
        this.InningsPitched = inningsPitched;
        this.Hits = hits;
        this.Runs = runs;
        this.EarnedRuns = earnedRuns;
        this.Walks = walks;
        this.StrikeOuts = strikeOuts;
        this.Homeruns = homeruns;
        this.Saves = saves;
        this.EarnedRunAverage = era;
        this.WHIP = whip;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspension;
        this.isInjured = isInjured;
        this.injuredReason = injury;
        this.number = number;
        this.team = team;
    }

    @Override
    public void putOnInjuredList(String message) 
    {
        this.isInjured = 1;
        this.injuredReason = message;
    }

    @Override
    public void putOnSuspensionList(String message) 
    {
        this.isSuspended = 1;
        this.suspendedReason = message;
    }

    @Override
    public void recordGame(List<Stat> stats) 
    {
        
    }

    @Override
    public void setup() 
    {
        
    }
}