package org.example.KursachP.services;

import org.example.KursachP.models.Employee;
import org.example.KursachP.models.Product;
import org.example.KursachP.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findOne(int id){
        Optional<Employee> foundEmployee = employeeRepository.findById(id);
        return  foundEmployee.orElse(null);
    }

    public List<Employee> findByEmployeeName (String employeeName){
        return employeeRepository.findByEmployeeName(employeeName);
    }

    @Transactional
    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    @Transactional
    public  void update(int id, Employee updateEmployee){
        updateEmployee.setId(id);
        employeeRepository.save(updateEmployee);
    }

    @Transactional
    public void delete(int id){
        employeeRepository.deleteById(id);
    }
}
