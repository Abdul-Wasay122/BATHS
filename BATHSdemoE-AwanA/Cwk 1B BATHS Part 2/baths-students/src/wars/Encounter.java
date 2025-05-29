package wars;
import java.io.Serializable;

/**
 * Stores the details for an encounter in the game and includes info like 
 * what type of encounter it is, what skill level is needed, where it happens (location),
 * and how much money you get if you win.
 * 
 * @author imankhan
 */
public class Encounter implements Serializable{
    private int encounterNo; //unique number for this encounter
    private EncounterType encounterType;//what kind of encounter it is
    private String location; //where encounter takes place
    private int requiredSkill;//how good the ship has to be in order to win
    private int prizeMoney; //the amount of money you get if you win

    /**
     * Constructor to make a new encounter with all the details.
     * 
    */
    public Encounter(int encNo, EncounterType type, String loc, int skill, int prize) {
        encounterNo = encNo;
        encounterType = type;
        location = loc;
        requiredSkill = skill;
        prizeMoney = prize;
    }

    //returns ID for this encounter
    public int getEncounterID() {
        return encounterNo;
    }

    //returns the type of encounter this is
    public EncounterType getEncounterType() {
        return encounterType;
    }

    //returns skill level needed to win
    public int getRequiredSkill() {
        return requiredSkill;
    }

    //returns the amount of money the player gets if they win
    public int getPrizeMoney() {
        return prizeMoney;
    }

    //used to print out info about the encounter in a clear way
    @Override
    public String toString() {
        return encounterNo + ": " + encounterType + " at " + location + " (Skill: " + requiredSkill + ", Prize: £" + prizeMoney + ")";
    }

}