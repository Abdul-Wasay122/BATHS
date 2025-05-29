/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

import java.io.Serializable;

/**
 *
 * @author aa23adm
 */
public class Corvette extends Ship implements Serializable {
    private String homePort;
    private boolean hasPurser;
    
    
    public Corvette(String name, String captain, int battleSkill, int commissionFee, String homePort, boolean hasPurser)
    {
        super(name, captain, battleSkill,commissionFee);
        this.hasPurser = hasPurser;
               
    }
    
    public boolean canFight(EncounterType type) {
        return type ==  EncounterType.BATTLE;
    }
}
