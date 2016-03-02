package com.example.novo.educationtestsample.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.fragments.ForumFragment;
import com.example.novo.educationtestsample.fragments.FragmentDrawer;
import com.example.novo.educationtestsample.fragments.TestFragment;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements FragmentInteractionListener,FragmentDrawer.FragmentDrawerListener {

    public FragmentDrawer drawerFragment;
    public Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

        // display the first navigation drawer view on app launch
        displayView(0);
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

    @Override
    public void onDrawerItemSelected(int position) {
    displayView(position);
    }



    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new TestFragment();
                title = getString(R.string.title_test);
                break;
            case 1:
                fragment = new ForumFragment();
                title = getString(R.string.title_forum);
                break;
            case 2:
                fragment = new ForumFragment();
                title = getString(R.string.title_result);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }




    @Override
    public void replaceFragment(Fragment fragmentToReplace, String titleOfFragment) {
        if (fragmentToReplace != null) {
            String backStackName=fragmentToReplace.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragmentToReplace);
            fragmentTransaction.addToBackStack(backStackName);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(titleOfFragment);

        }
    }
}
