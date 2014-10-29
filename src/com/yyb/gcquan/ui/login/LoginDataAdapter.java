package com.yyb.gcquan.ui.login;

import java.util.List;

import com.yyb.gcquan.R;
import com.yyb.gcquan.bean.UserAccountBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginDataAdapter extends BaseAdapter {

	private List<UserAccountBean> list;

	private Context context;

	private LayoutInflater inflater;

	public LoginDataAdapter(List<UserAccountBean> list, Context context) {
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
			convertView = inflater.inflate(R.layout.login_item, parent, false);
			holder.account = (TextView) convertView.findViewById(R.id.account);
			holder.delete = (ImageView) convertView.findViewById(R.id.delete);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.account.setText(list.get(position).getUsername());
		return convertView;
	}

	public class ViewHolder {
		public TextView account;
		public ImageView delete;
	}

}
