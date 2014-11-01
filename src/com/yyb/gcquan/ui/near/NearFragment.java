package com.yyb.gcquan.ui.near;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yyb.gcquan.R;
import com.yyb.gcquan.app.GcApplication;
import com.yyb.gcquan.bean.PersonBean;
import com.yyb.gcquan.support.debug.AppLog;
import com.yyb.gcquan.support.distance.DistentsUtil;
import com.yyb.gcquan.support.http.HttpPostUtility;
import com.yyb.gcquan.support.http.HttpUtility;
import com.yyb.gcquan.support.time.TimeUtility;
import com.yyb.gcquan.support.userdata.UserInfoUtility;
import com.yyb.gcquan.ui.abstractactivity.FragmentInterface;
import com.yyb.gcquan.ui.adapter.PersonInfoAdapter;
import com.yyb.gcquan.ui.group.GroupNearListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NearFragment extends Fragment implements FragmentInterface,
		OnRefreshListener<ListView> {

	private ListView nearListView;

	private PullToRefreshListView pullToRefreshListView;

	private PersonBean personBean;

	private List<PersonBean> userList = new ArrayList<>();

	private PersonInfoAdapter personInfoAdapter;

	private String json = UserInfoUtility.getNearUserJson();

	private View headView;

	private TextView address;

	private TextView groupAllNum;

	private TextView groupNearNum;

	private String count = UserInfoUtility.getNearGroupCount();

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
		initView(rootView);
		initData();
		return rootView;
	}

	@Override
	public void initView(View rootView) {
		// TODO Auto-generated method stub
		pullToRefreshListView = (PullToRefreshListView) rootView
				.findViewById(R.id.nearList);
		nearListView = pullToRefreshListView.getRefreshableView();
		pullToRefreshListView.setOnRefreshListener(this);
		headView = getActivity().getLayoutInflater().inflate(
				R.layout.group_info, null, false);
		address = (TextView) headView.findViewById(R.id.group_address);
		groupAllNum = (TextView) headView
				.findViewById(R.id.totle_group_all_num);
		groupNearNum = (TextView) headView
				.findViewById(R.id.totle_group_near_num);
		nearListView.addHeaderView(headView);
		headView.findViewById(R.id.near_group).setOnClickListener(listener);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		personInfoAdapter = new PersonInfoAdapter(userList, getActivity());
		nearListView.setAdapter(personInfoAdapter);
		if (TextUtils.isEmpty(json)) {
			reshNear();
		} else {
			parseJson();
		}
		address.setText(GcApplication.getInstance().getAddress());
		groupNearNum.setText(GcApplication.getInstance().getCity() + "地区共有"
				+ count + "群组");
	}

	private void reshNear() {
		personBean = GcApplication.getInstance().getPersonBean();
		if (personBean == null) {
			return;
		}

		getNearGroupNum();
		HttpPostUtility.checkNearUser(personBean.getId(), GcApplication
				.getInstance().getLatitude(), GcApplication.getInstance()
				.getLongitude(), handler);
	}

	private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {

		@Override
		public void onFailure(int statusCode, Header[] headers,
				Throwable throwable, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(statusCode, headers, throwable, errorResponse);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			parseJson(response);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			super.onFinish();
			pullToRefreshListView.onRefreshComplete();
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}

	};

	private void parseJson(JSONObject response) {
		if (!HttpUtility.isSuccess(response)) {
			return;
		}

		if (json.equals(response.toString())) {
			Toast.makeText(GcApplication.getInstance(), R.string.no_new_notice,
					Toast.LENGTH_SHORT).show();
			return;
		}

		json = response.toString();
		UserInfoUtility.setNearUserJson(json);
		try {
			JSONObject nearUser = response.getJSONObject("nearuser");
			JSONArray jsonArray = nearUser.getJSONArray("resultlist");
			int length = jsonArray.length();
			userList.clear();
			for (int i = 0; i < length; i++) {
				PersonBean personBean = HttpUtility.getPersonBean(jsonArray
						.getJSONObject(i).toString());
				if (!TextUtils.isEmpty(personBean.getReflashtime())) {
					personBean.setCn_time(TimeUtility.getListTime(personBean
							.getReflashtime()));
				}

				try {
					personBean.setDistance(DistentsUtil.GetDistance(
							Double.valueOf(personBean.getCommercialLat()),
							Double.valueOf(personBean.getCommercialLon()),
							GcApplication.getInstance().getLatitude(),
							GcApplication.getInstance().getLongitude()));
				} catch (Exception e) {
					// TODO: handle exception
					AppLog.e("错误:" + e.getMessage());
				}

				userList.add(personBean);
			}
			personInfoAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parseJson() {

		try {
			JSONObject response = new JSONObject(json);
			if (!HttpUtility.isSuccess(response)) {
				return;
			}

			try {
				JSONObject nearUser = response.getJSONObject("nearuser");
				JSONArray jsonArray = nearUser.getJSONArray("resultlist");
				int length = jsonArray.length();
				userList.clear();
				for (int i = 0; i < length; i++) {
					PersonBean personBean = HttpUtility.getPersonBean(jsonArray
							.getJSONObject(i).toString());
					if (!TextUtils.isEmpty(personBean.getReflashtime())) {
						personBean.setCn_time(TimeUtility
								.getListTime(personBean.getReflashtime()));
					}

					try {
						personBean.setDistance(DistentsUtil.GetDistance(
								Double.valueOf(personBean.getCommercialLat()),
								Double.valueOf(personBean.getCommercialLon()),
								GcApplication.getInstance().getLatitude(),
								GcApplication.getInstance().getLongitude()));
					} catch (Exception e) {
						// TODO: handle exception
						AppLog.e("错误:" + e.getMessage());
					}

					userList.add(personBean);
				}
				personInfoAdapter.notifyDataSetChanged();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void getNearGroupNum() {
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				try {
					count = response.getString("groupcount");
					groupNearNum.setText(GcApplication.getInstance().getCity()
							+ "地区共有" + count + "群组");
					UserInfoUtility.setNearGroupCount(count);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				address.setText(GcApplication.getInstance().getAddress());
			}
		};

		HttpPostUtility.checkNearGroupNum(
				GcApplication.getInstance().getCity(), handler);

	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		String label = DateUtils.formatDateTime(getActivity(),
				System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		reshNear();
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.near_group:
				showNearGroup();
				break;

			default:
				break;
			}
		}
	};

	private void showNearGroup() {
		startActivity(new Intent(getActivity(), GroupNearListActivity.class));
	}

}
