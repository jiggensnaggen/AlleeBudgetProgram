package org.example;

import java.util.Scanner;

public class MockClass {
    private Scanner mockScanner;

    public MockClass(){
        mockScanner = new Scanner(System.in);
    }

    public Scanner getScannerInstance(){
        return mockScanner;
    }
}
