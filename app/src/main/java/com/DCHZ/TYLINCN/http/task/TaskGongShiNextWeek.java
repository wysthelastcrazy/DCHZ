package com.DCHZ.TYLINCN.http.task;

import org.json.JSONObject;

import com.DCHZ.TYLINCN.commen.EventCommon;
import com.DCHZ.TYLINCN.controller.EventController;
import com.DCHZ.TYLINCN.http.base.BaseTask;
import com.DCHZ.TYLINCN.http.req.ReqGongShiLastWeekEntity;
import com.DCHZ.TYLINCN.http.req.ReqGongShiNextWeekEntity;
import com.DCHZ.TYLINCN.http.rsp.RspGongLastWeekEntity;
import com.DCHZ.TYLINCN.util.MyLog;

public class TaskGongShiNextWeek extends BaseTask<ReqGongShiNextWeekEntity>{

	public TaskGongShiNextWeek(ReqGongShiNextWeekEntity t) {
		super(t);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void getResponse(JSONObject jsonObj, boolean isSucc, int errorCode,
			int seqNo) {
		// TODO Auto-generated method stub
		RspGongLastWeekEntity rsp=null;
		if(isSucc){
			rsp=new RspGongLastWeekEntity(jsonObj, seqNo);
		}else{
			rsp=new RspGongLastWeekEntity(null, seqNo);
		}
//		MyLog.debug(TAG, "[getResponse]  jsonObj:"+jsonObj.toString());
		EventController.getInstance().notifyEvent(EventCommon.EVENT_NEXT_WEEK, seqNo, rsp);
	}
}
