package commerce.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import commerce.persistence.dao.customer.CustomerDao;
import commerce.persistence.entities.Customer;
import commerce.services.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;
	@Mock
	private CustomerDao customerDao;

	@Test
	void createCustomerTest() throws SQLException {
		Customer customerExpected = new Customer("any name", "any phone number", "any email");
		when(customerDao.createCustomer(any(Customer.class))).thenReturn(customerExpected);

		Customer customerCreated = customerService.createCustomer("any name", "any phone number", "any email");

		assertEquals(customerExpected, customerCreated);
	}

}
