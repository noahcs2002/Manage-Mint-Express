package Graphics;

import javax.swing.*;
import Engine.SQL;
import Interfaces.ISubscriber;

import java.awt.*;
import java.awt.event.*;

public class Navbar extends JPanel
{
        
    private String managerName = "Noah Sternberg";
    JLabel welcomeLabel = new JLabel("Welcome!");
    JComboBox<String> teamChoice = new JComboBox<>();
    JLabel managerNameLabel = new JLabel("Current User: " + managerName);
    JLabel currentTeamLabel = new JLabel("Currently Managing: ");
    SQL connector;

    public Navbar(ISubscriber subscriber)
    {
        connector = new SQL();

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

        teamChoice.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                currentTeamLabel.setText("Currently Managing: " + teamChoice.getSelectedItem().toString());
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
                    public void windowClosed(WindowEvent e) 
                    {
                        try
                        {
                            System.out.println("windowClosed Event Reached");
                            String[] teams = connector.getTeams();

                            for(int i = 0; i < teamChoice.getItemCount(); i += 1)
                            {
                                teamChoice.removeItemAt(i);
                            }
                            
                            for(String s : teams)
                                teamChoice.addItem(s);

                        }
                        catch(Exception ex)
                        {
                            System.out.println("<DEBUG> : Exception Handled \n\n\n" + ex.getMessage());
                        }
                    }
                });
            }
        });

        this.setLayout(new BorderLayout());
        this.add(teamChoice, BorderLayout.EAST);

        JPanel centerRegionPanel = new JPanel();

        centerRegionPanel.add(addTeamButton);

        this.add(centerRegionPanel, BorderLayout.CENTER);

        this.add(managerNameLabel, BorderLayout.WEST);
        this.add(currentTeamLabel, BorderLayout.NORTH);
    }
}
