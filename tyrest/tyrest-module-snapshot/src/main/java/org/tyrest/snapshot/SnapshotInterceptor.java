package org.tyrest.snapshot;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseEntity;
import org.tyrest.snapshot.face.orm.dao.SnapshotDAO;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: SnapshotInterceptor.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  Description:快照拦截器,如果实体开启了快照，则对实体进行快照
 * 
 *  Notes:
 *  $Id: SnapshotInterceptor.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */

@Aspect
@Order(value = 100)
@Component
public class SnapshotInterceptor
{
	private static final Logger logger = LoggerFactory.getLogger(SnapshotInterceptor.class);
	
	@Autowired
	private SnapshotDAO snapshotDAO;
	
	@Pointcut("execution(* org.tyrest.core.mysql.GenericDAOImpl.insert(..)) "
			+ " || execution(* org.tyrest.core.mysql.GenericDAOImpl.update(..)) "
			+ " || execution(* org.tyrest.*.dao.*DAOImpl.insertWithCache(..)) "
			+ " || execution(* org.tyrest.*.dao.*DAOImpl.updateWithCache(..)) ")
	public void snapshotCheck(){}
	
	@Around("snapshotCheck()")
	public Object checkSnapshot(ProceedingJoinPoint pjp) throws Throwable {
 		Object retVal = null;
		try {
			//正常的insert，update方法
			retVal = pjp.proceed();
			
			//检查entity是否需要快照
		    Object[] params = pjp.getArgs();
		    Object currentEntity = !ValidationUtil.isEmpty(params) ? params[0] : null;
		    
		    //如果entity上有开启快照的注解，则对entity进行快照
		    if(!ValidationUtil.isEmpty(currentEntity) && currentEntity instanceof BaseEntity){
		    	EnableSnapshot enableSnapshot = currentEntity.getClass().getAnnotation(EnableSnapshot.class);
		    	if(!ValidationUtil.isEmpty(enableSnapshot)){
		    		snapshotDAO.snapshot((BaseEntity)currentEntity, enableSnapshot.value());
		    	}
		    }
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return retVal;
	}
}

/*
*$Log: av-env.bat,v $
*/