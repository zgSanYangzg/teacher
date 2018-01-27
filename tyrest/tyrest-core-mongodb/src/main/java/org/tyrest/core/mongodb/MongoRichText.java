package org.tyrest.core.mongodb;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: MongoRichText.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: MongoRichText.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Document
public class MongoRichText implements Serializable{
	private static final long serialVersionUID = 191055253217833440L;
	
	@Id
	private Long sequenceNBR;
	private String payLoad;

	public MongoRichText(){}
	
	public MongoRichText(Long sequenceNBR,String payLoad){
		this.sequenceNBR = sequenceNBR;
		this.payLoad = payLoad;
	}
	
	public Long getSequenceNBR() {
		return sequenceNBR;
	}

	public void setSequenceNBR(Long sequenceNBR) {
		this.sequenceNBR = sequenceNBR;
	}

	public String getPayLoad() {
		return this.payLoad;
	}

	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}

}
