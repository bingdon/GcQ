package com.yyb.gcquan.db;

import com.yyb.gcquan.db.table.AccountTable;
import com.yyb.gcquan.db.table.FriendTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GcDbHelper extends SQLiteOpenHelper {

	private static final String NAME = "gcq.db";

	private static final int VERSION = 1;

	public static final String CREATE_USER_DB = "CREATE TABLE "
			+ AccountTable.TABLE_NAME + "(" + AccountTable._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ AccountTable.USER_NAME + " TEXT," + AccountTable.PASSWORD
			+ " TEXT" + ");";;

	public static final String CREATE_FRIEND_DB = "CREATE TABLE "
			+ FriendTable.TABLE_NAME + "(" + FriendTable._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
			+ FriendTable.FRI_ID + " TEXT," + FriendTable.RESH_TIME + " TEXT,"
			+ FriendTable.DATA + " BLOB" + ");";;

	public GcDbHelper(Context context) {
		super(context, NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_USER_DB);
		db.execSQL(CREATE_FRIEND_DB);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
