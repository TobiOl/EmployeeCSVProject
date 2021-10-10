package com.sparta.csvproject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class CSVProjectController{
    private static Logger logger = Logger.getLogger("CSV Project Logger");
    public ArrayList<Employee> CSVRead(){
        PropertyConfigurator.configure("log4j.properties");
        String line = null;
        ArrayList<Employee> employeeStore = new ArrayList<Employee>();
        java.util.Date DoB, DoJ;
        java.sql.Date sqlDoB, sqlDoJ;
        String[] values;

        //try-with-resources. closes resources when finished trying
        try (BufferedReader in = new BufferedReader(new FileReader("EmployeeRecordsLarge.csv"))){
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
                logger.info("New record stored in employeeStore arraylist");
            }

        } catch (IOException | ParseException io) {
            io.printStackTrace();
        }

        return employeeStore;
    }

    public ArrayList<Employee> EmptyFieldSearch(ArrayList<Employee> employeeArrayListUnedited){
        PropertyConfigurator.configure("log4j.properties");
        ArrayList<Employee> NullRecords = new ArrayList<>();
        int i = 0;
        for (Object o: employeeArrayListUnedited){
            if (employeeArrayListUnedited.get(i).CheckIfValid()==false){
                NullRecords.add(employeeArrayListUnedited.get(i));
                logger.warn("Record with empty field found!");
            }
            i++;
        }
        return NullRecords;
    }

    public int EmptyFieldCount(ArrayList<Employee> NullRecords){
        return NullRecords.size();
    }

    public int CleanFieldCount(ArrayList<Employee> NullRecords, ArrayList<Employee> employeeArrayListUnedited){
        return employeeArrayListUnedited.size() - NullRecords.size();
    }

    public HashSet DuplicatesSearch(ArrayList<Employee> employeeArrayListUnedited){
        PropertyConfigurator.configure("log4j.properties");
        int i = 0;
        HashSet<Employee> employeeHashSet = new HashSet<Employee>();
        for (Object o: employeeArrayListUnedited){
            employeeHashSet.add(employeeArrayListUnedited.get(i));
            logger.info("Finding duplicates");
            i++;
        }
        return employeeHashSet;
    }
    public int UniqueCount(HashSet<Employee> uniqueValues){
        return uniqueValues.size();
    }
    public int DuplicatesCount(HashSet<Employee> uniqueValues, ArrayList<Employee> employeeArrayListUnedited){
        int uniqueEmployeeCount = uniqueValues.size();
        int employeeListUneditedCount = employeeArrayListUnedited.size();
        int result = employeeListUneditedCount-uniqueEmployeeCount;
        return result;
    }


}

