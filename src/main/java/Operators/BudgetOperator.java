package Operators;
import org.apache.logging.log4j.core.*;

import java.util.List;
import java.util.Scanner;

public class BudgetOperator extends Operator{


    public BudgetOperator(Logger logger, String username) {
        super(logger,username);
    }
    public void addBudget(Scanner scanner, String username) {
        //have the user pick a csv file or manually enter the data
        Integer fromSwitch = this.askUserWhereFrom(scanner);
        SqlOperator addBudgetSqlOperator = new SqlOperator(logger);
        if (fromSwitch == 0){
            String csvName  = "";
            Boolean outcomeOfCsvPush = false;
            while (outcomeOfCsvPush == false) {
                csvName = promptUserForCsvName(scanner);
                outcomeOfCsvPush = addBudgetSqlOperator.pushCsvDataToDatabase(csvName, username, "BudgetTable");
            }
        }
        else {
            //repeat until user wants to stop
            boolean manualEntryStop = false;
            String itemsRequiredPrint = "budget_id, category, subcategory, limit, amount_used, amount_left, budget_start, budget_end,  budgeted_transaction_id_list, budget_instance_id";
            while (!manualEntryStop) {
                List<String> manualBudgetInstance = promptUserForManualInstance(scanner,itemsRequiredPrint);
                if (manualBudgetInstance.get(0) != "stop123"){
                    addBudgetSqlOperator.pushManualDataToDatabase(manualBudgetInstance,username, "BudgetTable");
                }
                else{
                    manualEntryStop = true;
                    logger.debug("user entered stop123 so the data entry phase ended.");
                    System.out.println("Ending Bill data entry.");
                }
            }
        }
    }




    @Override
    public Boolean validateManualInput(String billValuesRaw) {
        billValuesRaw = billValuesRaw.replace(" ", "");
        String[] x = billValuesRaw.split(",");
        try {
            if (x[0] == "stop123"){
                return true;
            }
            if (x.length != 10) {
                logger.debug("user entered too many commas.");
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
            returner = x[2].matches("[a-zA-z]+");
            if (returner == false) {
                return returner;
            }
            returner = x[3].matches("[+-]?([0-9]*[.])?[0-9]+");
            if (returner == false) {
                return returner;
            }
            returner = x[4].matches("[+-]?([0-9]*[.])?[0-9]+");
            if (returner == false) {
                return returner;
            }
            returner = x[5].matches("[+-]?([0-9]*[.])?[0-9]+");
            if (returner == false) {
                return returner;
            }
            returner = x[6].matches("((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
            if (returner == false) {
                return returner;
            }
            returner = x[7].matches("((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
            if (returner == false) {
                return returner;
            }
            returner = x[8].matches("^\\d+(?:[ \\t]*,[ \\t]*\\d+)+$");
            if (returner == false) {
                return returner;
            }
            returner = x[9].matches("[0-9]+");
            return returner;
        }
        catch(Exception e){
            logger.debug("user input for bill is incorrect.");
            logger.debug(e.toString());
            return false;
        }
    }
}
