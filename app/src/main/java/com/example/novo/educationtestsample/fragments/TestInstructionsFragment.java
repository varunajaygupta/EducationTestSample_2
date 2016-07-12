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
import com.example.novo.educationtestsample.Utils.AppInfo;
import com.example.novo.educationtestsample.Utils.ConstURL;
import com.example.novo.educationtestsample.Utils.GetHitAsyncTask;
import com.example.novo.educationtestsample.Utils.SQLQueryUtils;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;
import com.example.novo.educationtestsample.interfaces.ResponseCallback;
import com.example.novo.educationtestsample.models.Answer;
import com.example.novo.educationtestsample.models.Question;
import com.example.novo.educationtestsample.models.QuestionListJSON;
import com.example.novo.educationtestsample.models.SubmitAnswer;
import com.example.novo.educationtestsample.models.SubmitTestData;
import com.example.novo.educationtestsample.models.Test;
import com.example.novo.educationtestsample.models.TestInternalData;
import com.example.novo.educationtestsample.models.TestStatusYet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class TestInstructionsFragment extends Fragment implements View.OnClickListener{

    private FragmentInteractionListener mListener;
    Button startTest;
    Test test;
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
        fetchQuestions();

        break;
    }
    }

    private void fetchQuestions() {
        String requestParams = SQLQueryUtils.FETCH_PARTICULAR_TEST;
        requestParams = requestParams.replaceAll(SQLQueryUtils.TEST_ID,test.getTest_id());

        GetHitAsyncTask getHitAsyncTask= new GetHitAsyncTask(ConstURL.FETCH_TEST_DATA,requestParams, new ResponseCallback() {
            @Override
            public void onResult(String response) {
                Log.e(TAG, response );
                Type listType = new TypeToken<ArrayList<TestInternalData>>() {
                }.getType();
                List<TestInternalData> testInternalData = new Gson().fromJson(response, listType);
                populateQuestionListJSONFromTestData(testInternalData.get(0));
                fetchTestStatusYet();
                Log.e("TAG",(Arrays.asList(testInternalData.get(0).getQuestions()).get(0).toString()));

            }
        });

        getHitAsyncTask.execute();

    }

    private void fetchTestStatusYet() {
        String requestParams = SQLQueryUtils.FETCH_TEST_STATUS_YET;
        requestParams = requestParams.replaceAll(SQLQueryUtils.TEST_ID,test.getTest_id());
        requestParams = requestParams.replaceAll(SQLQueryUtils.STUDENT_ID, AppInfo.getUserId(getActivity()));
        GetHitAsyncTask getHitAsyncTask= new GetHitAsyncTask(ConstURL.FETCH_TEST_STATUS_YET,requestParams, new ResponseCallback() {
            @Override
            public void onResult(String response) {
                Log.e(TAG, response );
                if(response!=null && !response.equalsIgnoreCase("") &&response.equalsIgnoreCase("[]") ) {
                    Type listType = new TypeToken<ArrayList<TestStatusYet>>() {
                    }.getType();
                    List<TestStatusYet> testStatusYet = new Gson().fromJson(response, listType);
                    populateQuestionListJSONFromTestStatusYet(testStatusYet.get(0));
                }
                mListener.replaceFragment(new QuestionFragment(),"PreviousQuestion");


            }
        });

        getHitAsyncTask.execute();


    }

    private void populateQuestionListJSONFromTestStatusYet(TestStatusYet testStatusYet) {

        QuestionListJSON.getInstance().setNoOfQuesAttempted(Integer.parseInt(testStatusYet.getAttempted_questions()));
        QuestionListJSON.getInstance().setCurrentQuestion(Integer.parseInt(testStatusYet.getCurrent_question()));
        QuestionListJSON.getInstance().setTestId(testStatusYet.getTest_id());
        QuestionListJSON.getInstance().setTestDuration(testStatusYet.getRemaining_time());
        Iterator<Question> questionIterator= QuestionListJSON.getInstance().getQuestionList().iterator();
        Iterator<SubmitTestData> submitTestDataIterator=testStatusYet.getTestresponse().iterator();
        while (questionIterator.hasNext()&&submitTestDataIterator.hasNext()){
           // questionIterator.next().setAnswer_key_marked(submitTestDataIterator.next().getAnswer_key());
            Question question= questionIterator.next();
            SubmitTestData submitTestData=submitTestDataIterator.next();
            question.setAnswer_state(submitTestData.getAnswer_state());
            Iterator<Answer> answerIterator= Arrays.asList((question).getAnswer_array()).iterator();
            Iterator<SubmitAnswer> submitAnswerIterator= (submitTestData.getAnswer_array()).iterator();
            while (answerIterator.hasNext()&& submitAnswerIterator.hasNext()){
            answerIterator.next().setAnswer_marked(submitAnswerIterator.next().getAnswer_marked());
            }
        }
    }

    private void populateQuestionListJSONFromTestData(TestInternalData testInternalData) {
        QuestionListJSON.getInstance().setQuestionList(Arrays.asList(testInternalData.getQuestions()));
        QuestionListJSON.getInstance().setTestDuration(testInternalData.getDuration_seconds());
        QuestionListJSON.getInstance().setTestId(testInternalData.getTest_id());
        fillLastCheckedCheckboxPos(QuestionListJSON.getInstance().getQuestionList());
    }

    private void fillLastCheckedCheckboxPos(List<Question> questionList) {
        int checkedOptionPos;
        for(Question question:questionList){
            checkedOptionPos=0;
            for(Answer answer:question.getAnswer_array()){
               if(answer.getAnswer_marked()){
                   question.setLastCheckedCheckboxPos(checkedOptionPos);
                   break;
               }
               checkedOptionPos++;
            }
        }
    }


}
