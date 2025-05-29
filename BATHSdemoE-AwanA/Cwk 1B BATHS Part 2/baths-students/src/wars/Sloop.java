package wars;

import java.io.Serializable;

/**
 * Sloop is a type of small Ship that always has a fixed battle skill of 5 and 
 * can only take part in SKIRMISH type encounters.
 * 
 * @author imankhan
 */
public class Sloop extends Ship implements Serializable{
    private boolean hasDoctor; //true or false based on whether sloop has doctor onboard or not

    public Sloop(String name, String captain, int commissionFee, boolean hasDoctor) {
        super(name, captain, 5, commissionFee); //all sloops have skill - 5
        this.hasDoctor = hasDoctor;
    }

    @Override
    public boolean canFight(EncounterType type) {
        return type == EncounterType.SKIRMISH; //sloops can only fight SKIRMISH
    }


    @Override
    public String toString() {
        return super.toString() + ", Type: Sloop, Doctor: " + hasDoctor;
    }
}
