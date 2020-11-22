package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class FindPw extends AppCompatActivity {

    Button findpwbutton = findViewById(R.id.findpw);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findid_page);

        final TextView id = findViewById(R.id.id);
        final TextView name = findViewById(R.id.name);
        final TextView age = findViewById(R.id.age);
        final TextView sex = findViewById(R.id.sex);
        final TextView tag = findViewById(R.id.tag);

        findpwbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_id = id.getText().toString();
                final String user_name = name.getText().toString();
                final String user_age = age.getText().toString();
                final String user_sex = sex.getText().toString();
                final String user_tag = tag.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String user_id = jsonResponse.getString("user_id");
                                String user_name = jsonResponse.getString("user_name");
                                String user_age = jsonResponse.getString("user_age");
                                String user_sex = jsonResponse.getString("user_sex");
                                String user_tag = jsonResponse.getString("user_tag");
                                // 관리자 계정 체크 된 경우 관리자로 로그인
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindPw.this);
                                builder.setMessage("아이디를 찾았습니다.")
                                        .setNegativeButton("확인", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindPw.this);
                                builder.setMessage("아이디를 찾는 데 실패 했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                FindRequest findidRequest = new FindRequest(user_name, user_age, user_sex, user_tag, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FindPw.this);
                queue.add(findidRequest);
            }
        });
    }
}