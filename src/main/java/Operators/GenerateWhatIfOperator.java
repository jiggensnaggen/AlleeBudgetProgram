package Operators;

import org.apache.logging.log4j.core.*;

import java.util.Scanner;

public class GenerateWhatIfOperator extends Operator{
    public GenerateWhatIfOperator(Logger logger, String username) {
        super(logger, username);
    }

    @Override
    public Boolean validateManualInput(String valuesRaw) {
        return null;
    }

    public  void generateWhatIf(Scanner scanner, String username) {
    }
}
