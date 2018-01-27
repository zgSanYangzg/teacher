package org.tyrest.core.mysql;

import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.exceptions.DataNotFoundException;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.sequence.SequenceGenerator;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Date;

/**
 * 
 * <pre>
 * 
 *  freeapis
 *  File: BaseServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: BaseServiceImpl.java 31101200-9 2014-10-14 16:43:51Z freeapis\yangbochao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年8月13日		yangbochao		Initial.
 *
 * </pre>
 */
public class BaseServiceImpl<M, P> implements BaseService<M, P> {

	private Class<P> poClass = null;

	private Class<M> modelCalss = null;

	@Autowired
	private GenericDAO<P> genericDAO;
	
	@Autowired
	protected SequenceGenerator sequenceGenerator;

	@SuppressWarnings("unchecked")
	protected Class<P> getPoClass() {
		if (poClass == null) {
			poClass = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		}
		return poClass;
	}

	@SuppressWarnings("unchecked")
	protected Class<M> getModelClass() {
		if (modelCalss == null) {
			modelCalss = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return modelCalss;
	}

	protected String getTableName(){
		Class<P> clazz = this.getPoClass();
		return  clazz.getAnnotation(Table.class).name().toUpperCase();
	}

	protected String getTableName(Class<? extends BaseEntity> poClass){
		return  poClass.getAnnotation(Table.class).name().toUpperCase();
	}

	/**
	 * TODO.简化service层的创建操作,填充id创建时间等公有字段
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected P prepareEntity(M model) throws Exception {
		P entity = Bean.toPo(model, this.getPoClass().newInstance());
		if (entity instanceof BaseEntity) {
			BaseEntity temp = (BaseEntity) entity;
			temp.setSequenceNBR(sequenceGenerator.getNextValue());
			temp.setRecDate(new Date());
			temp.setRecStatus(CoreConstants.COMMON_ACTIVE);
			temp.setRecUserId(RequestContext.getExeUserId());
		}
		return entity;
	}
	
	@Override
	public M create(M model) throws Exception {
		P entity = this.prepareEntity(model);
		genericDAO.insert(entity);
		return Bean.toModel(entity, model);
	}

	@Override
	public String delete(Long... ids) throws Exception {
		for(Long id : ids){
			genericDAO.delete(id);
		}
		return Arrays.toString(ids);
	}

	@Override
	public M update(M model) throws Exception {
		if(!(model instanceof BaseModel))
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);

		P entity = genericDAO.findById(((BaseModel) model).getSequenceNBR());

		if(ValidationUtil.isEmpty(entity))
			throw new DataNotFoundException(MessageConstants.DATA_NOT_FOUND);

		if(!(entity instanceof BaseEntity))
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);

		BaseEntity currentEntity = (BaseEntity)entity;
		Bean.copyExistPropertis(model,currentEntity);
		currentEntity.setRecDate(new Date());
		currentEntity.setRecStatus(CoreConstants.COMMON_ACTIVE);
		currentEntity.setRecUserId(RequestContext.getExeUserId());
		genericDAO.update(entity);
		return Bean.toModel(entity, model);
	}

	@Override
	public M get(Long id) throws Exception {
		return Bean.toModel(genericDAO.findById(id), getModelClass().newInstance());
	}
}

/*
 * $Log: av-env.bat,v $
 */