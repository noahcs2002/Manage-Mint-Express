package Graphics;

import java.awt.*;
import javax.swing.*;

import Interfaces.ISubscribable;
import Interfaces.ISubscriber;


public class FancyFrame extends JFrame implements ISubscriber
{
    JPanel mainPanel = new JPanel();
    InfoBar infoBar = new InfoBar();
    Navbar navbar = new Navbar("L.A. Dodgers");


    public FancyFrame()
    {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(infoBar, BorderLayout.NORTH);
        InformationPanel infoPanel = new InformationPanel("L.A. Dodgers");

        //pitching panel has subscribed to changes from the navbar
        infoBar.subscribe(infoPanel);
        navbar.subscribe(infoBar);
        infoBar.subscribe(navbar);


        mainPanel.add(infoPanel, BorderLayout.CENTER);

        this.add(mainPanel);

        this.setJMenuBar(navbar);
        
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
