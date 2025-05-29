package wars;

import java.io.Serializable;
import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from the BATHS
 * system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS, Serializable 
{
    // may have one HashMap and select on stat

    private String admiral;
    private double warChest;
    private Map<String, Ship> reserveFleet = new HashMap<>();// ships not yet in use
    private Map<String, Ship> squadron = new HashMap<>();// active/resting ships
    private Map<Integer, Encounter> encounters = new HashMap<>();// all game encounters



//**************** BATHS ************************** 
    /** Constructor requires the name of the admiral
     * @param adm the name of the admiral
     */  
    public SeaBattles(String adm)
    {
        this.admiral = adm; //set admiral's name
        this.warChest = 1000; //start with 1000 money
        
       setupShips();
       setupEncounters();
    }
    
    /** Constructor requires the name of the admiral and the
     * name of the file storing encounters
     * @param admir the name of the admiral
     * @param filename name of file storing encounters
     */  
    public SeaBattles(String admir, String filename)  //Task 3
    {
        this.admiral = admir;
        this.warChest = 1000;

        
       setupShips();
       //setupEncounters();
       // uncomment for testing Task 
       readEncounters(filename);
    }
    
    
    /**Returns a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     * @return a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    public String toString()
    {
        String result = "";//start with empty string

        result += "Admiral: " + admiral + "\n";//show admiral
        result += "War Chest: £" + warChest + "\n";//show money

        if (isDefeated())
        {
            result += "Status: DEFEATED\n";//if game is lost
        }
        else
        {
            result += "Status: Is OK\n";// if still in game
        }

        result += "\n--Squadron--\n";//show squadron ships if there  are any
        if (squadron.isEmpty()) {
            result += "No ships\n";
        } 
        else
        {
            for (Ship ship : squadron.values())//list ships in Squadron
            {
                result += ship.toString() + "\n";
            }
        }

        result += "\n--Reserve Fleet--\n";//ships aren't commissioned yet
        if (reserveFleet.isEmpty())
        {
            result += "No ships\n";
        }
        else
        {
            for (Ship ship : reserveFleet.values())//list ships in Reserve Fleet
            {
                result += ship.toString() + "\n";
            }
        }

        return result;
    }
    
    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @returns true if War Chest <=0 and the admiral's fleet has no ships 
     * which can be retired. 
     */
    public boolean isDefeated()//check if game is lost
    {
        if (warChest > 0)//still have money
        {
            return false;
        }

        for (Ship ship : squadron.values())//check usable ships
        {
            if (ship.getState()== ShipState.ACTIVE || ship.getState()== ShipState.RESTING) {
                return false;//still have usable ships
            }
        }

        return true;//no ships/ no money
    }
    
    /** returns the amount of money in the War Chest
     * @returns the amount of money in the War Chest
     */
    public double getWarChest()
    {
        return warChest;
    }
    
    // task 5
    
   
    
    
    
    /**Returns a String representation of all ships in the reserve fleet
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet()
    {   //assumes reserves is a Hashmap
       
        if (reserveFleet.isEmpty()) {
            return "No ships";
        }
        
        String result = "";
        for (Ship ship : reserveFleet.values())//loop through ships
        {
            result += ship.toString() + "\n";//add each ship info
        }
        return result.trim();//remove extra newlines
    }
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
        if (squadron.isEmpty()) {
        return "No ships commissioned";// if empty
        }
        
        String result = "";
        for (Ship ship : squadron.values()) { //loop through squadron
            result += ship.toString() + "\n";
        }
        return result;//return all info
    }
    
    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips() {
        String result = "";

         // check reserve for sunk ships
        for (Ship s : reserveFleet.values())//check reserve
        {
            if (s.getState() == ShipState.SUNK)
            {
                result += s.getName() + "\n";
            }
        }

        // check squadron for sunk ships
        for (Ship s : squadron.values())//check squadron
        {
            if (s.getState() == ShipState.SUNK)
            {
                result += s.getName() + "\n";
            }
        }

        if (result.equals(""))
        {
            return "No ships sunk yet";
        }

        return result;
    }

    
    /**Returns a String representation of the all ships in the game
     * including their status
     * @return a String representation of the ships in the game
     **/
    public String getAllShips()
    {
        String result = "";

        // add ships in squadron
        for (String name : squadron.keySet())
        {
            Ship ship = squadron.get(name);
            if (ship != null) {
                result += ship.toString() + "\n";
            }
        }

        // add ships in reserve fleet
        for (String name : reserveFleet.keySet())
        {
            Ship ship = reserveFleet.get(name);
            if (ship != null)
            {
                result += ship.toString() + "\n";
            }
        }
        
        if (result.equals(""))
        {
            return "No ships";
        }

        return result;
    }
    
    
    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String nme)
    {
        Ship ship = squadron.get(nme); //look in squadron first
        if (ship != null)
        {
            return ship.toString();
        }
        
        ship = reserveFleet.get(nme); //then reserve
        if (ship != null)
        {
            return ship.toString();
        }
        return "No such ship";
    }     
 
    // ***************** Fleet Ships ************************   
    /** Allows a ship to be commissioned to the admiral's squadron, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if 
     * ship not found, "Not available" if ship is not in the reserve fleet, "Not 
     * enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        
        Ship ship = reserveFleet.get(nme);

        if (ship == null)
        {
            //check if ship exists in squadron or elsewhere
            ship = squadron.get(nme);
            if (ship != null) {
                return "Not available"; //already in use
            }
            return "Not found"; //not in any fleet
        }

        // Ship is in reserveFleet
        if (ship.getState() != ShipState.RESERVE)
        {
            return "Not available"; //shouldn not happen but safe to keep
        }

        double fee = ship.getCommissionFee();

        if (warChest < fee){
            return "Not enough money";
        }

        //all good - commission it
        warChest -= fee;
        ship.setState(ShipState.ACTIVE);
        squadron.put(ship.getName(), ship);
        reserveFleet.remove(ship.getName());

        return "Ship commissioned";
    }

    /** Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     * @param nme is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String nme)
    {
        Ship ship = squadron.get(nme);
        return ship != null && ship.getState() == ShipState.ACTIVE;
    }
    
    /** Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     * @param nme is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String nme)
    {
        if (!isInSquadron(nme))
        {
            return false;
        }

        Ship ship = squadron.get(nme);

        //only allow decommissioning if ship is ACTIVE or RESTING
        if (ship.getState() == ShipState.ACTIVE || ship.getState() == ShipState.RESTING)
        {
            ship.setState(ShipState.RESERVE);
            squadron.remove(nme);
            reserveFleet.put(nme, ship);

            //refund half of the original commission fee
            warChest += ship.getCommissionFee() / 2;

            return true;
        }

        return false;
    }

    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param ref the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {
        Ship ship = squadron.get(ref);
    
        //only restore if it's RESTING — not SUNK, not ACTIVE, not null
        if (ship != null && ship.getState() == ShipState.RESTING) {
            ship.setState(ShipState.ACTIVE);
        }
    }
    
//**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the reference number of the encounter
     * @returns true if the reference number represents a encounter, else false
     **/
     public boolean isEncounter(int num)
     {
         return encounters.get(num)!= null;
     }
     
     
/** Retrieves the encounter represented by the encounter 
      * number.Finds a ship from the fleet which can fight the 
      * encounter.The results of fighting an encounter will be 
      * one of the following: 
      * 0-Encounter won by...(ship reference and name)-add prize money to War 
      * Chest and set ship's state to RESTING,  
      * 1-Encounter lost as no ship available - deduct prize from the War Chest,
      * 2-Encounter lost on battle skill and (ship name) sunk" - deduct prize 
      * from War Chest and set ship state to SUNK.
      * If an encounter is lost and admiral is completely defeated because there 
      * are no ships to decommission,add "You have been defeated " to message, 
      * -1 No such encounter
      * Ensure that the state of the war chest is also included in the return message.
      * @param encNo is the number of the encounter
      * @return a String showing the result of fighting the encounter
      */ 
    public String fightEncounter(int encNo)
    {
        Encounter enc = encounters.get(encNo);// get the encounter by number
        if (enc == null)
        {
            // if no such encounter, return error message and current war chest
            return "-1 No such encounter\nWar Chest: £" + warChest;
        }
        //find first ship in squadron that can fight
        Ship chosenShip = null;
        for (Ship ship : squadron.values())
        {
            // check if ship is active and can handle this encounter type
            if (ship.getState() == ShipState.ACTIVE && ship.canFight(enc.getEncounterType()))
            {
                chosenShip = ship;// pick this ship
                break; // stop looking once found
            }
        }
        if (chosenShip == null)
        {
            // if no ship is available, lose encounter and lose prize money
            warChest -= enc.getPrizeMoney();
            String msg = "1 Encounter lost as no ship available";
            if (isDefeated())
            {
                msg += "\nYou have been defeated.";//  defeat notice if game is over
            }
            return msg + "\nWar Chest: £" + warChest;
        }
        // Win scenario
        if (chosenShip.getBattleSkill() >= enc.getRequiredSkill())
        {
            warChest += enc.getPrizeMoney();// add money if won
            chosenShip.setState(ShipState.RESTING);// set ship to resting
            return "0 Encounter won by " + chosenShip.getName() +
                   "\nWar Chest: £" + warChest;
        }
        // Lose on skill
        warChest -= enc.getPrizeMoney();// subtract prize from war chest
        chosenShip.setState(ShipState.SUNK);// mark ship as sunk
        String msg = "2 Encounter lost on battle skill - " + chosenShip.getName() + " sunk";
        if (isDefeated())
        {
            msg += "\nYou have been defeated.";
        }
        return msg + "\nWar Chest: £" + warChest;// return result
    }


    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        
        Encounter encounter = encounters.get(num);// look up encounter

        if (encounter != null) {
            return encounter.toString();// return details if found
        }

        return "\nNo such encounter";
    }
    
    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
        if (encounters.isEmpty())
        {
            return "No encounters";
        }

        String result = "";
        
        for (int key : encounters.keySet())
        {
            Encounter enc = encounters.get(key);// get each encounter
            
            if (enc != null)
            {
                result += enc.toString() + "\n";// add to result
            }
        }

    return result;// return full list
    }
    

    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
     private void setupShips()
     {
         reserveFleet.put("Victory", new ManOWar("Victory", "Alan Aikin", 3, 3, 30));
         reserveFleet.put("Sophie", new Frigate("Sophie", "Ben Baggins", 8, 16, true));
         reserveFleet.put("Endeavour", new ManOWar("Endeavour", "Col Cannon", 4, 2, 20));
         reserveFleet.put("Arrow", new Sloop("Arrow", "Dan Dare", 150, true));
         reserveFleet.put("Belerophon", new ManOWar("Belerophon", "Ed Evans", 8, 3, 50));
         reserveFleet.put("Surprise", new Frigate("Surprise", "Fred Fox", 6, 10, false));
         reserveFleet.put("Jupiter", new Frigate("Jupiter", "Gil Gamage", 7, 20, false));
         reserveFleet.put("Paris", new Sloop("Paris", "Hal Henry", 200, true));
         reserveFleet.put("Beast", new Sloop("Beast", "Ian Idle", 400, false));
         reserveFleet.put("Athena", new Sloop("Athena", "John Jones", 100, true));
         
         
         reserveFleet.put("Daisy", new ManOWar("Daisy", "Dick Deadeye", 5, 3, 30));
         reserveFleet.put("Gold", new Corvette("Gold", "Tom Thumb", 5, 200 ,"Plymouth",false));
         
         
     }
     
    private void setupEncounters() 
    {
        encounters.put(1, new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300));
        encounters.put(2, new Encounter(2, EncounterType.SKIRMISH, "Belle Isle", 3, 120));
        encounters.put(3, new Encounter(3, EncounterType.BLOCKADE, "Brest", 3, 150));
        encounters.put(4, new Encounter(4, EncounterType.BATTLE, "St Malo", 9, 200));
        encounters.put(5, new Encounter(5, EncounterType.BLOCKADE, "Dieppe", 7, 90));
        encounters.put(6, new Encounter(6, EncounterType.SKIRMISH, "Jersey", 8, 45));
        encounters.put(7, new Encounter(7, EncounterType.BLOCKADE, "Nantes", 6, 130));
        encounters.put(8, new Encounter(8, EncounterType.BATTLE, "Finisterre", 4, 100));
        encounters.put(9, new Encounter(9, EncounterType.SKIRMISH, "Biscay", 5, 200));
        encounters.put(10, new Encounter(10, EncounterType.BATTLE, "Cadiz", 1, 250));
        
        encounters.put(11, new Encounter(11, EncounterType.BLOCKADE, "Dieppe", 5, 150));
    }

        
    // Useful private methods to "get" objects from collections/maps

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3 ************************************************/

    
    //******************************** Task 3.5 **********************************
    /** reads data about encounters from a text file and stores in collection of 
     * encounters.Data in the file is editable
     * @param filename name of the file to be read
     */
    public void readEncounters(String filename) {
        try {
            //open the file for reading
            FileReader fileReader = new FileReader(filename);// open file for reading
            BufferedReader reader = new BufferedReader(fileReader);// buffer it for efficient reading

            String line = reader.readLine();// read the first line

            while (line != null) // keep going while there are lines left
            {
                //split the line by commas
                String[] data = line.split(",");

                //Check if we have exactly 5 values
                if (data.length == 5) {
                    //Read each value and clean it
                    int id = Integer.parseInt(data[0].trim());// get encounter number
                    String typeText = data[1].trim();// get type as text
                    String location = data[2].trim();// get location
                    int requiredSkill = Integer.parseInt(data[3].trim());// skill needed
                    int prizeMoney = Integer.parseInt(data[4].trim());// money for winning

                    // convert text to enum
                    EncounterType type = EncounterType.valueOf(typeText);

                    //create the Encounter and add it to the map
                    Encounter encounter = new Encounter(id, type, location, requiredSkill, prizeMoney);
                    encounters.put(id, encounter);
                }

                //move to the next line
                line = reader.readLine();
            }

            //close the file
            reader.close();

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());// file issue
        } catch (NumberFormatException e) {
            System.out.println("Problem with number in file: " + e.getMessage());// number issue
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid encounter type found in file.");// enum type wrong
        }
    }
 
 
    
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {   // uses object serialisation 
           try{
            FileOutputStream fileOut = new FileOutputStream(fname);// open output stream to file
            ObjectOutputStream out = new ObjectOutputStream(fileOut);// wrap in object output
            out.writeObject(this);// write current object (SeaBattles)
            out.close();// close object stream
            fileOut.close();// close file stream
        }
           catch (IOException e){
               System.out.println("Error saving game: " + e.getMessage());// print error
        }
    }
    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    public SeaBattles loadGame(String fname)
    {   // uses object serialisation 
       
        SeaBattles loaded = null; // for loaded object
        try{
            FileInputStream fileIn = new FileInputStream(fname);// open file input
            ObjectInputStream in = new ObjectInputStream(fileIn);// wrap in object input
            loaded = (SeaBattles) in.readObject();//  loaded object to SeaBattles
            in.close();
            fileIn.close();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());// print error
        }
        return loaded;// return loaded game
    } 
    
 
}
