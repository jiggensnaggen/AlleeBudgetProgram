package Operators;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class Operator {
    Logger logger;
    String username;

    public Operator(Logger loggerFromAbove, String usernameFromAbove){
        logger = loggerFromAbove;
        username = usernameFromAbove;
    }
    public Integer askUserWhereFrom(Scanner scanner) {
        String selection = "999";
        while (selection == "999"){
            System.out.println("Would you like to add from a csv file or manually add instances? 0 or 1?");
            selection = scanner.nextLine();
            if (Integer.parseInt(selection) == 0 | Integer.parseInt(selection) == 1){
                return Integer.parseInt(selection);
            }
            else{

            }
        }
        return Integer.parseInt(selection);
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
    public List<String> promptUserForManualInstance(Scanner scanner, String itemsRequiredPrint) {
        System.out.println("Please enter the following values separated with commas.Do not enter incorrect data types:");
        System.out.println(itemsRequiredPrint);
        String valuesRaw = scanner.nextLine();
        valuesRaw = valuesRaw.replace(", ",",");
        Boolean checked = false;
        while(checked == false){
            checked = validateManualInput(valuesRaw);
            if (checked == false){
                promptUserForManualInstance(scanner, itemsRequiredPrint);
            }
        }
        List<String> ValuesListOfString = Arrays.asList(valuesRaw.split(","));
        return ValuesListOfString;
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

    public abstract Boolean validateManualInput(String valuesRaw);
}
