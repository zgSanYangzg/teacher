package org.tyrest.systemctl.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.systemctl.face.orm.dao.LocationRelationDAO;
import org.tyrest.systemctl.face.orm.entity.LocationRelation;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: LocationRelationDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: LocationRelationDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="locationRelationDAO")
public class LocationRelationDAOImpl extends GenericDAOImpl<LocationRelation> implements LocationRelationDAO
{

}
