package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PZhiJieGongShiEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspZhiJieGongShiEntity extends RspBaseEntity{
	public PZhiJieGongShiEntity mEntity;
	public RspZhiJieGongShiEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		mEntity=com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(), PZhiJieGongShiEntity.class);
	}

}
