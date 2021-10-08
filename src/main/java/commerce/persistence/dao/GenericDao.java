package commerce.persistence.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDao<T> {
	@PersistenceContext(unitName = "commercePU")
	public EntityManager em;

	public Class<T> classEntity;

	@SuppressWarnings("unchecked")
	private void setClassEntity() {
		this.classEntity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public T create(T entity) {
		em.persist(entity);
		return entity;
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public void delete(T entity) {
		em.remove(em.merge(entity));
	}

	public T findById(Object id) {
		if (this.classEntity == null) {
			setClassEntity();
		}
		return em.find(this.classEntity, id);
	}

	public List<T> findAll() {
		if (this.classEntity == null) {
			setClassEntity();
		}

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(this.classEntity);
		query.select(query.from(this.classEntity));
		return em.createQuery(query).getResultList();
	}

}