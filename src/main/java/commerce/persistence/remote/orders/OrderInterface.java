package commerce.persistence.remote.orders;

import javax.ejb.Remote;

import commerce.persistence.entities.Order;

@Remote
public interface OrderInterface {

	public Order createOrder(String name);

	public void deleteOrder(Long id);

}
