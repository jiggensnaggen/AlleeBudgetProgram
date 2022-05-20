package Operators;

import org.apache.log4j.Logger;
import org.example.SqlOperator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BillOperator extends Operator {
    public BillOperator(Logger logger) {
        super(logger);
    }

    public void addBill(Scanner scanner, String username) {
        //have the user pick a csv file or manually enter the data
        Integer fromSwitch = this.askUserWhereFrom(scanner);
        SqlOperator addBillSqlOperator = new SqlOperator(logger);
        if (fromSwitch == 0){
            String csvName = promptUserForCsvName(scanner);
            addBillSqlOperator.pushCsvDataToDatabase(csvName);
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
        System.out.println("Please enter the following values separated with commas.Do not enter incorrect data types:";
        System.out.println("Due Date, Bill Name, Amount Due, Cash or Card Payment, Planned Payment Account Name... or enter stop123 to stop.");
        String billValuesRaw = scanner.nextLine();
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

    private Boolean validateManualBillInput(String billValuesRaw) {
        String[] x = billValuesRaw.split(",");
        if(x[0].matches("[0-9]+") & x[1].matches("[a-zA-z]+") & x[2].matches("^\\$(0|[1-9][0-9]{0,2})(,\\d{3})*(\\.\\d{1,2})?$") & x[3].matches("(cash|card)") & x[4].matches("[a-zA-z]+")){
            return true;
        }
        else
    }
}
