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
        List<Employee> EmployeeSubList;
        List<Employee> subList = null;
        System.out.println("Here 2");
        ArrayList<Employee> EmployeeSub = new ArrayList<>();
        ArrayList<Employee> uniqueEmployeeRecords = (ArrayList<Employee>)employeeRecords.stream().collect((Collectors.toList()));
        /*ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);
        System.out.println("Here 3");
        executorService.execute(dbDriver.InsertIntoDatabase(uniqueEmployeeRecords, numOfThreads));
        executorService.shutdown();*/
         ArrayList<Thread> ThreadList = new ArrayList<>();
        int pointer;
        int numSplits;
        for (int i = 0; i < numOfThreads; i++) {
            pointer = i*(uniqueEmployeeRecords.size()/numOfThreads);
            numSplits = (i+1)*(uniqueEmployeeRecords.size()/numOfThreads);
            EmployeeSubList = uniqueEmployeeRecords.subList(pointer, numSplits);
            EmployeeSub.addAll(EmployeeSubList);

            ThreadList.add(new Thread(dbDriver.InsertIntoDatabase(EmployeeSub, numOfThreads)));
         }
        for (int i = 0; i < ThreadList.size(); i++) {
            ThreadList.get(i).start();
        }
        for (int i = 0; i < ThreadList.size(); i++) {
            try {

                ThreadList.get(i).join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
   /* public static void splitEmpArrThreaded(ArrayList<Employee> empArrList, int threads){
        ArrayList<DatabaseDriver> insStateArrList = new ArrayList<>();
        ArrayList<Thread> threadArrList = new ArrayList<>();
        for(int i =0;i<threads;i++){
            insStateArrList.add(new InsertStatements(empArrList,(i)*(empArrList.size()/threads), (i+1)*(empArrList.size()/threads),i));
            threadArrList.add(new Thread(insStateArrList.get(i)));
        }
        int tArrSize = threadArrList.size();
        for(int i=0; i<tArrSize;i++){
            threadArrList.get(i).start();
        }
        for(int i=0;i<tArrSize;i++){
            try {
                threadArrList.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
