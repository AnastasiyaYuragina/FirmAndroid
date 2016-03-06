package com.rem.firm.firm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anastasiya on 3/4/2016.
 */
public class EmployeeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    private Firm firm;
    private ListView listViewEmployee;
    private Spinner spinnerSort;
    private ImageButton buttonUpdate;
    private Map<String, Comparator<Employee>> map = new HashMap<String, Comparator<Employee>>();

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
        map.put("name", Employee.SORT_BY_NAME);
        map.put("surname", Employee.SORT_BY_SURNAME);
        map.put("patronymic", Employee.SORT_BY_PATRONYMIC);
        map.put("salary", Employee.SORT_BY_SALARY);
        map.put("bank account", Employee.SORT_BY_BANK_ACCOUNT);
        map.put("sex", Employee.SORT_BY_SEX);

        Object[] mapKey = map.keySet().toArray();
        String[] arrayMapKey = new String[mapKey.length];
        for (int i = 0; i < mapKey.length; i++) {
            arrayMapKey[i] = mapKey[i].toString();
        }
        ArrayAdapter<String> arrayAdapterSort = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrayMapKey);
        arrayAdapterSort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(arrayAdapterSort);
        spinnerSort.setPrompt("Sort");

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onClickSort();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayList<Employee> employeeArrayList = firm.getAllEmployees();
        String [] arrayEmployee = new String[employeeArrayList.size()];
        for (int i = 0; i < employeeArrayList.size(); i++) {
            arrayEmployee[i] = employeeArrayList.get(i).toString() + employeeArrayList.get(i).getClass().getSimpleName();
        }
        final ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, arrayEmployee);
        listViewEmployee.setAdapter(adapterList);

//        listViewEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewEmployee.setAdapter(adapterList);
            }
        });


        return rootView;
    }

    private void onClickFireEmployee() {
        firm.fireEmployee(null, null, null);
    }

    private void onClickChangeDep() {
        firm.setDepartmentForEmployee(null, null, null, null);
    }

    private void onClickSort() {
        Comparator<Employee> comparator = map.get(spinnerSort.getSelectedItem().toString());
//        firm.getAllEmployeesSorted(comparator);

        ArrayList<Employee> employeeArrayList = firm.getAllEmployeesSorted(comparator);
        String[] arrayEmployee = new String[employeeArrayList.size()];
        for (int i = 0; i < employeeArrayList.size(); i++) {
            arrayEmployee[i] = employeeArrayList.get(i).toString();
        }
        final ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, arrayEmployee);
        listViewEmployee.setAdapter(adapterList);
    }
}
