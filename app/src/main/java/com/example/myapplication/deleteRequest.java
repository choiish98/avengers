package com.example.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class deleteRequest extends StringRequest {

    final static private String URL = "";
    private Map<String,String> parameters;

    public deleteRequest(String announcement_title, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("announcement_title", announcement_title);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
