package com.yyb.gcquan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GcDatabase {
	public static SQLiteDatabase proDatabase;

	public static void getDatebase(Context context) {
		if (null == proDatabase) {
			GcDbHelper proJectDbHelper = new GcDbHelper(context);
			proDatabase = proJectDbHelper.getWritableDatabase();
		}

	}
}
