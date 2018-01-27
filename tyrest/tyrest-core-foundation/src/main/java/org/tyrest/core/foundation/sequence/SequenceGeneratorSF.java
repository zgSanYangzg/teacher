package org.tyrest.core.foundation.sequence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tyrest.core.foundation.sequence.snowflake.IdWorker;
import org.tyrest.core.foundation.utils.ValidationUtil;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: SequenceGeneratorSF.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SequenceGeneratorSF.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
@Service(value = "sequenceGenerator")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceGeneratorSF implements SequenceGenerator {

	@Value("${IdWorkerSeq}")
	private String IdWorkerSeq; // 序列生成器唯一id

	private static IdWorker worker = null;

	private synchronized IdWorker getIdWorker() throws Exception {

		if (ValidationUtil.isEmpty(worker)) {
			Integer workId = null;
			try {

				workId = Integer.parseInt(IdWorkerSeq);
			} catch (Exception e) {
				throw new Exception("无法获取主键生成器策略编号,请管理员检查配置.");
			}
			worker = new IdWorker(workId, 0);

		}
		return worker;

	}

	@Override
	public long getNextValue() throws Exception {
		return this.getIdWorker().getNextValue();
	}
}
