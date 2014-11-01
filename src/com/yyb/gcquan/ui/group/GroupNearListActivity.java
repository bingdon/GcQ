package com.yyb.gcquan.ui.group;

import android.os.Bundle;

import com.yyb.gcquan.R;
import com.yyb.gcquan.ui.abstractactivity.BaseActivity;

public class GroupNearListActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_near_group);
	}

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		getActionBar().setTitle(R.string.near_group);
	}

}
