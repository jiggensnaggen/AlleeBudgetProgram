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
        Boolean result = testPaymentAccountOperator.validateManualInput(paymentAccountValuesTestEmpty);
        assertEquals(result,false);
    }

    @Test
    public void testValidateManualPaymentAccountInputGood(){
        String paymentAccountValuesTestGood = "5, 462, Boa Cash Rewards, Bank of America, credit, -1131, 2, 5, 5, 0";
        Boolean result = testPaymentAccountOperator.validateManualInput(paymentAccountValuesTestGood);
        assertEquals(result,true);
    }

    @Test
    public void testValidateManualPaymentAccountInputExtraComma(){
        String paymentAccountValuesTestGood = "5, 462, Boa Cash, Rewards, Bank of America, credit, -1131, 2, 5, 5, 0";
        Boolean result = testPaymentAccountOperator.validateManualInput(paymentAccountValuesTestGood);
        assertEquals(result,false);
    }
    @Test
    public void testValidateManualPaymentAccountInputIntWhereStringShouldBe(){
        String paymentAccountValuesTestGood = "5, 462, Boa Cash Rewards, 2, credit, -1131, 2, 5, 5, 0";
        Boolean result = testPaymentAccountOperator.validateManualInput(paymentAccountValuesTestGood);
        assertEquals(result,false);
    }

    @Test
    public void testValidateManualPaymentAccountInputStop123(){
        String paymentAccountValuesTestGood = "stop123";
        Boolean result = testPaymentAccountOperator.validateManualInput(paymentAccountValuesTestGood);
        assertEquals(result,true);
    }
}
