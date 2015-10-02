import java.io.Console;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

import PericosCorp.Framework.Core.ConfigurationHelper;
import PericosCorp.Framework.Core.Services.Implementation.LoggerService;
import PericosCorp.Framework.Data.HibernateUtil;


public class App 
{
    public static void main(String[] args)
    {
    	String path=ConfigurationHelper.GetLogPaths();
    	System.out.println(path);
    	HibernateUtil.getSessionFactory();        
    }
}
