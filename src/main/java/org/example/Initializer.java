package org.example;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Initializer {
    SqlOperator initializerSqlOperator= null;
    Logger logger = null;
    String dbName = "MoneyManager";
    public Initializer(Logger loggerFromAbove) {
        logger = loggerFromAbove;
        initializerSqlOperator = new SqlOperator(logger);
    }

    public void runMe() throws SQLException, IOException {
        Scanner initializerScanner = new Scanner(System.in);
        List<String> usernames = grabUsernames(initializerScanner);
        String chosenUser = pickUsername(usernames,initializerScanner);
        if(!checkForPreviousRun(chosenUser)){
            generateRequiredTables(chosenUser);
        }
    }
    public List<String> grabUsernames(Scanner initializerScanner) throws SQLException, IOException {
        // if the username db and table are present then skip creation
        if(!initializerSqlOperator.checkForUsernameTable()){
            //creates a database for the username table to sit in.
            //creates the username table
            initializerSqlOperator.createUsernameDatabaseAndTable();
            //fill the username table
            //crate a database for the corresponding username
            System.out.println("No usernames present.");
            //create username
            createUsernameInstanceAndCorrespondingDatabase(initializerScanner);

        }
        //
        List<String> usernamesReturner = initializerSqlOperator.grabUsernamesFromUsernameTable();
        return usernamesReturner;
    }

    private void createUsernameInstanceAndCorrespondingDatabase(Scanner initializerScanner) throws SQLException, IOException {
        System.out.println("Please enter a username:");
        String username = initializerScanner.nextLine();
        initializerSqlOperator.addInstanceToUserTable(username);
        initializerSqlOperator.generateUserDatabase(username);
    }

    public String pickUsername(List<String> usernames, Scanner scanner) throws SQLException, IOException {
        String pickedUsername = null;
        boolean validation = false;
        if(usernames.isEmpty()){
            while(validation == false){
                System.out.println("No usernames are present in the database. please create one (a-Z,1-0):");
                pickedUsername = scanner.nextLine();
                validation = validatePickedUsername(pickedUsername);
                if(validation == true){
                    initializerSqlOperator.putUsernameInDatabase(pickedUsername);
                }
            }
            return pickedUsername;
        }
        else {
            while(validation == false){
                //must pick from list or add new user
                System.out.println("Pick a username from the following list:");
                System.out.println(usernames.toString());
                pickedUsername = scanner.nextLine();
                validation = validatePickedUsername(pickedUsername);
            }
        }
        return pickedUsername;
    }

    private boolean validatePickedUsername(String pickedUsername) {
        if(pickedUsername.matches("[a-zA-z0-9]+")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkForPreviousRun(String chosenUser) {
        String checkForTransactionTableQuery = "SELECT * FROM " + chosenUser + "TransactionTable";
        Boolean previousRun = false;
        try {
            previousRun = initializerSqlOperator.sendStatementToDatabaseNoEH(checkForTransactionTableQuery, initializerSqlOperator.createConnectionUrl(dbName));
        }
        catch(Exception e){
            logger.debug("no transaction table was found for " + chosenUser);
            logger.debug(e.toString());
            return false;
        }
        return previousRun;
    }
    public void generateRequiredTables(String chosenUser) {
        generateBillTable(chosenUser);
        generateTransactionTable(chosenUser);
        generatePaymentAccountTable(chosenUser);
        generateBudgetTable(chosenUser);
        generateMasterTable(chosenUser);

    }
    public void generateBillTable(String chosenUser) {
        initializerSqlOperator.createTable("BillTable", chosenUser);
    }
    public void generateTransactionTable(String chosenUser) {
        //HARDCODE Transaction TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable("TransactionTable", chosenUser);
    }
    public void generatePaymentAccountTable(String chosenUser) {
        //HARDCODE PaymentAccount TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable("PaymentAccountTable", chosenUser);
    }
    public void generateBudgetTable(String chosenUser) {
        //HARDCODE Budget TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable("BudgetTable", chosenUser);
    }
    public void generateMasterTable(String chosenUser) {
        //HARDCODE Master TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable("MasterTable", chosenUser);
    }


}
