package com.example.novo.educationtestsample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import com.example.novo.educationtestsample.R;
import com.example.novo.educationtestsample.ViewHolders.TestChildViewHolder;
import com.example.novo.educationtestsample.ViewHolders.TestParentViewHolder;
import com.example.novo.educationtestsample.models.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by Varun on 6/13/2016.
 */
public class TestAdapterWithAnimation extends ExpandableRecyclerAdapter<TestParentViewHolder,TestChildViewHolder> {


    private LayoutInflater mInflater;


    public TestAdapterWithAnimation(Context context, List<ParentListItem> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
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
    public void onBindChildViewHolder(TestChildViewHolder testChildViewHolder, int i, Object o) {
        testChildViewHolder.date.setText(((Test) o).getTest_id());
    }
}
