package org.example;

import Operators.BillOperator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.*;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BillOperatorTest {
    Logger testLogger = (Logger) LogManager.getLogger();
    String username = "nicholas";
    BillOperator testBillOperator = new BillOperator(testLogger, username);

//    @Rule
//    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();


    public void testPromptUserForSelection(){
    }
    @Test
    public void testValidateManualBillInputGood(){
        String passingBillInput = "1, carls jr, 24.86, card, navy fed main";
        Boolean expected = true;
        Boolean result = testBillOperator.validateManualInput(passingBillInput);
        assertEquals(expected,result);
    }
    @Test
    public void testValidateManualBillInputExtraComma(){
        String passingBillInput = "1, carl,s jr, 24.86, card, navy fed main";
        Boolean expected = false;
        Boolean result = testBillOperator.validateManualInput(passingBillInput);
        assertEquals(expected,result);
    }

    @Test
    public void testValidateManualBillInputStringAsNumber(){
        String passingBillInput = "dont know what goes here, carls jr, 24.86, card, navy fed main";
        Boolean expected = false;
        Boolean result = testBillOperator.validateManualInput(passingBillInput);
        assertEquals(expected,result);
    }

    @Test
    public void testValidateManualBillInputInvalidCharacters(){
        String passingBillInput = "1, carls jr, 24.86, card, navy fed main$$big$here";
        Boolean expected = false;
        Boolean result = testBillOperator.validateManualInput(passingBillInput);
        assertEquals(expected,result);
    }

    //@Test
    public void testPromptUserForManualBillInstance() throws SQLException, IOException {
        Scanner testScanner = new Scanner(System.in);
        String[] passingBillInput = {"1", "carls jr", "24.86", "card", "navy fed main"};
        List<String> expected = List.of(passingBillInput);
        String userInput ="1, carls jr, 24.86, card, navy fed main";

//        InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(in);



        //systemInMock.provideLines(userInput);

        BillOperator obj = new BillOperator(testLogger, "nicholas");
        //create a mock scanner
        MockClass mockScanner = mock(MockClass.class);
        //set up the scanner
        when(mockScanner.getScannerInstance().nextLine()).thenReturn(userInput);

        List<String> value = obj.promptUserForManualBillInstance(mockScanner.getScannerInstance());

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PrintStream printStream = new PrintStream(baos);
//        System.setOut(printStream);




        assertEquals(expected,value);
    }
}