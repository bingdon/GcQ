package com.yyb.gcquan.db.utility;

import java.util.ArrayList;
import java.util.List;

import com.yyb.gcquan.bean.PersonBean;
import com.yyb.gcquan.db.GcDatabase;
import com.yyb.gcquan.db.table.FriendTable;
import com.yyb.gcquan.support.o2b.ObjectUtility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class FriendDbUtility implements MethodInterface {

	public FriendDbUtility(Context context) {
		GcDatabase.getDatebase(context);
	}

	@Override
	public long insert(Object object) {
		// TODO Auto-generated method stub
		PersonBean personBean = (PersonBean) object;
		long id = -1;
		try {
			ContentValues values = new ContentValues();
			values.put(FriendTable.FRI_ID, personBean.getId());
			values.put(FriendTable.RESH_TIME, personBean.getReflashtime());
			values.put(FriendTable.DATA, ObjectUtility.Object2Byte(personBean));
			id = GcDatabase.proDatabase.insert(FriendTable.TABLE_NAME, null,
					values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public long update(Object object) {
		// TODO Auto-generated method stub
		PersonBean personBean = (PersonBean) object;
		long updatepostion = 0;
		try {
			ContentValues values = new ContentValues();
			values.put(FriendTable.FRI_ID, personBean.getId());
			values.put(FriendTable.RESH_TIME, personBean.getReflashtime());
			values.put(FriendTable.DATA, ObjectUtility.Object2Byte(personBean));
			String where = FriendTable.FRI_ID + " = ?" + " and "
					+ FriendTable.RESH_TIME + " = ?";
			String[] whereValue = { String.valueOf(personBean.getId()),
					personBean.getReflashtime() };
			updatepostion = GcDatabase.proDatabase.update(
					FriendTable.TABLE_NAME, values, where, whereValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updatepostion;
	}

	@Override
	public long delete(Object object) {
		// TODO Auto-generated method stub
		PersonBean personBean = (PersonBean) object;
		int del = -1;
		try {
			String where = FriendTable.FRI_ID + " = ?";
			String[] whereArgs = { String.valueOf(personBean.getId()) };
			del = GcDatabase.proDatabase.delete(FriendTable.TABLE_NAME, where,
					whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return del;
	}

	@Override
	public long deleteAll() {
		// TODO Auto-generated method stub
		long del = -1;

		try {
			del = GcDatabase.proDatabase.delete(FriendTable.TABLE_NAME, null,
					null);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return del;
	}

	@Override
	public Object query() {
		// TODO Auto-generated method stub
		List<PersonBean> list = new ArrayList<>();
		Cursor cursor = null;
		try {
			cursor = GcDatabase.proDatabase.query(FriendTable.TABLE_NAME, null,
					null, null, null, null, "_ID desc");
			int friid = cursor.getColumnIndex(FriendTable.FRI_ID);
			int personIndex = cursor.getColumnIndex(FriendTable.DATA);
			for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
					.moveToNext()) {
				PersonBean personBean = ObjectUtility.Byte2PersonBean(cursor
						.getBlob(personIndex));
				if (personBean != null) {
					list.add(personBean);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				// πÿ±’”Œ±Í
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
