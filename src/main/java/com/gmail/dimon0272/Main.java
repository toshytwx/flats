package com.gmail.dimon0272;

/**
 * Created by User on 08.02.2017.
 */
public class Main {
    public static void main(String[] args){
        DBManipulations dbManipulations = new DBManipulations();
        dbManipulations.createDBconnection();
        dbManipulations.viewInfo();
    }
}
