package org.example;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class User {

    private static final Logger logger = Logger.getLogger(User.class);

    public User() {
        
    }

    public void runMe() {
        Integer selection = null;
        Scanner userScanner = new Scanner(System.in);
        while(selection != 7) {
            selection = promptUserForSelection(userScanner);
            makeSelection(selection, userScanner);
        }
    }

    public Integer promptUserForSelection(Scanner scanner) {
        boolean tryAgain = true;
        Integer selection = null;
        while (tryAgain) {
            //give the user a list of items to pick from. each item gets a corresponding number
            System.out.println("");
            String selectionString = scanner.nextLine();
            selection = Integer.getInteger(selectionString);
            //if invalid selction then return true to go again.
            tryAgain = validateSelection(selection);
        }
        return selection;
    }

    public boolean validateSelection(Integer selection) {
        return false;
    }

    public Boolean makeSelection(Integer selection, Scanner scanner){
        //switch case statement here to decode the selection into the correct function
        switch (selection) {
            case 1: addBill(scanner);
                break;
            case 2: addPaymentAccount(scanner);
                break;
            case 3: addBudget(scanner);
                break;
            case 4: addTransaction(scanner);
                break;
            case 5: generateWhatIf(scanner);
                break;
            case 6: generateReport(scanner);
                break;
            case 7: return false;
        }

        return true;
    }

    public void addBill(Scanner scanner) {
        //have the user pick a csv file or manually enter the data
        Integer fromSwitch = askUserWhereFrom(scanner);
        SqlOperator addBillSqlOperator = new SqlOperator();
        if (fromSwitch == 0){
            String csvName = promptUserForCsvName(scanner);
            addBillSqlOperator.pushCsvDataToDatabase(csvName);
        }
        else {
            //repeat until user wants to stop
            boolean manualEntryStop = false;
            while (!manualEntryStop) {
                List<String> manualBillInstance = promptUserForManualBillInstance();
                if (manualBillInstance.get(0) != "stop123"){
                    addBillSqlOperator.pushManualDataToDatabase(manualBillInstance);
                }
                else{
                    manualEntryStop = true;
                }
            }
        }

    }

    public Integer askUserWhereFrom(Scanner scanner) {
        return 0;
    }

    public String promptUserForCsvName(Scanner scanner) {
        boolean tryAgain = true;
        String csvNameString = null;
        while (tryAgain) {
            //ask the user for the csv name
            System.out.println("");
            csvNameString = scanner.nextLine();
            //if invalid selction then return true to go again.
            tryAgain = validateCsvIsPresent(csvNameString);
        }
        return csvNameString;
    }

    private boolean validateCsvIsPresent(String csvNameString) {
        return true;
    }

    public List<String> promptUserForManualBillInstance() {
        List<String> returner = null;
        return returner;
    }

    public void addPaymentAccount(Scanner scanner) {
    }

    public void addBudget(Scanner scanner) {
    }

    public void addTransaction(Scanner scanner) {
    }

    public void generateWhatIf(Scanner scanner) {
    }

    public void generateReport(Scanner scanner) {
    }
}
