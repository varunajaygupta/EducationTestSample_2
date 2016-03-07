package com.example.novo.educationtestsample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.activities.MainActivity;
import com.example.novo.educationtestsample.adapters.OptionListAdapter;
import com.example.novo.educationtestsample.adapters.QuestionListAdapter;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;
import com.example.novo.educationtestsample.models.Answer;
import com.example.novo.educationtestsample.models.Option;
import com.example.novo.educationtestsample.models.Question;
import com.example.novo.educationtestsample.models.QuestionListJSON;

import java.util.ArrayList;
import java.util.List;


public class QuestionFragment extends Fragment implements View.OnClickListener {

    FragmentInteractionListener mListener;
    private RecyclerView optionRecyclerView;
    private RecyclerView questionRecyclerView;
    private  TextView tv;
    ViewFlipper flipper;
    ImageView questionImage;
    TextView  questionText;
    TextView  questionMarks;
    OptionListAdapter optionListAdapter;
    QuestionListAdapter questionListAdapter;
    List<Answer> optionList;
    List<Question> questionList;
    LinearLayoutManager questionListLinearLayoutManager;
    private ClickListener clickListener;
    Animation animFlipInForeward;
    Animation animFlipOutForeward;
    Animation animFlipInBackward;
    Animation animFlipOutBackward;
    QuestionListJSON questionListJSON;
    Button markForReview;
//    Button leftbutton,rightbutton;


    public QuestionFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionList = new ArrayList<>();
        questionList=new ArrayList<>();
        questionListJSON=QuestionListJSON.getInstance();
        questionList=questionListJSON.getQuestionList();
        optionList= questionListJSON.getQuestionList().get(questionListJSON.getCurrentQuestion()).getAnswer_array();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_question, container, false);
        //TODO remove later
       /* leftbutton=(Button)root.findViewById(R.id.leftbutton);
        rightbutton=(Button)root.findViewById(R.id.rightbutton);*/
        markForReview=(Button)root.findViewById(R.id.btn_mark_for_review);
        markForReview.setOnClickListener(this);
        /*leftbutton.setOnClickListener(this);
        rightbutton.setOnClickListener(this);*/
        questionImage=(ImageView)root.findViewById(R.id.ivQuestion);
        questionText=(TextView)root.findViewById(R.id.tvQuestionText);
        questionMarks=(TextView)root.findViewById(R.id.tvQuestionMarks);
        inflateQuestionData(root);
        tv = (TextView) root.findViewById(R.id.tvQuestionText);
        flipper = (ViewFlipper) root.findViewById(R.id.flipper);
        animFlipInForeward = AnimationUtils.loadAnimation(getActivity(), R.anim.flipin);
        animFlipOutForeward = AnimationUtils.loadAnimation(getActivity(), R.anim.flipout);
        animFlipInBackward = AnimationUtils.loadAnimation(getActivity(), R.anim.flipin_reverse);
        animFlipOutBackward = AnimationUtils.loadAnimation(getActivity(), R.anim.flipout_reverse);
        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        Log.i("Fling called", "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                SwipeLeft();
                                Log.i("Swiping right to left", "Right to Left");
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                SwipeRight();
                                Log.i("Swipping left to right", "Left to Right");
                                tv.setText(getString(R.string.questiondummytext));
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

        inflateQuestionList(root);
        inflateOptionList(root);
        return root;
    }

    private void inflateQuestionData(View root) {
       // questionImage.setImageDrawable();
        questionText.setText(questionList.get(questionListJSON.getCurrentQuestion()).getQuestion_title());
        questionMarks.setText(String.valueOf(questionList.get(questionListJSON.getCurrentQuestion()).getQuestion_marks()));
    }

    private void inflateQuestionList(View root) {
        questionListLinearLayoutManager = new LinearLayoutManager(getActivity());
        questionListLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        questionRecyclerView =(RecyclerView)root.findViewById(R.id.rvQuestionList);
        questionListAdapter=new QuestionListAdapter(getActivity(), questionList, new ClickListener() {
            @Override
            public void onClick(int position) {
            questionListJSON.setCurrentQuestion(position);
            resetData();
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


    private void SwipeRight(){
        if(questionListJSON.getCurrentQuestion()>0) {
            questionListJSON.setCurrentQuestion(questionListJSON.getCurrentQuestion() - 1);
            Log.e("AfterSwiping", String.valueOf(questionListJSON.getCurrentQuestion()));
            resetData();
        }
        flipper.setInAnimation(animFlipInBackward);
        flipper.setOutAnimation(animFlipOutBackward);
        flipper.showPrevious();
    }

    private void SwipeLeft(){
        if(questionListJSON.getCurrentQuestion()+1< questionListJSON.getQuestionList().size()) {
            questionListJSON.setCurrentQuestion(questionListJSON.getCurrentQuestion() + 1);
            Log.e("AfterSwiping", String.valueOf(questionListJSON.getCurrentQuestion()));
            resetData();
        }
        flipper.setInAnimation(animFlipInForeward);
        flipper.setOutAnimation(animFlipOutForeward);
        flipper.showNext();
    }
    void resetData(){
        // questionImage.setImageDrawable();
        questionText.setText(questionList.get(questionListJSON.getCurrentQuestion()).getQuestion_title());
        questionMarks.setText(String.valueOf(questionList.get(questionListJSON.getCurrentQuestion()).getQuestion_marks()));
       optionListAdapter.data.clear();
        optionListAdapter.data=questionListJSON.getQuestionList().get(questionListJSON.getCurrentQuestion()).getAnswer_array();
        optionListAdapter.notifyDataSetChanged();
        questionListAdapter.notifyDataSetChanged();

    }
    void swipingRight(){
        Log.e("CurrentQuestion",String.valueOf(questionListJSON.getCurrentQuestion()));
        if(questionListJSON.getCurrentQuestion()+1< questionListJSON.getQuestionList().size()) {
            questionListJSON.setCurrentQuestion(questionListJSON.getCurrentQuestion() + 1);
            Log.e("AfterSwiping", String.valueOf(questionListJSON.getCurrentQuestion()));
            resetData();
        }
    }
    void swipingLeft(){
        Log.e("CurrentQuestion",String.valueOf(questionListJSON.getCurrentQuestion()));
        if(questionListJSON.getCurrentQuestion()>0) {
        questionListJSON.setCurrentQuestion(questionListJSON.getCurrentQuestion() - 1);
        Log.e("AfterSwiping", String.valueOf(questionListJSON.getCurrentQuestion()));
         resetData();
    }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mark_for_review:
                 if(questionListJSON.getQuestionList().get(questionListJSON.getCurrentQuestion()).getIsMarkedForReview()){
                     questionListJSON.getQuestionList().get(questionListJSON.getCurrentQuestion()).setIsMarkedForReview(false);
                     Toast.makeText(getActivity(),"UnMarked for review",Toast.LENGTH_SHORT).show();
                 }else {
                     Toast.makeText(getActivity(),"Marked for review",Toast.LENGTH_SHORT).show();
                     questionListJSON.getQuestionList().get(questionListJSON.getCurrentQuestion()).setIsMarkedForReview(true);
                 }
            break;

        }
    }
}
