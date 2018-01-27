(function(){
    var url = basePath + "doc/tyrest";
		window.swaggerUi = new SwaggerUi({
			url : url,
			dom_id : "swagger-ui-container",
			language:'ch',
			supportedSubmitMethods : [ 'get', 'post', 'put', 'delete' ],
			onComplete : function(swaggerApi, swaggerUi) {
				log("Loaded SwaggerUI");
				/*if (typeof initOAuth == "function") {
					initOAuth({
						clientId : "your-client-id",
						realm : "your-realms",
						appName : "your-app-name"
					});
				}*/
				$('pre code').each(function(i, e) {
					hljs.highlightBlock(e)
				});
			},
			onFailure : function(data) {
				log("Unable to Load SwaggerUI");
			},
			docExpansion : "none",
			sorter : "alpha"
		});

		function addApiKeyAuthorization() {
			var key = $('#input_apiKey')[0].value;
			log("key: " + key);
			if (key && key.trim() != "") {
				log("added key " + key);
				window.authorizations.add("api_key", new ApiKeyAuthorization(
						"api_key", key, "query"));
			}
		}

		$('#input_apiKey').change(function() {
			addApiKeyAuthorization();
		});

		// if you have an apiKey you would like to pre-populate on the page for demonstration purposes...
		/*
		  var apiKey = "myApiKeyXXXX123456789";
		  $('#input_apiKey').val(apiKey);
		  addApiKeyAuthorization();
		 */

		// -------add request header ----start------
		var requestHeaderTemplate = $('#requestHeaderTemp').html();
		$('#requestHeader').on(
				'focus',
				'.j-header input',
				function() {
					var keyOld = $(this).parent('.j-header').children(
							'input.input_apiKey').val();
					var valOld = $(this).parent('.j-header').children(
							'input.input_apiVal').val();
					$(this).off('change').on(
							'change',
							function() {
								window.authorizations.remove(keyOld);
								var key = $(this).parent('.j-header').children(
										'input.input_apiKey').val();
								var val = $(this).parent('.j-header').children(
										'input.input_apiVal').val();
								if (val && val.trim() != "" && key
										&& val.trim() != "") {
									window.authorizations.add(key,
											new ApiKeyAuthorization(key, val,
													"header"));
								}
							})
				});
		$('#headers').toggle(function() {
			var left = $(this).position().left - 570;
			var top = $(this).position().top + 30;
			$('#requestHeader').css({
				"left" : left,
				"top" : top
			}).fadeIn('fast');
		}, function() {
			$('#requestHeader').hide();
		});

		$('#requestHeader').on(
				'click',
				'.j-header span:not(".star")',
				function() {
					var key = $(this).parent('.j-header').children(
							'input.input_apiKey').val();
					window.authorizations.remove(key);
					$(this).parent('.j-header').remove();

				})

		$('#requestHeader').on(
				'click',
				'.j-header:last-child input.input_apiVal',
				function() {
					$(this).parents('#requestHeader').append(
							requestHeaderTemplate);

				})

		// -------add request header ----end------
				
		$(function($) {
			$(document).ready(function() {
				//enabling stickUp on the '.navbar-wrapper' class
				$('#header').stickUp();
			});
		});
		
		//tyrest searching
		$('#tyrest-search').click(function(){
			var currentSearchText = $('#tyrest-search-text').val();
			var currentApiLevel = $('#tyrest-search-apilevel').val();
			
			/*currentApiLevel === 'ALL' 
			? 
			$("#resources .resource,#resources .resource .endpoint").each(function(index,value){
				$(value).removeClass('filtered');
			})
			:*/
			$("#resources .resource").each(function(index,resource){
				$(resource).find(".endpoint").each(function(index,operation){
					$(operation).hasClass(currentApiLevel) && $(operation).attr('summary').indexOf(currentSearchText) > -1
					? $(operation).removeClass('filtered') : $(operation).addClass('filtered');
				});
				var totalOperationCount = $(resource).find("ul.endpoints > li").length;
				var hiddenOperationCount = $(resource).find("ul.endpoints > li.filtered").length;
				totalOperationCount - hiddenOperationCount === 0 ? $(resource).addClass('filtered') : $(resource).removeClass('filtered');
			});	
		});
		
		//tyrest refresh
		$('#tyrest-refresh').click(function(){
			window.swaggerUi.load();
		});
		
		window.swaggerUi.load();
})();