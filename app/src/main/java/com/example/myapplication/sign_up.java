package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class sign_up extends AppCompatActivity {
    private RadioButton sex_m, sex_f;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        sex_m = (RadioButton) findViewById(R.id.sex_m);
        sex_f = (RadioButton) findViewById(R.id.sex_f);
        radioGroup = (RadioGroup) findViewById(R.id.sex);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText pwText = (EditText) findViewById(R.id.pwText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText ageText = (EditText) findViewById(R.id.ageText);
        final EditText tagText = (EditText) findViewById(R.id.tagText);

        Button regiButton = (Button) findViewById(R.id.regiButton);


        regiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("listener", "눌렀음");
                String user_sex = "";
                String user_id = idText.getText().toString();
                String user_pw = pwText.getText().toString();
                String user_name = nameText.getText().toString();
                int user_age = Integer.parseInt(ageText.getText().toString());
                if(radioGroup.getCheckedRadioButtonId() == R.id.sex_f) {
                    user_sex = "f";
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.sex_m){
                    user_sex = "m";
                }
                String user_tag = tagText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d("listener", response);
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(sign_up.this);
                                builder.setMessage("회원 가입 성공.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(sign_up.this, login.class);
                                sign_up.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(sign_up.this);
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
                RegisterRequest registerRequest = new RegisterRequest(user_id, user_pw, user_name, user_age, user_sex, user_tag, responseListener);
                RequestQueue queue = Volley.newRequestQueue(sign_up.this);
                queue.add(registerRequest);

                sign_up_ranking sign_up_ranking = new sign_up_ranking(user_id, responseListener);
                queue.add(sign_up_ranking);
            }
        });
    }


}
