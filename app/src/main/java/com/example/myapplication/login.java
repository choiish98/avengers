package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class login extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText pwText = (EditText) findViewById(R.id.pwText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView regiButton = (TextView) findViewById(R.id.regiButton);

        final SwitchCompat switchCompat = findViewById(R.id.switch1);

        TextView findidButton = (TextView) findViewById(R.id.findidbutton);
        TextView findpwButton = (TextView) findViewById(R.id.findpwbutton);

        regiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regiIntent = new Intent(login.this, sign_up_link.class);
                login.this.startActivity(regiIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_id = idText.getText().toString();
                final String user_pw = pwText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String user_id = jsonResponse.getString("user_id");
                                String user_pw = jsonResponse.getString("user_password");
                                // 관리자 계정 체크 된 경우 관리자로 로그인
                                if (switchCompat.isChecked()) {
                                    Log.d("check", "check");
                                    Intent intent = new Intent(login.this, main_page_enter.class);
                                    intent.putExtra("user_id", user_id);
                                    startActivity(intent);
                                }
                                // 관리자 계정 체크 안 된 경우 일반 사용자로 로그인
                                else {
                                    Intent intent = new Intent(login.this, main_page.class);
                                    intent.putExtra("user_id", user_id);
                                    startActivity(intent);
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                                builder.setMessage("로그인에 실패")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                if (switchCompat.isChecked()) {
                    login_enter_request login_enter_request = new login_enter_request(user_id, user_pw, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(login.this);
                    queue.add(login_enter_request);
                }
                else {
                    LoginRequest loginRequest = new LoginRequest(user_id, user_pw, responseListener);
                    RequestQueue queue1 = Volley.newRequestQueue(login.this);
                    queue1.add(loginRequest);
                }
            }
        });

        findidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, FindId.class);
                startActivity(intent);
            }
        });

        findpwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, FindPw.class);
                startActivity(intent);
            }
        });
    }
}