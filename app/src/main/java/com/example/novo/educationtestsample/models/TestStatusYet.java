package com.example.novo.educationtestsample.models;

import java.util.List;

/**
 * Created by Varun on 7/1/2016.
 */
public class TestStatusYet {
    private String id;

    private List<SubmitTestData> testresponse;

    private String attempted_questions;

    private String status;

    private String current_question;

    private String student_name;

    private String student_id;

    private String test_id;

    private String remaining_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SubmitTestData> getTestresponse() {
        return testresponse;
    }

    public void setTestresponse(List<SubmitTestData> testresponse) {
        this.testresponse = testresponse;
    }

    public String getAttempted_questions() {
        return attempted_questions;
    }

    public void setAttempted_questions(String attempted_questions) {
        this.attempted_questions = attempted_questions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrent_question() {
        return current_question;
    }

    public void setCurrent_question(String current_question) {
        this.current_question = current_question;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    public String getRemaining_time() {
        return remaining_time;
    }

    public void setRemaining_time(String remaining_time) {
        this.remaining_time = remaining_time;
    }
}
