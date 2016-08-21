package com.DCHZ.TYLINCN.component;

import java.util.List;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.commen.Common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListTopItemView extends LinearLayout{
	private TextView text_item1_key;
	private TextView text_item1_value;
	private TextView text_item2_key;
	private TextView text_item2_value;
	private TextView text_item3_key;
	private TextView text_item3_value;
	private TextView text_item4_key;
	private TextView text_item4_value;
	private TextView text_item5_key;
	private TextView text_item5_value;
	private TextView text_item6_key;
	private TextView text_item6_value;
	private TextView text_item7_key;
	private TextView text_item7_value;
	private TextView[] keys;
	private TextView[] values;
	
	private RelativeLayout item_top;
	public ListTopItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public ListTopItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}
	private void init() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list_top_item, this, true);
		this.text_item1_key=(TextView) this.findViewById(R.id.text_item1_key);
		this.text_item1_value=(TextView) this.findViewById(R.id.text_item1_value);
		this.text_item2_key=(TextView) this.findViewById(R.id.text_item2_key);
		this.text_item2_value=(TextView) this.findViewById(R.id.text_item2_value);
		this.text_item3_key=(TextView) this.findViewById(R.id.text_item3_key);
		this.text_item3_value=(TextView) this.findViewById(R.id.text_item3_value);
		this.text_item4_key=(TextView) this.findViewById(R.id.text_item4_key);
		this.text_item4_value=(TextView) this.findViewById(R.id.text_item4_value);
		this.text_item5_key=(TextView) this.findViewById(R.id.text_item5_key);
		this.text_item5_value=(TextView) this.findViewById(R.id.text_item5_value);
		this.text_item6_key=(TextView) this.findViewById(R.id.text_item6_key);
		this.text_item6_value=(TextView) this.findViewById(R.id.text_item6_value);
		this.text_item7_key=(TextView) this.findViewById(R.id.text_item7_key);
		this.text_item7_value=(TextView) this.findViewById(R.id.text_item7_value);
		
		item_top=(RelativeLayout) this.findViewById(R.id.item_top);
		
		keys=new TextView[]{text_item1_key,text_item2_key,text_item3_key,text_item4_key,
				text_item5_key,text_item6_key,text_item7_key};
		values=new TextView[]{text_item1_value,text_item2_value,text_item3_value,text_item4_value,
				text_item5_value,text_item6_value,text_item7_value};
	}
	
	public void showBottom(){
		item_top.setVisibility(View.VISIBLE);
	}
	public void setData(String type,List<String> strs){
		if(strs!=null&&strs.size()>0){
			for(int i=0;i<strs.size();i++){
				keys[i].setVisibility(View.VISIBLE);
				values[i].setVisibility(View.VISIBLE);
				values[i].setText(strs.get(i));
			}
		}
		setKey(type);
	}
	public void setData(List<String> key,List<String> value){
		if(key!=null&&key.size()>0){
			for(int i=0;i<key.size();i++){
				keys[i].setVisibility(View.VISIBLE);
				values[i].setVisibility(View.VISIBLE);
				keys[i].setText(key.get(i));
				values[i].setText(value.get(i));
			}
		}
	}
	private void setKey(String type){
		if(Common.FAPIAO.equals(type)){
			text_item1_key.setText("发票金额");
			text_item2_key.setText("发票类型");
			text_item3_key.setText("合同编号");
			text_item4_key.setText("开票公司");
			text_item5_key.setText("合同名称");
		}else if(Common.FEIYONG.equals(type)){
			text_item1_key.setText("费用类型");
			text_item2_key.setText("费用日期");
			text_item3_key.setText("归属项目号");
			text_item4_key.setText("归属项目名称");
			text_item5_key.setText("项目主导部门");
			text_item6_key.setText("金额（元）");
		}else if(Common.FENBAO.equals(type)){
			text_item1_key.setText("分包方名称");
			text_item2_key.setText("单位地址");
			text_item3_key.setText("单位资质");
			text_item4_key.setText("企业法人");
			text_item5_key.setText("分包方等级");
			text_item6_key.setText("联系人");
			text_item7_key.setText("联系电话");
		}else if(Common.JIEKUAN.equals(type)){
			text_item1_key.setText("借款类别");
			text_item2_key.setText("项目编号");
			text_item3_key.setText("项目名称");
			text_item4_key.setText("借款事由");
			text_item5_key.setVisibility(View.GONE);
		}else if(Common.QINGJIA.equals(type)){
			text_item1_key.setText("本次请假");
			text_item2_key.setText("请假时间");
			text_item3_key.setText("当前项目状况");
		}else if(Common.TOUBIAO.equals(type)){
			text_item1_key.setText("保证金类型");
			text_item2_key.setText("担保金额（元）");
			text_item3_key.setText("支付方式");
			text_item4_key.setText("项目名称");
			text_item5_key.setText("申请内容");
		}else if(Common.YINGFUHT.equals(type)||Common.YINGSHOUHT.equals(type)){
			text_item1_key.setText("项目编号");
			text_item2_key.setText("结算方式");
			text_item3_key.setText("合同名称");
			text_item4_key.setText("项目名称");
			text_item5_key.setText("委托工作内容");
		}else if(Common.FUKUAN.equals(type)){
			text_item1_key.setText("分包合同名称：");
			text_item2_key.setText("分包方名称：");
			text_item3_key.setText("分包合同编号：");
			text_item4_key.setText("分包合同额：");
			text_item5_key.setText("已支付金额：");
			text_item6_key.setText("本次申请支付:");
		}else {
			//错误处理
		}
	}
}
