
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
			console.info(message)
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
				"</td><td><img width='50px' src='/images/"+list[i].img+"'>"+
				"</td><td>"+list[i].status.name+
				"</td><td>"+list[i].phone+
				"</td><td>"+list[i].location+
				"</td><td>"+list[i].message+
				"</td><td>"+list[i].area+
				"</td><td>"+list[i].price+
				"</td><td>"+"<a href='javascript:set("+list[i].id+")' class='badge badge-rounded bg-green'>修改</a><a href='javascript:del("+list[i].id+")' class='badge badge-rounded bg-green'>删除</a></td></tr>";
			}
			findTableTbody.html(str);
			setpage();
		}
	})
}
//设置分页
function setpage(){
	$("#page").html(page);
	$("#totalPage").html(totalPage);
	$("#totalNumber").html(totalNumber);
	
}
//删除
function del(id){
	console.info("删除编号为："+id+"的优惠");
	if(confirm("确定删除？")){
		$.ajax({
			url:"admin/room/id",
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
//修改
function set(id){
	setId=id;
	console.info("房间编号为："+id+"的房间");
	$.ajax({
		url:"/admin/room/"+id,
		async: false,
		type:"get",
		error:function(){
			alert("该房间不存在！！！")
		},
		success:function(mes){
			console.info(mes);		
			$("#showSetName").html(mes.name);
			$("#setname").val(mes.name);
			$("#setType").val(mes.type.id);
			$("#setimgname").html("<img width='100%' src='/images/"+mes.img+"'>");
			setimg=mes.img;
			$("#setphone").val(mes.phone);
			$("#setlocation").val(mes.location);
			$("#setmessage").val(mes.message);
			$("#setarea").val(mes.area);
			$("#setprice").val(mes.price);
		}
	})
	setPage();
}
var setId;
var insertimg;
var setimg;
//提交修改
function updata(){
	var updata = $("#updataForm").serializeArray();
	console.info(updata);
	var str = checkRoom(updata);
	if(str==null){
		return;
	}
	$.ajax({
		url:"/updateroom",
		async:true,
		type:"put",
		data:str+"&id="+setId+"&img="+setimg,
		error:function(){
			alert("修改失败，请检查是否正确和完整");
		},
		success:function(mes){
			alert("修改成功");
			jump()
			findPage()
		}
	})
}
//检查数据是否合格
function checkRoom(mes){
var str2 = '';
var str ='1=1';
str += mes[0].value==""?"":"&name="+mes[0].value;
str2 += mes[0].value!=""?"":"name=空\n";
str += mes[1].value==""?"":"&type.id="+mes[1].value;
str2 += mes[1].value!=""?"":"类型=空\n";
str += mes[2].value==""?"":"&phone="+mes[2].value;
str += mes[3].value==""?"":"&location="+mes[3].value;
str += mes[4].value==""?"":"&message="+mes[4].value;
str2 += mes[4].value!=""?"":"描述为空\n";
str += mes[5].value==""?"":"&area="+mes[5].value;
str += mes[6].value==""?"":"&price="+mes[6].value;
str2 += mes[6].value!=""?"":"价格为空\n";
console.info(str);
if(str2!=''){
	alert(str2);
	return "";
}
return str;
}
//提交添加
function insert(){
	var insert = $("#insertForm").serializeArray();
	console.info(insert);
	var str = checkRoom(insert);
	if(str==null){
		return;
	}
	$.ajax({
		url:"/insertroom",
		async:true,
		type:"post",
		data:str+"&img="+insertimg,
		error:function(){
			alert("添加失败，请检查是否正确和完整");
		},
		success:function(mes){
			alert("添加成功");
			jump()
			findPage()
		}
	})
	
}
//加载完毕执行
window.onload=function(){
	//加载分页
	jump();
	//跳转查询
	findPage();
	//获得房间类型
	getTypes();
}
//上一页
function before(){
	if(page==1){
		return;
	}
	page--;
	jump()
}
//下一页
function after(){
	if(page==totalPage){
		return;
	}
	page++;
	jump();
}
//图片上传
function upinsertimg(){
	
	/* var form = new FormData();
	 form.append("imgfile",img);*/
	
	 var formData = new FormData();
	 var img = $("#insertimg")[0].files[0];
	 formData.append("imgfile",img);
	 console.info(formData);
	 $.ajax({
		url:"/upimg",
		type: 'post',
		cache: false,
		data: formData,
		processData: false,
		contentType: false,
		success:function(mes){
			$("#insertimgname").html("<img width='100%' src='/images/"+mes+"'>")
			insertimg=mes;
		}
	 })
}
function upsetimg(){
	
	/* var form = new FormData();
	 form.append("imgfile",img);*/
	
	 var formData = new FormData();
	 var img = $("#setimg")[0].files[0];
	 formData.append("imgfile",img);
	 console.info(formData);
	 $.ajax({
		url:"/upimg",
		type: 'post',
		cache: false,
		data: formData,
		processData: false,
		contentType: false,
		success:function(mes){
			$("#setimgname").html("<img width='100%' src='/images/"+mes+"'>")
			setimg=mes;
		}
	 })
}
//获取类型
function getTypes(){
	$.ajax({
		url:"/typeall",
		type:"get",
		success:function(message){
		console.info(message);
		var str = '';
		for (var i = 0; i < message.length; i++) {
			str+="<option value='"+message[i].id+"'>"+message[i].name+"</option>"
		}
		$("#setType").html(str);
		$("#insertType").html(str);
		}
	})
}