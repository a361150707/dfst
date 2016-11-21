package com.ennuova.obd.util;

import java.io.UnsupportedEncodingException;

public class DpuString {
	
	int length;
	String string;
	
	
	public DpuString() {
	
	}

	public DpuString(String string) {
		length = string.getBytes().length;
		this.string = string;
	}
	
	public static DpuString fromBigData(byte[] data) {
		DpuString dpuString = new DpuString();
		dpuString.length = Utils.parseShortFromArrayAsBig(data, 0);
		byte[] stringArray = new byte[dpuString.length];
		System.arraycopy(stringArray, 2, stringArray, 0, dpuString.length);	
		try {
			dpuString.string = new String(stringArray, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dpuString;
	}
	
	public static DpuString fromLittleData(byte[] data) {
		DpuString dpuString = new DpuString();
		dpuString.length = Utils.parseShortFromArrayAsLittle(data, 0);
		byte[] stringArray = new byte[dpuString.length];
		System.arraycopy(stringArray, 2, stringArray, 0, dpuString.length);	
		try {
			dpuString.string = new String(stringArray, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dpuString;
	}
	
	public byte[] getBytesToBigArray()
	{

		byte[] strdata = string.getBytes();
		byte[] data = new byte[length + 2];
		data[0] = (byte) ((length >> 8) &0xFF);
		data[1] = (byte) (length & 0xFF);	
		System.arraycopy(strdata, 0, data, 2, length);
		return data;
	}
	
	public byte[] getBytesToLittleArray()
	{

		byte[] strdata = string.getBytes();
		byte[] data = new byte[length + 2];
		data[0] = (byte) (length & 0xFF);	
		data[1] = (byte) ((length >> 8) &0xFF);
		System.arraycopy(strdata, 0, data, 2, length);
		return data;
	}
	
	public String getString()
	{
		return string;
	}
	
	public int getLength() {
		return length;
	}
}
