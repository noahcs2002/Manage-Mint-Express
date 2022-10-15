package Subscribers;

import Misc.InfoCode;

public interface ISubscribable
{
    /**
     * Alert all subscribers of a change
     * @param change Change to alert subscribers of 
     */
    public void alert(Object change, InfoCode infoCode);

    /**
     * Add a watcher to the list of watchers
     * @param subscriber subscriber to add to list of watchers
     */
    public void addSubsriber(ISubscriber subscriber);

    /**
     * Remove a watcher from the list of watchers
     * @param subscriber subscriber to remove from list of watchers
     */
    public void removeSubscriber(ISubscriber subscriber);
}
