package wars; 


/**
 * Details of your team
 * 
 * @author (Team CSO4) 
 * @version (31/03/25)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "CS04";
        
        details[1] = "Aldreby";
        details[2] = "Fatimah";
        details[3] = "22046611";

        details[4] = "Awan";
        details[5] = "Abdul";
        details[6] = "22079675";

        details[7] = "Khan";
        details[8] = "Iman";
        details[9] = "23002248";


        details[10] = "Noor";
        details[11] = "Taybah";
        details[12] = "22044316";

	
	   // only if applicable
        details[13] = "surname of member5";
        details[14] = "first name of member5";
        details[15] = "SRN of member5";


    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
