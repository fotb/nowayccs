package com.ccs.icd.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class DefaultDAOSupport extends HibernateDaoSupport {
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(final SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
