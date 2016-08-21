package com.DCHZ.TYLINCN.http.req;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqGongShiLastWeekEntity extends ReqBaseEntity{

	public String ProjectID;
	public String PhaseID;
	public String ZhuanYeID;
	public String GSRenWuID;
	public String YHID;
	public String thisTimeStr;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_LAST_WEEK;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("ProjectID", ProjectID);
		mMap.put("PhaseID", PhaseID);
		try {
			mMap.put("ZhuanYeID", URLEncoder.encode(ZhuanYeID, "GBK"));
			mMap.put("GSRenWuID",  URLEncoder.encode(GSRenWuID, "GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mMap.put("YHID", YHID);
		mMap.put("thisTimeStr", thisTimeStr);
		return mMap;
	}

}
