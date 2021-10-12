package commerce.services.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import commerce.persistence.dao.order.OrderDao;
import commerce.persistence.entities.Order;
import commerce.persistence.entities.Product;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;

	@Mock
	private OrderDao orderDao;

	private static Order expectedOrder;
	private static List<Product> expectedProducts;

	@BeforeAll
	public static void setUp() {
		expectedOrder = new Order();
		expectedProducts = new ArrayList<Product>();
		Product product = new Product("any description 1", BigDecimal.ONE, 2.0d);
		expectedProducts.add(product);
		product = new Product("any description 2", BigDecimal.TEN, 0.12d);
		expectedProducts.add(product);
		expectedOrder.setProducts(expectedProducts);

	}

	@Test
	void getOrdersTotalValueTest() {
		when(orderDao.findById(anyLong())).thenReturn(expectedOrder);
		
		BigDecimal totalValue = orderService.getOrdersTotalValue(12L);
		
		assertEquals(totalValue, new BigDecimal(11));
	}

}
