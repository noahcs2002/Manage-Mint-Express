package Graphics;

import Subscribers.ISubscribable;
import Controllers.SqlController;
import Misc.InfoCode;
import Subscribers.ISubscriber;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 * Main panel information bar, for changing drop downs and clearing data etc.
 */
@SuppressWarnings("unused")
public class MainPanelInfoBar extends JPanel implements ISubscriber, ISubscribable
{
    private String team;
    private String pos;    

    private String managerName = "Noah";
    JLabel welcomeLabel = new JLabel("Welcome!");
    JComboBox<String> positionChoice = new JComboBox<>();
    SqlController connector;
    ArrayList<ISubscriber> subscribers;

    /**
     * Construct a new information bar
     * @param team Team in question
     * @param pos Default pos
     */
    public MainPanelInfoBar(String team, String pos)
    {
        this.team = team;
        this.pos = pos;

        subscribers = new ArrayList<>();
        connector = new SqlController();
        String[] positions = 
        {
            "Pitchers",
            "Catchers",
            "Infielders",
            "Outfielders",
        };

        for(String p : positions)
            positionChoice.addItem(p);

        positionChoice.addActionListener(e -> 
        {
            this.pos = positionChoice.getSelectedItem().toString();
            alert(this.pos, InfoCode.POSITION_CHANGE);
        });

        JButton clearAllButton = new JButton("Clear all");

        clearAllButton.addActionListener(e -> 
        {
            connector.clearData(positionChoice.getSelectedItem().toString());
            alert(null, InfoCode.CLEARED_DATA);
        });

        this.setLayout(new BorderLayout());
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());
        flowPanel.add(clearAllButton);
        flowPanel.add(positionChoice);

        
        this.add(flowPanel, BorderLayout.EAST);

        JPanel centerRegionPanel = new JPanel();

        this.add(centerRegionPanel, BorderLayout.CENTER);
    }

    @Override
    public void alert(Object change, InfoCode infoCode) 
    {
        for (ISubscriber sub : subscribers) 
        {
            sub.getAlert(change, infoCode);    
        }
    }

    @Override
    public void addSubsriber(ISubscriber subscriber) 
    {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) 
    {
        subscribers.remove(subscriber);
    }

    @Override
    public void getAlert(Object change, InfoCode infoCode) 
    {
        switch(infoCode)
        {
            case TEAM_CHANGE :
                this.team = (String) change;
            break;

            default : break;
        }
    }

    @Override
    public void subscribe(ISubscribable subscribable) 
    {
        subscribable.addSubsriber(this);
    }

    @Override
    public void unsubscribe(ISubscribable subscribable) 
    {
        subscribable.removeSubscriber(this);
    }
}
