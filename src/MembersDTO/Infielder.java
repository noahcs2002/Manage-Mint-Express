package MembersDTO;

import java.util.List;

import Interfaces.Pitcher;
import Misc.Stat;

/**
 * Stats from 
 * https://www.cbssports.com/mlb/stats/player/fielding/nl/regular/1B/qualifiers/?sortcol=po&sortdir=descending
 */

public class Infielder implements Pitcher
{
    
    Stat GamesPlayed;
    Stat GamesStarted;
    Stat TotalChances;
    Stat Errors;
    Stat PutOuts;
    Stat Assists;
    Stat DoublePlays;
    Stat OutfieldAssists;
    Stat FieldingPercentage;

    public Infielder()
    {
        GamesPlayed = new Stat("Games Played", 0);
        GamesStarted = new Stat("Games Started", 0);
        TotalChances = new Stat("Total Chances", 0);
        Errors = new Stat("Errors", 0);
        PutOuts = new Stat("Put Outs", 0);
        Assists = new Stat("Assists", 0);
        DoublePlays = new Stat("Double Plays", 0);
        OutfieldAssists = new Stat("Outfield Assists", 0);
        FieldingPercentage = new Stat("Fielding Percentage", 0);
    }

    public Infielder(
        Stat GamesPlayed,
        Stat GamesStarted,
        Stat TotalChances,
        Stat Errors,
        Stat PutOuts,
        Stat Assists,
        Stat DoublePlays,
        Stat OutfieldAssists,
        Stat FieldingPercentage )
    {
        this.GamesPlayed = new Stat(GamesPlayed);
        this.GamesStarted = new Stat(GamesStarted);
        this.TotalChances = new Stat(TotalChances);
        this.Errors = new Stat(Errors);
        this.PutOuts = new Stat(PutOuts);
        this.Assists = new Stat(Assists);
        this.DoublePlays = new Stat(DoublePlays);
        this.OutfieldAssists = new Stat(OutfieldAssists);
        this.FieldingPercentage = new Stat(FieldingPercentage);        
    }

    @Override
    public void recordGame(List<Stat> stats) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void putOnInjuredList(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void putOnSuspensionList(String message) {
        // TODO Auto-generated method stub
        
    }
}
