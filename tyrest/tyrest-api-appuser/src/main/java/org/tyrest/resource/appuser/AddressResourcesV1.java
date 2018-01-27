package org.tyrest.resource.appuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.security.validation.BeanValidation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.publicuser.face.model.AddressModel;
import org.tyrest.publicuser.face.service.AddressService;

@RestController
@RequestMapping(value = "/1/address")
@TyrestResource(module = "publicuser",value = "AddressResourcesV1", description = "公网用户收货地址管理")
public class AddressResourcesV1 extends BaseResources
{
	@Autowired
	AddressService addressService;
	
	@TyrstOperation(name = "createAddress",ApiLevel = APILevel.PUBLIC, description = "创建收货地址")
	@RequestMapping(value = "", method = RequestMethod.POST)
	@BeanValidation
	public ResponseModel<AddressModel> createAddress(@RequestBody AddressModel addressModel) throws Exception
	{
		return ResponseHelper.buildResponseModel(addressService.createAddress(addressModel));
	}
	
	@TyrstOperation(name = "deleteAddress", ApiLevel = APILevel.PUBLIC, description = "删除收货地址")
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseModel<String> deleteAddress(@RequestBody Long[] ids) throws Exception
	{
		addressService.deleteAddress(ids);
		return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
	}	
	
	@TyrstOperation(name = "updateAddress",ApiLevel = APILevel.PUBLIC, description = "更新收货地址")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseModel<AddressModel> updateAddress(@RequestBody AddressModel addressModel)throws Exception {
		addressService.updateAddress(addressModel);
		return ResponseHelper.buildResponseModel(addressModel);
	}
	
	@TyrstOperation(name = "setDefaultAddress",ApiLevel = APILevel.PUBLIC, description = "设置默认收货地址")
	@RequestMapping(value = "/{id}/defaultAddress", method = RequestMethod.PUT)
	public ResponseModel<AddressModel> setDefaultAddress(@PathVariable Long id) throws Exception
	{
		return ResponseHelper.buildResponseModel(addressService.updateDefaultAddress(id,Long.parseLong(RequestContext.getExeUserId())));
	}
	
	
	@TyrstOperation(name = "getCurrentUserAddress", ApiLevel = APILevel.PUBLIC, description = "公网用户获取自己的收货地址列表")
	@RequestMapping(value = "/public/me", method = RequestMethod.GET)
	public ResponseModel<List<AddressModel>> getCurrentUserAddress() throws Exception
	{
		return ResponseHelper.buildResponseModel(addressService.getAddresses(Long.parseLong(RequestContext.getExeUserId())));
	}
	
	@TyrstOperation(name = "getUserAddress", ApiLevel = APILevel.SUPERADMIN, description = "管理员查询用户收货地址列表")
	@RequestMapping(value = "/super/{userId}", method = RequestMethod.GET)
	public ResponseModel<List<AddressModel>> getUserAddress(
			@PathVariable Long userId,
			@RequestParam(required = false) String isDefault) throws Exception
	{
		return ResponseHelper.buildResponseModel(addressService.getByUserIdAndDefaultMark(userId,isDefault));
	}
	
}
/*
 * $Log: av-env.bat,v $
 */