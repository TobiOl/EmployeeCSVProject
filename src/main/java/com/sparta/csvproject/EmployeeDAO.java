package com.sparta.csvproject;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    List<Employee> findByID();
    void insertEmployee(Employee employee);
    Employee getEmployee(int ID);
}
