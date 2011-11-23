package com.ccs.sms.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

public final class SpringUtil {
	private static final Logger LOG = Logger.getLogger(SpringUtil.class);
	private static BeanFactoryLocator locator;
	private static BeanFactoryReference bfr;
	private static BeanFactory factory;

	private SpringUtil(){
	    super();
	}
	/**
	 * Return an instance of the specified bean
	 * @param beanRef The name of the bean to retrieve
	 * @return A bean instance
	 */
	public static synchronized Object getBean(final String beanRef) {
		Object bean = null;
		try{
		if (locator == null){
			locator = ContextSingletonBeanFactoryLocator.getInstance();
		}
		if (bfr == null){
			bfr = locator.useBeanFactory("com.ccs.sms");
		}
		if (factory == null){
			factory = bfr.getFactory();
		}
		bean = factory.getBean(beanRef);

		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}
		LOG.debug("Object returned from SpringUtil ["+beanRef+"]: "+bean);
		return bean;
	}
}
