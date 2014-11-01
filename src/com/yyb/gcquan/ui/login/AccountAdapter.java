package com.yyb.gcquan.ui.login;

import java.util.List;

import com.yyb.gcquan.R;
import com.yyb.gcquan.bean.UserAccountBean;
import com.yyb.gcquan.ui.login.LoginDataAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountAdapter extends ArrayAdapter<UserAccountBean> {
	private List<UserAccountBean> list;

	private Context context;

	private LayoutInflater inflater;

	private int resource;

	public AccountAdapter(Context context, int resource,
			List<UserAccountBean> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.list = objects;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.resource = resource;
	}

	@Override
	public int getPosition(UserAccountBean item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
			convertView = inflater.inflate(resource, parent, false);
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
