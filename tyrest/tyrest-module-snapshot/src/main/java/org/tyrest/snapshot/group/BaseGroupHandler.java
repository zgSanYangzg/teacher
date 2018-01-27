package org.tyrest.snapshot.group;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.core.foundation.exceptions.BusinessException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.snapshot.BaseSnapshot;
import org.tyrest.snapshot.SnapshotType;
import org.tyrest.snapshot.face.orm.dao.SnapshotDAO;
import org.tyrest.snapshot.face.orm.dao.SnapshotGroupDAO;
import org.tyrest.snapshot.face.orm.entity.SnapshotGroup;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: BaseGroupHandler.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: BaseGroupHandler.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Component(value = "baseGroupHandler")
public class BaseGroupHandler<M>
{
	@Autowired
	protected  SequenceGenerator sequenceGenerator;
	
	
	@Autowired
	protected SnapshotGroupDAO snapshotGroupDAO;
	
	@Autowired
	protected SnapshotDAO snapshotDAO;
	
	
	
	
	protected List<SnapshotGroup> saveSnapshot(SnapshotGroupType snapshotGroupType, String businessCode,Map<SnapshotType,Long> mainSnapshotNbrs) throws Exception
	{
		List<SnapshotGroup> returnList = new ArrayList<SnapshotGroup>();
		
		for(SnapshotType snapshotType : snapshotGroupType.getSnapshotArray())
		{
			Long mainNbr = mainSnapshotNbrs.get(snapshotType);
			if(!snapshotType.getNullAdble() && ValidationUtil.isEmpty(mainNbr)){
				throw new BusinessException("快照组【"+snapshotGroupType.getGroupName()+"】类型【"+snapshotType.getSnapshotType()+"】不能为空.");
			}
			if(ValidationUtil.isEmpty(mainNbr)){
				continue;
			}
			Long snptNbr = snapshotDAO.getLastSnptSequenceNbr(mainNbr, snapshotType.getSnptClass());
			if(ValidationUtil.isEmpty(snptNbr)){
				throw new BusinessException("快照组【"+snapshotGroupType.getGroupName()+"】类型为【"+snapshotType.getSnapshotType()+"】快照主键不存在.");
			}
			//保存
			SnapshotGroup snapshotGroup = new SnapshotGroup();
			snapshotGroup.setBusinessCode(businessCode);
			snapshotGroup.setGroupCode(snapshotGroupType.getGroupCode());
			snapshotGroup.setGroupName(snapshotGroupType.getGroupName());
			snapshotGroup.setSnapshotSequenceNbr(snptNbr);
			snapshotGroup.setSequenceNBR(sequenceGenerator.getNextValue());
			snapshotGroup.setSnapshotType(snapshotType.getSnapshotType());
			snapshotGroup.setNullable(String.valueOf(false));
			snapshotGroup.setRecDate(new Date());
			snapshotGroup.setRecStatus(CoreConstants.COMMON_ACTIVE);
			snapshotGroup.setRecUserId(RequestContext.getExeUserId());
			snapshotGroupDAO.insert(snapshotGroup);
			returnList.add(snapshotGroup);
		}
		return returnList;		
	}


	@SuppressWarnings("unchecked")
	protected M assembleModel(SnapshotGroupType snapshotGroupType, String businessCode) throws Exception
	{
		//查询快照列表
		M m = null;
		List<SnapshotGroup>  list = this.snapshotGroupDAO.queryForList(snapshotGroupType.getGroupCode(), businessCode, null);
		Map<Object,SnapshotGroup> map = new HashMap<Object,SnapshotGroup>();
		if(!ValidationUtil.isEmpty(list))
		{
			map = Bean.listToMap(list, "snapshotType", SnapshotGroup.class);
		}
		else
		{
			throw new BadRequestException("空的快照分组实例.");
		}
		for(SnapshotType snapshotType : snapshotGroupType.getSnapshotArray())
		{
			SnapshotGroup snapshotGroup  = map.get(snapshotType.getSnapshotType());
			if(!ValidationUtil.isEmpty(snapshotGroup))
			{
				BaseSnapshot baseSnapshot = snapshotDAO.getSnptEntity(snapshotGroup.getSnapshotSequenceNbr(), snapshotType.getSnptClass());
				m = (M)Bean.copyExistPropertis(baseSnapshot, m.getClass().newInstance());
			}
		}
		return m;
	}
	

}

/*
*$Log: av-env.bat,v $
*/