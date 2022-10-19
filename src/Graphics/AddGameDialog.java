package Graphics;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Controllers.SqlController;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

/**
 * Add a new game. 
 * Subscribable.
 */
public class AddGameDialog extends JDialog implements ISubscribable
{

    ArrayList<ISubscriber> subs = new ArrayList<>();

    public AddGameDialog()
    {
        SqlController connection = new SqlController();

        JPanel superPanel = new JPanel(new BorderLayout());
        JPanel subPanel1 = new JPanel();
        JPanel realSuperPanel = new JPanel();

        realSuperPanel.setLayout(new BoxLayout(realSuperPanel, BoxLayout.PAGE_AXIS));

        JLabel versusLabel = new JLabel("Opponent: ");
        JTextField versusField = new JTextField(25);

        JLabel dateLabel = new JLabel("Date: (yyyy-mm-dd)");
        JTextField dateField = new JTextField(25);

        JPanel versusPanel = new JPanel();
        versusPanel.setLayout(new BoxLayout(versusPanel, BoxLayout.PAGE_AXIS));

        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.PAGE_AXIS));

        versusPanel.add(versusLabel);
        versusPanel.add(versusField);

        datePanel.add(dateLabel);
        datePanel.add(dateField);

        subPanel1.add(versusPanel);
        subPanel1.add(datePanel);

        superPanel.add(subPanel1, BorderLayout.CENTER);

        realSuperPanel.add(superPanel);
        
        JButton scheduleButton = new JButton("Schedule");
        realSuperPanel.add(scheduleButton);

        scheduleButton.addActionListener(e -> 
        {
            connection.scheduleGame(versusField.getText(), dateField.getText());
            alert(null, InfoCode.NEW_GAME);
            this.dispose();
        });

        this.add(realSuperPanel);

        this.setTitle("Game Creation Wizard");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1200, 200);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void alert(Object change, InfoCode infoCode) 
    {
        for (ISubscriber sub : subs) 
            sub.getAlert(change, infoCode);
    }

    @Override
    public void addSubsriber(ISubscriber subscriber) 
    {
        subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) 
    {
        subs.remove(subscriber);
    }
}
