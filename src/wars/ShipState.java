package wars;

import java.io.*;

/**
 * Enumeration class UnitState:
 * To represent the different possible states a ship can be in
 * Different states of Ships are RESERVE, ACTIVE, RESTING, and SUNK
 * These track status of each ship during the game
 * 
 * @author A.Marczyk
 * @version 12/02/2025
 */
public enum ShipState implements Serializable
{
    RESERVE("In reserve fleet"), 
    ACTIVE("Active in squadron"), 
    RESTING("Resting"), 
    SUNK ("Sunk");
    private String state;
    
    private ShipState(String st)
    {
        state = st;
    }
    
    public String toString()
    {
        return state;
    }
}
