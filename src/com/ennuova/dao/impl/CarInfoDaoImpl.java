package com.ennuova.dao.impl;

import java.io.Serializable;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CarInfoDao;
import com.ennuova.entity.PubBrand;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubCustomer;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.util.DButil;
import com.ennuova.util.DateUtils;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Repository("carInfoDao")
public class CarInfoDaoImpl extends DaoSupportImpl<PubCarinfo> implements CarInfoDao{

	/**
	 * 获取车辆信息
	 */

	@Override
	public PubCarinfo queryCarInfo(long myCarId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql = " SELECT CAR.ID,CAR.FCUSTOMER,CAR.FBRAND,CAR.FCARNUM,CAR.FLINE,CAR.FVEHICLEMODEL,CAR.FCARCODE,CAR.FENGINENUM," +
				     " BRAND.FNAME BNAME,BRAND.FLOGO,LINE.FNAME LNAME,VEHICLEMODEL.FNAME VNAME FROM PUB_CARINFO CAR " +
				     " LEFT JOIN PUB_BRAND BRAND ON CAR.FBRAND=BRAND.ID LEFT JOIN PUB_LINE LINE ON CAR.FLINE=LINE.ID " +
				     " LEFT JOIN PUB_VEHICLEMODEL VEHICLEMODEL ON CAR.FVEHICLEMODEL=VEHICLEMODEL.ID WHERE CAR.ID='"+myCarId+"'";
		PubCarinfo carInfo =  new PubCarinfo();
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				carInfo.setId(rs.getLong("ID"));
				carInfo.setBid(rs.getLong("FBRAND"));
				carInfo.setLid(rs.getLong("FLINE"));
				carInfo.setVid(rs.getLong("FVEHICLEMODEL"));
				carInfo.setFcarcode(rs.getString("FCARCODE"));
				carInfo.setFenginenum(rs.getString("FENGINENUM"));
				carInfo.setBname(rs.getString("BNAME"));
				carInfo.setLname(rs.getString("LNAME"));
				carInfo.setVname(rs.getString("VNAME"));
				carInfo.setFcarnum(rs.getString("FCARNUM"));
				carInfo.setCusId(rs.getLong("FCUSTOMER"));
				carInfo.setBlogo(urlPath+rs.getString("FLOGO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return carInfo;
	}

	/**
	 * 查询车辆品牌
	 */
	@Override
	public List queryCarBrand() {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PUB_BRAND";
		List reList=new ArrayList();
		String urlPath=UrlUtil.getInstance().getImgurl();
		List brandList=new ArrayList();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map reMap=new HashMap();
				reMap.put("id", rs.getLong("ID"));
				reMap.put("fname", rs.getString("fname"));
				reMap.put("fcj", rs.getString("FCJ"));
				reMap.put("fcodeabb", rs.getString("FCODEABB"));
				reMap.put("fnumber", rs.getString("FNUMBER"));
				reMap.put("flogo", urlPath+rs.getString("FLOGO"));
				reList.add(reMap);
			}
			String[] zimu =new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","W","X","Y","Z"};
			for(int i=0;i<zimu.length;i++){
				Map ObjMap=new HashMap();
				List objList=new ArrayList();
				for(int j=0;j<reList.size();j++){
					Map test=(Map) reList.get(j);
					if(zimu[i].equals(test.get("fcodeabb"))){
						objList.add(test);
					}
				}
				if(objList.size()>0){
					ObjMap.put("key", zimu[i]);
					ObjMap.put("brandList", objList);
					brandList.add(ObjMap);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return brandList;
	}

	/**
	 * 根据品牌获取车系车型
	 */
	@Override
	public List queryBrandModel(String brandId) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT LINE.ID,LINE.FBRAND,LINE.FNUMBER,LINE.FNAME,M.ID MID,M.FNAME MNAME FROM PUB_LINE LINE ,PUB_VEHICLEMODEL M WHERE M.FLINE=LINE.ID AND LINE.FBRAND='"+brandId+"'  ORDER BY LINE.FNUMBER ASC,M.FNAME DESC";
		ArrayList<HashMap<String, String>> reList=new ArrayList<HashMap<String, String>>();
		LinkedHashMap<String,String> chexi= new LinkedHashMap<String,String>();
		List carInfoList=new ArrayList();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> reMap=new HashMap<String, String>();
				reMap.put("xid", Long.toString(rs.getLong("ID")));
				reMap.put("mid", Long.toString(rs.getLong("MID")));
				reMap.put("lname", rs.getString("FNAME"));
				reMap.put("mname", rs.getString("MNAME"));
				String xid= Long.toString(rs.getLong("ID"));
				chexi.put(xid, xid);
				reList.add(reMap);
			}
			for (String key : chexi.keySet()) {
				LinkedHashMap<String, Object> ObjMap=new LinkedHashMap<String, Object>();
				List objList=new ArrayList();
				for(int i=0;i<reList.size();i++){
					Map test=(Map) reList.get(i);
					if(key.equals(test.get("xid"))){
						ObjMap.put("lname", test.get("lname"));
						objList.add(test);
					}
				}
				ObjMap.put("xid", key);
				ObjMap.put("cxing", objList);
				carInfoList.add(ObjMap);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return carInfoList;
	}

	/**
	 * 获取我的爱车列表信息
	 */
	@Override
	public List<PubCarinfo> queryAllCarInfo(long userId) {
		List<PubCarinfo> Cars=new ArrayList<PubCarinfo>();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT CAR.ID,CAR.FCARNUM,CAR.FDEFAULT,CAR.FCUSTOMER,CAR.FBRAND,CAR.FCARNUM,CAR.FLINE,CAR.FVEHICLEMODEL,CAR.FCARCODE,CAR.FENGINENUM," +
	     " BRAND.FNAME BNAME,BRAND.FLOGO,LINE.FNAME LNAME,LINE.S_PICTURE as linePicture,VEHICLEMODEL.FNAME VNAME,(SELECT BOX.ID FROM PUB_BOX BOX WHERE CAR.ID=BOX.FCLID) ISBIND FROM PUB_CARINFO CAR " +
	     " LEFT JOIN PUB_BRAND BRAND ON CAR.FBRAND=BRAND.ID LEFT JOIN PUB_LINE LINE ON CAR.FLINE=LINE.ID " +
	     " LEFT JOIN PUB_VEHICLEMODEL VEHICLEMODEL ON CAR.FVEHICLEMODEL=VEHICLEMODEL.ID WHERE CAR.FCUSTOMER='"+userId+"'  ORDER BY CAR.ID DESC";
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				PubCarinfo c= new PubCarinfo();
				c.setId(rs.getLong("ID"));
				c.setBid(rs.getLong("FBRAND"));
				c.setLid(rs.getLong("FLINE"));
				c.setVid(rs.getLong("FVEHICLEMODEL"));
				c.setFcarcode(rs.getString("FCARCODE"));
				c.setFenginenum(rs.getString("FENGINENUM"));
				c.setBname(rs.getString("BNAME"));
				c.setLname(rs.getString("LNAME"));
				c.setVname(rs.getString("VNAME"));
				c.setCusId(rs.getLong("FCUSTOMER"));
				c.setFcarnum(rs.getString("FCARNUM"));
				c.setFdefault(rs.getLong("FDEFAULT"));
				c.setBlogo(urlPath+rs.getString("FLOGO"));
				c.setBoxId(rs.getLong("ISBIND"));
				String linePicture = rs.getString("linePicture");
				if(StringUtil.isNotEmpty(linePicture)){
					String imgUrl = UrlUtil.getInstance().getImgurl();
					linePicture = imgUrl + linePicture;
				}
				c.setLinePicture(linePicture);
				Cars.add(c);
			} 
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return Cars;
	}

	@Override
	public String addCarInfo(String userId, String brandId, String chexiId,
			String chexingId,String carnum,String carCode) {
		PubCarinfo c=new PubCarinfo();
		String result="success";
		try{
			PubCustomer cus=new PubCustomer();
			cus.setId(Long.valueOf(userId));
	        c.setPubCustomer(cus); 
	        PubBrand brand=new PubBrand();
	        brand.setId(Long.valueOf(brandId));
	        c.setPubBrand(brand);
	        PubLine line=new PubLine();
	        line.setId(Long.valueOf(chexiId));
	        c.setPubLine(line);
	        PubVehiclemodel vehiclemodel=new PubVehiclemodel();
	        vehiclemodel.setId(Long.valueOf(chexingId));
	        c.setPubVehiclemodel(vehiclemodel);
	        c.setFcarnum(carnum);
	        c.setFdefault(0l);
	        c.setFcarcode(carCode);
	        save(c);
		}catch(Exception e){
			result="false";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateCarInfo(String carId, String label, String content) {
		String sql = "";
		Date date = null;
		if("finsexpire".equals(label)){//修改保险到期
			sql = "UPDATE PUB_CARINFO SET FINSEXPIRE = ? WHERE ID = ?";
			date = DateUtils.parseDate(content);
		}
		if("maintainTime".equals(label)){//修改保养时间
			sql = "UPDATE PUB_CARINFO SET MAINTIAN_TIME = ? WHERE ID = ?";
			date = DateUtils.parseDate(content);
			return executeSql(sql, date, carId); 
		}
		if("fyearinsexpire".equals(label)){//修改年检到期
			sql = "UPDATE PUB_CARINFO SET FYEARINSEXPIRE = ? WHERE ID = ?";
			date = DateUtils.parseDate(content);
		}
		if("fguaexpire".equals(label)){//修改保修到期
			sql = "UPDATE PUB_CARINFO SET FGUAEXPIRE = ? WHERE ID = ?";
			date = DateUtils.parseDate(content);
		}
		if("maintainMileage".equals(label)){//修改保养 
			sql = "UPDATE PUB_CARINFO SET MAINTAIN_MILEAGE = ? WHERE ID = ?";
			return executeSql(sql, content, carId);
		}
		if(StringUtil.isNotEmpty(date)){
			return executeSql(sql, date, carId);
		}else{
			if(label.equals("fchassisno")){//修改底盘号（车架号）
				sql = "UPDATE PUB_CARINFO SET FCARCODE = ? where ID = ?";
			}else if(label.equals("flicenseno")){//修改车牌号
				sql = "UPDATE PUB_CARINFO SET FCARNUM = ? WHERE ID= ?";
			}else if(label.equals("fengineno")){//修改发动机号
				sql="UPDATE PUB_CARINFO SET FENGINENUM = ? WHERE ID = ?";
			}else if("maintainMileage".equals(label)){//修改保养里程
				sql = "UPDATE PUB_CARINFO SET MAINTAIN_MILEAGE = ? WHERE ID = ?";
			}
		}
		if(StringUtil.isNotEmpty(sql)){
			return executeSql(sql, content, carId);
		}else{
			return 0;
		}
	}

	@Override
	public String modifyCarInfo(String carId, String brandId, String chexiId,
			String chexingId) {
		Connection conn = null;
		PubCarinfo c=new PubCarinfo();
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		String result="success";
		try{
			String sql="UPDATE PUB_CARINFO SET FBRAND='"+brandId+"'," +
			           " FVEHICLEMODEL='"+chexingId+"',FLINE='"+chexiId+"' where ID='"+carId+"'";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
//			System.out.println("修改品牌车型车系成功--felix");   
			conn.close();  
		}catch (Exception e){
			result="false";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List getModel(long fline) {
			Connection conn=null;
			conn=getSessionFactory().getCurrentSession().connection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String sql="";
			List<PubVehiclemodel> mList = new ArrayList<PubVehiclemodel>();
			try {
				sql="SELECT * FROM PUB_VEHICLEMODEL WHERE FLINE='"+fline+"'";
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					PubVehiclemodel m = new PubVehiclemodel();
					m.setId(rs.getLong("id"));
					m.setFname(rs.getString("fname"));
					mList.add(m);
				}
//				System.out.println("获取车型成功！");
//				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
//				System.out.println("获取车型失败！");
			}finally{
				DButil.closeDb(conn, ps, rs);
			}
			return mList;
	}

	@Override
	public PubCarinfo queryCarinfo(long cusId) {
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="";
		PubCarinfo info = new PubCarinfo();
		try {
			sql=" SELECT CAR.ID CARID,BOX.ID BOXID,BOX.FXLH,CUS.ID FROM PUB_CUSTOMER CUS LEFT JOIN PUB_CARINFO CAR ON CUS.ID=CAR.FCUSTOMER LEFT JOIN PUB_BOX BOX ON BOX.FCLID=CAR.ID WHERE CAR.FDEFAULT=1 AND CUS.ID='"+cusId+"' ";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				info.setId(rs.getLong("CARID"));
				info.setBoxId(rs.getLong("BOXID"));
				info.setFxlh(rs.getString("FXLH"));
			}
//			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return info;
	}

	@Override
	public PubCarinfo getDefaultCarId(Long userId) {
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="";
		PubCarinfo info = new PubCarinfo();
		try {
			sql="select car.id carId,box.id boxid,box.FXLH boxFXLH,"
			  + "car.FINSEXPIRE,car.FGUAEXPIRE,car.FYEARINSEXPIRE,"
			  + "car.MAINTIAN_TIME,car.MAINTAIN_MILEAGE,car.fline carFline,"
			  + "model.fname modelname,car.FCARNUM carFCARNUM,"
			  + "nvl(line.FNAME,'未绑定车辆') as lineName"
			  + " from pub_carinfo car"
			  + " left join PUB_BOX box"
			  + " on car.ID=box.fclid"
			  + " left join PUB_VEHICLEMODEL model"
			  + " on car.FVEHICLEMODEL=model.id"
			  + " left join PUB_LINE line"
			  + " on car.fline = line.ID"
			  + " where car.FDEFAULT = 1 and car.FCUSTOMER ="+userId;
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				info.setId(rs.getLong("carId"));
				if(rs.getString("boxFXLH")!=null&&rs.getString("boxFXLH")!=""){
					info.setFxlh(rs.getString("boxFXLH"));
				}
				if(rs.getString("carFline")!=null&&rs.getString("carFline")!=""){
					info.setLname(rs.getString("carFline"));
					info.setLid(rs.getLong("carFline"));
				}
				if(rs.getString("carFCARNUM")!=null&&rs.getString("carFCARNUM")!=""){
					info.setFcarnum(rs.getString("carFCARNUM"));
				}
				if(rs.getString("modelname") != null && !rs.getString("modelname").equals("")){
					info.setVname(rs.getString("modelname"));
				}
				info.setFinsexpire(rs.getTimestamp("FINSEXPIRE"));
				info.setFguaexpire(rs.getTimestamp("FGUAEXPIRE"));
				info.setFyearinsexpire(rs.getTimestamp("FYEARINSEXPIRE"));
				info.setMaintianTime(rs.getTimestamp("MAINTIAN_TIME"));
				info.setByangMileage(rs.getFloat("MAINTAIN_MILEAGE"));
				info.setBoxId(rs.getLong("boxid"));
				info.setLineName(rs.getString("lineName"));
			}
//			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return info;
	}

	@Override
	public PubCarinfo getCarBoxInfo(Long fclid) {
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		PubCarinfo pubCarinfo = new PubCarinfo();
		PubVehiclemodel pubVehiclemodel = new PubVehiclemodel();
		try {
			String sql="select * from pub_carinfo where id = "+fclid;
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				pubCarinfo.setFcarcode(rs.getString("FCARCODE"));
				pubCarinfo.setFcarnum(rs.getString("FCARNUM"));
				pubCarinfo.setLid(rs.getLong("FLINE"));
				pubVehiclemodel.setId(rs.getLong("FVEHICLEMODEL"));
				pubCarinfo.setPubVehiclemodel(pubVehiclemodel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return pubCarinfo;
	}

	@Override
	public Long getOdbLine(Long lineId) {
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Long oId = 0L;
		try {
			String sql="select OBDLINEID from PUB_ODBLINEREL where LINEID="+lineId;
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				oId = rs.getLong("OBDLINEID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		/*finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}*/
		return oId;
	}

	@Override
	public Map<String, Object> getOdbCX(Long id) {
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String sql="select CARTYPE,DETAILID from PUB_ODBVEHICLE where id="+id;
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			String carType = "";
			String detailId = "";
			while(rs.next()){
				carType = rs.getString("CARTYPE");
				detailId = rs.getString("DETAILID");
			}
			if(carType!=""&&detailId!=""&&detailId!=null&&carType!=null){
				map.put("carType", carType);
				map.put("detailId", detailId);
			}else{
				map.put("carType", "");
				map.put("detailId", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return map;
	}

	@Override
	public Map<String, Object> getCarJHInfo(Long fclid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String sql="select FCARCODE,FVEHICLEMODEL from PUB_CARINFO where id="+fclid;
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			String fcarCode = "";
			Long vid = 0L;
			String fName = "";
			while(rs.next()){
				fcarCode = rs.getString("FCARCODE");
				vid = rs.getLong("FVEHICLEMODEL");
			}
//			System.out.println(fcarCode);
			String sql1="select FNAME from PUB_VEHICLEMODEL where id="+vid;
			ps=conn.prepareStatement(sql1);
			rs=ps.executeQuery();
			while(rs.next()){
				fName = rs.getString("FNAME");
			}
//			System.out.println(fName);
			map.put("fcarCode", fcarCode);
			map.put("fName", fName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return map;
	}

	@Override
	public boolean updateCarCode(Long fclid,String fCarCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean success = false;
		try {
			String sql="update PUB_CARINFO set FCARCODE='"+fCarCode+"' where id = "+fclid;
//			System.out.println(sql);
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return success;
	}

	@Override
	public PubLine getPubLine(long lineId) {
		PubLine pubLine = new PubLine();
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String urlPath=UrlUtil.getInstance().getImgurl();
			String sql= "select ID,FBRAND,MAILBOX_CAPACITY,FNUMBER,FNAME,S_PICTURE,S_CONTENT from PUB_LINE where id="+lineId;
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				pubLine.setId(rs.getLong("Id"));
				pubLine.setFname(rs.getString("FNAME"));
				pubLine.setFnumber(rs.getString("FNUMBER"));
				if(null!=rs.getString("MAILBOX_CAPACITY")&&!"".equals(rs.getString("MAILBOX_CAPACITY"))){
					pubLine.setMailboxCapacity(rs.getString("MAILBOX_CAPACITY"));
				}
				String pictureStr = rs.getString("S_PICTURE");
				if(pictureStr!=null&&pictureStr!=""){
					pubLine.setsPicture(urlPath+rs.getString("S_PICTURE"));
				}
				pubLine.setsContent(rs.getString("S_CONTENT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return pubLine;
	}
	/**
	 * 根据车辆id查询车辆的信息
	 * @author sududa
	 * @date 2016年7月13日
	 * @param carId
	 * @return
	 */
	@Override
	public Map<String, Object> findMyCarByCarId(String carId) {
		String sql = "select car.ID,car.FCARNUM as carNum,car.FCARCODE as carCode,"
				   + "car.FENGINENUM,"
				   + "(to_char(car.FINSEXPIRE,'yyyy-mm-dd HH24:mi:ss')) as FINSEXPIRE,"
				   + "(to_char(car.FGUAEXPIRE,'yyyy-mm-dd HH24:mi:ss')) as FGUAEXPIRE,"
				   + "(to_char(car.FYEARINSEXPIRE,'yyyy-mm-dd HH24:mi:ss')) as FYEARINSEXPIRE,"
				   + "car.MAINTAIN_MILEAGE as matntainMileage,"
				   + "(to_char(car.MAINTIAN_TIME,'yyyy-mm-dd HH24:mi:ss')) as maintainTime,"
				   + "m.FNAME as modelName,"
				   + "bU.REALNAME,bU.USERKEY,"
				   + "u.MOBILEPHONE,u.OFFICEPHONE,"
				   + "comp.S_NAME as compName,comp.S_TEL as compTel"
				   + " from PUB_CARINFO car"
				   + " left join PUB_VEHICLEMODEL m"
				   + " on car.FVEHICLEMODEL = m.ID"
				   + " left join T_S_BASE_USER bU"
				   + " on car.USER_ID = bU.ID"
				   + " left join T_S_USER u"
				   + " on car.USER_ID = u.ID"
				   + " left join PUB_INSCOMPANY comp"
				   + " on car.FINSCOMPANY = comp.ID"
				   + " where car.ID = ?";
		return findOneForJdbc(sql, carId);
	}

	/**
	 * 根据车辆id获取该车辆的车型名称
	 *@author sududa
	 *@date 2016年10月8日
	 * @param carinfoId
	 * @return
	 */
	@Override
	public Map<String, Object> findModelById(Serializable carinfoId) {
		String sql = "select model.ID,model.FNAME as modelName"
				   + " from PUB_VEHICLEMODEL model"
				   + " where model.ID = "
				   + " (select car.FVEHICLEMODEL"
				   + " from PUB_CARINFO car "
				   + " where car.ID = ?)";
		return findOneForJdbc(sql, carinfoId);
	}

	/**
	 * 查询附近的车列表
	 * @author sududa
	 * @date 2016年10月28日
	 */
	@Override
	public List<Map<String, Object>> doList(Integer page, Integer rows, String defCarId) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "select box.ID as boxId,"
				   + "to_char(box.FJHTIME,'yyyy-mm-dd HH24:mi:ss') as FJHTIME,"
				   + "car.ID as carId,"
				   + "line.FNAME as lineName,"
				   + "cus.ID as cusId,nvl(cus.FNICK,'匿名') as FNICK,"
				   + "nvl(cus.CUS_INTEREST,'未设置兴趣标签') as cusInterest,"
				   + "?||cus.FPHOTO as FPHOTO"
				   + " from PUB_BOX box"
				   + " left join PUB_CARINFO car"
				   + " on box.FCLID = car.ID and box.FJHYF = ?"
				   + " and car.FDEFAULT = ?"
				   + " left join PUB_LINE line"
				   + " on car.FLINE = line.ID"
				   + " left join PUB_CUSTOMER cus"
				   + " on car.FCUSTOMER = cus.ID"
				   + " where car.ID != ?"
				   + " order by box.ID";
		return findForJdbcPage(sql, page, rows, imgUrl, 1, 1, defCarId);
	}
}
