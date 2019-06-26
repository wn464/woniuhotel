if(typeof(WebSocket) == "undefined") {  
        console.log("您的浏览器不支持WebSocket");  
		}
var ws = null;
var url = "ws://"+window.location.hostname+":"+window.location.port+"/websocket";
//连接websocket
ws = new WebSocket(url);
//接受服务器数据
ws.onmessage=function(mes){
	//输出数据
	console.info(mes);
}