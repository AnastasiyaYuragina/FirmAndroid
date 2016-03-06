package com.rem.firm.firm;

/**
 * Created by Anastasiya on 3/4/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.nio.BufferUnderflowException;

/**
 * A placeholder fragment containing a simple view.
 */
public class FirmFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    private Firm firm;
    private TextView textViewSumOnBankAccount;
    private Button buttonAddMoneyToFirm;
    private Button buttonGiveSalary;
    private Button buttonSellFor10;
    private EditText editTextAddMoneyToFirm;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FirmFragment newInstance(int sectionNumber) {
        FirmFragment fragment = new FirmFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FirmFragment() {
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firm_tab, container, false);
        addDepartment();
        textViewSumOnBankAccount = (TextView) view.findViewById(R.id.textViewBankAccount);
        buttonAddMoneyToFirm = (Button) view.findViewById(R.id.buttonAddMoneyToFirm);
        buttonGiveSalary = (Button)  view.findViewById(R.id.buttonGiveSalary);
        buttonSellFor10 = (Button) view.findViewById(R.id.buttonSellFor10);
        editTextAddMoneyToFirm = (EditText) view.findViewById(R.id.editSumToBA);

        textViewSumOnBankAccount.setText(firm.getBankAccountFirm());

        buttonAddMoneyToFirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddMoneyToFirm();
            }
        });
        buttonGiveSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firm.giveSalaryForAll();
            }
        });
        buttonSellFor10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firm.sellFor10();
            }
        });


        return view;
    }

    private void onClickAddMoneyToFirm() {
        firm.addBankAccountFirm(Double.parseDouble(editTextAddMoneyToFirm.getText().toString()));
        editTextAddMoneyToFirm.setText("");
        textViewSumOnBankAccount.setText(firm.getBankAccountFirm());
    }

    private void addDepartment() {
        firm.addDepartment(new Department("IT"));
        firm.addDepartment(new Department("Managers"));
        firm.addDepartment(new Department("Sales"));
    }
}