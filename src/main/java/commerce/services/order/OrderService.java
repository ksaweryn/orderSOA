package commerce.services.order;

import javax.ejb.Stateless;
import javax.inject.Inject;

import commerce.persistence.dao.order.OrderDao;
import commerce.persistence.entities.Order;
import commerce.persistence.remote.orders.OrderInterface;

@Stateless
public class OrderService implements OrderInterface {

	@Inject
	private OrderDao orderDao;

	public Order createOrder(String name) {
		
		return orderDao.create(new Order());
	}

	public void deleteOrder(Long id) {

	}

}
