const http = require('http');
var config = {
	"feature1": false
};
const server = http.createServer(function(request, response) {
	console.log(request.method + ': ' + request.url);
	if (request.url != '/config') {
		return notFound(response);
	}

	if(request.method == 'GET') {
		return getConfig(response);
	} else if(request.method == 'PUT') {
		return putConfig(request, response);
	} else {
		return notFound(response);
	}
	
});

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
