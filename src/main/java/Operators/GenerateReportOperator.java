package Operators;

import org.apache.logging.log4j.core.*;

import java.util.Scanner;

public class GenerateReportOperator extends Operator{
    public GenerateReportOperator(Logger logger, String username) {
        super(logger, username);
    }

    @Override
    public Boolean validateManualInput(String valuesRaw) {
        return null;
    }

    public  void generateReport(Scanner scanner, String username) {
    }
}
