package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.CusLocationDao;
import com.ennuova.entity.Cuslocation;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Repository("cusLocationDaoImpl")
public class CusLocationDaoImpl extends BaseDAOImpl<Cuslocation> implements
		CusLocationDao {

	/**
	 * 根据客户id查找位置信息
	 *@author sududa
	 *@date 2016年10月18日
	 * @param cusId
	 * @return
	 */
	@Override
	public Map<String, Object> findByCusId(String cusId) {
		String sql = "select cl.ID,cl.LNG,cl.LAT"
				   + " from CUS_LOCATION cl"
				   + " where cl.CUS_ID = ?";
		return findOneForJdbc(sql, cusId);
	}

	/**
	 * 修改客户的位置信息
	 *@author sududa
	 *@date 2016年10月18日
	 * @param cuslocation
	 * @return
	 */
	@Override
	public Integer updateByCusId(Cuslocation cuslocation) {
		String sql = "update CUS_LOCATION cl"
				   + " set cl.LNG = ?,cl.LAT = ?,cl.LOCATION_TYPE = ?,cl.CREATE_DATE = ?"
				   + " where cl.CUS_ID = ?";
		return executeSql(sql, cuslocation.getLng(),cuslocation.getLat(),cuslocation.getLocationType(),cuslocation.getCreatetime(),cuslocation.getCusId());
	}

	/**
	 * 根据客户id删除客户的位置信息
	 *@author sududa
	 *@date 2016年10月19日
	 * @param cusId
	 * @return
	 */
	@Override
	public Integer deleteByCusId(String cusId) {
		String sql = "delete from CUS_LOCATION cl"
				   + " where cl.CUS_ID = ?";
		return executeSql(sql, cusId);
	}

	/**
	 * 查询附近的人列表
	 *@author sududa
	 *@date 2016年10月19日
	 * @param page 当前页(默认值为1)
	 * @param rows 每页显示的条数(默认值为10)
	 * @param interest
	 * @param authentication
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doList(Integer page, Integer rows,String interest, String authentication, String loginId, String sex) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		StringBuilder sql = new StringBuilder(""
				          + "select cus.ID,nvl(cus.FNICK,'匿名') as FNICK,"
				          + "nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest,"
				          + "?||cus.FPHOTO as FPHOTO,cus.FTEL,cus.FSEX,"
				          + "nvl(cus.FOLLOW_COUNT,0) as followCount,nvl(cus.FANS_COUNT,0) as fansCount,"
				          + "nvl(line.FNAME,'未绑定车辆') as lineName,"
				          + "l.LNG,l.LAT,"
				          + "box.FJHYF,to_char(box.FJHTIME,'yyyy-mm-dd hh24:mi:ss') as jhTime"
				          + " from PUB_CUSTOMER cus"
				          + " left join PUB_CARINFO car"
				          + " on cus.ID = car.FCUSTOMER"
				          + " left join PUB_LINE line"
				          + " on car.FLINE = line.ID"
				          + " left join CUS_LOCATION l"
				          + " on cus.ID = l.CUS_ID"
				          + " left join PUB_BOX box"
				          + " on car.ID = box.FCLID"
				          + " where cus.ID in ("
				          + " select cl.CUS_ID from CUS_LOCATION cl"
				          + " ) and cus.ID ! = ? and car.FDEFAULT = ?"
				          + " and cus.ID not in(select con.CONTACTS_ID from PUB_CONTACTS con"//已经拉黑的附近的人也要筛选掉
				          + " where con.LOGIN_ID = ? and con.RELATION = ?)");
		if(StringUtil.isNotEmpty(interest)){//表示根据兴趣查找
			String[] strArr = interest.split("# ");//拆分出所有以# 分隔开的性趣
			sql.append(" and (instr(cus.CUS_INTEREST,'"+strArr[0]+"') > 0");
			for(int i = 1;i < strArr.length;i++){
				if(StringUtil.isNotEmpty(strArr[i])){
					sql.append("or instr(cus.CUS_INTEREST,'"+strArr[i]+"') > 0");
				}
			}
			sql.append(")");
		}
		if(StringUtil.isNotEmpty(authentication)){//表示只查找认证的车主
			sql.append(" and cus.ID in ("
					 + " select car.FCUSTOMER from PUB_CARINFO car"
					 + " where car.ID in ("
					 + " select box.FCLID from PUB_BOX box"
					 + ")"
					 + ")");
		}
		if(StringUtil.isNotEmpty(sex)){//表示加上根据性别查找
			sql.append(" and cus.FSEX = '"+sex+"'");
		}
		sql.append(" order by cus.ID");
//		return findForJdbc(sql.toString(), loginId,1);
		return findForJdbcPage(sql.toString(), page, rows,imgUrl, loginId,1,loginId,2);
	}

	/**
	 * 查看附近人的详情
	 *@author sududa
	 *@date 2016年10月19日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> doDetail(String id) {
		String sql = "select cus.FPHOTO,cus.FNICK,cus.CUS_INTEREST as cusInterest,"
				   + "model.FNAME as modelName,"
				   + "to_char(box.FJHTIME,'yyyy-mm-dd hh24:mi:ss') as jhTime"
				   + " from PUB_CUSTOMER cus"
				   + " left join PUB_CARINFO car"
				   + " on cus.ID = car.FCUSTOMER"
				   + " left join PUB_VEHICLEMODEL model"
				   + " on car.FVEHICLEMODEL = model.ID"
				   + " left join PUB_BOX box"
				   + " on car.ID = box.FCLID"
				   + " where cus.ID = ? and car.FDEFAULT = 1";
		return findOneForJdbc(sql, id);
	}



}
