package Graphics;

// import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
// import java.awt.event.*;

public class FancyFrame extends JFrame
{

    Navbar navbar = new Navbar();
    FancyPanel panel  = new FancyPanel();

    public FancyFrame()
    {
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setTitle("Baseball Team Manager");
        this.setLocationRelativeTo(null);
        this.add(panel);
    }


}
