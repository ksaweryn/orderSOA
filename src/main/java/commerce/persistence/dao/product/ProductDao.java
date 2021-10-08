package commerce.persistence.dao.product;

import javax.ejb.Stateless;

import commerce.persistence.dao.GenericDao;
import commerce.persistence.entities.Product;

@Stateless
public class ProductDao extends GenericDao<Product> {

	public Product createProduct(Product product) {
		return create(product);
	}
}
