package com.sparta.csvproject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDriver implements EmployeeDAO , Runnable{
    private List<Employee> pls = new ArrayList<>();

    public List<Employee> getPls() {
        return pls;
    }

    public void setPls(List<Employee> pls) {
        this.pls = pls;
    }

    public void DatabaseHandler (){


        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csvproject", "root", "Sparta")){
            connection.setAutoCommit(false);
            PreparedStatement dropDBTable = connection.prepareStatement("DROP TABLE IF EXISTS Users");
            PreparedStatement createDBTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Users (ID int(255) UNIQUE, Prefix varchar(255), FirstName varchar(255)," +
                    " MiddleInitial char(1), LastName varchar(255), Gender char(1), " +
                    "Email varchar(255), DateOfBirth Date, DateOfJoin Date, Salary int(255))");
            dropDBTable.execute();
            System.out.println("drop done");
            createDBTable.execute();
            System.out.println("create done");
            createDBTable.close();
            dropDBTable.close();
            connection.commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Runnable InsertIntoDatabase(List<Employee> employeeList, int threads){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csvproject", "root", "Sparta")){
            connection.setAutoCommit(false);
            PreparedStatement insertIntoDB = connection.prepareStatement("INSERT IGNORE INTO Users(ID, Prefix, FirstName, MiddleInitial, LastName, Gender, Email, DateOfBirth, DateOfJoin, Salary)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            //should populate db with existing values
            for (int i = 0; i < employeeList.size(); i++) {
                insertIntoDB.setInt(1, employeeList.get(i).getEmployeeID());
                insertIntoDB.setString(2, employeeList.get(i).getEmployeeNamePrefix());
                insertIntoDB.setString(3, employeeList.get(i).getEmployeeFirstname());
                insertIntoDB.setString(4, String.valueOf(employeeList.get(i).getEmployeeMiddleInitial()));
                insertIntoDB.setString(5, employeeList.get(i).getEmployeeLastName());
                insertIntoDB.setString(6, String.valueOf(employeeList.get(i).getEmployeeGender()));
                insertIntoDB.setString(7, employeeList.get(i).getEmployeeEmail());
                insertIntoDB.setDate(8, employeeList.get(i).getEmployeeDoB());
                insertIntoDB.setDate(9, employeeList.get(i).getEmployeeDoJ());
                insertIntoDB.setInt(10, employeeList.get(i).getEmployeeSalary());
                insertIntoDB.addBatch();
            }
            int[] results = insertIntoDB.executeBatch();
            insertIntoDB.close();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
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


    @Override
    public void run() {

    }
}
