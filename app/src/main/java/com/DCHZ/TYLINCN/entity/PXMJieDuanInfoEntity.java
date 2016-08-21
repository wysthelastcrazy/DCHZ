package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PXMJieDuanInfoEntity implements Serializable{
	private static final long serialVersionUID = 888888L;
	
	public String PhaseID;
	public String PhaseName;
	public ArrayList<PGSXMZhuanYeInfoEntity> GSXMZhuanYeInfo;
}
