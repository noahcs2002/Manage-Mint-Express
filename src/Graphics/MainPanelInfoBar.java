package Graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Engine.SQL.SqlController;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

@SuppressWarnings("unused")
public class MainPanelInfoBar extends JPanel implements ISubscriber, ISubscribable
{
    private String team;
    private String pos;    

    private String managerName = "Noah";
    JLabel welcomeLabel = new JLabel("Welcome!");
    JComboBox<String> positionChoice = new JComboBox<>();
    SqlController connector;
    ArrayList<ISubscriber> subs;

    /**
     * Construct a new information bar
     * @param team Team in question
     * @param pos Default pos
     */
    public MainPanelInfoBar(String team, String pos)
    {
        this.team = team;
        this.pos = pos;

        subs = new ArrayList<>();
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
        for (ISubscriber sub : subs) 
            sub.getAlert(change, infoCode);
    }

    @Override
    public void addSubsriber(ISubscriber subscriber) 
    {
        Objects.requireNonNull(subscriber);

        if(!subs.contains(subscriber))
            subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) 
    {
        Objects.requireNonNull(subscriber);

        if(subs.contains(subscriber))
            subs.remove(subscriber);    
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
