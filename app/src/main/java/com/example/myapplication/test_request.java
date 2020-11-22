package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class test_request extends StringRequest {
    final static private String URL = "http://14.43.194.13/test.php";
    private Map<String, String> parameters;

    public test_request(String user_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}