package Graphics;

import Engine.SQL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddTeamDialog extends JDialog
{

    JPanel panel = new JPanel();
    SQL connection = new SQL();

    public AddTeamDialog()
    {
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
                }
                catch(Exception ex)
                {
                    System.out.println("<DEBUG>: Exception Handled\n\n\n" + ex.getLocalizedMessage());
                }
            }
        });

        panel.add(addTeamButton);
    }
}
