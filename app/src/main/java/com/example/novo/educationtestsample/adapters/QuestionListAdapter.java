package com.example.novo.educationtestsample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.models.Question;

import java.util.Collections;
import java.util.List;

/**
 * Created by Varun Ajay Gupta on 2/3/16.
 */
public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {

    public List<Question> questionList= Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;


    public QuestionListAdapter(Context context, List<Question> data, ClickListener clickListener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.questionList = data;
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
        holder.questionNumber.setText(questionList.get(position).getQuestionNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView questionNumber;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionNumber =(TextView)itemView.findViewById(R.id.tvQuestionNum);
        }
    }
}
