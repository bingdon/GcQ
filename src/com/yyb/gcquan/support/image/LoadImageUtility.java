package com.yyb.gcquan.support.image;

import com.yyb.gcquan.app.GcApplication;

import android.widget.ImageView;

public class LoadImageUtility {

	public static void displayWebImage(String url, ImageView imageView) {
		GcApplication.getImageLoader().displayImage(url, imageView,
				GcApplication.getOptions());
	}

	public static void displaySdImage(String url, ImageView imageView) {
		GcApplication.getImageLoader().displayImage("file://" + url, imageView,
				GcApplication.getOptions());
	}

}
