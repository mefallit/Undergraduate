/*
 * Sezgi Þensöz - 10792020
 */


import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;





public class Hospital {
	
	//Hospital includes patiens and doctors list
	private static ArrayList<String> ptList;
	private static ArrayList<String> drList;
	static Patient p;
	static Doctor d;
	private static String selection = "" ; //I used it for menus
	

	
	
	
	
	public static void hospitalMenu(Patient p,Doctor d) throws Exception{
		
		//It is menu for give chance of choice to user.

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader userInput = new BufferedReader(isr);

        System.out.println("|                         Hospital Menu                  |");
        System.out.println("|________________________________________________________|");
        System.out.println("|Please choose a number from the menu and press <ENTER>: |");
        System.out.println("|                                                        |");
        System.out.println("|1. Add a patient & illness                              |");  //
        System.out.println("|2. Remove a patient                                     |");  //
        System.out.println("|3. Modify a patient                                     |");
        System.out.println("|4. Find a patient by name                               |");  //
        System.out.println("|5. List all patients in currently in hospital           |");  //
        System.out.println("|6. Add a doctor                                         |");  //
        System.out.println("|7. Remove a doctor                                      |");  //
        System.out.println("|8. Modify a doctor                                      |");
        System.out.println("|9. List all doctors in currently in hospital            |");  //
        System.out.println("|10. Exit the system                                     |");  //
        System.out.println("|________________________________________________________|");
        System.out.println("\n");

        System.out.print("Please type your selection, then press <ENTER>: ");
        selection  = userInput.readLine(); //Reading which number pressed
        lookStatements(p); // according to user choices do something

    }
	
	public static void lookStatements(Patient p) throws Exception {
		 // according to user choices do something
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader userInput = new BufferedReader(isr);

        try {

            int selectionInt = Integer.parseInt(selection);
            //change string to integer

            switch(selectionInt) {

                case 1: addPatient();
                        //First choice to add patient
                        break;

                case 2: //Second choice is remove patient
                		if(p==null){
                			//if there is no patient give alert and return menu
                            System.out.println("There are no patients in the system.");
                            System.out.println("********************************\n\n");
                            hospitalMenu(p,d);
                        } 
                		//else remove patient from list and back to menu
                		removePatient();
                        hospitalMenu(p,d);
                        break;

                case 3: //3th choice is modify the patient
                	if(p==null){
                		//if there is no patient give alert and return menu
                			System.out.println("There are no patients in the system.");
                			System.out.println("********************************\n\n");
                            hospitalMenu(p,d);
                        } // else take name from user

                        System.out.print("Type patient name you want to change: ");
                        String change = userInput.readLine();
                        
                        //find patient and open the modify menu
                        findPatient(change);

                        modMenu(p,d); //Modify menu
                        break;

                case 4: //find patient 
                		if(p==null){
                			//if there is no patient gives alert and return menu
                			System.out.println("There are no patients in the system.");
                			System.out.println("********************************\n\n");
                            hospitalMenu(p,d);
                        } // end if

                		//take name from user and find
                        System.out.print("Type patient name you want to find: ");
                        String find = userInput.readLine();

                        
                        findPatient(find);
                        hospitalMenu(p,d);
                        break;

                case 5: //5th choice is print patient
                		if(p==null){
                			//if there is no patient gives alert and return menu
                			System.out.println("There are no patients in the system.");
                			System.out.println("********************************\n\n");
                            hospitalMenu(p,d);
                        } //Print Patient
                        System.out.println("\n Printing patient");
                        printPatientList();
                        hospitalMenu(p,d);
                        break;

                case 6: //6th choice is add Doctor
                		addDoctor();
                        break;

                case 7: //7th choice is remove doctor
                		if(d ==null){
                			//if there is no doctor gives alert and return menu
                			System.out.println("There are no doctor in the system.");
                			System.out.println("********************************\n\n");
                			hospitalMenu(p,d);
                		} // end if
                		//remove the doctor and return menu
        			removeDoctor();
        			hospitalMenu(p,d);
        			break;

                case 8: //8th choice is modify the doctor
                	if(d==null){
                		//if there is no doctor gives alert and return menu
        					System.out.println("There are no doctor in the system.");
        					System.out.println("********************************\n\n");
        					hospitalMenu(p,d);
                		} // take dr name from user and open the modify menu

                		System.out.print("Type doctor name you want to change: ");
                		String changedr = userInput.readLine();
                
                		findDoctor(changedr);

                		modMenu(p,d); //modify menu
                		break;
                		
                case 9: //9th choice is print dr's
                	  if(d==null){
                		//if there is no doctor gives alert and return menu
        					System.out.println("There are no doctor in the system.");
        					System.out.println("********************************\n\n");
        					hospitalMenu(p,d);
                		} // end if
                		System.out.println("\n Printing doctor");
                		printDoctorList(); //print dr
                		hospitalMenu(p,d);
                		break;
                		
                case 10: //10th choice is exit the system, take answer from user and exit system or return menu
                		System.out.print("\nYou chose to exit.  Do you really want to exit?(y/n): ");
                		String exit = userInput.readLine();

                		if(exit.equals("y")){ //if user want to quit, exit system
                			System.out.println("Thank you!");
                			System.out.println("*********************\n\n");
                			System.exit(0);
                		}
                		if(exit.equals("n")){ //if user dont want to quit, return menu
                			hospitalMenu(p,d);
                		}
                		else { //if dont type other things give alert and return menu
                			System.out.println("\t Please type y or n.");
                			hospitalMenu(p,d);
                		}

                default: //if user write another things gives alert
                         System.out.println("You did not choose a number from the menu.");
                         System.out.println("\n\n************************");
                         hospitalMenu(p,d);
                         break;

            } // END switch

        } // END try statement

        catch(NumberFormatException nfe) {
        	//if user doesnt write number gives exception
            System.out.println("Invalid input.  Please try again");
            System.out.println("****************************\n\n");
            hospitalMenu(p, d);

        } // END catch statement

    }
	
	public static void modMenu(Patient p,Doctor d) throws Exception{
		//Modify menu
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("|                 Modification Menu                         |");
        System.out.println("|___________________________________________________________|");
        System.out.println("|Please choose a number from the menu and press <ENTER>:    |");
        System.out.println("|1. Patient's name                                          |");
        System.out.println("|2. Illness                                                 |");
        System.out.println("|3. Doctor's name                                           |");
        System.out.println("|3. Return the main menu                                    |");
        System.out.println("|___________________________________________________________|");
        System.out.println("\n");
        
        modMenuChange(p,d); //modify choices...

    }
	
	public  static void modMenuChange(Patient p,Doctor d) throws Exception {
		//for user choice
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader userInput = new BufferedReader(isr);

        try {

            System.out.print("Please type your selection, then press <ENTER>: ");
            //take choice from user
            String modSelect = userInput.readLine();
            
            int mod = Integer.parseInt(modSelect);
            //change to integer
            switch(mod) {

                case 1: //change patient name
                		System.out.println("Original name: " + p.getPatientName());
                        System.out.print("Change name to: ");
                        String modName = userInput.readLine();
                        
                        p.setPatientName(modName); //change here

                        System.out.println("Name has been modified.");
                        System.out.println("***********************\n\n");
                        hospitalMenu(p, d); //return main menu

                        break;
                        
                case 2: //Change illness 
                		System.out.println("Original illness: " + p.getIllnessType());
                        System.out.print("Change illness to: ");

                        // take information from user 
                        String modIllness = userInput.readLine();
                        
                        p.setIllnessType(modIllness); //change here
                        
                        System.out.println("Illness has been modified.");
                        System.out.println("**********************\n\n");
                        hospitalMenu(p, d); //return menu

                        break;

                case 3: //change dr name
                		System.out.println("Original name: " + d.getDrName());
                		System.out.print("Change name to: ");
                		String modDrName = userInput.readLine();
                		 // take information from user 
                		d.setDrName(modDrName); //change here

                		System.out.println("Name has been modified.");
                		System.out.println("***********************\n\n");
                		hospitalMenu(p, d);

                break;

 

                case 4: //return the main menu
                        System.out.println("Returning to main menu.");
                        System.out.println("************************\n\n");
                        hospitalMenu(p, d);

                        break;

                default: //if user doesnt choice the number give alert
                         System.out.println("You did not choose a number from the menu.");
                         System.out.println("***************************\n\n");
                         hospitalMenu(p, d);
                         break;

            } // end switch
        } // end try

        catch(NumberFormatException nfeMod) {
        	//if user doesnt write number, gives exception
            System.out.println("Invalid integer.  Please try again.");
            System.out.println("*****************************\n\n");

            modMenu(p, d);

        } // end catch

    } 

	public static void addPatient() throws Exception{
		//add patient 
		ptList = new ArrayList<String>();
		

        System.out.println("\n- Adding Patient -\n");
        //take name from user
        System.out.print("Patient Name: ");
        Scanner tara=new Scanner(System.in); 
		String a = tara.next();
        //take illness from user
        System.out.print("Patient illness: ");
        Scanner tara1=new Scanner(System.in); 
		String b = tara1.next();



        p = new Patient(a, b); 
        p.setPatientName(a);
        p.setIllnessType(b);
        
        ptList.add(p.patientName); //add patient to list (we take name and illness from user=)
        
        System.out.println("\n\n*********************");
        System.out.println(a + " was added to the system.");
        hospitalMenu(p,d);
}

	
	public static void printPatientList(){
		//print patient
		for (int i = 0; i<ptList.size(); i++){
			System.out.println("patient name:" + " " + ptList.get(i));		
			}
	} 


	public static void removePatient() throws Exception{
		//remove Patient
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader userInput = new BufferedReader(isr);

        System.out.println("\n- Removing Patient -\n");
        //take name from user
        System.out.print("Type the Patient Name you want to remove: ");
        String name = userInput.readLine();
        
        int a = 0; //for index number
        for(int i= 0; i<ptList.size(); i++){
        	if(name == ptList.get(i)){
        		a = i; //find index number of patient
        	}
        	else {
        		System.out.println(name + "doesn't exist in system");
        		System.out.println("Please try again.");
        		hospitalMenu(p,d);
        	}
        }
        
        ptList.remove(a); //remove patient(we found index of it)
        System.out.println("\n\n**********");
        System.out.println(name + " removed from the system.");
        hospitalMenu(p,d);
	}
	
	public static void findPatient(String find) {
		 //  find patient from list
		int a = 0; //for index number
        for(int i= 0; i<ptList.size(); i++){
        	if(find == ptList.get(i)){
        		a=i;  //  find index number
        		System.out.println("Found " + find);
        		System.out.println("Information:");
        		System.out.println("\n" + ptList.get(a).toString() + "\n\n");
        	}
        }
        
    }
	

	
	public static void addDoctor() throws Exception{
		//add doctor
		drList=new ArrayList<String>();

        System.out.println("\n Adding Doctor \n");
        //take name from user
        System.out.print("Doctor Name: ");
		Scanner tara=new Scanner(System.in); 
		String a = tara.next(); //change to string our Scanner class
        drList.add(a); //add to dr list
        
        System.out.println("\n\n*********************");
        System.out.println(a + " " + "added to the system.");
        hospitalMenu(p,d);
	}
	
	
	public static void removeDoctor() throws Exception{
		
		//remove doctor 
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader userInput = new BufferedReader(isr);

        System.out.println("\n- Removing Doctor -\n");
        //take name from user for remove
        System.out.print("Type the Doctor Name you want to remove: ");
        String name = userInput.readLine();
        
        int a = 0; //index of list
        for(int i= 0; i<drList.size(); i++){
        	if(name == drList.get(i)){
        		a = i; //find index of list
        	}
        	else {
        		//if there is no doctor gives alert and return menu
        		System.out.println(name + "doesn't exist in system");
        		System.out.println("Please try again.");
        		hospitalMenu(p,d);
        	}
        }
        
        drList.remove(a); //remove doctor (we found the index number of this doctor)
        System.out.println("\n\n**********");
        System.out.println(name + " removed from the system.");
        hospitalMenu(p,d);
	}

	public static void findDoctor(String find) {
        //  find doctor from list
		int a = 0; //for list index
        for(int i= 0; i<drList.size(); i++){
        	if(find == drList.get(i)){
        		a=i; //find index number of doctor 
        		System.out.println("\n" + drList.get(a).toString() + "\n\n");
        	}
        }
        
    }
	
	public static void printDoctorList(){
		//print dr from list
		for (int i = 0; i<drList.size(); i++){
			System.out.println("doctor name:" + " " + drList.get(i));		
			}
	}

	 public static void main(String[] arg) throws Exception {

	        Hospital clinic = new Hospital();
	        hospitalMenu(p, d);
	        

	    }

}