package Interfaces;

import java.util.List;

import Misc.Stat;

public interface Pitcher 
{
    /**
     * @param stats What stats were recorded during the game:
     * Takes a list of stats and updates players in the database after a game
     */
    public void recordGame(List<Stat> stats);

    /**
     * Sets up a player in the database
     */
    public void setup();

    /**
     * @param message Why the player is on the Injured List:
     * Puts player on the injured list, and flags them in the database
     */
    public void putOnInjuredList(String message);

    /**
     * @param message Why the player is suspended:
     * Puts the player on the suspension list, and flags them in the database
     */
    public void putOnSuspensionList(String message);
}
