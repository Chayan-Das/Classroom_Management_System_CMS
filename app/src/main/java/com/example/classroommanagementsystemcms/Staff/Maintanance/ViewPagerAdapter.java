package com.example.classroommanagementsystemcms.Staff.Maintanance;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.classroommanagementsystemcms.Staff.Maintanance.Year.FirstYearFragment;
import com.example.classroommanagementsystemcms.Staff.Maintanance.Year.FourthYearFragment;
import com.example.classroommanagementsystemcms.Staff.Maintanance.Year.SecondYearFragment;
import com.example.classroommanagementsystemcms.Staff.Maintanance.Year.ThirdYearFragment;

import io.reactivex.rxjava3.annotations.NonNull;

public class ViewPagerAdapter
        extends FragmentPagerAdapter {

    public ViewPagerAdapter(
            @NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new FirstYearFragment();
        else if (position == 1)
            fragment = new SecondYearFragment();
        else if (position == 2)
            fragment = new ThirdYearFragment();
        else if (position == 3)
            fragment = new FourthYearFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "1st Year";
        else if (position == 1)
            title = "2nd Year";
        else if (position == 2)
            title = "3rd Year";

        else if (position == 3)
            title = "4th Year";
        return title;
    }
}



