package com.DCHZ.TYLINCN.msglist.itemview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.entity.PDaiBanEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.util.IntentUtils;

import android.view.View.OnClickListener;;
public class YiBanItemView extends BaseItemView<PDaiBanEntity> implements OnClickListener{
	public static final int TYPE_DAIBAN=1;
	public static final int TYPE_YIBAN=2;
	private int mType;
	private PDaiBanEntity mEntity;
	private TextView text_daibanList_name;
	private TextView text_daibanList_type;
	private TextView text_daibanList_moeny;
	private TextView text_daibanList_time;
	private ImageView image_yiban;
	private Context mContext;
	public YiBanItemView(Context context) {
		super(context);
		mContext=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMsg(PDaiBanEntity mEntity) {
		// TODO Auto-generated method stub
		this.mEntity=mEntity;
		
		//set name
		String name=mEntity.ShenQingRen;
		text_daibanList_name.setText(name);
		//set type
		String type=mEntity.YWTypeKey;
		text_daibanList_type.setText(type);
		//set moeny
		String moeny=mEntity.YWTypeValue;
		text_daibanList_moeny.setText(moeny);
		
		//set time
		String time=mEntity.BLReferDate;
		text_daibanList_time.setText(time);
		
		String state=mEntity.JianKong;
		image_yiban.setVisibility(View.GONE);
		if("已办结".equals(state)){
			image_yiban.setVisibility(View.VISIBLE);
		}
		mType=Common.TYPE_YIBAN;
	}

	@Override
	public PDaiBanEntity getMsg() {
		// TODO Auto-generated method stub
		return mEntity;
	}

	@Override
	public void onInflate() {
		// TODO Auto-generated method stub
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=li.inflate(com.DCHZ.TYLINCN.R.layout.daiban_list_item, this, true);
		view.setOnClickListener(this);
		text_daibanList_name=(TextView) view.findViewById(R.id.text_daibanList_name);
		text_daibanList_type=(TextView) view.findViewById(R.id.text_daibanList_type);
		text_daibanList_moeny=(TextView) view.findViewById(R.id.text_daibanList_moeny);
		text_daibanList_time=(TextView) view.findViewById(R.id.text_daibanList_time);
		
		image_yiban=(ImageView) view.findViewById(R.id.image_yiban);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		String LCID=mEntity.LCID;
		//费用报销单
		if(Common.FEIYONG.equals(LCID)){
			IntentUtils.startFeiYongDetailActivity(mContext, mEntity, mType);
		//请假申请
		}else if(Common.QINGJIA.equals(LCID)){
			IntentUtils.startQingJiagDetailActivity(mContext, mEntity, mType);
			//借款单
		}else if(Common.JIEKUAN.equals(LCID)){
			IntentUtils.startJieKuangDetailActivity(mContext, mEntity, mType);
			//应收合同评审
		}else if(Common.YINGSHOUHT.equals(LCID)){
			IntentUtils.startYingShouHTDetailActivity(mContext, mEntity, mType);
			//应付合同评审
		}else if(Common.YINGFUHT.equals(LCID)){
			IntentUtils.startYingFuHTDetailActivity(mContext, mEntity, mType);
			//发票开具
		}else if(Common.FAPIAO.equals(LCID)){
			IntentUtils.startFaPiaoDetailActivity(mContext, mEntity, mType);
			//投标或履约保证金
		}else if(Common.TOUBIAO.equals(LCID)){
			IntentUtils.startDetailActivity(mContext, mEntity, mType);
			//分包方评审
		}else if(Common.FENBAO.equals(LCID)){
			IntentUtils.startFenBaoFangDetailActivity(mContext, mEntity, mType);
		}else if(Common.FUKUAN.equals(LCID)){
			IntentUtils.startGongChengDetailActivity(mContext, mEntity, mType);
		}else{
			//TODO ：错误数据处理
		}
		
	}

}
