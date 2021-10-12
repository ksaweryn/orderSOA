package commerce.services.product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import commerce.persistence.dao.product.ProductDao;
import commerce.persistence.entities.Product;
import commerce.persistence.remote.products.ProductInterface;

@Stateless
public class ProductService implements ProductInterface {

	@Inject
	private ProductDao productDao;

	public Collection<Product> getProducts() {
		return productDao.findAll();
	}

	public Product getProduct(Long id) {
		return productDao.findById(id);
	}

	public Product createProduct(String description, BigDecimal price, Double weigth) throws SQLException {
		Product product = new Product(description, price, weigth);
		return productDao.create(product);
	}

	public Product updateProduct(Long id, String description, BigDecimal price, Double weigth) throws SQLException {
		Product product = productDao.findById(id);
		product.setDescription(description);
		product.setPrice(price);
		product.setWeigth(weigth);
		return productDao.create(product);

	}

	public void deleteProduct(Long id) {

	}

}
