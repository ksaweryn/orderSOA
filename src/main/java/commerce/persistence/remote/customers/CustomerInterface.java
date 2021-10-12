package commerce.persistence.remote.customers;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import commerce.persistence.entities.Customer;

@Remote
public interface CustomerInterface {

	public Customer createCustomer(String name, String phone, String email) throws SQLException;

	public List<Customer> getCustomers();

	public Customer getCustomer(Long id);

	public Customer updateCustomer(Long id, String name, String phone, String email) throws SQLException;

	public void deleteCustomer(Long id);
}
