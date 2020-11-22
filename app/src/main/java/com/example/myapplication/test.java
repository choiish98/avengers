package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class test extends AppCompatActivity {

    ListView test_list_view;
    TextView question_textview;
    Button submit_btn;
    TextView submit_textview;
    TextView answer_textview;
    int select_question=-1;
    int[][] answer_count=new int[3][2];
    int point;
    String[] answer_sheet={"a","id","2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page);

        question_textview=findViewById(R.id.question_textview);
        test_list_view=(ListView)findViewById(R.id.test_list_view);
        submit_textview=findViewById(R.id.submit_text);
        submit_btn=findViewById(R.id.submit_button);
        answer_textview=findViewById(R.id.answer_testview);

        SharedPreferences sharedPreferences=getSharedPreferences("file",0);
        point=sharedPreferences.getInt("point",0);
        answer_count[0][0]=sharedPreferences.getInt("htmltry",0);
        answer_count[0][1]=sharedPreferences.getInt("htmlsussess",0);
        answer_count[1][0]=sharedPreferences.getInt("csstry",0);
        answer_count[1][1]=sharedPreferences.getInt("csssussess",0);
        answer_count[2][0]=sharedPreferences.getInt("javascripttry",0);
        answer_count[2][1]=sharedPreferences.getInt("javascriptsussess",0);

        ListViewAdapter adapter;

        List<String> data=new ArrayList<>();
        adapter=new ListViewAdapter();
        test_list_view.setAdapter(adapter);
        adapter.addItem("HTML");
        adapter.addItem("CSS");
        adapter.addItem("JAVASCRIPT");


        test_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    question_textview.setText("HTML에서 하이퍼링크를 의미하는 태그는?");
                }
                else if(position==1)
                {
                    question_textview.setText("태그,클래스,아이디중 제일 우선으로 적용되는 것은?");
                }
                else if(position==2)
                {
                    question_textview.setText("var a =1;\nvar b=1;\n console.log(a+b);\n의 결과는");
                }
                select_question=position;
                answer_textview.setText("시도횟수:"+answer_count[position][0]+" 성공횟수:"+answer_count[position][1]+" ");
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String submit_text=submit_textview.getText().toString();
                if(select_question==-1)
                {
                    Toast.makeText(getApplicationContext(),"문제를 선택하지 않았습니다.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(submit_text.equals(answer_sheet[select_question]))
                    {
                        Toast.makeText(getApplicationContext(),"맞았습니다",Toast.LENGTH_SHORT).show();
                        if(answer_count[select_question][1]==0)
                        {
                            point+=1;
                        }
                        answer_count[select_question][1]+=1;
                        // 여기서 문제가 맞았으니까 해당 문제의 점수만큼 db에 더함
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if(success){
                                        String user_id = jsonResponse.getString("user_id");
                                    }
                                } catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        };

                        test_request test_Request = new test_request(getIntent().getExtras().getString("user_id"), responseListener);
                        RequestQueue queue = Volley.newRequestQueue(test.this);
                        queue.add(test_Request);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"틀렸습니다",Toast.LENGTH_SHORT).show();
                    }
                    answer_count[select_question][0]+=1;
                    answer_textview.setText("시도횟수:"+answer_count[select_question][0]+"   성공횟수:"+answer_count[select_question][1]+"   ");

                }
                /*
                if(select_question==1)
                {
                    if(submit_text.equals("아이디"))
                    {
                        Toast.makeText(getApplicationContext(),"맞았습니다",Toast.LENGTH_SHORT).show();
                        if(answer_count[1][1]==0)
                        {
                            point+=1;
                        }
                        answer_count[1][1]+=1;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"틀렸습니다",Toast.LENGTH_SHORT).show();
                    }
                }
                if(select_question==2)
                {
                    if(submit_text.equals("2"))
                    {
                        Toast.makeText(getApplicationContext(),"맞았습니다",Toast.LENGTH_SHORT).show();
                        if(answer_count[2][1]==0)
                        {
                            point+=1;
                        }
                        answer_count[2][1]+=1;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"틀렸습니다",Toast.LENGTH_SHORT).show();
                    }
                }
                if(select_question>=0)
                {

                }
                */
            }

        });

        ImageButton test_btn = findViewById(R.id.test_btn);
        ImageButton rank_btn = findViewById(R.id.rank_btn);
        ImageButton home_btn = findViewById(R.id.home_btn);

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test.this, test.class);
                startActivity(intent);
            }
        });
        rank_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test.this, ranking_page.class);
                startActivity(intent);
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test.this, main_page.class);
                startActivity(intent);
            }
        });
    }
}
