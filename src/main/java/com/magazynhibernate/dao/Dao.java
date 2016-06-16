/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.dao;

import com.magazynhibernate.utils.JpaUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author damian
 * @param <T>
 */
public class Dao<T> {
	
	protected final Class<T> clazz;

	public Dao(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public T insert(T t) {
		try (Session session = JpaUtils.getSessionFactory().openSession()) {
			Transaction tr = session.beginTransaction();
			session.save(t);
			tr.commit();
			return t;
		}
	}

	public void update(T t) {
		try (Session session = JpaUtils.getSessionFactory().openSession()) {
			Transaction tr = session.beginTransaction();
			session.update(t);
			tr.commit();
		}
	}

	public void delete(T t) {
		try (Session session = JpaUtils.getSessionFactory().openSession()) {
			Transaction tr = session.beginTransaction();
			session.delete(t);
			tr.commit();
		}
	}

	public T findOne(int id) {
		try (Session session = JpaUtils.getSessionFactory().openSession()) {
			T t = (T) session.createCriteria(clazz).add(Restrictions.idEq(id)).uniqueResult();
			return t;
		}
	}

	public List<T> findAll() {
		try (Session session = JpaUtils.getSessionFactory().openSession()) {
			List<T> list = session.createCriteria(clazz).list();
			return list;
		}
	}
	
}
