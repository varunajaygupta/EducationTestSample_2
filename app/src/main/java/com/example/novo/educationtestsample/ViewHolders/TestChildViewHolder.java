package com.example.novo.educationtestsample.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.novo.educationtestsample.R;

/**
 * Created by Varun on 6/13/2016.
 */
public class TestChildViewHolder extends ChildViewHolder {

    public TextView secondaryTitle;
    public TextView marks;
    public TextView duration;
    public TextView testOpenWindow;
    public TextView testID;
    public Button startTest;

    public TestChildViewHolder(View itemView) {
        super(itemView);
        secondaryTitle =(TextView)itemView.findViewById(R.id.tv_test_title_secondary);
        marks =(TextView)itemView.findViewById(R.id.tv_marks);
        duration =(TextView)itemView.findViewById(R.id.tv_duration);
        testOpenWindow =(TextView)itemView.findViewById(R.id.tv_test_open_window);
        testID =(TextView)itemView.findViewById(R.id.tv_test_id);
        startTest=(Button)itemView.findViewById(R.id.startTest);
    }
}
