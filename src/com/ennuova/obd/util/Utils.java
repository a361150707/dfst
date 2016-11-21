package com.ennuova.obd.util;

public class Utils {

	public static int parseShortFromArrayAsBig(byte[] data, int pos) {
		return ((data[pos] & 0xFF) << 8) | (data[pos + 1] & 0xFF);
	}

	public static int parseShortFromArrayAsLittle(byte[] data, int pos) {
		return data[pos] & 0xFF | ((data[pos + 1] & 0xFF) << 8);
	}
	
}
