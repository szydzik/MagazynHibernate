/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.dao;

import com.magazynhibernate.data.Magazynp;


/**
 *
 * @author szydzik
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
}
