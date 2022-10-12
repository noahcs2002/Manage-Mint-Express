package Graphics;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controllers.SqlControler;
import Interfaces.ISubscribable;
import Interfaces.ISubscriber;

public class InformationPanel extends JPanel implements ISubscriber
{
    JTable informationTable;
    SqlControler connectionDriver = new SqlControler();

    private String currentPositionSelection;
    private String currentTeamSelection;

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


    public InformationPanel(String teamName)
    {
        currentPositionSelection = "Pitchers";
        currentTeamSelection = "L.A. Dodgers";
        try
        {
            DefaultTableModel tableModel = new DefaultTableModel(pitchingTableColumnNames, 0);
            
            ArrayList<Object[]> results = connectionDriver.getPitchers(teamName);

            tableModel.addRow(pitchingTableColumnNames);

            for (Object[] objects : results) 
            {
                tableModel.addRow(objects);    
            }
                
            this.informationTable = new JTable(tableModel);
            this.add(informationTable);

        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG> EXCEPTION HANDLED (PitchersPanel): " + ex.getMessage());
        }
    }

    @Override
    public void recieveUpdate(Object change) 
    {
            DefaultTableModel tableModel = new DefaultTableModel(pitchingTableColumnNames, 0);

            this.informationTable.setModel(tableModel);
            
            ArrayList<Object[]> results = connectionDriver.getPitchers(change.toString());

            tableModel.addRow(pitchingTableColumnNames);

            for (Object[] objects : results) 
                tableModel.addRow(objects);    
    }

    @Override
    public void recieveUpdate(Object change, int code) 
    {
        switch(code) 
        {
            case 0 :

                currentTeamSelection = change.toString();
                updateInformation(currentTeamSelection, currentPositionSelection);

            break;

            case 1:
            
                currentPositionSelection = change.toString();
                updateInformation(currentTeamSelection, currentPositionSelection);

            break;

            
            case 1452316, 145233, 1452310, 1452315 :
                this.repaint();
                this.revalidate();
            break;

            default :
                System.out.println("Unknown code handled in InformationPanel: \nCode: " + code );
            break;
        }        
    }

    public void updateInformation(String team, String position)
    {
        DefaultTableModel tableModel ;
        ArrayList<Object[]> results ;

        switch(position)
        {
            case "Pitchers" :
                results = connectionDriver.getPitchers(team);
                tableModel = new DefaultTableModel(pitchingTableColumnNames, 0);
                this.informationTable.setModel(tableModel);
                tableModel.addRow(pitchingTableColumnNames);

                for (Object[] objects : results) 
                    tableModel.addRow(objects); 
            break;

            case "Catchers" :
                results = connectionDriver.getCatchers(team);
                tableModel = new DefaultTableModel(catchingTableColumnNames, 0);
                this.informationTable.setModel(tableModel);
                tableModel.addRow(catchingTableColumnNames);

                for (Object[] objects : results) 
                    tableModel.addRow(objects); 
            break;

            case "Infielders" :
                results = connectionDriver.getInfielders(team);
                tableModel = new DefaultTableModel(infielderTableColumnNames, 0);
                this.informationTable.setModel(tableModel);
                tableModel.addRow(infielderTableColumnNames);

                for (Object[] objects : results) 
                    tableModel.addRow(objects); 
            break;

            case "Outfielders" :
                results = connectionDriver.getOutfielders(team);
                tableModel = new DefaultTableModel(outfielderTableColumnNames, 0);
                this.informationTable.setModel(tableModel);
                tableModel.addRow(outfielderTableColumnNames);

                for (Object[] objects : results) 
                    tableModel.addRow(objects); 
            break;
        }
    }

    @Override
    public void subscribeTo(ISubscribable subscribable) {
        // TODO Auto-generated method stub
        
    }
}
