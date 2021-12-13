package com.example.exercise1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

Button btn_Reset,btn_Back3;
TextView textView2,startTime,edit1,edit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        btn_Back3=findViewById(R.id.btn_Back3);
        btn_Reset=findViewById(R.id.btn_Reset);
        startTime=findViewById(R.id.startTime);
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        textView2=findViewById(R.id.textView2);





            btn_Back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity4.this,MainActivity.class );
                intent.putExtra("msg","Come Back!");
                setResult(RESULT_OK,intent);
                finish();
            }
        });



    }










}