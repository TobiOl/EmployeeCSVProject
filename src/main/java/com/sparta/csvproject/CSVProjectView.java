package com.sparta.csvproject;

import java.util.Scanner;

public class CSVProjectView {
    public static void findUserID(){
        DatabaseDriver dbHandler = new DatabaseDriver();
        CSVProjectController.CSVRead();
        System.out.println("Enter Record ID to find Record");
        Scanner input = new Scanner(System.in);
        int userIdNumber = input.nextInt();
        Employee result = dbHandler.getEmployee(userIdNumber);
        System.out.println(result.toString());
    }

    public void PrintResults(int duplicatesCount, int uniqueCount, int emptyFieldCount, int cleanFieldCount){
        System.out.println("Duplicates: " + duplicatesCount);
        System.out.println("Uniques: " + uniqueCount);
        System.out.println("Clean: " + cleanFieldCount);
        System.out.println("Missing fields: " + emptyFieldCount);
    }
}
