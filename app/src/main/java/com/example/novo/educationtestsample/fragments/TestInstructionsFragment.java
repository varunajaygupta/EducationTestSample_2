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
import com.example.novo.educationtestsample.Utils.Utils;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;
import com.example.novo.educationtestsample.models.Question;
import com.example.novo.educationtestsample.models.QuestionListJSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;


public class TestInstructionsFragment extends Fragment implements View.OnClickListener{

    private FragmentInteractionListener mListener;
    Button startTest;



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
//        MyAsyncTask myAsyncTask=new MyAsyncTask("", "", new ResponseCallback() {
//            @Override
//            public void onResult(String Response) {
////                Gson gson= new Gson();
////                testJson=gson.fromJson(Response,Test.class);
//                mListener.replaceFragment(new QuestionFragment(),"Question");
//
//            }
//        });
        Gson gson= new Gson();
        Type collectionType = new TypeToken<Collection<Question>>(){}.getType();
        Collection<Question> questionList = gson.fromJson(Utils.loadJSONfromAssests(getActivity(), "dummyJSON.json"), collectionType);
        QuestionListJSON.getInstance().setQuestionList((List)questionList);
        Log.e("TAG",((List) questionList).get(0).toString());
        mListener.replaceFragment(new QuestionFragment(),"Question");

    }



}
