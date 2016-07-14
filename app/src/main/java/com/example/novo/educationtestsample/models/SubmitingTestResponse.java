package com.example.novo.educationtestsample.models;

/**
 * Created by Varun on 7/14/2016.
 */
public class SubmitingTestResponse {
    String where;
    TestStatusYet data;

    public TestStatusYet getData() {
        return data;
    }

    public void setData(TestStatusYet data) {
        this.data = data;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
