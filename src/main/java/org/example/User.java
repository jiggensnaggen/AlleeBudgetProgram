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
        String selectionString = null;
        while (tryAgain) {
            //give the user a list of items to pick from. each item gets a corresponding number
            System.out.println("Pick what you would like to do:");
            System.out.println("1: Add Bill");
            System.out.println("2: Add Payment Account");
            System.out.println("3: Add Budget");
            System.out.println("4: Add Transaction");
            System.out.println("5: Generate What-If");
            System.out.println("6: Generate Report");
            System.out.println("7: Exit");
            selectionString = scanner.nextLine();

            //if invalid selction then return true to go again.
            tryAgain = validateSelection(selectionString);
        }
        return Integer.getInteger(selectionString);
    }

    public boolean validateSelection(String selection) {
        Integer selectionInt = null;
        try{
            selectionInt = Integer.getInteger(selection);
        }
        catch(Exception e){
            logger.warn("User did not enter a number selection when choosing an action. Re-prompting user");
            return false;
        }
        for(int x=0;x<7;x++){
            if (selectionInt != x) {
                logger.warn("User did not enter a correct number selection when choosing an action. Re-promptin user");
                return false;
            }
        }
        return true;
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
