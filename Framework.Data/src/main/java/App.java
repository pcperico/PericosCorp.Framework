import PericosCorp.Framework.Core.ConfigurationHelper;
import PericosCorp.Framework.Core.Services.Implementation.LoggerService;


public class App 
{
    public static void main( String[] args )
    {
    	LoggerService ls=new LoggerService();
    	try{
    		throw new Exception();
    	}
    	catch(Exception ex)
    	{
    		ls.LogSever(ex, ConfigurationHelper.GetLogPaths());
    	}
        System.out.println( "Hello World! ");
        
    }
}
