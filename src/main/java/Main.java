
import export.Export;
import analytics.Analytics;
import logGenerator.Logger;
import queries.Queries;
import state.State;
import user.User;
import utilities.ConsoleReader;
import logical.DataModel;

public class Main {

    public static ConsoleReader reader = new ConsoleReader();
    public static State state = new State();
    public static Logger logger = new Logger();

    
    public static void main(String[] args) {

        try{
            System.out.println("Welcome to the Database Management System!");

            while (true) {
                System.out.println("\n\nPlease select an option:");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");


                int option = reader.readInt();

                switch (option) {
                    case 1:
                        state.setUserLoggedIn(User.login(state));
                        break;
                    case 2:
                        User.register();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }

                if (option == 3 || state.getUserLoggedIn()) {
                    break;
                }
            }

            if(state.getUserLoggedIn()) {
                logger.eventLog("LOGIN", "User logged in.", state);
                while (true) {
                    System.out.println("\n\nPlease select an option:");
                    System.out.println("1. Write Queries");
                    System.out.println("2. Logout and Exit");

                    System.out.print("\nOption>");
                    int option = reader.readInt();

                    switch (option) {
                        case 1:
                            Queries.menu(state);
                            break;
                        case 2:
                            state.setUserLoggedIn(false);
                            break;
                        default:
                            System.out.println("Invalid option!");
                            break;
                    }

                    if(option == 2 || !state.getUserLoggedIn()) {
                        break;
                    }
                }
            }

            System.out.println("\n\nClosing Database...");
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage()) ;
        }
    }
}