package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.*;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        final Logger logger = (Logger) LogManager.getLogger();
        Initializer mainInitializer = new Initializer(logger);
        //finds usernames in the database and prompts the user to pick one
        String username = mainInitializer.runMe();
        User mainUser = new User();
        // allows the user to pick from a list of actions
        mainUser.runMe(logger,username);
    }
}