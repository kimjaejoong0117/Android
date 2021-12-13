package com.example.exercise1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_login extends AppCompatActivity {
EditText id,pw;
Button btnLogin,btnJoin;

    //Server로 요청하는 객체
    private RequestQueue queue;
    //server로 요청시 필요한 정보를 담는 객체
    private StringRequest stringRequest;
    //client를 판별하는 값
    private String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnJoin=findViewById(R.id.btnJoin);
        btnLogin=findViewById(R.id.btnLogin);
        id=findViewById(R.id.id);
        pw=findViewById(R.id.pw);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_login.this,Join.class);
                startActivity(intent);
            }
        });

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            sendRequest();
           }
       });
    }



    public void sendRequest() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        // 서버에 요청할 주소
        String url = "http://220.80.203.45:8091/AndroidServer/LoginService";

        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);
                if(response.equals("fail")){
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                }else{
                    //json 타입 문자열을 json 객체로 변환해주는 코드
                    try {
                        JSONObject jsonObject= new JSONObject(response);
                        String id = jsonObject.getString("id");
                        String pw = jsonObject.getString("pw");
                        String nick = jsonObject.getString("nick");
                        String phone = jsonObject.getString("phone");
                        Log.v("resultValue",id+"/"+pw+""+nick+"/"+phone);
                        AndMemberVO info= new AndMemberVO(id,pw,nick,phone);
                        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("info",info);
                        startActivity(intent);

                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id",id.getText().toString());
                params.put("pw",pw.getText().toString());

                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }


}