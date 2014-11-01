package com.yyb.gcquan.constants;

public interface ConstantS {

	public static final String BAIDU_APP_ID = "bHqVbOCUBoAAMcl1Dkrokx81";

	public static final String URL = "http://www.gcquan.com/project/";

	public static final String BASE_URL = URL + "api/";
	/**
	 * 登陆
	 */
	public static final String LOGIN_URL = BASE_URL + "findUser";
	/**
	 * 附近成员
	 */
	public static final String NEAR_USER_URL = BASE_URL + "nearUser";
	/**
	 * 图片地址
	 */
	public static final String IMAGE_URL = URL + "upload";
	/**
	 * 附近群组查寻
	 */
	public static final String NEAR_GROUP_URL = BASE_URL + "nearGroup";
	/**
	 * 附近群组个数
	 */
	public static final String NEAR_GROUP_NUM_URL = BASE_URL + "countGroup";
	/**
	 * 获取好友列表
	 */
	public static final String GET_USER_FRIEND_URL = BASE_URL + "findFriends";

}
