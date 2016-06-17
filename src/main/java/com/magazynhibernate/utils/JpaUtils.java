/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.utils;

import java.util.Properties;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import com.magazynhibernate.data.Magazynp;
import com.magazynhibernate.data.NumerKarty;
import com.magazynhibernate.data.Odpad;

/**
 *
 * @author damian
 */
@UtilityClass
public class JpaUtils {
	
	public static SessionFactory getSessionFactory() {

//		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "error");
		System.setProperty("org.jboss.logging.provider", "slf4j");

		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/magazynp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		properties.setProperty("hibernate.connection.username", "root");
		properties.setProperty("hibernate.connection.password", "password");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
//		properties.setProperty("hibernate.connection.pool_size", "1");

		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.use_sql_comments", "true");
                properties.setProperty("hibernate.jdbc.batch_size", "20");
//		properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
		

		Configuration configuration = new Configuration().addProperties(properties);
		
		configuration.addAnnotatedClass(Magazynp.class);
		configuration.addAnnotatedClass(NumerKarty.class);
                configuration.addAnnotatedClass(Odpad.class);
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
	
}
