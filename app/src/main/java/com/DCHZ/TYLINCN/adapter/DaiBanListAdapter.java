package com.DCHZ.TYLINCN.adapter;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.commen.Global;
import com.DCHZ.TYLINCN.entity.PDaiBanEntity;
import com.DCHZ.TYLINCN.msglist.base.BaseItemView;
import com.DCHZ.TYLINCN.msglist.base.BaseListAdapter;
import com.DCHZ.TYLINCN.msglist.itemview.DaiBanItemView;

public class DaiBanListAdapter extends BaseListAdapter<PDaiBanEntity>{

	public DaiBanListAdapter(ArrayList<PDaiBanEntity> mList) {
		super(mList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BaseItemView<PDaiBanEntity> getItemView(PDaiBanEntity t) {
		// TODO Auto-generated method stub
		DaiBanItemView itemView=new DaiBanItemView(Global.getContext());
		return itemView;
	}

}
