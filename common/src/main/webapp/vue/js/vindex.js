function empty(name) {
    if (name.replace(/\s+/g, "") == "") {
        return true
    }
}

function chiid(name, css) {
    var k = 0;
    for (var i = 0; i < name.childNodes.length; i++) {
        if (name.childNodes[i].innerHTML) {
            if (name.childNodes[i].getAttribute("class") == css) {
                k++;
            }
        }
    }
    return k;
}

function verification(obj) {
    if (empty(obj.titleval)) {
        promptbox("标题不能为空", "promptbox-language");
        return true;
    }
    if (empty(obj.coverpreview)) {
        promptbox("封面不能为空", "promptbox-language");
        return true;
    }
    if (obj.way == "image" && obj.images.length == 0) {
        promptbox("上传图片不能为空", "promptbox-language");
        return true;
    }
    if (obj.way == "text" && empty(obj.textcontent)) {
        promptbox("上传文本不能为空", "promptbox-language");
        return true;
    }
    if (obj.way == "video" && !obj.videopath) {
        promptbox("上传视屏不能为空", "promptbox-language");
        return true;
    }
}

function promptbox(title, css) {
    if (this.setClear) {
        clearTimeout(this.setClear)
    }
    var body = document.getElementsByTagName("body")[0];
    if (chiid(body, css) == 0) {
        var div = document.createElement('div');
        div.setAttribute("class", css);
        div.innerHTML = title;
        body.appendChild(div);
    }
    this.setClear = setTimeout(function() {
        body.removeChild(document.querySelector("." + css));
    }, 3000)
}

function load(state, call) {
    var body = document.getElementsByTagName("body")[0];
    if (state && chiid(body, "u-pageLoad") == 0) {
        var div = document.createElement('div');
        var div2 = document.createElement('div');
        var i = document.createElement('i');
        var p = document.createElement('p');
        div.setAttribute("class", "u-pageLoad");
        i.setAttribute("class", "icon-load");
        p.innerHTML = "正在加载中....";
        div.appendChild(div2);
        div2.appendChild(i);
        div2.appendChild(p);
        body.appendChild(div);
    } else if (!state) {
        body.removeChild(document.querySelector(".u-pageLoad"));
    }

}

if ($.fn.select2) {
    var s2DefaultMatcher = $.fn.select2.defaults.defaults.matcher;
    $.fn.select2.defaults.set("matcher", function(params, data) {
        var result = s2DefaultMatcher(params, data);
        if (result != null) {
            return result;
        }
        var origin = $(data.element).data("search") + ""; // match search
        if (origin && origin != "" && origin.toUpperCase().indexOf(params.term.toUpperCase()) > -1) {
            return data;
        }
        return null;
    });
}

function threeTerminal() {
    var sUserAgent = navigator.userAgent.toLowerCase();
    var ipad = sUserAgent.match(/ipad/i) == "ipad";
    var iphone = sUserAgent.match(/iphone os/i) == "iphone os";
    var android = sUserAgent.match(/android/i) == "android";
    var ce = sUserAgent.match(/windows ce/i) == "windows ce";
    var PC = sUserAgent.match(/windows/i) == "windows";
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    if (ipad || iphone || android || ce || bIsMidp || bIsUc7 || bIsUc || bIsWM) {
        if ($(window).width() > 600) {
            return "ipad";
        } else {
            return "mobile";
        }
    } else {
        return "PC";
    }
}

function Picture(obj){
	//控制
	obj = obj ? obj : {};
	this.elem = obj.elem ? obj.elem : '元素不可用';
	this.cycle = obj.cycle ? obj.cycle : false;
	this.time  = obj.time ? obj.time : 3000;
	this.th = obj.th;
	this.w = window.innerWidth;
	this.l = 0; 
	this.el;
	this.index = 0;
}
var picture = new Picture();
Picture.prototype.Preview = function(obj){
	//预览
	var picture = new Picture(obj);
	var $body = document.getElementsByTagName('body')[0];
    var $ev = document.getElementById(picture.elem);
    var $child = $ev.children;
    picture.index = picture.th.getAttribute('index');
    picture.el = picture.Create($body,'div','preview-frame');
    var $left = picture.Create(picture.el,'div','icon-preview-left');
    var $right = picture.Create(picture.el,'div','icon-preview-right');
    var $close = picture.Create(picture.el,'div','icon-preview-close');
    var $frame = picture.Create(picture.el,'div','frame');
    $frame.style.width = this.w *  $child.length + 'px';
    $frame.style.transform = 'translateX(0px)';
    for(var i= 0;i<$child.length;i++){
    	var $div = picture.Create($frame,'div','image-frame')
    	picture.Create($div,'img','',$child[i].src)
    	$div.style.width = this.w + 'px';
    }
    picture.Dynamic ($frame);
    $right.addEventListener('click',function(){
    	if(Math.abs(picture.l) != ($frame.offsetWidth - picture.w) ){
    		picture.index ++;
    		picture.Dynamic($frame);
    	}
    	
    })
    $left.addEventListener('click',function(){
    	if(Math.abs(picture.l) > 0 ){
    		picture.index --;
    		picture.Dynamic($frame);
    	}
    })
    $close.addEventListener('click',function(){
    	picture.close($body);
    })
}
Picture.prototype.Create = function(Body,El,Class,Src){
	//创建元素
	var div = document.createElement(El);
	if(Class !="" && Class){
		div.setAttribute("class", Class);	
	}
	if(Src !="" && Src){
		div.setAttribute("src", Src);
	}
	Body.appendChild(div);
	return div;
}
Picture.prototype.Dynamic = function(name){
	//运动
	this.l = -(this.w * this.index);
	name.style.transform = 'translateX(' + this.l + 'px)';
}
Picture.prototype.close = function(name){
	//删除
	name.removeChild(this.el);
	this.w = window.innerWidth;
	this.l = 0; 
	this.el;
	this.index = 0;
}