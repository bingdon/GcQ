package com.yyb.gcquan.ui.contacts;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yyb.gcquan.R;
import com.yyb.gcquan.app.GcApplication;
import com.yyb.gcquan.bean.PersonBean;
import com.yyb.gcquan.db.utility.FriendDbUtility;
import com.yyb.gcquan.support.debug.AppLog;
import com.yyb.gcquan.support.distance.DistentsUtil;
import com.yyb.gcquan.support.http.HttpPostUtility;
import com.yyb.gcquan.support.http.HttpUtility;
import com.yyb.gcquan.support.time.TimeUtility;
import com.yyb.gcquan.ui.abstractfragment.BaseListFragment;
import com.yyb.gcquan.ui.adapter.PersonInfoAdapter;

public class ContactsFragment extends BaseListFragment implements
		OnRefreshListener<ListView> {

	public static ContactsFragment newInstance(int position) {
		ContactsFragment contactsFragment = new ContactsFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		contactsFragment.setArguments(bundle);
		return contactsFragment;
	}

	private View headView;
	private List<PersonBean> friendList = new ArrayList<>();
	private FriendDbUtility friendDbUtility;
	private PersonInfoAdapter personInfoAdapter;
	private String json = "";

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_contacts, container,
				false);
		initView(rootView);
		return rootView;
	}

	@Override
	public void initView(@Nullable View rootView) {
		// TODO Auto-generated method stub
		super.initView(rootView);
		pullToRefreshListView.setOnRefreshListener(this);
		headView = getActivity().getLayoutInflater().inflate(
				R.layout.friend_head_lay, null);
		listView.addHeaderView(headView);
		initData();
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		getLastData();
	}

	@SuppressWarnings("unchecked")
	private void getLastData() {
		if (friendDbUtility == null) {
			friendDbUtility = new FriendDbUtility(getActivity());
		}
		friendList = (List<PersonBean>) friendDbUtility.query();
		if (friendList == null) {
			friendList = new ArrayList<>();
		}

		personInfoAdapter = new PersonInfoAdapter(friendList, getActivity());
		listView.setAdapter(personInfoAdapter);
		if (friendList.size() == 0) {
			getFriendS();
		}
	}

	private void getFriendS() {
		if (null == GcApplication.getInstance().getPersonBean()) {
			return;
		}
		HttpPostUtility.getFriend(GcApplication.getInstance().getPersonBean()
				.getId(), getFirnedHandler);
	}

	private JsonHttpResponseHandler getFirnedHandler = new JsonHttpResponseHandler() {
		public void onSuccess(int statusCode, org.apache.http.Header[] headers,
				org.json.JSONObject response) {
			super.onSuccess(statusCode, headers, response);
			parseJson(response);
		};

		public void onFinish() {
			if (pullToRefreshListView != null) {
				pullToRefreshListView.onRefreshComplete();
			}
		};

		public void onStart() {
		};
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
		try {
			JSONObject nearUser = response.getJSONObject("friends");
			JSONArray jsonArray = nearUser.getJSONArray("resultlist");
			int length = jsonArray.length();
			friendList.clear();
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
					AppLog.e("´íÎó:" + e.getMessage());
				}
				update(personBean);
				friendList.add(personBean);
			}
			personInfoAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void update(PersonBean personBean) {
		if (friendDbUtility == null) {
			friendDbUtility = new FriendDbUtility(getActivity());
		}

		long update = friendDbUtility.update(personBean);
		if (update < 1) {
			friendDbUtility.insert(personBean);
		}

	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		getFriendS();
	}

}
