package PericosCorp.Framework.Data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import PericosCorp.Framework.Core.ConfigurationHelper;
import PericosCorp.Framework.Core.Services.Implementation.LoggerService;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
  
    private static SessionFactory buildSessionFactory() {
    	LoggerService ls = new LoggerService();
    	Configuration configuration = new Configuration();
        try {
            // Create the SessionFactory from hibernate.cfg.xml            
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ls.LogInfo("Hibernate Session Created",GetLogsPath());
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Exception ex) {
            ls.LogSever(ex, configuration.getProperty("LogsPath"));
        	//ls.LogSever(ex, ConfigurationHelper.GetLogPaths());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static String GetLogsPath()
    {
    	Configuration configuration = new Configuration();
    	configuration.configure("hibernate.cfg.xml");
    	return configuration.getProperty("LogsPath");
    }
}
