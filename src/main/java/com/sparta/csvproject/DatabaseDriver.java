package com.sparta.csvproject;
import java.sql.*;
public class DatabaseDriver {
    public static void DatabaseHandler(){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csvproject", "root", "Sparta")){
            PreparedStatement dropDBTable = connection.prepareStatement("DROP TABLE IF EXISTS Users");
            PreparedStatement createDBTable = connection.prepareStatement("CREATE TABLE Users (ID int(255), Prefix varchar(255), FirstName varchar(255), MiddleInitial char(1), LastName varchar(255), Gender char(1), Email varchar(255), DateOfBirth Date, DateOfJoin Date, Salary int(255))");
            dropDBTable.execute();
            createDBTable.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
