package com.DCHZ.TYLINCN.activity;

import java.util.ArrayList;
import java.util.List;

import com.DCHZ.TYLINCN.R;
import com.DCHZ.TYLINCN.activity.base.BaseNormalActivity;
import com.DCHZ.TYLINCN.adapter.DaiBanListAdapter;
import com.DCHZ.TYLINCN.adapter.YiBanListAdapter;
import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.commen.MConfiger;
import com.DCHZ.TYLINCN.component.ListViewEmptyView;
import com.DCHZ.TYLINCN.http.ProtocalManager;
import com.DCHZ.TYLINCN.http.rsp.RspDaiBanListEntity;
import com.DCHZ.TYLINCN.http.rsp.RspYiBanListEntity;
import com.DCHZ.TYLINCN.msglist.MsgPage;
import com.DCHZ.TYLINCN.msglist.NLPullRefreshView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.listener.IRefreshListener;
import com.DCHZ.TYLINCN.util.SharePreLoginUtil;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FirstActivity extends BaseNormalActivity implements
		OnClickListener {
	private final int FLAG_DAIBAN = 0x100;
	private final int FLAG_YIBAN = 0x101;
	private List<Integer> mReqList = new ArrayList<Integer>();
	private TextView textView_daiBan;
	private TextView textView_yiBan;
	private MsgPage mMsgPage;
	private DaiBanListAdapter mAdapterDaiBan;
	private YiBanListAdapter mAdapterYiban;
	private int pageIndex = 1;
//	private String YHID = "33379ECD-67AE-4202-AF64-D10075F31AAB";
	private String YHID = "";
	private boolean hasNext = true;
	private int mType = TYPE_DAIBAN;
	private static final int TYPE_DAIBAN = 1;
	private static final int TYPE_YIBAN = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initLayout();
		registMsgRecevier(EventCommon.EVENT_DAIBAN);
		registMsgRecevier(EventCommon.EVENT_YIBAN);
		YHID=SharePreLoginUtil.loadLoginInfo().YHID;
		
		int seq = ProtocalManager.getInstance().getDaiBanList(YHID, pageIndex);
		mReqList.add(seq);
		showLoading();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		this.textView_daiBan = (TextView) this
				.findViewById(R.id.textView_daiBan);
		this.textView_daiBan.setOnClickListener(this);
		this.textView_daiBan.setEnabled(false);
		this.textView_yiBan = (TextView) this.findViewById(R.id.textView_yiBan);
		this.textView_yiBan.setOnClickListener(this);

		mMsgPage = (MsgPage) findViewById(R.id.first_msgPage);
		this.mMsgPage.setRefreshListener(mRefreshListener);
		this.mMsgPage.setEmpty(ListViewEmptyView.TYPE_COMMENT);
//		ListTopItemView topView=new ListTopItemView(this);
//		String type="c36e62fe-5dbb-4f7d-b796-80b8e1cd6500";
//		List<String> mList=new ArrayList<String>();
//		mList.add("投标保证金");
//		mList.add("15000");
//		mList.add("单位网上转账");
//		mList.add("项目101");
//		mList.add("申请内容");
//		topView.setData(type, mList);
//		mMsgPage.addHeaderView(topView);
	}

	// mMsgpage的监听事件，包括下拉刷新和点击加载更多
	private IRefreshListener mRefreshListener = new IRefreshListener() {
		@Override
		public void bottomClick(int state) {
		}

		public void reachListViewBottom() {
			if (hasNext) {
				if (mType == TYPE_DAIBAN) {
					int seq = ProtocalManager.getInstance().getDaiBanList(YHID,
							nextPage());
					mReqList.add(seq);
				} else if (mType == TYPE_YIBAN) {
					int page=nextPage();
					int seq = ProtocalManager.getInstance().getYiBanList(YHID,
							page);
					mReqList.add(seq);
					showLoading();
				}
			} else {
				String str = "没有更多数据了！";
				showToast(str);
			}
		};

		@Override
		public void onRefresh(NLPullRefreshView view) {
			// TODO Auto-generated method stub
			if (mType == TYPE_DAIBAN) {
				mAdapterDaiBan=null;
				int seq = ProtocalManager.getInstance().getDaiBanList(YHID,
						refreshPage());
				mReqList.add(seq);
			} else if (mType == TYPE_YIBAN) {
				mAdapterYiban=null;
				int seq = ProtocalManager.getInstance().getYiBanList(YHID,
						refreshPage());
				mReqList.add(seq);
			}
		}
	};

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view == this.textView_daiBan) {
			textView_yiBan.setEnabled(true);
			textView_daiBan.setEnabled(false);
			hasNext=true;
			pageIndex=1;
			int seq = ProtocalManager.getInstance().getDaiBanList(YHID,
					pageIndex);
			mReqList.add(seq);
			showLoading();
			mType = TYPE_DAIBAN;
			 mAdapterDaiBan=null;
			 
		} else if (view == this.textView_yiBan) {
			textView_yiBan.setEnabled(false);
			textView_daiBan.setEnabled(true);
			hasNext=true;
			pageIndex=1;
			int seq = ProtocalManager.getInstance().getYiBanList(YHID,
					pageIndex);
			mReqList.add(seq);
			showLoading();
			mType = TYPE_YIBAN;
			mAdapterYiban=null;
		}
	}

	@Override
	public void handleReceiveMsg(int eventId, int seqNo, Object obj) {
		// TODO Auto-generated method stub
		if (eventId == EventCommon.EVENT_DAIBAN) {
			if (mReqList.remove(Integer.valueOf(seqNo))) {
				if (obj instanceof RspDaiBanListEntity) {
					RspDaiBanListEntity rsp = (RspDaiBanListEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_DAIBAN;
					msg.obj = rsp;
					sendMsg(msg);
				}
			}
		} else if (eventId == EventCommon.EVENT_YIBAN) {
			if (mReqList.remove(Integer.valueOf(seqNo))) {
				if (obj instanceof RspYiBanListEntity) {
					RspYiBanListEntity rsp = (RspYiBanListEntity) obj;
					Message msg = Message.obtain();
					msg.what = FLAG_YIBAN;
					msg.obj = rsp;
					sendMsg(msg);
				}
			}
		}
	}

	@Override
	protected void handleMsg(Message msg) {
		// TODO Auto-generated method stub
		super.handleMsg(msg);
		int what = msg.what;
		switch (what) {
		case FLAG_DAIBAN:
			hideLoadingDialog();
			RspDaiBanListEntity rsp = (RspDaiBanListEntity) msg.obj;
			if (rsp.isSucc) {
				if (mAdapterDaiBan == null) {
					mAdapterDaiBan = new DaiBanListAdapter(rsp.mEntity.daiBan);
					// 设置不显示底部bottom按钮条
					mAdapterDaiBan
							.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mMsgPage.setListAdapter(mAdapterDaiBan);
				} else {
					mAdapterDaiBan.appendList(rsp.mEntity.daiBan);
				}
				if (rsp.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
					hasNext = false;
				}
			} else {
				String str = "网络异常！";
				showToast(str);
			}
			mMsgPage.completeRefresh(rsp.isSucc);
			break;
		case FLAG_YIBAN:
			hideLoadingDialog();
			RspYiBanListEntity rsp1 = (RspYiBanListEntity) msg.obj;
			if (rsp1.isSucc) {
				if (mAdapterYiban == null) {
					mAdapterYiban = new YiBanListAdapter(rsp1.mEntity.daiBan);
					// 设置不显示底部bottom按钮条
					mAdapterYiban
							.setType(BaseListAdapter.ADAPTER_TYPE_NO_BOTTOM);
					mMsgPage.setListAdapter(mAdapterYiban);
				} else {
					mAdapterYiban.appendList(rsp1.mEntity.daiBan);

				}
				if (rsp1.mEntity.daiBan.size() < MConfiger.PAGE_SIZE) {
					hasNext = false;
				}
			} else {
				String str = "网络异常！";
				showToast(str);
			}
			mMsgPage.completeRefresh(rsp1.isSucc);
			break;
		default:
			break;
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
