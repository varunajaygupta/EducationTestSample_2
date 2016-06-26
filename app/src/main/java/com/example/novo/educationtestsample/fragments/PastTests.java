package com.example.novo.educationtestsample.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.Utils.AppInfo;
import com.example.novo.educationtestsample.Utils.ConstURL;
import com.example.novo.educationtestsample.Utils.GetHitAsyncTask;
import com.example.novo.educationtestsample.Utils.SQLQueryUtils;
import com.example.novo.educationtestsample.adapters.TestAdapter;
import com.example.novo.educationtestsample.adapters.TestAdapterWithAnimation;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;
import com.example.novo.educationtestsample.interfaces.ResponseCallback;
import com.example.novo.educationtestsample.models.QuestionListJSON;
import com.example.novo.educationtestsample.models.Test;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PastTests extends Fragment {

    FragmentInteractionListener mListener;
    private RecyclerView recyclerView;
   // private TestAdapter testAdapter;
    public static List<Test> testList = new ArrayList<>();
    ProgressDialog progress;
    QuestionListJSON questionListJSON;
    private static final String TAG = "PastTests";
    static int expandedItemPostion = -1;
    private RecyclerView.ViewHolder lastViewHolder=null;

    // fields for new Adapter
    TestAdapterWithAnimation testAdapterWithAnimation;
    List<ParentListItem> adapterTestList;

    public PastTests() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_missed_tests, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.topicList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).color(Color.WHITE)
                .sizeResId(R.dimen.divider).build());
        adapterTestList= new ArrayList<>();
        testAdapterWithAnimation = new TestAdapterWithAnimation(getActivity(), adapterTestList);
        recyclerView.setAdapter(testAdapterWithAnimation);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


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

    private void getTopicsList() {
        startLoader();
        String requestParams = SQLQueryUtils.FETCH_TEST_LIST_QUERY;
        requestParams = requestParams.replaceAll(SQLQueryUtils.COACHING_ID, AppInfo.getCoachingId(getActivity()));
        requestParams = requestParams.replaceAll(SQLQueryUtils.BATCH_ID, AppInfo.getBatchId(getActivity()));
        requestParams = requestParams.replaceAll(SQLQueryUtils.TEACHER_ID, AppInfo.getTeacherId(getActivity()));
        requestParams = requestParams.replaceAll(SQLQueryUtils.CURRENT_DATE, String.valueOf(System.currentTimeMillis()));
        requestParams = requestParams.replaceAll(SQLQueryUtils.OPERATOR_VALUE,SQLQueryUtils.LOWER_THAN );

        GetHitAsyncTask getHitAsyncTask = new GetHitAsyncTask(ConstURL.FETCH_TEST_LIST, requestParams, new ResponseCallback() {
            @Override
            public void onResult(String response) {
                stopLoader();
                Type listType = new TypeToken<ArrayList<Test>>() {
                }.getType();
                testList = new Gson().fromJson(response, listType);
                Log.e(TAG, "onResult: " + testList.toString());
                testAdapterWithAnimation = new TestAdapterWithAnimation(getActivity(), getAdapterTestList());
                recyclerView.setAdapter(testAdapterWithAnimation);
             }
        });

        getHitAsyncTask.execute();


    }


    private List<ParentListItem> getAdapterTestList() {
        adapterTestList= new ArrayList<>();
        for(Test test:testList){
        adapterTestList.add(test);
        }
        return adapterTestList;
    }

    private void startLoader() {
        progress = ProgressDialog.show(getContext(), "Loading",
                "Loading tests ", true);
    }

    private void stopLoader() {
        progress.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        getTopicsList();
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
