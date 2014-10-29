package com.yyb.gcquan.support.userdata;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import com.yyb.gcquan.app.GcApplication;
import com.yyb.gcquan.bean.PersonBean;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

public class UserInfoUtility {

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

}
