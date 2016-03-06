package com.rem.firm.firm;

import java.util.Comparator;

public class Employee implements Comparable<Employee>{
	private String name;
	private String surname;
	private String patronymic;
	private double salary;
	private double bankAccount;
	private String sex;
	private Department department;
	public final static String fMan = "Man";
	public final static String fWoman = "Woman";
	
	public static final Comparator<Employee> SORT_BY_NAME = new SortByName();
//	public static final Comparator<Employee> SORT_BY_NAME_REVERSED = new SortByName().reversed();
	public static final Comparator<Employee> SORT_BY_SURNAME = new SortBySurname();
//	public static final Comparator<Employee> SORT_BY_SURNAME_REVERSED = new SortBySurname().reversed();
	public static final Comparator<Employee> SORT_BY_PATRONYMIC = new SortByPatronymic();
//	public static final Comparator<Employee> SORT_BY_PATRONYMIC_REVERSED = new SortByPatronymic().reversed();
	public static final Comparator<Employee> SORT_BY_SALARY = new SortBySalary();
//	public static final Comparator<Employee> SORT_BY_SALARY_REVERSED = new SortBySalary().reversed();
	public static final Comparator<Employee> SORT_BY_BANK_ACCOUNT = new SortByBankAccount();
//	public static final Comparator<Employee> SORT_BY_BANK_ACCOUNT_REVERSED = new SortByBankAccount().reversed();
	public static final Comparator<Employee> SORT_BY_SEX = new SortBySex();
//	public static final Comparator<Employee> SORT_BY_SEX_REVERSED = new SortBySex().reversed();
	
	public Employee (String name, String surname, String patronymic, double salary, double bankAccount, String sex, Department department) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.salary = salary;
		this.bankAccount = bankAccount;
		this.sex = sex;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public double getSalary() {
		return salary;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void pay(double salary){
		this.bankAccount += salary;
	}
	
	public void setDepartment (Department depatrment) {
		this.department = depatrment;
	}

	@Override
	public String toString() {
		String stringEmployee = name + " " + surname + " " + patronymic + " " + salary + " " 
				+ bankAccount + " " + sex + " " + department.getNameDepartment() + "\r\n"; 
		return stringEmployee;
	}
	
	private static class SortByName implements Comparator<Employee> {
		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.name.compareTo(o2.name);
		}
	}
	
	private static class SortBySurname implements Comparator<Employee> {
		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.surname.compareTo(o2.surname);
		}
	}
	
	private static class SortByPatronymic implements Comparator<Employee> {
		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.patronymic.compareTo(o2.patronymic);
		}
	}
	
	private static class SortBySalary implements Comparator<Employee> {
		@Override
		public int compare(Employee o1, Employee o2) {
			return Double.compare(o1.salary, o2.salary);
		}
	}
	
	private static class SortByBankAccount implements Comparator<Employee> {
		@Override
		public int compare(Employee o1, Employee o2) {
			return Double.compare(o1.bankAccount, o2.bankAccount);
		}
	}
	
	private static class SortBySex implements Comparator<Employee> {
		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.sex.compareTo(o2.sex);
		}
	}

	@Override
	public int compareTo(Employee arg0) {
		return Comparators.surnameCompare.compare(this, arg0);
	}

	public static class Comparators {

		public static Comparator<Employee> salaryCompare = new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
//				return (int) (o1.salary - o2.salary);
				return (int) Double.compare(o1.salary, o2.salary);
			}
		};
		
		public static Comparator<Employee> surnameCompare = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.surname.compareTo(o2.surname);
            }
        };
	}
	
	
}
