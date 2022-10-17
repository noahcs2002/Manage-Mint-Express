package Graphics;

import java.awt.*;
import javax.swing.*;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

public class FancyFrame extends JFrame implements ISubscriber
{
    JPanel mainPanel = new JPanel();
    MainPanelInfoBar infoBar;
    Navbar navbar; 
    
    public FancyFrame(String team)
    {
        System.out.println(team);
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
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setVisible(true);
        this.setTitle("Manage-Mint Express");
        this.setLocationRelativeTo(null);
    }

    @Override
    public void getAlert(Object change, InfoCode infoCode) 
    {
        revalidate();
        pack();
        repaint();
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
