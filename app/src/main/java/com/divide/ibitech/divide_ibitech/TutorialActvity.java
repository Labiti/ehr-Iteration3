package com.divide.ibitech.divide_ibitech;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TutorialActvity extends AppCompatActivity {
private ViewPager mPager;
private int[] layouts={R.layout.slide_zero,R.layout.slide_one,R.layout.slide_two,R.layout.slide_three,R.layout.slide_four,R.layout.slide_five,R.layout.slide_six,R.layout.slide_seven,R.layout.slide_nine,R.layout.slide_ten};
private  MpageAdapter mpageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
mPager=(ViewPager)findViewById(R.id.viewPaper);
mpageAdapter= new MpageAdapter(layouts,this);
mPager.setAdapter(mpageAdapter);

    }
}
