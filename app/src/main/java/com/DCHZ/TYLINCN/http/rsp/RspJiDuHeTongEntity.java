package com.DCHZ.TYLINCN.http.rsp;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.entity.PJiDuHeTongListEntity;
import com.DCHZ.TYLINCN.http.base.RspBaseEntity;

public class RspJiDuHeTongEntity extends RspBaseEntity {
	public PJiDuHeTongListEntity mEntity;

	public RspJiDuHeTongEntity(JSONObject jsonObj, int seqNo) {
		super(jsonObj, seqNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parseData(JSONObject jsonObj) {
		// TODO Auto-generated method stub
//		JSONArray arr = jsonObj.optJSONArray("JiDuShouKuanInfo");
//		if (arr != null) {
			mEntity = com.alibaba.fastjson.JSON.parseObject(jsonObj.toString(),
					PJiDuHeTongListEntity.class);
//		}
	}

}
