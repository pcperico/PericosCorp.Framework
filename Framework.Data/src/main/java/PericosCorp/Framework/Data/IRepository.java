package PericosCorp.Framework.Data;

import java.util.List;

public interface IRepository<T> {
	public T Get(int id);
	public Integer Save(T entity);
	public void Update(T entity);
	public void SaveUpdate(T entity);
	public void Delete(T entity);
	public List<T> GetAll();	
}
