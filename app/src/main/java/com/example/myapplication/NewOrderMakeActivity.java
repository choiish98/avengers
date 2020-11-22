package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class NewOrderMakeActivity extends AppCompatActivity {

    EditText title_edit;
    EditText name_edit;
    EditText tag_edit;
    EditText recruitment_edit;
    EditText contents_edit;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_make);

        final EditText titleText = (EditText) findViewById(R.id.title_edit_text);
        final EditText nameText = (EditText) findViewById(R.id.name_edit_text);
        final EditText tagText = (EditText) findViewById(R.id.tag_edit_text);
        final EditText recruitmentText = (EditText) findViewById(R.id.recruitment_edit_text);
        final EditText contentsText = (EditText) findViewById(R.id.contents_edit_text);

        Button submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String announcement_title = titleText.getText().toString();
                String announcement_ProjectName = titleText.getText().toString();
                String announcement_tag = titleText.getText().toString();
                int announcement_recruitment = Integer.parseInt(recruitmentText.getText().toString());
                String announcement_contents = titleText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(NewOrderMakeActivity.this);
                                builder.setMessage("공고 등록 성공.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(NewOrderMakeActivity.this, main_page_enter.class);
                                NewOrderMakeActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(NewOrderMakeActivity.this);
                                builder.setMessage("공고 등록 실패.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                NewOrderMakeActivityRequest newOrderMakeActivityRequest = new NewOrderMakeActivityRequest(announcement_title, announcement_ProjectName, announcement_tag, announcement_recruitment, announcement_contents, responseListener);
                RequestQueue queue = Volley.newRequestQueue(NewOrderMakeActivity.this);
                queue.add(newOrderMakeActivityRequest);
            }
        });

        /*setContentView(R.layout.activity_new_order_make);
        name_edit=findViewById(R.id.name_edit_text);
        count_edit=findViewById(R.id.recruitment_edit_text);
        content_edit=findViewById(R.id.contents_edit_text);
        submit_btn=findViewById(R.id.submit_button);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("file",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("is_make_order",true);
                editor.putString("my_order_name",name_edit.getText().toString());
                editor.putString("my_order_count",count_edit.getText().toString());
                editor.putString("my_order_content",content_edit.getText().toString());
                editor.commit();
                Intent intent = new Intent(NewOrderMakeActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
