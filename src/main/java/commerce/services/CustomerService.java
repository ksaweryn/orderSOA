package commerce.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import commerce.persistence.dao.customer.CustomerDao;
import commerce.persistence.entities.Customer;
import commerce.persistence.remote.customers.CustomerInterface;

@Stateless
public class CustomerService implements CustomerInterface {

	@Inject
	private CustomerDao customerDao;

	public Customer createCustomer(String name, String phone, String email) {
		Customer customer = new Customer(name, phone, email);
		return customerDao.createCustomer(customer);
	}

	public List<Customer> getCustomers() {
		return customerDao.findAll();
	}

	public Customer getCustomer(Long id) {
		Customer customer = customerDao.findById(id);
		return customer;
	}

	public Customer updateCustomer(Long id, String name, String phone, String email) {
		Customer customer = customerDao.findById(id);
		customer.setName(name);
		customer.setPhone(phone);
		customer.setEmail(email);
		return customerDao.create(customer);
	}

	public void deleteCustomer(Long id) {
		Customer customer = customerDao.findById(id);
		customerDao.delete(customer);
	}

}
