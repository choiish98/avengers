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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class test_enter extends AppCompatActivity {

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
        setContentView(R.layout.test_page2);

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

        List<String> data=new ArrayList<>();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        test_list_view.setAdapter(adapter);
        data.add("HTML");
        data.add("CSS");
        data.add("JAVASCRIPT");

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

        ImageButton test_button;
        ImageButton rank_btn;
        ImageButton order_btn;
        ImageButton home_btn;

        test_button = findViewById(R.id.test_button);
        rank_btn = findViewById(R.id.rank_btn);
        order_btn = findViewById(R.id.order_btn);
        home_btn = findViewById(R.id.home_btn);

        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test_enter.this, test_enter.class);
                startActivity(intent);
            }
        });
        rank_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test_enter.this, ranking_page_enter.class);
                startActivity(intent);
            }
        });
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test_enter.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test_enter.this, main_page_enter.class);
                startActivity(intent);
            }
        });
    }
}
