
package org.tyrest.systemctl.face.enums;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SMSType.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SMSType.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
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