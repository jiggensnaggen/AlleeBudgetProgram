package Operators;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.Scanner;

public class Operator {
    Logger logger;

    public Operator(Logger loggerFromAbove){
        logger = loggerFromAbove;
    }
    public Integer askUserWhereFrom(Scanner scanner) {
        String selection = "999";
        while (selection == "999"){
            System.out.println("Would you like to add from a csv file or manually add instances? 0 or 1?");
            selection = scanner.nextLine();
            if (Integer.getInteger(selection) == 0 | Integer.getInteger(selection) == 1){
                return Integer.getInteger(selection);
            }
            else{

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
}
