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
import com.example.novo.educationtestsample.models.TestItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Varun Ajay Gupta on 1/3/16.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
   public List<TestItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;

    public List<TestItem> getData() {
        return data;
    }

    public void setData(List<TestItem> data) {
        this.data = data;
    }

    public TestAdapter(Context context, List<TestItem> data, ClickListener clickListener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.clickListener=clickListener;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public TestAdapter.TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.test_list_row,parent,false);
        TestViewHolder testViewHolder= new TestViewHolder(view);
        return testViewHolder;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getRowTitle());
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

    public class TestViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        public TestViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.topic_title);
        }
    }
}
