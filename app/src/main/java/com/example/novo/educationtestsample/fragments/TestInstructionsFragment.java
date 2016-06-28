package com.example.novo.educationtestsample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.Utils.GetHitAsyncTask;
import com.example.novo.educationtestsample.Utils.SQLQueryUtils;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;
import com.example.novo.educationtestsample.interfaces.ResponseCallback;
import com.example.novo.educationtestsample.models.QuestionListJSON;
import com.example.novo.educationtestsample.models.Test;
import com.example.novo.educationtestsample.models.TestInternalData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestInstructionsFragment extends Fragment implements View.OnClickListener{

    private FragmentInteractionListener mListener;
    Button startTest;
    Test test;
    private List<TestInternalData> testInternalData = new ArrayList<>();
    private static final String TAG = "TestInstructionsFragment";



    public TestInstructionsFragment() {
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
        Bundle args = getArguments();
        test = (Test) getArguments().getSerializable(
                getActivity().getString(R.string.test));
        View root= inflater.inflate(R.layout.test_instruction, container, false);
        startTest=(Button)root.findViewById(R.id.startTest);
        startTest.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.startTest:
        fetchQuestionsAndLaunchQuestionFragment();
        break;
    }
    }

    private void fetchQuestionsAndLaunchQuestionFragment() {
        String requestParams = SQLQueryUtils.FETCH_PARTICULAR_TEST;
        requestParams = requestParams.replaceAll(SQLQueryUtils.TEST_ID,test.getTest_id());

 //       Collection<PreviousQuestion> questionList = gson.fromJson(Utils.loadJSONfromAssests(getActivity(), "dummyJSON.json"), collectionType);

        GetHitAsyncTask getHitAsyncTask= new GetHitAsyncTask("http://studysolo.com/api/testdata?filter=",requestParams, new ResponseCallback() {
            @Override
            public void onResult(String response) {
                Log.e(TAG, response );
                Type listType = new TypeToken<ArrayList<TestInternalData>>() {
                }.getType();
                testInternalData = new Gson().fromJson(response, listType);
                populateQuestionListJSON(testInternalData.get(0));

        Log.e("TAG",(Arrays.asList(testInternalData.get(0).getQuestions()).get(0).toString()));
        mListener.replaceFragment(new QuestionFragment(),"PreviousQuestion");

            }
        });

        getHitAsyncTask.execute();
    }

    private void populateQuestionListJSON(TestInternalData testInternalData) {
        QuestionListJSON.getInstance().setQuestionList(Arrays.asList(testInternalData.getQuestions()));
        QuestionListJSON.getInstance().setTestDuration(testInternalData.getDuration_seconds());
        QuestionListJSON.getInstance().setTestId(testInternalData.getTest_id());

    }


}
