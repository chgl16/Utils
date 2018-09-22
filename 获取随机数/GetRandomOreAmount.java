package io.bbc13.util;

/**
 * 
 * @Lin
 * @随机为矿石生成0.000600-0.001800【6位小数】的金额
 *
 */

public class GetRandomOreAmount {
	public static double getAmount() {
		// 随机返回0.000600-0.001800 【6位小数】
		double amount = (double) Math.round(Math.random() * 1200 + 600) / 1000000;
		return amount;
	}

}
