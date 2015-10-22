define(function(require, exports, module) {
	require('jquery');
	require('vis');
	
	var rtmConnections = [ {
		src : {
			id : 2,
			name : 'storm-master',
			image : 'static/img/topology/storm.png'
		},
		dst : {
			id : 1,
			name : 'rtm',
			image : 'static/img/topology/default.png'
		}
	}, {
		src : {
			id : 3,
			name : 'storm-node-1',
			image : 'static/img/topology/storm.png'
		},
		dst : {
			id : 1,
			name : ' rtm',
			image : 'static/img/topology/default.png'
		}
	}, {
		src : {
			id : 4,
			name : 'storm-node-2',
			image : 'static/img/topology/storm.png'
		},
		dst : {
			id : 1,
			name : ' rtm',
			image : 'static/img/topology/default.png'
		}
	} ];
	
	createGraph(rtmConnections);

	function createGraph(connections) {
		var container = $('.topology-container')[0];
		var edgesArray = [];
		var nodesArray = [];
		var ids = {};
		for (var i = 0; i < connections.length; i++) {
			var con = connections[i];
			edgesArray.push({
				from : con.src.id,
				to : con.dst.id
			});
			if (typeof ids[con.src.id] === 'undefined') {
				nodesArray.push({
					id : con.src.id,
					label : con.src.name,
					shape : 'image',
					image : con.src.image
				});
				ids[con.src.id] = 1;
			}
			if (typeof ids[con.dst.id] === 'undefined') {
				nodesArray.push({
					id : con.dst.id,
					label : con.dst.name,
					shape : 'image',
					image : con.dst.image
				});
				ids[con.dst.id] = 1;
			}
		}
		var nodes = new vis.DataSet(nodesArray);
		var edges = new vis.DataSet(edgesArray);
		var data = {
			nodes : nodes,
			edges : edges
		};
		var options = {
			interaction : {
			// zoomView: false
			},
			clickToUse : true
		};
		var network = new vis.Network(container, data, options);
	}
});