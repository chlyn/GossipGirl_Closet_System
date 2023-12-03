import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login 
{
    private Scanner scnr;
    private List<AdminAccount> admins;
    private List<UserAccount> users;

    public Login(Scanner scnr) 
    {
        this.scnr = scnr;
        admins = new ArrayList<AdminAccount>(); // store admin logins
        users = new ArrayList<UserAccount>(); // store user logins
    }

    //full login system
    public int runLoginSystem() 
    {
        //intializing all needed variables
        int usertypeCode = 0; //code that will identify if admin or user

        String username = "";
        String password = "";
        String email = "";
        

        boolean repeat = true; // repeats program until user successfully creates account/logs in/ quits

        //main repeat
        while (repeat) 
        {
            displayIntro(); // asking if admin or user
            displayLines();
            System.out.print("Choice: ");
            String userType = scnr.nextLine(); //taking in answer
            displayLines();

            // MAIN BRANCHES OF PROGRAM
                if (userType.equalsIgnoreCase("ADMIN")) 
                {

                    boolean found = false; // condition to repeat loop until user creates account and logs in
                    usertypeCode = 1; //assinging 1 to admins
                    
                    do{
                    displayAccountType(); // asking if logging in or creating account
                    displayLines();
                    System.out.print("Choice: ");
                    String loginType = scnr.nextLine(); //taking in answer
                    displayLines();

                    if (loginType.equalsIgnoreCase("NEW")) {
                        System.out.println("\n\n______________Create Account______________\n");
                        System.out.println("   Please enter all required information.\n");
                        System.out.print("   Username: ");
                        username = scnr.nextLine();
                        System.out.print("   Password: ");
                        password = scnr.nextLine();
                        System.out.print("   Email: ");
                        email = scnr.nextLine();
                        System.out.println("____________________________________________\n");
                        
                        //adding admin info to arraylist
                        AdminAccount admin = new AdminAccount(username, password, email); 
                        admins.add(admin);
                        found = true; // Exit the loop after creating the admin account
                    } 
                    else if (loginType.equalsIgnoreCase("LOG IN")) {
                        
                        //verifying account method : will stay in method until user log in or makes new account
                        found = verifyAccount(true); //assinged with true to show they are an admin
                        if (found) { // will repeat until the login info is found
                            repeat = false; // exiting the login loop and going into the program
                        }
                    } else { //making admin type in new or log in correctly 
                        System.out.println("\nPlease choose either 'NEW' or 'LOG IN'");
                    }
                } while (!found);
                } 
                else if (userType.equalsIgnoreCase("USERS") || userType.equalsIgnoreCase("USER")) //Loop for users
                {
        
                    usertypeCode = 2; // assinging 2 to users
                    boolean found = false; // condition to repeat loop until user creates account and logs in

                    do{

                    displayAccountType(); // asking if logging in or creating account
                    String loginType = scnr.nextLine(); //taking in answer

                    // Creating account for user
                    if (loginType.equalsIgnoreCase("NEW"))
                    {
                        System.out.println("\n\n   ______Create Account_____\n");
                        System.out.println("Please enter all required information.\n");
                        System.out.print("Username: ");
                        username = scnr.nextLine();
                        System.out.print("Password: ");
                        password = scnr.nextLine();
                        System.out.print("Email: ");
                        email = scnr.nextLine();
                        System.out.println("   _________________________________\n");

                        UserAccount user = new UserAccount(username, password, email); // adding user into into arraylist
                        users.add(user); //adding to user account class
                    }
                    else if (loginType.equalsIgnoreCase("LOG IN"))
                    {
                        //verifying account method : will stay in method until user log in or makes new account
                        found = verifyAccount(false); //assinged with false to show they are a user and not admin
                        if (found) { // will repeat until the login info is found
                            repeat = false; // exiting the login loop and going into the program
                        }
                    } else {
                        System.out.println("\nPlease choose either 'NEW' or 'LOG IN'");
                    }
                } while(!found);
                }
                else if (userType.equals("#")) //For users or admin to exit out of program completely 
                {
                    repeat = false; //exiting loop
                    return 3; // returing 3 to exit loop in main.java (END PROGRAM)
                } 
                else //safefall to make users/admin spell correctly 
                {
                    System.out.println("\n SYSTEM MESSAGE: Please try with Admin or Users\n");
                }
        }

        return usertypeCode; //returning if the person is an admin or user

    }

    //verifying account for login 
    private boolean verifyAccount(boolean isAdmin)
    {
        String isUsername;
        String isPassword;
        boolean isAccount = false;

        do {
            System.out.println("\n\n   __________Login__________\n");
            System.out.println("Please enter your Username and Password.");
            System.out.print("Username: ");
            isUsername = scnr.nextLine(); //username to compare to stored username
            System.out.print("Password: ");
            isPassword = scnr.nextLine(); //password to compare to store password

            if (isAdmin) { // checking for admins
                for (AdminAccount admin : admins) {
                   
                    if (admin.getUsername().equals(isUsername) && admin.getPassword().equals(isPassword)) { 
                        isAccount = true; // showing they successfully logged in and to exit the loop
                        break;
                    }
                }
            } else { //checking for users
                for (UserAccount user : users) {
                    if (user.getUsername().equals(isUsername) && user.getPassword().equals(isPassword)) {
                        isAccount = true; // showing they successfully logged in and to exit the loop
                        break;
                    }
                }
            }

            //safefall if the user cannot log in
            if (!isAccount) {
                System.out.println("\nUsername or Password Incorrect. Please try again.");
                System.out.println("\nPress 1 to retry, or any other key to return to account type selection.");
                String retryInput = scnr.nextLine();
                if (!retryInput.equals("1")) {
                    return false; // Return false because the login failed and exit the loop. They will be brought back to login or new
                }
            }

        } while (!isAccount);

        return true; // Exit the loop after verifying the account

    }


    private void displayIntro() 
    {
        System.out.println("      Choose your account type\n");
        System.out.println("            |ADMIN|\n              or\n            |USERS|");
        System.out.println("\n     If exiting program press #\n");
    }
    
    private void displayAccountType() 
    {
        System.out.println("\n      Choose Log In or New");
        System.out.println("            |LOG IN|\n              or\n             |NEW|\n");

    }

    public void displayLines(){
        System.out.println("---------------------------------------");
    }
   
    
    
}
