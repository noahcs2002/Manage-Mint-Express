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
        InformationPanel infoPanel = new InformationPanel("L.A. Dodgers");

        //pitching panel has subscribed to changes from the navabr
        navbar.subscribe(infoPanel);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        this.add(mainPanel);

        this.setJMenuBar(new HelpBar());
        
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setVisible(true);
        this.setTitle("Manage-Mint Express");
        this.setLocationRelativeTo(null);
    }
}
