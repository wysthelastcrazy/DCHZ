package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.MAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.component.TopView;
import com.DCHZ.TYLINCN.http.rsp.RspZhiJieGongShiEntity;
import com.DCHZ.TYLINCN.util.MyLog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchJieDuanActivity extends BaseNormalActivity{
	private EditText edit_search;
	private ListView listView;
	private ArrayList<String> mArray;
	private MAdapter mAdapter;
	private TextView text_search;
	private TextView header;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		initExtra();
		initLayout();
	}
	private void initExtra() {
		// TODO Auto-generated method stub
		mArray=(ArrayList<String>) getIntent().getSerializableExtra("mlist");
	}
	private void initLayout(){
		header=(TextView) this.findViewById(R.id.text_header);
		header.setText("直接工时");
		
		listView=(ListView) this.findViewById(R.id.listView);
		if(mArray==null){
			mArray=new ArrayList<String>();
		}
		mAdapter=new MAdapter(this, mArray);
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				String str=mArray.get(pos);
				Intent intent=new Intent();
				intent.putExtra("key", str);
				intent.putExtra("pos", pos);
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
		edit_search=(EditText) this.findViewById(R.id.edit_search);
		edit_search.setVisibility(View.GONE);
		text_search=(TextView) this.findViewById(R.id.text_search);
		text_search.setVisibility(View.GONE);
	}
	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		
	}
}
