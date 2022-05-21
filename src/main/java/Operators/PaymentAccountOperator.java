package Operators;

import org.apache.log4j.Logger;
import org.example.SqlOperator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PaymentAccountOperator extends Operator{
    public PaymentAccountOperator(Logger logger, String usernameFromAbove) {
        super(logger, usernameFromAbove);
    }

    public void addPaymentAccount(Scanner scanner, String username) {
        //have the user pick a csv file or manually enter the data
        Integer fromSwitch = this.askUserWhereFrom(scanner);
        SqlOperator addBillSqlOperator = new SqlOperator(this.logger);
        if (fromSwitch == 0){
            String csvName  = "";
            Boolean outcomeOfCsvPush = false;
            while (outcomeOfCsvPush == false) {
                csvName = promptUserForCsvName(scanner);
                outcomeOfCsvPush = addBillSqlOperator.pushCsvDataToDatabase(csvName, username, "PaymentAccountTable");
            }
        }
        else {
            //repeat until user wants to stop
            boolean manualEntryStop = false;
            while (!manualEntryStop) {
                List<String> manualBillInstance = promptUserForManualPaymentAccountInstance(scanner);
                if (manualBillInstance.get(0) != "stop123"){
                    addBillSqlOperator.pushManualDataToDatabase(manualBillInstance,username);
                }
                else{
                    manualEntryStop = true;
                    logger.debug("user entered stop123 so the data entry phase ended.");
                    System.out.println("Ending Payment Account data entry.");
                }
            }
        }


    }

    private List<String> promptUserForManualPaymentAccountInstance(Scanner scanner) {
        System.out.println("Please enter the following values separated with commas.Do not enter incorrect data types:");
        System.out.println("payment_account_id, account_number, account_name,  account_institution, account_type, account_balance, account_due_date, account_report_date, account_closing_date, payment_account_instance_id... or enter stop123 to stop.");
        String billValuesRaw = scanner.nextLine();
        billValuesRaw = billValuesRaw.replace(", ",",");
        Boolean checked = false;
        while(checked == false){
            checked = validateManualPaymentAccountInput(billValuesRaw);
            if (checked == false){
                promptUserForManualPaymentAccountInstance(scanner);
            }
        }
        List<String> billValuesListOfString = Arrays.asList(billValuesRaw.split(","));
        return billValuesListOfString;
    }

    private Boolean validateManualPaymentAccountInput(String paymentAccountValuesRaw) {
        paymentAccountValuesRaw = paymentAccountValuesRaw.replace(" ", "");
        String[] x = paymentAccountValuesRaw.split(",");
        if (x.length != 10){
            logger.debug("user entered too many commas.");
            return false;
        }
///start here
        Boolean returner = false;
        //payment_account_id
        returner = x[0].matches("[0-9]+");
        if(returner == false){
            return returner;
        }
        //account number
        returner = x[1].matches("[0-9]+");
        if(returner == false){
            return returner;
        }
        //account_name
        returner = x[2].matches("[a-zA-z0-9]+");
        if(returner == false){
            return returner;
        }
        //,  account_institution,
        returner = x[3].matches("[a-zA-z]+");
        if(returner == false){
            return returner;
        }
        // account_type
        returner = x[4].matches("[debit|credit]+");
        if(returner == false){
            return returner;
        }


        //account_balance
        returner = x[5].matches("[+-]?([0-9]*[.])?[0-9]+");
        if(returner == false){
            return returner;
        }
        //account_due_date
        returner = x[6].matches("[0-9]+");
        if(returner == false){
            return returner;
        }
        //account_report_date
        returner = x[7].matches("[0-9]+");
        if(returner == false){
            return returner;
        }
        //account_closing_date
        returner = x[8].matches("[0-9]+");
        if(returner == false){
            return returner;
        }
        //payment_account_instance_id
        returner = x[9].matches("[0-9]+");
        return returner;

    }
}
