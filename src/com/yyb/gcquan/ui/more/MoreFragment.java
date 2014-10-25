package com.yyb.gcquan.ui.more;

import com.yyb.gcquan.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MoreFragment extends Fragment {

	public static MoreFragment newInstance(int postion) {
		MoreFragment moreFragment = new MoreFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("postion", postion);
		moreFragment.setArguments(bundle);
		return moreFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_more, container,
				false);
		return rootView;
	}

}
