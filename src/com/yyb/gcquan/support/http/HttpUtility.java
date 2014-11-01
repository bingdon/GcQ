package com.yyb.gcquan.support.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.yyb.gcquan.bean.PersonBean;

public class HttpUtility {

	public static boolean isSuccess(JSONObject response) {
		boolean success = false;
		try {
			if (response.getInt("result") == 1) {
				success = true;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;

	}

	public static PersonBean getPersonBean(String object) {
		PersonBean personBean = new PersonBean();
		try {
			personBean = new Gson().fromJson(object, PersonBean.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return personBean;
	}

}
