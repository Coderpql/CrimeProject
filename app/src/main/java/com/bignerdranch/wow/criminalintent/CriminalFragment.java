package com.bignerdranch.wow.criminalintent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;


public class CriminalFragment extends Fragment{
    private  Crime mCrime;
    private EditText mTitleField;
    private  Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
        UUID crimeid = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeid);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_criminal,container,false);
        mTitleField = (EditText)v.findViewById(R.id.criminal_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.crime_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                Intent intent = DatePickerActivity.newInstance(getActivity(),mCrime.getDate());
                startActivityForResult(intent,REQUEST_DATE);//如果使用getactivity().startActivityForResult()则只有Activity的onActivityResult执行，不用则都执行。
            }
        });
        mTimeButton = (Button)v.findViewById(R.id.crime_time);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CriminalFragment.this,REQUEST_TIME);
                dialog.show(manager,DIALOG_TIME);
            }
        });
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        return v;
    }

    public static CriminalFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);
        CriminalFragment fragment = new CriminalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        /*if (requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            mCrime.setDate(date);
            updateDate();
        }*/
        if (requestCode == REQUEST_TIME){
            Date date = (Date)data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);

            mCrime.setDate(date);
            updateDate();

        }
        if (requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            mCrime.setDate(date);
            updateDate();
        }
    }


    private void updateDate() {
        String mDate = (String) DateFormat.format("yyyy, MMMM dd日,EEEE, kk:mm",mCrime.getDate());
        mDateButton.setText(mDate);
    }
}
