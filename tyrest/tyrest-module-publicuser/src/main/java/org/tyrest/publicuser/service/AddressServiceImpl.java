package org.tyrest.publicuser.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.BadRequestException;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.publicuser.face.model.AddressModel;
import org.tyrest.publicuser.face.orm.dao.AddressDAO;
import org.tyrest.publicuser.face.orm.entity.Address;
import org.tyrest.publicuser.face.service.AddressService;
import org.tyrest.systemctl.face.orm.dao.LocationInfoDAO;
import org.tyrest.systemctl.face.orm.entity.LocationInfo;
/** 
 * 
 * <pre>
 *  Tyrest
 *  File: AddressServiceImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: AddressServiceImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value="addressService")
public class AddressServiceImpl extends BaseServiceImpl<AddressModel, Address> implements AddressService
{
	
	@Autowired
	AddressDAO addressDAO;
	
	@Autowired
	LocationInfoDAO locationInfoDAO;
	
	@Override
	public AddressModel createAddress(AddressModel addressModel) throws Exception {
		addressModel.setIsDefault(ValidationUtil.isEmpty(addressModel.getIsDefault())?CoreConstants.COMMON_N:addressModel.getIsDefault());
		addressModel.setUserId(Long.parseLong(RequestContext.getExeUserId()));
		Address address = this.prepareEntity(addressModel);
		addressDAO.insertWithDeleteCache(address);
		//修改默认的收货地址
		Bean.toModel(address, addressModel);
		updateDefaultAddress(addressModel);
		this.setLocationName(addressModel);
		return addressModel;
	}

	@Override
	public AddressModel updateAddress(AddressModel addressModel) throws Exception {
		if(ValidationUtil.isEmpty(addressModel.getSequenceNBR())){
			throw new ValidationException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		addressModel.setIsDefault(ValidationUtil.isEmpty(addressModel.getIsDefault())?CoreConstants.COMMON_N:addressModel.getIsDefault());
		Address oldAddress = this.addressDAO.findById(addressModel.getSequenceNBR());
		if(ValidationUtil.isEmpty(oldAddress)){
			throw new BadRequestException(MessageConstants.DATA_NOT_FOUND);
		}
		Bean.copyExistPropertis(addressModel, oldAddress);
		oldAddress.setRecDate(new Date());
		oldAddress.setRecUserId(RequestContext.getExeUserId());
		this.addressDAO.updateWithDeleteCache(oldAddress);
		//修改默认的收货地址
		Bean.toModel(oldAddress, addressModel);
		updateDefaultAddress(addressModel);
		this.setLocationName(addressModel);
		return addressModel;
	}

	@Override
	public void deleteAddress(Long[] ids) throws Exception {
		if(!ValidationUtil.isEmpty(ids)){
			for(Long id:ids){
				addressDAO.deleteWithDeleteCache(id);
			}
		}
	}

	@Override
	public List<AddressModel> getAddresses(Long userId) throws Exception {
		List<Address> addressList = addressDAO.findAddresses(userId);
		List<AddressModel> addressModelList = null;
		if(!ValidationUtil.isEmpty(addressList)){
			addressModelList = new ArrayList<AddressModel>();
			for(Address address : addressList){
				AddressModel addressModel = Bean.toModel(address, new AddressModel());
				setLocationName(addressModel);
				addressModelList.add(addressModel);
			}
		}
		return addressModelList;
	}
	
	@Override
	public AddressModel updateDefaultAddress(Long id, Long userId) throws Exception {
		Address oldAddress = this.addressDAO.findById(id);
		if(ValidationUtil.isEmpty(oldAddress)){
			throw new BadRequestException(MessageConstants.DATA_NOT_FOUND);
		}
		oldAddress.setIsDefault(CoreConstants.COMMON_Y);	
		oldAddress.setRecDate(new Date());
		oldAddress.setRecUserId(RequestContext.getExeUserId());
		this.addressDAO.updateWithDeleteCache(oldAddress);
		AddressModel addressModel = Bean.toModel(oldAddress, new AddressModel());
		//修改默认的收货地址
		updateDefaultAddress(addressModel);
		this.setLocationName(addressModel);
		return addressModel;
	}

	@Override
	public List<AddressModel> getByUserIdAndDefaultMark(Long userId, String isDefault) throws Exception {
		List<Address> addressList = null;
		if(ValidationUtil.isEmpty(isDefault)){
			addressList = addressDAO.findAddresses(userId);
		}else{
			addressList = addressDAO.findByList(userId, isDefault);
		}
		List<AddressModel> addressModelList = null;
		if(!ValidationUtil.isEmpty(addressList)){
			addressModelList = new ArrayList<AddressModel>();
			for(Address address : addressList){
				AddressModel addressModel = Bean.toModel(address, new AddressModel());
				setLocationName(addressModel);
				addressModelList.add(addressModel);
			}
		}
		return addressModelList;
	}
	
	/**
	 * 设置地址名称
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private void setLocationName(AddressModel model ) throws Exception{
		LocationInfo province = locationInfoDAO.findByCode(model.getProvince());
		LocationInfo city = locationInfoDAO.findByCode(model.getCity());
		LocationInfo region = locationInfoDAO.findByCode(model.getRegion());
		
		if(!ValidationUtil.isEmpty(region)){
			model.setRegionName(region.getLocationName());
		}
		if(!ValidationUtil.isEmpty(city)){
			model.setCityName(city.getLocationName());
		}
		if(!ValidationUtil.isEmpty(province)){
			model.setProvinceName(province.getLocationName());
		}
	}
	
	/**
	 * 修改默认的收货地址
	 * @param addressModel
	 * @throws Exception
	 */
	private void updateDefaultAddress(AddressModel addressModel) throws Exception {
		if(CoreConstants.COMMON_Y.equals(addressModel.getIsDefault())){
			List<Address> defaultAddressList = this.addressDAO.findByList(addressModel.getUserId(), CoreConstants.COMMON_Y);
			if(!ValidationUtil.isEmpty(defaultAddressList)){
				for(Address defaultAddress : defaultAddressList){
					if(!addressModel.getSequenceNBR().equals(defaultAddress.getSequenceNBR())){
						defaultAddress.setIsDefault(CoreConstants.COMMON_N);
						defaultAddress.setRecDate(new Date());
						defaultAddress.setRecUserId(RequestContext.getExeUserId());
						addressDAO.updateWithDeleteCache(defaultAddress);
					}
				}
			}
		}
	}
}
