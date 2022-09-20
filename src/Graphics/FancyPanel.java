package Graphics;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;

public class FancyPanel extends JPanel
{
    
    public FancyPanel()
    {
        this.setLayout(new BorderLayout());
        Navbar nav = new Navbar();

        this.add(nav, BorderLayout.NORTH);
    }



}
