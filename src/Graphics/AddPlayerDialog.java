package Graphics;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import Engine.SQL;
import java.awt.*;

public class AddPlayerDialog extends JDialog
{
    private String position;
    private String teamChoice;

    private final String[] pitchingTableColumnNames = 
    {
        "Player",
        "Team",
        "IP",
        "H",
        "R",
        "ER",
        "W",
        "SO",
        "HR",
        "Saves",
        "ERA",
        "WHIP",
        "Inj.",
        "Sus.",
        "Injury:",
        "Suspended Reason: ",
        "Number"
    };

    private final String[] catchingTableColumnNames = 
    {
        "Player",
        "Team",
        "GP",
        "GS",
        "IP",
        "TC",
        "PO",
        "A",
        "E",
        "DP",
        "Inj",
        "Injury",
        "Sus.",
        "Suspension Reason",
        "Pos.",
        "Number"
    };

    private final String[] infielderTableColumnNames = 
    {
        "Player",
        "Team",
        "GP",
        "GS",
        "IP",
        "TC",
        "PO",
        "A",
        "E",
        "DP",
        "Inj",
        "Injury",
        "Sus.",
        "Suspension Reason",
        "Pos.",
        "Number"
    };

    private final String[] outfielderTableColumnNames = 
    {
        "Player",
        "Team",
        "GP",
        "GS",
        "IP",
        "TC",
        "PO",
        "A",
        "E",
        "DP",
        "Inj.",
        "Injury",
        "Sus.",
        "Suspension Reason",
        "Pos.",
        "Number"
    };

    public AddPlayerDialog(String team, String position)
    {
        JTable emptyTable = new JTable();
        SQL connectionDriver = new SQL();
        DefaultTableModel model = new DefaultTableModel();
        JButton confButton = new JButton("Confirm");
        this.teamChoice = team;

        try
        {
            switch(position)
            {
                case "Pitchers" :
                    position = "Pitcher";
                    model = new DefaultTableModel(pitchingTableColumnNames, 0);
                    model.addRow(pitchingTableColumnNames);
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                break;

                case "Catchers" :
                    position = "Catcher";
                    model = new DefaultTableModel(catchingTableColumnNames, 0);
                    model.addRow(catchingTableColumnNames);
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                break;

                case "Infielders" :
                    position = "Infielder";
                    model = new DefaultTableModel(infielderTableColumnNames, 0);
                    model.addRow(infielderTableColumnNames);
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                break;

                case "Outfielders" :
                    position = "Outfielder";
                    model = new DefaultTableModel(outfielderTableColumnNames, 0);
                    model.addRow(outfielderTableColumnNames);
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                    model.addRow(new Object[]{});
                break;
            }

            emptyTable = new JTable(model);

        }
        catch(Exception ex)
        {
            System.out.println("Exception Thrown in AddPlayerDialog");
        }

        confButton.addActionListener(e -> 
        {
            
        });
        
        this.setLayout(new BorderLayout());
        this.setTitle("Player Creation Wizard");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1200, 200);
        this.setLocationRelativeTo(null);
        this.add(emptyTable, BorderLayout.CENTER);

        JPanel helperPanel = new JPanel();
        helperPanel.add(confButton);

        this.add(helperPanel, BorderLayout.SOUTH);
    }
}
