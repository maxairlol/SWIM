package com.example.artem.films;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Artem on 2018-04-13.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ActorsListFragment();
            case 1:
                return new GalleryFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
