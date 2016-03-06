package com.rem.firm.firm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Firm {

	private String name;
	private String address;
	private double bankAccount;
	private ArrayList<Employee> listEmployee;
	private ArrayList<Department> listDepartment;
	
	public Firm(String name, String address, double bankAccount) {
		this.name = name;
		this.address = address;
		this.bankAccount = bankAccount;
		listEmployee = new ArrayList<Employee>();
		listDepartment = new ArrayList<Department>();
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getName() {
		return name;
	}
	
	public void addDepartment (Department department) {
		listDepartment.add(department);
	}
	
	public ArrayList<Department> getListDepartment() {
		ArrayList<Department> copy = new ArrayList<Department>(listDepartment);
		return copy;
	}
	
	public Department getDepartmentByName(String name) {
		for (Department department : listDepartment) {
			if(department.getNameDepartment().equals(name)) {
				return department;
			}
		}
		return null;
	}

	public void addBankAccountFirm(double money) {
		this.bankAccount += money;
	}
	public String getBankAccountFirm() {
		String s = bankAccount + "";
		return s;
	}
	
	public boolean addEmployee(Employee employee) {
		boolean isUserInBD = false;
		for (Employee employee2 : listEmployee) {
			if(employee.getSurname().equals(employee2.getSurname()) && employee.getName().equals(employee2.getName())) {
				isUserInBD = true;
			}
		}
		if(isUserInBD == false){
			listEmployee.add(employee);
			employee.getDepartment().addEmployee(employee);
			return true;
		}
		return false;
	}

	public boolean fireEmployee(String name, String surname, String patronymic) {
		Employee employee = findEmployee(name, surname, patronymic);

		if(employee == null) {
			return false;
		} else if(employee.getDepartment().fireEmployee(employee)) {
			listEmployee.remove(employee);
			return true;
		} else {
			return false;
		}
	}

	public Employee findEmployee(String name, String surname, String patronymic) {

		for(int i = 0; i < listEmployee.size(); i++) {
			Employee indexOfEmployee = listEmployee.get(i);
			boolean bName = indexOfEmployee.getName().equals(name);
			boolean bSurname = indexOfEmployee.getSurname().equals(surname);
			boolean bPatronymic = indexOfEmployee.getPatronymic().equals(patronymic);
			
			if(bName && bSurname && bPatronymic) {
				return indexOfEmployee;
			}
		}
		return null;
	}

	public boolean setDepartmentForEmployee(String name, String surname, String patronymic, String departmentName) {
		Employee employee = findEmployee(name, surname, patronymic);
		boolean isSet = false;
		
		if(employee == null) {
			isSet = false;
		} else if(employee.getDepartment().fireEmployee(employee)) {
			for (Department department : listDepartment) {
				if(department.getNameDepartment().equals(departmentName)) {
					employee.setDepartment(department);
					department.addEmployee(employee);
					isSet = true;
				} 
			} 
		} else {
			isSet = false;
		}
		return isSet;
	}
	
	public void giveSalaryForAll(){
		for (Employee employee : listEmployee) {
			double salarys = employee.getSalary();
			double currenBankAccount = bankAccount;
			currenBankAccount -= salarys;
			if(currenBankAccount >= 0) {
				employee.pay(salarys);
				bankAccount -= salarys;
			} 
		}
	}

	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> copy = new ArrayList<Employee>(listEmployee);
		return copy;
	}

	public ArrayList<Employee> getAllEmployeesSorted(Comparator<Employee> comparator) {
		ArrayList<Employee> copy = new ArrayList<Employee>(listEmployee);
		Collections.sort(copy, comparator);
		return copy;
	}
	
	public ArrayList<Employee> getAllEmployeesOrderedBySalary() {
		return getAllEmployeesSorted(Employee.SORT_BY_SALARY);
//		
//		ArrayList<Employee> copy = new ArrayList<Employee>(listEmployee);
//		Collections.sort(copy, Employee.Comparators.salaryCompare);
//		return copy;
	}
	
	public ArrayList<Employee> getAllEmployeesSortedBySName() {
		return getAllEmployeesSorted(Employee.SORT_BY_SURNAME);
//		
//		ArrayList<Employee> copy = new ArrayList<Employee>(listEmployee);
//		Collections.sort(copy, Employee.Comparators.surnameCompare);
//		return copy;
	}
	
	public void sellFor10() {
		for (Employee employee : listEmployee) {
			if(employee instanceof SalesManager) {
				((SalesManager) employee).setSumSales(10_000);
			}
		}
	}
	
	public String toString(ArrayList<Employee> list) {
		StringBuilder stringBuilder = new StringBuilder("");
		for (Employee employee : list) {
			stringBuilder.append(employee.toString());
		} 
		return stringBuilder.toString();
	}
}

