package commerce.persistence.remote.orders;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import commerce.persistence.entities.Order;
import commerce.rest.vo.AddressVO;
import commerce.rest.vo.OrderVO;

@Remote
public interface OrderInterface {

	public Order createOrder(OrderVO order, AddressVO address, List<Long> productsId, Long customerId)
			throws SQLException;

	public void deleteOrder(Long id);

	public BigDecimal getOrdersTotalValue(Long id);

}
