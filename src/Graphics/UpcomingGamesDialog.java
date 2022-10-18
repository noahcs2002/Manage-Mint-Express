package Graphics;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Engine.SQL.SqlController;
import Misc.InfoCode;
import Subscribers.ISubscribable;
import Subscribers.ISubscriber;

public class UpcomingGamesDialog extends JDialog implements ISubscriber
{

    JPanel panel;
    DefaultTableModel tableModel;
    JTable table;
    final String[] columnHeaders = 
    {
        "Time",
        "Versus?"
    };

    private final SqlController connection = new SqlController();
    
    public UpcomingGamesDialog()
    {
        panel = new JPanel();

        tableModel = new DefaultTableModel(columnHeaders, 0);
        tableModel.addRow(columnHeaders);

        ArrayList<Object[]> games = connection.getFutureGames();

        for (Object[] objects : games) 
        {
            tableModel.addRow(objects);
        }

        table = new JTable(tableModel);
        panel.setLayout(new BorderLayout());
        panel.add(table, BorderLayout.CENTER);

        JPanel northPanel = new JPanel();

        JButton scheduleGameButton = new JButton("Schedule Game");

        scheduleGameButton.addActionListener(e -> 
        {
            AddGameDialog dialog = new AddGameDialog();
            this.subscribe(dialog);
        });

        northPanel.add(scheduleGameButton);

        panel.add(northPanel, BorderLayout.NORTH);

        this.add(panel);

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setTitle("Upcoming Games");
        this.setLocationRelativeTo(null);
    }

    @Override
    public void getAlert(Object change, InfoCode infoCode) 
    {
        this.dispose();
    }

    @Override
    public void subscribe(ISubscribable subscribable) 
    {
        Objects.requireNonNull(subscribable);

        subscribable.addSubsriber(this);
    }

    @Override
    public void unsubscribe(ISubscribable subscribable) 
    {
        Objects.requireNonNull(subscribable);

        subscribable.removeSubscriber(this);
    }
}
