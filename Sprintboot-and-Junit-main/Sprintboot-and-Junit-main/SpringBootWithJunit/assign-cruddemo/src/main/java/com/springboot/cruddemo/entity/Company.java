package com.springboot.cruddemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	
	//define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="c_name")
	private String c_name;

	@Column(name="c_branch")
	private String c_branch;

	public Company() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_branch() {
		return c_branch;
	}

	public void setC_branch(String c_branch) {
		this.c_branch = c_branch;
	}

	@Override
		public String toString() {
			return "Company [ + c_name=" + c_name + ", c_branch=" + c_branch + ", id=" + id + "]";
		}

	public Company(String c_name, String c_branch) {
		this.c_name = c_name;
		this.c_branch = c_branch;
	}

	public Company(int id, String c_name, String c_branch) {
		this.id = id;
		this.c_name = c_name;
		this.c_branch = c_branch;
	}
}
