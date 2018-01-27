package org.tyrest.core.rest.containers;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: APILevel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: APILevel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public enum APILevel
{
	ALL(0), 
	
	PUBLIC(100),

	AGENCY(200),

	SUPERADMIN(300);

	/**
	 * Field value
	 */
	private int value;

	/**
	 * Constructs ...
	 *
	 *
	 * @param str Integer
	 */
	private APILevel(int str)
	{
		this.value = str;
	}

	/**
	 * Method getValue
	 *
	 *
	 * @return Integer
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	public String toString()
	{
		String aPILevel = "";
		switch (this)
		{
			case PUBLIC:
				aPILevel = "PUBLIC";
				break;

			case AGENCY:
				aPILevel = "AGENCY";
				break;
			case ALL:
				aPILevel = "ALL";
				break;
			case SUPERADMIN:
				aPILevel = "SUPERADMIN";
				break;
			default:
				aPILevel = "";
		}

		return aPILevel;
	}

	public static APILevel getAPILevel(int str)
	{
		APILevel aPILevel = null;
		for (APILevel ut : APILevel.values())
		{
			if (ut.value == str)
			{
				aPILevel = ut;
				break;
			}
		}
		return aPILevel;
	}
}

/*
 * $Log: av-env.bat,v $
 */