package com.yyb.gcquan.ui.login;

import java.util.ArrayList;
import java.util.List;

import com.yyb.gcquan.R;
import com.yyb.gcquan.bean.UserAccountBean;
import com.yyb.gcquan.db.utility.AccountUtility;
import com.yyb.gcquan.ui.abstractactivity.ActivityInterface;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

public class LoginActivity extends Activity implements ActivityInterface {

	private MultiAutoCompleteTextView accountEditText;
	private EditText passwordEditText;
	private List<UserAccountBean> list = new ArrayList<>();
	private AccountUtility accountUtility;
	private AccountAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initData();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		accountEditText = (MultiAutoCompleteTextView) findViewById(R.id.account);
		passwordEditText = (EditText) findViewById(R.id.password);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		accountUtility = new AccountUtility(this);
		list = (List<UserAccountBean>) accountUtility.query();
		if (list != null) {
			adapter = new AccountAdapter(this, R.layout.login_item, list);
			accountEditText.setAdapter(adapter);
		}

	}
	
	private void attmpetLogin(){
		
	}
	
}
