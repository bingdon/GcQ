package com.yyb.gcquan.support.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.yyb.gcquan.constants.ConstantS;
import com.yyb.gcquan.support.debug.AppLog;

public class HttpPostUtility {

	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void login(String tel, String password,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("tel", tel);
		params.put("password", password);
		client.post(ConstantS.LOGIN_URL, params, handler);
		AppLog.i("登陆:" + ConstantS.LOGIN_URL + "?" + params);
	}

	public static void checkNearUser(String id, double lat, double lon,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("id", id);
		params.put("lat", lat);
		params.put("lon", lon);
		client.post(ConstantS.NEAR_USER_URL, params, handler);
		AppLog.i("附近成员:" + ConstantS.NEAR_USER_URL + "?" + params);
	}

	public static void checkNearGroup(double lat, double lon,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("lat", lat);
		params.put("lon", lon);
		client.post(ConstantS.NEAR_GROUP_URL, params, handler);
		AppLog.i("附近群组:" + ConstantS.NEAR_GROUP_URL + "?" + params);
	}

	public static void checkNearGroupNum(String key,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("key", key);
		client.post(ConstantS.NEAR_GROUP_NUM_URL, params, handler);
		AppLog.i("附近群组:" + ConstantS.NEAR_GROUP_NUM_URL + "?" + params);
	}

	public static void getFriend(String id, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("id", id);
		client.post(ConstantS.GET_USER_FRIEND_URL, params, handler);
		AppLog.i("好友列表:" + ConstantS.GET_USER_FRIEND_URL + "?" + params);
	}

}
