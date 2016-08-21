package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PGongShiTimeEntity;
import com.DCHZ.TYLINCN.entity.PJiDuHeTongListEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspGongShiTimeEntity extends RspBaseEntity{
	public PGongShiTimeEntity mEntity;
	public RspGongShiTimeEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity = com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(),
				PGongShiTimeEntity.class);
	}

}
