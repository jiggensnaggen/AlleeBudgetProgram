package org.example;

import java.util.List;
import java.util.Scanner;

public class User {
    public User() {
        runMe();
    }

    private void runMe() {
        Boolean runAgain = true;
        Scanner userScanner = new Scanner(System.in);
        while(runAgain) {
            runAgain = promptUserForSelection(userScanner);
        }
    }

    private Boolean promptUserForSelection(Scanner scanner) {
        //give the user a list of items to pick from. each item gets a corresponding number
        System.out.println("");
        String selection = scanner.nextLine();
        //switch case statement here to decode the selection into the correct function
        switch (selection) {
            case "1": addBill(scanner);
                break;
            case "2": addPaymentAccount(scanner);
                break;
            case "3": addBudget(scanner);
                break;
            case "4": addTransaction(scanner);
                break;
            case "5": generateWhatIf(scanner);
                break;
            case "6": generateReport(scanner);
                break;
            case "7": return false;
        }

        return true;
    }

    private void addBill(Scanner scanner) {
        //have the user pick a csv file or manually enter the data
        Integer fromSwitch = askUserWhereFrom(scanner);
        SqlOperator addBillSqlOperator = new SqlOperator();
        if (fromSwitch == 0){
            String csvName = promptUserForCsvName();
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

    private Integer askUserWhereFrom(Scanner scanner) {
        return 0;
    }

    private String promptUserForCsvName() {
        return "";
    }

    private List<String> promptUserForManualBillInstance() {
        List<String> returner = null;
        return returner;
    }

    private void addPaymentAccount(Scanner scanner) {
    }

    private void addBudget(Scanner scanner) {
    }

    private void addTransaction(Scanner scanner) {
    }

    private void generateWhatIf(Scanner scanner) {
    }

    private void generateReport(Scanner scanner) {
    }
}
