package wars;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * GameGUI - a basic Swing GUI to interact with the BATHS SeaBattles.
 * It allows the player to view ships, decommission them, and clear action.
 *  
 * @author A.A.Marczyk
 * @version 20/02/12
 */
public class GameGUI 
{
    private BATHS gp = new SeaBattles("Fred");
    private JFrame myFrame = new JFrame("Game GUI");
    private Container contentPane = myFrame.getContentPane();
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton fightBtn = new JButton("Fight Encounter");
    private JButton viewBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");
    private JButton EncBtn = new JButton("encounter");
    
    
    private JPanel eastPanel = new JPanel();

    public static void main(String[] args) {
        new GameGUI();
    }
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        contentPane.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));

        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightEncHandler());
        
        eastPanel.add(clearBtn);
        viewBtn.addActionListener(new ViewStateHandler());
        
        eastPanel.add(clearBtn);
        clearBtn.addActionListener(new ClearHandler());
        eastPanel.add(quitBtn);
        
        eastPanel.add(EncBtn);
        clearBtn.addActionListener(new isEnounterHandler());
        eastPanel.add(EncBtn);

        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
        
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("Ships");
        menubar.add(fileMenu);
        
        JMenuItem listShipItem = new JMenuItem("List reserve Ships");
        listShipItem.addActionListener(new ListFleetHandler());
        fileMenu.add(listShipItem);
        
        JMenuItem decommission = new JMenuItem("De-commission Ship");
        decommission.addActionListener(new DecommissionHandler());
        fileMenu.add(decommission);
        
        JMenuItem listSquadronItem = new JMenuItem("List Squadron");
        listSquadronItem.addActionListener(new ListSquadronHandler());
        fileMenu.add(listSquadronItem);
        
        JMenuItem viewShipItem = new JMenuItem("View a Ship");
        viewShipItem.addActionListener(new ViewShipHandler());
        fileMenu.add(viewShipItem);
        
        JMenuItem commission = new JMenuItem("Commission Ship");
        commission.addActionListener(new CommissionHandler());
        fileMenu.add(commission);
        
 
        
    }


    
    private class ListFleetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = gp.getReserveFleet();
            listing.setText(xx);
            
        }
    }

    
    private class ClearHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setText("");
            listing.setVisible(false);            
        }
    }


    
    private class DecommissionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = "";
            String inputValue = JOptionPane.showInputDialog("Ship code ?: ");
            
            if(gp.isInSquadron(inputValue)) 
            {
                gp.decommissionShip(inputValue);
                result = inputValue + " is decommissioned";
            }
            else
            {
                result = inputValue + " not in fleet";
            }
            JOptionPane.showMessageDialog(myFrame,result);    
        }
    }
    
    private class CommissionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String inputValue = JOptionPane.showInputDialog("Ship name to commission:");
            String result = gp.commissionShip(inputValue);
            JOptionPane.showMessageDialog(myFrame, result);    
        }
    }
    
   
    private class ClearButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setVisible(false);
            clearBtn.setVisible(false);
        }
    }
    
    private class ViewStateHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String result = gp.toString();
            listing.setText(result);
        }
    }

    
    private class ListSquadronHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true); //show text area
            String info = gp.getSquadron(); //get squadron info
            listing.setText(info); //display info
    
        }
    }   
    
    private class ViewShipHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String nme = JOptionPane.showInputDialog("Enter ship name: ");
            String result = gp.getShipDetails(nme);
            JOptionPane.showMessageDialog(myFrame, result);
        }
    }
    
    private class FightEncHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String encNo = JOptionPane.showInputDialog("Enter encounter number:");
            int encounterNumber = Integer.parseInt(encNo);
        
            listing.setVisible(true);
            String result = gp.fightEncounter(encounterNumber);
            listing.setText(result);

        }
    } 
    
    private class isEnounterHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String encNo = JOptionPane.showInputDialog("Enter encounter number:");
            int encounterNumber = Integer.parseInt(encNo);
        
            listing.setVisible(true);
            String result = gp.getEncounter(encounterNumber);
            listing.setText(result);
        
        }
}
    
}
    
   
