package org.tyrest.sysorder.order.state;
/**
 * <pre>
 * 
 *  File: BranchOperationType.java
 * 
 *  Description:
	流程支线操作定义 
 *  每种流程类型都应该有一套自己的支线操作定义
 * 
 *  Notes:
 *  BranchOperationType.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
public interface BranchOperationType
{
	/**
	 * 操作名称
	 */
	public String getOperationName();
	
	/**
	 * 
	 * 操作标识
	 */
	public String getOperationCode();
	
	
	/**
	 * 
	 * 操作规则验证
	 * @return
	 */
	public String getOprationRuleHandler();
	
	
	/**
	 * 分支操作处理器
	 * @return
	 */
	public String getBranchHandler();
}

/*
*$Log: av-env.bat,v $
*/