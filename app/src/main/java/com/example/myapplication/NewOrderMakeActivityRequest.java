package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NewOrderMakeActivityRequest extends StringRequest {
    final static private String URL = "http://14.43.194.13/NewOrderMake.php";
    private Map<String, String> parameters;

    public NewOrderMakeActivityRequest(String announcement_title, String announcement_ProjectName, String announcement_tag, int announcement_recruitment, String announcement_contents, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("announcement_title", announcement_title);
        parameters.put("announcement_ProjectName", announcement_ProjectName);
        parameters.put("announcement_tag", announcement_tag);
        parameters.put("announcement_recruitment", announcement_recruitment + "");
        parameters.put("announcement_contents", announcement_contents);
    }

    @Override
    public Map<String, String> getParams() { return parameters; }
}
