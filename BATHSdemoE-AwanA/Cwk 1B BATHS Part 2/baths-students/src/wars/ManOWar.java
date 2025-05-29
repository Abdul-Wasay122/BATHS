package wars;

import java.io.Serializable;

/**
 * ManOWar - heavy duty ship subclass with lots of decks and marines.
 * Typically strong in BATLLE and BLOCKADE encounters, but not good for SKIRMISHES.
 * 
 */
public class ManOWar extends Ship implements Serializable
{
    private int ManOWarDecks; //no. of decks on the ship
    private int ManOWarMarines; //no. of marines on board

    /**
     * constructor for a ManOWar ship
     * fee is calculated based on number of decks (2 = cheaper, 3+ = more).
     *
     * @param name - ships name
     * @param captain - name of captain
     * @param battleSkill - the ships skill level in battles
     * @param decks - no. of decks on the ship (2/ 3)
     * @param marines - no. of marines on board
     */
    public ManOWar(String name, String captain, int battleSkill, int decks, int marines) {
        super(name, captain, battleSkill, calculateFee(decks)); //commission fee depends on deck count
        ManOWarDecks = decks;
        ManOWarMarines = marines;
    }

    /**
     * calculates commission fee based on the number of decks
     * 2 decks= £300, else £500
     */
    private static int calculateFee(int decks) {
        if (decks == 2) {
            return 300;
        } else {
            return 500;
        }
    }

    /**
     * tells if this ship can take part in a given encounter
     * ManOWar ships can participate in battles and blockades, but not skirmishes (small fights)
     */
    @Override
    public boolean canFight(EncounterType type) {
        return type == EncounterType.BLOCKADE|| type == EncounterType.BATTLE;
    }


    /**
     * displays all ship details; its type, decks and marine
     */
    @Override
    public String toString() {
        return super.toString() + ", Type: ManOWar" + ", Decks: " + ManOWarDecks + ", Marines:" + ManOWarMarines;
    }

}
