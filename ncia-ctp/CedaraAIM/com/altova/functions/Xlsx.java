/**
 * Xlsx.java
 *
 * This file was generated by MapForce 2011r2sp1.
 *
 * YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
 *
 * Refer to the MapForce Documentation for further details.
 * http://www.altova.com/mapforce
 */

package com.altova.functions;

import com.altova.types.DateTime;
import com.altova.types.Duration;

public class Xlsx
{
	// comparison function
	public static boolean equalignorecase(String a, String b) { return a.equalsIgnoreCase(b); }

	public static DateTime XLSXToDatetime(double o)
	{	
		int days = (int) o;
		double seconds = Math.floor((o - days) * 86400000.0 +0.5) / 1000.0;
				
		days--;
		
		if (days >= 60)
			days--;

		return Lang.datetimeAdd(new DateTime(1900, 1, 1), new Duration(0, 0, days, 0, 0, (int) seconds, 0.0, false));
	}	

	public static DateTime XLSXToTime(double o)
	{
		int days = (int) o;
		double seconds = Math.floor((o - days) * 86400000.0 + 0.5) / 1000.0;
		
		return Lang.datetimeAdd(new DateTime(1, 1, 1), new Duration(0, 0, 0, 0, 0, (int) seconds, 0.0, false));
	}

	public static DateTime XLSXToDate(double o)
	{	
		int days = (int) o;
		
		days--;
		
		if (days >= 60)
			days--;

		return Lang.datetimeAdd(new DateTime(1900,1,1), new Duration(0, 0, days, 0, 0, 0, 0.0, false));
	}

	public static double datetimeToXLSX(DateTime o)
	{	
		DateTime dt = new DateTime(o);
		dt.setTimezoneOffset(0);
		
		Duration diff = Lang.datetimeDiff(dt, new DateTime(1900, 1, 1));
		double result = (double) diff.getDayTimeValue() / 86400000.0;
		
		if (result >= 60)
			result++;
		
		return ++result;
	}

	public static double timeToXLSX(DateTime o)
	{	
		DateTime dt = new DateTime(o);
		dt.setTimezoneOffset(0);

		Duration diff = Lang.datetimeDiff(dt, new DateTime(1, 1, 1));
		return (double) diff.getDayTimeValue() / 86400000.0;
	}

	public static double dateToXLSX(DateTime o)
	{	
		return datetimeToXLSX(o);
	}
	
	public static int columnName2Index(String name)
	{
		return com.altova.xml.XLSXFileReader.columnName2Index(name);
	}
	
	public static String index2ColumnName(int index)
	{
		return com.altova.xml.XLSXFileReader.index2ColumnName(index);
	}
	
}
