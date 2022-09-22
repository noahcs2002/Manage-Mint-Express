package Graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import java.awt.event.*;

public class FancyFrame extends JFrame
{
    JPanel mainPanel = new JPanel();
    Navbar navbar = new Navbar();


    public FancyFrame()
    {
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(navbar, BorderLayout.NORTH);

        this.add(mainPanel);
        
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(3);
        // this.pack();
        this.setVisible(true);
        this.setTitle("Baseball Team Manager");
        this.setLocationRelativeTo(null);
    }
}
