package org.example;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        final Logger logger = Logger.getLogger(Main.class);
        Initializer mainInitializer = new Initializer(logger);
        mainInitializer.runMe();
//        User mainUser = new User();
//        mainUser.runMe();
    }
}