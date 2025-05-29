package wars;

import java.io.*;
/**
 * Enum for different types of encounters that ships can face in the game and is
 * used to describe whether the encounter is a blockade, a full battle, or just a skirmish.
 * There is also an INVALID type just in case something goes wrong or isn't recognisable.
 * 
 * @author A.Marczyk
 * @version 12/02/2025
 */
public enum EncounterType implements Serializable
{
    BLOCKADE(" Blockade"), //when ship is blocked from moving or trading
    BATTLE(" Battle"), //big fight between ships
    SKIRMISH (" Skirmish"), //smaller fight/ quick clash
    INVALID (" Invalid"); //used when the encounter isn't valid or recognised
    private String type;
    
    /**
     * sets the string that will be shown for this encounter type
     *
     */
    private EncounterType(String ty)
    {
        type = ty;
    }
    
    /**
     *returns the name of the encounter type as a string
     */
    public String toString()
    {
        return type;
    }
}
