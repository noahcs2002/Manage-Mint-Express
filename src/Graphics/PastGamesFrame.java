package Graphics;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Controllers.SqlController;

public class PastGamesFrame extends JFrame
{

    public PastGamesFrame()
    {
        JPanel panel = new JPanel();
        SqlController connection = new SqlController();

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
        panel.add(new GamesInfoBar(), BorderLayout.NORTH);
        this.add(panel);

        

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setTitle("Manage-Mint Express");
        this.setLocationRelativeTo(null);
    }
}
