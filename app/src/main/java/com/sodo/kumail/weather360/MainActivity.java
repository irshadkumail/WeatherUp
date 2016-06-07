package com.sodo.kumail.weather360;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    NestedScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.pager);
       // scrollView = (NestedScrollView) findViewById (R.id.nest_scrollview);
        //scrollView.setFillViewport (true);


        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends FragmentStatePagerAdapter
    {
        public MyAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }
        public Fragment getItem(int pos)
        {
            Fragment fragment=null;
            if(pos==0) {
                fragment = new FragmentA();
            }
            if(pos==1)
            {
                fragment= new FragmentB();
            }
            if(pos==2)
            {
                fragment= new FragmentC();
            }

            return fragment;
        }
        public int getCount()
        {
            return 3;
        }
        public CharSequence getPageTitle(int pos)
        {
            if(pos==0)
            {
                return "TODAY";
            }
            if(pos==1)
            {
                return "HOURLY";
            }
            if(pos==2)
            {
                return "10 DAYS";
            }
            return "NULLA";
        }
    }
}
