const http = require('http');
var config = {
	"feature1": false
};
const server = http.createServer(function(request, response) {
	console.log(request.method + ': ' + request.url);
	response.setHeader('Access-Control-Allow-Origin', '*');
	response.setHeader('Access-Control-Allow-Methods', 'PUT, POST, GET, OPTIONS');
	if(request.method == 'OPTIONS') {
		response.writeHead(200);
		response.end();
		return
	}

	if (!request.url.match(/\/config.*/)) {
		return notFound(response);
	}

	if(request.method == 'GET') {
		return getConfig(response);
	} else if(request.method == 'PUT') {
		if(request.url == '/config') {
			return putConfig(request, response);	
		} else {
			return putFeature(request, response);
		}
	} else {
		return notFound(response);
	}
	
});
	
function putFeature(request, response) {
	var body = "";
	request.on('data', function(data) {
		body += data;
	});
	request.on('end', function() {
		let isEnabled = JSON.parse(body);
		body = "";

		let feature = request.url.substring(request.url.lastIndexOf("/") + 1);
		config[feature] = isEnabled;
		response.writeHead(200);
		response.end();
	});
}

function notFound(response) {
	response.writeHead(404);
	response.end();
}

function putConfig(request, response) {
	var body = "";
	request.on('data', function(data) {
		body += data;
	});
	request.on('end', function() {
		config = JSON.parse(body);
		body = "";

		console.log(JSON.stringify(config));
		response.writeHead(200);
		response.end();
	});
}

function getConfig(response) {
	response.writeHead(200, {'Content-Type': 'application/json'});
	response.write(JSON.stringify(config));
	response.end();
}

server.listen(8080);
console.log('Server listening on port 8080');
