package com.rem.firm.firm;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Anastasiya on 3/4/2016.
 */
public class DepartmentFragment extends ParentFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    private Firm firm;
    private ImageButton imageButtonUpdateDep;
    private Spinner spinnerDep;
    private ArrayList<Employee> employeeArrayList;
    private Department selectDep;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DepartmentFragment newInstance(int sectionNumber) {
        DepartmentFragment fragment = new DepartmentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public DepartmentFragment() {
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.department_tab, container, false);

        imageButtonUpdateDep = (ImageButton) rootView.findViewById(R.id.imageButtonUpdateDep);
        spinnerDep = (Spinner) rootView.findViewById(R.id.spinnerDepartment);
        spinnerSort = (Spinner) rootView.findViewById(R.id.spinnerSortDep);
        listViewEmployee = (ListView) rootView.findViewById(R.id.listViewEmployeeDep);

        updateSpinnerSort();
        spinnerSort.setPrompt("Sort");
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortDep();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayList<Department> listDep = firm.getListDepartment();
        String[] arrayDep = new String[listDep.size()];
        for (int i = 0; i < listDep.size(); i++) {
            arrayDep[i] = listDep.get(i).getNameDepartment();
        }
        ArrayAdapter<String> arrayAdapterDep = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrayDep);
        arrayAdapterDep.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDep.setAdapter(arrayAdapterDep);
        spinnerDep.setPrompt("Department");
        selectDep = firm.getDepartmentByName(spinnerDep.getSelectedItem().toString());
        spinnerDep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showEmployees();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        employeeArrayList = selectDep.getAllEmployeesFromDep();
        updateListAdapter(employeeArrayList);

        listViewEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialog(employeeArrayList.get(position));
            }
        });

        imageButtonUpdateDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDep = firm.getDepartmentByName(spinnerDep.getSelectedItem().toString());
                employeeArrayList = selectDep.getAllEmployeesFromDep();
                updateListAdapter(employeeArrayList);
            }
        });

        return rootView;
    }

    private void sortDep() {
        Comparator<Employee> comparator = map.get(spinnerSort.getSelectedItem().toString());
        selectDep = firm.getDepartmentByName(spinnerDep.getSelectedItem().toString());

        employeeArrayList = selectDep.getEmployeesFromDepSorted(comparator);
        updateListAdapter(employeeArrayList);
    }

    private void showEmployees() {
        selectDep = firm.getDepartmentByName(spinnerDep.getSelectedItem().toString());
        employeeArrayList = selectDep.getAllEmployeesFromDep();
        updateListAdapter(employeeArrayList);
    }

    private void fireEmployee(Employee employee) {
        firm.fireEmployee(employee.getName(), employee.getSurname(), employee.getPatronymic());
        selectDep = firm.getDepartmentByName(spinnerDep.getSelectedItem().toString());
        employeeArrayList = selectDep.getAllEmployeesFromDep();
        updateListAdapter(employeeArrayList);
    }

    private void alertDialog(final Employee employee) {
        Context context = getActivity();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setPositiveButton(ParentFragment.MESSAGE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fireEmployee(employee);

            }
        });

        alertDialog.show();
    }
}
