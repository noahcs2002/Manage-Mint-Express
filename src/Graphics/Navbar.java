package Graphics;

import javax.swing.*;
import Engine.SQL;
import Interfaces.ISubscribable;
import Interfaces.ISubscriber;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Navbar extends JPanel implements ISubscribable
{
        
    private String managerName = "Noah";
    JLabel welcomeLabel = new JLabel("Welcome!");
    JComboBox<String> teamChoice = new JComboBox<>();
    JComboBox<String> positionChoice = new JComboBox<>();
    JLabel managerNameLabel = new JLabel("Current User: " + managerName);
    JLabel currentTeamLabel = new JLabel("Currently Managing: ");
    SQL connector;
    ArrayList<ISubscriber> subscribers;

    public Navbar()
    {
        subscribers = new ArrayList<>();
        connector = new SQL();
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

        positionChoice.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                sendNotification(subscribers, positionChoice.getSelectedItem().toString(), 1);                
            }
            
        });

        teamChoice.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                currentTeamLabel.setText("Currently Managing: " + teamChoice.getSelectedItem().toString());
                sendNotification(subscribers, teamChoice.getSelectedItem().toString(), 0);
            }
        });

        JButton addTeamButton = new JButton("Add Team");

        addTeamButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                AddTeamDialog newTeamDialog = new AddTeamDialog();
                
                newTeamDialog.addWindowListener(new WindowAdapter()
                {
                    // public void windowClosed(WindowEvent e) 
                    // {
                    //     try
                    //     {
                    //         System.out.println("windowClosed Event Reached");


                    //     }
                    //     catch(Exception ex)
                    //     {
                    //         System.out.println("<DEBUG> : Exception Handled \n\n\n" + ex.getMessage());
                    //     }
                    // }
                });
            }
        });

        JButton addPlayerButton = new JButton("Add Player");

        addPlayerButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // new AddPlayerDialog(teamChoice.getSelectedItem().toString(), positionChoice.getSelectedItem().toString());
            }
        });

        this.setLayout(new BorderLayout());
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());
        flowPanel.add(addPlayerButton);
        flowPanel.add(positionChoice);
        flowPanel.add(teamChoice);
        
        this.add(flowPanel, BorderLayout.EAST);

        JPanel centerRegionPanel = new JPanel();

        centerRegionPanel.add(addTeamButton);

        this.add(centerRegionPanel, BorderLayout.CENTER);

        this.add(managerNameLabel, BorderLayout.WEST);
        this.add(currentTeamLabel, BorderLayout.NORTH);
    }

    @Override
    public void subscribe(ISubscriber subscriber) 
    {
        this.subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) 
    {
        this.subscribers.remove(subscriber);
    }

    @Override
    /**
     * @apiNote CODE LIST: 
     * 0 -> Team selection has been updated
     * 1 -> Position selection has been updated
     */
    public void sendNotification(List<ISubscriber> subscribers, Object change, int code) 
    {
        for(ISubscriber i : subscribers)
            i.recieveUpdate(change, code);
    }

    @Override
    public void sendNotification(List<ISubscriber> subscribers, Object change) 
    {
        for (ISubscriber iSubscriber : subscribers) 
            iSubscriber.recieveUpdate(change);    
    }

    
}
