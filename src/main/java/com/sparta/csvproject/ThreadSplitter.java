package com.sparta.csvproject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ThreadSplitter {

    public static void SplitRecords(HashSet<Employee> employeeRecords, int numOfThreads){
        DatabaseDriver dbDriver = new DatabaseDriver();
        //Copies hashset to arraylist
        ArrayList<Employee> uniqueEmployeeRecords = (ArrayList<Employee>)employeeRecords.stream().collect((Collectors.toList()));
        //starts multithreaded tasks
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);
        executorService.execute(() -> dbDriver.InsertIntoDatabase(uniqueEmployeeRecords,numOfThreads));
        executorService.shutdown();

    }
}
