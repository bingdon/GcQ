package com.yyb.gcquan.ui.adapter;

import com.astuetz.PagerSlidingTabStrip.CustomViewProvider;
import com.yyb.gcquan.R;
import com.yyb.gcquan.ui.abstractfragment.SuperAwesomeCardFragment;
import com.yyb.gcquan.ui.contacts.ContactsFragment;
import com.yyb.gcquan.ui.more.MoreFragment;
import com.yyb.gcquan.ui.near.NearFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter implements
		CustomViewProvider {

	private final String[] TITLES = { "附近", "通讯录", "消息", "工作", "更多" };

	private final int[] VIEWRESIDS = { R.layout.tabview, R.layout.tabview,
			R.layout.tabview, R.layout.tabview, R.layout.tabview };

	private final int[] NORMAL_HEAD = { R.drawable.ic_tab_near,
			R.drawable.ic_tab_contact, R.drawable.ic_tab_msg,
			R.drawable.ic_tab_job, R.drawable.ic_tab_more };

	private final int[] CURRTENT_HEAD = { R.drawable.ic_tab_near_press,
			R.drawable.ic_tab_contact_press, R.drawable.ic_tab_msg_press,
			R.drawable.ic_tab_job_press, R.drawable.ic_tab_more_press };

	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position];
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return NearFragment.newInstance(position);
		case 1:
			return ContactsFragment.newInstance(position);

		case 4:
			return MoreFragment.newInstance(position);
		default:
			break;
		}
		return SuperAwesomeCardFragment.newInstance(position);
	}

	@Override
	public int getPageViewResId(int position) {
		// TODO Auto-generated method stub
		return VIEWRESIDS[position];
	}

	@Override
	public int getPageImageLayoutResID() {
		// TODO Auto-generated method stub
		return R.id.tab_img;
	}

	@Override
	public int getCurPageImageResID(int postion) {
		// TODO Auto-generated method stub
		return CURRTENT_HEAD[postion];
	}

	@Override
	public int getPageImageResID(int postion) {
		// TODO Auto-generated method stub
		return NORMAL_HEAD[postion];
	}

	@Override
	public int getPageTxtResID() {
		// TODO Auto-generated method stub
		return R.id.tab_notice;
	}

}
