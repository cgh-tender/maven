package com.dw.common.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BaseDAOHibernate {
	@Autowired
	@Qualifier("dwrest_sessionFactory")
	protected SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession() ;
	}
	
	public Session getNewSession() {
		return this.sessionFactory.openSession();
	}
}
