package DTOs;

import java.time.LocalDate;

public class Game 
{
    public final LocalDate date;
    public final String versusWho;
    public final Boolean didWin;
    public final Double yourScore;
    public final Double oppScore;
    public final Double hits;
    public final Double errors;

    public Game(
        final LocalDate date,
        final String versusWho,
        final Boolean didWin,
        final Double yourScore,
        final Double oppScore,
        final Double hits,
        final Double errors
    )
    {
        this.date = date;
        this.versusWho = versusWho;
        this.didWin = didWin;
        this.yourScore = yourScore;
        this.oppScore = oppScore;
        this.hits = hits;
        this.errors = errors;
    }

}
