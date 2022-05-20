package org.example;

import Operators.BillOperator;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User testUser = new User();
    Logger testLogger = Logger.getRootLogger();

    public void testPromptUserForSelection(){
    }

    public void testValidateManualBillInput(){
        BillOperator testBillOperator = new BillOperator(testLogger);
        String passingBillInput = "1, carls jr, 24.86,card, navy fed main";
        Boolean expeted = true;
        Boolean result = testBillOperator.validateManualBillInput(passingBillInput);
        assertEquals(expeted,result);
    }

}