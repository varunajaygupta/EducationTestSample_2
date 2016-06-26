package com.example.novo.educationtestsample.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Test implements ParentListItem, Serializable {
    private String[] topics;

    private String title;

    private String duration_seconds;

    private String window_stop;

    private String window_start;

    private String test_id;

    private String total_marks;


    public Test(String[] topics, String title, String duration_seconds, String window_stop, String window_start, String test_id, String total_marks) {
        this.topics = topics;
        this.title = title;
        this.duration_seconds = duration_seconds;
        this.window_stop = window_stop;
        this.window_start = window_start;
        this.test_id = test_id;
        this.total_marks = total_marks;
    }

    public String[] getTopics ()
    {
        return topics;
    }

    public void setTopics (String[] topics)
    {
        this.topics = topics;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDuration_seconds ()
    {
        return duration_seconds;
    }

    public void setDuration_seconds (String duration_seconds)
    {
        this.duration_seconds = duration_seconds;
    }

    public String getWindow_stop ()
    {
        return window_stop;
    }

    public void setWindow_stop (String window_stop)
    {
        this.window_stop = window_stop;
    }

    public String getWindow_start ()
    {
        return window_start;
    }

    public void setWindow_start (String window_start)
    {
        this.window_start = window_start;
    }

    public String getTest_id ()
    {
        return test_id;
    }

    public void setTest_id (String test_id)
    {
        this.test_id = test_id;
    }

    public String getTotal_marks ()
    {
        return total_marks;
    }

    public void setTotal_marks (String total_marks)
    {
        this.total_marks = total_marks;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [topics = "+topics+", primaryTitle = "+title+", duration_seconds = "+duration_seconds+", window_stop = "+window_stop+", window_start = "+window_start+", test_id = "+test_id+", total_marks = "+total_marks+"]";
    }



    @Override
    public List<?> getChildItemList() {
        ArrayList<Object>childObjectList= new ArrayList<>();
        childObjectList.add(new Test(this.topics,this.title,this.duration_seconds,this.window_stop,this.window_start,this.test_id,this.total_marks));
        return childObjectList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}