package com.example.exercise1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity  {

    //Server로 요청하는 객체
    private RequestQueue queue;
    //server로 요청시 필요한 정보를 담는 객체
    private StringRequest stringRequest;
    //client를 판별하는 값
    private String TAG = "main";

    Button btn_En ;
    Button btn_Beacon ;
    TextView tvResult,tvResult2;

    private static final int Main4 = 1000;//MainActivity4
    private static final int Main3 = 1001;//MainActivity3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_En=findViewById(R.id.btn_En);
        btn_Beacon=findViewById(R.id.btn_Beacon);

        tvResult=findViewById(R.id.tvResult);
        tvResult2=findViewById(R.id.tvResult2);

        //메인1에 아이디랑 같은 값 뜨게
        Intent intent = getIntent();
        AndMemberVO info =(AndMemberVO)intent.getSerializableExtra("info");
        tvResult.setText(info.getId()+"님 환영합니다!!");


        //로그인페이지-->메인엑티비티

        /*  String edit_id=getIntent().getStringExtra("edit_id");
        String edit_pw=getIntent().getStringExtra("edit_pw");

        if(edit_id.equals("smart") && edit_pw.equals("1234")) {
            tvResult.setText(edit_id + "님 로그인 성공");
        }else{
            Toast.makeText( this.getApplicationContext(), "이메일 혹은 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }*/

        //비콘버튼 클릭시 메인엑티비티4로
        Button btn_Beacon = findViewById(R.id.btn_Beacon);

        btn_Beacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MainActivity4.class );
                startActivityForResult(intent,Main4);
            }
        });

        //환경버튼 클릭시 메인엑티비티4로
        Button btn_En = findViewById(R.id.btn_En);

        btn_En.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MainActivity3.class);
                startActivityForResult(intent,Main3);
            }
        });



    }//end oncreate


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //널값을허용한다  Nullable
        super.onActivityResult(requestCode, resultCode, data);
        //어떤 액티비티에서 되돌아 왔는지를 체크!
        //성공적으로 처리되었는지 체크!
        if (requestCode == Main4 && resultCode == RESULT_OK) {

            String msg = data.getStringExtra("msg");
            tvResult2.setText(msg);



        }else if(requestCode == Main3 && resultCode == RESULT_OK){
            String msg2 = data.getStringExtra("msg2");
            tvResult2.setText(msg2);
        }
    }
}
