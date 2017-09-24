package com.bignerdranch.wow.criminalintent;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by wow on 2017/9/13.
 */

public class CrimePagerActivity extends FragmentActivity{

    private List<Crime> mCrimes;
    private ViewPager mViewPager;
    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager)findViewById(R.id.activity_crime_pager_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CriminalFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        for (int i= 0; i < mCrimes.size();i++){
            if (mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


}
