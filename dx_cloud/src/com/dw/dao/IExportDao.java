package com.dw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dw.model.ExportMessage;

public interface IExportDao {

	List<Map<String, Object>> test(ExportMessage message);

	/**
	 * 获取指定字段的内容
	 * @return
	 */
	HashMap<String, Object> getFieldsMap(Map<String, Object> map) throws Exception;

	void getFieldHeadHibenate(ExportMessage message,String sql) throws Exception;
	/**
	 * 获取 数据
	 * @param sql 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getData(ExportMessage message, String sql) throws Exception;
	/**
	 * 获取行数
	 * @param sql
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getCount(ExportMessage message, String sql)throws Exception;
	
	/**
	 * 测试 sql
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqls(ExportMessage message) throws Exception;
	
	/**
	 * 获客 dal_bdcsc_fix_opp_nbr_dur_info_special_msk_d 全国记录数及波动率统计 图2 参数: dt 全国数据
	 * dal_bdcsc_fix_opp_nbr_dur_info_special_msk_d
	 * report_ac_cust_call.do?fieldNameEN=requestType1,requestType2,dts&fieldVal=2,dal_bdcsc_fix_opp_nbr_dur_info_special_msk_d,20190310-20190316&typechart=1
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlGuestcall(ExportMessage message) throws Exception;

	/**
	 * 获客 dal_bdcsc_fix_opp_nbr_dur_info_special_msk_d 全国记录数及波动率统计 图3 参数: dt 全国数据
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlGuestcallUserProportion(ExportMessage message) throws Exception;
	
	/**
	 * 获客 dal_bdcsc_fix_opp_nbr_dur_info_special_msk_d 全国异常记录数统计 图4 参数: dt 全国数据
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlGuestcallAbnormalTotal(ExportMessage message)throws Exception;
	
	/**
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlGuest(ExportMessage message) throws Exception;
	/**
	 * IDMapping ID 体系 ID总量历史趋势图 report_id_mapping_cover_rate.do 图1 参数 dt prov_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlIDMappingIDsystem(ExportMessage message) throws Exception;
	
	/**
	 * 位置融合  dwi_res_regn_mergelocation_msk_d  全国CityId字段校验统计	report_dwi_res_regn_mergelocation_msk_d_citysource	dt,prov_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlLocationFusionMergelocationCitysource(ExportMessage message) throws Exception;
 
	/**
	 * 位置融合  dwi_res_regn_mergelocation_msk_d  全国超速用户数及记录数占比统计	report_dwi_res_regn_mergelocation_msk_d_speeding	dt,prov_id,city_id
	 * 全国超速用户数及记录数占比统计
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlLocationFusionMergelocationSpeeding(ExportMessage message) throws Exception;
 
	/**
	 * 位置融合  dwi_res_regn_mergelocation_msk_d  全国乒乓用户数及记录数占比统计	report_dwi_res_regn_mergelocation_msk_d_pp	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlLocationFusionMergelocationPp(ExportMessage message) throws Exception;
	
	
	/**
	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国城市用户数及记录数统计	report_dwi_res_regn_mergelocation_msk_d_cityid	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlLocationFusionMergelocationCityid(ExportMessage message) throws Exception;
	
	
	/**
	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国城市用户数及记录数波动率统计	report_dwi_res_regn_mergelocation_msk_d_prov_volatility	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String getSqlLocationFusionMergelocationVlatility(ExportMessage message) throws Exception;
	

	/**
	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国城市分类数据源用户数及记录数波动率统计	report_dwi_res_regn_mergelocation_msk_d_prov_source_volatility	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	 String getSqlLocationFusionprovSourceVolatility(ExportMessage message) throws Exception;
	 


 	/**
 	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国分时段城市用户数及记录数统计	report_dwi_res_regn_mergelocation_msk_d_24timeslot	dt,prov_id,time[00,01,02,...23]
 	 * @param message
 	 * @return
 	 * @throws Exception
 	 */
	String LocationFusionMergelocation24timeslot(ExportMessage message) throws Exception;
	
	
	/**
	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国用户数统计	report_dwi_res_regn_mergelocation_msk_d_interval	dt,prov_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	 String LocationFusionMergelocationSourceNumRate(ExportMessage message) throws Exception;
	 
 
	 /**
	  * 位置融合 dwi_res_regn_mergelocation_msk_d 全国用户数统计	report_dwi_res_regn_mergelocation_msk_d_interval	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	String LocationFusionMergelocation_mskInterval(ExportMessage message) throws Exception;

	/**
	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国分区间位置更新用户数及记录数统计	report_dwi_res_regn_mergelocation_msk_d_update_interval	dt,prov_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String LocationFusionMergelocationUpdateInterval(ExportMessage message) throws Exception;
	
	/**
	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国用户停留城市用户数统计出行省份	report_dwi_res_regn_mergelocation_msk_d_range_provid	dt,prov_id（全国）
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String LocationFusionMergelocationRangeProvid(ExportMessage message) throws Exception;
	
	/**
	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国用户停留城市用户数统计出行城市	report_dwi_res_regn_mergelocation_msk_d_range_cityid	dt,prov_id(全国)  city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String LocationFusionMergelocationRangeCityid(ExportMessage message) throws Exception;
	
	
	/**
	 * 位置融合 dwi_res_regn_mergelocation_msk_d 全国分类数据源乒乓记录数统计	report_dwi_res_regn_mergelocation_msk_d_pp	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	 String LocationFusionMergelocationPP(ExportMessage message) throws Exception;
	
	 /**
      * 位置融合 dwi_res_regn_mergelocation_msk_d  全国分类数据源超速记录数统计	report_dwi_res_regn_mergelocation_msk_d_speeding	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String LocationFusionMergelocationSpeeding(ExportMessage message) throws Exception;
	 
	
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d  全国分省乒乓记录数统计	report_dwi_res_regn_staypoint_msk_d_pp_all_num	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String StoppingPointStaypointPPAllNum(ExportMessage message) throws Exception;
	
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d  全国分地市乒乓记录数统计	report_dwi_res_regn_staypoint_msk_d_pp_num	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String StoppingPointStaypointPPNum(ExportMessage message) throws Exception;
	
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d  记录数及用户数统计	report_dwi_res_regn_staypoint_msk_d_num	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String StoppingPointStaypointDNum(ExportMessage message) throws Exception;
	
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d 分时段记录数及用户数统计	report_dwi_res_regn_staypoint_msk_d_24hour_num	dt,prov_id,city_id,time
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String StoppingPointStaypoint24hourNum(ExportMessage message) throws Exception;
		
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d 用户记录数区间统计	report_dwi_res_regn_staypoint_msk_d_interval	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String StoppingPointStaypointInterval(ExportMessage message) throws Exception;
	
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d 单用户记录数统计	report_dwi_res_regn_staypoint_msk_d_sixhours	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String StoppingPointStaypointSixhours(ExportMessage message) throws Exception;
	
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d  统计网格停留数分布情况{看看表中那天有数据} 20190115 - 20190123	report_dwi_res_regn_staypoint_msk_d_point	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String StoppingPointStaypointPoint(ExportMessage message) throws Exception;
	 
	
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d 无停留记录点网格点统计	report_dwi_res_regn_staypoint_msk_d_nogrid	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	 String StoppingPointStaypointNogrid(ExportMessage message) throws Exception;

	
	 /**
	  * 停留点 dwi_res_regn_staypoint_msk_d  无停留点用户数统计	report_dwi_res_regn_staypoint_msk_d_nopoint	dt,prov_id,city_id
	  * @param message
	  * @return
	  * @throws Exception
	  */
	String StoppingPointStaypointNopoint(ExportMessage message) throws Exception;
	
	/**
	 * 停留点 dwi_res_regn_staypoint_msk_d 停留时间分布	report_dwi_res_regn_staypoint_msk_d_rangetime	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String StoppingPointStaypointRangetime(ExportMessage message) throws Exception;

	
	/**
	 * 跨域 dws_wdtb_city_od_msk_d 记录数稽核{看看表中那天有数据}20181220,20190115,20190116,20190117,20190118	report_dws_wdtb_city_od_msk_d	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainCity(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_city_od_msk_d OD出行时长分布	report_dws_wdtb_city_od_msk_d_rangetime	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainCityRangetime(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_city_od_msk_d OD出行距离分布	report_dws_wdtb_city_od_msk_d_distance	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainCityDistance(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_city_od_msk_d 流出人口分布统计	report_dws_wdtb_city_od_msk_d_out	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainCityOut(ExportMessage message) throws Exception;
	
	
	/**
	 *  跨域 dws_wdtb_city_od_msk_d  流入人口分布统计	report_dws_wdtb_city_od_msk_d_in	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainCityIn(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_city_od_msk_d 流入流出人口分布_OD是否相同	report_dws_wdtb_city_od_msk_d_xor	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainCityXor(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_city_od_msk_d OD出行方式分布(以出行距离进行行转列)	report_dws_wdtb_city_od_msk_d_range	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainCityRange(ExportMessage message) throws Exception;

	 
	/**
	 * 跨域 dws_wdtb_county_od_msk_d  用户数、记录数及波动率统计	report_dws_wdtb_city_od_msk_d	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomaiCityMsk_d(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_county_od_msk_d OD出行时长分布	report_dws_wdtb_city_od_msk_d_rangetime	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainCityMsk_D_Rangetime(ExportMessage message) throws Exception;

	/**
	 * 跨域 dws_wdtb_county_od_msk_d  OD出行距离分布	report_dws_wdtb_city_od_msk_d_distance	dt,prov_id,city_id
	 * @param message
	 * @return sql
	 * @throws Exception
	 */
	String CrossDomainCityMsk_D_Distance(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_county_od_msk_d 流出人口分布统计	report_dws_wdtb_city_od_msk_d_out	dt,prov_id,city_id
	 * @param message
	 * @return sql
	 * @throws Exception
	 */
	String CrossDomainCityMsk_D_Out(ExportMessage message) throws Exception;
		
	/**
	 * 跨域 dws_wdtb_county_od_msk_d 流入人口分布统计	report_dws_wdtb_city_od_msk_d_in	dt,prov_id,city_id
	 * @param message
	 * @return sql
	 * @throws Exception
	 */
	String CrossDomainCityMsk_D_In(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_county_od_msk_d 流入流出人口分布_OD是否相同	report_dws_wdtb_city_od_msk_d_xor	dt,prov_id,city_id
	 * @param message
	 * @return sql
	 * @throws Exception
	 */
	String CrossDomainCityMsk_D_xor(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_history_access_county_msk_d 合规记录数及占比统计	report_dws_wdtb_history_access_city_msk_d	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainHistoryAccessCityMsk_D(ExportMessage message) throws Exception;
		
	/**
	 * 跨域 dws_wdtb_history_access_city_msk_d 乒乓城市个数统计	report_dws_wdtb_history_access_city_msk_d_pp	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainHistoryAccessCityMsk_D_PP(ExportMessage message) throws Exception;
		
	/**
	 * 跨域  dws_wdtb_history_access_county_msk_d  记录数稽核  report_dws_wdtb_history_access_msk_d_num  dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainHistoryAccessCityMsk_D_NUM(ExportMessage message) throws Exception;
	
		
	/**
	 * 跨域 dws_wdtb_history_access_county_msk_d 合规记录数及占比统计	report_dws_wdtb_history_access_city_msk_d	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainHistoryAccessCityMsk_D1(ExportMessage message) throws Exception;
			
	/**
	 * 跨域 dws_wdtb_history_access_county_msk_d 乒乓城市个数统 report_dws_wdtb_history_access_county_msk_d_pp	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainHistoryAccessCityMsk_D_PP1(ExportMessage message) throws Exception;
	
	/**
	 * 跨域 dws_wdtb_history_access_city_msk_d	记录数稽核	report_dws_wdtb_history_access_msk_d_num	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CrossDomainHistoryAccessCityMsk_D_NUM1(ExportMessage message) throws Exception;
		
	/**
	 * 居住人口属性 dws_wdtb_workplace_msk_w {看看表中那天有数据}	report_dws_wdtb_workplace_msk_w_city_volatility	dt,prov_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String ResidentialPopulationAttributeWorkplaceVolatility(ExportMessage message) throws Exception;
	
	  		
	/**
	 * 居住人口属性 dws_wdtb_workplace_msk_w 当前工作地置信度用户统计	report_dws_wdtb_workplace_msk_w_tracking	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String ResidentialPopulationAttributeWorkplaceTracking(ExportMessage message) throws Exception;
	
	/**
	 * 居住人口属性 dws_wdtb_resident_msk_w 用户数及波动率统计{这个日期有数据20190127}	report_dws_wdtb_resident_msk_w_city_volatility	dt,prov_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String ResidentialPopulationAttributeResidentCityVolatility(ExportMessage message) throws Exception;
	
		
	/**
	 * 居住人口属性 dws_wdtb_resident_msk_w 当前居住城市置信度用户数统计	report_dws_wdtb_resident_msk_w_tracking	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String ResidentialPopulationAttributeResidentTracking(ExportMessage message) throws Exception;
	
	/**
	 * CRM 用户帐务信息月宽表 dwi_act_acct_user_info_msk_m 在网移动用户账户信息情况统计	report_dwi_act_acct_user_info_msk_m_type	dt,prov_id(没有全部,全国 数据的问题请注意)
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CRMAcctUserInfoType(ExportMessage message) throws Exception;
	
	 
	/**
	 * 用户帐务信息月宽表 dwi_act_acct_user_info_msk_m 在网移动用户账户信息分布统计	report_dwi_act_acct_user_info_msk_m_cut	dt,prov_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CRMAcctUserInfoCUT(ExportMessage message) throws Exception;
	
		
	/**
	 * CRM 用户帐务费用信息月宽表 dwi_act_acct_user_fee_msk_m 在网移动用户账户信息情况统计	report_dwi_act_acct_user_fee_msk_m_type	dt,prov_id(没有全部,全国 数据的问题请注意)
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CRMAcctUserFeetype(ExportMessage message) throws Exception;
	
	
	/**
	 * 用户帐务费用信息月宽表	dwi_act_acct_user_fee_msk_m  在网移动用户账户信息分布统计	report_dwi_act_acct_user_fee_msk_m_cut	dt,prov_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String CRMAcctUserFeeCUT(ExportMessage message) throws Exception;
	
	/**
	 * 小蜗牛集市	 用户基础信息表  dal_snail_user_info_msk_m	全国记录数及用户数统计下载
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String SmallACCUser(ExportMessage message) throws Exception;
	
	/**
	 * 位置融合整合 居住人口属性 dws_wdtb_user_traffic_city_population_msk_w	全国用户数及波动率统计{波动率}20190121	report_dws_wdtb_user_traffic_city_population_msk_w	dt,prov_id,city_id
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String ResidentialPopulationAttributecityVolatility(ExportMessage message)throws Exception;
	
	/**
	 * 应用稽核 商业地产集市 商圈热力图表	o2oestate_trade_heatmap_msk_m 空值率
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String businessNUllRaito(ExportMessage message)throws Exception;
	
	/**
	 * 应用稽核 商业地产集市 商圈热力图表	o2oestate_trade_heatmap_msk_m  零值率
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String businessZeroRaito(ExportMessage message)throws Exception;
	
	/**
	 * 应用稽核 商业地产集市 商圈热力图表	o2oestate_trade_heatmap_msk_m   合规率
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String businessHeguiRaito(ExportMessage message)throws Exception;
	
	/**
	 * 应用稽核 商业地产集市 商场热力图表	o2oestate_mall_heatmap_msk_m 空值率
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String businessNUllmallRaito(ExportMessage message)throws Exception;
	
	/**
	 * 应用稽核 商业地产集市 商场热力图表	o2oestate_mall_heatmap_msk_m  零值率
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String businessZeromallRaito(ExportMessage message) throws Exception;
	
	/**
	 * 应用稽核 商业地产集市 商场热力图表	o2oestate_mall_heatmap_msk_m  合规率
	 * @param message
	 * @return
	 * @throws Exception
	 */
	String businessHeguimallRaito(ExportMessage message)throws Exception;
	
}
