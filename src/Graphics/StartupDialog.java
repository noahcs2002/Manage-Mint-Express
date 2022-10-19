package Graphics;

import javax.swing.*;
import Controllers.SqlController;
import Engine.ErrorHandler.ErrorHandler;

import java.awt.*;
import java.awt.event.*;

/**
 * Dialog for project start up procs.
 */
public class StartupDialog extends JDialog
{
    public String team;
    
    public StartupDialog()
    {
        SqlController connection = new SqlController();
        JPanel panel = new JPanel();

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
        panel.add(buttonPanel);

        this.setTitle("Welcome to MME");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(400, 00);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.add(panel, BorderLayout.CENTER);
        this.pack();
        

        addTeamButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    team = teamNameField.getText();
                    int teamRank = 1;

                    connection.makeTeam(team, teamRank);

                    dispose();
                }
                catch(Exception ex)
                {
                    ErrorHandler.handle("<DEBUG>: Exception Handled\n\n\n" + ex.getLocalizedMessage());
                }
            }
        });

    }

}
