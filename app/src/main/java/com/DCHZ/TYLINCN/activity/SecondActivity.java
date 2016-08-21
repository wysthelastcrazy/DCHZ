package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;
import java.util.List;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.GongShiShenPiAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.component.GongShiHeaderView;
import com.DCHZ.TYLINCN.component.GongShiTianBaoView;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.component.TopView;
import com.DCHZ.TYLINCN.component.GongShiHeaderView.BtnClickListener;
import com.DCHZ.TYLINCN.entity.PGSXMInfoEntity;
import com.DCHZ.TYLINCN.entity.PGSXMRenWuInfoEntity;
import com.DCHZ.TYLINCN.entity.PGSXMZhuanYeInfoEntity;
import com.DCHZ.TYLINCN.entity.PGongShiItemEntity;
import com.DCHZ.TYLINCN.entity.PJianJieGSXMInfoEntity;
import com.DCHZ.TYLINCN.entity.PXMJieDuanInfoEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspGSShenPiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGSTuiHuiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongLastWeekEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongShiShenPiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongShiTimeEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSubmitGongShiInfoEntity;
import com.DCHZ.TYLINCN.listener.IGongShiShenPiClickListener;
import com.DCHZ.TYLINCN.listener.IGongshiTianBaoLisneter;
import com.DCHZ.TYLINCN.listener.IItemClickListner;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;
import com.DCHZ.TYLINCN.util.StrUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends BaseNormalActivity implements
		OnClickListener {
	private final int FLAG_SET_LSIT = 0x100;
	private final int FLAG_GS_SHENPI=0x101;
	private final int FLAG_GS_TUIHUI=0x102;
	private final int FLAG_TIME=0x103;
	private final int FLAG_LAST=0x104;
	private final int FLAG_NEXT=0x105;
	private final int FLAG_SUBMIT=0x106;
	private ArrayList<PGongShiItemEntity> mList;
	private boolean isAllSelected;
//	private GongShiHeaderView mHeaderView;
	private MsgPage mMsgPage;
	private GongShiShenPiAdapter mAdapter;

//	private String YHID = "028F5499-A43E-49FA-8980-677665CAECAC";
	private String YHID;
	private String YHName;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private int pageIndex = 1;
	private boolean hasNext = true;
	private Button btn_cancel;
	private Button btn_ok;
	private LinearLayout layout;
	
	private TextView textView_daiBan;
	private TextView textView_yiBan;
	private GongShiTianBaoView mGongShi;
	private TextView text_xuan;
	private TextView mTextView;
	private ArrayList<String> mArray;
	private PGSXMInfoEntity nameEntity;
	private PXMJieDuanInfoEntity JieDuanEntity;
	private PGSXMZhuanYeInfoEntity zhuanyeEntity;
	private PGSXMRenWuInfoEntity renWuEntity;
	private PJianJieGSXMInfoEntity JianJieNameEntity;
	private int mType;
	
	private String thisTimeStr;
	private String currTimeStr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		initLayout();
		registMsgRecevier(EventCommon.EVENT_GONGSHI_SHENPI);
		registMsgRecevier(EventCommon.EVEN_GSSHENPI);
		registMsgRecevier(EventCommon.EVENT_GSTUIHUI);
		
		registMsgRecevier(EventCommon.EVENT_GONGSHI_TIME);
		registMsgRecevier(EventCommon.EVENT_LAST_WEEK);
		registMsgRecevier(EventCommon.EVENT_NEXT_WEEK);
		registMsgRecevier(EventCommon.EVENT_TIJIAO_GONGSHI);//工时提交
		YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		YHName=SharePreLoginUtil.loadLoginInfo().YHName;
		int seq = ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
				pageIndex);
		mReqList.add(seq);
		
		int seq1=ProtocalManager.getInstance().reqGongShiTime("", "", "", "", YHID, "");
		mReqList.add(seq1);
		showLoading();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		mArray=new ArrayList<String>();
		mList=new ArrayList<PGongShiItemEntity>();
//		
		text_xuan=(TextView) this.findViewById(R.id.text_xuan);
		text_xuan.setOnClickListener(this);
		
		
		mMsgPage = (MsgPage) findViewById(R.id.mMsgPage);
		mMsgPage.setEmpty(ListViewEmptyView.TYPE_COMMENT);
		mMsgPage.setEnablePullDown(false);
//		mMsgPage.setRefreshListener(mRefreshListener);
//		mMsgPage.setEnablePullDown(false);
		
		btn_ok=(Button) this.findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);
		btn_cancel=(Button) this.findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(this);
		layout=(LinearLayout) this.findViewById(R.id.layout);
		
		textView_daiBan=(TextView) this.findViewById(R.id.textView_daiBan);
		textView_daiBan.setOnClickListener(this);
		this.textView_daiBan.setEnabled(false);
		textView_yiBan=(TextView) this.findViewById(R.id.textView_yiBan);
		textView_yiBan.setOnClickListener(this);
		
		mGongShi=(GongShiTianBaoView) this.findViewById(R.id.gongshiTianBao);
//		mMsgPage.setVisibility(View.GONE);
//		layout.setVisibility(View.GONE);
//		text_xuan.setVisibility(View.GONE);
//		mGongShi.setVisibility(View.VISIBLE);
		mGongShi.setTopClickLisneter(topItemClickListener);
		mGongShi.setBtnClickLisneter(btnClickListener);
	}
	private IGongshiTianBaoLisneter btnClickListener=new IGongshiTianBaoLisneter() {
		
		@Override
		public void btnClick() {
			// TODO Auto-generated method stub
//			showToast("提交");
			if(mType==TopView.TYPE_ZHIJIE){
				if(nameEntity!=null&&JieDuanEntity!=null&&zhuanyeEntity!=null&&renWuEntity!=null){
					ProtocalManager.getInstance().reqSubmitGongShiInfo(nameEntity.GSType, nameEntity.ProjectID,nameEntity.ProjectCode, nameEntity.ProjectName,
							nameEntity.XiangMuJingLiName, JieDuanEntity.PhaseID, JieDuanEntity.PhaseName, zhuanyeEntity.ZhuanYeID, zhuanyeEntity.ZhuanYeName, renWuEntity.GSRenWuID, renWuEntity.GSRenWu,
							mGongShi.getValue(), YHID, YHName, "");
				}else{
					showToast("参数错误！");
				}
			}else{
				if(JianJieNameEntity!=null&&renWuEntity!=null){
					ProtocalManager.getInstance().reqSubmitGongShiInfo(JianJieNameEntity.GSType, JianJieNameEntity.ProjectID,JianJieNameEntity.ProjectCode, JianJieNameEntity.ProjectName,
							JianJieNameEntity.XiangMuJingLiName, "", "", "", "", renWuEntity.GSRenWuID, renWuEntity.GSRenWu,
							mGongShi.getValue(), YHID, YHName, "");
				}else{
					showToast("参数错误！");
				}
			}
		}

		@Override
		public void backClick() {
			// TODO 上一周
			if(mType==TopView.TYPE_ZHIJIE){
				if(nameEntity!=null&&JieDuanEntity!=null&&zhuanyeEntity!=null&&renWuEntity!=null){
					int seq=ProtocalManager.getInstance().reqLastWeek(nameEntity.ProjectID, JieDuanEntity.PhaseID,
							zhuanyeEntity.ZhuanYeID, renWuEntity.GSRenWuID, YHID, currTimeStr);
					mReqList.add(seq);
				}else{
					int seq=ProtocalManager.getInstance().reqLastWeek("", "",
							"", "", YHID, currTimeStr);
					mReqList.add(seq);
				}
			}else {
				if(JianJieNameEntity!=null&&renWuEntity!=null){
					int seq=ProtocalManager.getInstance().reqLastWeek(JianJieNameEntity.ProjectID, "",
							"", renWuEntity.GSRenWuID, YHID, currTimeStr);
					mReqList.add(seq);
				}else{
					int seq=ProtocalManager.getInstance().reqLastWeek("", "",
							"", "", YHID, currTimeStr);
					mReqList.add(seq);
				}
			}
		}

		@Override
		public void thisClick() {
			// TODO 本周
			if(mType==TopView.TYPE_ZHIJIE){
				if(nameEntity!=null&&JieDuanEntity!=null&&zhuanyeEntity!=null&&renWuEntity!=null){
					int seq=ProtocalManager.getInstance().reqGongShiTime(nameEntity.ProjectID, JieDuanEntity.PhaseID,
							zhuanyeEntity.ZhuanYeID, renWuEntity.GSRenWuID, YHID, thisTimeStr);
					mReqList.add(seq);
				}else{
					int seq=ProtocalManager.getInstance().reqGongShiTime("", "",
							"", "", YHID, thisTimeStr);
					mReqList.add(seq);
				}
			}else{
				if(JianJieNameEntity!=null&&renWuEntity!=null){
					int seq=ProtocalManager.getInstance().reqGongShiTime(JianJieNameEntity.ProjectID, "",
							"", renWuEntity.GSRenWuID, YHID, thisTimeStr);
					mReqList.add(seq);
				}else{
					int seq=ProtocalManager.getInstance().reqGongShiTime("", "",
							"", "", YHID, thisTimeStr);
					mReqList.add(seq);
				}
			}
		}

		@Override
		public void nextClick() {
			// TODO 下一周
			if(mType==TopView.TYPE_ZHIJIE){
				if(nameEntity!=null&&JieDuanEntity!=null&&zhuanyeEntity!=null&&renWuEntity!=null){
					int seq=ProtocalManager.getInstance().reqNextWeek(nameEntity.ProjectID, JieDuanEntity.PhaseID,
							zhuanyeEntity.ZhuanYeID, renWuEntity.GSRenWuID, YHID, currTimeStr);
					mReqList.add(seq);
				}else{
	//				http://192.168.1.2:8001/DCMobileWebService/MobileService.asmx/CurrentWeekGongShiInfo?ProjectID=&PhaseID=&ZhuanYeID=&GSRenWuID=&YHID=33379ECD-67AE-4202-AF64-D10075F31AAB&thisTimeStr=2015-11-9
					int seq=ProtocalManager.getInstance().reqNextWeek("", "",
							"", "", YHID, currTimeStr);
					mReqList.add(seq);
	//				showToast("上一周 currTimeStr:"+currTimeStr);
				}
			}else{
				if(JianJieNameEntity!=null&&renWuEntity!=null){
					int seq=ProtocalManager.getInstance().reqNextWeek(JianJieNameEntity.ProjectID, "",
							"", renWuEntity.GSRenWuID, YHID, currTimeStr);
					mReqList.add(seq);
				}else{
					int seq=ProtocalManager.getInstance().reqNextWeek("", "",
							"", "", YHID, currTimeStr);
					mReqList.add(seq);
				}
			}
		}
	};
	private IItemClickListner topItemClickListener=new IItemClickListner() {
		
		@Override
		public void itemClick(TextView textView,int type,int gsType) {
			// TODO Auto-generated method stub
			mTextView=textView;
			mType=gsType;
			if(type==TopView.TYPE_NAME){
				Intent intent=new Intent(SecondActivity.this,SearchActivity.class);
				intent.putExtra("gsType", gsType);
				startActivityForResult(intent, 100);
			}else if(type==TopView.TYPE_JIEDUAN){
				if(nameEntity!=null){
					mArray.clear();
					for(int i=0;i<nameEntity.XMJieDuanInfo.size();i++){
						mArray.add(nameEntity.XMJieDuanInfo.get(i).PhaseName);
					}
					MyLog.debug(TAG, "[IItemClickListner]  mArray:"+mArray.size());
					Intent intent=new Intent(SecondActivity.this,SearchJieDuanActivity.class);
					intent.putExtra("mlist", mArray);
					startActivityForResult(intent, 200);
				}
			}else if(type==TopView.TYPE_ZHUANYE){
				if(JieDuanEntity!=null){
					mArray.clear();
					for(int i=0;i<JieDuanEntity.GSXMZhuanYeInfo.size();i++){
						mArray.add(JieDuanEntity.GSXMZhuanYeInfo.get(i).ZhuanYeName);
					}
					if(mArray.size()>0){
						Intent intent=new Intent(SecondActivity.this,SearchJieDuanActivity.class);
						intent.putExtra("mlist", mArray);
						startActivityForResult(intent, 300);
					}else{
						showToast("没有可供选择的专业");
					}
				}
			}else if(type==TopView.TYPE_NEIRONG){
				Intent intent=new Intent(SecondActivity.this,SearchNeiRongActivity.class);
				intent.putExtra("gsType", gsType);
				startActivityForResult(intent, 400);
			}
		}
	};
	private IRefreshListener mRefreshListener=new IRefreshListener() {
		@Override
		public void bottomClick(int state) {
			// TODO Auto-generated method stub
			super.bottomClick(state);
		}
		public void reachListViewBottom() {
//			if (hasNext) {
//				int seq=ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
//						nextPage());
//					mReqList.add(seq);
//					showLoading();
//			} else {
//				String str = "没有更多数据了！";
//				showToast(str);
//			}
		}
		public void onRefresh(NLPullRefreshView view) {
			mAdapter=null;
			int seq=ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
					refreshPage());
				mReqList.add(seq);
		};
	};
	private IGongShiShenPiClickListener mListener = new IGongShiShenPiClickListener() {

		@Override
		public void selectedListener(int pos) {
			// TODO Auto-generated method stub
			mAdapter.getIsSelected().put(pos, true);
		}

		@Override
		public void cancelSelectListener(int pos) {
			// TODO Auto-generated method stub
			mAdapter.getIsSelected().put(pos, false);
		}
	};

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==btn_ok){
			int seq=ProtocalManager.getInstance().GSShenPi(getSelecedItem(), YHID);
			mReqList.add(seq);
			mAdapter=null;
			showLoading();
		}else if(view==btn_cancel){
			AlertDialog.Builder builder=new Builder(this);
			builder.setTitle("提示");
			builder.setMessage("请确定是否退回工时?");
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					// TODO Auto-generated method stub
					int seq=ProtocalManager.getInstance().GSTuiHui(getSelecedItem());
					mReqList.add(seq);
					mAdapter=null;
					showLoading();
					dialog.dismiss();
				}
			});
			builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
				
			});
			 builder.create().show();
		}else if(view==textView_daiBan){
			mMsgPage.setVisibility(View.GONE);
			layout.setVisibility(View.GONE);
			text_xuan.setVisibility(View.GONE);
			mGongShi.setVisibility(View.VISIBLE);
			textView_daiBan.setEnabled(false);
			textView_yiBan.setEnabled(true);
		}else if(view==textView_yiBan){
			mGongShi.setVisibility(View.GONE);
			text_xuan.setVisibility(View.VISIBLE);
			mMsgPage.setVisibility(View.VISIBLE);
			layout.setVisibility(View.VISIBLE);
			textView_daiBan.setEnabled(true);
			textView_yiBan.setEnabled(false);
		}else if(view==text_xuan){
			if (isAllSelected) {
				for (int i = 0; i < mList.size(); i++) {
					mAdapter.getIsSelected().put(i, false);
				}
				text_xuan.setText("全选");
			} else {
				for (int i = 0; i < mList.size(); i++) {
					mAdapter.getIsSelected().put(i, true);
				}
				text_xuan.setText("取消");
			}
			dataChanged();
			isAllSelected = !isAllSelected;
		}
	}

	private String getSelecedItem() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < mList.size(); i++) {
			if (mAdapter.getIsSelected().get(i)) {
				list.add(mList.get(i).GSID);
			}
		}
		return StrUtil.getString(list);
	}


	private void dataChanged() {
		// 通知listView刷新
		mAdapter.notifyDataSetChanged();
		// TextView显示最新的选中数目
	}

	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if (eventId == EventCommon.EVENT_GONGSHI_SHENPI) {
			if (mReqList.remove(Integer.valueOf(seqNo))) {
				if (obj instanceof RspGongShiShenPiEntity) {
					RspGongShiShenPiEntity rsp = (RspGongShiShenPiEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_SET_LSIT;
					msg.obj = rsp;
					sendMsg(msg);
				}
			}
		}else if(eventId == EventCommon.EVEN_GSSHENPI){
			if (mReqList.remove(Integer.valueOf(seqNo))) {
				if(obj instanceof RspGSShenPiEntity){
					RspGSShenPiEntity rsp=(RspGSShenPiEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_GS_SHENPI;
					msg.obj = rsp;
					sendMsg(msg);
				}
			}
		}else if(eventId == EventCommon.EVENT_GSTUIHUI){
			if (mReqList.remove(Integer.valueOf(seqNo))) {
					RspGSTuiHuiEntity rsp=(RspGSTuiHuiEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_GS_TUIHUI;
					msg.obj = rsp;
					sendMsg(msg);
			}
		}else if(eventId==EventCommon.EVENT_GONGSHI_TIME){
			if(obj instanceof RspGongShiTimeEntity){
				RspGongShiTimeEntity rsp=(RspGongShiTimeEntity) obj;
				Message msg = Message.obtain();
				msg.what = FLAG_TIME;
				msg.obj = rsp;
				sendMsg(msg);
			}
		}else if (eventId==EventCommon.EVENT_LAST_WEEK) {
			if(obj instanceof RspGongLastWeekEntity){
				RspGongLastWeekEntity rsp=(RspGongLastWeekEntity) obj;
				Message msg = Message.obtain();
				msg.what = FLAG_LAST;
				msg.obj = rsp;
				sendMsg(msg);
			}
		}else if(eventId==EventCommon.EVENT_NEXT_WEEK){
			if(obj instanceof RspGongLastWeekEntity){
				RspGongLastWeekEntity rsp=(RspGongLastWeekEntity) obj;
				Message msg = Message.obtain();
				msg.what = FLAG_NEXT;
				msg.obj = rsp;
				sendMsg(msg);
			}
		}else if(eventId==EventCommon.EVENT_TIJIAO_GONGSHI){
			if(obj instanceof RspSubmitGongShiInfoEntity){
				RspSubmitGongShiInfoEntity rsp=(RspSubmitGongShiInfoEntity) obj;
				Message msg = Message.obtain();
				msg.what = FLAG_SUBMIT;
				msg.obj = rsp;
				sendMsg(msg);
			}
		}
	}
	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		int what=msg.what;
		switch (what) {
		case FLAG_SET_LSIT:
			hideLoadingDialog();
			RspGongShiShenPiEntity rsp = (RspGongShiShenPiEntity) msg.obj;
			if(rsp!=null&&rsp.isSucc){
				if(mAdapter==null){
					mAdapter=new GongShiShenPiAdapter(rsp.mEntity.loadGongShiShenPiInfo);
					mAdapter.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mAdapter.setLisntener(mListener);
					mMsgPage.setListAdapter(mAdapter);
					mList=rsp.mEntity.loadGongShiShenPiInfo;
				}else{
					mAdapter.appendList(rsp.mEntity.loadGongShiShenPiInfo);
					mList.addAll(rsp.mEntity.loadGongShiShenPiInfo);
				}
				if(rsp.mEntity.loadGongShiShenPiInfo.size()<MConfiger.PAGE_SIZE){
					hasNext=false;
				}
			}else{
				String str = "网络异常！";
				showToast(str);
			}
			mMsgPage.completeRefresh(rsp.isSucc);
			break;
		case FLAG_GS_SHENPI:
			hideLoadingDialog();
			RspGSShenPiEntity rsp1=(RspGSShenPiEntity) msg.obj;
			if(rsp1!=null&&rsp1.isSucc){
				int seq=ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
						refreshPage());
					mReqList.add(seq);
					showLoading();
			}
			break;
		case FLAG_GS_TUIHUI:
			hideLoadingDialog();
			RspGSTuiHuiEntity rsp2=(RspGSTuiHuiEntity) msg.obj;
			if(rsp2!=null&&rsp2.isSucc){
				int seq=ProtocalManager.getInstance().getGongShiShenPiInfo(YHID,
						refreshPage());
					mReqList.add(seq);
					showLoading();
			}
			break;
		case FLAG_TIME:
			RspGongShiTimeEntity timeRsp=(RspGongShiTimeEntity) msg.obj;
			if(timeRsp!=null&&timeRsp.isSucc){
				mGongShi.setData(timeRsp.mEntity.CurrentWeekRange.get(0).BetweenAndEndOfWeek);
				thisTimeStr=timeRsp.mEntity.DateTimeStr;
				currTimeStr=thisTimeStr;
				if(timeRsp.mEntity.CurrentWeekProj!=null&&timeRsp.mEntity.CurrentWeekProj.size()>0){
					mGongShi.setColor(timeRsp.mEntity.CurrentWeekProj.get(0).ShuXing);
				}else{
					mGongShi.setColor(null);
				}
			}
			break;
		case FLAG_LAST:
			RspGongLastWeekEntity lastWeekRsp=(RspGongLastWeekEntity) msg.obj;
			if(lastWeekRsp!=null&&lastWeekRsp.isSucc){
				mGongShi.setData(lastWeekRsp.mEntity.CurrentWeekRange.get(0).BetweenAndEndOfWeek);
				currTimeStr=lastWeekRsp.mEntity.DateTimeStr;
				if(lastWeekRsp.mEntity.CurrentWeekProj!=null&&lastWeekRsp.mEntity.CurrentWeekProj.size()>0){
					mGongShi.setColor(lastWeekRsp.mEntity.CurrentWeekProj.get(0).ShuXing);
				}
			}
			break;
		case FLAG_NEXT:
			lastWeekRsp=(RspGongLastWeekEntity) msg.obj;
			if(lastWeekRsp!=null&&lastWeekRsp.isSucc){
				mGongShi.setData(lastWeekRsp.mEntity.CurrentWeekRange.get(0).BetweenAndEndOfWeek);
				currTimeStr=lastWeekRsp.mEntity.DateTimeStr;
				if(lastWeekRsp.mEntity.CurrentWeekProj!=null&&lastWeekRsp.mEntity.CurrentWeekProj.size()>0){
					mGongShi.setColor(lastWeekRsp.mEntity.CurrentWeekProj.get(0).ShuXing);
				}
			}
			break;
		case FLAG_SUBMIT:
			RspSubmitGongShiInfoEntity subRsp=(RspSubmitGongShiInfoEntity) msg.obj;
			if(subRsp!=null&&subRsp.isSucc){
				showToast("提交成功！");
				if(mType==TopView.TYPE_ZHIJIE){
					if(nameEntity!=null&&JieDuanEntity!=null&&zhuanyeEntity!=null&&renWuEntity!=null){
						int seq=ProtocalManager.getInstance().reqGongShiTime(nameEntity.ProjectID, JieDuanEntity.PhaseID,
								zhuanyeEntity.ZhuanYeID, renWuEntity.GSRenWuID, YHID, thisTimeStr);
						mReqList.add(seq);
					}else{
						int seq=ProtocalManager.getInstance().reqGongShiTime("", "",
								"", "", YHID, thisTimeStr);
						mReqList.add(seq);
					}
				}else{
					if(JianJieNameEntity!=null&&renWuEntity!=null){
						int seq=ProtocalManager.getInstance().reqGongShiTime(JianJieNameEntity.ProjectID, "",
								"", renWuEntity.GSRenWuID, YHID, thisTimeStr);
						mReqList.add(seq);
					}else{
						int seq=ProtocalManager.getInstance().reqGongShiTime("", "",
								"", "", YHID, thisTimeStr);
						mReqList.add(seq);
					}
				}
			}else{
				showToast("提交失败！");
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode!=Activity.RESULT_OK){
			return;
		}
		
		if(requestCode==100){
			String str=data.getStringExtra("key");
			mTextView.setText(str);
			if(mType==TopView.TYPE_ZHIJIE){
				nameEntity=(PGSXMInfoEntity) data.getSerializableExtra("entity");
				if(nameEntity!=null){
					int seq=ProtocalManager.getInstance().reqGongShiTime(nameEntity.ProjectID, "",
							"", "", YHID, currTimeStr);
					mReqList.add(seq);
				}
			}else{
				JianJieNameEntity=(PJianJieGSXMInfoEntity) data.getSerializableExtra("entity");
				if(JianJieNameEntity!=null){
					int seq=ProtocalManager.getInstance().reqGongShiTime(JianJieNameEntity.ProjectID, "",
							"", "", YHID, currTimeStr);
					mReqList.add(seq);
				}
			}
		}else if(requestCode==200){
			String str=data.getStringExtra("key");
			mTextView.setText(str);
			int pos=data.getIntExtra("pos", 0);
			JieDuanEntity=nameEntity.XMJieDuanInfo.get(pos);
			if(JieDuanEntity!=null){
//				int seq=ProtocalManager.getInstance().reqGongShiTime(nameEntity.ProjectID, "",
//						"", "", YHID, currTimeStr)
//				mReqList.add(seq);
				int seq=ProtocalManager.getInstance().reqGongShiTime(nameEntity.ProjectID, JieDuanEntity.PhaseID,
						"", "", YHID, currTimeStr);
				mReqList.add(seq);
			}
		}else if(requestCode==300){
			String str=data.getStringExtra("key");
			mTextView.setText(str);
			int pos=data.getIntExtra("pos", 0);
			zhuanyeEntity=JieDuanEntity.GSXMZhuanYeInfo.get(pos);
			if(zhuanyeEntity!=null){
				int seq=ProtocalManager.getInstance().reqGongShiTime(nameEntity.ProjectID, JieDuanEntity.PhaseID,
						zhuanyeEntity.ZhuanYeID, "", YHID, currTimeStr);
				mReqList.add(seq);
				}
		}else if(requestCode==400){
			String str=data.getStringExtra("key");
			mTextView.setText(str);
			renWuEntity= (PGSXMRenWuInfoEntity) data.getSerializableExtra("entity");
			if(renWuEntity!=null){
				if(mType==TopView.TYPE_ZHIJIE){
					int seq=ProtocalManager.getInstance().reqGongShiTime(nameEntity.ProjectID, JieDuanEntity.PhaseID,
							zhuanyeEntity.ZhuanYeID, renWuEntity.GSRenWuID, YHID, currTimeStr);
					mReqList.add(seq);
				}else{
					int seq=ProtocalManager.getInstance().reqGongShiTime(JianJieNameEntity.ProjectID, "",
							"", renWuEntity.GSRenWuID, YHID, currTimeStr);
					mReqList.add(seq);
				}
			}
		}
	}
	
	
	private int nextPage() {
		pageIndex = pageIndex + 1;
		return pageIndex;
	}

	private int refreshPage() {
		pageIndex = 1;
		return pageIndex;
	}
}
