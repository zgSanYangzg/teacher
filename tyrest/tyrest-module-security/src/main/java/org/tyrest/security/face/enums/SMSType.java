
package org.tyrest.security.face.enums;
/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: SMSType.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SMSType.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月13日		yangbochao		Initial.
 *
 * </pre>
 */
public enum SMSType
{
	REG,

	FINDPASSWORD,

	AUTH, 
	
	CASHOUT_FINISH;

	/**
	 * toString
	 *
	 * @return String
	 */
	public String toString()
	{
		String sMSType = "";
		switch (this)
		{
			case REG:
				sMSType = "注册短信";
				break;
			case FINDPASSWORD:
				sMSType = "找回密码";
				break;
			case AUTH:
				sMSType = "登陆验证";
				break;
			default:
				sMSType = "";
		}

		return sMSType;
	}

	public static SMSType getSMSType(String str)
	{
		SMSType sMSType = null;
		for (SMSType ut : SMSType.values())
		{
			if (ut.name().equals(str))
			{
				sMSType = ut;
				break;
			}
		}
		return sMSType;
	}

}

/*
 * $Log: av-env.bat,v $
 */