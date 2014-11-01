package com.yyb.gcquan;

import com.astuetz.PagerSlidingTabStrip;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.yyb.gcquan.app.GcApplication;
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

	private static final String TAG = MainActivity.class.getSimpleName();
	private PagerSlidingTabStrip tabStrip;
	private ViewPager mainPager;
	private MainPagerAdapter mainPagerAdapter;
	private LocationClient mLocationClient;
	private GcLocationListener gcLocationListener;
	private LocationMode tempMode = LocationMode.Hight_Accuracy;
	private String tempcoor = "gcj02";
	private int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		InitLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("position", position);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState != null) {
			position = savedInstanceState.getInt("position", 0);
			if (mainPager != null) {
				mainPager.setCurrentItem(position);
			}
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (null != mLocationClient) {
			mLocationClient.stop();
		}
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
		position = arg0;
		getActionBar().setTitle(mainPagerAdapter.getPageTitle(arg0));
	}

	private void InitLocation() {
		mLocationClient = new LocationClient(this);
		gcLocationListener = new GcLocationListener();
		mLocationClient.registerLocationListener(gcLocationListener);
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);// 设置定位模式
		option.setCoorType(tempcoor);// 返回的定位结果是百度经纬度，默认值gcj02
		option.setIsNeedAddress(true);
		final int span = 1000 * 60;
		option.setScanSpan(span);// 设置发起定位请求的间隔时间为1分钟
		mLocationClient.setLocOption(option);
		mLocationClient.start();
	}

	private class GcLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation arg0) {
			// TODO Auto-generated method stub
			if (arg0 != null) {
				GcApplication.getInstance().setLatitude(arg0.getLatitude());
				GcApplication.getInstance().setLongitude(arg0.getLongitude());
				GcApplication.getInstance().setAddress(arg0.getAddrStr());
				GcApplication.getInstance().setCity(arg0.getCity());
			}
		}

	}

}
