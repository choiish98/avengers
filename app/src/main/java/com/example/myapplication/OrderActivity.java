package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private ListView order_list_view;
    private orderlistadapter adapter;
    private List<order> orderList;

    ImageButton test_btn;
    ImageButton rank_btn;
    ImageButton main_btn;
    ImageButton order_btn;
    Button new_order_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();

        order_list_view = findViewById(R.id.order_list_view);
        orderList = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("list"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String announcement_title, announcement_tag, announcement_recruitment, announcement_contents;
            while(count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);
                announcement_title = object.getString("announcemnet_title");
                announcement_tag = object.getString("announcemnet_tag");
                announcement_recruitment = object.getString("announcemnet_recruitment");
                announcement_contents = object.getString("announcemnet_contents");
                order order = new order(announcement_title, announcement_tag, announcement_recruitment, announcement_contents);
                orderList.add(order);
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        test_btn=findViewById(R.id.test_btn);
        rank_btn=findViewById(R.id.rank_btn);
        main_btn=findViewById(R.id.home_btn);
        order_btn=findViewById(R.id.order_btn);

        order_list_view=findViewById(R.id.order_list_view);
        new_order_btn=findViewById(R.id.new_order_button);

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, test_enter.class);
                startActivity(intent);
            }
        });
        rank_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, ranking_page_enter.class);
                startActivity(intent);
            }
        });
        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, main_page_enter.class);
                startActivity(intent);
            }
        });
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });


        new_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, NewOrderMakeActivity.class);
                startActivity(intent);
            }
        });
    }
}
