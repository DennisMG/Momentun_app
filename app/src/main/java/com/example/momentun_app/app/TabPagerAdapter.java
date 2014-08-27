package com.example.momentun_app.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by dell on 22/08/2014.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CameraActivity();

            case 1:

                return new GifGallery();
            case 2:

                return new CardActivity();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
