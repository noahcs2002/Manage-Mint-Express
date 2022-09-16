package Interfaces;

public interface IHeadManager
{
    /**
     * {@summary}
     * Setup task for manager
     * @param name User name
     * @param password User password
     */
    public void setup(String name, String password);

    /**
     * {@summary}
     * Suspend a player (memeber) for (reason)
     * @param memeber Memeber to suspend
     * @param reason Reason for suspension
     */
    public void suspendPlayer(IMember memeber, String reason);

    /**
     * {@summary}
     * Puts a player on the injured list
     * @param member Memeber that is injured
     */
    public void putPlayerOnInjuredList(IMember member);

    /**
     * {@summary}
     * Offers a trade to another team
     * @param team Team to offer the trade to
     * @param member Memeber to offer as a trade
     * @param req Memeber to request in the trade
     */
    public void offerTrade(ITeam team, IMember member, IMember req);

    /**
     * {@summary} Run updates to player stats after a game
     * @param team Team to run updates against
     * @param game Game to pull stats from
     */
    public void runUpdates(ITeam team, IGame game);
}
