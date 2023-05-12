package com.springboot.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
	
	//define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dept")
	private String dept;
	
	@Column(name="location")
	private String location;

	public Employee() {

	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "company_id")
	private Company company;
	
	//define constructors


	public Employee(String firstName, String lastName, String email, String dept, String location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dept = dept;
		this.location = location;
	}

	
	//define getters and setters
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	//define tostring	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", dept=" + dept + ", location=" + location + "]";
	}

}
