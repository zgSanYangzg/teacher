(function(win, $) {
	var queryModel = {
		start : 0,
		length : 20
	}, queryUrl = "logquery.do";

	function initDataBinding() {
		var logManageViewModel = function() {
			this.logList = ko.observable([]);
			
			this.searchKeyMapping = ko.observable({});
			
			this.logEntities = ko.observable([]);
			
			this.searchLog = function(){
				$("#changableSearch input").each(function(){
					if($.trim($(this).val())){
						queryModel[$(this).attr("id")] = $(this).val();
					}
				});
				doSearchLog();
			}
			
			this.showLogContent = function(data,event){
				$("#detailModal #detail-parameters").html(library.json.prettyPrint(data.parameters));
				$("#detailModal #detail-logContent").html(library.json.prettyPrint(data.logContent));
				$("#detailModal").modal();
			}
			
			this.showTime = function(recDate){
				return date('Y-m-d H:i:s',parseFloat(recDate));
			}
		}
		dataModel = new logManageViewModel();
		ko.applyBindings(dataModel, document.getElementById("logManage"));
	}
	
	function initSearchArea(){
		$.restAjax({
			url : "initquery.do",
			type : "GET",
			async : false,
			success : function(data) {
				dataModel.searchKeyMapping(data.result.searchKeyMapping);
				var logEntities = [];
				for(item in data.result.searchKeyMapping){
					logEntities.push(item);
				}
				dataModel.logEntities(logEntities);
				changeSearchView(logEntities[0])
			}
		});
	}

	//生成请求的url,带参数
	function generateUrl() {
		var search = '';
		for (filed in queryModel) {
			if (search !== '') {
				search += "&";
			} else {
				search += "?";
			}
			search += filed + "=" + queryModel[filed];
		}
		return queryUrl + search;
	}

	function initLogList() {
		$.restAjax({
			url : generateUrl(),
			type : "GET",
			async : false,
			success : function(data) {
				dataModel.logList(data.result.list);
				initPagination(data);
			}
		});
	}

	//初始化分页插件
	function initPagination(data) {
		$('#page-more').bootstrapPaginator({
			currentPage : data.result.currentPage,
			numberOfPages : data.result.pageRecorders,
			totalPages : data.result.totalPages,
			onPageClicked : function(e, originalEvent, type, page) {
				queryModel.start = (page - 1) * queryModel.length;
				$.restAjax({
					url : generateUrl(),
					type : "get",
					async : false,
					success : function(data) {
						dataModel.logList(data.result.list);
					}
				});
			}
		});
	}

	function atachEvent() {
		$("#logEntities").change(function(e){
			changeSearchView($(this).val());
		});
	}
	
	function changeSearchView(currentLogEntity){
		queryModel.type = currentLogEntity;
		var input = dataModel.searchKeyMapping()[currentLogEntity];
		var inputs = [];
		console.log(input);
		for(id in input){
			inputs.push(input[id] + "<input type='text' id='"+ id +"'>");
		}
		$("#changableSearch").html(inputs.join("&nbsp;&nbsp;"));
	}

	function init() {
		initDataBinding();
		initSearchArea();
		initLogList();
		atachEvent();
	}

	function doSearchLog() {
		queryModel.start = 0;
		queryModel.length = 20;
		$.restAjax({
			url : generateUrl(),
			type : "GET",
			async : false,
			success : function(data) {
				dataModel.logList(data.result.list);
				initPagination(data);
			}
		});
		for(var i = 1; i <= 6; i++){
			delete queryModel['searchKey' + i];
		}
	}
	
	init();
}(window, jQuery));