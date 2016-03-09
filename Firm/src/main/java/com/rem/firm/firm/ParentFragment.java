package com.rem.firm.firm;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anastasiya on 3/9/2016.
 */
public class ParentFragment extends Fragment{

    protected static final String NAME = "name";
    protected static final String SURNAME = "surname";
    protected static final String PATRONYMIC = "patronymic";
    protected static final String SALARY = "salary";
    protected static final String BANK_ACCOUNT = "bank account";
    protected static final String SEX = "sex";
    protected static final String MESSAGE = "Fire Employee";

    private ArrayAdapter<String> adapterList;
    protected ListView listViewEmployee;
    protected Map<String, Comparator<Employee>> map = new HashMap<String, Comparator<Employee>>();
    protected Spinner spinnerSort;


    public void updateListAdapter(ArrayList<Employee> employeeArrayList) {
        String [] arrayEmployee = new String[employeeArrayList.size()];
        for (int i = 0; i < employeeArrayList.size(); i++) {
            arrayEmployee[i] = employeeArrayList.get(i).toString() + employeeArrayList.get(i).getClass().getSimpleName();
        }
        adapterList = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, arrayEmployee);
        listViewEmployee.setAdapter(adapterList);
    }

    public void updateSpinnerSort() {
        map.put(ParentFragment.NAME, Employee.SORT_BY_NAME);
        map.put(ParentFragment.SURNAME, Employee.SORT_BY_SURNAME);
        map.put(ParentFragment.PATRONYMIC, Employee.SORT_BY_PATRONYMIC);
        map.put(ParentFragment.SALARY, Employee.SORT_BY_SALARY);
        map.put(ParentFragment.BANK_ACCOUNT, Employee.SORT_BY_BANK_ACCOUNT);
        map.put(ParentFragment.SEX, Employee.SORT_BY_SEX);

        Object[] mapKey = map.keySet().toArray();
        String[] arrayMapKey = new String[mapKey.length];
        for (int i = 0; i < mapKey.length; i++) {
            arrayMapKey[i] = mapKey[i].toString();
        }
        ArrayAdapter<String> arrayAdapterSort = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, arrayMapKey);
        arrayAdapterSort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(arrayAdapterSort);
    }
}
