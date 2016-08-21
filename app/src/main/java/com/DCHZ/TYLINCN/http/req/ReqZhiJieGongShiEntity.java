package com.DCHZ.TYLINCN.http.req;

import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqZhiJieGongShiEntity extends ReqBaseEntity{
	public String YHID;
	public String strWhere;
	public String pageSize;
	public String pageIndex;
	public String TianBaoType="0";
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_ZHIJIE_GONGSHI;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("YHID", YHID);
		mMap.put("strWhere", strWhere);
		mMap.put("pageSize", pageSize);
		mMap.put("pageIndex", pageIndex);
		mMap.put("gsTianBaoTYpe", TianBaoType);
		return mMap;
	}

}
