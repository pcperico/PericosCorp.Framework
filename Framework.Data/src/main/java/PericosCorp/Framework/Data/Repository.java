package PericosCorp.Framework.Data;

import java.util.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import PericosCorp.Framework.Core.ConfigurationHelper;
import PericosCorp.Framework.Core.Services.Implementation.LoggerService;



public class Repository<T> implements IRepository<T> {	
	protected Session session;
    protected Transaction tx;    
    LoggerService ls = new LoggerService();
    protected void beginOperation() throws HibernateException
    {
    	try
    	{
    		session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();	
    	}
    	catch(Exception ex)
    	{
    		 ls.LogSever(ex, ConfigurationHelper.GetLogPaths());
    	}        
    }
    
    protected void manageException(Exception he) throws HibernateException
    {
        tx.rollback();
        session.close();  
        ls.LogSever(he, HibernateUtil.GetLogsPath());
        throw new HibernateException("Error on data tier", he);
    }
    
    protected void finishOperation()
    {
        tx.commit();
    }
    
    @SuppressWarnings("unchecked")
	private Class<T> getGenericClass() throws ClassNotFoundException{
        Class<T> inferedClass = null;
        if(inferedClass == null)
        {
            Type mySuperclass = getClass().getGenericSuperclass();
            Type tType = ((ParameterizedType)mySuperclass).getActualTypeArguments()[0];
            String className = tType.toString().split(" ")[1];
            inferedClass = (Class<T>) Class.forName(className);
        }
        return inferedClass;
    }    

	@SuppressWarnings("unchecked")
	public T Get(int id) {			
		beginOperation();
        try 
        {        	
            return (T) session.get(getGenericClass(), id);
        } 
        catch (ClassNotFoundException ex) 
        {
           manageException(ex);
        }
        finally
        {
            session.close();
        }
        return null;
	}

	public void SaveUpdate(T entity) {
		try 
        { 
			beginOperation();	
            session.saveOrUpdate(entity);             
        }
		catch(Exception he) 
        { 
            manageException(he);            
        }
		finally 
        { 
            finishOperation();
        } 		
	}

	public void Delete(T entity) {
		try 
        { 
            beginOperation();
            session.delete(entity);             
        }
		catch(HibernateException he) 
        { 
            manageException(he);
            throw he; 
        }
		finally 
        { 
            finishOperation();
        } 		
	}

	@SuppressWarnings("unchecked")
	public List<T> GetAll() {
		 beginOperation();
	     try 
	     {
	    	 return session.createCriteria(getGenericClass()).list();            
	     }
	     catch (ClassNotFoundException ex) 
	     {
	    	manageException(ex);	    	
	     }
	     finally{
	            session.close();
	     }
	     return null;
	}
	
	public Integer Save(T entity) {
		int id =0;
        try 
        { 
            beginOperation();
            id=(Integer) session.save(entity);             
        }catch(Exception he) 
        {         	
            manageException(he);            
        }finally 
        { 
            finishOperation();
        } 
        return id;
	}
	
	public void Update(T entity) {
		try 
        { 
            beginOperation();
            session.update(entity);             
        }catch(Exception he) 
        { 
            manageException(he);            
        }finally 
        { 
            finishOperation();
        }
	}
}
