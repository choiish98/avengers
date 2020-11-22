package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class sign_up_enter extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up2);

        final EditText user_id = (EditText) findViewById(R.id.user_id);
        final EditText user_pw = (EditText) findViewById(R.id.user_pw);
        final EditText user_crn = (EditText) findViewById(R.id.user_crn);
        final EditText user_cname = (EditText) findViewById(R.id.user_cname);
        final EditText user_phonenum = (EditText) findViewById(R.id.user_phonenum);

        Button regiButton = (Button) findViewById(R.id.regiButton);

        regiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("listener", "눌렀음");
                String id = user_id.getText().toString();
                String pw = user_pw.getText().toString();
                String crn = user_crn.getText().toString();
                String cname = user_cname.getText().toString();
                int phonenum = Integer.parseInt(user_phonenum.getText().toString());

                Response.Listener<String> reponseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d("listener", response);
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(sign_up_enter.this);
                                builder.setMessage("회원 가입 성공.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(sign_up_enter.this, login.class);
                                sign_up_enter.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(sign_up_enter.this);
                                builder.setMessage("회원 가입 실패.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                sign_up_enter_request sign_up_enter_request = new sign_up_enter_request(id, pw, crn, cname, phonenum, reponseListener);
                RequestQueue queue = Volley.newRequestQueue(sign_up_enter.this);
                queue.add(sign_up_enter_request);
            }
        });
    }


}
