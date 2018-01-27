package org.tyrest.publicuser.face.orm.dao;

import java.util.List;


import org.tyrest.core.mysql.GenericDAO;
import org.tyrest.publicuser.face.orm.entity.Address;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AddressDAO.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AddressDAO.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface AddressDAO extends GenericDAO<Address>
{
	/**
	 * 查询公网用户的地址信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Address> findAddresses(Long userId) throws Exception;

	/**
	 * 添加地址信息
	 * @param address
	 * @throws Exception 
	 */
	public void insertWithDeleteCache(Address address) throws Exception;

	/**
	 * 修改地址信息
	 * @param address
	 * @throws Exception 
	 */
	public void updateWithDeleteCache(Address address) throws Exception;

	/**
	 * 查询地址信息列表
	 * @param userId
	 * @param isDefault
	 * @return
	 * @throws Exception
	 */
	public List<Address> findByList(Long userId, String isDefault) throws Exception;

	/**
	 * 删除地址信息
	 * @param id
	 * @throws Exception 
	 */
	public void deleteWithDeleteCache(Long id) throws Exception;

}
