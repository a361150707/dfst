package com.ennuova.dao.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubOdbvehicleDao;
import com.ennuova.entity.PubOdbvehicle;
import com.ennuova.obd.model.JSONMsg;
import com.ennuova.obd.tools.CommonUtils;

/**
 * ODB车型DAOIMPL
 * @author dofast-pc
 *
 */
@Repository("pubOdbvehicleDao")
public class PubOdbvehicleDaoImpl extends DaoSupportImpl<PubOdbvehicle> implements PubOdbvehicleDao{

	@Override
	public void bachSave(String line) {
		
		JSONObject jsonObject = JSONObject.fromObject(line);
		JSONMsg jsonMsg = new JSONMsg();
		// 对JSON数据进行解析
		jsonMsg.decode(jsonObject);
		JSONArray array1 = jsonMsg.getJsonArray();
		if (array1 != null) {
			for (int i = 0; i < array1.size(); i++) {

				JSONObject jsonobject = array1.getJSONObject(i);

				// ODB车型
				PubOdbvehicle model = new PubOdbvehicle();
				if (jsonobject.has("carType")
						&& !CommonUtils.isEmpty(jsonobject
								.getString("carType")))
					model.setCartype(jsonobject.getString("carType"));
				if (jsonobject.has("id")
						&& !CommonUtils.isEmpty(jsonobject
								.getString("id")))
					model.setVehiclemodelid(jsonobject.getString("id"));
				if (jsonobject.has("carModel")
						&& !CommonUtils.isEmpty(jsonobject
								.getString("carModel")))
					model.setCarmodel(jsonobject.getString("carModel"));
				if (jsonobject.has("diagCarModel")
						&& !CommonUtils.isEmpty(jsonobject
								.getString("diagCarModel")))
					model.setDiagcarmodel(jsonobject
							.getString("diagCarModel"));
				if (jsonobject.has("detailName")
						&& !CommonUtils.isEmpty(jsonobject
								.getString("detailName")))
					model.setDetailname(jsonobject
							.getString("detailName"));
				if (jsonobject.has("lanId")
						&& !CommonUtils.isEmpty(jsonobject
								.getString("lanId")))
					model.setLanid(jsonobject.getString("lanId"));
				if (jsonobject.has("detailId")
						&& !CommonUtils.isEmpty(jsonobject
								.getString("detailId")))
					model.setDetailid(jsonobject.getString("detailId"));

				System.out.println(JSONObject.fromObject(model)
						.toString());

				// 保存数据库
				try {
					save(model);
				} catch (Exception e) {
					System.out.println("导入失败->"+e.getMessage());
				}
		
			}
		}
		
	}

}
