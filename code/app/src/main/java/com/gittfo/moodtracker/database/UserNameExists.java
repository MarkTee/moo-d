package com.gittfo.moodtracker.database;

public class UserNameExists extends Exception {

    /**
     * Constructor for custom exception which is thrown when
     * the database already has the given username. 
     * The username is included in a custom error message.
     * @param username
     */
    UserNameExists(String username){
        super("Username: " + username + " Already exists in the database");
    }
}
