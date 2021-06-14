package com.example.employeemanager.service;

import com.example.employeemanager.exception.UserNotFoundExeption;
import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repo.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final IEmployeeRepo employeeRepo;

    //use this Repository to do all crud-operations
    @Autowired
    public EmployeeService(IEmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return  employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee findEmployeeById(Long id){
        return employeeRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundExeption("User by id " + id + "was not found"));
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }
}
