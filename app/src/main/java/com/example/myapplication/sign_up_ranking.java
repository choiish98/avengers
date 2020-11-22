package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class sign_up_ranking extends StringRequest {
    final static private String URL = "http://14.43.194.13/sign_up_ranking.php";
    private Map<String, String> parameters;

    public sign_up_ranking(String user_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
    }

    @Override
    public Map<String, String> getParams() { return parameters; }
}
