package com.sparta.csvproject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CSVProjectMain {
    public static void main(String[] args) throws ParseException {
        String line = null;
        ArrayList<Employee> employeeStore = new ArrayList<Employee>();
        java.util.Date DoB, DoJ;
        java.sql.Date sqlDoB, sqlDoJ;
        String[] values;

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

            }

        } catch (IOException | ParseException io) {
            io.printStackTrace();
        }

    }
}
