package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class main_page extends AppCompatActivity {
    private ScrollView idea_list_view;
    private LinearLayout linearLayout;
    private ArrayList<Announcement> announcements;

    ImageButton test_button;
    ImageButton rank_btn;
    ImageButton home_btn;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.RIGHT);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        JSONArray data = jsonObject.getJSONArray("data");

                        announcements = new ArrayList<>();

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject o = (JSONObject) data.get(i);

                            Announcement a = new Announcement(o);

                            announcements.add(a);
                        }

                        // 실제 TextView 들이 추가됨

                        for (final Announcement a : announcements) {
                            TextView tv = new TextView(main_page.this);

                            tv.setTextSize(20);
                            tv.setText(a.getTitle());
                            tv.setPadding(100, 20, 20, 50);

                            Typeface face = ResourcesCompat.getFont(main_page.this, R.font.bmdohyeon);
                            tv.setTypeface(face);


                            tv.setOnClickListener(new TextView.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(main_page.this, OrderDataActivity.class);
                                    Bundle bundle = new Bundle();

                                    bundle.putString("title", a.getTitle());
                                    bundle.putString("name", a.getName());
                                    bundle.putString("tag", a.getTag());
                                    bundle.putInt("recruitment", a.getRecruitment());
                                    bundle.putString("contents", a.getContents());

                                    intent.putExtra("Bundle", bundle);

                                    startActivity(intent);
                                }
                            });

                            linearLayout.addView(tv);
                        }

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(main_page.this);
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

        main_pageRequest mainPageRequest = new main_pageRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(main_page.this);
        queue.add(mainPageRequest);

        idea_list_view = (ScrollView) findViewById(R.id.order_list_view);
        idea_list_view.addView(linearLayout);

        test_button = findViewById(R.id.test_btn);
        rank_btn = findViewById(R.id.rank_btn);
        home_btn = findViewById(R.id.home_btn);

        /*
        SharedPreferences sharedPreferences = getSharedPreferences("file", 0);
        boolean my_order = sharedPreferences.getBoolean("is_make_order", false);

        final String my_order_name = sharedPreferences.getString("my_order_name", "??");
        final String my_order_count = sharedPreferences.getString("my_order_count", "??");
        final String my_order_content = sharedPreferences.getString("my_order_content", "??");
        if (my_order) {
            data.add(my_order_name);
        }
        */

        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_page.this, test.class);
                String user_id = getIntent().getExtras().getString("user_id");
                intent.putExtra("user_id", user_id);
                startActivity(intent);
            }
        });
        rank_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_page.this, ranking_page.class);
                startActivity(intent);
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_page.this, main_page.class);
                startActivity(intent);
            }
        });
    }
}