package com.example.novo.educationtestsample.interfaces;

import android.view.View;

/**
 * Created by Varun Ajay Gupta on 28/2/16.
 */
public interface ClickListener {

  void onClick(int position);
  void onLongClick(int position);
  void onNoOfAttemptedQuesChanged(int noOfAttemptedQuesChanged);
}