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
import com.example.novo.educationtestsample.adapters.QuestionListAdapter;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;
import com.example.novo.educationtestsample.models.Option;
import com.example.novo.educationtestsample.models.Question;

import java.util.ArrayList;
import java.util.List;


public class QuestionFragment extends Fragment {

    FragmentInteractionListener mListener;
    private RecyclerView optionRecyclerView;
    private RecyclerView questionRecyclerView;
    ImageView questionImage;
    OptionListAdapter optionListAdapter;
    QuestionListAdapter questionListAdapter;
    List<Option> optionList;
    List<Question> questionList;
    LinearLayoutManager questionListLinearLayoutManager;
    public QuestionFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionList = new ArrayList<Option>();
        optionList.add(new Option("A"));
        optionList.add(new Option("A"));
        optionList.add(new Option("A"));
        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));
//        optionList.add(new Option("A"));

        questionList=new ArrayList<Question>();
        questionList.add(new Question("1"));
        questionList.add(new Question("2"));
        questionList.add(new Question("3"));
        questionList.add(new Question("4"));
        questionList.add(new Question("5"));
        questionList.add(new Question("6"));
        questionList.add(new Question("7"));
        questionList.add(new Question("8"));
        questionList.add(new Question("1"));
        questionList.add(new Question("2"));
        questionList.add(new Question("3"));
        questionList.add(new Question("4"));
        questionList.add(new Question("5"));
        questionList.add(new Question("6"));
        questionList.add(new Question("7"));
        questionList.add(new Question("8"));
        questionList.add(new Question("1"));
        questionList.add(new Question("2"));
        questionList.add(new Question("3"));
        questionList.add(new Question("4"));
        questionList.add(new Question("5"));
        questionList.add(new Question("6"));
        questionList.add(new Question("7"));
        questionList.add(new Question("8"));
        questionList.add(new Question("1"));
        questionList.add(new Question("2"));
        questionList.add(new Question("3"));
        questionList.add(new Question("4"));
        questionList.add(new Question("5"));
        questionList.add(new Question("6"));
        questionList.add(new Question("7"));
        questionList.add(new Question("8"));
        questionList.add(new Question("1"));
        questionList.add(new Question("2"));
        questionList.add(new Question("3"));
        questionList.add(new Question("4"));
        questionList.add(new Question("5"));
        questionList.add(new Question("6"));
        questionList.add(new Question("7"));
        questionList.add(new Question("8"));
        questionList.add(new Question("1"));
        questionList.add(new Question("2"));
        questionList.add(new Question("3"));
        questionList.add(new Question("4"));
        questionList.add(new Question("5"));
        questionList.add(new Question("6"));
        questionList.add(new Question("7"));
        questionList.add(new Question("8"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_question, container, false);
        inflateQuestionList(root);
        inflateOptionList(root);
        return root;
    }

    private void inflateQuestionList(View root) {
        questionListLinearLayoutManager = new LinearLayoutManager(getActivity());
        questionListLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        questionRecyclerView =(RecyclerView)root.findViewById(R.id.rvQuestionList);
        questionListAdapter=new QuestionListAdapter(getActivity(), questionList, new ClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onLongClick(int position) {

            }
        });
        questionRecyclerView.setAdapter(questionListAdapter);
        questionRecyclerView.setLayoutManager(questionListLinearLayoutManager);

    }

    private void inflateOptionList(View root) {
        ((MainActivity)getActivity()).drawerFragment.setMenuVisibility(false);
        ((MainActivity)getActivity()).mToolbar.setVisibility(View.GONE);
        optionRecyclerView =(RecyclerView)root.findViewById(R.id.optionList);
        optionListAdapter=new OptionListAdapter(getActivity(),optionList, new ClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onLongClick(int position) {

            }
        });
        optionRecyclerView.setAdapter(optionListAdapter);
        optionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
