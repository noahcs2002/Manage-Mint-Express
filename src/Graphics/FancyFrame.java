package Graphics;

import java.awt.*;
import javax.swing.*;

import Engine.ErrorHandler.ErrorHandler;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

/**
 * Main frame.
 * Subscriber.
 */
public class FancyFrame extends JFrame implements ISubscriber
{
    JPanel mainPanel = new JPanel();
    MainPanelInfoBar infoBar;
    Navbar navbar; 
    
    public FancyFrame(String team)
    {
        ErrorHandler.handle(team);
        mainPanel.setLayout(new BorderLayout());
        InformationPanel infoPanel = new InformationPanel(team);
        infoBar = new MainPanelInfoBar(team, "Pitchers");
        navbar = new Navbar(team);
        
        mainPanel.add(infoBar, BorderLayout.NORTH);
        infoBar.subscribe(navbar);
        navbar.subscribe(infoBar);
        
        infoPanel.subscribe(navbar);
        navbar.subscribe(infoPanel);

        infoPanel.subscribe(infoBar);
        infoBar.subscribe(infoPanel);
        
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        this.subscribe(navbar);
        this.subscribe(infoBar);

        this.add(mainPanel);

        this.setJMenuBar(navbar);
        
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setTitle("Manage-Mint Express");
        this.setLocationRelativeTo(null);
    }

    @Override
    public void getAlert(Object change, InfoCode infoCode) 
    {
        if(infoCode == InfoCode.TERMINATE)
            this.dispose();
        else
        {
            revalidate();
            pack();
            repaint();
        }
    }

    @Override
    public void subscribe(ISubscribable subscribable) 
    {
        subscribable.addSubsriber(this);
    }

    @Override
    public void unsubscribe(ISubscribable subscribable) 
    {
        subscribable.removeSubscriber(this);
    }
}
