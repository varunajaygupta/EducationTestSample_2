package com.example.novo.educationtestsample.Utils;

/**
 * Created by Varun on 5/17/2016.
 */
public class ConstUtils {
    //TODO: Put all constants here
    public static final String TRUE="TRUE";
    public static final String FALSE="FALSE";
    public static final String SINGLE_CHOICE="Single Choice";
    public static final String MULTIPLE_CHOICE="Multiple Choice";
    public static final String ANSWER_KEY_TRUE  ="T";
    public static final String ANSWER_KEY_FALSE  ="F";

    //Enums
    public enum AnswerStateEnum{
      A,U,RU,RA;
    }
/*
  a. A -- Attempted.
   b. U -- UnAttempted.
   c. RU -- Marked for review and UnAttempted.
   d. RA -- MArked for review and Attempted.
 */

}
