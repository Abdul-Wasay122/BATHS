/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 * 
 * 
 * @author Abdul Awan
 * student id : 22079675
 * team CS04
 */
public class DemoE {
    public static void main(String[] args)
    {
        BATHS prince = new SeaBattles("Rupert");
        
        
        
        System.out.println(prince.getSquadron());
        System.out.println("*****************");
        
        System.out.println(prince.commissionShip("Victory"));
        System.out.println(prince.getWarChest());
        
        System.out.println(prince.commissionShip("Beast"));
        System.out.println(prince.getWarChest());
        
        System.out.println(prince.commissionShip("Jupiter"));
        System.out.println(prince.getWarChest());
        
        
        System.out.println(prince.commissionShip("Angel"));
        System.out.println(prince.getWarChest());
        
        System.out.println(prince.commissionShip("Paris"));
        System.out.println(prince.getWarChest());
        
        System.out.println(prince.getReserveFleet());
        System.out.println("*********************");
        
        
        System.out.println(" If Admiral fought the encounter one "
                + "===calculation of the expected result==="
                + "that the encounter will be won by ship victory in the start because of the stats"
                + "=== expected results ======"
                + "Encounter won by Victor");
        
        
       System.out.println(prince.fightEncounter(1));
       System.out.println(prince.getWarChest());
       
       System.out.println(prince.fightEncounter(6));
       System.out.println(prince.getWarChest());

       System.out.println(prince.fightEncounter(3));
       System.out.println(prince.getWarChest());
       
       System.out.println(prince.fightEncounter(8));
       System.out.println(prince.getWarChest());
       
       System.out.println(prince.fightEncounter(1));
       System.out.println(prince.getWarChest());
 
        
      prince.restoreShip("Victory");
       System.out.println(prince.fightEncounter(1));
       
       prince.commissionShip("Gold");
      
        
    }
    
}
