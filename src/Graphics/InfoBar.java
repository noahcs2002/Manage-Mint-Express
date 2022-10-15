package Graphics;

import Subscribers.ISubscribable;
import Controllers.SqlController;
import Misc.InfoCode;
import Subscribers.ISubscriber;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class InfoBar extends JPanel implements ISubscriber, ISubscribable
{
    private String team;
    private String pos;    

    private String managerName = "Noah";
    JLabel welcomeLabel = new JLabel("Welcome!");
    JComboBox<String> teamChoice = new JComboBox<>();
    JComboBox<String> positionChoice = new JComboBox<>();
    JLabel managerNameLabel = new JLabel("Current User: " + managerName);
    JLabel currentTeamLabel = new JLabel("Currently Managing: ");
    SqlController connector;
    ArrayList<ISubscriber> subscribers;

    /**
     * Construct a new information bar
     * @param team Default team
     * @param pos Default pos
     */
    public InfoBar(String team, String pos)
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

        try
        {
            String[] teams = connector.getTeams();

            for(String s : teams)
                teamChoice.addItem(s);
        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG>: EXCEPTION THROWN\n\n\n"+ex.getLocalizedMessage());
        }

        currentTeamLabel.setText(currentTeamLabel.getText() + teamChoice.getSelectedItem().toString());

        positionChoice.addActionListener(e -> 
        {
            this.pos = positionChoice.getSelectedItem().toString();
            alert(this.pos, InfoCode.POSITION_CHANGE);
        });

        this.setLayout(new BorderLayout());
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());
        // flowPanel.add(addPlayerButton);
        flowPanel.add(positionChoice);
        
        this.add(flowPanel, BorderLayout.EAST);

        JPanel centerRegionPanel = new JPanel();

        // centerRegionPanel.add(addTeamButton);

        this.add(centerRegionPanel, BorderLayout.CENTER);

        this.add(managerNameLabel, BorderLayout.WEST);
        this.add(currentTeamLabel, BorderLayout.NORTH);
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

            case NEW_TEAM :
                
                teamChoice.addItem((String) change);

                teamChoice.revalidate();
                teamChoice.repaint();
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
