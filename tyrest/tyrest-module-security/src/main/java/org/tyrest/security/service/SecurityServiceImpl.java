package org.tyrest.security.service;

import org.tyrest.core.cache.Redis;
import org.tyrest.core.foundation.constants.CoreConstants;
import org.tyrest.core.foundation.constants.ErrorCodeConstants;
import org.tyrest.core.foundation.constants.MessageConstants;
import org.tyrest.core.foundation.context.RequestContext;
import org.tyrest.core.foundation.enumeration.UserType;
import org.tyrest.core.foundation.exceptions.DataValidateException;
import org.tyrest.core.foundation.exceptions.UnauthorizedException;
import org.tyrest.core.foundation.utils.Bean;
import org.tyrest.core.foundation.utils.Encrypt;
import org.tyrest.core.foundation.utils.ValidationUtil;
import org.tyrest.core.mysql.BaseServiceImpl;
import org.tyrest.security.face.orm.dao.LoginHistoryDAO;
import org.tyrest.security.face.orm.dao.LoginInfoDAO;
import org.tyrest.security.face.orm.dao.PrincipalDAO;
import org.tyrest.security.face.orm.dao.UserRoleDAO;
import org.tyrest.security.face.constants.SecurityConstants;
import org.tyrest.security.face.orm.entity.*;
import org.tyrest.security.face.enums.AuthType;
import org.tyrest.security.face.enums.IdType;
import org.tyrest.security.face.model.AuthRequestModel;
import org.tyrest.security.face.model.PrincipalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyrest.security.face.service.SecurityService;
import org.tyrest.security.face.service.SmsVerifyService;
import org.tyrest.security.service.authentication.Authenticator;
import org.tyrest.systemctl.face.constants.SystemConstants;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 
 *  freeapis
 *  File: SecurityServiceImpl.java
 * 
 *  freeapis, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: SecurityServiceImpl.java 72642 2009-01-01 20:01:57Z freeapis\wuqiang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年9月12日		wuqiang		Initial.
 *  
 * </pre>
 */
@Service(value = "securityService")
public class SecurityServiceImpl extends BaseServiceImpl<PrincipalModel, Principal> implements SecurityService {

	@Autowired
	private LoginInfoDAO loginInfoDAO;
	
	@Autowired
	private SmsVerifyService smsVerifyService;
	
	@Autowired
	private PrincipalDAO principalDAO;
	
	@Autowired
	private LoginHistoryDAO loginHistoryDAO;
	
	@Autowired
	private Authenticator authenticator;
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	@Override
	public PrincipalModel createSecurityInfoForRegister(IdType idType, UserType userType, AuthRequestModel authModel)
			throws Exception {
		// #1验证参数,判断要注册的loginId是否有效,短信验证码是否有效
		String agencyCode =authModel.getAgency();
		String loginId = authModel.getLoginId();
		String sessionId = RequestContext.getSessionId();
		String identifyingCode = authModel.getIdentifyingCode();
		String password = authModel.getPassword();
		
		if(ValidationUtil.isEmpty(idType)
				|| ValidationUtil.isEmpty(userType)
				|| ValidationUtil.isEmpty(agencyCode)
				|| ValidationUtil.isEmpty(loginId)
				|| ValidationUtil.isEmpty(sessionId)
				|| ValidationUtil.isEmpty(identifyingCode)
				|| ValidationUtil.isEmpty(password)){
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}

		if (!loginInfoDAO.isLoginIdAvailable(CoreConstants.CODE_SUPER_ADMIN, loginId)) {
			throw new DataValidateException(SecurityConstants.MESSAGE_LOGINID_ALREADY_EXIST);
		}

		if (!smsVerifyService.verifyCode(sessionId,loginId,identifyingCode)) {
			throw new DataValidateException(SecurityConstants.MESSAEG_IDENTIFYINGCODE_ERROR);
		}

		// #2添加用户
		PrincipalModel principalModel = new PrincipalModel();
		principalModel.setAgencyCode(agencyCode);
		principalModel.setUserType(userType.getValue());
		principalModel.setPassword(password);
		principalModel.setLoginId(loginId);
		principalModel.setUserId(sequenceGenerator.getNextValue());
		principalModel.setMobile(loginId);
		principalModel = this.createPrincipal(principalModel,idType);
		return beSafe(principalModel);
	}

	@Override
	public PrincipalModel createSecurityInfoIfNotExist(String agencyCode, String loginId, UserType userType,IdType idType) throws Exception {
		LoginInfo currentLoginInfo = loginInfoDAO.findLoginInfoForLogin(agencyCode, loginId, userType, idType);
		if(ValidationUtil.isEmpty(currentLoginInfo)){
			currentLoginInfo = new LoginInfo();
			currentLoginInfo.setSequenceNBR(sequenceGenerator.getNextValue());
			currentLoginInfo.setUserId(currentLoginInfo.getSequenceNBR());
			currentLoginInfo.setLoginId(loginId);
			currentLoginInfo.setIdType(idType.name());
			currentLoginInfo.setAgencyCode(CoreConstants.CODE_SUPER_ADMIN);
			currentLoginInfo.setRecDate(new Date());
			currentLoginInfo.setRecStatus(CoreConstants.COMMON_ACTIVE);
			currentLoginInfo.setRecUserId(CoreConstants.SYSTEM.toString());
			currentLoginInfo.setLockStatus(CoreConstants.COMMON_N);
			loginInfoDAO.insert(currentLoginInfo);
		}
		Principal currentPrincipal = principalDAO.findByUserId(currentLoginInfo.getUserId());
		if(ValidationUtil.isEmpty(currentPrincipal)){
			currentPrincipal = new Principal();
			currentPrincipal.setSequenceNBR(sequenceGenerator.getNextValue());
			currentPrincipal.setUserId(currentLoginInfo.getUserId());
			currentPrincipal.setMobile("");
			currentPrincipal.setAgencyCode(CoreConstants.CODE_SUPER_ADMIN);
			currentPrincipal.setRecDate(new Date());
			currentPrincipal.setRecStatus(CoreConstants.COMMON_ACTIVE);
			currentPrincipal.setRecUserId(CoreConstants.SYSTEM.toString());
			currentPrincipal.setRegisterDate(new Date());
			currentPrincipal.setUserType(UserType.PUBLIC_USER.getValue());
			currentPrincipal.setLockStatus(CoreConstants.COMMON_N);
			currentPrincipal.setSalt(Encrypt.generateSalt(10));
			currentPrincipal.setPassword(Encrypt.md5ForAuth(SecurityConstants.DEFAULT_USER_PASSWORD, currentPrincipal.getSalt()));
			principalDAO.insert(currentPrincipal);
		}
		return beSafe(Bean.toModel(currentPrincipal, new PrincipalModel())).withLoginId(loginId);
	}

	@Override
	public PrincipalModel getSecurityInfo(String agencyCode, String loginId, UserType userType, IdType idType)
			throws Exception {
		LoginInfo currentLoginInfo = loginInfoDAO.findLoginInfoForLogin(agencyCode, loginId, userType, idType);
		if(!ValidationUtil.isEmpty(currentLoginInfo)){
			return Bean.toModel(principalDAO.findByUserId(currentLoginInfo.getUserId()),new PrincipalModel()).withLoginId(loginId);
		}
		return null;
	}

	@Override
	public PrincipalModel createPrincipal(PrincipalModel principalModel,IdType idType)
			throws Exception {
		// #1.添加登录信息
		String lockStatus = ValidationUtil.isEmpty(principalModel.getLockStatus())?CoreConstants.COMMON_N:principalModel.getLockStatus();
		String currentAgencyCode = !ValidationUtil.isEmpty(principalModel.getAgencyCode())
				? principalModel.getAgencyCode() : RequestContext.getAgencyCode();
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setSequenceNBR(sequenceGenerator.getNextValue());
		loginInfo.setUserId(principalModel.getUserId());
		loginInfo.setLoginId(principalModel.getLoginId());
		loginInfo.setIdType(idType.name());
		loginInfo.setAgencyCode(currentAgencyCode);
		loginInfo.setRecDate(new Date());
		loginInfo.setRecStatus(CoreConstants.COMMON_ACTIVE);
		loginInfo.setRecUserId(CoreConstants.SYSTEM.toString());
		loginInfo.setLockStatus(lockStatus);
		loginInfoDAO.insert(loginInfo);
		//#2.添加认证主体信息
		Principal principal = Bean.toPo(principalModel, new Principal());
		principal.setSequenceNBR(sequenceGenerator.getNextValue());
		principal.setUserId(principalModel.getUserId());
		principal.setAgencyCode(currentAgencyCode);
		principal.setRecDate(new Date());
		principal.setRecStatus(CoreConstants.COMMON_ACTIVE);
		principal.setRecUserId(CoreConstants.SYSTEM.toString());
		principal.setRegisterDate(new Date());
		principal.setUserType(principalModel.getUserType());
		principal.setLockStatus(lockStatus);
		principal.setSalt(Encrypt.generateSalt(10));
		principal.setPassword(Encrypt.md5ForAuth(principal.getPassword(), principal.getSalt()));
		principalDAO.insert(principal);
		//#3.添加角色信息
		if(!ValidationUtil.isEmpty(principalModel.getRoleCodes())){
			String[] roleCodes = principalModel.getRoleCodes().split(",");
			saveUserRoles(roleCodes,principalModel.getUserId(),principalModel.getAgencyCode());
		}
		return beSafe(Bean.toModel(principal, principalModel));		
	}

	@Override
	public void deletePrincipal(Long userId) throws Exception {
		Principal currentPrincipal = principalDAO.findByUserId(userId);
		if(!ValidationUtil.isEmpty(currentPrincipal)){
			principalDAO.deleteByUserId(userId);
			userRoleDAO.deleteUserRoles(currentPrincipal.getAgencyCode(), userId);
		}
	}

	@Override
	public PrincipalModel updatePrincipal(PrincipalModel principalModel) throws Exception {
		Principal currentPrincipal = principalDAO.findByUserId(principalModel.getUserId());
		if(ValidationUtil.isEmpty(currentPrincipal)){
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		//#1更新主体的基础信息
		Bean.copyExistPropertis(principalModel,currentPrincipal);
		principalDAO.update(currentPrincipal);
		//#2更新主体的角色信息
		if(!ValidationUtil.isEmpty(principalModel.getRoleCodes())){
			userRoleDAO.deleteUserRoles(currentPrincipal.getAgencyCode(), currentPrincipal.getUserId());
			String[] roleCodes = principalModel.getRoleCodes().split(",");
			saveUserRoles(roleCodes,principalModel.getUserId(),principalModel.getAgencyCode());
		}
		return beSafe(Bean.toModel(currentPrincipal, principalModel));
	}

	/**
	 * 保存员工角色信息
	 * @param roleCodes
	 * @param userId
	 * @param agencyCode
	 * @throws Exception
	 */
	private void saveUserRoles(String[] roleCodes,Long userId,String agencyCode) throws Exception {
		for(String roleCode : roleCodes){
			UserRole userRole = new UserRole();
			userRole.setSequenceNBR(sequenceGenerator.getNextValue());
			userRole.setRecDate(new Date());
			userRole.setRecStatus(CoreConstants.COMMON_ACTIVE);
			userRole.setRecUserId(RequestContext.getExeUserId());
			userRole.setUserId(userId);
			userRole.setRoleCode(roleCode);
			userRole.setAgencyCode(agencyCode);
			userRole.setLockStatus(CoreConstants.COMMON_N);
			userRoleDAO.insert(userRole);
		}
	}
	
	//屏蔽密码属性，让主体向外暴露的更加安全
	private PrincipalModel beSafe(PrincipalModel principalModel){
		principalModel.setPassword(null);
		principalModel.setSalt(null);
		return principalModel;
	}

	@Override
	public PrincipalModel getPrincipal(Long userId) throws Exception {
		return beSafe(Bean.toModel(principalDAO.findByUserId(userId), new PrincipalModel()));
	}
	
	@Override
	public PrincipalModel updateLockStatus(Long userId) throws Exception {
		// #1启用/禁用用户的基础信息
		Principal currentPrincipal = principalDAO.findByUserId(userId);
		if (ValidationUtil.isEmpty(currentPrincipal)) {
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		String lockStatus = CoreConstants.COMMON_Y.equals(currentPrincipal.getLockStatus())
				? CoreConstants.COMMON_N : CoreConstants.COMMON_Y;
		currentPrincipal.setLockStatus(lockStatus);
		principalDAO.update(currentPrincipal);

		// #2.启用/禁用登录信息，一个用户可能对应多个账号信息
		List<LoginInfo> loginInfos = loginInfoDAO.findLoginInfos(currentPrincipal.getAgencyCode(),currentPrincipal.getUserId());
		for (LoginInfo loginInfo : loginInfos) {
			loginInfo.setLockStatus(lockStatus);
			loginInfoDAO.update(loginInfo);
		}
		// #3启用/禁用后清除用户的session
		this.cleanLoginCache(userId);
		return beSafe(Bean.toModel(currentPrincipal, new PrincipalModel()));		
	}
	
	/**
	 * TODO.清除用户的登录缓存,如登录信息,session信息等
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public void cleanLoginCache(Long userId) throws Exception {
		List<LoginHistory> loginHistorys = loginHistoryDAO.findLoginHistorys(userId);

		if (!ValidationUtil.isEmpty(loginHistorys)) {
			for (LoginHistory e : loginHistorys) {
				Redis.remove(SecurityConstants.CACHE_KEY_PREFIX_SESSION, e.getSsoSessionId(), e.getActionByProduct());
			}
		}

		List<LoginInfo> loginInfos = loginInfoDAO.findLoginInfos(CoreConstants.CODE_SUPER_ADMIN, userId);

		if (!ValidationUtil.isEmpty(loginInfos)) {
			for (LoginInfo loginInfo : loginInfos) {
				Redis.removeSingle(LoginInfo.class, loginInfo.getAgencyCode(), loginInfo.getLoginId());
			}
		}
	}

	@Override
	public void updatePassword(Long userId,String password,String oldPassword) throws Exception {
		// 重置员工的密码,要修改该员工对应的所有账号的密码
		Principal principal = principalDAO.findByUserId(userId);
		if (ValidationUtil.isEmpty(principal)) {
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		oldPassword = Encrypt.md5ForAuth(oldPassword, principal.getSalt());
		if(!oldPassword.equals(principal.getPassword())){
			throw new DataValidateException(SecurityConstants.MESSAGE_PASSWORD_ERROR);
		}
		principal.setPassword(Encrypt.md5ForAuth(password, principal.getSalt()));
		principalDAO.update(principal);
		// 清除员工登录缓存
		this.cleanLoginCache(userId);
	}

	@Override
	public void resetPassword(Long userId) throws Exception {
		// 重置公网用户的密码,要重置该用户对应的所有账号的密码
		Principal currentPrincipal = principalDAO.findByUserId(userId);
		if (ValidationUtil.isEmpty(currentPrincipal)) {
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		currentPrincipal.setPassword(Encrypt.md5ForAuth(SecurityConstants.DEFAULT_USER_PASSWORD, currentPrincipal.getSalt()));
		principalDAO.update(currentPrincipal);
		
		// #5清除用户登录缓存
		this.cleanLoginCache(userId);		
	}

	@Override
	public void modifyPassword(AuthRequestModel requiredParams) throws Exception {
		// #1获取参数
		String loginId = requiredParams.getLoginId();
		String sessionId = RequestContext.getSessionId();
		String identifyingCode = requiredParams.getIdentifyingCode();
		String newPassword = requiredParams.getPassword();
		String mobile = requiredParams.getMobile();

		// #2验证参数完整性
		LoginInfo currentLoginInfo = loginInfoDAO.findLoginInfo(CoreConstants.CODE_SUPER_ADMIN, loginId);
		if (ValidationUtil.isEmpty(currentLoginInfo)) {
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		Principal currentPrincipal = principalDAO.findByUserId(currentLoginInfo.getUserId());
		if (ValidationUtil.isEmpty(currentPrincipal) 
				|| ValidationUtil.isEmpty(currentLoginInfo)
				|| ValidationUtil.isEmpty(sessionId)
				|| ValidationUtil.isEmpty(identifyingCode)
				|| ValidationUtil.isEmpty(newPassword)) {
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		// #3验证短信验证码有效性
		if (!smsVerifyService.verifyCode(sessionId,mobile,identifyingCode)) {
			throw new DataValidateException(SecurityConstants.MESSAEG_IDENTIFYINGCODE_ERROR);
		}

		// #4修改密码
		currentPrincipal.setPassword(Encrypt.md5ForAuth(newPassword, currentPrincipal.getSalt()));
		principalDAO.update(currentPrincipal);
		
		// #5清除用户登录缓存
		this.cleanLoginCache(currentPrincipal.getUserId());		
	}

	@Override
	public void modifyMobileNumber(String oldMobile,String newMobile,String sessionId,String identifyingCode,String password) throws Exception {
		// #1获取参数
		Long userId = Long.parseLong(RequestContext.getExeUserId());

		// #2验证参数完整性
		LoginInfo currentLoginInfo = loginInfoDAO.findLoginInfoForLogin(CoreConstants.CODE_SUPER_ADMIN,oldMobile,UserType.PUBLIC_USER,IdType.mobile);
		Principal currentPrincipal = principalDAO.findByUserId(userId);
		if (ValidationUtil.isEmpty(currentPrincipal) 
				|| ValidationUtil.isEmpty(currentLoginInfo)
				|| ValidationUtil.isEmpty(oldMobile)
				|| ValidationUtil.isEmpty(newMobile)
				|| ValidationUtil.isEmpty(sessionId)
				|| ValidationUtil.isEmpty(identifyingCode)
				|| ValidationUtil.isEmpty(password)) {
			throw new DataValidateException(MessageConstants.DATA_VALIDATION_FAILED);
		}
		// #3验证短信验证码有效性
		if (!smsVerifyService.verifyCode(sessionId,newMobile,identifyingCode)) {
			throw new DataValidateException(SecurityConstants.MESSAEG_IDENTIFYINGCODE_ERROR);
		}
		// #4验证密码
		if (!currentPrincipal.getPassword().equals(Encrypt.md5ForAuth(password, currentPrincipal.getSalt()))) {
			throw new DataValidateException(SecurityConstants.MESSAGE_PASSWORD_ERROR);
		}

		// #4判断要修改的手机号是否已经绑定过微信,如果绑定过,则不能修改
		LoginInfo targetLoginInfo = loginInfoDAO.findLoginInfoForLogin(CoreConstants.CODE_SUPER_ADMIN, newMobile, UserType.PUBLIC_USER, IdType.mobile);
		if (!ValidationUtil.isEmpty(targetLoginInfo)) {
			throw new DataValidateException("要修改的手机号已经存在!");
		}

		// #5修改手机号
		// #5-1修改用户详情的手机号
		currentPrincipal.setMobile(newMobile);
		principalDAO.update(currentPrincipal);
		// #5-2添加新的登录信息
		currentLoginInfo.setLoginId(newMobile);
		loginInfoDAO.update(currentLoginInfo);
		// #5-3清除登录session,让用户重新登录
		this.cleanLoginCache(userId);		
	}

	@Override
	public void updateCleanTestData(String userName, String operrationType) throws Exception {
		// 清理指定测试用户数据
		cleanWithLimit(userName, operrationType);
		// 清理所有用户数据
	}

	private void cleanWithLimit(String userName, String operrationType) throws Exception {
		// 设置测试用户使用的商品金额
		if (SystemConstants.testAccounts.containsKey(userName) && !ValidationUtil.isEmpty(userName)) {
			String loginId = SystemConstants.testAccounts.get(userName);

			LoginInfo currentLoginInfo = loginInfoDAO.findLoginInfo(CoreConstants.CODE_SUPER_ADMIN, loginId);

			if (!ValidationUtil.isEmpty(currentLoginInfo)) {
				cleanTestData(currentLoginInfo.getUserId(), operrationType);
			}
		} else {
			throw new DataValidateException("You are bad person.");
		}
	}

	private void cleanTestData(Long userId, String operrationType) throws Exception {
		if (!ValidationUtil.isEmpty(userId)) {
			this.cleanLoginCache(userId);
			Redis.removeSingle(Principal.class, userId.toString());
			if(SecurityConstants.TEST_DATA_OPERATTYPE_DELETE.equals(operrationType)){
				principalDAO.cleanTestData(userId);
			}
		} else {
			throw new DataValidateException("You are bad person.");
		}
	}

	@Override
	public void updateCleanTargetData(String phoneNum, String optionPassword) throws Exception {
		// 清理指定用户数据
		LoginInfo currentLoginInfo = loginInfoDAO.findLoginInfo(CoreConstants.CODE_SUPER_ADMIN, phoneNum);
		if (!ValidationUtil.isEmpty(currentLoginInfo)) {
			cleanTestData(currentLoginInfo.getUserId(), SecurityConstants.TEST_DATA_OPERATTYPE_DELETE);
		}
	}
	
	@Override
	public UserSession getOrRefreshSession(String token, String product)
			throws Exception {
		if (ValidationUtil.isEmpty(token)) {
			throw new UnauthorizedException(SecurityConstants.MESSAGE_TOKEN_REQUIRED);
		}
		if (ValidationUtil.isEmpty(product)) {
			throw new UnauthorizedException(SecurityConstants.MESSAGE_PRODUCT_REQUIRED);
		}

		UserSession session = Redis.get(SecurityConstants.CACHE_KEY_PREFIX_SESSION, token, product);

		// 如果缓存中没有session,则去登录记录中查找该用户是否登陆过,如果登录过,那就刷新缓存,生成新的session
		if (ValidationUtil.isEmpty(session)) {
			session = authenticator.refreshSession(token, product);
			if (!ValidationUtil.isEmpty(session)) {
				Redis.setWithExpire(session, Long.parseLong(session.getSsoSessionExpiration()), SecurityConstants.CACHE_KEY_PREFIX_SESSION, session.getSsoSessionId(),
								session.getActionByProduct() );
				session.setRefresh(true);
			}
		}

		if (ValidationUtil.isEmpty(session)) {
			throw new UnauthorizedException(ErrorCodeConstants.TOKEN_NOT_FOUND_CODE + SecurityConstants.splitor
					+ SecurityConstants.MESSAGE_TOKEN_NOT_FOUND);
		}
		return session;
	}

	public void updateSession(UserSession userSession) throws Exception {
		Redis.setWithExpire(userSession, Long.parseLong(userSession.getSsoSessionExpiration()),
				new String[] { SecurityConstants.CACHE_KEY_PREFIX_SESSION, userSession.getSsoSessionId(),
						userSession.getActionByProduct() });
	}

	public void deleteSession(String sessionId, String product) throws Exception {
		Redis.remove(SecurityConstants.CACHE_KEY_PREFIX_SESSION, sessionId, product);
	}

	@Override
	public UserSession createSession(IdType idType,AuthType authType,UserType userType,AuthRequestModel authModel) throws Exception {
		
		//#1登录认证,认证成功后返回会话信息
		UserSession session = authenticator.authenticate(idType, authType, userType, authModel);
		
		// #2清除旧的session信息
		LoginHistory loginHistory = loginHistoryDAO.getLastLoginFromHistory(session.getLoginId(),
				session.getAgencyCode(), session.getActionByProduct());
		if (!ValidationUtil.isEmpty(loginHistory)) {
			this.deleteSession(loginHistory.getSsoSessionId(), loginHistory.getActionByProduct());
		}
		// #3添加登录记录
		LoginHistory history = new LoginHistory();
		history.setSequenceNBR(sequenceGenerator.getNextValue());
		history.setSsoSessionCreation(session.getSsoSessionCreation());
		history.setSsoSessionExpiration(session.getSsoSessionExpiration());
		history.setSsoSessionStatus(session.getSsoSessionStatus());
		history.setSsoSessionId(session.getSsoSessionId());
		history.setActionByIp(session.getActionByIp());
		history.setActionByProduct(session.getActionByProduct());
		history.setActionByAgent(session.getActionByAgent());
		history.setRecStatus(CoreConstants.COMMON_ACTIVE);
		history.setRecUserId(session.getUserId().toString());
		history.setRecDate(new Date());
		history.setUserId(session.getUserId());
		history.setLoginId(session.getLoginId());
		history.setSsoUserName(session.getLoginId());
		history.setAgencyCode(session.getAgencyCode());
		history.setUserType(session.getUserType());
		loginHistoryDAO.insert(history);
		
		// #3缓存新的session信息
		Redis.setWithExpire(session, Long.parseLong(session.getSsoSessionExpiration()),
				SecurityConstants.CACHE_KEY_PREFIX_SESSION, session.getSsoSessionId(), session.getActionByProduct());
		return session;
	}
}

/*
*$Log: av-env.bat,v $
*/