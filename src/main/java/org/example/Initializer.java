package org.example;


import org.apache.log4j.Logger;

import java.util.Scanner;

public class Initializer {
    SqlOperator initializerSqlOperator= new SqlOperator();
    Logger logger = null;
    public Initializer(Logger logger) {
        logger = logger;
    }

    public void runMe() {
        Scanner initializerScanner = new Scanner(System.in);
        String[] usernames = grabUsernames(initializerScanner);
        String chosenUser = pickUsername(usernames);
        if(!checkForPreviousRun(chosenUser)){
            generateRequiredTables(chosenUser);
        }
    }
    public String[] grabUsernames(Scanner initializerScanner) {
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
        String[] usernamesReturner = initializerSqlOperator.grabUsernamesFromUsernameTable();
        return usernamesReturner;
    }

    private void createUsernameInstanceAndCorrespondingDatabase(Scanner initializerScanner) {
        System.out.println("Please enter a username:");
        String username = initializerScanner.nextLine();
        initializerSqlOperator.addInstanceToUserTable(username);
        initializerSqlOperator.generateUserDatabase(username);
    }

    public String pickUsername(String[] usernames) {
        //must pick from list or add new user
        return null;
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
