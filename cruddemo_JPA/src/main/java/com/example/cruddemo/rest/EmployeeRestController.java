package com.example.cruddemo.rest;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {

        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee ==null){
            throw new RuntimeException("Employee not found");
        }

            return theEmployee;
    }

    //ADD MAPPING FOR POST ADD EMPLOYEES
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
    //force to add not update
        theEmployee.setId(0); //in case they sent id in JSON
        employeeService.save(theEmployee);

        return theEmployee;

    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        employeeService.save(theEmployee);
         return  theEmployee;
    }

    @DeleteMapping(value = "/employees/{id}")
        public String deleteEmployee(@PathVariable int id){
        employeeService.deleteById(id);
        return "Deleted Employee Successfully with id -" +id;
    }

}
