package org.example;

import Operators.SqlOperator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Test;

public class CompleteResetTest {
    String dbName = "MoneyManager";
    Logger testLogger = (Logger) LogManager.getLogger();
    String username = "nicholas";
    SqlOperator testSQLOperator = new SqlOperator(testLogger);

    @Test
    public void resetAll(){
        testSQLOperator.sendStatementToDatabase("DROP TABLE " + username + "PaymentAccountTable;", testSQLOperator.createConnectionUrl(dbName));
        testSQLOperator.sendStatementToDatabase("DROP TABLE " + username + "BudgetTable;", testSQLOperator.createConnectionUrl(dbName));
        testSQLOperator.sendStatementToDatabase("DROP TABLE " + username + "TransactionTable;", testSQLOperator.createConnectionUrl(dbName));
        testSQLOperator.sendStatementToDatabase("DROP TABLE " + username + "BillTable;", testSQLOperator.createConnectionUrl(dbName));
        testSQLOperator.sendStatementToDatabase("DROP TABLE " + username + "MasterTable;", testSQLOperator.createConnectionUrl(dbName));
        testSQLOperator.sendStatementToDatabase("Delete from usernames;", testSQLOperator.createConnectionUrl(dbName));
    }

    @Test
    public void resetUsernames(){
        testSQLOperator.sendStatementToDatabase("Delete from usernames;", testSQLOperator.createConnectionUrl(dbName));
    }

}
