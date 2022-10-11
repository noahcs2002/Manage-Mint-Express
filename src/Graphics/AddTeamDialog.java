package Graphics;

import javax.swing.*;

import Controllers.SqlControler;
import Interfaces.ISubscribable;
import Interfaces.ISubscriber;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;

public class AddTeamDialog extends JDialog implements ISubscribable
{

    JPanel panel = new JPanel();
    SqlControler connection = new SqlControler();
    private String newTeamName;

    ArrayList<ISubscriber> subs;

    public AddTeamDialog(ISubscriber sub)
    {
        subs = new ArrayList<>();
        subs.add(sub);

        

        this.setTitle("Team Creation Wizard");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        // GroupLayout layout = new GroupLayout(panel);
        // layout.setAutoCreateGaps(true);
        // layout.setAutoCreateContainerGaps(true);
        // panel.setLayout(layout);

        this.add(panel, BorderLayout.CENTER);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel teamNamePanel = new JPanel();
        teamNamePanel.setLayout(new FlowLayout());

        JPanel teamRankPanel = new JPanel();
        teamRankPanel.setLayout(new FlowLayout());  

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JLabel teamNameLabel = new JLabel("Team Name: ");
        JLabel teamRankLabel = new JLabel("Team Rank: ");

        JTextField teamNameField = new JTextField(25);
        JTextField teamRankField = new JTextField(25);

        JButton addTeamButton = new JButton("Add Team");

        buttonPanel.add(addTeamButton);

        teamNamePanel.add(teamNameLabel);
        teamNamePanel.add(teamNameField);

        teamRankPanel.add(teamRankLabel);
        teamRankPanel.add(teamRankField);

        panel.add(teamNamePanel);
        panel.add(teamRankPanel);

        addTeamButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String teamName = teamNameField.getText();
                    int teamRank = Integer.parseInt(teamRankField.getText());

                    connection.makeTeam(teamName, teamRank);

                    String update = teamName;
                    System.out.println(update);
                    sendNotification(subs, update, 0);
                    dispose();
                }
                catch(Exception ex)
                {
                    System.out.println("<DEBUG>: Exception Handled\n\n\n" + ex.getLocalizedMessage());
                }
            }
        });

        panel.add(addTeamButton);
        this.pack();

    }
    
    public String getNewTeamName()
    {
        return this.newTeamName;
    }

    @Override
    public void subscribe(ISubscriber subscriber) 
    {
        subs.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) 
    {   
        subs.remove(subscriber);
    }

    @Override
    public void sendNotification(List<ISubscriber> subscribers, Object change, int code) 
    {
        for (ISubscriber iSubscriber : subscribers) {
            iSubscriber.recieveUpdate(change, code);
        }
    }

    @Override
    public void sendNotification(List<ISubscriber> subscribers, Object change) 
    {
        for (ISubscriber iSubscriber : subscribers) {
            iSubscriber.recieveUpdate(change);
        }
    }
}
