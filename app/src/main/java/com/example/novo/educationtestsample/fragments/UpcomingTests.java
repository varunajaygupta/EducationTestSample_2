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

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.Utils.AppInfo;
import com.example.novo.educationtestsample.Utils.ConstURL;
import com.example.novo.educationtestsample.Utils.GetHitAsyncTask;
import com.example.novo.educationtestsample.Utils.SQLQueryUtils;
import com.example.novo.educationtestsample.adapters.TestAdapter;
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


public class UpcomingTests extends Fragment {

    FragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private TestAdapter testAdapter;
    public static List<Test> testList = new ArrayList<>();
    ProgressDialog progress;
    QuestionListJSON questionListJSON;
    private static final String TAG = "UpcomingTests";
    static int expandedItemPostion = -1;
    private RecyclerView.ViewHolder lastViewHolder=null;


    public UpcomingTests() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void getTopicsList(final View view) {
        startLoader();
        String requestParams = SQLQueryUtils.FETCH_TEST_DATA_QUERY;
        requestParams = requestParams.replaceAll(SQLQueryUtils.COACHING_ID, AppInfo.getCoachingId(getActivity()));
        requestParams = requestParams.replaceAll(SQLQueryUtils.BATCH_ID, AppInfo.getBatchId(getActivity()));
        requestParams = requestParams.replaceAll(SQLQueryUtils.TEACHER_ID, AppInfo.getTeacherId(getActivity()));
        requestParams = requestParams.replaceAll(SQLQueryUtils.CURRENT_DATE, String.valueOf(0));

        GetHitAsyncTask getHitAsyncTask = new GetHitAsyncTask(ConstURL.FETCH_TEST_DATA, requestParams, new ResponseCallback() {
            @Override
            public void onResult(String response) {
                stopLoader();
                Type listType = new TypeToken<ArrayList<Test>>() {
                }.getType();
                testList = new Gson().fromJson(response, listType);
                Log.e(TAG, "onResult: " + testList.toString());
                testAdapter.setData(testList);
                testAdapter.notifyDataSetChanged();
            }
        });

        getHitAsyncTask.execute();


    }

    private void startLoader() {
        progress = ProgressDialog.show(getContext(), "Loading",
                "Loading tests ", true);
    }

    private void stopLoader() {
        progress.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_available_tests, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.topicList);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).color(Color.WHITE)
                .sizeResId(R.dimen.divider).build());
        testAdapter = new TestAdapter(getActivity(), testList, new ClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onClick(int position, RecyclerView.ViewHolder viewHolder) {
                // questionListJSON.setTestDuration(testList.get(position).getDuration_mins());
                if(lastViewHolder!=null){
                    ((TestAdapter.TestViewHolder)lastViewHolder).description.setVisibility(View.GONE);
                }
                ((TestAdapter.TestViewHolder)viewHolder).description.setVisibility(View.VISIBLE);
                Log.e(TAG, "Position: "+ String.valueOf(expandedItemPostion));
                lastViewHolder=viewHolder;
//                questionListJSON.setTestId(testList.get(position).getTest_id());
//                mListener.replaceFragment(new TestInstructionsFragment(), "Instructions");
            }

            @Override
            public void onLongClick(int position) {

            }

            @Override
            public void onNoOfAttemptedQuesChanged(int noOfAttemptedQuesChanged) {

            }
        });
        recyclerView.setAdapter(testAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getTopicsList(view);
        return view;
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
