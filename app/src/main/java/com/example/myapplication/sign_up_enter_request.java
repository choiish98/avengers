package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class sign_up_enter_request extends StringRequest {
    final static private String URL = "http://14.43.194.13/Register_enterprise.php";
    private Map<String, String> parameters;

    public sign_up_enter_request(String user_id, String user_pw, String user_crn, String user_cname, int user_phonenum, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
        parameters.put("user_password", user_pw);
        parameters.put("user_CRN", user_crn);
        parameters.put("user_Cname", user_cname);
        parameters.put("user_phonenum", user_phonenum + "");
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
