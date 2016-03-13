package com.example.novo.educationtestsample.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.models.QuestionListJSON;

/**
 * Created by Varun Ajay Gupta on 2/3/16.
 */
public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;
    QuestionListJSON questionListJSON=QuestionListJSON.getInstance();


    public QuestionListAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.clickListener=clickListener;
    }
    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.questions_list_row,parent,false);
        QuestionViewHolder questionViewHolder= new QuestionViewHolder(view);
        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, final int position) {
        holder.questionNumber.setText(String.valueOf(position + 1));
        holder.questionNumber.setTextColor(Color.WHITE);
        holder.questionNumber.setTextSize(15);
        holder.questionNumber.setBackgroundResource(0);
        if(questionListJSON.getQuestionList().get(position).getIsMarkedForReview() && questionListJSON.getCurrentQuestion()==(position)){
            holder.questionNumber.setTextColor(Color.RED);
            holder.questionNumber.setTextSize(20);
            holder.questionNumber.setBackgroundResource(R.drawable.background_white);
        }
        else if (questionListJSON.getQuestionList().get(position).getIsMarkedForReview()){
            holder.questionNumber.setTextColor(Color.RED);
        }
        else if(questionListJSON.getCurrentQuestion()==(position)){
            holder.questionNumber.setTextColor(Color.BLACK);
            holder.questionNumber.setTextSize(20);
            holder.questionNumber.setBackgroundResource(R.drawable.background_white);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionListJSON.getQuestionList().size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView questionNumber;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionNumber =(TextView)itemView.findViewById(R.id.tvQuestionNum);
        }
    }
}
