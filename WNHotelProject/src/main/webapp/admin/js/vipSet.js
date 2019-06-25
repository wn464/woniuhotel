function insert(){
	var insert = $("#insertFrom").serializeArray();
	console.info(insert);
	$.ajax({
		url:"/admin/vip",
		async:true,
		type:"post",
		data:insert,
		error:function(){
			alert("添加失败，请检查是否正确和完整");
		},
		success:function(mes){
			alert("添加成功");
			getVipAll();
		}
	})
}
window.onload=function(){
	
	getVipAll()
}

function getVipAll(){
	var vipTableTbody = $("#vipTableTbody");
	
	$.ajax({
		url:"/admin/vip",
		async: false,
		type:"get",
		success:function(mes){
			console.info(mes);
			var str = '';
			for (var i = 0; i < mes.length; i++) {
				str +="<tr><td>"+mes[i].id+"</td><td>"+mes[i].name+"</td><td>￥"+mes[i].maxMoney+"</td><td>"+mes[i].discount+"</td><td>";
				str+="<a href='javascript:set("+mes[i].id+")' class='badge badge-rounded bg-green' >修改</a>";
				str+="<a href='javascript:del("+mes[i].id+")' class='badge badge-rounded bg-green' >删除</a>";
				str+="</td></tr>";
			}		
			vipTableTbody.html(str)
		}
	})
}
var vipId;
function set(id){
	vipId = id;
	console.info("vip编号为："+id+"的订单");
	$.ajax({
		url:"/admin/vip/"+id,
		async: false,
		type:"get",
		success:function(mes){
			$("#showSetName").html(mes.name);
			$("#setName").val(mes.name);
			$("#setMaxMoney").val(mes.maxMoney);
			$("#setDiscount").val(mes.discount);
		}
	})
	
}
function del(id){
	console.info("删除编号为："+id+"的优惠");
	if(confirm("确定删除？")){
		$.ajax({
			url:"/admin/vip",
			async: false,
			data:"id="+id,
			type:"delete",
			error:function(){
				alert("删除失败");
			},
			success:function(mes){
				if(mes==1){
					alert("删除成功");
					getVipAll();
				}else{ 
					alert("删除失败");
				}
				
			}
		})
	}
}
function update(){
	var update = $("#setFrom").serialize()+"&id="+vipId;
	console.info(update);
	$.ajax({
		url:"/admin/vip",
		async:true,
		type:"put",
		data:update,
		error:function(){
			alert("修改失败，请检查是否正确和完整");
		},
		success:function(mes){
			alert("修改成功");
			getVipAll();
		}
	})
}