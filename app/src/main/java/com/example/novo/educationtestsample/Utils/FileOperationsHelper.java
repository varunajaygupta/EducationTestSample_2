package com.example.novo.educationtestsample.Utils;

import android.content.Context;
import android.util.Log;

import com.example.novo.educationtestsample.models.QuestionListJSON;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class FileOperationsHelper {

    private static final String TAG = FileOperationsHelper.class.getSimpleName().toString();
    private static FileOperationsHelper ourInstance;

    private final String FILE_NAME = "TestData.txt";

    public static FileOperationsHelper getInstance() {
        if(ourInstance == null)
            ourInstance = new FileOperationsHelper();
        return ourInstance;
    }

    private FileOperationsHelper() {
        // private constructor
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        Log.e(TAG, "Copy file started.");
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        Log.e(TAG, "Copy file finished.");
    }

//    private void createFileFromAssets(Context context){
//        try {
//            Log.e(TAG, "createFileFromAssets started.");
//            InputStream in = context.getAssets().open(FILE_NAME);
//            FileOutputStream out=null;
//            if(ConstURL.appStatus==0){
//                out = context.openFileOutput(D_FILE_NAME, Context.MODE_PRIVATE);
//            }else if(ConstURL.appStatus==1){
//                out = context.openFileOutput(S_FILE_NAME, Context.MODE_PRIVATE);
//            }else {
//                out = context.openFileOutput(P_FILE_NAME, Context.MODE_PRIVATE);
//            }
//            copyFile(in, out);
//            in.close();
//            out.flush();
//            out.close();
//            Log.e(TAG, "createFileFromAssets finished.");
//        } catch(IOException e) {
//            Log.e(TAG, "Failed to copy asset file: " + FILE_NAME, e);
//        }
//    }

    /**
     * Return JSON Formatted string and create a file if required
     * @param context
     * @return json data
     */
    public QuestionListJSON readFile(Context context){

        if(isFileExists(context)){
            Log.e(TAG, "File exist, reading");
            QuestionListJSON questionListJSON;
            try {
                InputStream inputStream = context.openFileInput(FILE_NAME);
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                Gson gson = new Gson();
                questionListJSON = gson.fromJson(new String(buffer, "UTF-8"), QuestionListJSON.class);
                Log.e(TAG, "File read successfully.");
            } catch (IOException ex) {
                Log.e(TAG, "Exception while reading the file");
                ex.printStackTrace();
                return null;
            }
            return questionListJSON;
        }
        return null;
    }

    public void writeFile(Context context, QuestionListJSON questionListJSON){
        try {
            Log.e(TAG, "Write file started");
            Gson gson = new Gson();
            String json = gson.toJson(questionListJSON);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(json);
            outputStreamWriter.close();
            Log.e(TAG, "Write file successful");
        }
        catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }
    }

    public boolean isFileExists(Context context){
        String path=context.getFilesDir().getAbsolutePath()+"/"+FILE_NAME;
        return new File( path ).exists();
    }
    public void createFile(Context context){
       File file= new File(context.getFilesDir().getAbsolutePath()+"/"+FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


