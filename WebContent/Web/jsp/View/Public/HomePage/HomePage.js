$(function(){
	$('#teacher_column div ul').children().eq(0).hide();
	$(".artcle_area_tea_pic").mouseenter(function(event){
		var $target = $(event.target);
		if($target.prop("tagName") == "DIV"){
			$target = $($target.children("img")[0]); 
		}
		if($target.prop("tagName") == "IMG"){
			$target.prev().fadeIn(200);
		}
	});
	$(".artcle_area_tea_pic").mouseleave(function(event){
		$(".tea_pic_con").fadeOut(100);
	});
	//师资力量的图片
	$('#teacher_column_last').click(function(){
		var parent = $('#teacher_column div ul');
		if(parent.children().eq(0).is(':visible')){
			parent.children().eq(0).hide();
			parent.children().eq(1).fadeIn();
		}else if(parent.children().eq(1).is(':visible')){
			parent.children().eq(1).hide();
			parent.children().eq(0).fadeIn();
		}
	});
	$('#teacher_column_next').click(function(){
		var parent = $('#teacher_column div ul');
		if(parent.children().eq(0).is(':visible')){
			parent.children().eq(0).hide();
			parent.children().eq(1).fadeIn();
		}else if(parent.children().eq(1).is(':visible')){
			parent.children().eq(1).hide();
			parent.children().eq(0).fadeIn();
		}
	});
	//团队的坑
	$("#team1").mouseenter(function(){
			$("#team1").find("img.team_own_picture").animate({
				width: '200px',
				height: '200px',
				top: '0px',
				left: '0px'
					},100);
			$("#team1").find("img.bubble").fadeOut();
			$("#team1").find("div.team_content").fadeIn();
	});
	$('#team1').mouseleave(function(){
		$("#team1").find("img.bubble").fadeIn();
		$("#team1").find("img.team_own_picture").animate({
			width: '150px',
			height: '150px',
			top: '35px',
			left: '35px'
				},100);
		$("#team1").find("div.team_content").fadeOut();
	});
	$("#team2").mouseenter(function(){
		$("#team2").find("img.team_own_picture").animate({
			width: '200px',
			height: '200px',
			top: '0px',
			left: '0px'
				},100);
		$("#team2").find("img.bubble").fadeOut();
		$("#team2").find("div.team_content").fadeIn(100);
			
	});
	$("#team2").mouseleave(function(){
		$("#team2").find("img.bubble").fadeIn();
		$("#team2").find("img.team_own_picture").animate({
			width: '150px',
			height: '150px',
			top: '30px',
			left: '30px'
				},100);
		$("#team2").find("div.team_content").fadeOut();
	
	});
	$("#team3").mouseenter(function(){
			$("#team3").find("img.team_own_picture").animate({
				width: '200px',
				height: '200px',
				top: '0px',
				left: '0px'
					},100);
			$("#team3").find("img.bubble").fadeOut();
			$("#team3").find("div.team_content").fadeIn();
	
	});
	$("#team3").mouseleave(function(){
			$("#team3").find("img.bubble").fadeIn();
			$("#team3").find("img.team_own_picture").animate({
				width: '150px',
				height: '150px',
				top: '20px',
				left: '12px'
					},100);
			$("#team3").find("div.team_content").fadeOut();
	});
});
//借用了一个插件ZoomPic，但是心好累，有bug，感觉自己在猝死的路上越来越近了。
function ZoomPic()
{
	this.initialize.apply(this, arguments);
}
ZoomPic.prototype = 
{
	initialize : function (id)
	{
		var _this = this;
		// 在ZoomPic的环境下执行
		// ZoomPic添加一个属性为wrap
		this.wrap = typeof id === "string" ? document.getElementById(id) : id;
		//ul
		this.oUl = this.wrap.getElementsByTagName("ul")[0];
		//li
		this.aLi = this.wrap.getElementsByTagName("li");
		//上一个和下一个按钮
		//this.prev = this.wrap.getElementsByTagName("span")[0];
		//this.next = this.wrap.getElementsByTagName("span")[1];
		//时间
		this.timer = 1000;
		//使用数组执行函数
		this.aSort = [];
		//暂时未明白
		this.iCenter = 2;
		//执行“前一个”的函数
		this._doPrev = function () {return _this.doPrev.apply(_this)};
		//执行“后一个”的函数
		this._doNext = function () {return _this.doNext.apply(_this)};
		//
		this.options = [
			{ width: 0, height: 0, left: 100, top: 62, display: 'none'},
			{ zIndex: 1,width: 600, height: 250,  top: 62, left: 0}, //左边一个
			{ zIndex: 2,width: 900, height: 375,  top: 0, left: 62}, //正中一个
			{  zIndex: 1,width: 600, height: 250, top: 62, left: 424}, //右边一个
			{ width: 0, height: 0, top: 62, display: 'none'}
		];
		//局部变量声明完毕，将li放进数组
		for (var i = 0; i < this.aLi.length; i++) this.aSort[i] = this.aLi[i];
		//unshift:在前端添加元素，
		//pop():推出最后一个元素
		//执行函数
		this.aSort.unshift(this.aSort.pop());
		//执行setUp函数
		this.setUp();
		//添加事件
		//this.addEvent(this.prev, "click", this._doPrev);
		//添加事件
		//this.addEvent(this.next, "click", this._doNext);
		//执行函数
		this.doImgClick();		
		//执行timer
		_this.timer = setInterval(function ()
		{
			_this.doNext()	
		}, 7000);	
		//<div id="focus_Box"></div>
		this.wrap.onmouseover = function ()
		{
			clearInterval(_this.timer)	
		};
		//<div id="focus_Box"></div>
		this.wrap.onmouseout = function ()
		{
			_this.timer = setInterval(function ()
			{
				_this.doNext()	
			}, 7000);	
		}
	},
	//转前一个
	doPrev : function ()
	{
		this.aSort.unshift(this.aSort.pop());
		this.setUp()
	},
	doNext : function ()
	{
		this.aSort.push(this.aSort.shift());
		this.setUp()
	},
	doImgClick : function ()
	{
		var _this = this;
		for (var i = 0; i < this.aSort.length; i++)
		{
			this.aSort[i].onclick = function ()
			{
				if (this.index > _this.iCenter)
				{
					for (var i = 0; i < this.index - _this.iCenter; i++) _this.aSort.push(_this.aSort.shift());
					_this.setUp()
				}
				else if(this.index < _this.iCenter)
				{
					for (var i = 0; i < _this.iCenter - this.index; i++) _this.aSort.unshift(_this.aSort.pop());
					_this.setUp()
				}
			}
		}
	},
	setUp : function ()
	{
		var _this = this;
		var i = 0;
		for (i = 0; i < this.aSort.length; i++) this.oUl.appendChild(this.aSort[i]);
		for (i = 0; i < this.aSort.length; i++)
		{
			this.aSort[i].index = i;
			//这个暂时未明白
			if (i < 5)
			{
				//如果i小于5，最后一个显示block
				this.css(this.aSort[i], "display", "block");
				//执行 doMove函数
				//、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
				this.doMove(this.aSort[i], this.options[i], function ()
				{
					_this.doMove(_this.aSort[_this.iCenter].getElementsByTagName("img")[0], {opacity:100}, function ()
					{
						_this.doMove(_this.aSort[_this.iCenter].getElementsByTagName("img")[0], {opacity:100}, function ()
						{
							_this.aSort[_this.iCenter].onmouseover = function ()
							{
							//	_this.doMove(this.getElementsByTagName("div")[0], {bottom:0})
							};
							_this.aSort[_this.iCenter].onmouseout = function ()
							{
							//	_this.doMove(this.getElementsByTagName("div")[0], {bottom:-100})
							}
						})
					})
				});
			}else
			{
				//i == 5的时候
				this.css(this.aSort[i], "display", "none");
				this.css(this.aSort[i], "width", 0);
				this.css(this.aSort[i], "height", 0);
				this.css(this.aSort[i], "top", 37);
				this.css(this.aSort[i], "left", this.oUl.offsetWidth / 2)
			}
			if (i < this.iCenter || i > this.iCenter)
			{
				this.css(this.aSort[i].getElementsByTagName("img")[0], "opacity", 100)
				//onmouseover: 鼠标首次移动进该元素
				this.aSort[i].onmouseover = function ()
				{
					_this.doMove(this.getElementsByTagName("img")[0], {opacity:100})	
				};
				this.aSort[i].onmouseout = function ()
				{
					_this.doMove(this.getElementsByTagName("img")[0], {opacity:100})
				};
				this.aSort[i].onmouseout();
			}
			else
			{
				this.aSort[i].onmouseover = this.aSort[i].onmouseout = null
			}
		}		
	},
	//加载事件的东西
	addEvent : function (oElement, sEventType, fnHandler)
	{
		return oElement.addEventListener ? oElement.addEventListener(sEventType, fnHandler, false) : oElement.attachEvent("on" + sEventType, fnHandler)
	},
	css : function (oElement, attr, value)
	{
		if (arguments.length == 2)
		{
			if(oElement){
				return oElement.currentStyle ? oElement.currentStyle[attr] : getComputedStyle(oElement, null)[attr]
			}
			return ;
			
		}else if (arguments.length == 3)
		{
			if(oElement){
				switch (attr)
				{
					case "width":
					case "height":
					case "top":
					case "left":
					case "bottom":
						oElement.style[attr] = value + "px";
						break;
					case "opacity" :
						oElement.style.filter = "alpha(opacity=" + value + ")";
						oElement.style.opacity = value / 100;
						break;
					default :
						oElement.style[attr] = value;
						break;
				}	
			}
		}
	},
	//在setUp函数里面调用这个函数
	/**
	 * [doMove description]
	 * @param  {[type]} oElement   aSort元素
	 * @param  {[type]} oAttr      属性
	 * @param  {[type]} fnCallBack [description]
	 * @return {[type]}            [description]
	 */
	doMove : function (oElement, oAttr, fnCallBack)
	{
		var _this = this;
		//猜想 输出setUp,
		//清除这个函数的队列
		if(oElement){
			if(oElement.timer){
				clearInterval(oElement.timer);
			}
		}
		//每30秒执行一次
		oElement.timer = setInterval(function ()
		{
			var bStop = true;
			for (var property in oAttr)
			{
				var iCur = parseFloat(_this.css(oElement, property));
				property == "opacity" && (iCur = parseInt(iCur.toFixed(2) * 100));
				var iSpeed = (oAttr[property] - iCur) / 5;
				iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);
				
				if (iCur != oAttr[property])
				{
					bStop = false;
					_this.css(oElement, property, iCur + iSpeed)
				}
			}
			if (bStop)
			{
				clearInterval(oElement.timer);
				fnCallBack && fnCallBack.apply(_this, arguments)	
			}
		}, 30)
	}
};
window.onload = function ()
{
	new ZoomPic("focus_Box");
};
function upToTop(){
	var t = document.documentElement.scrollTop || document.body.scrollTop; 
	window.scrollBy(0,-10);
	scrolldelay = setTimeout('upToTop();',10);
	if( t === 0){
		clearTimeout(scrolldelay);
	}
}
(function(){
window.onscroll = loadScroll;
function loadScroll(){
	var t = document.documentElement.srollTop || document.body.scrollTop;
	if(t >= 300){
		var a = document.getElementById("news_column");
		document.getElementById("fixed_ul").style.display = "block";
		document.getElementById("fixed_ul").style.left = (a.offsetLeft + a.offsetWidth) +"px";
		window.onscroll = reloadScroll;
	}
}
function reloadScroll(){
	var t = document.documentElement.srollTop || document.body.scrollTop;
	if(t <= 300){
		document.getElementById("fixed_ul").style.display = "none";
		window.onscroll = loadScroll;
	}
}
function show(condition, message, time){
	if(condition){
		var target = document.getElementById("prompt_area");
		var parent =  document.getElementById("prompt_message");
		
		target.innerHTML = "<div id='temp_left'></div><div id='temp_right'></div>"+"<p>"+message+"</p>";
		parent.style.display = "block";
		
		target.onclick = function(event){
			event.stopPropagation();
		}
		parent.onclick = function(event){
			disappear();
			event.stopPropagation();
		};
		setTimeout("disappear()", time);
	}
}
function disappear(){
	var parent = document.getElementById("prompt_message");
	parent.style.display = "none";
}



//展示大张图片
document.getElementById("er_wei_ma").onclick =  function(){
	show(true,"<img src='/GDUTHackerSpace/Web/images/temp/er_wei_ma.jpg' alt='创客微信二维码'/>",500000)
};
document.getElementById("to_top").onclick = function(){
	upToTop()
};
}());

