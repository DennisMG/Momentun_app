package com.example.momentun_app.app;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.parse.Parse;


public class MainActivity extends FragmentActivity {


    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    android.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "e8c95V53VCL4erEEcB9RFkG8u3snb8YPevhQ8Tvj", "0ONFzrSprwkymciPf3mL7cHHoEvlXBFbJiwUbYuZ");

        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/









        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        Tab = (ViewPager)findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar = getActionBar();
                        actionBar.setSelectedNavigationItem(position);                    }
                });
        Tab.setAdapter(TabAdapter);
        Tab.setPageTransformer(true, new ZoomOutPageTransformer());
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar = getActionBar();
        actionBar.hide();




        //Enable Tabs on Action Bar
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        }
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                //Tab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        //Add New Tab
        actionBar.addTab(actionBar.newTab().setText("Camera").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Gallery").setTabListener(tabListener));
    }

    public void openCamera(View view) {
        Intent myIntent = new Intent(this, CameraIndependantActivity.class);
        startActivityForResult(myIntent, 0);
    }
}
