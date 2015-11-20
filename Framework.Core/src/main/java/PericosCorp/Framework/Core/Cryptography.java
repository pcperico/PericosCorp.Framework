/**
 * Cryptography.java
 * Created on 20/11/2015
 * Copyright(c) 2015 PericosCorp Company, Inc.  All Rights Reserved.
     * This software is the proprietary information of PericosCorp Company.
*/
package PericosCorp.Framework.Core;

import java.util.Base64;

public final class Cryptography {
	/**
	 *
	 * @author Arturo E. Salinas
	 */
	public static String EncodeBase64(String string)
	{
		byte[] encodedBytes = Base64.getEncoder().encode(string.getBytes());
		return new String(encodedBytes);
	}
	
	public static String DecodeBase64(String string)
	{
		byte[] decodedBytes = Base64.getDecoder().decode(string.getBytes());
		return new String(decodedBytes);
	}
	
}
