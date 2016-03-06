package com.rem.firm.firm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Department {
	private String nameDepartment;
	private ArrayList<Employee> listEmployee;
	private Manager manager;

	public Department(String nameDepartment) {
		this.nameDepartment = nameDepartment;
		listEmployee = new ArrayList<Employee>();
	}

	public boolean addManager(Manager manager) {
		if(this.manager == null) {
			this.manager = manager;
			return true;
		} else {
			return false;
		}
	}

	public String getNameDepartment() {
		return nameDepartment;
	}

	public boolean addEmployee(Employee employee) {
			if (listEmployee.add(employee)) {
				return true;
			}else {
				return false;
			}
	}
	
	public boolean fireEmployee(Employee employee) {
		if(employee == null) {
			return false;
		}else {
			listEmployee.remove(employee);
			return true;
		}
	}

	public ArrayList<Employee> getAllEmployeesFromDep() {
		ArrayList<Employee> copy = new ArrayList<Employee>(listEmployee);
		return copy;
	}

	public ArrayList<Employee> getEmployeesFromDepSorted(Comparator<Employee> comparator) {
		ArrayList<Employee> copy = new ArrayList<Employee>(listEmployee);
		Collections.sort(copy, comparator);
		return copy;
	}

	public ArrayList<Employee> getEmployeesFromDepSortedBySalary() {
		ArrayList<Employee> copy = new ArrayList<Employee>(listEmployee);
		Collections.sort(copy, Employee.SORT_BY_SALARY);
		return copy;
	}

	public ArrayList<Employee> getEmployeesFromDepSortedBySName() {
		ArrayList<Employee> copy = new ArrayList<Employee>(listEmployee);
		Collections.sort(copy, Employee.SORT_BY_SURNAME);
		return copy;
	}

}
