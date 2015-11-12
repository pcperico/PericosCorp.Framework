/*******************************************************************************
 * ILoggerService
 * Created on 03-11-2015
 * Copyright(c) 2015 PericosCorp Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of PericosCorp Company.
 *******************************************************************************/
package PericosCorp.Framework.Core.Services.Interfaces;

/**
*
* @author Arturo E. Salinas
*/
public interface ILoggerService {
	
	/**
	 * Method to log an exception, logged on file specified on xml file
	 * @param ex Exception that will be logged
	 */
	public void LogSever(Exception ex);
	
	/**
	 * Method to log an info, logged on file specified on xml file
	 * @param info String that will be logged as info
	 */
	public void LogInfo(String info);
}
