package com.yyb.gcquan.app;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yyb.gcquan.R;
import com.yyb.gcquan.bean.PersonBean;
import com.yyb.gcquan.support.userdata.UserInfoUtility;

import android.app.Application;
import android.graphics.Bitmap;
import android.text.TextUtils;

public class GcApplication extends Application {

	public static GcApplication instance;

	private PersonBean personBean;

	private static ImageLoader imageLoader = ImageLoader.getInstance();

	private static DisplayImageOptions options;

	private double Latitude = 0;

	private double Longitude = 0;

	private String address = "";

	private String city = "";

	public void setCity(String city) {
		UserInfoUtility.setCity(instance, city);
		this.city = city;
	}

	public String getCity() {
		if (TextUtils.isEmpty(city)) {
			city = UserInfoUtility.getCity(instance);
		}
		return city;
	}

	public void setAddress(String address) {
		UserInfoUtility.setAddress(instance, address);
		this.address = address;
	}

	public String getAddress() {
		if (TextUtils.isEmpty(address) && address.equals("null")) {
			address = UserInfoUtility.getAddress(instance);
		}
		return "" + address;
	}

	public void setLongitude(double longitude) {
		UserInfoUtility.setLongitude(instance, longitude);
		Longitude = longitude;
	}

	public double getLongitude() {
		if (Longitude == 0) {
			Longitude = UserInfoUtility.getLatitude(instance);
		}
		return Longitude;
	}

	public void setLatitude(double latitude) {
		UserInfoUtility.setLatitude(instance, latitude);
		Latitude = latitude;
	}

	public double getLatitude() {
		if (Latitude == 0) {
			Latitude = UserInfoUtility.getLatitude(instance);
		}
		return Latitude;
	}

	public static GcApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		create();
	}

	private void create() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// .memoryCache(new LruMemoryCache(maxSize))
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(false)
				.bitmapConfig(Bitmap.Config.ARGB_8888).cacheOnDisc(true)
				.considerExifParams(true).build();
	}

	public void setPersonBean(PersonBean personBean) {
		UserInfoUtility.savePersonInfo(personBean, instance);
		this.personBean = personBean;
	}

	public PersonBean getPersonBean() {
		return personBean;
	}

	public static ImageLoader getImageLoader() {
		return imageLoader;
	}

	public static DisplayImageOptions getOptions() {
		return options;
	}
}
