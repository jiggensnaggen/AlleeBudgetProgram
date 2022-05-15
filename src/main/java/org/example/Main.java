package org.example;

import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class);
        Initializer mainInitializer = new Initializer(logger);
        mainInitializer.runMe();
        User mainUser = new User();
        mainUser.runMe();
    }
}