package org.tyrest.publicuser.constants;

/**
 * Created by magintursh on 2016-12-01.
 */
public class AppuserConstants {

    /**用户类型-游客*/
    public static final String APPUSER_TYPE_VISITOR = "visitor";
    /**用户类型-代理*/
    public static final String APPUSER_TYPE_PROXY = "proxy";
    /**用户类型-客服*/
    public static final String APPUSER_TYPE_SERVICE = "service";

    /**代理用户状态-正常*/
    public static final String APPUSER_PROXY_STATUS_NORMAL = "normal";
    /**代理用户状态-移除*/
    public static final String APPUSER_PROXY_STATUS_REMOVAL = "removal";
    /**代理用户状态-暂停接单*/
    public static final String APPUSER_PROXY_STATUS_SUSPEND = "suspend";
    /**代理用户状态-过期*/
    public static final String APPUSER_PROXY_STATUS_EXPIRED = "expired";

    /**普通用户状态-过期*/
    public static final String APPUSER_VISITOR_STATUS_NORMAL = "normal";
    /**普通用户状态-禁止发帖*/
    public static final String APPUSER_VISITOR_STATUS_FORBIDDEN = "forbidden";


    /**私信推送状态-未推送*/
    public static final String MESSAGE_PUSH_STATUS_SUSPEND = "suspend";
    /**私信推送状态-已推送*/
    public static final String MESSAGE_PUSH_STATUS_PUSHED = "pushed";
    /**私信阅读状态-未读*/
    public static final String MESSAGE_READ_STATUS_UNREAD = "unread";
    /**私信阅读状态-已读*/
    public static final String MESSAGE_READ_STATUS_READ = "read";

    /**代理申请状态--待审核*/
    public static final String PROXY_APPLY_STATUS_PENDING = "pending";
    /**代理申请状态--待缴费*/
    public static final String PROXY_APPLY_STATUS_WAIT_PAYMENT = "wait_payment";
    /**代理申请状态--已审批*/
    public static final String PROXY_APPLY_STATUS_APPROVED = "approved";
    /**代理申请状态--被拒绝*/
    public static final String PROXY_APPLY_STATUS_REFUSED = "refused";




    /**用戶管理模塊字典code*/
    public static final String DICT_APPUSER_CONFIG = "APPUSER_CONFIG";

    /**代理續費類型--按年*/
    public static final String DICT_KEY_RENEWALTYPE_YEAR = "RENEWALTYPE_YEAR";
    /**代理續費類型--按月*/
    public static final String DICT_KEY_RENEWALTYPE_MONTH = "RENEWALTYPE_MONTH";


    /**代理编号生成*/
    public static final String DICT_KEY_CREATE_APPUSER_NO = "CREATE_APPUSER_NO";


    /**代理續費類型*/
    public static final String RENEWALTYPE = "RENEWALTYPE";



    /**地区代理列表缓存key前缀*/
    public static  final  String LOCALTION_PROXY = "LOCALTION_PROXY";











}
