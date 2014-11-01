package com.yyb.gcquan.ui.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.yyb.gcquan.MainActivity;
import com.yyb.gcquan.R;
import com.yyb.gcquan.app.GcApplication;
import com.yyb.gcquan.bean.PersonBean;
import com.yyb.gcquan.bean.UserAccountBean;
import com.yyb.gcquan.constants.ConstantS;
import com.yyb.gcquan.db.utility.AccountUtility;
import com.yyb.gcquan.support.debug.AppLog;
import com.yyb.gcquan.support.http.HttpPostUtility;
import com.yyb.gcquan.support.http.HttpUtility;
import com.yyb.gcquan.support.image.LoadImageUtility;
import com.yyb.gcquan.support.userdata.UserInfoUtility;
import com.yyb.gcquan.ui.abstractactivity.ActivityInterface;
import com.yyb.gcquan.ui.login.LoginDataAdapter.onDataClickListener;

import android.R.mipmap;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

public class LoginActivity extends Activity implements ActivityInterface,
		OnItemClickListener, onDataClickListener {

	private static final String TAG = LoginActivity.class.getSimpleName();
	private MultiAutoCompleteTextView accountEditText;
	private EditText passwordEditText;
	private List<UserAccountBean> list = new ArrayList<>();
	private AccountUtility accountUtility;
	private LoginDataAdapter adapter;
	private UserAccountBean userAccountBean = new UserAccountBean();
	private ProgressDialog progressDialog;
	private ImageView headImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		initData();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		accountEditText = (MultiAutoCompleteTextView) findViewById(R.id.account);
		passwordEditText = (EditText) findViewById(R.id.password);
		headImageView = (ImageView) findViewById(R.id.head);
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getString(R.string.logining));
		findViewById(R.id.login).setOnClickListener(listener);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		accountUtility = new AccountUtility(this);
		list = (List<UserAccountBean>) accountUtility.query();
		if (list != null) {
			adapter = new LoginDataAdapter(list, this);
			accountEditText.setAdapter(adapter);
			accountEditText.setThreshold(1);
			accountEditText
					.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
			accountEditText.setOnItemClickListener(this);
			adapter.setListener(this);
		}

		if (null != GcApplication.getInstance().getPersonBean()) {
			AppLog.i(TAG, "Í·ÏñµØÖ·:"
					+ ConstantS.IMAGE_URL
					+ GcApplication.getInstance().getPersonBean()
							.getHeadimage());
			LoadImageUtility.displayWebImage(ConstantS.IMAGE_URL
					+ GcApplication.getInstance().getPersonBean()
							.getHeadimage(), headImageView);
		}

	}

	private void attmpetLogin() {
		String accountString = accountEditText.getText().toString();
		if (TextUtils.isEmpty(accountString)) {
			accountEditText.setError(getString(R.string.account_hint));
			accountEditText.startAnimation(AnimationUtils.loadAnimation(this,
					R.anim.shake));
			return;
		}

		String passwordString = passwordEditText.getText().toString();
		if (TextUtils.isEmpty(passwordString)) {
			passwordEditText.setError(getString(R.string.password_hint));
			passwordEditText.startAnimation(AnimationUtils.loadAnimation(this,
					R.anim.shake));
			return;
		}

		userAccountBean.setUsername(accountString);
		userAccountBean.setPassword(passwordString);

		HttpPostUtility.login(accountString, passwordString, handler);

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
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			progressDialog.show();
		}

		public void onFinish() {
			super.onFinish();
			progressDialog.dismiss();
		};

	};

	private void parseJson(JSONObject response) {
		AppLog.i(TAG, "µÇÂ½·µ»Ø:" + response);
		if (HttpUtility.isSuccess(response)) {
			try {
				JSONObject obj = response.getJSONObject("user");
				PersonBean personBean = HttpUtility.getPersonBean(obj
						.toString());
				GcApplication.getInstance().setPersonBean(personBean);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updata();
			showMain();
		}
	}

	private void updata() {
		long a = accountUtility.update(userAccountBean);
		if (a == 0) {
			accountUtility.insert(userAccountBean);
		}
	}

	private void showMain() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.login:
				attmpetLogin();
				break;

			default:
				break;
			}

		}

	};

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		getAccountByPosition(position);
	}

	private void getAccountByPosition(int position) {
		UserAccountBean userAccountBean = list.get(position);
		if (null != userAccountBean) {
			accountEditText.setText(userAccountBean.getUsername());
			passwordEditText.setText(userAccountBean.getPassword());
		}
	}

	@Override
	public void onDeleteListener(int position) {
		// TODO Auto-generated method stub
		try {
			accountUtility.delete(list.get(position));
			list.remove(position);
		} catch (Exception e) {
			// TODO: handle exception
		}
		adapter.notifyDataSetChanged();
	}

}
