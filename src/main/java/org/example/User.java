package org.example;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {

    private static final Logger logger = Logger.getLogger(User.class);

    public User() {
        
    }

    public void runMe() {
        Integer selection = 99999;
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
        return Integer.valueOf(selectionString);
    }

    public boolean validateSelection(String selection) {
        Integer selectionInt = 9999;
        try{
            selectionInt = Integer.valueOf(selection);
        }
        catch(Exception e){
            logger.warn("User did not enter a number selection when choosing an action. Re-prompting user");
            return false;
        }
        Boolean validation = true;
        for(int x=1;x<=7;x++){
            if (selectionInt == x) {
                validation = false;
            }
        }
        return validation;
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
            case 7: System.out.println("Ending Program. Goodbye.");
                System.exit(0);
                return false;
        }

        return true;
    }

    public void addBill(Scanner scanner) {
        //have the user pick a csv file or manually enter the data
        Integer fromSwitch = askUserWhereFrom(scanner);
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
                    addBillSqlOperator.pushManualDataToDatabase(manualBillInstance);
                }
                else{
                    manualEntryStop = true;
                    logger.debug("user entered stop123 so the data entry phase ended.");
                    System.out.println("Ending Bill data entry.");
                }
            }
        }
    }

    public Integer askUserWhereFrom(Scanner scanner) {
        String selection = "999";
        while (selection == "999"){
            System.out.println("Would you like to add from a csv file or manually add instances? 0 or 1?");
            selection = scanner.nextLine();
            if (Integer.getInteger(selection) == 0 | Integer.getInteger(selection) == 1){

            }
        }
        return Integer.getInteger(selection);
    }

    public String promptUserForCsvName(Scanner scanner) {
        boolean tryAgain = true;
        String csvNameString = null;
        while (tryAgain) {
            //ask the user for the csv name
            System.out.println("Enter the name of the csv file here:");
            csvNameString = scanner.nextLine();
            //if invalid selection then return true to go again.
            tryAgain = validateCsvIsPresent(csvNameString);
        }
        return csvNameString;
    }

    private boolean validateCsvIsPresent(String csvNameString) {
        File f = new File(csvNameString + ".csv");

        // Checking if the specified file exists or not
        if (f.exists()){
            // Show if the file exists
            logger.debug("Csv file entered by the user Exists");
            return true;}
        else{
            // Show if the file does not exist
            logger.debug("Csv file entered by the user does not Exist");
            return false;
        }
    }

    public List<String> promptUserForManualBillInstance(Scanner scanner) {
        List<String> returner = null;
        System.out.println("Please enter the following values separated with commas.Do not enter incorrect datatypes:Due Date, Bill Name, Amount Due, Cash or Card Payment, Planned Payment Account Name... or enter stop123 to stop.");
        String billValuesRaw = scanner.nextLine();
        List<String> billValuesListOfString = Arrays.asList(billValuesRaw.split(","));
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
