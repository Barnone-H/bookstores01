package com.kth.kthstore.utils;

import java.util.UUID;
/**
 * ����ע�ἤ����Ĺ�����
 */
public class ActiveCodeUtils {
	public static String createActiveCode() {
		return UUID.randomUUID().toString();
	}
}
