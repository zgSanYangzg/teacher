package org.tyrest.publicuser.face.service;

import java.util.List;


import org.tyrest.core.mysql.BaseService;
import org.tyrest.publicuser.face.model.AddressModel;
import org.tyrest.publicuser.face.orm.entity.Address;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AddressService.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AddressService.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public interface AddressService extends BaseService<AddressModel,Address>
{
	/**
	 * 创建收货地址
	 * @param adressModel
	 * @return
	 * @throws Exception
	 */
	public AddressModel createAddress(AddressModel adressModel) throws Exception;

	/**
	 * 修改收货地址
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public AddressModel updateAddress(AddressModel model) throws Exception;

	/**
	 * 删除收货地址
	 * @param ids
	 * @throws Exception
	 */
	void deleteAddress(Long[] ids) throws Exception;

	/**
	 * 获取公网用户的收货地址
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<AddressModel> getAddresses(Long userId) throws Exception;

	/**
	 * 修改默认的收货地址
	 * @param id
	 * @param parseLong
	 * @return
	 * @throws Exception
	 * @throws Exception 
	 */
	public AddressModel updateDefaultAddress(Long id, Long userId) throws Exception, Exception;

	/**
	 * 根据用户ID和默认标志查询收货地址
	 * @param userId
	 * @param isDefault
	 * @return
	 * @throws Exception 
	 */
	public List<AddressModel> getByUserIdAndDefaultMark(Long userId, String isDefault) throws Exception;
}
