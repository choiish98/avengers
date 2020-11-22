package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class first_page extends AppCompatActivity {

    private Button go_to_login_button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);

        go_to_login_button = findViewById(R.id.go_to_login_button);
        go_to_login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.d("listener", "눌렀음");
                Intent intent = new Intent(first_page.this, login.class);
                startActivity(intent);
            }
        });

        textView=(TextView)findViewById(R.id.go_to_sign_up);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(first_page.this, sign_up_link.class);
                startActivity(intent);
            }
        });
    }
}