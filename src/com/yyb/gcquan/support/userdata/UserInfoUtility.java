package com.yyb.gcquan.support.userdata;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

import com.yyb.gcquan.app.GcApplication;
import com.yyb.gcquan.bean.PersonBean;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

public class UserInfoUtility {

	private static final String TAG = UserInfoUtility.class.getSimpleName();

	private static final String LATTITUDE = "lat";

	private static final String LONGTITUDE = "lon";

	private static final String ADDRESS = "address";

	private static final String CITY = "city";

	private static final String NEAR_USER = "near_user";

	private static final String NEAR_GROUP_COUNT = "near_group_count";

	/**
	 * 保存信息
	 * 
	 * @param info
	 * @param context
	 */
	public static void savePersonInfo(PersonBean info, Context context) {
		SharedPreferences preferences = context.getSharedPreferences("Login",
				Context.MODE_PRIVATE);

		Editor editor = preferences.edit();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream infoStream = new ObjectOutputStream(baos);
			infoStream.writeObject(info);
			String infobase64 = Base64.encodeToString(baos.toByteArray(),
					Base64.DEFAULT);
			editor.putString("personinfo", infobase64);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 提交修改
		editor.commit();
	}

	/**
	 * 获取信息
	 */
	public static void getPersonInfo(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"Login", Context.MODE_PRIVATE);
		String personstr = sharedPreferences.getString("personinfo", "");// //getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
		if (!TextUtils.isEmpty(personstr)) {
			byte[] bytepersonbase64 = Base64.decode(personstr.getBytes(),
					Base64.DEFAULT);
			ByteArrayInputStream bis = new ByteArrayInputStream(
					bytepersonbase64);
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(bis);
				try {
					PersonBean myPersonBean = (PersonBean) ois.readObject();
					GcApplication.getInstance().setPersonBean(myPersonBean);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 判断是否存在
	 * 
	 * @param list
	 * @param personBean
	 * @return
	 */
	public static boolean isHasByPerson(List<PersonBean> list,
			PersonBean personBean) {
		int length = list.size();
		for (int i = 0; i < length; i++) {
			if (list.get(i).equals(personBean)) {
				return true;
			}
		}
		return false;
	}

	public static boolean setLatitude(Context context, double lat) {
		SharedPreferences preferences = context.getSharedPreferences(LATTITUDE,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(LATTITUDE, "" + lat);
		return editor.commit();
	}

	public static double getLatitude(Context context) {
		double lat = 0;
		SharedPreferences preferences = context.getSharedPreferences(TAG,
				Context.MODE_PRIVATE);
		try {
			lat = Double.valueOf(preferences.getString(LATTITUDE, "0"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lat;
	}

	public static boolean setLongitude(Context context, double lat) {
		SharedPreferences preferences = context.getSharedPreferences(TAG,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(LONGTITUDE, "" + lat);
		return editor.commit();
	}

	public static double getLongitude(Context context) {
		double lat = 0;
		SharedPreferences preferences = context.getSharedPreferences(TAG,
				Context.MODE_PRIVATE);
		try {
			lat = Double.valueOf(preferences.getString(LONGTITUDE, "0"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lat;
	}

	public static boolean setAddress(Context context, String address) {
		SharedPreferences preferences = context.getSharedPreferences(TAG,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(ADDRESS, address);
		return editor.commit();
	}

	public static String getAddress(Context context) {
		String address = "";
		SharedPreferences preferences = context.getSharedPreferences(TAG,
				Context.MODE_PRIVATE);
		address = preferences.getString(ADDRESS, "");
		return address;

	}

	public static boolean setCity(Context context, String city) {
		SharedPreferences preferences = context.getSharedPreferences(TAG,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(CITY, city);
		return editor.commit();
	}

	public static String getCity(Context context) {
		String city = "";
		SharedPreferences preferences = context.getSharedPreferences(TAG,
				Context.MODE_PRIVATE);
		city = preferences.getString(CITY, "");
		return city;

	}

	public static boolean setNearUserJson(String json) {
		SharedPreferences preferences = GcApplication.getInstance()
				.getSharedPreferences(TAG, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(NEAR_USER, json);
		return editor.commit();
	}

	public static String getNearUserJson() {
		String json = "";
		SharedPreferences preferences = GcApplication.getInstance()
				.getSharedPreferences(TAG, Context.MODE_PRIVATE);
		json = preferences.getString(NEAR_USER, "");
		return json;
	}

	public static boolean setNearGroupCount(String count) {
		SharedPreferences preferences = GcApplication.getInstance()
				.getSharedPreferences(TAG, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(NEAR_GROUP_COUNT, count);
		return editor.commit();
	}

	public static String getNearGroupCount() {
		String count = "0";
		SharedPreferences preferences = GcApplication.getInstance()
				.getSharedPreferences(TAG, Context.MODE_PRIVATE);
		count = preferences.getString(NEAR_GROUP_COUNT, "0");
		return count;
	}

}
