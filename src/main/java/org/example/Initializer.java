package org.example;


import java.util.Scanner;

public class Initializer {
    SqlOperator initializerSqlOperator= new SqlOperator();
    public Initializer() {

    }

    public void runMe() {
        Scanner initializerScanner = new Scanner(System.in);
        grabUsernames(initializerScanner);
        pickUsername();
        if(!checkForPreviousRun()){
            generateRequiredTables();
        }
    }
    public void grabUsernames(Scanner initializerScanner) {
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


    }

    private void createUsernameInstanceAndCorrespondingDatabase(Scanner initializerScanner) {
        System.out.println("Please enter a username:");
        String username = initializerScanner.nextLine();
        initializerSqlOperator.addInstanceToUserTable(username);
        initializerSqlOperator.generateUserDatabase(username);
    }

    public void pickUsername() {
        //must pick from list or add new user
    }
    public boolean checkForPreviousRun() {
        return false;
    }
    public void generateRequiredTables() {
        generateBillTable();
        generateTransactionTable();
        generatePaymentAccountTable();
        generateBudgetTable();
        generateMasterTable();

    }
    public void generateBillTable() {
        initializerSqlOperator.createTable("BillTable");
    }
    public void generateTransactionTable() {
        //HARDCODE Transaction TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable("TransactionTable");
    }
    public void generatePaymentAccountTable() {
        //HARDCODE PaymentAccount TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable("PaymentAccountTable");
    }
    public void generateBudgetTable() {
        //HARDCODE Budget TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable("BudgetTable");
    }
    public void generateMasterTable() {
        //HARDCODE Master TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable("MasterTable");
    }


}
