package Graphics;

import java.awt.*;
import javax.swing.*;
// import java.awt.event.*;
// import java.awt.event.*;

import Interfaces.ISubscriber;

public class FancyFrame extends JFrame implements ISubscriber
{
    JPanel mainPanel = new JPanel();
    Navbar navbar = new Navbar(this);


    public FancyFrame()
    {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(navbar, BorderLayout.NORTH);
        mainPanel.add(new PitchingPanel("LA Dodgers"), BorderLayout.CENTER);

        this.add(mainPanel);
        
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setVisible(true);
        this.setTitle("Baseball Team Manager");
        this.setLocationRelativeTo(null);
    }
}
