/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.dao;

import com.magazynhibernate.data.Magazynp;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;


/**
 *
 * @author damian
 */
public class MagazynpDao extends Dao<Magazynp> {

	private static MagazynpDao instance;

	private MagazynpDao() {
		super(Magazynp.class);
	}

	public synchronized static MagazynpDao getInstance() {
		if (instance == null) {
			instance = new MagazynpDao();
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
