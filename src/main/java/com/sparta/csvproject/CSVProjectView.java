package com.sparta.csvproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CSVProjectView {
    public static void findUserID(){
       DatabaseDriver dbHandler = new DatabaseDriver();

        CSVProjectController CPC = new CSVProjectController();
        ArrayList<Employee> employeeStore;
        Employee foundEmployee = new Employee();
        employeeStore = CPC.CSVRead();

        //Values from the models are stored here for use elsewhere
        HashSet<Employee> uniqueValues = CPC.DuplicatesSearch(employeeStore);

        ArrayList<Employee> NullRecords = CPC.EmptyFieldSearch(employeeStore);

        int duplicatesCount = CPC.DuplicatesCount(uniqueValues, employeeStore);
        int uniqueCount = CPC.UniqueCount(uniqueValues);

        int emptyFieldCount = CPC.EmptyFieldCount(NullRecords);
        int cleanFieldCount = CPC.CleanFieldCount(NullRecords, employeeStore);

        long startTime = System.currentTimeMillis();
        long endTime;
        dbHandler.DatabaseHandler();
        ThreadSplitter.SplitRecords(uniqueValues, 20);
        endTime = System.currentTimeMillis();
        long result = endTime-startTime;
        System.out.println("Time taken: " + result + "(ms)");


        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to find a specific record? (y/n)");
        String findChoice = input.next();
        if (findChoice.equals("y")){
            System.out.println("Enter Record ID to find Record");
            int userIdNumber = input.nextInt();
            foundEmployee = dbHandler.getEmployee(userIdNumber);

        }
        PrintResults(duplicatesCount, uniqueCount, emptyFieldCount, cleanFieldCount, foundEmployee);

    }

    public static void PrintResults(int duplicatesCount, int uniqueCount, int emptyFieldCount, int cleanFieldCount, Employee foundEmployee){
        System.out.println("Duplicates: " + duplicatesCount);
        System.out.println("Uniques: " + uniqueCount);
        System.out.println("Clean: " + cleanFieldCount);
        System.out.println("Missing fields: " + emptyFieldCount);
        if(foundEmployee != null){
            System.out.println(foundEmployee);
        }
    }
}
