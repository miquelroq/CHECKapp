package com.example.alarm_doc;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
            case 3:
                return new Question4();
            case 4:
                return new Question5();
            case 5:
                return new Question6();
            case 6:
                return new Question7();
            case 7:
                return new Question8();
            case 8:
                return new Question9();
            case 9:
                return new Question10();
            case 10:
                return new Question11();
            case 11:
                return new Question12();
            case 12:
                return new Question13();
            case 13:
                return new Question14();
            case 14:
                return new Question15();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nNoOfTabs;
    }
}
