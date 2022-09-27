package Interfaces;

public interface ISubscriber 
{
    /**
     * Recieve an updated from a subscribable.
     * @param change Update to take note of.
     */
    public void recieveUpdate(Object change);

    /**
     * Recieve a code-based update. Interpret the code to decide what updated
     * @param change Update to take note of
     * @param code Code corresponding to update. Can be custom interpreted this way
     */
    public void recieveUpdate(Object change, int code);
}
