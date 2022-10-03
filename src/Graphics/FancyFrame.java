package Graphics;

import java.awt.*;
import javax.swing.*;


public class FancyFrame extends JFrame
{
    JPanel mainPanel = new JPanel();
    Navbar navbar = new Navbar();


    public FancyFrame()
    {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(navbar, BorderLayout.NORTH);
        InformationPanel pitchingPanel = new InformationPanel("LA Dodgers");

        //pitching panel has subscribed to changes from the navabr
        navbar.subscribe(pitchingPanel);

        mainPanel.add(pitchingPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setVisible(true);
        this.setTitle("Manage-Mint Express");
        this.setLocationRelativeTo(null);
    }
}
