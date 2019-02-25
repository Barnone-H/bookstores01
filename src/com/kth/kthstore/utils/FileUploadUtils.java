package com.kth.kthstore.utils;

import java.util.UUID;

public class FileUploadUtils {
	/**
	 * ��ȡ��ʵ�ļ���
	 * 
	 * @param fileName
	 * @return
	 */
	public static String subFileName(String fileName) {
		// �������һ�� \����λ��
		int index = fileName.lastIndexOf("\\");
		if (index == -1) {
			return fileName;
		}
		return fileName.substring(index + 1);
	}

	// ������UUID�ļ���
	public static String generateRandonFileName(String fileName) {
		// �����չ��
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			String ext = fileName.substring(index);
			return UUID.randomUUID().toString() + ext;
		}
		return UUID.randomUUID().toString();
	}

	// ���hashcode���ɶ���Ŀ¼
	public static String generateRandomDir(String uuidFileName) {
		int hashCode = uuidFileName.hashCode();
		// һ��Ŀ¼
		int d1 = hashCode & 0xf;
		// ����Ŀ¼
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
}

