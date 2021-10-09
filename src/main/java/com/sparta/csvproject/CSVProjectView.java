package com.sparta.csvproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CSVProjectView {
    public static void findUserID(){
        DatabaseDriver dbHandler = new DatabaseDriver();
        CSVProjectController CPC = new CSVProjectController();
        ArrayList<Employee> employeeStore = CPC.CSVRead();

        //Values from the models are stored here for use elsewhere
        HashSet<Employee> uniqueValues = CPC.DuplicatesSearch(employeeStore);
        ArrayList<Employee> NullRecords = CPC.EmptyFieldSearch(employeeStore);
        int duplicatesCount = CPC.DuplicatesCount(uniqueValues, employeeStore);
        int uniqueCount = CPC.UniqueCount(uniqueValues);
        int emptyFieldCount = CPC.EmptyFieldCount(NullRecords);
        int cleanFieldCount = CPC.CleanFieldCount(NullRecords, employeeStore);
        DatabaseDriver.DatabaseHandler(uniqueValues);

        PrintResults(duplicatesCount, uniqueCount, emptyFieldCount, cleanFieldCount);

        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to find a specific record? (y/n)");
        String choice = input.next();
        if (choice.equals("y")){
            System.out.println("Enter Record ID to find Record");
            int userIdNumber = input.nextInt();
            Employee result = dbHandler.getEmployee(userIdNumber);
            System.out.println(result.toString());
        }

    }

    public static void PrintResults(int duplicatesCount, int uniqueCount, int emptyFieldCount, int cleanFieldCount){
        System.out.println("Duplicates: " + duplicatesCount);
        System.out.println("Uniques: " + uniqueCount);
        System.out.println("Clean: " + cleanFieldCount);
        System.out.println("Missing fields: " + emptyFieldCount);
    }
}
