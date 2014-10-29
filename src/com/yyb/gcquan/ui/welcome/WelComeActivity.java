package com.yyb.gcquan.ui.welcome;

import com.yyb.gcquan.MainActivity;
import com.yyb.gcquan.R;
import com.yyb.gcquan.app.GcApplication;
import com.yyb.gcquan.support.userdata.UserInfoUtility;
import com.yyb.gcquan.ui.login.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Window;

public class WelComeActivity extends Activity implements Callback {

	private Handler handler;
	private final long DELAYE_SHOW_TIME = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		handler = new Handler(this);
		handler.sendEmptyMessageDelayed(0, DELAYE_SHOW_TIME);
		UserInfoUtility.getPersonInfo(this);
	}

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub

		if (null == GcApplication.getInstance().getPersonBean()) {
			startActivity(new Intent(this, LoginActivity.class));
		} else {
			startActivity(new Intent(this, MainActivity.class));
		}

		finish();

		return false;
	}

}
