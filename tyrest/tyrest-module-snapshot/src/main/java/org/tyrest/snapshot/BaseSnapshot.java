package org.tyrest.snapshot;

import java.util.Date;

import javax.persistence.MappedSuperclass;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: BaseSnapshot.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: BaseSnapshot.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@MappedSuperclass
public interface BaseSnapshot
{
	public Long getMasterSequenceNbr();
	public void setMasterSequenceNbr(Long masterSequenceNbr);
	public Date getMasterRecDate();
	public void setMasterRecDate(Date masterRecDate);
	public Long getSequenceNBR();
	public void setSequenceNBR(Long sequenceNBR);
}

/*
*$Log: av-env.bat,v $
*/