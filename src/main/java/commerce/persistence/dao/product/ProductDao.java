package commerce.persistence.dao.product;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import commerce.persistence.dao.GenericDao;
import commerce.persistence.entities.Product;

@Stateless
public class ProductDao extends GenericDao<Product> {

	public Product createProduct(Product product) throws SQLException {
		return create(product);
	}

	public Integer deleteProduct(Long id)
			throws IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Product> deleteCriteria = cb.createCriteriaDelete(Product.class);
		Root<Product> root = deleteCriteria.from(Product.class);
		deleteCriteria.where(
				cb.and(
						cb.equal(root.get("id"), id),
						cb.isNotEmpty(root.get("products"))
				)
		);

		return em.createQuery(deleteCriteria).executeUpdate();

	}
}
