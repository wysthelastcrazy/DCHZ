package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqSubmitGongShiInfoEntity;
import com.DCHZ.TYLINCN.http.rsp.RspSubmitGongShiInfoEntity;

public class TaskSubmitGongShiInfo extends BaseTask<ReqSubmitGongShiInfoEntity>{

	public TaskSubmitGongShiInfo(ReqSubmitGongShiInfoEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
@Override
public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
		int seqNo) {
	// TODO Auto-generated method stub
	RspSubmitGongShiInfoEntity rsp=null;
	if(isSucc){
		rsp=new RspSubmitGongShiInfoEntity(jsonObj, seqNo);
	}else{
		rsp=new RspSubmitGongShiInfoEntity(null,seqNo);
	}
	EventController.getInstance().notifyEvent(EventCommon.EVENT_TIJIAO_GONGSHI, seqNo, rsp);
}
}
