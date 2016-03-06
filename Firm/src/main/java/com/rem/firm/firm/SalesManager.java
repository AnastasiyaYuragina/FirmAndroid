package com.rem.firm.firm;

public class SalesManager extends Employee {
	private double sumSales;

	public SalesManager(String name, String surname, String patronymic, double salary, double bankAccount, String sex,
			Department department) {
		super(name, surname, patronymic, salary, bankAccount, sex, department);
	}
	
	public double getSumSales() {
		return sumSales;
	}
	
	public void setSumSales(double sum){
		sumSales = sum;
	}
	
	public double getSalary() {
		double salary = super.getSalary();
		return salary + (sumSales * 0.3);
	}
}
