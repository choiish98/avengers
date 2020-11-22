package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class OrderDataActivity extends AppCompatActivity {

    TextView announcement_title;
    TextView announcement_ProjectName;
    TextView announcement_tag;
    TextView announcement_recruitment;
    TextView announcement_contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_data);
        announcement_title=findViewById(R.id.announcement_title);
        announcement_ProjectName=findViewById(R.id.announcement_ProjectName);
        announcement_tag=findViewById(R.id.announcement_tag);
        announcement_recruitment=findViewById(R.id.announcement_recruitment);
        announcement_contents=findViewById(R.id.announcement_contents);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");

        announcement_title.setText("제목 : " + bundle.getString("title"));
        announcement_ProjectName.setText("프로젝트이름 : " + bundle.getString("name"));
        announcement_tag.setText("태그 : " + bundle.getString("tag"));
        announcement_recruitment.setText("모집인원 : " + Integer.toString(bundle.getInt("recruitment")));
        announcement_contents.setText("프로젝트내용 : " +bundle.getString("contents"));

    }
}
