package commerce.rest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import commerce.persistence.entities.Customer;
import commerce.persistence.remote.customers.CustomerInterface;
import commerce.rest.presentation.customer.CustomerRestService;
import commerce.rest.vo.CustomerVO;

@ExtendWith(MockitoExtension.class)
class CustomerRestServiceTest {

	private Customer customer;

	@InjectMocks
	private CustomerRestService customerRestService;

	@Mock
	private CustomerInterface customerInterface;

	@Test
	@Disabled
	public void createCustomer() throws ConstraintViolationException, PersistenceException, SQLException {
		MockitoAnnotations.openMocks(this);
		customer = new Customer();
		customer.setId(1L);
		customer.setName("any name");
		customer.setPhone("any phone");
		customer.setEmail("any email");
		customerInterface = Mockito.mock(CustomerInterface.class);
		when(customerInterface.createCustomer(anyString(), anyString(), anyString())).thenReturn(customer);

		Response serviceResponse = customerRestService
				.createCustomer(new CustomerVO("any name", "any phone", "any email"));
		System.out.println(serviceResponse);
		assertNotNull(serviceResponse);
	}

}
