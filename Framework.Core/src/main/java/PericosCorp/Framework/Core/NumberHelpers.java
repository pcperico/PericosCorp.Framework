/**
 * NumberHelpers.java
 * Created on 4/12/2015
 * Copyright(c) 2015 PericosCorp Company, Inc.  All Rights Reserved.
     * This software is the proprietary information of PericosCorp Company.
*/
package PericosCorp.Framework.Core;

import java.text.DecimalFormat;

public class NumberHelpers {
	/**
	 *
	 * @author Arturo E. Salinas
	 */
	public static double RoundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
	}
}
