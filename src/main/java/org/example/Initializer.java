package org.example;


public class Initializer {
    SqlOperator initializerSqlOperator= new SqlOperator();
    public Initializer() {
        runMe();
    }

    private void runMe() {
        grabUsernames();
        pickUsername();
        if(!checkForPreviousRun()){
            generateRequiredTables();
        }
    }
    private void grabUsernames() {

    }
    private void pickUsername() {

    }
    private boolean checkForPreviousRun() {
        return false;
    }
    private void generateRequiredTables() {
        generateBillTable();
        generateTransactionTable();
        generatePaymentAccountTable();
        generateBudgetTable();
        generateMasterTable();

    }
    private void generateBillTable() {
        //HARDCODE BILL TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable();
    }
    private void generateTransactionTable() {
        //HARDCODE Transaction TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable();
    }
    private void generatePaymentAccountTable() {
        //HARDCODE PaymentAccount TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable();
    }
    private void generateBudgetTable() {
        //HARDCODE Budget TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable();
    }
    private void generateMasterTable() {
        //HARDCODE Master TABLE CREATION INPUTS HERE AND PASS THEM INTO .createTable()
        initializerSqlOperator.createTable();
    }


}
