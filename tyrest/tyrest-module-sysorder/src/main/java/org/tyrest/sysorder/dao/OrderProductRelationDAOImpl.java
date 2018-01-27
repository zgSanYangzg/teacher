package org.tyrest.sysorder.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysorder.face.orm.dao.OrderProductRelationDAO;
import org.tyrest.sysorder.face.orm.entity.OrderProductRelation;
/**
 * 
 * <pre>
 * 
 *  File: OrderProductRelationDAOImpl.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  OrderProductRelationDAOImpl.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
@Repository(value="orderProductRelationDAO")
public class OrderProductRelationDAOImpl extends GenericDAOImpl<OrderProductRelation> implements OrderProductRelationDAO
{

	@Override
	public List<OrderProductRelation> findByOrderSn(String orderSn) throws Exception {
		StringBuffer sql=new StringBuffer();
		Map<String,Object> params=new HashMap<String,Object>();
		if(!ValidationUtil.isEmpty(orderSn)){
			sql.append(" AND ORDER_SN = :ORDER_SN ");
			params.put("ORDER_SN", orderSn);
		}
		return this.find(sql.toString(), params, null, null);
	}


}
