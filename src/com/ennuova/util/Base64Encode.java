package com.ennuova.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Encode {

	public static String getBase64Code(String imagePath) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imagePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();

		return encoder.encode(data);
	}

	// 解密
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}