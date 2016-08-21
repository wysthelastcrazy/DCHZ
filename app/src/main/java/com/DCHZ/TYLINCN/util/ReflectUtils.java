package com.DCHZ.TYLINCN.util;

import java.lang.reflect.Field;

import com.DCHZ.TYLINCN.R;
import com.common.util.DeviceUtil;

import android.widget.EditText;
import android.widget.TextView;


/***
 * 反射工具类
 * @date 2015/09/16
 */
public class ReflectUtils {
	private static final String TAG = "ReflectUtils";
	
	/***
	 * 设置输入框颜色
	 */
	public static final void setEditCursorColor(EditText editTxt,int c){
		int sdkVer = DeviceUtil.getAndroidSDKVersion();
		if(sdkVer >= 12){
			try {
			    Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
			    f.setAccessible(true);
			    f.set(editTxt,R.drawable.rectangle_blue_line_color);
			} catch (Exception ee) {
				MyLog.error(TAG,ee);
			}
		}
	}
}
