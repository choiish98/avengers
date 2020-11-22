package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.util.ArrayList;


public class ranking_page extends AppCompatActivity {

    private static final String TAG = "statics";
    ImageView profile;
    private LineChart mChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_page);

        profile = (ImageView) findViewById(R.id.profile);

        new TedPermission(this).setRationaleMessage("이 기능은 외부 저장소에 접근 권한이 필요합니다.")
                        .setDeniedMessage("권한 접근을 거부했습니다.")
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                profile.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Intent.ACTION_PICK);
                                        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                                        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(intent, 10);
                                    }
                                });
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                            }
                        }).check();




        drawchart();

        ImageButton test_btn = findViewById(R.id.test_btn);
        ImageButton rank_btn = findViewById(R.id.rank_btn);
        ImageButton home_btn = findViewById(R.id.home_btn);

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ranking_page.this, test.class);
                startActivity(intent);
            }
        });
        rank_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ranking_page.this, ranking_page.class);
                startActivity(intent);
            }
        });
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ranking_page.this, main_page.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @NonNull Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10){
            Uri image = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
                profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void drawchart(){
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
    }
}