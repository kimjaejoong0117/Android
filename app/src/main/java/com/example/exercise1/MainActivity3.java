package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    Button btn_Back2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //뒤로 클릭시 메인 1로...
        btn_Back2 = findViewById(R.id.btn_Back2);

        btn_Back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity3.this,MainActivity.class );
                intent.putExtra("msg2","오늘은 날씨가 구름이 많네요..");
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }
}