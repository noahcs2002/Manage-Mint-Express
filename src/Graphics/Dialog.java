package Graphics;

import javax.swing.*;

public class Dialog extends JDialog
{
    public Dialog()
    {
        this.setTitle("Team Creation Wizard");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);

        this.add(new JLabel("Hello world!"));
    }
}
