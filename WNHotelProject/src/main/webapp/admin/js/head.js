var str1 = ""+
"<span class='heading'></span>"+
"<ul class='list-unstyled' >"+
"<li><a href='findRoom.html'> <i class='icon-home'></i>顾客管理</a></li>"+
"<li><a href='findOrder.html'> <i class='fa fa-bar-chart'></i>订单操作</a></li>"+
"<li><a href='/afterstatus/1/4'> <i class='icon-grid'></i>房间管理</a></li>"+
"<li><a href='login.html'> <i class='icon-interface-windows'></i>注销</a></li>"+
"</ul><span class='heading'>管理员</span>"+
"<ul class='list-unstyled'>"+
"<li> <a href='discountSet.html'> <i class='icon-mail'></i>优惠管理</a></li>"+
"<li> <a href='vipSet.html'> <i class='icon-picture'></i>vip管理</a></li>"+
"<li> <a href='roleSet.html'> <i class='icon-interface-windows'></i>权限管理</a></li></ul>";
$(".side-navbar").html(str1);
var str2=""+
"<!-- Item -->"+
"<div class='col-xl-3 col-sm-6'><div class='item d-flex align-items-center'>"+
"<div class='icon bg-violet'><i class='icon-user'></i></div>"+
"<div class='title'>"+
"<a href='findRoom.html'> <span>旅客<br>入住</span></a>"+
"</div></div></div>"+
"<!-- Item -->"+
"<div class='col-xl-3 col-sm-6'><div class='item d-flex align-items-center'>"+
"<div class='icon bg-red'><i class='icon-padnote'></i></div><div class='title'>"+
"<a href='roomManager.html'><span>房间<br>管理</span></a>"+
"</div></div></div>"+
"<!-- Item -->"+
"<div class='col-xl-3 col-sm-6'><div class='item d-flex align-items-center'>"+
"<div class='icon bg-green'><i class='icon-bill'></i>"+
"</div><div class='title'>"+
"<a href='findOrder.html'><span>订单<br>查询</span></a>"+
"</div></div></div>"+
"<!-- Item -->"+
"<div class='col-xl-3 col-sm-6'><div class='item d-flex align-items-center'>"+
"<div class='icon bg-orange'><i class='icon-check'></i>"+
"</div><div class='title'>"+
"<a href='findOrder.html'><span>旅客<br>结账</span></a>"+
"</div></div></div>";
$("#DashboardCountsSection").html(str2);