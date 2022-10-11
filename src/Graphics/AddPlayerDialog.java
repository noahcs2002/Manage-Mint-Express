package Graphics;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controllers.SqlControler;
import Members.Pitcher;

import java.awt.*;

public class AddPlayerDialog extends JDialog
{
    private String position;
    private String teamChoice;
    JTable emptyTable = new JTable();

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
        this.teamChoice = team;
        this.position = position;
        SqlControler connectionDriver = new SqlControler();
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
                break;

                case "Catchers" :
                    position = "Catcher";
                    model = new DefaultTableModel(catchingTableColumnNames, 0);
                    model.addRow(catchingTableColumnNames);
                    model.addRow(new Object[]{});
                break;

                case "Infielders" :
                    position = "Infielder";
                    model = new DefaultTableModel(infielderTableColumnNames, 0);
                    model.addRow(infielderTableColumnNames);
                    model.addRow(new Object[]{});
                break;

                case "Outfielders" :
                    position = "Outfielder";
                    model = new DefaultTableModel(outfielderTableColumnNames, 0);
                    model.addRow(outfielderTableColumnNames);
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
            if(this.position.equals("Pitchers"))
            {

                var name = emptyTable.getModel().getValueAt(1, 0).toString();
                var teamName = emptyTable.getModel().getValueAt(1, 1).toString();
                var inningsPitched = Double.parseDouble(emptyTable.getModel().getValueAt(1, 2).toString());
                var hits = Double.parseDouble(emptyTable.getModel().getValueAt(1, 3).toString());
                var runs = Double.parseDouble(emptyTable.getModel().getValueAt(1, 4).toString());
                var earnedRuns = Double.parseDouble(emptyTable.getModel().getValueAt(1, 5).toString());
                var walks = Double.parseDouble(emptyTable.getModel().getValueAt(1, 6).toString());
                var strikeouts = Double.parseDouble(emptyTable.getModel().getValueAt(1, 7).toString());
                var homeruns = Double.parseDouble(emptyTable.getModel().getValueAt(1, 8).toString());
                var saves = Double.parseDouble(emptyTable.getModel().getValueAt(1, 9).toString());
                var era = Double.parseDouble(emptyTable.getModel().getValueAt(1, 10).toString());
                var whip = Double.parseDouble(emptyTable.getModel().getValueAt(1, 11).toString());
                var isInjured = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 12).toString());
                var isSuspended = Boolean.parseBoolean(emptyTable.getModel().getValueAt(1, 13).toString());
                var injury = emptyTable.getModel().getValueAt(1, 14).toString();
                var suspension = emptyTable.getModel().getValueAt(1, 15).toString();
                var number = emptyTable.getModel().getValueAt(1, 16).toString();

                int isInjuredBit = 0;
                int isSuspendedBit = 0;

                if(isInjured == true)
                    isInjuredBit = 1;
                if(isSuspended == true)
                    isSuspendedBit = 1;    

                connectionDriver.makePitcher(new Pitcher(
                    name, teamName, inningsPitched, hits, runs, earnedRuns, walks, strikeouts,
                    homeruns, saves, era, whip, isInjuredBit, isSuspendedBit, injury, suspension, number, teamChoice
                ));
            }
        });
        
        HelpBar menuBar = new HelpBar();

        this.setJMenuBar(menuBar);
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
