package com.bignerdranch.wow.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by wow on 2017/9/7.
 */

public class CriminalListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CriminalListFragment();
    }
}
