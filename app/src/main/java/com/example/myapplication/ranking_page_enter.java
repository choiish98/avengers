package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ranking_page_enter extends AppCompatActivity {

    private static final String TAG = "statics";

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_page2);

        mChart = (LineChart) findViewById(R.id.linechart);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 70f));
        yValues.add(new Entry(3, 30f));
        yValues.add(new Entry(4, 50f));
        yValues.add(new Entry(5, 60f));
        yValues.add(new Entry(6, 65f));
        LineDataSet set1 = new LineDataSet(yValues, "Data set 1");

        set1.setFillAlpha(110);

        set1.setColor(Color.RED);
        set1.setLineWidth(2f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mChart.setData(data);

        ImageButton test_button;
        ImageButton rank_btn;
        ImageButton order_btn;
        ImageButton home_btn;

        test_button = findViewById(R.id.test_btn);
        rank_btn = findViewById(R.id.rank_btn);
        order_btn = findViewById(R.id.order_btn);
        home_btn = findViewById(R.id.home_btn);

        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ranking_page_enter.this, test_enter.class);
                startActivity(intent);
            }
        });
        rank_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ranking_page_enter.this, ranking_page_enter.class);
                startActivity(intent);
            }
        });
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ranking_page_enter.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ranking_page_enter.this, main_page_enter.class);
                startActivity(intent);
            }
        });
    }

}
