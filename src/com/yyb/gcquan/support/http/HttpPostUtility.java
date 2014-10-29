package com.yyb.gcquan.support.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yyb.gcquan.constants.ConstantS;

public class HttpPostUtility {

	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void login(String tel, String password,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("tel", tel);
		params.put("password", password);
		client.post(ConstantS.LOGIN_URL, params, handler);
	}

}
