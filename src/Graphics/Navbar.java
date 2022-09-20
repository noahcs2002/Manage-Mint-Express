package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Navbar extends JPanel
{
        
    private String managerName = "Noah Sternberg";
    JLabel welcomeLabel = new JLabel("Welcome!");
    JComboBox<String> teamChoice = new JComboBox<>(new String[]{
        "LA Dodgers",
        "Chicago Cubs",
        "Atlanta Braves",
        "New York Yankees",
        "+ Team"
    });
    JLabel managerNameLabel = new JLabel("Current User: " + managerName);
    JLabel currentTeamLabel = new JLabel("Currently Managing: ");

    public Navbar()
    {
        currentTeamLabel.setText(currentTeamLabel.getText() + teamChoice.getSelectedItem().toString());
        teamChoice.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                currentTeamLabel.setText("Currently Managing: " + teamChoice.getSelectedItem().toString());
                Random r = new Random();

                if(teamChoice.getSelectedItem().toString().equalsIgnoreCase("+ Team"))
                {

                    new Dialog();
                    teamChoice.addItem("New Team " + r.nextInt());
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(teamChoice, BorderLayout.EAST);
        this.add(managerNameLabel, BorderLayout.WEST);
        this.add(currentTeamLabel, BorderLayout.NORTH);
    }
}
