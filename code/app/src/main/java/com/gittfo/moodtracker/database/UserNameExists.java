package com.gittfo.moodtracker.database;

public class UserNameExists extends Exception {
    UserNameExists(String message){
        super(message);
    }
}
