package com.DCHZ.TYLINCN.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PCurrentWeekRangeEntity implements Serializable{
	private static final long serialVersionUID = 44444L;
	
	public ArrayList<PBetweenAndEndOfWeekEntity> BetweenAndEndOfWeek;
	public String NowOfWeek;
	public String WeekBeginTime;
	public String WeekEndTime;
}
