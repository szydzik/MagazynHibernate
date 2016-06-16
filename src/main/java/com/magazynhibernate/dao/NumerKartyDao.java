/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.dao;

import com.magazynhibernate.data.NumerKarty;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;


/**
 *
 * @author damian
 */
public class NumerKartyDao extends Dao<NumerKarty> {

	private static NumerKartyDao instance;

	private NumerKartyDao() {
		super(NumerKarty.class);
	}

	public synchronized static NumerKartyDao getInstance() {
		if (instance == null) {
			instance = new NumerKartyDao();
		}
		return instance;
	}

//	public Optional<Magazynp> findByLoginAndPassword(String login, String password) {
//		try (Session session = JpaUtils.getSessionFactory().openSession()) {
//			Magazynp m = (Magazynp) session
//					.createQuery("from Employee e where e.login=:login and e.password=:p")
//					.setString("login", login)
//					.setString("p", password)
//					.uniqueResult();
//			return Optional.ofNullable(m);
//		}
//	}
//	
//	public List<Magazynp> findAllOrderByPositionName() {
//		try (Session session = JpaUtils.getSessionFactory().openSession()) {
//			return  session
//					.createQuery("from Employee e order by e.position.name")
//					.list();
//		}
//	}

}
