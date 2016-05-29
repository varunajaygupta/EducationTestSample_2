package com.example.novo.educationtestsample.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.Utils.PostHitAsyncTask;
import com.example.novo.educationtestsample.Utils.Utils;
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
import java.util.Collection;
import java.util.List;


public class UpcomingTests extends Fragment {

    FragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private TestAdapter testAdapter;
    public static List<Test> testList;
    ProgressDialog progress;
    QuestionListJSON questionListJSON;


    public UpcomingTests() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTopicsList();
        testList= new ArrayList<Test>();
        Gson gson= new Gson();
        Type collectionType = new TypeToken<Collection<Test>>(){}.getType();
        testList = gson.fromJson(Utils.loadJSONfromAssests(getContext(), "Quiz.json"), collectionType);
        questionListJSON=QuestionListJSON.getInstance();


    }

    private void getTopicsList() {
        startLoader();
        PostHitAsyncTask myAsyncTask= new PostHitAsyncTask("", "", new ResponseCallback() {
            //TO DO

            @Override
            public void onResult(String Response) {
             //   testAdapter.setData("");
                testAdapter.notifyDataSetChanged();


            }
        });
         myAsyncTask.execute();

        testAdapter.setData(testList);
        testAdapter.notifyDataSetChanged();


    }

    private void startLoader() {
        progress = ProgressDialog.show(getContext(), "Loading",
                "Loading tests ", true);
    }
    private void stopLoader(){
        progress.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_available_tests, container, false);
         recyclerView=(RecyclerView)view.findViewById(R.id.topicList);
         recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).color(Color.WHITE)
                 .sizeResId(R.dimen.divider).build());
         testAdapter=new TestAdapter(getActivity(),testList, new ClickListener() {
            @Override
            public void onClick(int position) {
                questionListJSON.setTestDuration(testList.get(position).getDuration_mins());
                questionListJSON.setTestId(testList.get(position).getTest_id());
                mListener.replaceFragment(new TestInstructionsFragment(),"Instructions");
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
     //   getTopicsList();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
