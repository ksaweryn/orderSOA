package commerce.persistence.dao.customer;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import commerce.persistence.dao.GenericDao;
import commerce.persistence.entities.Customer;

@Stateless
@Transactional
public class CustomerDao extends GenericDao<Customer> {

	public Customer createCustomer(Customer customer) throws SQLException {
		return create(customer);
	}

	public Integer deleteCustomer(Long id)
			throws IllegalStateException, TransactionRequiredException, QueryTimeoutException, PersistenceException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Customer> deleteCriteria = cb.createCriteriaDelete(Customer.class);
		Root<Customer> root = deleteCriteria.from(Customer.class);
		deleteCriteria.where(cb.and(cb.equal(root.get("id"), id), cb.isNotEmpty(root.get("orders"))));

		return em.createQuery(deleteCriteria).executeUpdate();
	}

}
