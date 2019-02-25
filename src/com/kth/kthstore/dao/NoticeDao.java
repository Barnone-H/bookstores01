package com.kth.kthstore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kth.kthstore.domain.Notice;
import com.kth.kthstore.utils.DataSourceUtils;

public class NoticeDao {
	//��̨ϵͳ����ѯ���еĹ���
	public List<Notice> getAllNotices() throws SQLException {
		String sql = "select * from notice order by n_time desc limit 0,10";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Notice>(Notice.class));
	}

	//��̨ϵͳ����ӹ���
	public void addNotice(Notice n) throws SQLException {
		String sql = "insert into notice(title,details,n_time) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, n.getTitle(),n.getDetails(),n.getN_time());
	}

	//��̨ϵͳ������id���ҹ���
	public Notice findNoticeById(String n_id) throws SQLException {
		String sql = "select * from notice where n_id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Notice>(Notice.class),n_id);
	}

	//��̨ϵͳ������id�޸ĵ�������
	public void updateNotice(Notice n) throws SQLException {
		String sql = "update notice set title=?,details=?,n_time=? where n_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, n.getTitle(),n.getDetails(),n.getN_time(),n.getN_id());
	}

	//��̨ϵͳ������idɾ������
	public void deleteNotice(String n_id) throws SQLException {
		String sql = "delete from notice where n_id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, n_id);
	}

	//ǰ̨ϵͳ����ѯ������ӻ��޸ĵ�һ������
	public Notice getRecentNotice() throws SQLException {
		String sql = "select * from notice order by n_time desc limit 0,1";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Notice>(Notice.class));
	}
}
