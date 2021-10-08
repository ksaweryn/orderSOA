package commerce.persistence.dao.customer;

import javax.ejb.Stateless;

import commerce.persistence.dao.GenericDao;
import commerce.persistence.entities.Customer;

@Stateless
public class CustomerDao extends GenericDao<Customer> {

	public Customer createCustomer(Customer customer) {
		return create(customer);
	}

}
