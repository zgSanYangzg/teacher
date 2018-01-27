package org.tyrest.snapshot.face.orm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.tyrest.core.mysql.BaseEntity;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotGroup.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  快照分组
 * 
 *  Notes:
 *  $Id: SnapshotGroup.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "snapshot_group")
public class SnapshotGroup extends BaseEntity
{
	 /**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 3729425585073163980L;
	private String groupCode ;// '快照分组类型编号',
	 private String groupName ;// '快照分组名称',
	 private Long snapshotSequenceNbr ;// '快照主键',
	 private String snapshotType ;// '快照类型（系统默认按快照对象分类）',
	 private String nullable ;//'是否可为空',
	 private String businessCode ;// '业务标识code',
	 
	 
	public String getGroupCode()
	{
		return groupCode;
	}
	public void setGroupCode(String groupCode)
	{
		this.groupCode = groupCode;
	}
	public String getGroupName()
	{
		return groupName;
	}
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}
	public Long getSnapshotSequenceNbr()
	{
		return snapshotSequenceNbr;
	}
	public void setSnapshotSequenceNbr(Long snapshotSequenceNbr)
	{
		this.snapshotSequenceNbr = snapshotSequenceNbr;
	}
	public String getSnapshotType()
	{
		return snapshotType;
	}
	public void setSnapshotType(String snapshotType)
	{
		this.snapshotType = snapshotType;
	}
	public String getNullable()
	{
		return nullable;
	}
	public void setNullable(String nullable)
	{
		this.nullable = nullable;
	}
	public String getBusinessCode()
	{
		return businessCode;
	}
	public void setBusinessCode(String businessCode)
	{
		this.businessCode = businessCode;
	}

	
}

