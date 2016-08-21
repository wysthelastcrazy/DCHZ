package com.DCHZ.TYLINCN.component;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.MainActivity;
import com.DCHZ.TYLINCN.entity.PShuXingEntity;
import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.ReflectUtils;
import com.common.util.KeyBoardUtils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class DataItem extends LinearLayout implements OnClickListener{

	private TextView text_date;
	private TextView text_week;
	private EditText edit_hour;
	public DataItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public DataItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public DataItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		LayoutInflater li=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.data_item,this,true);
		text_date=(TextView) this.findViewById(R.id.text_data);
		text_date.setOnClickListener(this);
		text_week=(TextView) this.findViewById(R.id.text_week);
		text_week.setOnClickListener(this);
		edit_hour=(EditText) this.findViewById(R.id.edit_hour);
		ReflectUtils.setEditCursorColor(edit_hour, 0);
		edit_hour.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if(arg1){
					KeyBoardUtils.showKeyBoardLater(getContext(), edit_hour);
//					MainActivity.hideTab();
				}else{
					KeyBoardUtils.hideSoftKeyBroad(getContext(), edit_hour);
//					MainActivity.show();
				}
			}
		});
	}
	public void setText(String date,String week){
		text_date.setText(date);
		text_week.setText(week);
	}
	public void setData(PShuXingEntity entity){
		if(entity==null){
			edit_hour.setEnabled(true);
			edit_hour.setText("");
			return;
		}
		if("True".equals(entity.ReadOnly)){
			edit_hour.setEnabled(false);
		}else{
			edit_hour.setEnabled(true);
		}
		if(!TextUtils.isEmpty(entity.GSTBZhengChang)){
			edit_hour.setText(entity.GSTBZhengChang);
		}else{
			edit_hour.setText("");
		}
		if(!TextUtils.isEmpty(entity.BgColor)){
			String color="#"+entity.BgColor;
			edit_hour.setTextColor(Color.parseColor(color));
		}
	}
	public String gethour(){
		if(!"0".equals(edit_hour.getText().toString())&&!TextUtils.isEmpty(edit_hour.getText().toString())){
			String str=text_date.getText()+"|"+edit_hour.getText().toString();
			MyLog.debug("dd", "[gethour]  str1:"+str);
			try {
				str=URLEncoder.encode(str, "GBK");
				MyLog.debug("dd", "[gethour]  str2:"+str);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}else{
			return null;
		}
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==text_date||view==text_week){
			KeyBoardUtils.hideSoftKeyBroad(getContext(), edit_hour);
//			MainActivity.show();
		}else{
//			MainActivity.hideTab();
		}
	}
}
