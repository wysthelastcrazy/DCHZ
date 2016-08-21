package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;


















import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.MAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.component.ListOpenWordView;
import com.DCHZ.TYLINCN.component.TopView;
import com.DCHZ.TYLINCN.entity.PGSXMInfoEntity;
import com.DCHZ.TYLINCN.entity.PJianJieGSXMInfoEntity;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspJianJieGongShiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspZhiJieGongShiEntity;
import com.DCHZ.TYLINCN.util.MyLog;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends BaseNormalActivity{
	private EditText edit_search;
	private ListView listView;
	private ArrayList<String> mArray;
	private MAdapter mAdapter;
	private String YHID;
	private String strWhere="";
	private final int FLAG_ZHIJIE=0x100;
	private final int FLAG_JIANJIE=0x101;
	private TextView text_search;
	
	private RspZhiJieGongShiEntity mRsp;
	private RspJianJieGongShiEntity mJianJieRsp;
	private int mType;
	private TextView header;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		registMsgRecevier(EventCommon.EVENT_ZHIJIAGONGSHI_TAINBAO);
		registMsgRecevier(EventCommon.EVENT_JIANJIEGONGSHI_TIANBAO);
		YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		initEx();
		if(mType==TopView.TYPE_ZHIJIE){
			ProtocalManager.getInstance().reZhiJieGongShi(YHID, 1, strWhere);
			showLoading();
		}else{
			ProtocalManager.getInstance().reqJianJieGongJie(YHID, 1, strWhere);
			showLoading();
		}
		initLayout();
	}
	private void initEx() {
		// TODO Auto-generated method stub
		mType=getIntent().getIntExtra("gsType", TopView.TYPE_ZHIJIE);
	}
	private void initLayout(){
		header=(TextView) this.findViewById(R.id.text_header);
		if(mType==TopView.TYPE_ZHIJIE){
			header.setText("直接工时");
		}else{
			header.setText("间接工时");
		}
		
		
		listView=(ListView) this.findViewById(R.id.listView);
		edit_search=(EditText) this.findViewById(R.id.edit_search);
		mArray=new ArrayList<String>();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				String str=mArray.get(pos);
				Intent intent=new Intent();
				intent.putExtra("key", str);
				if(mType==TopView.TYPE_ZHIJIE){
					PGSXMInfoEntity entity=mRsp.mEntity.GSXMInfo.get(pos);
					intent.putExtra("entity", entity);
				}else{
					PJianJieGSXMInfoEntity entity=mJianJieRsp.mEntity.GSXMInfo.get(pos);
					intent.putExtra("entity", entity);
				}
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
		
		text_search=(TextView) this.findViewById(R.id.text_search);
		text_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				strWhere=edit_search.getText().toString();
				if(mType==TopView.TYPE_ZHIJIE){
					ProtocalManager.getInstance().reZhiJieGongShi(YHID, 1, strWhere);
					showLoading();
				}else{
					ProtocalManager.getInstance().reqJianJieGongJie(YHID, 1, strWhere);
					showLoading();
				}
			}
		});
	}
	
	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if(eventId==EventCommon.EVENT_ZHIJIAGONGSHI_TAINBAO){
			if(obj instanceof RspZhiJieGongShiEntity){
				RspZhiJieGongShiEntity rsp=(RspZhiJieGongShiEntity) obj;
				Message msg=Message.obtain();
				msg.what=FLAG_ZHIJIE;
				msg.obj=rsp;
				sendMsg(msg);
			}
		}else if(eventId==EventCommon.EVENT_JIANJIEGONGSHI_TIANBAO){
			if(obj instanceof RspJianJieGongShiEntity){
				RspJianJieGongShiEntity rsp=(RspJianJieGongShiEntity) obj;
				Message msg=Message.obtain();
				msg.what=FLAG_JIANJIE;
				msg.obj=rsp;
				sendMsg(msg);
			}
		}
	}
	
	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		int what=msg.what;
		hideLoadingDialog();
		switch (what) {
		case FLAG_ZHIJIE:
			mArray.clear();
			MyLog.debug(TAG, "[handleMsg]  ddd");
			RspZhiJieGongShiEntity rsp=(RspZhiJieGongShiEntity) msg.obj;
			if(rsp!=null&&rsp.isSucc){
				if(rsp.mEntity!=null&&rsp.mEntity.GSXMInfo!=null){
					mRsp=rsp;
					for(int i=0;i<rsp.mEntity.GSXMInfo.size();i++){
						mArray.add("("+rsp.mEntity.GSXMInfo.get(i).ProjectCode+")"+rsp.mEntity.GSXMInfo.get(i).ProjectName);
					}
					if(mAdapter==null){
						mAdapter=new MAdapter(SearchActivity.this, mArray);
						listView.setAdapter(mAdapter);
					}else{
						mAdapter.reSetList(mArray);
					}
				}
			}
			break;
		case FLAG_JIANJIE:
			mArray.clear();
			MyLog.debug(TAG, "[handleMsg]  ddd");
			RspJianJieGongShiEntity jianjieRsp=(RspJianJieGongShiEntity) msg.obj;
			if(jianjieRsp!=null&&jianjieRsp.isSucc){
				if(jianjieRsp.mEntity!=null&&jianjieRsp.mEntity.GSXMInfo!=null){
					mJianJieRsp=jianjieRsp;
					for(int i=0;i<jianjieRsp.mEntity.GSXMInfo.size();i++){
						mArray.add("("+jianjieRsp.mEntity.GSXMInfo.get(i).ProjectCode+")"+jianjieRsp.mEntity.GSXMInfo.get(i).ProjectName);
					}
					if(mAdapter==null){
						mAdapter=new MAdapter(SearchActivity.this, mArray);
						listView.setAdapter(mAdapter);
					}else{
						mAdapter.reSetList(mArray);
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
