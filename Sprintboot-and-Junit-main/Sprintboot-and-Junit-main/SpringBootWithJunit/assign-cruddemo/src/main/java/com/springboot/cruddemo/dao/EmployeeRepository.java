package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByCompany(Company company);
}
