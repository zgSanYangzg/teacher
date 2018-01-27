package org.tyrest.core.foundation.standard;

import java.util.List;

/**
 * 价格相关处理顶级接口
 * @author Administrator
 *
 */
public interface ProductPrice {

	/**
	 * 计算商品价格
	 * @param products
	 * @return
	 */
	Double getProductsPrice(List<StandardProduct> products);
	
	
	
}
