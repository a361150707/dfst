package com.ennuova.util;

import java.util.Random;

/** 
* 
*/
public class RandomNumberGenerator {

	/**
	 * 这是典型的随机洗牌算法�? 流程是从备�?数组中�?择一个放入目标数组中，将选取的数组从备�?数组移除（放至最后，并缩小�?择区域） 算法时间复杂度O(n)
	 * 
	 * @return 随机8为不重复数组
	 */
	//产生6位随机数验证�?
	public static String generateNumber() {
		String no = "";
		int num[] = new int[6];
		int c = 0;
		for (int i = 0; i < 6; i++) {
			num[i] = new Random().nextInt(10);
			c = num[i];
			for (int j = 0; j < i; j++) {
				if (num[j] == c) {
					i--;
					break;
				}
			}
		}
		if (num.length > 0) {
			for (int i = 0; i < num.length; i++) {
				no += num[i];
			}
		}
		return no;
	}

	// 产生8位随机数
	public static String generateNumber2() {
		String no = "";
		int num[] = new int[8];
		int c = 0;
		for (int i = 0; i < 8; i++) {
			num[i] = new Random().nextInt(10);
			c = num[i];
			for (int j = 0; j < i; j++) {
				if (num[j] == c) {
					i--;
					break;
				}
			}
		}
		if (num.length > 0) {
			for (int i = 0; i < num.length; i++) {
				no += num[i];
			}
		}
		return no;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(generateNumber());
			System.out.println(generateNumber2());
		}
	}
}