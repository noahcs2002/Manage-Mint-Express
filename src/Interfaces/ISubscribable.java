package Interfaces;

import java.util.List;

/**
 * Makes an object subscribable. Everyime that this gets updated, a list of subscribers will be notified of those changes
 */
public interface ISubscribable 
{
    /**
     * Add a subscriber to the list of objects to notify of updates
     * @param subscriber Subscriber to add to the list
     */
    public void subscribe(ISubscriber subscriber);

    /**
     * Remove a subscriber from the list of objects to notify of updates
     * @param subscriber Subscriber to remove from the list
     */
    public void unsubscribe(ISubscriber subscriber);

    /**
     * Send an update to all subscribers. 
     * @apiNote Use this when there are more than one types of update that you could be sending
     * @param subscribers List of subscribers to notify
     * @param change Update to notify subscriber of
     * @param code Update code. Used for multiple different kinds of updates
     */
    public void sendNotification(List<ISubscriber> subscribers, Object change, int code);

    /**
     * Sends an update to all subscribers
     * @param subscribers List of subscribers to notify
     * @param change Update to notify subscribers of 
     */
    public void sendNotification(List<ISubscriber> subscribers, Object change);
}
