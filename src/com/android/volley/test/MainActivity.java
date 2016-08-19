package com.android.volley.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.R;

public class MainActivity extends Activity{
	String url = "http://www.sina.com";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void strRequest(View view){
		// 1.首先建立requestqueue对象
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		// 2.建立StringRequest对象
		StringRequest stringRequest = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.i("TAG","响应数据为："+response);
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
		});
		
		// 3.将StringRequest请求对象添加到请求队列中
		requestQueue.add(stringRequest);
		
	}
}
