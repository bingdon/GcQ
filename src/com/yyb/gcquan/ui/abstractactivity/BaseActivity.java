package com.yyb.gcquan.ui.abstractactivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yyb.gcquan.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;

public class BaseActivity extends Activity {

	protected Activity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		onInitActionBar();
		setCusmenutome();
	}

	protected void onInitActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(getResources()
				.getColor(R.color.theme_dark)));
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);

	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub

		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);

				} catch (Exception e) {
				}
			}
		}

		return super.onMenuOpened(featureId, menu);
	}

	private void setCusmenutome() {
		try {
			ViewConfiguration mconfig = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(mconfig, false);
			}
		} catch (Exception ex) {
		}
	}

}
