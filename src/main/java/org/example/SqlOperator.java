package org.example;

import org.apache.log4j.Logger;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlOperator {
    Logger logger = null;
    public SqlOperator(Logger loggerFromAbove) {
        logger = loggerFromAbove;
    }

    public void createTable(String tableName, String chosenUser) {
        String createTableConnectionUrl = createConnectionUrl("MoneyManager");
        String createTableQuery = null;
        //HARDCODE TABLE CREATION INPUTS HERE
        if ("BillTable".equals(tableName)) {

        } else if ("TransactionTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser + tableName + " (transaction_id INTEGER(64),  description varchar(255), amount_in_or_out DOUBLE(255,2), date_paid DATETIME, category varchar(255), subcategory varchar(255), payment_account_id INTEGER(64), budget_id INTEGER(64), bill_id INTEGER(64));";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("PaymentAccountTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser  + tableName + " (payment_account_id INTEGER(64), account_number INTEGER(64), account_name VARCHAR(255),  account_institution VARCHAR(255), account_type varchar(255), account_balance DOUBLE(255,2), account_due_date INTEGER(64), account_report_date INTEGER(64), account_closing_date INTEGER(64), payment_account_instance_id INTEGER(64));";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("BillTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser  + tableName + " (bill_id INTEGER(64), due_date DATETIME, item_name VARCHAR(255),  amount_due DOUBLE(255,2), cash_or_card_payment varchar(255), payment_type_used VARCHAR(255), payment_status VARCHAR(255), bill_transaction_difference DOUBLE(255,2), transaction_id INTEGER(64), planned_payment_account_id INTEGER(64), used_payment_account_id INTEGER(64), date_paid DATETIME, bill_instance_id INTEGER(64));";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("BudgetTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser  + tableName + " (budget_id INTEGER(64), category VARCHAR(255), subcategory VARCHAR(255), limit DOUBLE(255,2), amount_used DOUBLE(255,2), amount_left DOUBLE(255,2), budget_start DATETIME, budget_end DATETIME,  budgeted_transaction_id_list VARCHAR(255), budget_instance_id INTEGER(64));";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("MasterTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser  + tableName + " (category varchar(255), subcategory varchar(255), description varchar(255), amount_in_or_out DOUBLE(255,2),date_paid DATETIME, payment_account_id INTEGER(64), account_name VARCHAR(255), account_type varchar(255), account_balance DOUBLE(255,2),budget_id INTEGER(64), budget_start DATETIME, budget_end DATETIME,amount_used DOUBLE(255,2), amount_left DOUBLE(255,2),  limit DOUBLE(255,2), master_table_id INTEGER(64));";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);
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

    public List<String> grabUsernamesFromUsernameTable() throws SQLException, IOException {
        String grabUsernamesQuery = "Select * from usernames;";
        String connectionUrl = createConnectionUrl("MoneyManager");
        Table returnerTable = returnDataFromDatabase(grabUsernamesQuery,connectionUrl);
        List<String> returnerList = (List<String>) returnerTable.column(0).asList();
        return returnerList;
    }

    private Table returnDataFromDatabase(String query, String connectionUrl) throws SQLException, IOException {
        Table myTable = Table.create();

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             Statement stmt = conn.createStatement();
        ) {
            ResultSet resultSet = stmt.executeQuery(query);
            System.out.println(query);
            System.out.println("sql statement did not throw an error!");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            Integer colCount = rsmd.getColumnCount();
            for (int x = 1; x <= colCount; x++) {
                //create a column in the new table for each col in result set
                StringColumn newColumn = StringColumn.create(rsmd.getColumnName(x));
                myTable.addColumns(newColumn);
            }
            System.out.println("query returned: " + colCount + " columns.------------------------------");
            while (resultSet.next()) {
                //getting the column type
                ArrayList<String> listOfValues = new ArrayList<>();
                for (int x = 1; x <= colCount; x++) {
                    // Read values using index
                    String value = resultSet.getString(x);
                    listOfValues.add(value);

                }
                Row newRow = myTable.appendRow();
                for (int y = 0; y < listOfValues.size(); y++) {
                    newRow.setString(rsmd.getColumnName(y + 1), listOfValues.get(y));
                }
            }
            return myTable;

        }
        catch (SQLException e) {
           logger.fatal("could not return data to the database using thie query: ");
           logger.fatal(query);
           System.out.println("could not get data from database. exiting now");
           e.printStackTrace();
           System.exit(0);
        }
        return myTable;
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
