/*******************************************************************************
 * 
 * LoggerService
 * Created on 03-11-2015
 * Copyright(c) 2015 PericosCorp Company, Inc.  All Rights Reserved.
 * This software is the proprietary information of PericosCorp Company.
 * 
 *******************************************************************************/
package PericosCorp.Framework.Core.Services.Implementation;

/**
*
* @author Arturo E. Salinas
*/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import PericosCorp.Framework.Core.Services.Interfaces.ILoggerService;


public class LoggerService implements ILoggerService {
	Logger logger = Logger.getLogger("Logs");
	FileHandler fh;
	private String path;
	
	/**
	 * {@inheritDoc}
	 */
	public void LogSever(Exception ex) {
		try {
			if(!new File(path).exists())
			{
				new File("path").mkdir();
			}			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			fh = new FileHandler(String.format("%sLog-%s.log", path,
					dateFormat.format(Calendar.getInstance().getTime())), true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (IOException ex1) {
			System.out.println(ex1.getMessage());
		} catch (SecurityException ex1) {
			System.out.println(ex1.getMessage());
		}
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		logger.severe(errors.toString());		
		fh.flush();
		fh.close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void LogInfo(String info) {
		try {
			if(!new File(path).exists())
			{
				new File("path").mkdir();
			}
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			fh = new FileHandler(String.format("%sLog-%s.log", path,
					dateFormat.format(Calendar.getInstance().getTime())), true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (IOException ex1) {
			System.out.println(ex1.getMessage());
		} catch (SecurityException ex1) {
			System.out.println(ex1.getMessage());
		}
		logger.info(info);
		fh.flush();
		fh.close();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
