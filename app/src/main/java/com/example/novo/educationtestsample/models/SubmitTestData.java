package com.example.novo.educationtestsample.models;

import java.util.List;

/**
 * Created by Varun on 7/1/2016.
 */
public class SubmitTestData {

    private List<SubmitAnswer> answer_array;

  //  private String answer_key;

    private String answer_state;

    public List<SubmitAnswer> getAnswer_array() {
        return answer_array;
    }

    public void setAnswer_array(List<SubmitAnswer> answer_array) {
        this.answer_array = answer_array;
    }

//    public String getAnswer_key() {
//        return answer_key;
//    }
//
//    public void setAnswer_key(String answer_key) {
//        this.answer_key = answer_key;
//    }

    public String getAnswer_state() {
        return answer_state;
    }

    public void setAnswer_state(String answer_state) {
        this.answer_state = answer_state;
    }
}
