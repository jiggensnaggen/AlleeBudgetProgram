package Operators;
import org.apache.logging.log4j.core.*;

import java.util.Scanner;

public class TransactionOperator extends Operator{
    public TransactionOperator(Logger logger, String username) {
        super(logger, username);
    }

    @Override
    public Boolean validateManualInput(String valuesRaw) {
        return null;
    }

    public static void addTransaction(Scanner scanner, String username) {
    }
}
