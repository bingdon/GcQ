package com.yyb.gcquan.ui.near;

import com.yyb.gcquan.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NearFragment extends Fragment {

	public static NearFragment newInstance(int position) {
		NearFragment nearFragment = new NearFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		return nearFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_near, container,
				false);
		return rootView;
	}

	
	
}
