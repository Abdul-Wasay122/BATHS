/**
 * this is the Frigate class, which is a specific type of Ship
 * a Frigate has cannons and might have a pinnace (a smaller boat)
 */
package wars;
import java.io.Serializable;

/**
 *
 * frigate class extends the Ship class and adds extra features like cannons and a pinnace
 * frigates can take part in different types of encounters, which depends on whether they have a pinnace
 * 
 * @author imankhan
 */
public class Frigate extends Ship implements Serializable
{
    private int cannons;// number of cannons on the ship
    private boolean hasPinnace;// whether the frigate has a pinnace or not

    /**
     * constructor for Frigate
     * the commission fee is based on the number of cannons (10 pounds per cannon)
     *
     */
    public Frigate(String name, String captain, int battleSkill, int cannons, boolean hasPinnace)
    {
        super(name, captain, battleSkill, cannons * 10); // 10 pounds per cannon commission fee
        this.cannons= cannons;
        this.hasPinnace= hasPinnace;
    }

    /**
     * checks if the frigate can fight a certain type of encounter
     * frigates can only take part in SKIRMISH (small fight/clash) or BLOCKADE (blocked ship) if they have a pinnace
     * all Frigates can fight in battles regardless
     * 
     * @param type: type of encounter
     * @return true if this frigate can participate in that encounter
     */
    public boolean canFight(EncounterType type)
    {
        if (type == EncounterType.SKIRMISH || type == EncounterType.BLOCKADE)
        {
            return hasPinnace;
        }
        return true; // only allow non-pinnace ships to fight Battles
    }


     /**
     * returns string showing the ships details
     */
    @Override
    public String toString(){
        return super.toString() + ", Type: Frigate, Cannons: " + cannons + ", Pinnace: " + hasPinnace;
    }

}