package org.tyrest.security.face.orm.dao;

import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.security.face.orm.entity.Principal;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: PublicUserDAO.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PublicUserDAO.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月21日		yangbochao		Initial.
 *
 * </pre>
 */
public interface PrincipalDAO extends GenericDAO<Principal> {
	
	void deleteByUserId(Long userId) throws Exception;

	Principal findByUserId(Long userId) throws Exception;

	void cleanTestData(Long userId) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */