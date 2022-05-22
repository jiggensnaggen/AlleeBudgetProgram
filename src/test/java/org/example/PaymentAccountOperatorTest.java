package org.example;

import Operators.BillOperator;
import Operators.PaymentAccountOperator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class PaymentAccountOperatorTest {


    Logger testLogger = (Logger) LogManager.getLogger();
    String username = "nicholas";
    PaymentAccountOperator testPaymentAccountOperator = new PaymentAccountOperator(testLogger, username);

//    @Rule
//    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void testValidateManualPaymentAccountInputEmpty(){
        String paymentAccountValuesTestEmpty = "";
        Boolean result = testPaymentAccountOperator.validateManualPaymentAccountInput(paymentAccountValuesTestEmpty);
        assertEquals(result,false);
    }

    @Test
    public void testValidateManualPaymentAccountInputGood(){
        String paymentAccountValuesTestGood = "5, 462, Boa Cash Rewards, Bank of American, credit, -1131, 2, 5, 5, 0";
        Boolean result = testPaymentAccountOperator.validateManualPaymentAccountInput(paymentAccountValuesTestGood);
        assertEquals(result,true);
    }


}
