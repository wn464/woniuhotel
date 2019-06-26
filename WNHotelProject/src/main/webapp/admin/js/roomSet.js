
function addPage(){
	$("#insert").show();
	$("#set").hide();
	$("#find").hide();
}
function findPage(){
	$("#insert").hide();
	$("#set").hide();
	$("#find").show();
}
function setPage(){
	$("#insert").hide();
	$("#set").show();
	$("#find").hide();
}
var page=1;
var size=10;
var totalNumber;
var totalPage;
function jump(){
	$.ajax({
		url:"/afterroomalltwo/"+page+"/"+size,
		type:"get",
		success:function(message){
			page=message.page;
			size=message.size;
			totalNumber=message.totalNumber;
			totalPage=message.totalPage;
			var str = '';
			var list = message.list;
			var findTableTbody = $("#findTableTbody");
			for (var i = 0; i < list.length; i++) {
				str+="<tr><td>"+list[i].id+
				"</td><td>"+list[i].name+
				"</td><td>"+list[i].type.name+
				"</td><td>"+list[i].img+
				"</td><td>"+list[i].status.name+
				"</td><td>"+list[i].phone+
				"</td><td>"+list[i].location+
				"</td><td>"+list[i].message+
				"</td><td>"+list[i].area+
				"</td><td>"+list[i].price+
				"</td><td>"+"<a herf='javascript:set("+list[i].id+")' class='badge badge-rounded bg-green'>修改</a><a herf='javascript:del("+list[i].id+")' class='badge badge-rounded bg-green'>删除</a></td></tr>";
			}
			findTableTbody.html(str);
			
		}
	})
}
function setpage(){
	
}
function del(id){
	
}
function set(id){
	
}
window.onload=function(){
	jump();
}
function before(){
	if(page==1){
		return;
	}
	page--;
	jump()
}
function after(){
	if(page==totalPage){
		return;
	}
	page++;
	jump();
}