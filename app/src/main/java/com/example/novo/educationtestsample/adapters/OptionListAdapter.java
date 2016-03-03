package com.example.novo.educationtestsample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.models.NavDrawerItem;
import com.example.novo.educationtestsample.models.Option;

import java.util.Collections;
import java.util.List;

/**
 * Created by Varun Ajay Gupta on 2/3/16.
 */
public class OptionListAdapter extends RecyclerView.Adapter<OptionListAdapter.OptionViewHolder> {
    List<Option> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;


    public OptionListAdapter(Context context, List<Option> data, ClickListener clickListener) {
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
        holder.title.setText(data.get(position).getOptionText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);


            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        public OptionViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tvOptionNum);
        }
    }
}