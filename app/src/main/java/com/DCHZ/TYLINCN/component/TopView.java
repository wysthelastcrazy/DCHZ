package com.DCHZ.TYLINCN.component;



import java.util.ArrayList;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.listener.IItemClickListner;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class TopView extends LinearLayout implements OnClickListener{
	public static final int TYPE_ZHIJIE=1;
	public static final int TYPE_JIANJIE=2;
	
	public static final int TYPE_NAME=3;
	public static final int TYPE_JIEDUAN=4;
	public static final int TYPE_ZHUANYE=5;
	public static final int TYPE_NEIRONG=6;
	
	
	private TextView txtZhiJie;
	private TextView txtJianJie;
	private TextView txtkey1;
	private TextView txtkey2;
	private TextView txtkey3;
	private TextView txtkey4;
	private TextView txtvalue1;
	private TextView txtvalue2;
	private TextView txtvalue3;
	private TextView txtvalue4;
	private LinearLayout layoutItem3;
	private LinearLayout layoutItem2;
	private View line_jieduan;
	private View line_zhuanye;
	
	private IItemClickListner mListener;
	private int mType=TYPE_ZHIJIE;
	public TopView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public TopView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public TopView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.top_view,this,true);
		txtZhiJie=(TextView) this.findViewById(R.id.text_zhijie);
		txtZhiJie.setEnabled(false);
		txtZhiJie.setOnClickListener(this);
		txtJianJie=(TextView) this.findViewById(R.id.text_jianjie);
		txtJianJie.setOnClickListener(this);
		txtvalue1=(TextView) this.findViewById(R.id.text_value1);
		txtvalue1.setOnClickListener(this);
		txtvalue2=(TextView) this.findViewById(R.id.text_value2);
		txtvalue2.setOnClickListener(this);
		txtvalue3=(TextView) this.findViewById(R.id.text_value3);
		txtvalue3.setOnClickListener(this);
		txtvalue4=(TextView) this.findViewById(R.id.text_value4);
		txtvalue4.setOnClickListener(this);
		
		txtkey1=(TextView) this.findViewById(R.id.text_key1);
		txtkey2=(TextView) this.findViewById(R.id.text_key2);
		txtkey3=(TextView) this.findViewById(R.id.text_key3);
		txtkey4=(TextView) this.findViewById(R.id.text_key4);
		
		layoutItem3=(LinearLayout) this.findViewById(R.id.layout_item3);
		layoutItem2=(LinearLayout) this.findViewById(R.id.layout_item2);
		line_jieduan=this.findViewById(R.id.line_jieduan);
		line_zhuanye=this.findViewById(R.id.line_zhuanye);
	}
	
	public void setClickLisneter(IItemClickListner mListener){
		this.mListener=mListener;
	}
	
	public ArrayList<String> getData(){
		ArrayList<String> strs=new ArrayList<String>();
		String str1=txtvalue1.getText().toString();
		if(!TextUtils.isEmpty(str1)){
			strs.add(str1);
		}
		String str2=txtvalue2.getText().toString();
		if(!TextUtils.isEmpty(str2)){
			strs.add(str2);
		}
		String str3=txtvalue3.getText().toString();
		if(!TextUtils.isEmpty(str3)){
			strs.add(str3);
		}
		String str4=txtvalue4.getText().toString();
		if(!TextUtils.isEmpty(str4)){
			strs.add(str4);
		}
		return strs;
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==txtZhiJie){
			updata(TYPE_ZHIJIE);
			txtZhiJie.setEnabled(false);
			txtJianJie.setEnabled(true);
		}else if(view==txtJianJie){
			updata(TYPE_JIANJIE);
			txtZhiJie.setEnabled(true);
			txtJianJie.setEnabled(false);
		}else if(view==txtvalue1){
			if (mListener!=null) {
				mListener.itemClick(txtvalue1,TYPE_NAME,mType);
			}
		}else if(view==txtvalue2){
			if (mListener!=null) {
				mListener.itemClick(txtvalue2,TYPE_JIEDUAN,mType);
			}
		}else if(view==txtvalue3){
			if (mListener!=null) {
				mListener.itemClick(txtvalue3,TYPE_ZHUANYE,mType);
			}
		}else if(view==txtvalue4){
			if (mListener!=null) {
				mListener.itemClick(txtvalue4,TYPE_NEIRONG,mType);
			}
		}
	}
	
	private void updata(int type){
		txtvalue1.setText("");
		txtvalue2.setText("");
		txtvalue3.setText("");
		txtvalue4.setText("");
		mType=type;
		switch (type) {
		case TYPE_ZHIJIE:
//			txtkey2.setText("内容");
			layoutItem2.setVisibility(View.VISIBLE);
			layoutItem3.setVisibility(View.VISIBLE);
			line_jieduan.setVisibility(View.VISIBLE);
			line_zhuanye.setVisibility(View.VISIBLE);
			break;
		case TYPE_JIANJIE:
//			txtkey2.setText("工作内容");
			layoutItem2.setVisibility(View.GONE);
			layoutItem3.setVisibility(View.GONE);
			line_jieduan.setVisibility(View.GONE);
			line_zhuanye.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}
}
