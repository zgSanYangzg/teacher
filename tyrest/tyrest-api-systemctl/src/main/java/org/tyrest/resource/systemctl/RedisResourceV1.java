package org.tyrest.resource.systemctl;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.rest.BaseResources;
import org.tyrest.core.rest.containers.APILevel;
import org.tyrest.core.rest.doc.swagger.annotations.TyrstOperation;
import org.tyrest.core.rest.doc.swagger.annotations.TyrestResource;
import org.tyrest.core.rest.utils.ResponseHelper;
import org.tyrest.core.rest.utils.ResponseModel;


@RestController
@RequestMapping(value = "/1/redis")
@TyrestResource(module = "systemctl",value = "RedisResourceV1", description = "REDIS缓存管理")
public class RedisResourceV1 extends BaseResources
{
	
	public static final String ASTERISK = "*";
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@SuppressWarnings("unchecked")
	@TyrstOperation(name = "deleteRedisByKey", ApiLevel = APILevel.SUPERADMIN,  description = "根据key删除缓存")
	@RequestMapping(value = "/{key}/key", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseModel<String> deleteRedisByKey(
			@PathVariable String key) throws Exception
	{
		redisTemplate.delete(key);
		return ResponseHelper.buildResponseModel(key);
	}
	
	
	@SuppressWarnings({"unchecked" })
	@TyrstOperation(name = "flushDb", ApiLevel = APILevel.SUPERADMIN,  description = "清除当前redisdb的所有缓存")
	@RequestMapping(value = "/flushdb", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseModel<String> flushDb() throws Exception
	{
			redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				 connection.flushDb();
				return null;
			}
		}, true);
		return ResponseHelper.buildResponseModel(MessageConstants.SUCCEED);
	}                                                                                                                                     
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@TyrstOperation(name = "deleteRedisByPrefix", ApiLevel = APILevel.SUPERADMIN,  description = "根据前缀删除该模块全部缓存")
	@RequestMapping(value = "/{prefix}/prefix", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseModel deleteRedisByPrefix(@PathVariable String prefix) throws Exception
	{
		Set matchKeys = redisTemplate.keys(prefix+ASTERISK);
		redisTemplate.delete(matchKeys);
		return ResponseHelper.buildResponseModel(matchKeys);
	}
}
