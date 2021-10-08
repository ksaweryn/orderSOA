package commerce.persistence.remote;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Remote;

import commerce.persistence.entities.Product;

@Remote
public interface ProductInterface {

	public Collection<Product> getProducts();

	public Product getProduct(Long id);

	public Product createProduct(String description, BigDecimal price, Double weigth);

	public Product updateProduct(Long id, String description, BigDecimal price, Double weigth);

	public void deleteProduct(Long id);

}
