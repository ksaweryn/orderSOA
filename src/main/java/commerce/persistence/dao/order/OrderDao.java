package commerce.persistence.dao.order;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import commerce.persistence.dao.GenericDao;
import commerce.persistence.entities.Order;
import commerce.persistence.entities.Product;

@Stateless
public class OrderDao extends GenericDao<Order> {
	public Order createOrder(Order order) throws SQLException {
		if (order.getProducts().isEmpty()) {
			throw new PersistenceException("Order must include a product");
		}
		return create(order);
	}

	public BigDecimal getOrdersTotalValue(Long id) {
		Order order = this.findById(id);
		return order.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::subtract);
	}
}
