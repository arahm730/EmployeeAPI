package com.arahman.EmployeeManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arahman.EmployeeManagementSystem.exceptions.EmployeeAlreadyExistsException;
import com.arahman.EmployeeManagementSystem.exceptions.EmployeeNotFoundException;
import com.arahman.EmployeeManagementSystem.model.Employee;
import com.arahman.EmployeeManagementSystem.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee createEmployee(Employee employee) {
		Optional<Employee> duplicateEmployees = employeeRepository.findByFirstNameAndLastNameAndState(employee.getFirstName(), employee.getLastName(), employee.getState());
		if (!duplicateEmployees.isEmpty()) {
			throw new EmployeeAlreadyExistsException("An employee with the given details already exists");
		}
		return employeeRepository.save(employee);
	}

	public Employee getEmployee(long employeeId) {
		Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		if (employeeOpt.isEmpty()) {
			throw new EmployeeNotFoundException("Employee with id " + employeeId + " does not exist");
		}
		return employeeOpt.get();
	}
	public List<Employee> getEmployeesByFullName(String firstName, String lastName) {
		Optional<List<Employee>> employeeOpt = employeeRepository.findByFirstNameAndLastName(firstName, lastName);
		if (employeeOpt.isEmpty()) {
			throw new EmployeeNotFoundException("Employee(s) with requested first and last name does not exist");
		}
		return employeeOpt.get();
	}

	public Employee updateEmployee(long employeeId, Employee employee) {
		Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
		if (existingEmployee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee with id " + employeeId + " does not exist");
		}
		Employee oldEmployee = existingEmployee.get();
		oldEmployee.setFirstName(employee.getFirstName());
		oldEmployee.setLastName(employee.getLastName());
		oldEmployee.setSalary(employee.getSalary());
		oldEmployee.setState(employee.getState());
		oldEmployee.setCountry(employee.getCountry());
		return employeeRepository.save(oldEmployee);
	}

	public void deleteEmployee(long employeeId) {
		Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
		if (existingEmployee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee with id " + employeeId + " does not exist");
		}
		employeeRepository.deleteById(employeeId);
		
	}
}
