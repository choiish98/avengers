package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class login_enter_request extends StringRequest {
    final static private String URL = "http://14.43.194.13/Login_enterprise.php";
    private Map<String, String> parameters;

    public login_enter_request(String user_id, String user_pw, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
        parameters.put("user_password", user_pw);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
