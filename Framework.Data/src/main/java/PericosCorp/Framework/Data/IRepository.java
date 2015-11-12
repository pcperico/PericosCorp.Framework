/*******************************************************************************
 * IRepository
 * Created on 03-11-2015
 * Copyright(c) 2015 PericosCorp Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of PericosCorp Company.
 *******************************************************************************/

package PericosCorp.Framework.Data;

import java.util.List;

/**
*
* @author Arturo E. Salinas
*/

public interface IRepository<T> {
	/**
	 * Method to create LoggerService, need path of xml file that content path of logs.
	 */
	public void setLoggerService();
	
	/**
	 * Method to get an entity from db
	 * @param id id of entity to be get
	 * @return Entity that corresponds to provided id, if not exists, return null
	 */
	public T Get(int id);
	
	/**
	 * Method to persist an entity on db
	 * @param entity entity that will be persisted in db
	 * @return id of entity inserted
	 */
	public Integer Save(T entity);
	
	/**
	 * Method to update and exists entity on db
	 * @param entity entity modified, to be updated on db
	 */
	public void Update(T entity);
	
	/**
	 * Method to update or insert an entity on db
	 * @param entity entity to be inserted or updated on db
	 */
	public void SaveUpdate(T entity);
	
	/**
	 * method to delete an entity from db
	 * @param entity entity to be deleted
	 */
	public void Delete(T entity);
	
	/**
	 * Method to get all entities of T type persisted on db
	 * @return a List of T type contents all rows of table T
	 */
	public List<T> GetAll();	
}
