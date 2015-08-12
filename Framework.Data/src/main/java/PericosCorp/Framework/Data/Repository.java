package PericosCorp.Framework.Data;

import java.util.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Repository<T> implements IRepository<T> {
	
	protected Session sesion;
    protected Transaction tx;    
    
    protected void beginOperation() throws HibernateException
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    protected void manageException(Exception he) throws HibernateException
    {
        tx.rollback();
        sesion.close();        
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
    
    protected void finishOperation()
    {
        tx.commit();        
    }
    
    @SuppressWarnings("unchecked")
	private Class<T> getGenericClass() throws ClassNotFoundException{
        Class<T> inferedClass = null;
        if(inferedClass == null){
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
        try {
            return (T) sesion.get(getGenericClass(), id);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(RepositoryBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            sesion.close();
        }
        return null;
	}

	public void SaveUpdate(T entity) {
		// TODO Auto-generated method stub
		
	}

	public void Delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<T> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
