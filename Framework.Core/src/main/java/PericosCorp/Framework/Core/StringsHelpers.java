/**
 * StringsHelpers.java
 * Created on 20/11/2015
 * Copyright(c) 2015 PericosCorp Company, Inc.  All Rights Reserved.
     * This software is the proprietary information of PericosCorp Company.
*/
package PericosCorp.Framework.Core;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class StringsHelpers {
	/**
	 *
	 * @author Arturo E. Salinas
	 */
	public static String GetStringofDate(Date date)
	{
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 return sdf.format(date);	
	}
}
