package com.sparta.csvproject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DatabaseDriver implements EmployeeDAO {
    public static void DatabaseHandler (ArrayList<Employee> employeeRecords){
        Employee employee = new Employee();
        long startTime = System.currentTimeMillis();
        long endTime;
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
            for (int i = 0; i < employeeRecords.size(); i++) {
                insertIntoDB.setInt(1, employeeRecords.get(i).getEmployeeID());
                insertIntoDB.setString(2, employeeRecords.get(i).getEmployeeNamePrefix());
                insertIntoDB.setString(3, employeeRecords.get(i).getEmployeeFirstname());
                insertIntoDB.setString(4, String.valueOf(employeeRecords.get(i).getEmployeeMiddleInitial()));
                insertIntoDB.setString(5, employeeRecords.get(i).getEmployeeLastName());
                insertIntoDB.setString(6, String.valueOf(employeeRecords.get(i).getEmployeeGender()));
                insertIntoDB.setString(7, employeeRecords.get(i).getEmployeeEmail());
                insertIntoDB.setDate(8, employeeRecords.get(i).getEmployeeDoB());
                insertIntoDB.setDate(9, employeeRecords.get(i).getEmployeeDoJ());
                insertIntoDB.setInt(10, employeeRecords.get(i).getEmployeeSalary());
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
    public List<Employee> getAllEmployees() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csvproject", "root", "Sparta")){


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Employee> findByID() {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csvproject", "root", "Sparta")){

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertEmployee(Employee employee) {

    }

    @Override
    public Employee getEmployee(int ID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csvproject", "root", "Sparta")){

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
