package com.example.novo.educationtestsample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.models.Answer;
import com.example.novo.educationtestsample.models.Question;
import com.example.novo.educationtestsample.models.QuestionListJSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Varun Ajay Gupta on 2/3/16.
 */
public class OptionListAdapter extends RecyclerView.Adapter<OptionListAdapter.OptionViewHolder> {
    public List<Answer> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;
    private Question currentQuestion;
    private static CheckBox lastCheckedCheckBox = null;
    private static int lastCheckedPos = 0;


    public OptionListAdapter(Context context, Question currentQuestion, ClickListener clickListener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.currentQuestion = currentQuestion;
        this.clickListener = clickListener;
        this.data = this.currentQuestion.getAnswer_array();
    }


    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.option_layout, parent, false);
        OptionViewHolder optionViewHolder = new OptionViewHolder(view);
        return optionViewHolder;
    }

    @Override
    public void onBindViewHolder(final OptionViewHolder holder, final int position) {
        holder.optionNumber.setText(String.valueOf(position + 1 + "."));
        holder.optionText.setText(data.get(position).getAnswer_title());
        //holder.optionImage.setImg(data.get(position).getAnswer_title());
        holder.optionCheckBox.setChecked(data.get(position).getAnswer_marked());
        holder.optionCheckBox.setTag(new Integer(position));

        holder.optionCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion.getQuestion_type().equalsIgnoreCase("multipleChoice")) {
                    clickListener.onClick(position);
                    if (holder.optionCheckBox.isChecked()) {
                        holder.optionCheckBox.setChecked(false);
                        data.get(position).setAnswer_marked(false);
                    } else {
                        holder.optionCheckBox.setChecked(true);
                        data.get(position).setAnswer_marked(true);
                    }
                    updateNoOfQuesAttemptedInMultipleChoiceMode(currentQuestion,false);
                } else {
                    updateNoOfQuesAttemptedInSingleChoiceMode();
                    // Storing the last checked checkbox and we first make it unchecked and then make the new one checked.
                    CheckBox checkBox = (CheckBox) v;
                    Integer pos = ((Integer) checkBox.getTag()).intValue();
                    if (checkBox.isChecked()) {
                        if (lastCheckedCheckBox != null) {
                            lastCheckedCheckBox.setChecked(false);
                            data.get(lastCheckedPos).setAnswer_marked(false);
                        }
                        //((CheckBox) v).setChecked(true);
                        data.get(position).setAnswer_marked(true);
                        lastCheckedCheckBox = checkBox;
                        lastCheckedPos = pos;

                    } else {
                        lastCheckedCheckBox = null;
                        lastCheckedPos = 0;
                        data.get(position).setAnswer_marked(false);
                    }

                }
            }
        });
    }

    private void updateNoOfQuesAttemptedInMultipleChoiceMode(Question currentQuestion,Boolean marked) {
    int noOfQuesAttempted = QuestionListJSON.getInstance().getNoOfQuesAttempted();
    if(marked && !currentQuestion.getIsAttempted()){
        QuestionListJSON.getInstance().setNoOfQuesAttempted(noOfQuesAttempted + 1);
    }else if(!marked){
        Boolean unMarked=false;
        for(Answer answer: currentQuestion.getAnswer_array())
        {
           if(answer.getAnswer_marked()){
               unMarked=true;
           }
        }
        if(!unMarked){
            if (noOfQuesAttempted > 0) {
                QuestionListJSON.getInstance().setNoOfQuesAttempted(noOfQuesAttempted - 1);
            }
        }
    }

    }

    private void updateNoOfQuesAttemptedInSingleChoiceMode() {
        int noOfQuesAttempted = QuestionListJSON.getInstance().getNoOfQuesAttempted();
        if (currentQuestion.getIsAttempted()) {
            currentQuestion.setIsAttempted(false);
            if (noOfQuesAttempted > 0) {
                QuestionListJSON.getInstance().setNoOfQuesAttempted(noOfQuesAttempted - 1);
            }
        } else {
            QuestionListJSON.getInstance().setNoOfQuesAttempted(noOfQuesAttempted + 1);
        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder {

        TextView optionText;
        TextView optionNumber;
        ImageView optionImage;
        CheckBox optionCheckBox;

        public OptionViewHolder(View itemView) {
            super(itemView);
            optionText = (TextView) itemView.findViewById(R.id.tvOptionText);
            optionNumber = (TextView) itemView.findViewById(R.id.tvOptionNum);
            optionImage = (ImageView) itemView.findViewById(R.id.ivOption);
            optionCheckBox = (CheckBox) itemView.findViewById(R.id.chOption);
        }
    }
}
