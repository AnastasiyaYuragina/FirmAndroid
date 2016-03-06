package com.rem.firm.firm;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Anastasiya on 3/4/2016.
 */
public class AddEmployeeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    private final String MANAGER = "Manager";
    private final String EMPLOYEE = "Employee";
    private final String SALES_MANAGER = "Sales manager";

    private Firm firm;
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPatronymic;
    private EditText editTextSalary;
    private EditText editTextSumInBank;
    private RadioButton radioButtonMan;
    private RadioButton radioButtonWoman;
    private Button buttonAddEmployee;
    private Spinner spinnerDepartment;
    private Spinner spinnerTypeEmployee;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AddEmployeeFragment newInstance(int sectionNumber) {
        AddEmployeeFragment fragment = new AddEmployeeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public AddEmployeeFragment() {
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_employee_tab, container, false);

        editTextName = (EditText) rootView.findViewById(R.id.editName);
        editTextSurname = (EditText) rootView.findViewById(R.id.editSurname);
        editTextPatronymic = (EditText) rootView.findViewById(R.id.editPatronymic);
        editTextSalary = (EditText) rootView.findViewById(R.id.editSalary);
        editTextSumInBank = (EditText) rootView.findViewById(R.id.editSumInBank);
        radioButtonMan = (RadioButton) rootView.findViewById(R.id.rButtonMan);
        radioButtonWoman = (RadioButton) rootView.findViewById(R.id.rButtonWoman);
        buttonAddEmployee = (Button) rootView.findViewById(R.id.buttonAddEmployee);
        spinnerDepartment = (Spinner) rootView.findViewById(R.id.department);
        spinnerTypeEmployee = (Spinner) rootView.findViewById(R.id.typeEmployee);

        ArrayList<Department> listDep = firm.getListDepartment();
        String[] arrayDep = new String[listDep.size()];
        for (int i = 0; i < listDep.size(); i++) {
            arrayDep[i] = listDep.get(i).getNameDepartment();
        }
        ArrayAdapter<String> arrayAdapterDep = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrayDep);
        arrayAdapterDep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(arrayAdapterDep);
        spinnerDepartment.setPrompt("Department");

        String[] arrayTypeEmployee = {EMPLOYEE, MANAGER, SALES_MANAGER};
        ArrayAdapter<String> arrayAdapterEmpl = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrayTypeEmployee);
        arrayAdapterEmpl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeEmployee.setAdapter(arrayAdapterEmpl);
        spinnerTypeEmployee.setPrompt("Type of employee");

        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddEmployee();
            }
        });

        return rootView;
    }

    private void onClickAddEmployee() {
        String sex = null;
        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        String patronymic = editTextPatronymic.getText().toString();
        String salaryS = editTextSalary.getText().toString();
        String bankAccountS = editTextSumInBank.getText().toString();
        Department department = firm.getDepartmentByName(spinnerDepartment.getSelectedItem().toString());
        Employee employee;
        Manager manager;
        SalesManager salesManager;
        if (radioButtonWoman.isChecked()) {
            sex = Employee.fWoman;
        } else if(radioButtonMan.isChecked()) {
            sex = Employee.fMan;
        }
        if (!name.isEmpty() && !surname.isEmpty() && !patronymic.isEmpty() &&
                !salaryS.isEmpty() && !bankAccountS.isEmpty() && !sex.equals(null)) {

            double salary = Double.parseDouble(salaryS);
            double bankAccount = Double.parseDouble(bankAccountS);
            if (spinnerTypeEmployee.getSelectedItem().equals(EMPLOYEE)) {
                employee = new Employee(name, surname, patronymic, salary, bankAccount, sex, department);
                if (firm.addEmployee(employee)) {
                    messageToast("Employee successfully added");
                } else {
                    messageToast("Employee not add");
                }

            } else if (spinnerTypeEmployee.getSelectedItem().equals(MANAGER)) {
                manager = new Manager(name, surname, patronymic, salary, bankAccount, sex, department);
                if (firm.addEmployee(manager)) {
                    messageToast("Employee successfully added");
                } else {
                    messageToast("Employee not add");
                }
            } else if (spinnerTypeEmployee.getSelectedItem().equals(SALES_MANAGER)) {
                salesManager = new SalesManager(name, surname, patronymic, salary, bankAccount, sex, department);
                if (firm.addEmployee(salesManager)) {
                    messageToast("Employee successfully added");
                } else {
                    messageToast("Employee not add");
                }
            }
        } else {
            messageToast("Employee not add");
        }
    }

    private void messageToast(String message) {
        Context context = this.getContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
