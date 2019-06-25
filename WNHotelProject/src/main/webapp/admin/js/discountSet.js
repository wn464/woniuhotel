var discountType1 = $("#discountType1");
var discountType2 = $("#discountType2");
function getdiscounts(){
	$.ajax({
		url:"/admin/allDiscount",
		type:"get",
		success:function(mes){
			console.info(mes);
			var str = '';
			console.info( new Date(mes[1].beginTime).toLocaleDateString());
			for (var i = 0; i < mes.length; i++) {
				str+="<tr><td>"+mes[i].id+"</td><td>"+mes[i].name+
				"</td><td>"
			}
		}
	})
}

window.onload=function(){
	getdiscountType();
	getdiscounts();
	
}
var discountTableTbody = $("#discountTableTbody");
function getdiscountType(){
	$.ajax({
		url:"/admin/discountType",
		type:"get",
		success:function(mes){
			console.info(mes);
			var str = '';
			for (var i = 0; i < mes.length; i++) {
				
				str+="<option value='"+mes[i].id+"'>"+mes[i].message+"</option>";
			}
			discountType1.html(str);
			discountType2.html(str);
			//discountType2.val(2);
		}
	})
}

