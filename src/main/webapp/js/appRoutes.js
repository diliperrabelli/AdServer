var appRoutes = (function () {
		console.info('index started............');
		var rp;

		function update()
		{

			var routes = [
			              {path: '/recruiterform',  route: {templateUrl: 'screens/recruiterform.html', title: "recruiterform"}},
			              {path: '/',  route: {templateUrl: 'screens/recruiterform.html'}}
			              ];

			var otherwises = {
					'unknown': {redirectTo: '/'}
			};


			// cleanup
			rp.clear();

			for (var i = 0; i < routes.length; i++) {
				rp.when(routes[i].path, routes[i].route);

			}


		}

		function init(routeProvider) {
			rp = routeProvider;
			update();
		}

		return {'init': init, 'update': update};
	})();