package com.example.novo.educationtestsample.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.novo.educationtestsample.adapters.TestAdapter;

/**
 * Created by Varun Ajay Gupta on 28/2/16.
 */
public interface ClickListener {
  void onClick(int position);
  void onClick(int position, RecyclerView.ViewHolder v);
  void onLongClick(int position);
  void onNoOfAttemptedQuesChanged(int noOfAttemptedQuesChanged);
}