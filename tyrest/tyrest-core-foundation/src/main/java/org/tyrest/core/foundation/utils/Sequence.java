package org.tyrest.core.foundation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.tyrest.core.foundation.constants.CoreConstants;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: Sequence.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: Sequence.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class Sequence {

	/**
	 * 生成訂單號,
	 * 
	 * @return
	 */
	public synchronized static  String generatorOrderSn() {
		return generatorSequence(3, CoreConstants.DATAFORMAT_STR);
	}
	
	
	/**
	 * 生成账单号,
	 * 
	 * @return
	 */
	public synchronized static  String generatorBillNo() {
		return generatorSequence(3, CoreConstants.DATAFORMAT_STR);
	}

	/**
	 * 
	 * 生成交易流水号25位 17位时间标识 年月日时分秒毫秒 再加 八位 随机数
	 */
	public synchronized static String generatorSerialNo() {
		return generatorSequence(8, CoreConstants.DATAFORMAT_STR);
	}

	/**
	 * 按時間和隨機數生成指定的編號，
	 * 
	 * @param randomCount
	 *            隨機數位數
	 * @param dataformatStr
	 *            時間格式
	 * @return
	 */
	public static String generatorSequence(int randomCount, String dataformatStr) {
		String randomStr = "";
		for (int i = 0; i < randomCount; i++) {
			randomStr += new Double(Math.random() * 10).intValue() + "";
		}
		return  new SimpleDateFormat(dataformatStr).format(new Date())+randomStr;
	}

}
