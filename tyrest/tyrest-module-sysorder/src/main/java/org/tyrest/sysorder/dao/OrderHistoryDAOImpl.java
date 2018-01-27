package org.tyrest.sysorder.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysorder.face.orm.dao.OrderHistoryDAO;
import org.tyrest.sysorder.face.orm.entity.OrderHistory;
/**
 * 
 * <pre>
 * 
 *  File: OrderHistoryDAOImpl.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  OrderHistoryDAOImpl.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
@Repository(value = "orderInfoHistoryDAO")
public class OrderHistoryDAOImpl extends GenericDAOImpl<OrderHistory>implements OrderHistoryDAO {

	@Override
	public OrderHistory findByOrderSn(String agencyCode, String orderSn) throws Exception {
		StringBuffer sql=new StringBuffer();
		Map<String,Object> params=new HashMap<String,Object>();
		if(!ValidationUtil.isEmpty(agencyCode)){
			sql.append(" AND AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE", agencyCode);
		}
		if(!ValidationUtil.isEmpty(orderSn)){
			sql.append(" AND ORDER_SN = :ORDER_SN ");
			params.put("ORDER_SN", orderSn);
		} 
		return this.findFirst(sql.toString(), params);
	
	}

	
	
}