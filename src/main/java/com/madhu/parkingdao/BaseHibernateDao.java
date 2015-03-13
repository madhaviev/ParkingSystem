package com.madhu.parkingdao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author madhavi
 *
 * @param <T>
 * @param <ID>
 */
@SuppressWarnings("unchecked")
public abstract class BaseHibernateDao<T,ID extends Serializable> {

	private Class<T> classObj;
	
	public BaseHibernateDao(Class<T> classtype) {
		this.classObj = classtype;
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Returns the current Session object
	 * @return
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	
	/**
	 * Make the transient object to persistant
	 * @param entity
	 * @return
	 */
	public T makePersistant(T entity) {

		this.getSession().save(entity);
		return entity;
	}
	

	/**
	 * Merge with the object in DB
	 * @param entity
	 * @return
	 */
	public T makeMerge(T entity) {
		return (T) this.getSession().merge(entity);
	}

	
	/**
	 * Make the object from persistant to transient
	 * @param entity
	 */
	public void makeTransient(T entity) {

		this.getSession().delete(entity);
	}
	
	
	/**
	 * Retrieve the object based on Id
	 * @param id
	 * @return
	 */
	public T findById(ID id) {
		return (T) this.getSession().get(this.classObj, id);
	}
	
}
