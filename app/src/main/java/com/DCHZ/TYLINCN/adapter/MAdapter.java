package com.DCHZ.TYLINCN.adapter;

import java.util.ArrayList;

import com.DCHZ.TYLINCN.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MAdapter extends BaseAdapter{
	private Context mContext;
	private ArrayList<String> mList;
	private LayoutInflater li;
	public MAdapter(Context context,ArrayList<String> list){
		mContext=context;
		mList=list;
		li=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public void reSetList(ArrayList<String> list){
		mList=list;
		notifyDataSetChanged();
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view=li.inflate(R.layout.name_item, null);
		String item=mList.get(arg0);
		TextView textView=(TextView) view.findViewById(R.id.text_item);
		textView.setText(item);
		return view;
	}

}
