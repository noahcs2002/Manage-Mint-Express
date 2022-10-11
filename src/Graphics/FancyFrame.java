package Graphics;

import java.awt.*;
import javax.swing.*;

import Interfaces.ISubscribable;
import Interfaces.ISubscriber;


public class FancyFrame extends JFrame implements ISubscriber
{
    JPanel mainPanel = new JPanel();
    Navbar navbar = new Navbar();
    UpperNavbar upperNavbar = new UpperNavbar();


    public FancyFrame()
    {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(navbar, BorderLayout.NORTH);
        InformationPanel infoPanel = new InformationPanel("L.A. Dodgers");

        //pitching panel has subscribed to changes from the navabr
        navbar.subscribe(infoPanel);
        upperNavbar.subscribe(navbar);


        mainPanel.add(infoPanel, BorderLayout.CENTER);

        this.add(mainPanel);

        this.setJMenuBar(upperNavbar);
        
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setVisible(true);
        this.setTitle("Manage-Mint Express");
        this.setLocationRelativeTo(null);
    }


    @Override
    public void recieveUpdate(Object change) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void recieveUpdate(Object change, int code) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void subscribeTo(ISubscribable subscribable) {
        // TODO Auto-generated method stub
        
    }
}
