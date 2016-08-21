package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PGSXMInfoEntity implements Serializable{
	private static final long serialVersionUID = 88888L;
	
	public String ProjectID;
	public String ProjectCode;
	public String ProjectName;
	public String XiangMuJingLiName;
	public String GSType;
	public String GSDate;
	public ArrayList<PXMJieDuanInfoEntity> XMJieDuanInfo;
}
