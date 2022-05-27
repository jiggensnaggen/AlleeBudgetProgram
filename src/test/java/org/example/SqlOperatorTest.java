package org.example;

import Operators.PaymentAccountOperator;
import Operators.SqlOperator;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class SqlOperatorTest  {


    String dbName = "MoneyManager";
    Logger testLogger = (Logger) LogManager.getLogger();
    String username = "nicholas";
    SqlOperator testSQLOperator = new SqlOperator(testLogger);
    @BeforeEach
    protected void setUp(){}

    @Test
    public void verifyPushPaymentAccountCsvDataToDb(){
        Boolean value = true;
        assertEquals(value, testSQLOperator.pushCsvDataToDatabase("paymentAccountTableTest","nicholasPaymentAccountTable"));
    }

    @Test
    public void verifyPushBudgetCsvDataToDb(){
        Boolean value = true;
        assertEquals(value, testSQLOperator.pushCsvDataToDatabase("budgetTableTest","nicholasBudgetTable"));
    }
    @AfterEach
    protected void tearDown() throws Exception{
        testSQLOperator.sendStatementToDatabase("Delete from nicholasPaymentAccountTable where payment_account_instance_id like 999999;", testSQLOperator.createConnectionUrl(dbName));
        testSQLOperator.sendStatementToDatabase("Delete from nicholasBudgetTable where budget_instance_id like 999999;", testSQLOperator.createConnectionUrl(dbName));
    }


}
