package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://14.43.194.13/Register_general.php";
    private Map<String, String> parameters;

    public RegisterRequest(String user_id, String user_pw, String user_name, int user_age, String user_sex, String user_tag, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
        parameters.put("user_password", user_pw);
        parameters.put("user_name", user_name);
        parameters.put("user_age", user_age + "");
        parameters.put("user_sex", user_sex);
        parameters.put("user_tag", user_tag);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
