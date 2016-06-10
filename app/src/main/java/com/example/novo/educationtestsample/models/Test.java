package com.example.novo.educationtestsample.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Test implements Parcelable
{
    private String[] topics;

    private String title;

    private String duration_seconds;

    private String window_stop;

    private String window_start;

    private String test_id;

    private String total_marks;

    protected Test(Parcel in) {
        topics = in.createStringArray();
        title = in.readString();
        duration_seconds = in.readString();
        window_stop = in.readString();
        window_start = in.readString();
        test_id = in.readString();
        total_marks = in.readString();
    }

    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel in) {
            return new Test(in);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };

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
        return "ClassPojo [topics = "+topics+", title = "+title+", duration_seconds = "+duration_seconds+", window_stop = "+window_stop+", window_start = "+window_start+", test_id = "+test_id+", total_marks = "+total_marks+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(topics);
        dest.writeString(title);
        dest.writeString(duration_seconds);
        dest.writeString(window_stop);
        dest.writeString(window_start);
        dest.writeString(test_id);
        dest.writeString(total_marks);
    }
}