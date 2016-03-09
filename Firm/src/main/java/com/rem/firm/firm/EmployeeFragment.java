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
public class EmployeeFragment extends ParentFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    private Firm firm;
    private ImageButton buttonUpdate;
    private ArrayList<Employee> employeeArrayList;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EmployeeFragment newInstance(int sectionNumber) {
        EmployeeFragment fragment = new EmployeeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public EmployeeFragment() {
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.employee_tab, container, false);
        listViewEmployee = (ListView) rootView.findViewById(R.id.listViewEmployeeFirm);
        buttonUpdate = (ImageButton) rootView.findViewById(R.id.imageButtonUpdate);
        spinnerSort = (Spinner) rootView.findViewById(R.id.spinnerSort);

        updateSpinnerSort();
        spinnerSort.setPrompt("Sort");
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortEmployees();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        employeeArrayList = firm.getAllEmployees();
        updateListAdapter(employeeArrayList);

        listViewEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickAlertDialog(employeeArrayList.get(position));
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeArrayList = firm.getAllEmployees();
                updateListAdapter(employeeArrayList);
            }
        });

        return rootView;
    }

    private void onClickFireEmployee(Employee employee) {
        firm.fireEmployee(employee.getName(), employee.getSurname(), employee.getPatronymic());
        employeeArrayList = firm.getAllEmployees();
        updateListAdapter(employeeArrayList);
    }

    private void sortEmployees() {
        Comparator<Employee> comparator = map.get(spinnerSort.getSelectedItem().toString());

        employeeArrayList = firm.getAllEmployeesSorted(comparator);
        updateListAdapter(employeeArrayList);
    }

    private void onClickAlertDialog(final Employee employee) {
        Context context = getActivity();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setPositiveButton(ParentFragment.MESSAGE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onClickFireEmployee(employee);
            }
        });

        alertDialog.show();
    }
}
