package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class main_pageRequest extends StringRequest {
    final static private String URL = "http://14.43.194.13/main_page.php";

    public main_pageRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
    }
}
