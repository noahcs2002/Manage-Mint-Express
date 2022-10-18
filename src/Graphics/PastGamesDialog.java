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

public class PastGamesDialog extends JDialog implements ISubscriber
{

    JPanel panel;
    DefaultTableModel tableModel;
    JTable table;
    final String[] columnHeaders = 
    {
        "Time",
        "Versus?",
        "Win?",
        "Your Score",
        "Opponents Score",
        "Hits",
        "Errors"
    };

    private final SqlController connection = new SqlController();
    

    public PastGamesDialog()
    {
        JPanel panel = new JPanel();
        SqlController connection = new SqlController();

        final String[] columnHeaders = 
        {
            "Date",
            "Versus?",
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);
        tableModel.addRow(columnHeaders);

        ArrayList<Object[]> games = connection.getPastGames();

        for (Object[] objects : games) 
        {
            tableModel.addRow(objects);
        }

        JTable table = new JTable(tableModel);
        panel.setLayout(new BorderLayout());
        panel.add(table, BorderLayout.CENTER);

        JButton recordGamesButton = new JButton("Record Game");

        JPanel northPanel = new JPanel();
        northPanel.add(recordGamesButton);

        panel.add(northPanel, BorderLayout.NORTH);

        recordGamesButton.addActionListener(e -> 
        {
            RecordGameDialog dialog = new RecordGameDialog();
            this.subscribe(dialog);
        });

        this.add(panel);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setTitle("Manage-Mint Express");
        this.setLocationRelativeTo(null);
    }

    @Override
    public void getAlert(Object change, InfoCode infoCode) 
    {
        this.repaintAndRevalidate();
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

    private void repaintAndRevalidate()
    {
        ArrayList<Object[]> games = connection.getPastGames();
        this.tableModel = new DefaultTableModel(columnHeaders, 0);
        this.tableModel.addRow(columnHeaders);
        
        for (Object[] objects : games) 
        {
            this.tableModel.addRow(objects);
        }
        
        this.table.setModel(tableModel);

        this.revalidate();
        this.repaint();
    }
}
