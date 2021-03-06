/*******************************************************************************
 * Repository
 * Created on 03-11-2015
 * Copyright(c) 2015 PericosCorp Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of PericosCorp Company.
 *******************************************************************************/

package PericosCorp.Framework.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import PericosCorp.Framework.Core.Services.Interfaces.ILoggerService;

/**
*
* @author Arturo E. Salinas
*/

public class Repository<T> implements IRepository<T> {	
	protected Session session;
    protected Transaction tx;    
    protected ILoggerService loggerService;
    
    public void setLoggerService()
    {
    	if(loggerService==null)
    	{
    		@SuppressWarnings("resource")
			ApplicationContext ctx = new ClassPathXmlApplicationContext("DataContext.xml");
    		loggerService=(ILoggerService) ctx.getBean("ILoggerServiceDataTier");
    	}
    }
    
    
    public Repository()
    {
    	if(loggerService==null)
    		setLoggerService();
    }
    protected void beginOperation() throws HibernateException
    {
    	try
    	{    		
    		session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();	
    	}
    	catch(Exception ex)
    	{
    		loggerService.LogSever(ex);
    	}        
    }
    
    protected void manageException(Exception he) throws HibernateException
    {
    	session.flush();
        tx.rollback();
        session.close();
        loggerService.LogSever(he);
        throw new HibernateException("Error on data tier", he);
    }
    
    protected void closeSession()
    {
    	if(session!=null && session.isOpen())
    	{    		
    		session.flush();
    		tx.rollback();
            session.clear();
    		session.close();    		
    	}
    }
    
    protected void finishOperation()
    {
    	try
    	{
            session.flush();
    		tx.commit();
    		session.clear();
    		session.close();
    	}
    	catch(Exception ex)
    	{
    		manageException(ex);
    	}
        
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

    /**
	 * {@inheritDoc}
	 */
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
            finishOperation();
        }
        return null;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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
	            finishOperation();
	     }
	     return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
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
