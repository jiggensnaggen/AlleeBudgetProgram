package org.example;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SqlOperator {
    Logger logger = null;
    public SqlOperator(Logger logger) {
        logger = logger;
    }

    public void createTable(String tableName, String chosenUser) {
        String[] tableNames = new String[]{"BillTable", "TransactionTable","PaymentAccountTable","BudgetTable","MasterTable"};
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

    public void createUsernameDatabaseAndTable() {
    }

    public boolean checkForUsernameDatabaseAndTable(){


        return false;
    }

    public String[] grabUsernamesFromUsernameTable() {
    }


    public boolean sendStatementToDatabase(String sqlQuery,String connectionUrl){
        // try to connect to db
        try{
            Connection conn = DriverManager.getConnection(connectionUrl);
            Statement stmt = conn.createStatement();
            // try to send statement to db
            try {
                stmt.executeUpdate(sqlQuery);
                logger.debug("statement " + sqlQuery + " successfully executed");
                return true;
            }
            catch (SQLException e) {
                logger.fatal("Statement " + sqlQuery + " was not executed successfully.");
                System.out.println("Statement " + sqlQuery + " was not executed successfully. Exiting now.");
                System.exit(0);
            }
        }
        catch(Exception e){
            logger.fatal("Could not connect to the database.");
            System.out.println("Could not connect to the database. Exiting now.");
            System.exit(0);
        }
        return false;
    }
}
