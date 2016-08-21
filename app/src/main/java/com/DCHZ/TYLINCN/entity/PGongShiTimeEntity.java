package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PGongShiTimeEntity implements Serializable{
	private static final long serialVersionUID = 333333333L;
	
	public ArrayList<PCurrentWeekProjEntity> CurrentWeekProj;
	public ArrayList<PCurrentWeekRangeEntity> CurrentWeekRange;
	public String DateTimeStr;
}
