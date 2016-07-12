package com.example.novo.educationtestsample.Utils;

/**
 * Created by Varun on 6/1/2016.
 */
public class SQLQueryUtils {
    public static final String COACHING_ID  = "coachingId";
    public static final String TEACHER_ID  = "teacherId";
    public static final String BATCH_ID  = "batchesId";
    public static final String TEST_ID="testId";
    public static final String STUDENT_ID="studentId";
    public static final String CURRENT_DATE="currentDate";
    public static final String OPERATOR_VALUE= "operatorValue";
    public static final String GREATER_THAN  = "gt";
    public static final String LOWER_THAN  = "lt";


    public static final String FETCH_TEST_LIST_QUERY = "{\"where\": {\"coaching_id\": \"coachingId\",\"status\":\"submitted\"," +
            "\"teacher_id\": \"teacherId\",\"batches\":\"batchesId\",\"window_stop\": {\"operatorValue\": currentDate}}," +
            " \"fields\": {\"test_id\": true, \"primaryTitle\": true, \"duration_seconds\": true, " +
            "\"topics\":true, \"window_start\": true, \"window_stop\": true, \"total_marks\":true}, \"order\": \"date_updated DESC\"}";


    public static final String FETCH_PARTICULAR_TEST= "{\"where\":{\"test_id\":\"testId\"}}";
    public static final String FETCH_TEST_STATUS_YET= "{\"where\":{\"test_id\":\"testId\",\"student_id\":\"studentId\"}}";
}
