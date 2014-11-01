package com.yyb.gcquan.ui.abstractfragment;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yyb.gcquan.R;
import com.yyb.gcquan.ui.abstractactivity.FragmentInterface;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

public class BaseListFragment extends Fragment implements FragmentInterface {

	protected ListView listView;

	protected PullToRefreshListView pullToRefreshListView;

	public void initView(@Nullable View rootView) {
		pullToRefreshListView = (PullToRefreshListView) rootView
				.findViewById(R.id.reshList);
		listView = pullToRefreshListView.getRefreshableView();
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
