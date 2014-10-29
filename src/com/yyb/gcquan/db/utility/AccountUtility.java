package com.yyb.gcquan.db.utility;

import java.util.ArrayList;
import java.util.List;

import com.yyb.gcquan.bean.UserAccountBean;
import com.yyb.gcquan.db.GcDatabase;
import com.yyb.gcquan.db.table.AccountTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class AccountUtility implements MethodInterface {

	public AccountUtility(Context context) {
		super();
		// TODO Auto-generated constructor stub
		GcDatabase.getDatebase(context);
	}

	@Override
	public long insert(Object object) {
		// TODO Auto-generated method stub
		UserAccountBean userAccountBean = (UserAccountBean) object;
		long id = -1;
		try {
			ContentValues values = new ContentValues();
			values.put(AccountTable.USER_NAME, userAccountBean.getUsername());
			values.put(AccountTable.PASSWORD, userAccountBean.getPassword());
			// 调用方法插入数据
			id = GcDatabase.proDatabase.insert(AccountTable.TABLE_NAME, null,
					values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;

	}

	@Override
	public long update(Object object) {
		// TODO Auto-generated method stub
		UserAccountBean userAccountBean = (UserAccountBean) object;
		long updatepostion = 0;
		try {
			ContentValues values = new ContentValues();
			values.put(AccountTable.USER_NAME, userAccountBean.getUsername());
			values.put(AccountTable.PASSWORD, userAccountBean.getPassword());
			String where = AccountTable.USER_NAME + " = ?";
			String[] whereValue = { String.valueOf(userAccountBean
					.getUsername()) };
			updatepostion = GcDatabase.proDatabase.update(
					AccountTable.TABLE_NAME, values, where, whereValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updatepostion;

	}

	@Override
	public long delete(Object object) {
		// TODO Auto-generated method stub
		UserAccountBean userAccountBean = (UserAccountBean) object;
		int del = -1;
		try {
			String where = AccountTable._ID + " = ?";
			String[] whereArgs = { String.valueOf(userAccountBean.get_id()) };
			del = GcDatabase.proDatabase.delete(AccountTable.TABLE_NAME, where,
					whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;

	}

	@Override
	public long deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object query() {
		// TODO Auto-generated method stub
		List<UserAccountBean> list = new ArrayList<>();
		Cursor cursor = null;
		try {
			cursor = GcDatabase.proDatabase.query(AccountTable.TABLE_NAME,
					null, null, null, null, null, "_ID desc");
			int idIndex = cursor.getColumnIndex(AccountTable._ID);
			int nameIndex = cursor.getColumnIndex(AccountTable.USER_NAME);
			int passwordIndex = cursor.getColumnIndex(AccountTable.PASSWORD);
			for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
					.moveToNext()) {
				UserAccountBean userAccountBean = new UserAccountBean();
				userAccountBean.set_id(cursor.getInt(idIndex));
				userAccountBean.setUsername(cursor.getString(nameIndex));
				userAccountBean.setPassword(cursor.getString(passwordIndex));

				list.add(userAccountBean);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				// 关闭游标
				cursor.close();
			}

		}

		return list;

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (GcDatabase.proDatabase != null && GcDatabase.proDatabase.isOpen()) {
			GcDatabase.proDatabase.close();
			GcDatabase.proDatabase = null;
		}

	}

}
