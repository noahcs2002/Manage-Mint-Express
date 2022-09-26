package Graphics;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabExpander;

import Engine.SQL;
// import java.awt.event.*;
// import java.sql.DriverManager;
// import java.awt.*;

public class PitchingPanel extends JPanel
{
    JTable pitchersTable;
    SQL connectionDriver = new SQL();

    private final String[] tableColumnNames = 
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

    Object[][] data;

    public PitchingPanel(String teamName)
    {
        try
        {
            DefaultTableModel tableModel = new DefaultTableModel(tableColumnNames, 0);
            
            ArrayList<Object[]> results = connectionDriver.getPitchers(teamName);

            tableModel.addRow(tableColumnNames);

            for (Object[] objects : results) 
            {
                tableModel.addRow(objects);    
            }
                
            this.pitchersTable = new JTable(tableModel);
            this.add(pitchersTable);

        }
        catch(Exception ex)
        {
            System.out.println("<DEBUG> EXCEPTION HANDLED (PitchersPanel): " + ex.getMessage());
        }
    }
    
}
