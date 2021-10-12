package commerce.persistence.remote.orders;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Remote;

import commerce.persistence.entities.Order;

@Remote
public interface OrderInterface {

	public Order createOrder(String name) throws SQLException;

	public void deleteOrder(Long id);

	public BigDecimal getOrdersTotalValue(Long id);

}
