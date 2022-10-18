package Graphics;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

public class ResetDialog implements ISubscribable
{

    ArrayList<ISubscriber> subs;

    public ResetDialog()
    {
        JOptionPane.showMessageDialog(null, "WARNING: THIS ACTION IS PERMANANT, AND CANNOT BE UNDONE.", "WARNING!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void alert(Object change, InfoCode infoCode) 
    {
        for (ISubscriber sub : subs) 
            sub.getAlert(change, infoCode);    
        
    }

    @Override
    public void addSubsriber(ISubscriber subscriber) 
    {
        this.subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) 
    {
        this.subs.remove(subscriber);
    }
    
}
