package org.tyrest.resource.systemctl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.ParamConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.model.Page;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.security.validation.BeanValidation;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;
import org.tyrest.systemctl.face.model.DictionaryEntryModel;
import org.tyrest.systemctl.face.model.DictionaryModel;
import org.tyrest.systemctl.face.service.DictionaryService;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: DictionaryResourceV1.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: DictionaryResourceV1.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */ 
@RestController
@RequestMapping(value = "/1/dictionary")
@TyrestResource(module = "systemctl",value = "DictionaryResourceV1",description="字典信息")
public class DictionaryResourceV1 extends BaseResources
{
	@Autowired
	private DictionaryService dictionaryService;
	
	@TyrstOperation(name = "createDictionary", ApiLevel = APILevel.AGENCY,description = "创建字典")
	@RequestMapping(value = "", method = RequestMethod.POST)
	@BeanValidation
	public ResponseModel<DictionaryModel> createDictionary(@RequestBody DictionaryModel dictionaryModel) throws Exception
	{
		return ResponseHelper.buildResponseModel(dictionaryService.createDictionary(dictionaryModel));
	}
	
	@TyrstOperation(name = "deleteDictionary", ApiLevel = APILevel.AGENCY,description = "删除字典")
	@RequestMapping(value = "", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseModel<String> deleteDictionary(@RequestBody String[] codes) throws Exception
	{
		return ResponseHelper.buildResponseModel(dictionaryService.deleteDictionary(RequestContext.getAgencyCode(), codes));
	}

	@TyrstOperation(name = "updateDictionary", ApiLevel = APILevel.AGENCY,description = "更新字典")
	@RequestMapping(value = "/{code}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseModel<DictionaryModel> updateDictionary(
			@RequestBody DictionaryModel dictionaryModel,
			@PathVariable(value="code") String code) throws Exception {
		dictionaryModel.setDictCode(code);
		return ResponseHelper.buildResponseModel(dictionaryService.updateDictionary(dictionaryModel));
	}
	@TyrstOperation(name = "queryByCode", ApiLevel = APILevel.ALL,description = "通过字典编码获取字典")
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ResponseModel<DictionaryModel> queryByCode(
			@PathVariable(value = "code") String code) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.getByCode(RequestContext.getAgencyCode(), code));
	}
	
	@TyrstOperation(name = "queryForPage", ApiLevel = APILevel.ALL,description = "分页查询字典信息")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseModel<Page> queryForPage(
			@RequestParam(value = "agencyCode", required = false) String agencyCode,
			@RequestParam(value = "buType", required = false) String buType,
			@RequestParam(value = "dictAlias", required = false) String dictAlias,
			@RequestParam(value = "dictName", required = false) String dictName,
			@RequestParam(value = "dictCode", required = false) String dictCode,
			@RequestParam(value = ParamConstants.SIDX, required = false) String orderby,
			@RequestParam(value = ParamConstants.SORT, required = false) String order,
			@RequestParam(value = ParamConstants.OFFSET) int start,
			@RequestParam(value = ParamConstants.LENGTH) int length) throws Exception
	{
		return ResponseHelper.buildResponseModel(dictionaryService.getDictionaryByPage(RequestContext.getAgencyCode(),
				dictCode, dictName, new Page(length,start), orderby, order));
	}
	
	@TyrstOperation(name = "isDictionaryCodeAvailable", ApiLevel = APILevel.ALL,description = "检查字典编号是否可用")
	@RequestMapping(value = "/{code}/available", method = RequestMethod.GET)
	public ResponseModel<Boolean> isDictionaryCodeAvailable(
			@PathVariable(value = "code") String code) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.isDictionaryCodeAvailable(RequestContext.getAgencyCode(), code));
	}
	
	@TyrstOperation(name = "queryForValuePage", ApiLevel = APILevel.ALL, description = "根据字典编码分页条件查询字典值")
	@RequestMapping(value = "/{code}/entries/page", method = RequestMethod.GET)
	public ResponseModel<Page> queryForValuePage(
			@PathVariable(value = "code") String dictCode,
			@RequestParam(value = "agencyCode", required = false) String agencyCode,
			@RequestParam(value = ParamConstants.SIDX, required = false) String orderby,
			@RequestParam(value = ParamConstants.SORT, required = false) String order,
			@RequestParam(value = ParamConstants.OFFSET) int start,
			@RequestParam(value = ParamConstants.LENGTH) int length) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.getEntryByPage(agencyCode, dictCode, new Page(length,start), orderby, order));
	}

	@TyrstOperation(name = "queryList", ApiLevel = APILevel.ALL, description = "根据字典编码获取当前商家字典的所有值")
	@RequestMapping(value = "/{code}/entries", method = RequestMethod.GET)
	public ResponseModel<List<DictionaryEntryModel>> queryList(
			@PathVariable(value = "code") String code ) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.getEntries(RequestContext.getAgencyCode(), code));
	}


	@TyrstOperation(name = "createStandardChoicesValue", ApiLevel = APILevel.AGENCY, description = "创建字典值")
	@RequestMapping(value = "/{code}/entry", method = RequestMethod.POST)
	public ResponseModel<DictionaryEntryModel> createStandardChoicesValue(
			@RequestBody DictionaryEntryModel dictionaryEntryModel,
			@PathVariable String code ) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.createEntry(dictionaryEntryModel));
	}

	@TyrstOperation(name = "deleteStandardChoicesValue", ApiLevel = APILevel.AGENCY, description = "删除字典值")
	@RequestMapping(value = "/{code}/entries", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseModel<String> deleteStandardChoicesValue(
			@PathVariable String code,
			@RequestBody String[] keys) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.deleteEntry(RequestContext.getAgencyCode(), code, keys));
	}

	@TyrstOperation(name = "updateStandardChoicesValue", ApiLevel = APILevel.AGENCY, description = "更新字典值")
	@RequestMapping(value = "/{code}/entry/{key}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseModel<DictionaryEntryModel> updateStandardChoicesValue(
			@RequestBody DictionaryEntryModel dictionaryEntryModel, 
			@PathVariable String code,@PathVariable String key) throws Exception {
		dictionaryEntryModel.setDictCode(code);
		dictionaryEntryModel.setEntryKey(key);
		return ResponseHelper.buildResponseModel(dictionaryService.updateEntry(dictionaryEntryModel));
	}


	@TyrstOperation(name = "isEntryKeyAvailable", ApiLevel = APILevel.ALL, description = "字典值key是否可用")
	@RequestMapping(value = "/{code}/entry/{key}/available", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseModel<Boolean> isEntryKeyAvailable(
			@PathVariable String key,
			@PathVariable String code, 
			@RequestParam String agencyCode) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.isEntryKeyAvailable(agencyCode, code, key));
	}

	@TyrstOperation(name = "updateLockStatus", ApiLevel = APILevel.AGENCY,  description = "启用/禁用字典值。")
	@RequestMapping(value = "/{code}/entry/{key}/lockStatus", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseModel<DictionaryEntryModel> updateLockStatus(
			@PathVariable String key,
			@PathVariable String code) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.updateEntryLockStatus(RequestContext.getAgencyCode(), code, key));
	}


	@TyrstOperation(name = "updateLockStatus", ApiLevel = APILevel.SUPERADMIN,  description = "获取字典值对象。")
	@RequestMapping(value = "/{code}/entry/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseModel<DictionaryEntryModel> getEntry(
			@PathVariable String key,
			@PathVariable String code) throws Exception {
		return ResponseHelper.buildResponseModel(dictionaryService.getEntry(CoreConstants.CODE_SUPER_ADMIN,code, key));
	}
}

/*
 * $Log: av-env.bat,v $
 */