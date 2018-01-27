package org.tyrest.sysorder.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysorder.face.orm.dao.OrderDetailDAO;
import org.tyrest.sysorder.face.orm.entity.OrderDetail;


/** 
 * 
 * <pre>
 *  Tyrest
 *  File: OrderDetailDAOImpl.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: OrderDetailDAOImpl.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
@Repository(value = "orderDetailDAO")
public class OrderDetailDAOImpl extends GenericDAOImpl<OrderDetail>implements OrderDetailDAO
{

	@Override
	public OrderDetail findByOrderSn(String orderSn) throws Exception {
		StringBuffer sql=new StringBuffer();
		Map<String,Object> params=new HashMap<String,Object>();
		if(!ValidationUtil.isEmpty(orderSn)){
			sql.append(" AND ORDER_SN = :ORDER_SN ");
			params.put("ORDER_SN", orderSn);
		}
		return this.findFirst(sql.toString(), params);
	}

}
