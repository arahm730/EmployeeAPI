package com.arahman.EmployeeManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arahman.EmployeeManagementSystem.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	public Optional<Employee> findByFirstNameAndLastNameAndState(String firstName, String lastName, String state);
	public Optional<List<Employee>> findByFirstNameAndLastName(String firstName, String lastName);
}
