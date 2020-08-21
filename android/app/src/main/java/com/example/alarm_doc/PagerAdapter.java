package com.example.alarm_doc;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int nNoOfTabs;

    public PagerAdapter(FragmentManager fm, int behavior) {
        super(fm);
        this.nNoOfTabs = behavior;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Question1();
            case 1:
                return new Question2();
            case 2:
                return new Question3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nNoOfTabs;
    }
}
