
function secondMethod()
{
	alert("function in js file");
}

//计算平方值
function getSquare()
{
	//得到录入的文本
	var s = document.getElementById('txtNumber').value;
	//判断
	if(isNaN(s))
	{
		alert("请输入数字");
	} else {
		//转换为数值类型
		var data = parseInt(s);
		//计算
		alert(data * data);
	}
}


//1累加到20
function getSum()
{
	var data = 0;
	for(var i = 0; i <= 20; i++)
	{
		data += i;
	}
	alert(data);
}

/**
 * replaceString()
 */
function replaceString()
{
	var s = document.getElementById("txtString").value;

	/*使用循环过滤
	var index = s.indexOf("js");

	while(index > -1)
	{
		s = s.replace("js","**");
		index = s.indexOf("js");//最后找不到js就返回-1，循环结束
	}
	*/
	//使用正则过滤,g代表全局，i代表忽略大小写
	s = s.replace(/js/gi,"*");
	document.getElementById("txtString").value = s;
}

/*
数组的翻转和排序
 */
function operater(type)
{
	//得到文本
	var s = document.getElementById("txtArray").value;
	//拆分为数组
	var array = s.split(",");
	//根据类型做操作
	switch(type) 
	{
		case 1:
			array.reverse();
			break;
		case 2:
			array.sort();
			break;
		case 3:
			array.sort(sortFuc);
			break;
	}
	//显示
	alert(array.join("|"));
	//自定义一个比较方法
	function sortFuc(a,b)
	{
		return a - b;
	}
}



/*
数组的翻转和排序
 */
// function operater(type)
// {
// 	//得到文本
// 	var s = document.getElementById("txtArray").value;
// 	//拆分为数组
// 	var array = s.split(",");
// 	//根据类型做操作
// 	switch(type) 
// 	{
// 		case 1:
// 			array.reverse();
// 			break;
// 		case 2:
// 			array.sort();
// 			break;
// 		case 3:
// 			var f = new Function(a,b)
// 				{
// 					return a - b;
// 				}
// 			array.sort(f);
// 			break;
// 	}
// 	//显示
// 	alert(array.join("|"));
// 	//自定义一个比较方法
// 	// function sortFuc(a,b)
// 	// {
// 	// 	return a - b;
// 	// }
// }
/*
得到随机数
 */
function getRandom()
{
	var min = 3;
	var max = 9;
	var seed = Math.random();
	var n = Math.floor(seed * (max - min) + min);
	alert(n);
}
//输入验证
function valiData()
{
	//得到文本
	var s = document.getElementById("txtName").value;
	var reg = /^[a-z]{3,5}$/;
	if(reg.test(s))
		alert("输入正确");
	else
		alert("请输入正确的格式");
}

function valiData1()
{
	//得到文本
	var s = document.getElementById("txtName1").value;
	var reg = /^[\u4e00-\u9fa5]{3}$/;
	if(reg.test(s))
	{	
		alert("输入正确");
		break;
	}
	else
	{
		alert("请输入正确的格式");
		break;
	}
}

function doubleColorBall()
{
	var redBall = new Array();
	var redLen = redBall.length;
	while(redLen<6){
	　var ball = ranNumber(1,33);
	　var flag = true;
	　for(var j=0;j<redLen;j++){
		//排除重复的
	　if(redBall[j]==ball){
	　　　flag = false;
	　　　break;
	　　}
	　}
	　
	　if(flag){
	　　if(ball<10){
	　　　redBall.push("0"+ball);
	　　}else{
	　　　redBall.push(ball);
	　　}
	　}
	　
	　redLen = redBall.length;
	}
	redBall.sort();
	var blueBall = ranNumber(1,16);
	if(blueBall<10){
	　blueBall = "0"+blueBall;
	}
		alert(redBall.join(',') + "|" + blueBall);
}
//得到随机数
function ranNumber(s,e){
　var staVal = parseFloat(s);
　var endVal = parseFloat(e);
　return Math.floor(Math.random()*(endVal-staVal)+staVal);
}

//模拟实现方法的重载，arguments,一个参数相乘，两个参数相加
function testMethod()
{
	if(arguments.length == 1)
	{
		var data = arguments[0];
		data = parseInt(data);
		alert(data * data);
	}
	else if (arguments.length == 2)
	{
		alert(arguments[0] + arguments[1]);
	}
}


//模拟简单计算器
function calculater(s)
{
	if(s == '=')
	{
		document.getElementById("calNum").value = eval(document.getElementById(calNum).value);
	}
	else 
	{
		document.getElementById("calNum").value += s;
	}
}
function testConfig()
{
	window.open("http://www.baidu.com","name","height:300px;width:300px;")
}
//显示时间
function showTime()
{
	var t = new Date();
	document.getElementById("txtTime").value = t.toLocaleTimeString();
}
//启动时钟
var timer;
function startClock()
{
	timer = window.setInterval(showTime,1000);
}
//停止时钟
function stopClock()
{
	window.clearInterval(timer);
}

var timer1;
function wait() {
	timer1 = window.setTimeout("alert('Hello');",5000);
}
function cancelAlert() {
	window.clearTimeout(timer1);
}
//修改节点信息
function testDocument() {
	//修改图片的显示
	var imgObj = document.getElementById("img1");
	imgObj.src = "edit.png";
	//修改P的信息
	var pObj = document.getElementById("p1");
	pObj.style.color = "red";
	pObj.style.backgroundColor = "silver";
	pObj.innerHTML = "new text";
}
//修改Name
function VailName() {
	//得到Name
	var nameObj = document.getElementById("nameTxt").value;
	//验证
	var r = /^[a-z]{3,5}$/;
	//判断
	if(r.test(nameObj)) {
		document.getElementById("nameInfo").className = "success";
	} else {
		document.getElementById("nameInfo").className = "fail";
	}
	//添加返回
	return r.test(nameObj);
}
//修改Age
function VailAge() {
	//得到Age
	var ageObj = document.getElementById("ageTxt").value;
	//验证
	var r = /^[0-9]{2}$/;
	//判断
	if(r.test(ageObj)) {
		document.getElementById("ageInfo").className = "success";
	} else {
		document.getElementById("ageInfo").className = "fail";
	}
	//添加返回
	return r.test(ageObj);
}


function vailDatas() {
	//验证name和age，return true或者false
	var r1 = VailName();
	var r2 = VailAge();
	return r1 && r2;
}

//增加购物数量
function add(btnObj) {
	//得到td
	var tdObj = btnObj.parentNode;
	//得到td的所有子元素，找到那个文本框
	for (var i = 0; i < tdObj.childNodes.length; i++) {
		var childNode = tdObj.childNodes[i];
		if(childNode.nodeName == "INPUT" &&
			childNode.type == "text") {
			//得到旧数据，操作并显示
			var data = parseInt(childNode.value);
			data++;
			childNode.value = data;
		}
	}
}
//减少购物数量
function sub(btnObj) {
	//得到td
	var tdObj = btnObj.parentNode;
	//得到td的所有子元素，找到那个文本框
	for (var i = 0; i < tdObj.childNodes.length; i++) {
		var childNode = tdObj.childNodes[i];
		if(childNode.nodeName == "INPUT" &&
			childNode.type == "text") {
			//得到旧数据，操作并显示
			var data = parseInt(childNode.value);
			if(data > 0) {
				data--;
				childNode.value = data;
			}			
		}
	}
}