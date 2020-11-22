package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MakeRequest extends StringRequest {
    final static private String URL = "http://14.43.194.13/makeRequest.php";
    private Map<String, String> parameters;

    public MakeRequest(String name_edit, String project_edit, String count_edit, String recruitment, String tag_edit, String content_edit, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("announcement_title", name_edit);
        parameters.put("announcement_ProjectName", project_edit);
        parameters.put("announcement_tag", count_edit);
        parameters.put("announcement_recruitment", recruitment);
        parameters.put("announcement_Term", tag_edit);
        parameters.put("announcement_contents", content_edit);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
