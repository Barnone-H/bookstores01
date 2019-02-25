package com.kth.kthstore.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kth.kthstore.domain.Order;
import com.kth.kthstore.domain.OrderItem;
import com.kth.kthstore.domain.Product;
import com.kth.kthstore.utils.DataSourceUtils;

public class ProductDao {
	// �����Ʒ
	public void addProduct(Product p) throws SQLException {

		String sql = "insert into products values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, p.getId(), p.getName(), p.getPrice(),
				p.getCategory(), p.getPnum(), p.getImgurl(), p.getDescription());
	}
	// ����������Ʒ
	public List<Product> listAll() throws SQLException {
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}
	// ��ȡ����������
	public int findAllCount(String category) throws SQLException {
		String sql = "select count(*) from products";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (!"all".equals(category)) {
			sql += " where category=?";

			Long count = (Long) runner
					.query(sql, new ScalarHandler(), category);
			return count.intValue();
		} else {
			Long count = (Long) runner.query(sql, new ScalarHandler());

			return count.intValue();
		}
	}
	// ��ȡ��ǰҳ����
	public List<Product> findByPage(int currentPage, int currentCount,
			String category) throws SQLException {
		// Ҫִ�е�sql���
		String sql = null;
		// ����
		Object[] obj = null;
		// ���category��Ϊnull,�����ǰ��������
		if (!"all".equals(category)) {
			sql = "select * from products  where category=? limit ?,?";
			obj = new Object[] { category, (currentPage - 1) * currentCount,
					currentCount, };
		} else {
			sql = "select * from products  limit ?,?";
			obj = new Object[] { (currentPage - 1) * currentCount,
					currentCount, };
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				obj);
	}

	// ����id������Ʒ
	public Product findProductById(String id) throws SQLException {
		String sql = "select * from products where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class), id);
	}

	// ���ɶ���ʱ������Ʒ��������
	public void changeProductNum(Order order) throws SQLException {
		String sql = "update products set pnum=pnum-? where id=?";
		QueryRunner runner = new QueryRunner();
		List<OrderItem> items = order.getOrderItems();
		Object[][] params = new Object[items.size()][2];

		for (int i = 0; i < params.length; i++) {
			params[i][0] = items.get(i).getBuynum();
			params[i][1] = items.get(i).getP().getId();
		}

		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	// ���۰�
	public List<Object[]> salesList(String year, String month)
			throws SQLException {
		String sql = "SELECT products.name,SUM(orderitem.buynum) totalsalnum FROM orders,products,orderItem WHERE orders.id=orderItem.order_id AND products.id=orderItem.product_id AND orders.paystate=1 and year(ordertime)=? and month(ordertime)=? GROUP BY products.name ORDER BY totalsalnum DESC";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), year, month);
	}

	// ��������ѯ
	public List<Product> findProductByManyCondition(String id, String name,
			String category, String minprice, String maxprice)
			throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from products where 1=1 ";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (id != null && id.trim().length() > 0) {
			sql += " and id=?";
			list.add(id);
		}

		if (name != null && name.trim().length() > 0) {
			sql += " and name=?";
			list.add(name);
		}
		if (category != null && category.trim().length() > 0) {
			sql += " and category=?";
			list.add(category);
		}
		if (minprice != null && maxprice != null
				&& minprice.trim().length() > 0 && maxprice.trim().length() > 0) {
			sql += " and price between ? and ?";
			list.add(minprice);
			list.add(maxprice);
		}

		Object[] params = list.toArray();

		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				params);
	}
	// �޸���Ʒ��Ϣ
	public void editProduct(Product p) throws SQLException {
		//1.�������ϲ�����Ʒ��Ϣ��ӵ�������
		List<Object> obj = new ArrayList<Object>();
		obj.add(p.getName());
		obj.add(p.getPrice());
		obj.add(p.getCategory());
		obj.add(p.getPnum());
		obj.add(p.getDescription());
		//2.����sql��䣬��ƴ��sql
		String sql  = "update products " +
				      "set  name=?,price=?,category=?,pnum=?,description=? ";
		//�ж��Ƿ���ͼƬ
		if (p.getImgurl() != null && p.getImgurl().trim().length() > 0) {
			sql += " ,imgurl=?";
			obj.add(p.getImgurl());
		}
		sql += " where id=?";
		obj.add(p.getId());		
		System.out.println(sql);		
		System.out.println(obj);
		//3.����QueryRunner����
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//4.ʹ��QueryRunner�����update()������������
		runner.update(sql, obj.toArray());
	}
	//ɾ������ʱ���޸���Ʒ����
	public void updateProductNum(List<OrderItem> items) throws SQLException {
		
		String sql = "update products set pnum=pnum+? where id=?";
		QueryRunner runner = new QueryRunner();
		
		Object[][] params = new Object[items.size()][2];

		for (int i = 0; i < params.length; i++) {
			params[i][0] = items.get(i).getBuynum();
			params[i][1] = items.get(i).getP().getId();
		}

		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	//ǰ̨����ȡ����������Ʒ
	public List<Object[]> getWeekHotProduct() throws SQLException {
		String sql = "SELECT products.id,products.name, "+
                             " products.imgurl,SUM(orderitem.buynum) totalsalnum "+
                     " FROM orderitem,orders,products "+
                     " WHERE orderitem.order_id = orders.id "+
                             " AND products.id = orderitem.product_id "+
                             " AND orders.paystate=1 "+
                             " AND orders.ordertime > DATE_SUB(NOW(), INTERVAL 7 DAY) "+
                     " GROUP BY products.id,products.name,products.imgurl "+
                     " ORDER BY totalsalnum DESC "+
                     " LIMIT 0,2 ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}

	//ǰ̨���������������������ģ����ѯ��Ӧ��ͼ��
	public List<Product> findBookByName(int currentPage, int currentCount,
			String searchfield) throws SQLException {
		//��������ģ����ѯͼ��
		String sql = "SELECT * FROM products WHERE name LIKE '%"+searchfield+"%' LIMIT ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		//���ڷ�ҳ��ѯ������
//		Object obj = new Object[] { (currentPage - 1) * currentCount, currentCount };
		return runner.query(sql, 
				new BeanListHandler<Product>(Product.class),currentPage-1,currentCount);
	}

	//ǰ̨�����򣬸�������ģ����ѯ����ͼ��������
	public int findBookByNameAllCount(String searchfield) throws SQLException {
		String sql = "SELECT COUNT(*) FROM products WHERE name LIKE '%"+searchfield+"%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//��ѯ��������������������Ϊlong����
		Long count = (Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	//��̨ϵͳ������idɾ����Ʒ��Ϣ
	public void deleteProduct(String id) throws SQLException {
		String sql = "DELETE FROM products WHERE id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
}
