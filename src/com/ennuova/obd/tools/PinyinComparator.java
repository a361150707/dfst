package com.ennuova.obd.tools;

import java.util.Comparator;

import com.ennuova.obd.model.CarSeries;

/**
 * 
 * @author ljq
 *
 */
public class PinyinComparator implements Comparator<CarSeries> {

	// 首字母排序
//	public int compare(CarSeries o1, CarSeries o2) {
//		if (o1.getSortKey().equals("@")
//				|| o2.getSortKey().equals("#")) {
//			return -1;
//		} else if (o1.getSortKey().equals("#")
//				|| o2.getSortKey().equals("@")) {
//			return 1;
//		} else {
//			return o1.getSortKey().compareTo(o2.getSortKey());
//		}
//	}
	// modify by zhangwei 全拼音排序 2014-10-29
	public int compare(CarSeries o1, CarSeries o2) {

		return o1.getPinying().compareToIgnoreCase(o2.getPinying());
	}

}
