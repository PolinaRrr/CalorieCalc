package com.example.caloriecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void button_add_onClick(View view) {
        setContentView(R.layout.layout_add);
    }

    public void button_list_onClick(View view) {
        setContentView(R.layout.layout_list);
    }

    public void button_del_onClick(View view) {
        setContentView(R.layout.layout_del);
    }

    public void button_exit_onClick(View view) {
        System.out.close();
    }

}