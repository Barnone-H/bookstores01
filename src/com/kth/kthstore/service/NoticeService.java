package com.kth.kthstore.service;

import java.sql.SQLException;
import java.util.List;

import com.kth.kthstore.dao.NoticeDao;
import com.kth.kthstore.domain.Notice;

public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	//��̨ϵͳ����ѯ���й���
	public List<Notice> getAllNotices() {
		try {
			return dao.getAllNotices();
		} catch (SQLException e) {
			throw new RuntimeException("��ѯ���еĹ���ʧ�ܣ�");
		}
	}
	//��̨ϵͳ����ӹ���
	public void addNotice(Notice notice) {
		try {
			dao.addNotice(notice);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��ӹ���ʧ��!");
		}
	}
	//��̨ϵͳ������id���ҹ���
	public Notice findNoticeById(String n_id) {
		try {
			return dao.findNoticeById(n_id);
		} catch (SQLException e) {
			throw new RuntimeException("����id���ҹ���ʧ�ܣ�");
		}
	}
	
	//��̨ϵͳ������id�޸Ĺ���
	public void updateNotice(Notice bean) {
		try {
			dao.updateNotice(bean);
		} catch (SQLException e) {
//			throw new RuntimeException("����id�޸Ĺ���ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	//��̨ϵͳ������idɾ������
	public void deleteNotice(String n_id) {
		try {
			dao.deleteNotice(n_id);
		} catch (SQLException e) {
			throw new RuntimeException("����idɾ������ʧ�ܣ�");
		}
	}
	
	//ǰ̨ϵͳ����ѯ������ӻ��޸ĵ�һ������
	public Notice getRecentNotice() {
		try {
			return dao.getRecentNotice();
		} catch (SQLException e) {
			throw new RuntimeException("��ѯ������ӻ��޸ĵ�һ������ʧ�ܣ�");
		}
	}
}

