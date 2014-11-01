package com.yyb.gcquan.ui.adapter;

import java.util.List;

import com.yyb.gcquan.R;
import com.yyb.gcquan.bean.PersonBean;
import com.yyb.gcquan.constants.ConstantS;
import com.yyb.gcquan.support.image.LoadImageUtility;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonInfoAdapter extends BaseAdapter {

	private List<PersonBean> list;

	private Context context;

	private LayoutInflater inflater;

	public PersonInfoAdapter(List<PersonBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.near_item, parent, false);
			holder.ditance = (TextView) convertView.findViewById(R.id.distance);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.headImageView = (ImageView) convertView
					.findViewById(R.id.head);
			holder.username = (TextView) convertView
					.findViewById(R.id.username);
			holder.userinfo = (TextView) convertView
					.findViewById(R.id.userinfo);
			holder.useraddress = (TextView) convertView
					.findViewById(R.id.useraddress);
			holder.usertype = (TextView) convertView
					.findViewById(R.id.usertype);
			holder.userequ = (TextView) convertView.findViewById(R.id.userequ);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		PersonBean personBean = list.get(position);
		LoadImageUtility.displayWebImage(
				ConstantS.IMAGE_URL + personBean.getHeadimage(),
				holder.headImageView);
		holder.ditance.setText(personBean.getDistance() + "km");
		holder.time.setText(personBean.getCn_time());
		holder.useraddress.setText(personBean.getAddress());
		holder.userequ.setText(personBean.getEquipment());
		if (!TextUtils.isEmpty(personBean.getType())) {
			holder.usertype.setText(personBean.getType());
		}
		if (!TextUtils.isEmpty(personBean.getInfo())) {
			holder.userinfo.setText(personBean.getInfo());
		}
		if (!TextUtils.isEmpty(personBean.getUsername())) {
			holder.username.setText(personBean.getUsername());
		}
		return convertView;
	}

	private class ViewHolder {
		public ImageView headImageView;
		public TextView username;
		public TextView userinfo;
		public TextView useraddress;
		public TextView userequ;
		public TextView time;
		public TextView ditance;
		public TextView usertype;

	}

}
