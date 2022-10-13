package Subscribers;

import MembersDTO.InfoCode;

public interface ISubscriber
{
    /**
     * @category MUST BE OVERRIDDEN
     * @param change Change to take note of
     */
    public void getAlert(Object change, InfoCode infoCode);

    /**
     * Begin watching a subscribable
     * @param subscribable Subscribable to watch
     */
    public void subscribe(ISubscribable subscribable);

    /**
     * Stop watching a subscribable
     * @param subscribable Subscribable to stop watching
     */
    public void unsubscribe(ISubscribable subscribable);
}
