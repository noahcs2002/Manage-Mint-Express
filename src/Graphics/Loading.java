package Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Loading extends JFrame
{
    private JProgressBar progressBar;
    private JLabel compsInitLabel;
    
    public Loading()
    {

        JLabel loadingLabel = new JLabel("Loading ...");

        JPanel panel = new JPanel();

        progressBar = new JProgressBar(0,2000);

        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setBounds(40,40,160,30);
        compsInitLabel = new JLabel("Components Initialising");

        panel.add(loadingLabel);
        panel.add(progressBar);
        panel.add(compsInitLabel);

        this.add(panel);

        this.setSize(200,150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void loading()
    {
        for(int i = 0; i <= 2000; i += 50)
        {
            progressBar.setValue(i);

            try
            {
                Thread.sleep(150);
            }
            catch(Exception exc)
            {

            }
        }
    }
}
