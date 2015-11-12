/*******************************************************************************
 * HibernateUtil
 * Created on 03-11-2015
 * Copyright(c) 2015 PericosCorp Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of PericosCorp Company.
 *******************************************************************************/
package PericosCorp.Framework.Data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import PericosCorp.Framework.Core.Services.Interfaces.ILoggerService;

/**
*
* @author Arturo E. Salinas
*/

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();		
	private static ILoggerService loggerService;
	
	private static void setLoggerService()
	{
		if(loggerService==null)
		{
			@SuppressWarnings("resource")
			ApplicationContext ctx = new ClassPathXmlApplicationContext("DataContext.xml");
			loggerService= (ILoggerService)ctx.getBean("ILoggerServiceDataTier");
		}
	}
	
    private static SessionFactory buildSessionFactory() {    	
    	setLoggerService();    	
    	Configuration configuration = new Configuration();
        try {
            // Create the SessionFactory from hibernate.cfg.xml            
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            loggerService.LogInfo("Hibernate Session Created");
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Exception ex) {
        	loggerService.LogSever(ex);        	
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }   
   
}
