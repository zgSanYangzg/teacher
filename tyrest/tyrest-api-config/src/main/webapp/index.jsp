<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Tyrest</title>
	<base href="<%=basePath%>">
	<link rel='icon' href='<%=basePath%>favicon.ico' type='image/x-icon'>
	<link rel='shortcut icon' href='<%=basePath%>favicon.ico' type='image/x-icon'>
	<link href='css/font.css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css' />
	<link href='css/highlight.default.css' media='screen' rel='stylesheet' type='text/css' />
	<link href='css/screen.css' media='screen' rel='stylesheet' type='text/css' />
	<script type="text/javascript">var basePath = "<%=basePath%>";</script>
	</head>

	<body>
		<div id='header' style="height:90px;">
			<div class="swagger-ui-wrap">
				<a id="logo" href="#"></a>
				<form id='api_selector'>
					<div class='input'>
						<input placeholder="http://example.com/api" id="input_baseUrl"
							name="baseUrl" type="text" />
					</div>
	
					<div class='input' style="margin-right: 20px;">
						<a id="headers" href="#">Headers</a>
					</div>
					<div class='input'>
						<a id="explore" href="#">Explore</a>
					</div>
				</form>
	
			</div>
	
			<div id="requestHeaderTemp" style="display: none;">
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" type="text" /> <input
						placeholder="Value" class="input_apiVal" type="text" /> <span>-</span>
				</div>
			</div>
	
			<div id="requestHeader">
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="appKey"
						type="text" readonly required /> <input placeholder="Value"
						class="input_apiVal" type="text" required /> <span class="star">*</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey"
						value="transactionID" type="text" readonly required /> <input
						placeholder="Value" class="input_apiVal" type="text" required /> <span
						class="star">*</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="reqTime"
						type="text" readonly required /> <input placeholder="Value"
						class="input_apiVal" type="text" required /> <span class="star">*</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="version"
						type="text" readonly required /> <input placeholder="Value"
						class="input_apiVal" type="text" required /> <span class="star">*</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="Locale"
						type="text" readonly required /> <input placeholder="Value"
						class="input_apiVal" type="text" required /> <span class="star">*</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="sign"
						type="text" /> <input placeholder="Value" class="input_apiVal"
						type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="format"
						type="text" /> <input placeholder="Value" class="input_apiVal"
						type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="accessToken"
						type="text" /> <input placeholder="Value" class="input_apiVal"
						type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="dstSysID"
						type="text" /> <input placeholder="Value" class="input_apiVal"
						type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="tenantId"
						type="text" /> <input placeholder="Value" class="input_apiVal"
						type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey"
						value="acceptRegionCode" type="text" /> <input placeholder="Value"
						class="input_apiVal" type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey"
						value="acceptChannelCode" type="text" /> <input
						placeholder="Value" class="input_apiVal" type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey"
						value="acceptStaffId" type="text" /> <input placeholder="Value"
						class="input_apiVal" type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="acceptOspId"
						type="text" /> <input placeholder="Value" class="input_apiVal"
						type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey"
						value="acceptLoginType" type="text" /> <input placeholder="Value"
						class="input_apiVal" type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="acceptNemId"
						type="text" /> <input placeholder="Value" class="input_apiVal"
						type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey"
						value="acceptLoginStatus" type="text" /> <input
						placeholder="Value" class="input_apiVal" type="text" /> <span>-</span>
				</div>
				<div class='input j-header'>
					<input placeholder="Header" class="input_apiKey" value="notifyFlag"
						type="text" /> <input placeholder="Value" class="input_apiVal"
						type="text" /> <span>-</span>
				</div>
			</div>
	
			<div style="margin-top: 55px; text-align: center;"
				class="swagger-ui-wrap">
				<!-- <button id="showOsesb">Sprint</button>  -->
				<!-- <button id="showBackend">Selfcare</button> -->
			</div>
	
			<div style="width:960px;margin:0px auto;text-align:right;margin-top:20px">
				<select id="tyrest-search-apilevel" style="cursor:pointer;">
					<option value="ALL">ALL</option>
					<option value="PUBLIC">PUBLIC</option>
					<option value="AGENCY">AGENCY</option>
					<option value="SUPERADMIN">SUPERADMIN</option>
				</select>
				<input type="text" id="tyrest-search-text" /> 
				<button id="tyrest-search">搜索</button>
				<button id="tyrest-refresh">刷新</button>
			</div>
		</div>
		<div id="message-bar" class="swagger-ui-wrap"></div>
	
		<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
		<script type="text/javascript" src="lib/shred.bundle.js"></script>
		<script type='text/javascript' src='lib/jquery-1.8.0.min.js'></script>
		<script type='text/javascript' src='lib/jquery.slideto.min.js'></script>
		<script type='text/javascript' src='lib/jquery.wiggle.min.js'></script>
		<script type='text/javascript' src='lib/jquery.ba-bbq.min.js'></script>
		<script type='text/javascript' src='lib/handlebars-1.0.0.js'></script>
		<script type='text/javascript' src='lib/underscore-min.js'></script>
		<script type='text/javascript' src='lib/backbone-min.js'></script>
		<script type='text/javascript' src='lib/swagger.js'></script>
		<script type='text/javascript' src='lib/swagger-client.js'></script>
		<script type='text/javascript' src='lib/swagger-ui.js'></script>
		<script type='text/javascript' src='lib/highlight.7.3.pack.js'></script>
		<!-- enabling this will enable oauth2 implicit scope support -->
		<script type='text/javascript' src='lib/swagger-oauth.js'></script>
		<script type='text/javascript' src='lib/stickUp.min.js'></script>
		<script type="text/javascript" src="lib/tyrest-doc-main.js"></script>
	</body>
</html>