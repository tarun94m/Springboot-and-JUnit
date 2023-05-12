package com.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.cruddemo.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
