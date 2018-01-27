package org.tyrest.core.foundation.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import org.apache.log4j.Logger;
/**
 * 
 * <pre>
 *  Tyrest
 *  File: RetryUtil.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: RetryUtil.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class RetryUtil {
	
	private static Logger logger = Logger.getLogger(RetryUtil.class);
	
	public interface Result {
		boolean isSuccess();
		String result();
	}
	
	public static Result retryOnException(Callable<Result> callee,int retryLimit){
		Result result = null;
		final List<String> retryError = new ArrayList<String>();
		
		for (int i = 0; i < retryLimit; i++) {
			try {
				result = callee.call();
			} catch (Exception e) {
				retryError.add(e.getMessage());
				
				result = new Result(){
					public boolean isSuccess() {
						return false;
					}
					public String result() {
						return Arrays.toString(retryError.toArray(new String[]{}));
					}
				};
				if (logger.isDebugEnabled()) {
					logger.debug("retry on " + (i + 1) + " times v = " + (result == null ? null : result.result()) , e);
				}
			}
			if (result.isSuccess()) break;
			logger.error("retry on " + (i + 1) + " times but not matching v = " + (result == null ? null : result.result()));
		}
		return result;
	}
	
	public static void main(String[] args) {
		retryOnException(new Callable<RetryUtil.Result>() {
			public Result call() throws Exception {
				Random random = new Random();
				final int result = random.nextInt(5);
				return new Result(){

					@Override
					public boolean isSuccess() {
						return result == 3;
					}

					@Override
					public String result() {
						return null;
					}
					
				};
			}
		}, 3);
	}
}

/*
*$Log: av-env.bat,v $
*/