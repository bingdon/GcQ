package com.yyb.gcquan.ui.login;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.yyb.gcquan.R;
import com.yyb.gcquan.bean.UserAccountBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginDataAdapter extends BaseAdapter implements Filterable {

	private List<UserAccountBean> list;

	private Context context;

	private LayoutInflater inflater;

	private ArrayList<UserAccountBean> userAccountBeans;

	private FilterPhone pFilterPhone;

	private onDataClickListener listener;

	public void setListener(onDataClickListener listener) {
		this.listener = listener;
	}

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
		final int realPosition = position;
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
		holder.delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listener != null) {
					listener.onDeleteListener(realPosition);
				}
			}
		});
		return convertView;
	}

	public class ViewHolder {
		public TextView account;
		public ImageView delete;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		if (pFilterPhone == null) {
			pFilterPhone = new FilterPhone();
		}
		return pFilterPhone;
	}

	private class FilterPhone extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// TODO Auto-generated method stub
			FilterResults results = new FilterResults();

			if (userAccountBeans == null) {
				userAccountBeans = new ArrayList<UserAccountBean>(list);
			}

			if (constraint == null || constraint.length() == 0) {
				ArrayList<UserAccountBean> list = userAccountBeans;
				results.values = list;
				results.count = list.size();
			} else {
				String prefixString = constraint.toString().toLowerCase();

				ArrayList<UserAccountBean> unfilteredValues = userAccountBeans;
				int count = unfilteredValues.size();

				ArrayList<UserAccountBean> newValues = new ArrayList<UserAccountBean>(
						count);

				for (int i = 0; i < count; i++) {
					UserAccountBean pc = unfilteredValues.get(i);
					if (pc != null) {

						if (pc.getUsername() != null
								&& pc.getUsername().startsWith(prefixString)) {

							newValues.add(pc);
						}
					}
				}

				results.values = newValues;
				results.count = newValues.size();
			}

			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			// TODO Auto-generated method stub
			list = (List<UserAccountBean>) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}

	}

	public interface onDataClickListener {
		public void onDeleteListener(int position);
	}

}
