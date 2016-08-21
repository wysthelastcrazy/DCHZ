package com.common.util;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 */
public class KeyBoardUtils {

	/**
	 * 显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showSoftKeyBroad(Context context, EditText editText) {
		try {
			InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			// only will trigger it if no physical keyboard is open
			mgr.showSoftInput(editText, mgr.SHOW_IMPLICIT);
			// mgr.showSoftInput(editText, 0);
			// mgr.toggleSoftInput(mgr.SHOW_IMPLICIT, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void hideSoftKeyBroad(Context context, EditText editText) {
		InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		// mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

	/**
	 * 显示软键盘，和上面的showSoftKeyBroad方法的区别在于，如果从其他activity返回的时候需要延迟一点才能显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showKeyBoardLater(final Context context, final EditText editText) {
		try {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					showSoftKeyBroad(context, editText);
				}
			}, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isSoftKeyActive(final Context context, final EditText editText) {
		InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		return mgr.isActive(editText);
	}
}
