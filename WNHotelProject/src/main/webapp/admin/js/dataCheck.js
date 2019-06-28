function checkinput(mes){
	if(!mes.hasClass("form-control")){
		mes.addClass("form-control")
	}
}
//成功后显示
function success(mes){
	checkinput(mes)
	console.info("true")
	mes.removeClass("is-invalid");
	mes.addClass("is-valid");
	if(mes.next().attr("class")=="invalid-feedback"){
		mes.next().remove() ;
	}
}
//失败后显示
function fail(mes,str){
	checkinput(mes)
	console.info("false")
	mes.removeClass("is-valid");
	mes.addClass("is-invalid");
	if(!mes.next().attr("class")=="invalid-feedback"||mes.next().length==0){
		var str1 = "<div class='invalid-feedback'>"+str+"</div>";
		mes.after(str1)
	}
}
//onchange="moneyCheck()"
function moneyCheck(node){
	node = $(node);
	console.info(node)
	var reg = /^[0-9]*\.?[0-9]{0,2}$/;
	reg.test(node.val())?success(node):fail(node,"请输入数字，小数点后不超过两位数");
}
//onchange="phoneCheck()"
function phoneCheck(node){
	node = $(node);
	console.info(node)
//	var reg = /^((\d{3,4}-)|\d{3.4}-)?\d{7,8}$/;
//	reg.test(node.val())?success(node):fail(node,"电话号码有误");
}
//onchange="namecheck()"
function namecheck(node){
	node = $(node);
	console.info(node)
	var reg = /^[\u4E00-\u9FA5A-Za-z0-9]{4,20}$/;
	reg.test(node.val())?success(node):fail(node,"输入字母或者汉字，4个到20个字以内");
}