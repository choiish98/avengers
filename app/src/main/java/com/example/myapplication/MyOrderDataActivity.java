package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyOrderDataActivity extends AppCompatActivity {

    Button order_result_btn;
    TextView project_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_data);

        order_result_btn=findViewById(R.id.order_result_btn);
        project_name=findViewById(R.id.project_name);

        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();

        project_name.setText("프로젝트이름:"+bundle.getString("name"));

        order_result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("file",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("is_make_order",false);
                editor.commit();
                Intent intent = new Intent(MyOrderDataActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
