package com.sparta.csvproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class CSVProjectView {
    public static void findUserID(){
       DatabaseDriver dbHandler = new DatabaseDriver();

        CSVProjectController CPC = new CSVProjectController();
        ArrayList<Employee> employeeStore = new ArrayList<>();

        employeeStore = CPC.CSVRead();
        System.out.println("Here 0");
        //Values from the models are stored here for use elsewhere
        HashSet<Employee> uniqueValues = CPC.DuplicatesSearch(employeeStore);
        System.out.println("Here Arrays");
        ArrayList<Employee> NullRecords = CPC.EmptyFieldSearch(employeeStore);
        System.out.println("Here Arrays");
        int duplicatesCount = CPC.DuplicatesCount(uniqueValues, employeeStore);
        System.out.println("Here Arrays");
        int uniqueCount = CPC.UniqueCount(uniqueValues);
        System.out.println("Here Arrays");
        int emptyFieldCount = CPC.EmptyFieldCount(NullRecords);
        System.out.println("Here Arrays");
        int cleanFieldCount = CPC.CleanFieldCount(NullRecords, employeeStore);
        System.out.println("Here Arrays");
    /*    DatabaseDriver dbHandlerThread1 = new DatabaseDriver();
        DatabaseDriver dbHandlerThread2 = new DatabaseDriver();
        DatabaseDriver dbHandlerThread3 = new DatabaseDriver();
        DatabaseDriver dbHandlerThread4 = new DatabaseDriver();
        DatabaseDriver dbHandlerThread5 = new DatabaseDriver();
        DatabaseDriver dbHandlerThread6 = new DatabaseDriver();
*/
        long startTime = System.currentTimeMillis();
        long endTime;
        dbHandler.DatabaseHandler();
        System.out.println("Here 1");
        ThreadSplitter.SplitRecords(uniqueValues, 50);
        endTime = System.currentTimeMillis();
        long result = endTime-startTime;
        System.out.println("Time taken: " + result + "(ms)");
        PrintResults(duplicatesCount, uniqueCount, emptyFieldCount, cleanFieldCount);

        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to find a specific record? (y/n)");
        String findChoice = input.next();
        if (findChoice.equals("y")){
            System.out.println("Enter Record ID to find Record");
            int userIdNumber = input.nextInt();
            Employee result2 = dbHandler.getEmployee(userIdNumber);
            System.out.println(result2.toString());
        }

    }

    public static void PrintResults(int duplicatesCount, int uniqueCount, int emptyFieldCount, int cleanFieldCount){
        System.out.println("Duplicates: " + duplicatesCount);
        System.out.println("Uniques: " + uniqueCount);
        System.out.println("Clean: " + cleanFieldCount);
        System.out.println("Missing fields: " + emptyFieldCount);
    }
}
