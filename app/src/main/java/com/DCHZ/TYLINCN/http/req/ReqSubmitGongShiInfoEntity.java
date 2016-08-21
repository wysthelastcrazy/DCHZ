package com.DCHZ.TYLINCN.http.req;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.DCHZ.TYLINCN.commen.Common;
import com.DCHZ.TYLINCN.http.base.ReqBaseEntity;

public class ReqSubmitGongShiInfoEntity extends ReqBaseEntity{
	public String GSType;
	public String ProjectID;
	public String ProjectCode;
	public String ProjectName;
	public String XiangMuJingLiName;
	public String PhaseID;
	public String PhaseName;
	public String ZhuanYeID;
	public String ZhuanYeName;
	public String GSRenWuID;
	public String GSRenWu;
	public String GSTianBaoKeyValue;
	public String YHID;
	public String YHName;
	public String TimeStr;
	@Override
	public String getReqUrl() {
		// TODO Auto-generated method stub
		return Common.URL_TIJIAO_GONGSHI;
	}

	@Override
	public Map<String, String> getReqData() {
		// TODO Auto-generated method stub
		Map<String ,String> mMap=new HashMap<String, String>();
		mMap.put("ProjectID", ProjectID);
		mMap.put("ProjectCode", ProjectCode);
		mMap.put("PhaseID", PhaseID);
		mMap.put("YHID", YHID);
		mMap.put("TimeStr", TimeStr);
		mMap.put("GSTianBaoKeyValue", GSTianBaoKeyValue);
		try {
			mMap.put("GSType", URLEncoder.encode(GSType, "GBK"));
			mMap.put("ProjectName", URLEncoder.encode(ProjectName, "GBK"));
			mMap.put("XiangMuJingLiName",  URLEncoder.encode(XiangMuJingLiName, "GBK"));
			mMap.put("PhaseName",  URLEncoder.encode(PhaseName, "GBK"));
			mMap.put("ZhuanYeID",  URLEncoder.encode(ZhuanYeID, "GBK"));
			mMap.put("ZhuanYeName",  URLEncoder.encode(ZhuanYeName, "GBK"));
			mMap.put("GSRenWuID", URLEncoder.encode(GSRenWuID, "GBK"));
			mMap.put("GSRenWu", URLEncoder.encode(GSRenWu, "GBK"));
			mMap.put("YHName", URLEncoder.encode(YHName, "GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mMap;
	}

}
