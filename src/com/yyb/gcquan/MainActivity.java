package com.yyb.gcquan;

import com.astuetz.PagerSlidingTabStrip;
import com.yyb.gcquan.ui.abstractactivity.ActivityInterface;
import com.yyb.gcquan.ui.adapter.MainPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements
		ActivityInterface, OnPageChangeListener {

	private PagerSlidingTabStrip tabStrip;
	private ViewPager mainPager;
	private MainPagerAdapter mainPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabStrip);
		tabStrip.setShouldExpand(true);
		mainPager = (ViewPager) findViewById(R.id.pager);
		mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
		mainPager.setAdapter(mainPagerAdapter);
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		mainPager.setPageMargin(pageMargin);
		tabStrip.setViewPager(mainPager);
		tabStrip.setOnPageChangeListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		getActionBar().setTitle(mainPagerAdapter.getPageTitle(arg0));
	}

}
