package org.tyrest.sysorder.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.GenericDAOImpl;
import org.tyrest.sysorder.face.orm.dao.OrderInfoDAO;
import org.tyrest.sysorder.face.orm.entity.OrderInfo;

/**
 * 
 * <pre>
 * 
 *  File: OrderInfoDAOImpl.java
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  OrderInfoDAOImpl.java  tyrest\magintursh
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月29日					magintursh				   Initial.
 *
 * </pre>
 */
@Repository(value = "orderInfoDAO")
public class OrderInfoDAOImpl extends GenericDAOImpl<OrderInfo> implements OrderInfoDAO {

	@Override
	public OrderInfo findByOrderSn(String agencyCode, String orderSn) throws Exception {
		StringBuffer sql=new StringBuffer();
		Map<String,Object> params=new HashMap<String,Object>();
		if(!ValidationUtil.isEmpty(agencyCode)){
			sql.append(" AND  AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE", agencyCode);
		}
		if(!ValidationUtil.isEmpty(orderSn)){
			sql.append(" AND ORDER_SN = :ORDER_SN ");
			params.put("ORDER_SN", orderSn);
		}
		return this.findFirst(sql.toString(), params);
	}

	@Override
	public OrderInfo findByBillNo(String agencyCode, String billNo) throws Exception {
		StringBuffer sql=new StringBuffer();
		Map<String,Object> params=new HashMap<String,Object>();
		if(!ValidationUtil.isEmpty(agencyCode)){
			sql.append(" AND  AGENCY_CODE = :AGENCY_CODE ");
			params.put("AGENCY_CODE", agencyCode);
		}
	    if(!ValidationUtil.isEmpty(billNo)){
	    	sql.append(" AND BILL_NO = :BILL_NO ");
	    	params.put("BILL_NO", billNo);
	    }
	    return this.findFirst(sql.toString(), params);	
	
	}
	
	/**
	 * 
	 * 查询订单列表
	 *
	 * @param billNo	
	 * @param orderTypes
	 * @param orderStatus
	 * @param payMethod
	 * @param payStatus
	 * @param userId
	 * @param agencyCode
	 * @param source
	 * @return
	 */
	public List<OrderInfo> findForList(String billNo,String [] orderTypes,String []orderStatus,String payMethod,String payStatus,Long userId,String agencyCode,String source)
	{
		return null;
	}
	
	

	
}
