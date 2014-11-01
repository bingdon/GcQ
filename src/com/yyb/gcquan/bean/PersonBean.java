package com.yyb.gcquan.bean;

import java.io.Serializable;

import com.yyb.gcquan.app.GcApplication;
import com.yyb.gcquan.support.debug.AppLog;
import com.yyb.gcquan.support.distance.DistentsUtil;
import com.yyb.gcquan.support.time.TimeUtility;

import android.text.TextUtils;

public class PersonBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accept;

	private String address;

	private String age;

	private String birthday;

	private String business;

	private String businessinfo;

	private String token;

	private String commercialLat;

	private String commercialLon;

	private String companyname;

	private String createtime;

	private String equipment;

	private String headimage;

	private String hobby;

	private String id;

	private String info;

	private String lastlogintime;

	private String place;

	private String pnumber;

	private String rplace;

	private String sign;

	private String tel;

	private String type;

	private String username;

	private String reflashtime;

	private double distance;

	private String Cn_time;

	public void setCn_time(String cn_time) {
		Cn_time = cn_time;
	}

	public String getCn_time() {
		return "" + Cn_time;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setReflashtime(String reflashtime) {
		this.reflashtime = reflashtime;
	}

	public String getReflashtime() {
		return reflashtime;
	}

	public String getToken() {
		return "" + token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBusinessinfo() {
		return businessinfo;
	}

	public void setBusinessinfo(String businessinfo) {
		this.businessinfo = businessinfo;
	}

	public String getAccept() {
		return "" + accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getAddress() {
		return "" + address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return "" + age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthday() {
		return "" + birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBusiness() {
		return "" + business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getCommercialLat() {
		return "" + commercialLat;
	}

	public void setCommercialLat(String commercialLat) {
		this.commercialLat = commercialLat;
	}

	public String getCommercialLon() {
		return "" + commercialLon;
	}

	public void setCommercialLon(String commercialLon) {
		this.commercialLon = commercialLon;
	}

	public String getCompanyname() {
		return "" + companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCreatetime() {
		return "" + createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getHeadimage() {
		return "" + headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getHobby() {
		return "" + hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getId() {
		return "" + id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfo() {
		if (TextUtils.isEmpty(info)) {
			return "";
		}
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getLastlogintime() {
		return "" + lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getPlace() {
		return "" + place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPnumber() {
		return "" + pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public String getRplace() {
		return "" + rplace;
	}

	public void setRplace(String rplace) {
		this.rplace = rplace;
	}

	public String getSign() {
		return "" + sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTel() {
		return "" + tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return "" + username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEquipment() {
		return "" + equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o == null) {
			return false;
		}
		PersonBean personBean = (PersonBean) o;

		if (getReflashtime().equals(personBean.getReflashtime())
				&& getId().equals(personBean.getId())
				&& getCommercialLat().equals(personBean.getCommercialLat())
				&& getUsername().equals(personBean.getUsername())
				&& getHeadimage().equals(personBean.getHeadimage())
				&& getSign().equals(personBean.getSign())) {
			return true;
		}
		return false;
	}

}
