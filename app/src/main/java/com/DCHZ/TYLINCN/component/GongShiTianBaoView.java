package com.DCHZ.TYLINCN.component;


import java.util.ArrayList;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.PBetweenAndEndOfWeekEntity;
import com.DCHZ.TYLINCN.entity.PShuXingEntity;
import com.DCHZ.TYLINCN.listener.IGongshiTianBaoLisneter;
import com.DCHZ.TYLINCN.listener.IItemClickListner;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GongShiTianBaoView extends RelativeLayout implements OnClickListener {
	
	private TopView mTopView;
	private DataItem item1;
	private DataItem item2;
	private DataItem item3;
	private DataItem item4;
	private DataItem item5;
	private DataItem item6;
	private DataItem item7;
	
	private Button btn;
	private TextView text_back;
	private TextView text_this;
	private TextView text_next;
	private IGongshiTianBaoLisneter mLisneter;
	public GongShiTianBaoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initLayout();
	}
	public GongShiTianBaoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initLayout();
	}
	public GongShiTianBaoView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initLayout();
	}
	private void initLayout() {
		// TODO Auto-generated method stub
		LayoutInflater li=(LayoutInflater) Global.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.activity_gongshiguanli, this, true);
		mTopView=(TopView) this.findViewById(R.id.topView);
//		mTopView.setClickLisneter(mListener);
		
		item1=(DataItem) this.findViewById(R.id.item1);
		item2=(DataItem) this.findViewById(R.id.item2);
		item3=(DataItem) this.findViewById(R.id.item3);
		item4=(DataItem) this.findViewById(R.id.item4);
		item5=(DataItem) this.findViewById(R.id.item5);
		item6=(DataItem) this.findViewById(R.id.item6);
		item7=(DataItem) this.findViewById(R.id.item7);
		
		btn=(Button) this.findViewById(R.id.btn);
		btn.setOnClickListener(this);
		
		text_back=(TextView) this.findViewById(R.id.text_back);
		text_back.setOnClickListener(this);
		text_this=(TextView) this.findViewById(R.id.text_this);
		text_this.setOnClickListener(this);
		text_next=(TextView) this.findViewById(R.id.text_next);
		text_next.setOnClickListener(this);
	}
	public String getValue(){
		String value="";
		if(!TextUtils.isEmpty(item1.gethour())){
			value=value+item1.gethour()+",";
		}
		if(!TextUtils.isEmpty(item2.gethour())){
			value=value+item2.gethour()+",";
		}
		if(!TextUtils.isEmpty(item3.gethour())){
			value=value+item3.gethour()+",";
		}
		if(!TextUtils.isEmpty(item4.gethour())){
			value=value+item4.gethour()+",";
		}
		if(!TextUtils.isEmpty(item5.gethour())){
			value=value+item5.gethour()+",";
		}
		if(!TextUtils.isEmpty(item6.gethour())){
			value=value+item6.gethour()+",";
		}
		if(!TextUtils.isEmpty(item7.gethour())){
			value=value+item7.gethour()+",";
		}
		if(!TextUtils.isEmpty(value)){
			return value.substring(0, value.length()-1);
		}else{
			return value;
		}
	}
	public void setTopClickLisneter(IItemClickListner mListener){
		mTopView.setClickLisneter(mListener);
	}
	public void setBtnClickLisneter(IGongshiTianBaoLisneter mLisneter){
		this.mLisneter=mLisneter;
	}
	
	
	public void setData(ArrayList<PBetweenAndEndOfWeekEntity> mList){
		if(mList!=null&&mList.size()>0){
			item1.setText(mList.get(0).WeekDate01.split("\\|")[0], mList.get(0).WeekDate01.split("\\|")[1]);
			item2.setText(mList.get(0).WeekDate11.split("\\|")[0], mList.get(0).WeekDate11.split("\\|")[1]);
			item3.setText(mList.get(0).WeekDate21.split("\\|")[0], mList.get(0).WeekDate21.split("\\|")[1]);
			item4.setText(mList.get(0).WeekDate31.split("\\|")[0], mList.get(0).WeekDate31.split("\\|")[1]);
			item5.setText(mList.get(0).WeekDate41.split("\\|")[0], mList.get(0).WeekDate41.split("\\|")[1]);
			item6.setText(mList.get(0).WeekDate51.split("\\|")[0], mList.get(0).WeekDate51.split("\\|")[1]);
			item7.setText(mList.get(0).WeekDate61.split("\\|")[0], mList.get(0).WeekDate61.split("\\|")[1]);
		}
		
	}
	public void setColor(ArrayList<PShuXingEntity> ShuXing){
		if(ShuXing!=null&&ShuXing.size()>0){
			item1.setData(ShuXing.get(0));
			item2.setData(ShuXing.get(1));
			item3.setData(ShuXing.get(2));
			item4.setData(ShuXing.get(3));
			item5.setData(ShuXing.get(4));
			item6.setData(ShuXing.get(5));
			item7.setData(ShuXing.get(6));
		}else{
			item1.setData(null);
			item2.setData(null);
			item3.setData(null);
			item4.setData(null);
			item5.setData(null);
			item6.setData(null);
			item7.setData(null);
		}
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(mLisneter!=null){
			if(view==btn){
				mLisneter.btnClick();
			}
			if(view==text_back){
				mLisneter.backClick();
			}
			if(view==text_this){
				mLisneter.thisClick();
			}
			if(view==text_next){
				mLisneter.nextClick();
			}
		}
	}
}


