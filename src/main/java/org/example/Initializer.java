package org.example;


import java.util.Scanner;

public class Initializer {
    SqlOperator initializerSqlOperator= new SqlOperator();
    public Initializer() {

    }

    public void runMe() {
        Scanner initilizerScanner = new Scanner(System.in);
        grabUsernames(initilizerScanner);
        pickUsername();
        if(!checkForPreviousRun()){
            generateRequiredTables();
        }
    }
    public void grabUsernames(Scanner initilizerScanner) {
        if(!checkForUsernameTable()){
            createUsernameTable(initilizerScanner);
        }


    }

    private boolean checkForUsernameTable() {
    }

    private void createUsernameTable(Scanner initilizerScanner) {
        System.out.println("No usernames present. Please enter a username:");
        String username = initilizerScanner.nextLine();
        initializerSqlOperator.addInstanceToUserTable(username);
        initializerSqlOperator.generateUserDatabase(username);
    }

    public void pickUsername() {
        //if the name is not in the db then it can be added
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
