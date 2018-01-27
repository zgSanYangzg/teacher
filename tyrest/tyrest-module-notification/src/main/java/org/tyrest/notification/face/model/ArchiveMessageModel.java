package org.tyrest.notification.face.model;

import java.io.Serializable;
import java.util.Date;

public class ArchiveMessageModel implements Serializable {
	private static final long serialVersionUID = -4705027167525829595L;
	private Long sequenceNBR;
	private String messageType;
	private Date createTime;
	private String body;
	private String status;
	private String options;
	private String errorInfo;

	public Long getSequenceNBR() {
		return sequenceNBR;
	}

	public void setSequenceNBR(Long sequenceNBR) {
		this.sequenceNBR = sequenceNBR;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}
