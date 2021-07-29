package com.example.usermanage.controller;

import com.example.usermanage.exception.ResourceNotFounrdException;
import com.example.usermanage.model.Employee;
import com.example.usermanage.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFounrdException("Employee not found"));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable  Long id, @RequestBody Employee employeeDetail) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFounrdException("Employee not found"));

        employee.setFirstName(employeeDetail.getFirstName());
        employee.setLastName(employeeDetail.getLastName());
        employee.setEmailId(employeeDetail.getEmailId());

        Employee updateEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFounrdException("Employee not exist with id :" + id));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response) ;
    }

    @GetMapping("/cc")
    public String cc(){
        return "cc";
    }

}
