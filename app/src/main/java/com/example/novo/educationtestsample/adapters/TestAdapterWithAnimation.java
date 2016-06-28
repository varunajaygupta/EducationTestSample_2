package com.example.novo.educationtestsample.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.ViewHolders.TestChildViewHolder;
import com.example.novo.educationtestsample.ViewHolders.TestParentViewHolder;
import com.example.novo.educationtestsample.fragments.TestInstructionsFragment;
import com.example.novo.educationtestsample.interfaces.ClickListener;
import com.example.novo.educationtestsample.models.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Varun on 6/13/2016.
 */
public class TestAdapterWithAnimation extends ExpandableRecyclerAdapter<TestParentViewHolder,TestChildViewHolder> {


    private LayoutInflater mInflater;
    private Context context;
    private static final String TAG = "TestAdapterWithAnimation";
    private ClickListener clickListener;

    public TestAdapterWithAnimation(Context context, List<ParentListItem> parentItemList,ClickListener clickListener) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
        this.context=context;
        this.clickListener=clickListener;

    }

    @Override
    public TestParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.test_list_row_parent_layout, viewGroup, false);
        return new TestParentViewHolder(view);
    }

    @Override
    public TestChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.test_list_row_child_layout, viewGroup, false);
        return new TestChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(TestParentViewHolder testParentViewHolder, int position, ParentListItem parentListItem) {
        testParentViewHolder.title.setText(((Test) parentListItem).getTest_id());

    }


    @Override
    public void onBindChildViewHolder(TestChildViewHolder testChildViewHolder, final int position, Object object) {
        testChildViewHolder.secondaryTitle.setText(((Test) object).getTest_id());
        testChildViewHolder.duration.setText(context.getString(R.string.duration)+ ((Test) object).getDuration_seconds());
        testChildViewHolder.testID.setText("("+((Test) object).getTest_id()+")");
        testChildViewHolder.marks.setText(context.getString(R.string.marks)+((Test) object).getTotal_marks());
        testChildViewHolder.testOpenWindow.setText(getOpenWindow(object));
        testChildViewHolder.startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            clickListener.onClick(position);
            }
        });

    }

    private String getOpenWindow(Object object) {
        DateFormat orignalDateFormat= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateFormat tagetDateFormat= new SimpleDateFormat("dd/MMM/yyyy E hh:mm ");

        Date startDate= null;
        Date endDate=null;
        try {
            startDate = orignalDateFormat.parse(((Test) object).getWindow_start());
            endDate= orignalDateFormat.parse(((Test) object).getWindow_stop());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return context.getString(R.string.testWindow)+" "+tagetDateFormat.format(startDate)+"  --  "+tagetDateFormat.format(endDate);
    }
}
