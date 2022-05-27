package org.example;

import Operators.PaymentAccountOperator;
import Operators.SqlOperator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class SqlOperatorTest {

    Logger testLogger = (Logger) LogManager.getLogger();
    String username = "nicholas";
    SqlOperator testSQLOperator = new SqlOperator(testLogger);

    @Test
    public void verifyPushCsvDataToDb(){
        assertEquals(true, testSQLOperator.pushCsvDataToDatabase("paymentAccountTableNicholas","nicholasPaymentAccountTable"));
    }
}
