package com.arahman.EmployeeManagementSystem.model;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="EMPLOYEES")
@Component
public class Employee {
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private BigDecimal salary;
	private String state;
	private String country;
	public Employee(long id, String firstName, String lastName, BigDecimal salary, String state, String country) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.state = state;
		this.country = country;
	}
	public Employee() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public int hashCode() {
		return Objects.hash(country, firstName, id, lastName, salary, state);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(country, other.country) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(salary, other.salary)
				&& Objects.equals(state, other.state);
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
				+ ", state=" + state + ", country=" + country + "]";
	}
	
	

}
