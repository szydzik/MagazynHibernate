/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.dao;

import com.magazynhibernate.data.Odpad;


/**
 * @author szydzik
 */
public class OdpadDao extends Dao<Odpad> {

	private static OdpadDao instance;

	private OdpadDao() {
		super(Odpad.class);
	}

	public synchronized static OdpadDao getInstance() {
		if (instance == null) {
			instance = new OdpadDao();
		}
		return instance;
	}
}
