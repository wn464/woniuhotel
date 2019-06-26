if(typeof(WebSocket) == "undefined") {  
        console.log("您的浏览器不支持WebSocket");  
		}
var ws = null;
var url = "ws://"+window.location.hostname+":"+window.location.port+"/websocket";
//连接websocket
ws = new WebSocket(url);
//接受服务器数据
ws.onmessage=function(message){
	
	//输出数据
	console.info(message);
	play1("你有新订单了");
	neworder();
}
function play1(mes){
    var url = "http://tsn.baidu.com/text2audio?tex="+mes+"&lan=zh&cuid=123456&ctp=1&tok=24.86e2322f84fd6192754a3a07df13f256.2592000.1563772494.282335-16604505";
    var audio = document.createElement('audio');
    var source = document.createElement('source');   
    source.type = "audio/mp3";
    source.type = "audio/mp3";
    source.src = url;   
    source.autoplay = "autoplay";
    source.controls = "controls";
    audio.appendChild(source); 
    audio.play();
}
window.onload=function(){
	$("#notifications").parent().html(str1);
}
var num=0
function neworder(){
	num++;
	str1 =
		"<a id='notifications' rel='nofollow' data-target='#' href='#' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' class='nav-link'>"+
		"<i class='fa fa-bell-o'></i>"+
		"<span class='badge bg-red badge-corner'>"+num+"</span></a>"+
		"<ul aria-labelledby='notifications' class='dropdown-menu'>"+
		"<li><a rel='nofollow' href='appointment.html' class='dropdown-item'> "+
		"<div class='notification'>"+
		"<div class='notification-content'><i class='fa fa-twitter bg-blue'></i>你有"+num+"个新订单</div>"+
		"<div class='notification-time'><small></small></div>"+
		"</div></a></li><li>"+
		"<a rel='nofollow' href='appointment.html' class='dropdown-item all-notifications text-center'> "+
		"<strong>view all notifications</strong></a></li></ul>";
}
var str1 =
	"<a id='notifications' rel='nofollow' data-target='#' href='#' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' class='nav-link'>"+
	"<i class='fa fa-bell-o'></i>"+
//	<span class='badge bg-red badge-corner'>12</span>
	"</a>"+
	"<ul aria-labelledby='notifications' class='dropdown-menu'>"+
	"<li>"+
	"<a rel='nofollow' href='appointment.html' class='dropdown-item all-notifications text-center'> "+
	"<strong>view all notifications</strong></a></li></ul>";