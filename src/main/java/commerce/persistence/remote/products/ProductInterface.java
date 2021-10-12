package commerce.persistence.remote.products;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

import javax.ejb.Remote;

import commerce.persistence.entities.Product;

@Remote
public interface ProductInterface {

	public Collection<Product> getProducts();

	public Product getProduct(Long id);

	public Product createProduct(String description, BigDecimal price, Double weigth)
			throws SQLIntegrityConstraintViolationException, SQLException;

	public Product updateProduct(Long id, String description, BigDecimal price, Double weigth) throws SQLException;

	public void deleteProduct(Long id);

}