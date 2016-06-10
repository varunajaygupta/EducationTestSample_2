package com.example.novo.educationtestsample.Utils;

/**
 * Created by Varun on 6/1/2016.
 */
public class SQLQueryUtils {
    public static final String COACHING_ID  = "coachingId";
    public static final String TEACHER_ID  = "teacherId";
    public static final String BATCH_ID  = "batchesId";
    public static final String TEST_ID="testId";
    public static final String CURRENT_DATE="currentDate";


    public static final String FETCH_TEST_DATA_QUERY = "{\"where\": {\"coaching_id\": \"coachingId\",\"status\":\"submitted\"," +
            "\"teacher_id\": \"teacherId\",\"batches\":\"batchesId\",\"window_stop\": {\"gt\": currentDate}}," +
            " \"fields\": {\"test_id\": true, \"title\": true, \"duration_seconds\": true, " +
            "\"topics\":true, \"window_start\": true, \"window_stop\": true, \"total_marks\":true}, \"order\": \"date_updated DESC\"}";
}
