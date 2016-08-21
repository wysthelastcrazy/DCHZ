package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqZhiJieGongShiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspZhiJieGongShiEntity;

public class TaskZhiJieGongShi extends BaseTask<ReqZhiJieGongShiEntity>{

	public TaskZhiJieGongShi(ReqZhiJieGongShiEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspZhiJieGongShiEntity rsp=null;
		if(isSucc){
			rsp=new RspZhiJieGongShiEntity(jsonObj, seqNo);
		}else{
			rsp=new RspZhiJieGongShiEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_ZHIJIAGONGSHI_TAINBAO, seqNo, rsp);
	}
}
