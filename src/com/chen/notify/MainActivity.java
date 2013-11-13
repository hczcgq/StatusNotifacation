package com.chen.notify;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	private Button about;
	private boolean flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		flag=false;
		about=(Button) findViewById(R.id.about);
		
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,MyService.class);
				startService(intent);
				flag=true;
			}
		});
	}

	@Override
	protected void onDestroy() {
		if(flag==true){
			stopService(new Intent(MainActivity.this,MyService.class));
			flag=false;
		}
		super.onDestroy();
	}

}
