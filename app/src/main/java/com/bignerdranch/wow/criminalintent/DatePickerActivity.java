package com.bignerdranch.wow.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.Date;

/**
 * Created by wow on 2017/9/21.
 */

public class DatePickerActivity extends SingleFragmentActivity {
    public static final String EXTRA_DATE2 = "com.bignerdranch.android.criminalintent.date";
    @Override
    protected Fragment createFragment() {
        return new DatePickerFragment();
    }

    public static Intent newInstance(Context packageContext, Date date){
        Intent intent = new Intent(packageContext,DatePickerActivity.class);
        intent.putExtra(EXTRA_DATE2,date);
        return intent;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date date = (Date)getIntent().getSerializableExtra(EXTRA_DATE2);
        DatePickerFragment.newInstance(date);
    }
}
