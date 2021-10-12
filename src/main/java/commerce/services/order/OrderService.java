package commerce.services.order;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import commerce.persistence.dao.address.AddressDao;
import commerce.persistence.dao.customer.CustomerDao;
import commerce.persistence.dao.order.OrderDao;
import commerce.persistence.dao.product.ProductDao;
import commerce.persistence.entities.Address;
import commerce.persistence.entities.Customer;
import commerce.persistence.entities.Order;
import commerce.persistence.entities.Product;
import commerce.persistence.remote.orders.OrderInterface;
import commerce.rest.vo.AddressVO;
import commerce.rest.vo.OrderVO;

@Stateless
public class OrderService implements OrderInterface {

	@Inject
	private OrderDao orderDao;
	@Inject
	private ProductDao productDao;
	@Inject
	private CustomerDao customerDao;
	@Inject
	private AddressDao addressDao;

	public Order createOrder(OrderVO newOrder, AddressVO shippingAddress, List<Long> productsId, Long customerId)
			throws SQLException, EntityNotFoundException {
		List<Product> products = productsId.stream().map(productId -> {
			Product product = productDao.findById(productId);
			if (!Objects.isNull(product)) {
				return product;
			}
			return product;
		}).collect(Collectors.toList());

		Customer customer = customerDao.findById(customerId);
		if (Objects.isNull(customer)) {
			throw new EntityNotFoundException("There is no customer with id " + customerId);
		}

		Address address = new Address(
				shippingAddress.getStreet(),
				shippingAddress.getNumber(),
				shippingAddress.getCity(),
				shippingAddress.getState(),
				shippingAddress.getZipCode(),
				shippingAddress.getCountry());
		address = addressDao.create(address);

		BigDecimal orderTotalPrice = calculateTotalValue(products);
		Order order = new Order(newOrder.getPaymentType(), orderTotalPrice, customer, address, products);

		return orderDao.create(order);
	}

	public void deleteOrder(Long id) {

	}

	public BigDecimal getOrdersTotalValue(Long id) {
		Order order = orderDao.findById(id);
		return calculateTotalValue(order.getProducts());
	}

	private BigDecimal calculateTotalValue(List<Product> products) {
		return products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
