package com.example.novo.educationtestsample.models;

/**
 * Created by Varun on 5/23/2016.
 */
public class LoginResponse
{
    private Response response;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+"]";
    }
}
