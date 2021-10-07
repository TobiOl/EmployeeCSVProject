package com.sparta.csvproject;

public class CSVProjectView {
    public void PrintResults(int duplicatesCount, int uniqueCount, int emptyFieldCount, int cleanFieldCount){
        System.out.println("Duplicates: " + duplicatesCount);
        System.out.println("Uniques: " + uniqueCount);
        System.out.println("Clean: " + cleanFieldCount);
        System.out.println("Missing fields: " + emptyFieldCount);

    }
}
