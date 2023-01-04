package com.arahman.EmployeeManagementSystem.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arahman.EmployeeManagementSystem.model.Employee;
import com.arahman.EmployeeManagementSystem.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/employees")
@CrossOrigin(origins="http://localhost:4200/")
public class EmployeeController {
private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@Operation(summary="Used to view list of Employee objects in database")
	@ApiResponse(responseCode = "200", description = "Contains a list of all employees", content = {
			@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)
	})
	@GetMapping
	public List<Employee> getEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@Operation(summary="Used to create an Employee in the database")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Employee resource was successfully created", content = {
				@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)
		}),
		@ApiResponse(responseCode = "303", description = "Employee resource could not be created since employee with the given details already exists", content = {
				@Content(mediaType=MediaType.APPLICATION_JSON_VALUE) }) })
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee createdEmployee = employeeService.createEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdEmployee.getId()).toUri();
		return ResponseEntity.created(location).body(createdEmployee);
	}
	
	@Operation(summary="Used to retrieve an Employee by their id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Employee resource by the given id was successfully found", content = {
				@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)
		}),
		@ApiResponse(responseCode = "404", description = "Employee resource by the given id could not be found", content = {
				@Content(mediaType=MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping("/{id}")
	public Employee retrieveEmployee(@PathVariable("id") long employeeId) {
		return employeeService.getEmployee(employeeId);
	}
	
	@Operation(summary="Used to update an Employee in the database")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Employee resource was successfully updated", content = {
				@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)
		}),
		@ApiResponse(responseCode = "404", description = "Employee resource could not be found", content = {
				@Content(mediaType=MediaType.APPLICATION_JSON_VALUE) }) })
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee) {
		Employee updateEmployee = employeeService.updateEmployee(employeeId, employee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	@Operation(summary="Used to delete an Employee from the database")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Employee resource was successfully deleted", content = {
				@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)
		}),
		@ApiResponse(responseCode = "404", description = "Employee resource could not be found", content = {
				@Content(mediaType=MediaType.APPLICATION_JSON_VALUE) }) })
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}
	
	@Operation(summary="Used to view list of Employee objects with the given first and last name in database")
	@ApiResponse(responseCode = "200", description = "Contains a list of all employees with the given first and last name", content = {
			@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)
	})
	@GetMapping("/{first}/{last}")
	public List<Employee> retrieveEmployeesByFullName(@PathVariable("first") String firstName, @PathVariable("last") String lastName) {
		return employeeService.getEmployeesByFullName(firstName, lastName);
	}

}
