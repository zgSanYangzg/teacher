package org.tyrest.notification.face.orm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notification_archive_message")
public class ArchiveMessage implements Serializable {
	private static final long serialVersionUID = -4705027167525829595L;
	@Id
	@Column(name = "SEQUENCE_NBR")
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

	@Column(name = "MESSAGE_TYPE", nullable = false)
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	@Column(name = "CREATE_TIME", nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "BODY")
	public Object getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "OPTIONS")
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ERROR_INFO")
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

}
