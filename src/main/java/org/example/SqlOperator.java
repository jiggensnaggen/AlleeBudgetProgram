package org.example;

import org.apache.log4j.Logger;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlOperator {
    Logger logger = null;
    String dbName = "MoneyManager";
    public SqlOperator(Logger loggerFromAbove) {
        logger = loggerFromAbove;
    }

    public void createTable(String tableName, String chosenUser) {
        String createTableConnectionUrl = createConnectionUrl("MoneyManager");
        String createTableQuery = null;
        //HARDCODE TABLE CREATION INPUTS HERE
        if ("TransactionTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser + tableName + " (transaction_id INTEGER,  description varchar(255), amount_in_or_out DECIMAL(38,2), date_paid DATETIME, category varchar(255), subcategory varchar(255), payment_account_id INTEGER, budget_id INTEGER, bill_id INTEGER);";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("PaymentAccountTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser  + tableName + " (payment_account_id INTEGER, account_number INTEGER, account_name VARCHAR(255),  account_institution VARCHAR(255), account_type varchar(255), account_balance DECIMAL(38,2), account_due_date INTEGER, account_report_date INTEGER, account_closing_date INTEGER, payment_account_instance_id INTEGER);";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("BillTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser  + tableName + " (bill_id INTEGER, due_date DATETIME, item_name VARCHAR(255),  amount_due DECIMAL(38,2), credit_or_debit_payment varchar(255), payment_type_used VARCHAR(255), payment_status VARCHAR(255), bill_transaction_difference DECIMAL(38,2), transaction_id INTEGER, planned_payment_account_id INTEGER, used_payment_account_id INTEGER, date_paid DATETIME, bill_instance_id INTEGER);";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("BudgetTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser  + tableName + " (budget_id INTEGER, category VARCHAR(255), subcategory VARCHAR(255), limit DECIMAL(38,2), amount_used DECIMAL(38,2), amount_left DECIMAL(38,2), budget_start DATETIME, budget_end DATETIME,  budgeted_transaction_id_list VARCHAR(255), budget_instance_id INTEGER);";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);

        } else if ("MasterTable".equals(tableName)) {
            createTableQuery = "CREATE TABLE " + chosenUser  + tableName + " (category varchar(255), subcategory varchar(255), description varchar(255), amount_in_or_out DECIMAL(38,2),date_paid DATETIME, payment_account_id INTEGER, account_name VARCHAR(255), account_type varchar(255), account_balance DECIMAL(38,2),budget_id INTEGER, budget_start DATETIME, budget_end DATETIME,amount_used DECIMAL(38,2), amount_left DECIMAL(38,2),  limit DECIMAL(38,2), master_table_id INTEGER);";
            sendStatementToDatabase(createTableQuery, createTableConnectionUrl);
        }

    }

    public String createConnectionUrl(String databaseName) {
        String connectionUrl =
                "jdbc:sqlserver://localhost;"
                        + "databaseName="+ databaseName +";" +
                        "encrypt=true;" +
                        "trustServerCertificate=true;" +
                        "integratedsecurity=true;"
        ;
        return connectionUrl;
    }

    public void pushManualDataToDatabase(List<String> manualBillInstance, String username) {
        String billQuery = "INSERT INTO " + username + "BillTable VALUES ('" + manualBillInstance.get(0) + "','" +manualBillInstance.get(1) + "','" +manualBillInstance.get(2) + "','" +manualBillInstance.get(3) + "','" +manualBillInstance.get(4) + "');";
        sendStatementToDatabase(billQuery,createConnectionUrl(dbName));
    }

    public Boolean pushCsvDataToDatabase(String csvName, String username, String tableName) {
        String csvQuery = "BULK INSERT " + username + tableName +  "\n" +
                "FROM 'C:\\budgetingSourceData\\"+ csvName +".csv'\n" +
                "WITH\n" +
                "(\n" +
                "    FIRSTROW = 2, -- as 1st one is header\n" +
                "    FIELDTERMINATOR = ',',  --CSV field delimiter\n" +
                "    ROWTERMINATOR = '\\n',   --Use to shift the control to next row\n" +
                "    TABLOCK\n" +
                ")";
        try{
            sendStatementToDatabase(csvQuery,createConnectionUrl(dbName));
            logger.debug("csv data pushed to database table " + username + tableName + " was successful.");
            return true;
        }
        catch(Exception e){
            logger.debug("failed to push csv data to database table " + username + tableName + ".");
            System.out.println("failed to push csv data to the "+ username + tableName + "table. Please check your data and try again.");
            e.printStackTrace();
            return false;
        }
    }

    public void addInstanceToUserTable(String username) throws SQLException, IOException {
        String nextId = null;
        String connectionUrl = createConnectionUrl(dbName);
        String getNextIdQuery = "select MAX(user_id) from usernames;";
        Table returnedTable = returnDataFromDatabase(getNextIdQuery,connectionUrl);
        System.out.println(returnedTable.toString());
        System.exit(0);
        String addInstanceQuery = "INSERT INTO usernames(username, user_id) VALUES ('" + username + "', '" + nextId + "');";
    }


    public void createUsernameDatabaseAndTable() {
        String createUsernamesTable = "CREATE TABLE usernames (username VARCHAR(255), user_id INTEGER);";
        String connectionUrl = createConnectionUrl(dbName);
        if (sendStatementToDatabase(createUsernamesTable,connectionUrl)){
            logger.debug("creation of username table successful.");
        }
        else{
            logger.fatal("Could not create the usernames table. Exiting now.");
            System.out.println("Could not create the usernames table. Exiting now.");
            System.exit(0);
        }
    }

    public boolean checkForUsernameTable(){
        //creates query and connection url, then validates that usernames is present in the database
        String userNameChecker = "Select * from usernames;";
        String connectionUrl = createConnectionUrl(dbName);
        Boolean usernamePresent = sendStatementToDatabase(userNameChecker,connectionUrl);
        return usernamePresent;
    }

    public List<String> grabUsernamesFromUsernameTable() throws SQLException, IOException {
        String grabUsernamesQuery = "Select * from usernames;";
        String connectionUrl = createConnectionUrl(dbName);
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
                String resultSetReturned = "com.microsoft.sqlserver.jdbc.SQLServerException: A result set was generated for update.";
                if(e.toString().contains(resultSetReturned)){
                    logger.debug("statement " + sqlQuery + " successfully executed");
                    return true;
                }

                if(sqlQuery != "Select * from usernames;"){
                    logger.fatal("Statement " + sqlQuery + " was not executed successfully.");
                    System.out.println("Statement " + sqlQuery + " was not executed successfully. Exiting now.");
                    logger.fatal(e.toString());
                    System.exit(0);
                }
                else{
                    logger.debug("could not find a table called usernames. going to create one.");
                    logger.debug(e); ///remove when done
                    }
            }
        }
        catch(Exception e){

            logger.fatal("Could not connect to the database.");
            System.out.println("Could not connect to the database. Exiting now.");
            System.exit(0);
        }
        return false;
    }

    public boolean sendStatementToDatabaseNoEH(String sqlQuery,String connectionUrl) throws SQLException {
        // try to connect to db
        Connection conn = DriverManager.getConnection(connectionUrl);
        Statement stmt = conn.createStatement();
        // try to send statement to db
        stmt.executeUpdate(sqlQuery);
        logger.debug("statement " + sqlQuery + " successfully executed");
        return true;
    }

    public void putUsernameInDatabase(String pickedUsername) {
        Integer nextUsernameId = (getNextUsernameIdFromDatabase() + 1);
        String nextUsernameIdString = nextUsernameId.toString();
        String addUsernameToDatabaseQuery = "INSERT INTO usernames (username, user_id) VALUES ('" + pickedUsername + "', '" + nextUsernameIdString +"');";
        sendStatementToDatabase(addUsernameToDatabaseQuery,createConnectionUrl(dbName));
    }

    private Integer getNextUsernameIdFromDatabase() {
        String queryForNextUsernameId = "SELECT MAX(user_id) FROM usernames";
        String connectionUrl = createConnectionUrl(dbName);
        try {
            Table returnedIdTable = returnDataFromDatabase(queryForNextUsernameId, connectionUrl);
            String returnedIdString = (String) returnedIdTable.column(0).asList().get(0);
            Integer returnedIdInt = 0;
            if (returnedIdString.equals("")){
                returnedIdInt = -1;
                return returnedIdInt;
            }
            Integer returnedId = Integer.getInteger(returnedIdString);
            return returnedId;
        }
        catch(Exception e){
            logger.fatal("could not get username id from the database. Exiting here.");
            System.out.println("could not get username id from the database. Exiting here.");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("couldnt get username id from the database. exiting here");
        System.exit(0);
        return -1;
    }
}
