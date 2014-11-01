package com.yyb.gcquan.support.debug;

import com.yyb.gcquan.BuildConfig;

import android.util.Log;

public class AppLog {

	private static final String TAG = AppLog.class.getSimpleName();

	public static void i(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void w(String msg) {
		if (BuildConfig.DEBUG) {
			Log.w(TAG, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.w(tag, msg);
		}
	}

	public static void e(String msg) {
		if (BuildConfig.DEBUG) {
			Log.e(TAG, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (BuildConfig.DEBUG) {
			Log.v(tag, msg);
		}
	}

	public static void i(String msg) {
		if (BuildConfig.DEBUG) {
			Log.i(TAG, msg);
		}
	}

}
