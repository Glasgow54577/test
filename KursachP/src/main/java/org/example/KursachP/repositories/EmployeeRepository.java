package org.example.KursachP.repositories;

import org.example.KursachP.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByEmployeeName(String employeeName);

    Optional<Employee> findByUsername(String username);
}
