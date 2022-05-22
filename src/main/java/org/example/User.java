package org.example;

import Operators.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {

    private static final Logger logger = (Logger) LogManager.getLogger(User.class);


    public User() {
        
    }

    public void runMe(Logger logger, String username) {
        Integer selection = 99999;
        Scanner userScanner = new Scanner(System.in);
        while(selection != 7) {
            selection = promptUserForSelection(userScanner);
            makeSelection(selection, userScanner, username, logger);

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

    public Boolean makeSelection(Integer selection, Scanner scanner, String username, Logger logger){
        //switch case statement here to decode the selection into the correct function
        switch (selection) {
            case 1: BillOperator MyBillOperator = new BillOperator(logger, username);
                    MyBillOperator.addBill(scanner,username);
                break;
            case 2: PaymentAccountOperator myPaymentAccountOperator = new PaymentAccountOperator(logger, username);
                    myPaymentAccountOperator.addPaymentAccount(scanner, username);
                break;
            case 3: BudgetOperator myBudgetOperator = new BudgetOperator(logger, username);
                    myBudgetOperator.addBudget(scanner, username);
                break;
            case 4: TransactionOperator MyTransactionOperator = new TransactionOperator(logger, username);
                    MyTransactionOperator.addTransaction(scanner, username);
                break;
            case 5: GenerateWhatIfOperator myGenerateWhatIfOperator = new GenerateWhatIfOperator(logger, username);
                    myGenerateWhatIfOperator.generateWhatIf(scanner, username);
                break;
            case 6: GenerateReportOperator myGenerateReportOperator = new GenerateReportOperator(logger, username);
                    myGenerateReportOperator.generateReport(scanner, username);
                break;
            case 7: System.out.println("Ending Program. Goodbye.");
                System.exit(0);
                return false;
        }

        return true;
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
