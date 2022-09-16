package Members;

import java.util.List;
import Interfaces.IPlayer;
import Misc.Stat;

@SuppressWarnings("unused")
public class Pitcher implements IPlayer
{
    private Stat InningsPitched;
    private Stat Hits;
    private Stat Runs;
    private Stat EarnedRuns;
    private Stat Walks;
    private Stat StrikeOuts;
    private Stat Homeruns;
    private Stat Saves;
    private Stat EarnedRunAverage;
    private Stat WHIP;
    private boolean isSuspended;
    private boolean isInjured;
    private String name;
    private String number;
    private String injuredReason;
    private String suspendedReason;

    public Pitcher()
    {
        this.InningsPitched = new Stat("Innings pitched", 0);
        this.Hits = new Stat("Hits", 0);
        this.Runs = new Stat("Runs", 0);
        this.EarnedRuns = new Stat("Earned runs", 0);
        this.Walks = new Stat("Walks", 0);
        this.StrikeOuts = new Stat("Strikeouts", 0);
        this.Homeruns = new Stat("Homeruns allowed", 0);
        this.Saves = new Stat("Saves made", 0);
        this.EarnedRunAverage = new Stat("Earned Runs on Average", 0);
        this.WHIP = new Stat("Walks and Hits per Inning Pitched (WHIP)", 0);
    }

    public Pitcher( double IP,
                    double Hits,
                    double Runs,
                    double ER,
                    double Walks,
                    double Ks,
                    double HRs,
                    double Saves,
                    double ERA,
                    double WHIP )
    {
        this.InningsPitched = new Stat("Innings pitched", IP);
        this.Hits = new Stat("Hits", Hits);
        this.Runs = new Stat("Runs", Runs);
        this.EarnedRuns = new Stat("Earned runs", ER);
        this.Walks = new Stat("Walks", Walks);
        this.StrikeOuts = new Stat("Strikeouts", Ks);
        this.Homeruns = new Stat("Homeruns allowed", HRs);
        this.Saves = new Stat("Saves made", Saves);
        this.EarnedRunAverage = new Stat("Earned Runs on Average", ERA);
        this.WHIP = new Stat("Walks and Hits per Inning Pitched (WHIP)", WHIP);
    }

    //#region
    public double getInningsPitched() 
{
        return this.InningsPitched.getStat();
    }

    public void setInningsPitched(Stat InningsPitched) 
    {
        this.InningsPitched = new Stat(InningsPitched);
    }

    public double getHits() 
    {
        return this.Hits.getStat();
    }

    public void setHits(Stat Hits) 
    {
        this.Hits = new Stat(Hits);
    }

    public double getRuns() 
    {
        return this.Runs.getStat();
    }

    public void setRuns(Stat Runs) 
    {
        this.Runs = new Stat(Runs);
    }

    public double getEarnedRuns() 
    {
        return this.EarnedRuns.getStat();
    }

    public void setEarnedRuns(Stat EarnedRuns) 
    {
        this.EarnedRuns = new Stat(EarnedRuns);
    }

    public double getWalks() 
    {
        return this.Walks.getStat();
    }

    public void setWalks(Stat Walks) 
    {
        this.Walks = new Stat(Walks);
    }

    public double getStrikeOuts() 
    {
        return this.StrikeOuts.getStat();
    }

    public void setStrikeOuts(Stat StrikeOuts) 
    {
        this.StrikeOuts = new Stat(StrikeOuts);
    }

    public double getHomeruns() 
    {
        return this.Homeruns.getStat();
    }

    public void setHomeruns(Stat Homeruns) 
    {
        this.Homeruns = new Stat(Homeruns);
    }

    public double getSaves() 
    {
        return this.Saves.getStat();
    }

    public void setSaves(Stat Saves) 
    {
        this.Saves = new Stat(Saves);
    }

    public double getEarnedRunAverage() 
    {
        return this.EarnedRunAverage.getStat();
    }

    public void setEarnedRunAverage(Stat EarnedRunAverage) 
    {
        this.EarnedRunAverage = new Stat(EarnedRunAverage);
    }

    public double getWHIP() 
    {
        return this.WHIP.getStat();
    }

    public void setWHIP(Stat WHIP) 
    {
        this.WHIP = new Stat(WHIP);
    }
    //#endregion

    @Override
    public void recordGame(List<Stat> stats) 
    {
        // .stream() works like .Where() in C#
        this.InningsPitched = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Innings pitched")).findFirst().get());

        this.Hits = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Hits")).findFirst().get());

        this.Runs = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Runs")).findFirst().get());

        this.EarnedRuns = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Earned Runs")).findFirst().get());

        this.Walks = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Walks")).findFirst().get());

        this.StrikeOuts = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Strikeouts")).findFirst().get());

        this.Homeruns = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Homeruns Allowed")).findFirst().get());

        this.Saves = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Saves Made")).findFirst().get());

        this.EarnedRunAverage = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Earned Runs on Average")).findFirst().get());

        this.WHIP = new Stat(stats.stream().filter(
            (stat) -> stat.getStatDescription().equals("Walks and Hits per Inning Pitched (WHIP)")).findFirst().get());
    }

    @Override
    public void setup() 
    {
        this.name = "";
        this.number = "";
    }

    @Override
    public void putOnInjuredList(String message) 
    {
        this.isInjured = true;
        this.injuredReason = message;
        
    }

    @Override
    public void putOnSuspensionList(String message) 
    {
        this.isSuspended = true;
        this.suspendedReason = message;
        
    }

}
