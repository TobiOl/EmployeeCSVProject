package com.sparta.csvproject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseDriver implements EmployeeDAO {
    public static void DatabaseHandler (HashSet<Employee> employeeRecords){
        long startTime = System.currentTimeMillis();
        long endTime;
        //Copies hashset to arraylist
        ArrayList<Employee> uniqueEmployeeRecords = (ArrayList<Employee>)employeeRecords.stream().collect((Collectors.toList()));
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csvproject", "root", "Sparta")){
            connection.setAutoCommit(false);
            PreparedStatement dropDBTable = connection.prepareStatement("DROP TABLE IF EXISTS Users");
            PreparedStatement createDBTable = connection.prepareStatement("CREATE TABLE Users (ID int(255), Prefix varchar(255), FirstName varchar(255)," +
                    " MiddleInitial char(1), LastName varchar(255), Gender char(1), " +
                    "Email varchar(255), DateOfBirth Date, DateOfJoin Date, Salary int(255))");
            PreparedStatement insertIntoDB = connection.prepareStatement("INSERT INTO Users(ID, Prefix, FirstName, MiddleInitial, LastName, Gender, Email, DateOfBirth, DateOfJoin, Salary)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            dropDBTable.execute();
            createDBTable.execute();
            //should populate db with existing values
            for (int i = 0; i < uniqueEmployeeRecords.size(); i++) {
                insertIntoDB.setInt(1, uniqueEmployeeRecords.get(i).getEmployeeID());
                insertIntoDB.setString(2, uniqueEmployeeRecords.get(i).getEmployeeNamePrefix());
                insertIntoDB.setString(3, uniqueEmployeeRecords.get(i).getEmployeeFirstname());
                insertIntoDB.setString(4, String.valueOf(uniqueEmployeeRecords.get(i).getEmployeeMiddleInitial()));
                insertIntoDB.setString(5, uniqueEmployeeRecords.get(i).getEmployeeLastName());
                insertIntoDB.setString(6, String.valueOf(uniqueEmployeeRecords.get(i).getEmployeeGender()));
                insertIntoDB.setString(7, uniqueEmployeeRecords.get(i).getEmployeeEmail());
                insertIntoDB.setDate(8, uniqueEmployeeRecords.get(i).getEmployeeDoB());
                insertIntoDB.setDate(9, uniqueEmployeeRecords.get(i).getEmployeeDoJ());
                insertIntoDB.setInt(10, uniqueEmployeeRecords.get(i).getEmployeeSalary());
                insertIntoDB.execute();
            }
            insertIntoDB.close();
            createDBTable.close();
            dropDBTable.close();
            connection.commit();
            endTime = System.currentTimeMillis();
            long result = endTime-startTime;
            System.out.println("Time taken: " + result + "(ms)");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Employee getEmployee(int ID) {
        Employee recordFound = new Employee();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csvproject", "root", "Sparta")){
            //will go into existing DB and find specific record based off of ID value passed in from CSVProjectView's Start class
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE ID = " + ID);
            while (rs.next()){
                recordFound = new Employee(rs.getInt("ID"), rs.getString("Prefix"), rs.getString("FirstName"), rs.getString("MiddleInitial").charAt(0),
                        rs.getString("LastName"), rs.getString("Gender").charAt(0), rs.getString("Email"), rs.getDate("DateOfBirth"),
                        rs.getDate("DateOfJoin"), rs.getInt("Salary"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recordFound;
    }
}
