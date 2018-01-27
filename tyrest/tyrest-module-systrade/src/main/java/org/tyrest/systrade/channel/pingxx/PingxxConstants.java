package org.tyrest.systrade.channel.pingxx;

/** 
 * 
 * <pre>
 *  Tyrest
 *  File: PingxxConstants.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: PingxxConstants.java  Tyrest\magintrursh $
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月17日		magintrursh		Initial.
 *
 * </pre>
 */
public class PingxxConstants {

		
		
		
		////**************************************************
		///
		//////默认值定义
		///
		////**************************************************

	
		
		/**
		 * 默认币种cny人民币
		 */
		public static final String CNY = "cny";
		
		/**
		 * chargeId
		 */
		public static final String ID = "id";
		
		
		/**
		 * 支付使用的 app 对象的 id。
		 */
		public static final String APP = "app";
		
		
		/**
		 * 订单编号
		 */
		public static final String ORDER_NO = "order_no";
		
		/**
		 * 支付使用的第三方支付渠道
		 */
		public static final String CHANNEL = "channel";
		
		
		/**
		 * 币种：目前支持cny人民币
		 */
		public static final String CURRENCY = "currency";
		
		
		/**
		 * 发起支付请求终端的 IP 地址，格式为 IPV4，如: 127.0.0.1。
		 */
		public static final String CLIENT_IP = "client_ip";
		
		
		/**
		 * 商品的标题，该参数最长为 32 个 Unicode 字符，银联全渠道（upacp/upacp_wap）限制在 32 个字节。
		 */
		public static final String SUBJECT = "subject";
		
		
		/**
		 * 商品的标题，该参数最长为 32 个 Unicode 字符，银联全渠道（upacp/upacp_wap）限制在 32 个字节。
		 */
		public static final String BODY = "body";
		
		
		/**
		 * <pre>
		 * 
		 *  <strong>特定渠道发起交易时需要的额外参数以及部分渠道支付成功返回的额外参数。</strong>
		 * 
		 * <strong>alipay:(支付宝手机支付)。</strong>
		 * 	参数 extern_token[string] 开放平台返回的包含账户信息的 token（授权令牌，商户在一定时间内对支付宝某些服务的访问权限）。
		 * 	通过授权登录后获取的 alipay_open_id，作为该参数的 value，登录授权账户即会为支付账户，32 位字符串，optional；
		 * 	支付完成将额外返回付款用户的支付宝账号 buyer_account 。
		 * 
		 * <strong>wx:(微信支付)。</strong>
		 * 	支付完成将额外返回付款用户的 open_id 和付款银行类型 bank_type。
		 * </pre>
		 */
		public static final String EXTRA = "extra";
		
		
		/**
		 *  选填
		 * 订单失效时间，用 Unix 时间戳表示。时间范围在订单创建后的 1 分钟到 15 天，默认为 1 天，创建时间以 Ping++ 服务器时间为准。 微信对该参数的有效值限制为 2 小时内；银联对该参数的有效值限制为 1 小时内。
		 */
		public static final String TIME_EXPIRE = "time_expire";
		
		
		
		/**
		 * 一些 Ping++ 对象支持加入用户指定的 metadata 参数。你可以使用键值对的形式来构建自己的 metadata，例如 metadata[color] = red，你可以在每一个 charge 对象中加入订单的一些详情，如颜色、型号等属性，在查询时获得更多信息。每一个对象的 metadata 最多可以拥有 10 个键值对，数据总长度在 1000 个 Unicode 字符以内。
		 */
		public static final String METADATA = "metadata";
		
		/**
		 * 订单附加说明，最多 255 个 Unicode 字符。
		 */
		public static final String DESCRIPTION = "description";
		
		
		/**
		 * 交易对象实体类型
		 */
		public static final String OBJECT = "object";
		
		
		/**
		 * integer and > 0
		 * 订单总金额，单位为对应币种的最小货币单位，例如：人民币为分（如订单总金额为 1 元，此处请填 100）。
		 */
		public static final String AMOUNT = "amount";
		
		
		
		
		
		
		public static final String BILLNO = "billNo";
		
		public static final String SERIALNO = "serialNo";
		
		public static final String ORDERSN = "orderSn";

		public static final String USERID = "userId";
		
		public static final String AGENCYCODE = "agencyCode";
		
		public static final String OPENID = "openId";

		public static final String BILL_TYPE_PROCESSOR = "BILL_TYPE_PROCESSOR";

		public static final String BILL_TYPE = "BILL_TYPE";



		/**	第三方交易流水單號*/
		public static final String CHANNEL_SERIAL_NO = "channelSerialNo";


		
		
		
		/*
		 * 企业转账B2C
		 */
		
		/**
		 * 付款类型。 当前仅支持 B2C 企业付款。
		 */
		public  static final String TYPE  = "type";
		public  static final String TYPE_VALUE  = "b2c";
		
		/**
		 * 接收者 id，为用户在 wx(新渠道)、wx_pub 下的 open id 。
		 */
		public  static final String RECIPIENT  = "recipient";


	/**上一天 0 点到 23 点 59 分 59 秒的交易金额和交易量统计，在每日 02:00 点左右触发。*/
	public static  final String  EVENT_SUMMARY_DAILY_AVAILABLE  = "summary.daily.available";

	/**上周一 0 点至上周日 23 点 59 分 59 秒的交易金额和交易量统计，在每周一 02:00 点左右触发。*/
	public static  final String  EVENT_SUMMARY_WEEKLY_AVAILABLE  = "summary.weekly.available";

	/**上月一日 0 点至上月末 23 点 59 分 59 秒的交易金额和交易量统计，在每月一日 02:00 点左右触发。*/
	public static  final String  EVENT_SUMMARY_MONTHLY_AVAILABLE  = "summary.monthly.available";

	/**支付对象，支付成功时触发。*/
	public static  final String  EVENT_CHARGE_SUCCEEDED  = "charge.succeeded";

	/**退款对象，退款成功时触发。*/
	public static  final String  EVENT_REFUND_SUCCEEDED  = "refund.succeeded";

	/**企业支付对象，支付成功时触发。*/
	public static  final String  EVENT_TRANSFER_SUCCEEDED  = "transfer.succeeded";

	/**红包对象，红包发送成功时触发。*/
	public static  final String  EVENT_RED_ENVELOPE_SENT  = "red_envelope.sent";

	/**红包对象，红包接收成功时触发。*/
	public static  final String  EVENT_RED_ENVELOPE_RECEIVED  = "red_envelope.received";

	/**批量企业付款对象，批量企业付款成功时触发。*/
	public static  final String  EVENT_BATCH_TRANSFER_SUCCEEDED  ="batch_transfer.succeeded";




	public static  String PINGXX_APPKEY ="";


}

/*
 * $Log: av-env.bat,v $
 */