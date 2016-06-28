package com.example.novo.educationtestsample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.Utils.Utils;
import com.example.novo.educationtestsample.activities.MainActivity;
import com.example.novo.educationtestsample.adapters.OptionListAdapter;
import com.example.novo.educationtestsample.adapters.QuestionListAdapter;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.interfaces.FragmentInteractionListener;
import com.example.novo.educationtestsample.models.Answer;
import com.example.novo.educationtestsample.models.PreviousQuestion;
import com.example.novo.educationtestsample.models.Question;
import com.example.novo.educationtestsample.models.QuestionListJSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
    Animation animFlipInForeward;
    Animation animFlipOutForeward;
    Animation animFlipInBackward;
    Animation animFlipOutBackward;
    QuestionListJSON questionListJSON;
    Button markForReview;
    Question currentQuestion;
    TextView countDownTimer;
    ImageView menu;
    PopupMenu popupMenu;
    TextView noOfQuesAttempted;


    public QuestionFragment() {
        // Required empty public constructor
    }

    public void startCountDownTimer(long time){

        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                countDownTimer.setText("Time left: "+String.valueOf(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))+" mins");
            }

            public void onFinish() {
                countDownTimer.setText("Finish!");
            }
        }.start();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionList = new ArrayList<>();
        questionList =new ArrayList<>();
        questionListJSON=QuestionListJSON.getInstance();
        questionList =questionListJSON.getQuestionList();
        try {
            currentQuestion= (Question) questionList.get(questionListJSON.getCurrentQuestion()).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        optionList= Arrays.asList(currentQuestion.getAnswer_array());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_question, container, false);
        markForReview=(Button)root.findViewById(R.id.btn_mark_for_review);
        countDownTimer=(TextView)root.findViewById(R.id.tv_timer);
        markForReview.setOnClickListener(this);
        questionImage=(ImageView)root.findViewById(R.id.ivQuestion);
        questionText=(TextView)root.findViewById(R.id.tvQuestionText);
        questionMarks=(TextView)root.findViewById(R.id.tvQuestionMarks);
        menu = (ImageView) root.findViewById(R.id.iv_menu);
        menu.setOnClickListener(this);
        noOfQuesAttempted=(TextView)root.findViewById(R.id.noofattemptedquestions);
        inflateQuestionData(root);
        flipper = (ViewFlipper) root.findViewById(R.id.flipper);
        animFlipInForeward = AnimationUtils.loadAnimation(getActivity(), R.anim.flipin);
        animFlipOutForeward = AnimationUtils.loadAnimation(getActivity(), R.anim.flipout);
        animFlipInBackward = AnimationUtils.loadAnimation(getActivity(), R.anim.flipin_reverse);
        animFlipOutBackward = AnimationUtils.loadAnimation(getActivity(), R.anim.flipout_reverse);
        handlingSwipe(root);

        inflateQuestionList(root);
        inflateOptionList(root);
        startCountDownTimer(Utils.changeTimeIntoMilliseconds(Integer.parseInt(questionListJSON.getTestDuration())));
        countDownTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showNotification(getActivity(),"Take Test","New math test uploaded");
            }
        });
        return root;
    }

    public void handlingSwipe(View view) {
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
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
    }

    private void inflateQuestionData(View root) {
        // questionImage.setImageDrawable();


        questionText.setText(Html.fromHtml(currentQuestion.getQuestion_title()));
        questionMarks.setText(String.valueOf(currentQuestion.getQuestion_marks()));
        noOfQuesAttempted.setText(String.valueOf(questionListJSON.getNoOfQuesAttempted()));
    }

    private void inflateQuestionList(View root) {
        questionListLinearLayoutManager = new LinearLayoutManager(getActivity());
        questionListLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        questionRecyclerView =(RecyclerView)root.findViewById(R.id.rvQuestionList);
        questionListAdapter=new QuestionListAdapter(getActivity(),new ClickListener() {
            @Override
            public void onClick(int position) {
                onSave();
                questionListJSON.setCurrentQuestion(position);
                resetData();
            }

            @Override
            public void onClick(int position, RecyclerView.ViewHolder v) {
                onSave();
                questionListJSON.setCurrentQuestion(position);
                resetData();
            }

            @Override
            public void onLongClick(int position) {

            }

            @Override
            public void onNoOfAttemptedQuesChanged(int noOfAttemptedQuesChanged) {

            }
        });
        questionRecyclerView.setAdapter(questionListAdapter);
        questionRecyclerView.setLayoutManager(questionListLinearLayoutManager);

    }

    private void inflateOptionList(View root) {
        optionRecyclerView =(RecyclerView)root.findViewById(R.id.optionList);
        optionListAdapter=new OptionListAdapter(getActivity(), currentQuestion, new ClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onClick(int position, RecyclerView.ViewHolder v) {

            }

            @Override
            public void onLongClick(int position) {

            }

            @Override
            public void onNoOfAttemptedQuesChanged(int noOfAttemptedQuesChanged) {
            noOfQuesAttempted.setText(String.valueOf(noOfAttemptedQuesChanged));
            }
        },QuestionFragment.this);
        optionRecyclerView.setAdapter(optionListAdapter);
        optionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onStart() {
        super.onStart();
//        ((MainActivity)getActivity()).drawerFragment.setMenuVisibility(false);
        ((MainActivity)getActivity()).mToolbar.setVisibility(View.GONE);
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
        onSave();
        if(questionListJSON.getCurrentQuestion()>0) {
            questionListJSON.setCurrentQuestion(questionListJSON.getCurrentQuestion() - 1);
            resetData();
            flipper.setInAnimation(animFlipInBackward);
            flipper.setOutAnimation(animFlipOutBackward);
            flipper.showPrevious();
        }
    }

    private void SwipeLeft(){
        onSave();
        if(questionListJSON.getCurrentQuestion()+1< questionListJSON.getQuestionList().size()) {
            questionListJSON.setCurrentQuestion(questionListJSON.getCurrentQuestion() + 1);
            resetData();
            flipper.setInAnimation(animFlipInForeward);
            flipper.setOutAnimation(animFlipOutForeward);
            flipper.showNext();
        }
    }
    void resetData(){
        try {
            currentQuestion = (Question) questionList.get(questionListJSON.getCurrentQuestion()).clone();
            // questionImage.setImageDrawable();
            noOfQuesAttempted.setText(String.valueOf(questionListJSON.getNoOfQuesAttempted()));
            questionText.setText(Html.fromHtml(currentQuestion.getQuestion_title()));
            questionMarks.setText(String.valueOf(currentQuestion.getQuestion_marks()));
            optionListAdapter.currentQuestion = currentQuestion;
            optionListAdapter.data= Arrays.asList(currentQuestion.getAnswer_array());
            optionListAdapter.notifyDataSetChanged();
            questionListAdapter.notifyDataSetChanged();
            questionRecyclerView.smoothScrollToPosition(questionListJSON.getCurrentQuestion());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mark_for_review:
                if(questionListJSON.getQuestionList().get(questionListJSON.getCurrentQuestion()).getIsMarkedForReview()){
                    questionListJSON.getQuestionList().get(questionListJSON.getCurrentQuestion()).setIsMarkedForReview(false);
                    currentQuestion.setIsMarkedForReview(false);
                    Toast.makeText(getActivity(),"UnMarked for review",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"Marked for review",Toast.LENGTH_SHORT).show();
                    questionListJSON.getQuestionList().get(questionListJSON.getCurrentQuestion()).setIsMarkedForReview(true);
                    currentQuestion.setIsMarkedForReview(true);
                }
                break;
            case R.id.iv_menu:
                popupMenu = new PopupMenu(getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.custom_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.test:
                                return true;

                            case R.id.forum:
                                return true;

                            case R.id.results:
                                return true;

                            default:
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                break;

        }

    }
    public void onSave(){
        questionListJSON.getQuestionList().set(questionListJSON.getCurrentQuestion(), currentQuestion);
    }

    @Override
    public void onStop() {
        super.onStop();
        //((MainActivity)getActivity()).drawerFragment.setMenuVisibility(true);
        ((MainActivity)getActivity()).mToolbar.setVisibility(View.VISIBLE);
    }

}
