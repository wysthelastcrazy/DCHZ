package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqGongShiTimeEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongShiTimeEntity;

public class TaskGongShiTime extends BaseTask<ReqGongShiTimeEntity>{

	public TaskGongShiTime(ReqGongShiTimeEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspGongShiTimeEntity rsp=null;
		if(isSucc){
			rsp=new RspGongShiTimeEntity(jsonObj, seqNo);
		}else{
			rsp=new RspGongShiTimeEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_GONGSHI_TIME, seqNo, rsp);
	}
}
