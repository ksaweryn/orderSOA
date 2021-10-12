package commerce.services.order;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import commerce.persistence.dao.order.OrderDao;
import commerce.persistence.entities.Order;
import commerce.persistence.entities.Product;
import commerce.persistence.remote.orders.OrderInterface;

@Stateless
public class OrderService implements OrderInterface {

	@Inject
	private OrderDao orderDao;

	public Order createOrder(String name) throws SQLException {

		return orderDao.create(new Order());
	}

	public void deleteOrder(Long id) {

	}

	public BigDecimal getOrdersTotalValue(Long id) {
		Order order = orderDao.findById(id);
		return order.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
