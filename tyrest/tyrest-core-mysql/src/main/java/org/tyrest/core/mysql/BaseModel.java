package org.tyrest.core.mysql;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: BaseModel.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: BaseModel.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public class BaseModel implements Serializable
{
	private static final long serialVersionUID = 8714060812739139000L;

	protected Long sequenceNBR;
	protected Date recDate;
	protected String recUserId;
	protected String recStatus;
	protected String extend1;
	protected String extend2;
	protected String extend3;
	protected String description;
	
	public BaseModel(){
		
	}
	
	public BaseModel(Long sequneceNBR, Date recDate, String recUserId,
			String recStatus, String extend1, String extend2, String extend3,
			String description) {
		super();
		this.sequenceNBR = sequneceNBR;
		this.recDate = recDate;
		this.recUserId = recUserId;
		this.recStatus = recStatus;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
		this.description = description;
	}



	

	public Long getSequenceNBR() {
		return sequenceNBR;
	}



	public void setSequenceNBR(Long sequenceNBR) {
		this.sequenceNBR = sequenceNBR;
	}



	public Date getRecDate() {
		return recDate;
	}
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
	public String getRecUserId() {
		return recUserId;
	}
	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId;
	}
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	public String getExtend2() {
		return extend2;
	}
	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	public String getExtend3() {
		return extend3;
	}
	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}