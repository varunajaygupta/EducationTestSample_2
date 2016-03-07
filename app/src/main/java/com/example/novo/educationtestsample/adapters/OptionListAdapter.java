package com.example.novo.educationtestsample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.models.Answer;
import com.example.novo.educationtestsample.models.NavDrawerItem;
import com.example.novo.educationtestsample.models.Option;

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
    CheckBox checkBox;


    public OptionListAdapter(Context context, List<Answer> data, ClickListener clickListener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.clickListener=clickListener;
    }


    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.option_layout,parent,false);
        OptionViewHolder optionViewHolder= new OptionViewHolder(view);
        return optionViewHolder;
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, final int position) {
        holder.optionNumber.setText(String.valueOf(position+1+"."));
        holder.optionText.setText(data.get(position).getAnswer_title());
        //holder.optionImage.setIm(data.get(position).getAnswer_title());
        holder.optionCheckBox.setChecked(data.get(position).getAnswer_marked());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
                checkBox = (CheckBox) v.findViewById(R.id.chOption);
                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                }else{
                    checkBox.setChecked(true);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder{

        TextView optionText;
        TextView optionNumber;
        ImageView optionImage;
        CheckBox optionCheckBox;

        public OptionViewHolder(View itemView) {
            super(itemView);
            optionText=(TextView)itemView.findViewById(R.id.tvOptionText);
            optionNumber=(TextView)itemView.findViewById(R.id.tvOptionNum);
            optionImage=(ImageView)itemView.findViewById(R.id.ivOption);
            optionCheckBox=(CheckBox)itemView.findViewById(R.id.chOption);
        }
    }
}
