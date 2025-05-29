package wars;

import java.io.Serializable;

/**
 * abstract base class for all types of ships
 * holds common attributes and behaviours that all ships share
 * 
 * @author imankhan
 */
public abstract class Ship implements Serializable
{
    protected String name;
    protected String captain;
    protected int battleSkill;
    protected int commissionFee; //cost to add ship to the squadron
    protected ShipState state; //current state of ship (e.g. ACTIVE, SUNK...)
    
    
    
    public Ship(String nm, String cp, int skill, int fee) {
        name = nm;
        captain = cp;
        battleSkill = skill;
        commissionFee = fee;
        state = ShipState.RESERVE;
    }

    //abstract method that each subclass must implement
    //determines if this ship can fight a specific encounter
    public abstract boolean canFight(EncounterType type);

    public String getName() {
        return name;
    }

    public int getBattleSkill() {
        return battleSkill;
    }

    public int getCommissionFee() {
        return commissionFee;
    }

    public ShipState getState() {
        return state;
    }

    public void setState(ShipState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Ship Name: " + name + ", Captain: " + captain + ", Skill: " + 
                battleSkill + ", Fee: " + commissionFee + ", Ship State: " + state;
    }
}