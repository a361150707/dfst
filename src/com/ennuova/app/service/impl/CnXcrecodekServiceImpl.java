package com.ennuova.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.ennuova.app.service.CnXcrecodeService;
import com.ennuova.dao.CnBjrecodeDao;
import com.ennuova.dao.CnSsxcrecodeDao;
import com.ennuova.dao.CnXcrecodeDao;
import com.ennuova.entity.CnSsxcrecode;
import com.ennuova.entity.CnXcrecode;
import com.ennuova.entity.XcRecord;
import com.ennuova.entity.XcrecodeVO;
import com.ennuova.util.Constant;
import com.ennuova.util.ConvertTools;
import com.ennuova.util.URLRequest;
import com.ennuova.util.Util;
/**行程管理serviceImpl
 * @author 李智辉 
 * 2015-12-9下午1:32:51
 */
@Service("cnXcrecodeService")
public class CnXcrecodekServiceImpl implements CnXcrecodeService {
	@Resource
	private CnXcrecodeDao cnXcrecodeDao;
	@Resource
	private CnBjrecodeDao cnBjrecodeDao; 
	@Resource
	private CnSsxcrecodeDao cnSsxcrecodeDao;
	@Override
	public Map<String, Object> getLbsList(Long fclid, int status) {
		List<CnXcrecode> cnXcrecodes = new ArrayList<CnXcrecode>();
		List<CnXcrecode> cnXcrecodes1 = new ArrayList<CnXcrecode>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> addressList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		List<String> addressListStr = new ArrayList<String>();
		List<String> LBSList = new ArrayList<String>();
		Map<String, List<String>> mapAddress = new HashMap<String, List<String>>();
		try {
			String startTime = "";
			String endTime = "";
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (status==Constant.STATUS_ONE) {
				endTime = dateFormat.format(date); 
				date.setDate(date.getDate()-7);
				startTime = dateFormat.format(date);
			}else if (status==Constant.STATUS_TWO) {
				endTime = dateFormat.format(date); 
				date.setDate(date.getDate()-15);
				startTime = dateFormat.format(date);
			}else {
				endTime = dateFormat.format(date); 
				date.setDate(date.getDate()-30);
				startTime = dateFormat.format(date);
			}
			
			cnXcrecodes = cnXcrecodeDao.queryCnXcrecodeListByEndTime(fclid, startTime, endTime);
			for (CnXcrecode cnXcrecode : cnXcrecodes) {
				/*if(cnXcrecode.getFbeginjd()==null||cnXcrecode.getFbeginwd()==null||cnXcrecode.getFbeginjd()==0||cnXcrecode.getFbeginwd()==0){
					String selectTime = dateFormat.format(new Date(cnXcrecode.getFbegintime().getTime()+10*60*1000));
					String selectTimeFiveAgo = dateFormat.format(cnXcrecode.getFbegintime());
					CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getXcrecodeLBSByBegin(fclid, selectTime, selectTimeFiveAgo);
					if(cnSsxcrecode.getFjd()!=null){
						cnXcrecode.setFbeginjd(cnSsxcrecode.getFjd());
						cnXcrecode.setFbeginwd(cnSsxcrecode.getFwd());
					}
				}
				if(cnXcrecode.getFendjd()==null||cnXcrecode.getFendwd()==null||cnXcrecode.getFendjd()==0||cnXcrecode.getFendwd()==0){
					String selectTime = dateFormat.format(cnXcrecode.getFendtime());
					String selectTimeFiveAgo = dateFormat.format(new Date(cnXcrecode.getFendtime().getTime()-10*60*1000));
					CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getXcrecodeLBS(fclid, selectTime, selectTimeFiveAgo);
					if(cnSsxcrecode.getFjd()!=null){
						cnXcrecode.setFendjd(cnSsxcrecode.getFjd());
						cnXcrecode.setFendwd(cnSsxcrecode.getFwd());
					}
				}*/
				if(cnXcrecode.getFendjd()!=null&&cnXcrecode.getFendwd()!=null&&cnXcrecode.getFendjd()>0&&cnXcrecode.getFendwd()>0&&
						cnXcrecode.getFbeginjd()!=null&&cnXcrecode.getFbeginwd()!=null&&cnXcrecode.getFbeginjd()>0&&cnXcrecode.getFbeginwd()>0){
					double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
		            double x = cnXcrecode.getFbeginjd() - 0.0065, y = cnXcrecode.getFbeginwd() - 0.006;
		            double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		            double theta =Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		            cnXcrecode.setFbeginjd(z * Math.cos(theta));
		            cnXcrecode.setFbeginwd(z * Math.sin(theta));
		            
		            double x1 = cnXcrecode.getFendjd() - 0.0065, y1 = cnXcrecode.getFendwd() - 0.006;
		            double z1 = Math.sqrt(x1 * x1 + y1 * y1) - 0.00002 * Math.sin(y1 * x_pi);
		            double theta1 =Math.atan2(y1, x1) - 0.000003 * Math.cos(x1 * x_pi);
		            cnXcrecode.setFendjd(z1 * Math.cos(theta1));
		            cnXcrecode.setFendwd(z1 * Math.sin(theta1));
		            LBSList.add(z * Math.cos(theta)+","+z * Math.sin(theta));
		            LBSList.add(z1 * Math.cos(theta1)+","+z1 * Math.sin(theta1));
		            cnXcrecodes1.add(cnXcrecode);
				}
			}
			System.out.println(LBSList.size());
			int lineNun = LBSList.size()%20==0? LBSList.size()/20:LBSList.size()/20+1;
			for (int i = 0; i < lineNun; i++) {
				List<String> list = new ArrayList<String>();
				if(LBSList.size()>=20){
					list = LBSList.subList(0, 20);
				}else {
					list = LBSList.subList(0, LBSList.size());
				}
				mapAddress = Util.getAddressByLBS(list);
				if(LBSList.size()>19){
					for (int j = 0; j < 20; j++) {
						LBSList.remove(0);
					}
				}
				tempList.addAll(mapAddress.get("city"));
				addressListStr.addAll(mapAddress.get("address"));
			}
			
			
			
			if(null!=tempList){
				for(String str:tempList){
			        if(!addressList.contains(str)){  
			        	addressList.add(str);  
			        }  
			    } 
			}
			map.put("cnXcrecodes", cnXcrecodes1);
			map.put("cityList", addressList);
			map.put("addressList", addressListStr);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	public static void main(String[] args) {
		Date date = new Date(1460034054000L);
		System.out.println(date);
		Date date1 = new Date(1454636362000L);
		System.out.println(date1);
	}
	@Override
	public List<XcrecodeVO> getXcRecordList(Long fclid,String time) {
		String startTime = "";
		String endTime = "";
		List<XcrecodeVO> list = new ArrayList<XcrecodeVO>();
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<CnXcrecode> cnXcrecodes = this.cnXcrecodeDao.getXcrecodeDataByTime(fclid,time);
			if (cnXcrecodes.size()!=0) {
				for (CnXcrecode cnXcrecode : cnXcrecodes) {
					XcrecodeVO xcrecodeVO = new XcrecodeVO();
					/*if(cnXcrecode.getFbeginjd()==null||cnXcrecode.getFbeginwd()==null||cnXcrecode.getFbeginjd()==0||cnXcrecode.getFbeginwd()==0){
						String selectTime = dateFormat.format(new Date(cnXcrecode.getFbegintime().getTime()+10*60*1000));
						String selectTimeFiveAgo = dateFormat.format(cnXcrecode.getFbegintime());
						CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getXcrecodeLBSByBegin(fclid, selectTime, selectTimeFiveAgo);
						if(cnSsxcrecode.getFjd()!=null){
							cnXcrecode.setFbeginjd(cnSsxcrecode.getFjd());
							cnXcrecode.setFbeginwd(cnSsxcrecode.getFwd());
						}
					}
					if(cnXcrecode.getFendjd()==null||cnXcrecode.getFendwd()==null||cnXcrecode.getFendjd()==0||cnXcrecode.getFendwd()==0){
						String selectTime = dateFormat.format(cnXcrecode.getFendtime());
						String selectTimeFiveAgo = dateFormat.format(new Date(cnXcrecode.getFendtime().getTime()-10*60*1000));
						CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getXcrecodeLBS(fclid, selectTime, selectTimeFiveAgo);
						if(cnSsxcrecode.getFjd()!=null){
							cnXcrecode.setFendjd(cnSsxcrecode.getFjd());
							cnXcrecode.setFendwd(cnSsxcrecode.getFwd());
						}
					}*/
					if(cnXcrecode.getFendjd()!=null&&cnXcrecode.getFendwd()!=null&&cnXcrecode.getFendjd()>0&&cnXcrecode.getFendwd()>0&&
							cnXcrecode.getFbeginjd()!=null&&cnXcrecode.getFbeginwd()!=null&&cnXcrecode.getFbeginjd()>0&&cnXcrecode.getFbeginwd()>0){
						double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
			            double x = cnXcrecode.getFbeginjd() - 0.0065, y = cnXcrecode.getFbeginwd() - 0.006;
			            double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
			            double theta =Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
			            
			            double x1 = cnXcrecode.getFendjd() - 0.0065, y1 = cnXcrecode.getFendwd() - 0.006;
			            double z1 = Math.sqrt(x1 * x1 + y1 * y1) - 0.00002 * Math.sin(y1 * x_pi);
			            double theta1 =Math.atan2(y1, x1) - 0.000003 * Math.cos(x1 * x_pi);
			            xcrecodeVO.setFbeginjd(z * Math.cos(theta));
			            xcrecodeVO.setFbeginwd(z * Math.sin(theta));
			            xcrecodeVO.setFendjd(z1 * Math.cos(theta1));
			            xcrecodeVO.setFendwd(z1 * Math.sin(theta1));
			            List<String> LBSList = new ArrayList<String>();
			            LBSList.add(z * Math.cos(theta)+","+z * Math.sin(theta));
			            LBSList.add(z1 * Math.cos(theta1)+","+z1 * Math.sin(theta1));
			            List<String> addressList = Util.getAddress(LBSList);
						if(addressList.size()>1){
							xcrecodeVO.setStartAddress(addressList.get(0));
							xcrecodeVO.setEndAddress(addressList.get(1));
						}
						List<String> stringList = new ArrayList<String>();
						startTime = dateFormat.format(cnXcrecode.getFbegintime());
						endTime = dateFormat.format(cnXcrecode.getFendtime());
						stringList = cnBjrecodeDao.getCnBjrecodeList(fclid, startTime, endTime);
						int tA = 0,tB = 0;
						for (String str : stringList) {
							if (Constant.TA.equals(str)) {
								tA++;
							}
							if (Constant.TB.equals(str)) {
								tB++;
							}
						}
						String yearMonth = startTime.substring(2,4)+startTime.substring(5,7)+startTime.substring(8,10);
						xcrecodeVO.setId(cnXcrecode.getId());
						xcrecodeVO.settA(ConvertTools.intToString(tA));//设置加速次数
						xcrecodeVO.settB(ConvertTools.intToString(tB));//减速次数
						xcrecodeVO.setTimeStr(yearMonth);
						xcrecodeVO.setFbcxsyh(cnXcrecode.getFbcxsyh());//总油耗
						xcrecodeVO.setFbcxslc(cnXcrecode.getFbcxslc());//总里程
						if(cnXcrecode.getFbcxslc()>0){
							xcrecodeVO.setAverageOil(cnXcrecode.getFbcxsyh()/cnXcrecode.getFbcxslc()*100);//平均油耗
						}else {
							xcrecodeVO.setAverageOil(0d);//平均油耗
						}
						Long timeLong = (cnXcrecode.getFendtime().getTime() - cnXcrecode.getFbegintime().getTime())/1000/60; //计算分钟数
						xcrecodeVO.setTravelMin(ConvertTools.longToString(timeLong));//行驶分钟数
						
						list.add(xcrecodeVO);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}
	@Override
	public List<XcRecord> getXcRecordListByMaxId(Long fclid, Long maxId) {
		String startTime = "";
		String endTime = "";
		List<XcRecord> list = new ArrayList<XcRecord>();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<CnXcrecode> cnXcrecodes = this.cnXcrecodeDao.getOneYearData(fclid, maxId);
			if (cnXcrecodes.size()!=0) {
				for (CnXcrecode cnXcrecode : cnXcrecodes) {
					List<String> stringList = new ArrayList<String>();
					XcRecord xcRecord = new XcRecord();
					startTime = dateFormat.format(cnXcrecode.getFbegintime());
					endTime = dateFormat.format(cnXcrecode.getFendtime());
					stringList = cnBjrecodeDao.getCnBjrecodeList(fclid, startTime, endTime);
					int tA = 0,tB = 0;
					for (String str : stringList) {
						if (Constant.TA.equals(str)) {
							tA++;
						}
						if (Constant.TB.equals(str)) {
							tB++;
						}
					}
					xcRecord.setId(cnXcrecode.getId());
					xcRecord.settA(ConvertTools.intToString(tA));//设置加速次数
					xcRecord.settB(ConvertTools.intToString(tB));//减速次数
					String yearMonth = startTime.substring(2,4)+startTime.substring(5,7)+startTime.substring(8,10);
					xcRecord.setStartDate(yearMonth);//年月
					xcRecord.setMileage(ConvertTools.doubleToString(cnXcrecode.getFbcxslc()));//总里程
					xcRecord.setOil(ConvertTools.doubleToString(cnXcrecode.getFbcxsyh()));//总油耗
					Long timeLong = (cnXcrecode.getFendtime().getTime() - cnXcrecode.getFbegintime().getTime())/1000/60; //计算分钟数
					xcRecord.setMin(ConvertTools.longToString(timeLong));//行驶分钟数
					list.add(xcRecord);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
