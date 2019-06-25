var discountType1 = $("#discountType1");
var discountType2 = $("#discountType2");
var discoountId;
function getdiscounts(){
	$.ajax({
		url:"/admin/allDiscount",
		async: false,
		type:"get",
		success:function(mes){
			console.info(mes);
			var str = '';
			for (var i = 0; i < mes.length; i++) {
				str+="<tr><td>"+mes[i].id+"</td><td>"+mes[i].name+"</td><td>";
				for (var j = 0; j < discountType.length; j++) {
					
					if (discountType[j].id==mes[i].discountType) {
						str+=discountType[j].message;
					}
				}
				str+="</td><td>"+new Date(mes[i].beginTime).toLocaleDateString();
				str+="</td><td>"+new Date(mes[i].endTime).toLocaleDateString();
				str+="</td><td>"+mes[i].number1;
				str+="</td><td>"+mes[i].number2;
				if(mes[i].id!=1){
					str+="</td><td><a href='javascript:set("+mes[i].id+")' class='badge badge-rounded bg-green' >修改</a> <a href='javascript:del("+mes[i].id+")' class='badge badge-rounded bg-green' >删除</a></td></tr>";
				}else{
					str+="</td><td><a href='javascript:set("+mes[i].id+")' class='badge badge-rounded bg-green' >修改</a></td></tr>";
				}
				
			}
			discountTableTbody.html(str);
		}
	})
}
function del(id){
	console.info("删除编号为："+id+"的优惠");
	if(confirm("确定删除？")){
		$.ajax({
			url:"/admin/discount",
			async: false,
			data:"id="+id,
			type:"delete",
			success:function(mes){
				if(mes==1){
					alert("删除成功");
				}else{
					alert("删除失败");
				}
				getdiscounts();
			}
		})
	}
}
function set(id){
	discoountId = id;
	console.info("修改编号为："+id+"的订单");
	$.ajax({
		url:"/admin/dicount/"+id,
		async: false,
		type:"get",
		success:function(mes){
			console.info(mes);
			$("#showSetName").html(mes.name);
			$("#setName").val(mes.name);
			$("#discountType2").val(mes.discountType);
			$("#setBeginTime").val(dateformat(mes.beginTime));
			$("#setEndTime").val(dateformat(mes.endTime));
			$("#setNumber1").val(mes.number1);
			$("#setNumber2").val(mes.number2);
		}
	})
	
}
function sendDateFormat(time){
	var nTime = new Date(time);
	var format = "";
	format += nTime.getFullYear()+"-";
	format += (nTime.getMonth()+1)<10?"0"+(nTime.getMonth()+1):(nTime.getMonth()+1);
	format += "-";
	format += nTime.getDate()<10?"0"+(nTime.getDate()):(nTime.getDate());
	format += " ";
	format += nTime.getHours()<10?"0"+(nTime.getHours()):(nTime.getHours());
	format += ":";
	format += nTime.getMinutes()<10?"0"+(nTime.getMinutes()):(nTime.getMinutes());
	format += ":00";
	return format;
}
function dateformat(time){
	var nTime = new Date(time);
	console.info(nTime);
	var format = "";
	format += nTime.getFullYear()+"-";
	format += (nTime.getMonth()+1)<10?"0"+(nTime.getMonth()+1):(nTime.getMonth()+1);
	format += "-";
	format += nTime.getDate()<10?"0"+(nTime.getDate()):(nTime.getDate());
	format += "T";
	format += nTime.getHours()<10?"0"+(nTime.getHours()):(nTime.getHours());
	format += ":";
	format += nTime.getMinutes()<10?"0"+(nTime.getMinutes()):(nTime.getMinutes());
	format += ":00";
	return format;
}
var discountType;
window.onload=function(){
	getdiscountType();
	getdiscounts();
	
}
var discountTableTbody = $("#discountTableTbody");
function getdiscountType(){
	$.ajax({
		url:"/admin/discountType",
		async:false,
		type:"get",
		success:function(mes){
			discountType = mes;
			console.info(mes);
			var str = '';
			for (var i = 0; i < mes.length; i++) {
				
				str+="<option value='"+mes[i].id+"'>"+mes[i].message+"</option>";
			}
			discountType1.html(str);
			discountType2.html(str);
			
		}
	})
}
function update(){
	var update = $("#setForm").serializeArray();setForm
	var data ='';
	data +="id="+discoountId;
	data +="&name="+update[0].value;
	data +="&discountType="+update[1].value;
	data +="&beginTime="+sendDateFormat(update[2].value);
	data +="&endTime="+sendDateFormat(update[3].value);
	data +="&number1="+update[4].value;
	data +="&number2="+update[5].value;
	$.ajax({
		url:"/admin/discount",
		async:true,
		type:"put",
		data:data,
		error:function(){
			alert("修改失败，请检查是否正确和完整");
		},
		success:function(mes){
			alert("修改成功");
			getdiscounts();
		}
	})
		
}
function insert(){
	var insert = $("#insertForm").serializeArray();
	var data ='';
	data +="name="+insert[0].value;
	data +="&discountType="+insert[1].value;
	data +="&beginTime="+sendDateFormat(insert[2].value);
	data +="&endTime="+sendDateFormat(insert[3].value);
	data +="&number1="+insert[4].value;
	data +="&number2="+insert[5].value;
	$.ajax({
		url:"/admin/discount",
		async:true,
		type:"Post",
		data:data,
		error:function(){
			alert("添加失败，请检查是否正确和完整");
		},
		success:function(mes){
			alert("添加成功");
			getdiscounts();
		}
		
	
	})
}
