package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqJianJieGongShiEntity;
import com.DCHZ.TYLINCN.http.req.ReqZhiJieGongShiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspJianJieGongShiEntity;
import com.DCHZ.TYLINCN.http.rsp.RspZhiJieGongShiEntity;

public class TaskJianJieGongShi extends BaseTask<ReqJianJieGongShiEntity>{

	public TaskJianJieGongShi(ReqJianJieGongShiEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspJianJieGongShiEntity rsp=null;
		if(isSucc){
			rsp=new RspJianJieGongShiEntity(jsonObj, seqNo);
		}else{
			rsp=new RspJianJieGongShiEntity(null, seqNo);
		}
		EventController.getInstance().notifyEvent(EventCommon.EVENT_JIANJIEGONGSHI_TIANBAO, seqNo, rsp);
	}
}
