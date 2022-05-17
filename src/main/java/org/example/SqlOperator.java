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
        String createTableConnectionUrl = createConnectionUrl("MoneyManager");
        String createTableQuery = null;
        //HARDCODE TABLE CREATION INPUTS HERE
        if ("BillTable".equals(tableName)) {

        } else if ("TransactionTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE "  + tableName + " (transaction_id INTEGER(64),  description varchar(255), amount_in_or_out DOUBLE(255,2), date_paid DATETIME,category varchar(255), subcategory varchar(255), payment_account_id INTEGER(64), budget_id INTEGER(64), bill_id INTEGER(64));";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("PaymentAccountTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE "  + tableName + " (payment_account_id INTEGER(64), account_number INTEGER(64), account_name VARCHAR(255),  account_institution VARCHAR(255), account_type varchar(255), account_balance DOUBLE(255,2), account_due_date DATETIME, account_report_date DATETIME, account_closing_date DATETIME, payment_account_instance_id INTEGER(64));";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("PaymentAccountTable".equals(tableName)) {

        } else if ("BudgetTable".equals(tableName)) {

        } else if ("MasterTable".equals(tableName)) {

        }

    }

    private String createConnectionUrl(String databaseName) {
        String connectionUrl =
                "jdbc:sqlserver://localhost;"
                        + "databaseName="+ databaseName +";" +
                        "encrypt=true;" +
                        "trustServerCertificate=true;" +
                        "integratedsecurity=true;"
        ;
        return connectionUrl;
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
        return ;
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
