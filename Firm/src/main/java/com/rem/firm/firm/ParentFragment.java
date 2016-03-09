package com.rem.firm.firm;

import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

/**
 * Created by Anastasiya on 3/9/2016.
 */
public class ParentFragment extends Fragment{
    private ArrayAdapter<String> adapterList;
    protected ListView listViewEmployee;
    protected static final String NAME = "name";
    protected static final String SURNAME = "surname";
    protected static final String PATRONYMIC = "patronymic";
    protected static final String SALARY = "salary";
    protected static final String BANK_ACCOUNT = "bank account";
    protected static final String SEX = "sex";
    protected static final String MESSAGE = "Fire Employee";

    public void updateListAdapter(ArrayList<Employee> employeeArrayList) {
        String [] arrayEmployee = new String[employeeArrayList.size()];
        for (int i = 0; i < employeeArrayList.size(); i++) {
            arrayEmployee[i] = employeeArrayList.get(i).toString() + employeeArrayList.get(i).getClass().getSimpleName();
        }
        adapterList = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, arrayEmployee);
        listViewEmployee.setAdapter(adapterList);
    }
}
