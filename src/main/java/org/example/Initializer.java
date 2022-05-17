package org.example;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Initializer {
    SqlOperator initializerSqlOperator= null;
    Logger logger = null;
    public Initializer(Logger logger) {
        logger = logger;
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
        if(!initializerSqlOperator.checkForUsernameDatabaseAndTable()){
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

    private void createUsernameInstanceAndCorrespondingDatabase(Scanner initializerScanner) {
        System.out.println("Please enter a username:");
        String username = initializerScanner.nextLine();
        initializerSqlOperator.addInstanceToUserTable(username);
        initializerSqlOperator.generateUserDatabase(username);
    }

    public String pickUsername(List<String> usernames, Scanner scanner) throws SQLException, IOException {
        //must pick from list or add new user
        System.out.println("Pick a username from the following list:");
        System.out.println(usernames.toString());
        String pickedUsername = scanner.nextLine();
        return pickedUsername;
    }
    public boolean checkForPreviousRun(String chosenUser) {
        return false;
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
