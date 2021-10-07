package com.sparta.csvproject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;

public class CSVProjectController {
    public static void CSVRead(){
        String line = null;
        ArrayList<Employee> employeeStore = new ArrayList<Employee>();
        java.util.Date DoB, DoJ;
        java.sql.Date sqlDoB, sqlDoJ;
        String[] values;
        CSVProjectView view = new CSVProjectView();

        //try-with-resources. closes resources when finished trying
        try (BufferedReader in = new BufferedReader(new FileReader("EmployeeRecords.csv"));
             BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"))){
            String headerLine = in.readLine();
            while ((line = in.readLine()) != null){
                values = line.split(",");

                DoB = new SimpleDateFormat("MM/dd/yyyy").parse(values[7]);
                sqlDoB = new java.sql.Date(DoB.getTime());
                DoJ = new SimpleDateFormat("MM/dd/yyyy").parse(values[8]);
                sqlDoJ = new java.sql.Date(DoJ.getTime());
                Employee employeeObj = new Employee(Integer.parseInt(values[0]), values[1], values[2],values[3].charAt(0), values[4], values[5].charAt(0)
                        ,values[6] ,sqlDoB ,sqlDoJ ,Integer.parseInt(values[9]));
                employeeStore.add(employeeObj);

            }

        } catch (IOException | ParseException io) {
            io.printStackTrace();
        }

        HashSet<Employee> uniqueValues = CSVProjectController.DuplicatesSearch(employeeStore);
        ArrayList<Employee> NullRecords = EmptyFieldSearch(employeeStore);
        int duplicatesCount = DuplicatesCount(uniqueValues, employeeStore);
        int uniqueCount = UniqueCount(uniqueValues);
        int emptyFieldCount = EmptyFieldCount(NullRecords);
        int cleanFieldCount = CleanFieldCount(NullRecords, employeeStore);
        view.PrintResults(duplicatesCount, uniqueCount, emptyFieldCount, cleanFieldCount);
    }

    public static ArrayList<Employee> EmptyFieldSearch(ArrayList<Employee> employeeArrayListUnedited){
        ArrayList<Employee> NullRecords = new ArrayList<>();
        int i = 0;
        for (Object o: employeeArrayListUnedited){
            if (employeeArrayListUnedited.get(i).CheckIfValid()==false){
                NullRecords.add(employeeArrayListUnedited.get(i));
            }
            i++;
        }
        return NullRecords;
    }

    public static int EmptyFieldCount(ArrayList<Employee> NullRecords){
        return NullRecords.size();
    }

    public static int CleanFieldCount(ArrayList<Employee> NullRecords, ArrayList<Employee> employeeArrayListUnedited){
        return employeeArrayListUnedited.size() - NullRecords.size();
    }

    public static HashSet DuplicatesSearch(ArrayList<Employee> employeeArrayListUnedited){
        int i = 0;
        int i2 = employeeArrayListUnedited.size();
        int result;
        HashSet<Employee> employeeHashSet = new HashSet<Employee>();
        for (Object o: employeeArrayListUnedited){
            employeeHashSet.add(employeeArrayListUnedited.get(i));
            i++;
        }
        return employeeHashSet;
    }
    public static int UniqueCount(HashSet<Employee> uniqueValues){
        return uniqueValues.size();
    }
    public static int DuplicatesCount(HashSet<Employee> uniqueValues, ArrayList<Employee> employeeArrayListUnedited){
        int count = uniqueValues.size();
        int i2 = employeeArrayListUnedited.size();
        int result = i2-count;
        return result;
    }
}

