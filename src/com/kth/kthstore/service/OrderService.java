package com.kth.kthstore.service;
import java.sql.SQLException;
import java.util.List;

import com.kth.kthstore.dao.OrderDao;
import com.kth.kthstore.dao.OrderItemDao;
import com.kth.kthstore.dao.ProductDao;
import com.kth.kthstore.domain.Order;
import com.kth.kthstore.domain.OrderItem;
import com.kth.kthstore.domain.User;
import com.kth.kthstore.utils.DataSourceUtils;
public class OrderService {
	private OrderItemDao oidao = new OrderItemDao();
	private OrderDao odao = new OrderDao();
	private ProductDao pdao = new ProductDao();
	// 1.��Ӷ���
	public void addOrder(Order order) {
		try {
			// 1.��������
			DataSourceUtils.startTransaction();
			// 2.��ɲ���
			// 2.1��orders�����������
			odao.addProduct(order);
			// 2.2��orderItem�����������
			oidao.addOrderItem(order);
			// 2.3�޸���Ʒ��������.
			pdao.changeProductNum(order);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback(); // ����ع�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				// �رգ��ͷ��Լ��ύ����
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// �����û����Ҷ���
	public List<Order> findOrderByUser(User user) {
		List<Order> orders = null;
		try {
			// ���ҳ�������Ϣ
			orders = odao.findOrderByUser(user);

			// // ���ҳ���������Ϣ.
			// for (Order order : orders) {
			// List<OrderItem> items = oidao.findOrderItemByOrder(order);
			// //���ҵ������еĶ�������Ϣ
			//
			// order.setOrderItems(items);
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	// ����id���Ҷ���
	public Order findOrderById(String id) {
		Order order = null;
		try {
			order = odao.findOrderById(id);
			List<OrderItem> items = oidao.findOrderItemByOrder(order);
			order.setOrderItems(items);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	// �������ж���
	public List<Order> findAllOrder() {
		List<Order> orders = null;
		try {
			// ���ҳ�������Ϣ
			orders = odao.findAllOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	// ֧���ɹ����޸Ķ���״̬
	public void updateState(String id) {
		try {
			odao.updateOrderState(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// ��������ѯ������Ϣ
	public List<Order> findOrderByManyCondition(String id, String receiverName) {
		List<Order> orders = null;
		try {
			orders = odao.findOrderByManyCondition(id, receiverName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	//����idɾ������ ����Աɾ������
	public void delOrderById(String id) {			
		try {
			DataSourceUtils.startTransaction();//��������
			oidao.delOrderItems(id);
			odao.delOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	//��ͨ�û�ɾ������
	public void delOrderByIdWithClient(String id) {
		try {
			DataSourceUtils.startTransaction();//��������
			//�Ӷ������в�����Ʒ��������
			Order order=new Order();
			order.setId(id);
			List<OrderItem> items=oidao.findOrderItemByOrder(order);
			//�޸���Ʒ����			
			pdao.updateProductNum(items);						
			oidao.delOrderItems(id); //ɾ��������
			odao.delOrderById(id); //ɾ������
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
