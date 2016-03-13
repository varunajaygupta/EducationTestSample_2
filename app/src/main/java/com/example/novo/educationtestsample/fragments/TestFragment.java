package com.example.novo.educationtestsample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.activities.MainActivity;
import com.example.novo.educationtestsample.adapters.ViewPagerAdapter;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestFragment.fragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TestFragment extends Fragment {


    private TabLayout tabLayout;
    private FragmentInteractionListener mListener;
    private ViewPager viewPager;


    public TestFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  ((MainActivity)getActivity()).drawerFragment.setMenuVisibility(true);
        ((MainActivity)getActivity()).mToolbar.setVisibility(View.VISIBLE);
        View root= inflater.inflate(R.layout.fragment_test, container, false);
        viewPager = (ViewPager)root. findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)root. findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(getString(R.string.available_tests), new AvailableTests());
        viewPagerAdapter.addFragment(getString(R.string.missed_tests), new MissedTests());
        viewPagerAdapter.addFragment(getString(R.string.pending_tests), new PendingTests());
        viewPager.setAdapter(viewPagerAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
