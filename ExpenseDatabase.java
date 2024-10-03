/*
 * @author Ezekiel Chow
 * Expense Database
 * CIS 22C, Final Project
 */

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class ExpenseDatabase {

    private final int NUM_EXPENSES = 100;

    Hash<Expense> ht = new Hash<>(NUM_EXPENSES);
    BST<Expense> bst1 = new BST<>();
    BST<Expense> bst2 = new BST<>();

    public static void main(String[] args) throws IOException {


        // Making a File, scanning all of the user data and storing them in a list.

        File file = new File("Users.txt");
        Scanner userScanner = new Scanner(file);
        List<User> userList = new List();

        String textFile = "";

        populateUserList(userList, userScanner, file);


        System.out.println("Welcome to the Expense Tracker! \n");

        Scanner userInput = new Scanner(System.in);



        String in = "";

        while(!(in.equalsIgnoreCase("X"))){
            System.out.println("Please select from the following options");
            System.out.println();System.out.println("\nA. Log in\nB. Create an Account\nX. Exit the program");
            System.out.println();
            System.out.print("Enter your choice: ");
            in = userInput.nextLine();


            if(in.equalsIgnoreCase("A")){

                System.out.println("Logging in...");
                System.out.print("Enter your username: ");
                String username = userInput.nextLine();
                System.out.print("Enter your password: ");
                String password = userInput.nextLine();


                userList.placeIterator();
                for(int i = 0; i < userList.getLength(); i++){
                    if(userList.getIterator().getUsername().equals(username) && userList.getIterator().getPassword().equals(password)){
                         textFile = userList.getIterator().getTextFile();
                    }
                    else {
                        userList.advanceIterator();
                    }
                    if(userList.offEnd()){
                        System.out.println("User Not found Please create an account!");
                    }
                }
                textFile = textFile+".txt";


                File uf = new File(textFile);

                Scanner inFile = new Scanner (uf);

                ExpenseDatabase database = new ExpenseDatabase();


                String expenseID;
                String expenseName;
                String expenseType;
                String date;
                String time;
                double cost;

                    // adding the persons expenses into a hash table.

                while(inFile.hasNextLine()){
                    expenseID = inFile.nextLine();
                    expenseName = inFile.nextLine();
                    expenseType = inFile.nextLine();
                    date = inFile.nextLine();
                    time = inFile.nextLine();
                    cost = inFile.nextDouble();
                    if(inFile.hasNextLine()){
                        inFile.nextLine();
                    }
                    if(inFile.hasNextLine()){
                        inFile.nextLine();
                    }



                    Expense addExpense = new Expense(expenseID, expenseName, expenseType, date, time, cost);

                    //Need to figure out how to sort bst by name and date etc etc.
                    database.ht.insert(addExpense);
                    database.bst1.insert(addExpense);
                    database.bst2.insert(addExpense);


                }
                System.out.println();
//---------------------------------------------------------------------------------------------
                String input = "";

                while (!(input.equalsIgnoreCase("x"))){


                    System.out.println("Please select from the following options!");
                    System.out.println("\nD. Display Expenses.\nA. Add to Expenses.\nR. Remove from Expenses\nS. Search for Expense \nX. Exit the Program");
                    System.out.println();
                    System.out.print("Enter your choice: ");
                    input = userInput.nextLine();

                    if(input.equalsIgnoreCase("D")){

                        System.out.println("Please select one of the following options:\n");
                        System.out.println("U. Unsorted");
                        System.out.println("S: Sorted By ID");
                        System.out.println("N. Sorted by NAME");
                        System.out.println("0. Go Back.");
                        System.out.println();

                        System.out.print("Enter your choice: ");
                        String in1 = userInput.nextLine();
                        System.out.println();

                        if(in1.equalsIgnoreCase("U")){
                            System.out.println(database.ht.toString());
                        }

                        else if(in1.equalsIgnoreCase("S")){
                          database.bst1.inOrderPrint();
                        }

                        else if (in1.equalsIgnoreCase("N")){




                        }
                        else if(in1.equalsIgnoreCase("0")){
                            break;
                        }

                    }
                    else if(input.equalsIgnoreCase("A")){
                        System.out.println("Adding to Expense...");

                    }
                    else if(input.equalsIgnoreCase("R")){
                        System.out.println("Removing from Expense...");

                    }
                    else if(input.equalsIgnoreCase("S")){
                        System.out.println("Searching for expense...");
                    }

                }

            }

            else if(in.equalsIgnoreCase("B")){

                System.out.println("Creating an account.");
                System.out.println();
                System.out.print("Enter your name: ");
                String name = userInput.nextLine();

                String newUserName = "";
                while(true) {
                    System.out.println("Enter your Username: ");
                    newUserName = userInput.next();

                    if(usernameTaken(newUserName,userList)){
                        System.out.println("Username is already taken please select a different one! ");
                        continue;
                    }
                    break;
                }



                String newPassword = "";
                String confPassword = "";
                while(true){
                    userInput.nextLine();
                    System.out.print("Enter your password: ");
                    newPassword = userInput.nextLine();
                    System.out.print("Please confirm your password: ");
                    confPassword = userInput.nextLine();

                    if(!(newPassword.equals(confPassword))){
                        System.out.println("The password does not match please try again!");
                        continue;
                    }
                    break;
                }

                String newFileName = newUserName;
                userList.addLast(new User(name, newUserName, newPassword,newFileName));
                PrintWriter pw = new PrintWriter(new FileOutputStream(file,false));
                pw.print(userList.toString());
                File newFile = new File(newFileName + ".txt");
                newFile.createNewFile();
                pw.close();



            }

        }

        System.out.println("\nGoodbye!");



    }


    public static boolean usernameTaken(String userName, List<User> userList){
        userList.placeIterator();
        for(int i = 0; i < userList.getLength(); i++){
            if(userList.getIterator().getUsername().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public static void populateUserList(List<User> userList, Scanner userScanner, File file){
        while (userScanner.hasNextLine()){

            String name = userScanner.nextLine();
            String username = userScanner.nextLine();
            String password = userScanner.nextLine();
            String textFileName = userScanner.nextLine();

            User user = new User(name, username, password, textFileName);
            userList.addLast(user);
        }

    }


}
