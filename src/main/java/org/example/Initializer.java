package org.example;


public class Initializer {
    SqlOperator initializerSqlOperator= new SqlOperator();
    public Initializer() {

    }

    public void runMe() {
        grabUsernames();
        pickUsername();
        if(!checkForPreviousRun()){
            generateRequiredTables();
        }
    }
    public void grabUsernames() {

    }
    public void pickUsername() {

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
