package com.common.util;


import com.DCHZ.TYLINCN.commen.Global;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/***
 * 网络工具类
 */
public class NetUtils {

	public static boolean isNetworkConnected() {
		Context context = Global.mContext;
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

}
