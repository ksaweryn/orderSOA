package commerce.persistence.dao.order;

import javax.ejb.Stateless;

import commerce.persistence.dao.GenericDao;
import commerce.persistence.entities.Order;

@Stateless
public class OrderDao extends GenericDao<Order> {
	public Order createOrder(Order order) {
		return create(order);
	}
}
