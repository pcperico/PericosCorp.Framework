package PericosCorp.Framework.Data;

import java.util.List;

public interface IRepository<T> {
	public T Get(int id);
	public void SaveUpdate(T entity);
	public void Delete(int id);
	public List<T> GetAll();	
}
