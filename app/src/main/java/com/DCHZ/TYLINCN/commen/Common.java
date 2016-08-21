package com.DCHZ.TYLINCN.commen;

/***
 * 基础常量
 * @date 2015/06/27
 */
public class Common {

	public static int seqNo = 200;
	
	/***
	 * 生成全局序列号
	 * @return
	 */
	public static final int getSeqNo(){
		return ++seqNo;
	}
	
	public static final String URL_TEST="/hello";
	public static final String URL_VERSION="/GetLatestAppVersion";
	/**登录**/
	public static final String URL_LOGIN="/MobileLogin";
	/**获取代办列表**/
	public static final String URL_DAIBAN="/daiBanList";
	/**代办列表分页**/
	public static final String URL_DAIBAN_FENYE="/daiBanListFenYe";
	/**已办列表分页**/
	public static final String URL_YIBAN_FENYE="/yiBanListFenYe";
	/**代办数量**/
	public static final String URL_DAIBAN_COUNT="/daiBanCount";
	
	/**审批信息**/
	public static final String URL_SHENPI_INFO="/TouBiaoBaoZhengJinSQInfo";
	/**办理意见**/
	public static final String URL_BANLIYIJIAN="/banLiYiJian_ios";
	/**工时审批info**/
	public static final String RUL_GONGSHI_SHENPI="/LoadGongShiShenPiInfo";
	public static final String URL_JIDU_HETONG="/JiDuHeTongInfo";
	public static final String URK_JIDU_SHOUKUAN="/JiDuShouKuanInfo";
	
	/**工时审批**/
	public static final String URL_GSSHENPI="/GSShenPi";
	/**工时退回**/
	public static final String URL_GSTUIHUI="/GSTuiHui";
	/**费用报销单详情**/
	public static final String URL_FEIYONG_DETAIL="/baoXiaoInfo";
	/**借款单详情**/
	public static final String URL_JIEKUANG_DETAIL="/jieKuanInfo";
	/**请假申请**/
	public static final String URL_QINGJIA_DETAIL="/QingJiaInfo";
	/**发票**/
	public static final String URL_FAPIAO_DETAIL="/FaPiaoKaiJuShenQingInfo";
	/**应付合同**/
	public static final String URK_YINFU_HT_DETAIL="/YingFuHeTongPSInfo";
	/**应收合同**/
	public static final String URK_YINSHOU_HT_DETAIL="/YingShouHeTongPSInfo";
	/**分包方合同**/
	public static final String URK_FENBAOFANG_DETAIL="/HeGeGongFangPingShenInfo";
	/**流程提交**/
	public static final String URL_SAVEFLOW_BUSINESS="/SaveFlowBusiness";
	/**退回**/
	public static final String URL_RETURN_BUSINESS="/SaveReturnFlowBusiness";
	/**分包方合同是否合格**/
	public static final String URL_ISHEGE="/SaveHeGeGongFangInfo";
	/**更新**/
	public static final String URL_UPDATE="/GetLatestAppVersion";
	public static final String URL_GONGCHENG="/FenBaoHeTongFuKuanInfo";
	/**直接工时填报**/
	public static final String URL_ZHIJIE_GONGSHI="/GetGongShiXiangMuInfoByYHID";
	/**工时时间**/
	public static final String URL_GONGSHI_TIME="/CurrentWeekGongShiInfo";
	/**上周时间**/
	public static final String URL_LAST_WEEK="/PreWeekGongShiInfo";
	/**下周时间**/
	public static final String URL_NEXT_WEEK="/NextWeekGongShiInfo";
	/**提交工时**/
	public static final String URL_TIJIAO_GONGSHI="/SubmitGongShiInfo";
	/**===========================================================================**/
	/**协议返回成功**/
	public static final int ERROR_CODE_SUCC = 0;
	/**协议返回错误,服务器内部异常也会出现这个问题**/
	public static final int ERROR_CODE_PROTOCAL = -100;
	/**网络错误**/
	public static final int ERROR_CODE_NET_ERROR = -101;
	/**发生Exception错误**/
	public static final int ERROR_CODE_EXCEPTION = -102;
	/**Exception UnSupportEncoding**/
	public static final int ERROR_CODE_UNSUPPORT_ENCODING = -103;
	
	public static volatile boolean isPageStop = true;
	
	
	//表单类型
	public static final int TYPE_DAIBAN=1;//待办
	public static final int TYPE_YIBAN=2;//已办
	/**费用报销单**/
	public static final String FEIYONG="ccc4b31c-b09c-4b8d-a422-b5372e34a492";
	/**请假申请**/
	public static final String QINGJIA="0aad3f42-3e3a-4c3a-8377-9ea66809caee";
	/**借款单**/
	public static final String JIEKUAN="515ba74c-4ed2-4384-aac5-31556a1d914d";
	/**应收合同评审**/
	public static final String YINGSHOUHT="cf588eea-b157-4a3b-bc97-4a54a407eb06";
	/**应付合同评审**/
	public static final String YINGFUHT="c309098f-26d0-40d1-a15f-71d85e6d421a";
	/**发票开具申请**/
	public static final String FAPIAO="c36e62fe-5dbb-4f7d-b796-80b8e1cd6500";
	/**投标或履约保证金**/
	public static final String TOUBIAO="a618b8d2-ff76-465b-8db3-b292f962576f";
	/**分包方评审**/
	public static final String FENBAO="c3aeaae0-b582-4f89-ac77-023f38a651a2";
	/**付款申请**/
	public static final String FUKUAN="dd271305-d12b-4edf-8042-537877d6e33d";
}
