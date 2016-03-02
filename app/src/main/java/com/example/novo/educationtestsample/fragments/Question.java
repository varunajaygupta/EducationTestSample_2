package com.example.novo.educationtestsample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.activities.MainActivity;
import com.example.novo.educationtestsample.adapters.OptionListAdapter;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;
import com.example.novo.educationtestsample.models.Option;

import java.util.ArrayList;
import java.util.List;


public class Question extends Fragment {

    FragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    ImageView questionImage;
    OptionListAdapter optionListAdapter;
    List<Option> optionList;

    public Question() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionList = new ArrayList<Option>();
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
        optionList.add(new Option("Test 1"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_question, container, false);
        ((MainActivity)getActivity()).drawerFragment.setMenuVisibility(false);
        ((MainActivity)getActivity()).mToolbar.setVisibility(View.GONE);
        recyclerView=(RecyclerView)root.findViewById(R.id.optionList);
        optionListAdapter=new OptionListAdapter(getActivity(), optionList, new ClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onLongClick(int position) {

            }
        });
        recyclerView.setAdapter(optionListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
       return root;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            mListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
