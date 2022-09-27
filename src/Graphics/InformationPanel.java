package Graphics;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Engine.SQL;
// import java.awt.event.*;
// import java.sql.DriverManager;
// import java.awt.*;
import Interfaces.ISubscriber;

public class InformationPanel extends JPanel implements ISubscriber
{
    JTable informationTable;
    SQL connectionDriver = new SQL();

    private String currentPositionSelection;
    private String currentTeamSelection;

    private final String[] pitchingTableColumnNames = 
    {
        "Player",
        "Team",
        "Innings Pitched",
        "Hits",
        "Runs",
        "Earned Runs",
        "Walks",
        "Strike outs",
        "Homeruns",
        "Saves",
        "ERA",
        "WHIP",
        "Injured?",
        "Suspended?",
        "Player Number",
        "Injury: ",
        "Suspension Reason "
    };

    private final String[] catchingTableColumnNames = 
    {
        "Player",
        "Team",
        "Games Played",
        "Games Started",
        "Innings Played",
        "Total Chances",
        "Putouts",
        "Assists",
        "Errors",
        "Double Plays",
        "Stolen Bases Allowed",
        "Steals Caught",
        "Stolen Base Allowed Percentage",
        "Wild Pitches",
        "Fielding Percentage",
        "Injured",
        "Injury",
        "Suspended?",
        "Suspension Reason",
    };

    private final String[] infielderTableColumnNames = 
    {
        "Player",
        "Team",
        "Games Played",
        "Games Started",
        "Innings Played",
        "Total Chances",
        "Putouts",
        "Assists",
        "Errors",
        "Double Plays",
        "Injured",
        "Injury",
        "Suspended?",
        "Suspension Reason",
        "Position"
    };

    private final String[] outfielderTableColumnNames = 
    {
        "Player",
        "Team",
        "Games Played",
        "Games Started",
        "Innings Played",
        "Total Chances",
        "Putouts",
        "Assists",
        "Errors",
        "Double Plays",
        "Injured",
        "Injury",
        "Suspended?",
        "Suspension Reason",
    };

    Object[][] data;

    public InformationPanel(String teamName)
    {
        currentPositionSelection = "Pitchers";
        currentTeamSelection = "LA Dodgers";
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
        // 0 -> team change
        // 1 -> position change
        
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
}
