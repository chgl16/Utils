package io.bbc13.util;

/**
 * 
 * @Lin
 * @���Ϊ��ʯ����0.000600-0.001800��6λС�����Ľ��
 *
 */

public class GetRandomOreAmount {
	public static double getAmount() {
		// �������0.000600-0.001800 ��6λС����
		double amount = (double) Math.round(Math.random() * 1200 + 600) / 1000000;
		return amount;
	}

}
