package org.example;

import Operators.BillOperator;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.junit.jupiter.api.Assertions.*;

class BillOperatorTest {
    Logger testLogger = Logger.getRootLogger();
    BillOperator testBillOperator = new BillOperator(testLogger);

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    public void testPromptUserForSelection(){
    }
    @Test
    public void testValidateManualBillInputGood(){
        String passingBillInput = "1, carls jr, 24.86, card, navy fed main";
        Boolean expected = true;
        Boolean result = testBillOperator.validateManualBillInput(passingBillInput);
        assertEquals(expected,result);
    }
    @Test
    public void testValidateManualBillInputExtraComma(){
        String passingBillInput = "1, carl,s jr, 24.86, card, navy fed main";
        Boolean expected = false;
        Boolean result = testBillOperator.validateManualBillInput(passingBillInput);
        assertEquals(expected,result);
    }

    @Test
    public void testValidateManualBillInputStringAsNumber(){
        String passingBillInput = "dont know what goes here, carls jr, 24.86, card, navy fed main";
        Boolean expected = false;
        Boolean result = testBillOperator.validateManualBillInput(passingBillInput);
        assertEquals(expected,result);
    }

    @Test
    public void testValidateManualBillInputInvalidCharacters(){
        String passingBillInput = "1, carls jr, 24.86, card, navy fed main$$big$here";
        Boolean expected = false;
        Boolean result = testBillOperator.validateManualBillInput(passingBillInput);
        assertEquals(expected,result);
    }

    @Test
    public void testPromptUserForManualBillInstance() throws SQLException, IOException {
        Scanner testScanner = new Scanner(System.in);
        String[] passingBillInput = {"1", "carls jr", "24.86", "card", "navy fed main"};
        List<String> expected = List.of(passingBillInput);


        String userInput ="1, carls jr, 24.86, card, navy fed main";
        systemInMock.provideLines(userInput);

        BillOperator obj = new BillOperator(testLogger);

        List<String> value = obj.promptUserForManualBillInstance(testScanner);

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(baos);
//        System.setOut(printStream);




        assertEquals(expected,value);
    }
}