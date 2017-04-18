var api = (function() {
	var timerReset;
	var apiFailed = false;
	$.support.cors = true;

	var _session = null;

	function prepareRequest(method, args) {
		//var session = getSession();
		var url;


		switch (method) {

		case "createCamppaigns":
			return {
			url:   "/rest/ad",
			data: JSON.stringify(args),
			dataType: "json",
			contentType: 'application/json',
			type: "POST"
		};

		case "getCompaigns":
		return {
			url: "/rest/ad/" + args,
			dataType: "json",
			contentType: 'application/json',
			type: "GET"
		};
		
		default:
			alert("Error. Requested method is not implemented.");
		return null;
		}
	}

	


	

	// public api:
	return {
		/**
		 * Execute a server method with parameters
		 *
		 * @param method -
		 *            logical method name
		 * @param args -
		 *            arguments object
		 * @param successCb -
		 *            success callback
		 * @param errorCb -
		 *            error callback e.x.: api.execute("gatewayListGet", {userID:
		 *            123}, function(data){alert(data);}, function(){});
		 */
		execute: function(method, args, successCb, errorCb) {

			// translate method and arguments to jQuery request structure:
			var request = prepareRequest(method, args);
			if (!request)
				return null; // execution might be cancelled or UI redirected for

			var xhr;

			console.info('inside xhr............');
			if (request.url && request.type == "GET") {
				var index = request.url.indexOf('?');
				if (index != -1) {
					request.url = request.url.substring(0, index + 1) + request.url.substring(index + 1, request.url.length);
				} else
					request.url = request.url;
			}

			//Override the MIME type of the request
			request.beforeSend = function(xhr) {
				xhr.overrideMimeType("application/json; charset=UTF-8");
			};

			if (request.data) {
				console.info('inside request data............');
				request.contentType = "application/json; charset=utf-8";

				console.log(JSON.stringify(request));
				request.data = JSON.stringify(JSON.parse(request.data));
			}


			xhr = $.ajax(request);
		
			xhr.done(
					function(data, textStatus, xhr) {

						if (successCb) {
							
							console.info(data.data);
						
							 successCb(data.data, textStatus, xhr);
						}
					});
		}


	};
})();