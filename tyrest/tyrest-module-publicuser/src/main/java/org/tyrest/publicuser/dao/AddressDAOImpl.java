package org.tyrest.publicuser.dao;

import org.springframework.stereotype.Repository;
import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.publicuser.face.orm.dao.AddressDAO;
import org.tyrest.publicuser.face.orm.entity.Address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AddressDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AddressDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value="addressDAO")
public class AddressDAOImpl extends GenericDAOImpl<Address> implements AddressDAO
{

	@Override
	public List<Address> findAddresses(Long userId) throws Exception {
		List<Address> address = Redis.get(Address.class.getSimpleName(), userId.toString());
		if(!ValidationUtil.isEmpty(address)){
			return address;
		}
		String sqlSufix = " AND USER_ID = :USER_ID ORDER BY IS_DEFAULT DESC, REC_DATE DESC ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("USER_ID", userId);
		List<Address> returnAddress = this.find(sqlSufix, params, null, null);
		if(!ValidationUtil.isEmpty(returnAddress)){
			Redis.set(returnAddress, Address.class.getSimpleName(), userId.toString());
		}
		return returnAddress;
	}

	@Override
	public void insertWithDeleteCache(Address address) throws Exception {
		Redis.remove(Address.class.getSimpleName(), address.getUserId().toString());
		this.insert(address);
	}

	@Override
	public void updateWithDeleteCache(Address address) throws Exception {
		Redis.remove(Address.class.getSimpleName(), address.getUserId().toString());
		this.update(address);
		
	}

	@Override
	public List<Address> findByList(Long userId, String isDefault) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder sqlSufix = new StringBuilder();
		if(!ValidationUtil.isEmpty(userId)){
			sqlSufix.append(" AND USER_ID=:USER_ID ");
			parameters.put("USER_ID", userId);
		}
		if(!ValidationUtil.isEmpty(isDefault)){
			sqlSufix.append(" AND IS_DEFAULT=:IS_DEFAULT ");
			parameters.put("IS_DEFAULT", isDefault);
		}
		sqlSufix.append(" ORDER BY IS_DEFAULT DESC, REC_DATE DESC ");
		return this.find(sqlSufix.toString(), parameters, null, null);
	}

	@Override
	public void deleteWithDeleteCache(Long id) throws Exception {
		Redis.remove(Address.class.getSimpleName(), id.toString());
		this.delete(id);
	}

}
