package Operators;

import org.apache.logging.log4j.core.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BillOperator extends Operator {
    public BillOperator(Logger logger, String usernameFromAbove) {
        super(logger, usernameFromAbove);
    }

    public void addBill(Scanner scanner, String username) {
        //have the user pick a csv file or manually enter the data
        Integer fromSwitch = this.askUserWhereFrom(scanner);
        SqlOperator addBillSqlOperator = new SqlOperator(logger);
        if (fromSwitch == 0){
            String csvName  = "";
            Boolean outcomeOfCsvPush = false;
            while (outcomeOfCsvPush == false) {
                csvName = promptUserForCsvName(scanner);
                outcomeOfCsvPush = addBillSqlOperator.pushCsvDataToDatabase(csvName, username, "BillTable");
            }
        }
        else {
            //repeat until user wants to stop
            boolean manualEntryStop = false;
            while (!manualEntryStop) {
                List<String> manualBillInstance = promptUserForManualBillInstance(scanner);
                if (manualBillInstance.get(0) != "stop123"){
                    addBillSqlOperator.pushManualDataToDatabase(manualBillInstance,username);
                }
                else{
                    manualEntryStop = true;
                    logger.debug("user entered stop123 so the data entry phase ended.");
                    System.out.println("Ending Bill data entry.");
                }
            }
        }
    }



    public List<String> promptUserForManualBillInstance(Scanner scanner) {
        System.out.println("Please enter the following values separated with commas.Do not enter incorrect data types:");
        System.out.println("Due Date, Bill Name, Amount Due, Credit or Debit Payment, Planned Payment Account Name... or enter stop123 to stop.");
        String billValuesRaw = scanner.nextLine();
        billValuesRaw = billValuesRaw.replace(", ",",");
        Boolean checked = false;
        while(checked == false){
            checked = validateManualBillInput(billValuesRaw);
            if (checked == false){
                promptUserForManualBillInstance(scanner);
            }
        }
        List<String> billValuesListOfString = Arrays.asList(billValuesRaw.split(","));
        return billValuesListOfString;
    }

    public Boolean validateManualBillInput(String billValuesRaw) {
        billValuesRaw = billValuesRaw.replace(" ", "");
        String[] x = billValuesRaw.split(",");
        try {
            if (x[0] == "stop123"){
                return true;
            }
            if (x.length != 5) {
                logger.debug("user entered too many commas.");
                return false;
            }
            if (Integer.valueOf(x[0]) > 31) {
                logger.debug("user entered a due date greater than 31.");
                return false;
            }
            Boolean returner = false;
            returner = x[0].matches("[0-9]+");
            if (returner == false) {
                return returner;
            }
            returner = x[1].matches("[a-zA-z]+");
            if (returner == false) {
                return returner;
            }
            returner = x[2].matches("[+-]?([0-9]*[.])?[0-9]+");
            if (returner == false) {
                return returner;
            }
            returner = x[3].matches("(cash|card)");
            if (returner == false) {
                return returner;
            }
            returner = x[4].matches("[a-zA-z]+");
            return returner;
        }
        catch(Exception e){
            logger.debug("user input for bill is incorrect.");
            logger.debug(e.toString());
            return false;
        }
    }
}
