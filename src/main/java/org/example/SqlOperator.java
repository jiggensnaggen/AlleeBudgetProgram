package org.example;

import java.util.List;

public class SqlOperator {
    public SqlOperator() {
    }

    public void createTable(String tableName) {
        String[] tableNames = new String[]{"BillTable", "TransactionTable","PaymentAccountTable","BudgetTable","MasterTable"});
        //HARDCODE TABLE CREATION INPUTS HERE
        if ("BillTable".equals(tableNames)) {

        } else if ("TransactionTable".equals(tableNames)) {

        } else if ("PaymentAccountTable".equals(tableNames)) {

        } else if ("PaymentAccountTable".equals(tableNames)) {

        } else if ("BudgetTable".equals(tableNames)) {

        } else if ("MasterTable".equals(tableNames)) {

        }

    }

    public void pushManualDataToDatabase(List<String> manualBillInstance) {
    }

    public void pushCsvDataToDatabase(String csvName) {
    }

    public void addInstanceToUserTable(String username) {
    }

    public void generateUserDatabase(String username) {
    }
}
