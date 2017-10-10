$(document).ready(function(){
   $("body").on("click",function(e){
     if($(e.target).attr("data-Allselect")){
       allSelectd.allCheckbox($(e.target));  //全选函数调用（全选框）
     }if($(e.target).attr("data-Allselect-child")){
       allSelectd.allCheild($(e.target));  //全选函数调用（被全选框）
     }
     if(hisDialog){
       $(hisDialog).hide();
     }
  });
  //dialog弹出对话框事件
  $("body").on("click","[data-popup]",function(e){
     var myData = $(this).attr("data-popup");
     popDialog(myData);
  });
  //显示
  $("body").on("click","[data-show]",function(e){
     var myshow = $(this).attr("data-show");
     xtShow(myshow);
  });
  //隐藏
  $("body").on("click","[data-hide]",function(e){
     var myhide = $(e.target).attr("data-hide");
     hiddenMe(myhide);
  });
   //滑动显示
  $("body").on("click","[data-slideShow]",function(e){
     var myshow = $(this).attr("data-slideShow");
     slideShow(myshow);
  });
  //滑动隐藏
  $("body").on("click","[data-slideHide]",function(e){
     var myhide = $(e.target).attr("data-slideHide");
     slidehidden(myhide);
  });
  //删除
  $("body").on("click","[data-close]",function(e){
     var myClose = $(e.target).attr("data-close");
     closeMe(myClose);
  });

  //输入框事件
  var inputHistory;
  var historyIput;
   $("input[vanish],textarea[vanish]").click(function(e){
     e.stopPropagation(); 
     historyIput = $(this);
     if($(this).attr("placeholder")!=""){
      inputHistory = $(this).attr("placeholder");
     }
     $(this).attr("placeholder","");
   });
   
   $("input[vanish],textarea[vanish]").blur(function(e){
      if($(this).val()==""){
        historyIput.attr("placeholder",inputHistory);
      }
   });

  //table表格全选
  function AllSelectd(){
    this.allCheckbox = function(ev){
      var myChild = $(ev.attr("data-Allselect")).find("input[data-Allselect-child]");
      for(var i=0;i<myChild.length;i++){
        if($(ev).get(0).checked){
          $(myChild).eq(i).get(0).checked = true;
        }else{
          $(myChild).eq(i).get(0).checked = false;
        }
      }
    }
    this.allCheild = function(ev){
      var myAllData = $(ev.attr("data-Allselect-child")).find("input[data-Allselect]");
      var myChild = $(ev.attr("data-Allselect-child")).find("input[data-Allselect-child]");
      for(var i=0;i<myChild.length;i++){
        if($(myChild).eq(i).get(0).checked == false){
           $(myAllData).get(0).checked = false;
           return false;
        }else{
          $(myAllData).get(0).checked = true;
        }
      }
    }
  }
  var allSelectd = new AllSelectd();

  //表格绑定事件
  var hasNub;
   $(".u-table-column>.u-table-fixed>.u-table-fixed-body").scroll(function(){
     var nub = $(this).scrollTop();
     if(hasNub){
        if(Math.abs(nub-hasNub)>0){
          $(this).parents(".u-table-fixed").siblings(".u-table-fixed").children(".u-table-fixed-body").scrollTop(nub)
        }
     }
     hasNub = nub;
   });
   $(".u-table-column").scroll(function(){
      $(this).scrollLeft();
      $(this).children(".u-table-fixed.column-left").css({"left": $(this).scrollLeft()})
   });

});

//纵横表格
//transverseTable（name）id：是.u-table-fixed-body或.u-table上一级的id, 是按照表身宽度来计算表格宽度
function transverseTable(name,num){
  var td = $(name).children(".u-table-fixed-body,.u-table").find("tr").eq(0).children("td");
  var chid = $(td).length;
  var sum = 0;
  if(chid < 30){
    for(var i=0;i<chid;i++){
     sum += parseInt($(td).eq(i).css("max-width"));
    }
  }else{
      //超过30个则按第一个td宽度的倍数来计算
      sum = chid * parseInt($(td).eq(0).css("max-width"));
  }
  $(name).css({"width": sum + (chid * 2) + "px"});

  if($(name).width() > ($(window).width() - num ) ){
     $(name).siblings(".column-left").addClass("active");
   }else{
     $(name).siblings(".column-left").removeClass("active");
  }

}
//transverseTableHead 是按照表头来计算表格宽度
function transverseTable(name,num){
    var td = $(name).children(".u-table-fixed-body,.u-table").find("tr").eq(0).children("td");
    var chid = $(td).length;
    var sum = 0;
    if(chid < 40){
        for(var i=0;i<chid;i++){
            sum += parseInt($(td).eq(i).css("max-width"));
        }
    }else{
        sum = chid * parseInt($(td).eq(0).css("max-width"));
    }
    $(name).css({"width": sum + (chid * 2) + "px"});
    if($(name).width() > ($(window).width() - num ) ){
        $(name).siblings(".column-left").addClass("active");
    }else{
        $(name).siblings(".column-left").removeClass("active");
    }

}


//页面加载事件
//pageLoad（bol）,bol：是传入的false(隐藏),true（显示），
function pageLoad(bol){
    var strLoad = '<div class="u-pageLoad">'+
                      '<div>'+
                          '<i class="icon-load"></i>'+
                          '<p>正在加载中....</p>'+
                      '</div>'+
                   '</div>';
    if(bol){
      $("html").append(strLoad);
    }else{
      if($(".u-pageLoad").length){
        $(".u-pageLoad").remove();
      }
      
    }
}


//页面自适应固定高度事件
//pageLoad（id,nub）,id：是需要高度的元素，nub是需要减去的高度和目标元素的id
function adaptive(name,nub){
  if(isNaN(nub)){
    $(name).height($(window).height()-$(nub).height());
  }else{
    $(name).height($(window).height()-parseInt(nub));
  }
} 

//页面自适应最大高度事件
//MaxAdaptive（id,nub）,id：是需要高度的元素，nub是需要减去的高度和目标元素的id
function MaxAdaptive(name,nub){
  if(isNaN(nub)){
    $(name).css({"max-height":$(window).height()-$(nub).height()});
  }else{
    $(name).css({"max-height":$(window).height()-parseInt(nub)});
  }
}


//以父元素进行定位
//dynamicDialog（ancestor,theName,ev,con）
//ancestor属性是当前父元素，可以是id,class,标签，自定义属性。
//theName属性是当前被控制的元素，可以是id,class,标签，自定义属性。
//ev是当天点击的位置属性
//con是否判断上，下，左，右，自适应内容。
var hisDialog;
function dynamicDialog(ancestor,theName,ev,con){
   if(hisDialog){
      $(hisDialog).hide();
   }
   $(theName).show();
   var location = $(ev).position();
   var l = location.left +"px";
   var t = location.top + $(ev).height() + 10 +"px";
   if(con){
     if($(ancestor).height()-(location.top - $(ancestor).scrollTop() + $(ev).height() + 10) < $(theName).height()){
       t = location.top - $(theName).height() + "px";
     }
     var w = $(ancestor).width()-(location.left - $(ancestor).scrollLeft());
     if( w  < $(theName).width()){
        var l = location.left - ($(theName).width() - w) +"px";
     }
   }
   
  $(theName).css({"left":l,"top":t});
  hisDialog = theName;
}

//dialog弹出事件popDialog(name)弹出框
function popDialog(name){
    clearTimeout(myPopup);
    var chid = $(name).children("div");
    $(chid).children(".u-dialog-content").css('max-height',($(window).height()-120));
    $(name).fadeIn(200);
    if($(chid).attr("fullScreen")!=""){
      var t = $(chid).height()/2;
      var l = $(chid).width()/2;
      $(chid).css({"margin-top":-t,"margin-left":-l});
    }
    var myPopup = setTimeout(function(){
        $(chid).fadeIn(200);
    },100);
}

//通知提示框
//text提示文字
//state提示状态
//ms显示的时间
var setTime;
function funNotice (text,state,ms){
   var str = '<div class="u-notice" id="warningId">'+text+'</div>';
   var time = 3000;
   clearTimeout(setTime);
   ms ? time = ms : time = time;
   if($("body").find(".u-notice").length == 0){
     $("body").append(str);
     if(state){
       var cas = "icon-" + state;
       $(".u-notice").addClass(cas);
     }
   }
   var l =  50 - ($(".u-notice").width() / $(window).width() * 100) / 2 ;
   console.log(l)
   $(".u-notice").css('left', l + "%" );
   setTime = setTimeout(function(){
      $(".u-notice").remove();
   },time);
}


function closeMe(ev){
   $(ev).remove();
}
function hiddenMe(ev){
   $(ev).hide();
}
var histoShow;
function xtShow(ev){
  if(histoShow){
    $(histoShow).hide();
  }
  $(ev).show();
  histoShow = ev;
}
var histoSlideShow;
function slideShow(ev){
  if(histoSlideShow){
    $(histoShow).slideUp();
  }
   $(ev).slideDown();
   histoSlideShow = ev;
}
function slidehidden(ev){
   $(ev).slideUp()
}
function othHide(){
  var blan1 = $(event.target).parents(".othHide").hasClass("othHide");
  var blan2 = $(event.target).hasClass("othHide");
  if(!$(event.target).attr("data-show") && (!blan1 && !blan2) ){
      $(".othHide").hide();
  }
  // return;
}


//进入全屏
function fullScreen(){
    var docElm = document.documentElement;
    //W3C   
    if (docElm.requestFullscreen) {
        docElm.requestFullscreen();
    }
    //FireFox   
    else if (docElm.mozRequestFullScreen) {
        docElm.mozRequestFullScreen();
    }
    //Chrome等   
    else if (docElm.webkitRequestFullScreen) {
        docElm.webkitRequestFullScreen();
    }
    //IE11   
    else if (elem.msRequestFullscreen) {
        elem.msRequestFullscreen();
    }
}
//退出全屏
function outFullScreen() {
    if (document.exitFullscreen) {
        document.exitFullscreen();
    } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
    } else if (document.webkitCancelFullScreen) {
        document.webkitCancelFullScreen();
    } else if (document.msExitFullscreen) {
        document.msExitFullscreen();
    }
}
document.addEventListener("fullscreenchange", function() {
    fullscreenState.innerHTML = (document.fullscreen) ? "" : "not ";
}, false);
document.addEventListener("mozfullscreenchange", function() {
    fullscreenState.innerHTML = (document.mozFullScreen) ? "" : "not ";
}, false);
document.addEventListener("msfullscreenchange", function() {
    fullscreenState.innerHTML = (document.msFullscreenElement) ? "" : "not ";
}, false);

// 文字间距
function spanCol(){
    var san = $("span[col]");
    for(var i=0;i<san.length;i++){
      var col = $(san).eq(i).attr("col");
      $(san).eq(i).css('width',col + "px");
    }
}
spanCol();

//手风琴
var histo;
function Accordion(ev){
    var ban = false;
    var par =  $(ev).parents(".u-accordion[data-accordion]");
    var chld = $(par).children("ul");
    if($(par).attr("histo") == "" && histo && $(histo).html() != $(chld).html()){
        $(histo).slideUp();
        $(histo).parent(".u-accordion").attr('data-accordion',false);
      }
    $(par).attr('data-accordion') == "false" ? ban = true :  ban = false;
    $(par).attr('data-accordion',ban);
    $(chld).slideToggle(300);
    histo = chld;
}

//侧导航栏nav(未确定)
function setUpSkin() {
    event.stopPropagation();
    var skin = $(".u-side-nav").attr("data-skin");
    if (isEmpty(skin)) {
        skin = "black";
    }
    var str = '<div class="u-skin-module" id="inSkin">'+
        '<div>选择皮肤</div>'+
        '<div class="u-skin-color">'+
        '<div class="black" onclick="inSkin(this)"></div>'+
        '<div class="white" onclick="inSkin(this)"></div>'+
        '<div class="grey" onclick="inSkin(this)"></div>'+
        '<div class="green" onclick="inSkin(this)"></div>'+
        '<div class="red" onclick="inSkin(this)"></div>'+
        '<div class="blue" onclick="inSkin(this)"></div>'+
        '</div>'+
        '<div class="mt-28">'+
        '<button type="button" fill data-slideHide="#inSkin">取消</button>'+
        '<button type="button" fill class="u-btn-blue ml-10" onclick="saveSkin()">确定</button>'+
        '</div>'+
        '</div>';
    if(!$("body").children(".u-skin-module").get(0)){
        $("body").append(str);
        $("#inSkin .u-skin-color ." + skin).addClass("icon-active");
        $(".u-skin-module").slideDown();
    }else{
        $(".u-skin-module").slideDown();
    }
}

function inSkin(ev){
    var skinColor = $(".u-side-nav").attr("data-skin");
    if(ev){
        var color = $(ev).prop("class");
        if(!$(ev).hasClass("icon-active")){
            $(".u-side-nav").attr("data-skin",color);
        }
        $(".u-skin-color div").removeClass("icon-active");
        $(ev).addClass("icon-active");
    }else{
        $(".u-skin-color").children("."+skinColor).addClass("icon-active");
    }
}

function Navigation(name){
  if($(event.target).hasClass("u-nav-head")){
    if($(name).width()<100){
      $(name).css({"width":"172px"});
      $("#allContent").css({"padding-left":"172px"})
    }else{
      $(name).css({"width":"48px"});
      $("#allContent").css({"padding-left":"48px"})
    }
    return;
  }
  if($(event.target).attr("submenu")==""){
    $(event.target).siblings("ul").slideToggle(300);
    $(event.target).toggleClass("navUnfold");
  }else if(!$(event.target).prop("class")){
    $(name).find("li,div").removeClass("active");
    $(event.target).addClass("active");
  }
}
$(document).click(function(){
    var ban1 = $(event.target).parents(".u-skin-module").hasClass("u-skin-module");
    var ban2 = $(event.target).hasClass("u-skin-module");
    if(!ban1 && !ban2){
        $(".u-skin-module").slideUp();
    }
    othHide();
})

// input搜索提示下拉框插件
jQuery.fn.fastLiveFilter = function(list, options) {
  // Options: input, list, timeout, callback
  options = options || {};
  list = jQuery(list);
  var input = this;
  var lastFilter = '';
  var timeout = options.timeout || 0;
  var callback = options.callback || function() {};
  
  var keyTimeout;

  var lis = list.children();
  var len = lis.length;
  var oldDisplay = len > 0 ? lis[0].style.display : "block";
  callback(len); // 对初始化进行一次性回调以确保所有内容都是同步的
  
  input.change(function() {
    // var startTime = new Date().getTime();
    var filter = input.val().toLowerCase();
    var li, innerText;
    var numShown = 0;
    for (var i = 0; i < len; i++) {
      li = lis[i];
      innerText = !options.selector ? 
        (li.textContent || li.innerText || "") : 
        $(li).find(options.selector).text();
      
      if (innerText.toLowerCase().indexOf(filter) >= 0) {
        if (li.style.display == "none") {
          li.style.display = oldDisplay;
        }
        numShown++;
      } else {
        if (li.style.display != "none") {
          li.style.display = "none";
        }
      }
    }
    callback(numShown);
    // var endTime = new Date().getTime();
    // console.log('Search for ' + filter + ' took: ' + (endTime - startTime) + ' (' + numShown + ' results)');
    return false;
  }).keydown(function() {
    clearTimeout(keyTimeout);
    keyTimeout = setTimeout(function() {
      if( input.val() === lastFilter ) return;
      lastFilter = input.val();
      input.change();
    }, timeout);
  });
  return this; // maintain jQuery chainability
}

//input文本输入框
jQuery.fn.inputSelect = function(data, eachCall,clickCall) {
    var name = this;
    var str = "<ul>";
    var da;
    data ? da = data : da = []; // 初始化
    var childIpt = $(name).children("input");
    var child = $(name).children("ul");
    $(document).unbind("click"); // 删除documnetclick事件
    if (!child.length) {
        for ( var i in da) {
            var item = da[i];
            if (!eachCall) {
                str += '<li ><span>'+ item.name +'</span><i>'+ item.pinyin +'</i></li>';
            } else {
                str += eachCall(item);
            }
        }
        str += "</ul>";
        $(name).append(str);
        $(childIpt).fastLiveFilter($(name).children("ul")); // 插件函数
    }
    child = $(name).children("ul");
    $(child).show();
    name.one('click', 'li', function() { // 给li绑定函数
        $(childIpt).val($(this).children("span").text());
        if (clickCall) {
            // 点击回调函数
            clickCall(childIpt, this);
        }
        $(child).hide();
        return false;
    });
    $(document).click(function() { // 给document绑定函数
        if (!$(event.target).parents(".u-select-input").length) {
            $(child).hide();
        }
    });
    return this;
}

// 三端判断
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
    if(ipad || iphone || android || ce || bIsMidp || bIsUc7 || bIsUc || bIsWM) {
        if($(window).width() > 600){
           return "ipad";
        }else{
           return "mobile";
        }
    }else{
        return "PC";
    }
}
//移动端适配
function resize_view(pw) {
    var phoneWidth = parseInt(window.screen.width);
    var phoneScale = phoneWidth / pw;

    var ua = navigator.userAgent;
    if (/Android (\d+\.\d+)/.test(ua)) {
        var version = parseFloat(RegExp.$1);
        // andriod 2.3
        if (version > 2.3) {
            $("head").append('<meta name="viewport" content="width='+pw+',user-scalable=no">');
            // minimum-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ',
            // andriod 2.3以上
        } else {
            $("head").append('<meta name="viewport" content="width='+pw+',user-scalable=no">');
        }
        // 其他系统 user-scalable=no,
    } else {
        $("head").append('<meta name="viewport" content="width='+pw+',user-scalable=no">');
        // minimum-scale = ' + phoneScale + ',maximum-scale = ' + phoneScale + ',
    }
}

//H5视屏
function Video(evn){
  var video = $(name).children("video");
  var name = "'" + evn + "'";
  var str = '<div class="u-change-video" onmousemove="progressbarMove('+ name +')" >'+
                '<img src="" alt="">'+
                '<div class="u-video-stop">'+
                    '<i class="icon-caret-right"></i>'+
                '</div>'+
                '<div class="u-video-interface">'+
                    '<div class="u-video-progressBar" onclick="clickProgressBar('+ name +')">'+
                        '<div>'+
                        '</div>'+
                    '</div>'+
                    '<div class="u-video-point" data-press="false" onmousedown="progressbarPress('+ name +')" ></div>'+
                    '<div class="u-video-allControls">'+
                        '<div class="u-video-controls" onclick="toggleControls('+ name +')">'+
                           '<i class="icon-caret-right"></i>'+
                        '</div>'+
                        '<div class="u-video-time">'+
                            '<span>00:00</span> / <span>00:00</span>'+
                        '</div>'+
                        '<div class="u-video-magnify" onclick="allScreen('+ name +')"><i class="icon-full-screen"></i></div>'+
                        '<div class="u-video-voice" onmousemove="voiceMove('+ name +')" >'+
                            '<span class="icon-volume"></span>'+
                            '<div class="u-voice-progressBar" onclick="clickMe('+ name +')" >'+
                                '<div></div>'+
                                '<div class="u-voice-point" data-press="false" onmousedown="progressbarPress('+ name +')" ></div>'+
                            '</div>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
            '</div>'
  $(evn).append(str);
  $(evn).mouseup(function(e){
      e.stopPropagation()
      $(evn).find(".u-video-point").attr('data-press',false);
      $(evn).find(".u-voice-point").attr('data-press',false);
  });
}

//毫秒转换为时间格式
function videoTime(num){
    var s = Math.round(num);
    var ss;
    if(s<10){
       ss = "00:0" + s;
    }else if(s >= 10 && s < 60){
       ss = "00:" + s;
    }else if(s >= 60 && s< 600){
       ss = "0"+ parseInt(s/60) +":";
       if((s % 60) < 10){
          ss = ss +"0"+s % 60;
       }else{
          ss = ss + s % 60;
       }
    }else if(s>=600){
        ss =  parseInt(s/60) + ":";
        if((s % 60) < 10){
            ss = ss +"0"+s % 60;
        }else{
            ss = ss + s % 60;
        }
    }
   return ss;
   
}

//暂停与开始
function toggleControls(name,e){
  window.event? window.event.cancelBubble = true : e.stopPropagation();  //js组织冒泡
  var video = $(name).children("video");
  if(video[0].paused){
     video[0].play();
     $(name).find(".u-video-controls>i").prop('class','icon-start');
     $(name).find(".u-video-stop").hide();
  }else{
    video[0].pause();
    $(name).find(".u-video-controls>i").prop('class','icon-caret-right');
    $(name).find(".u-video-stop").show();
  }
  video.on('timeupdate', function() {
      VideoProgressBar(name);
  })
  return false;
}

//播放进度条位置
function VideoProgressBar(name){
   var video = $(name).children("video");
   var maxduration  = video[0].duration;
   var currentPos = video[0].currentTime;
   var percentage  = 100 * currentPos / maxduration;
   $(name).find(".u-video-time").children("span:first").text(videoTime(currentPos));
   $(name).find(".u-video-time").children("span:last").text(videoTime(maxduration));
   $(name).find(".u-video-progressBar>div").css('width',(percentage - 0.6) +"%");
   $(name).find(".u-video-point").css('left',(percentage - 0.6) + "%");
}

// 点击声音
function clickMe(name){
  var video = $(name).children("video");
  var eWidth = event.pageX - ($(name).find('.u-voice-progressBar').offset().left);
  if(eWidth>=0 && eWidth <=100){
     video[0].volume = eWidth / 100;
     $(name).find(".u-voice-progressBar>div:first").css('width',(eWidth - 5) +"%");
     $(name).find(".u-voice-progressBar>.u-voice-point").css('left',(eWidth - 5) +"%");
  }
  
}
 //点击进度条
function clickProgressBar (name,e){
    window.event? window.event.cancelBubble = true : e.stopPropagation();  //js组织冒泡
    var video = $(name).children("video");
    var maxduration  = video[0].duration;
    var eWidth = event.pageX - $(name).offset().left;
    video[0].currentTime = eWidth / ($(name).width() / maxduration);
    VideoProgressBar(name);
}

// 全屏
function allScreen(name){
  if(!$(name).attr('allScreen')){
    $(name).attr('allScreen','allScreen');
    fullScreen();
  }else{
    $(name).removeAttr('allScreen');
    outFullScreen();
  }
}
//按下进度条
function progressbarPress(name,e){
  window.event? window.event.cancelBubble = true : e.stopPropagation();  //js组织冒泡
    $(event.target).attr('data-press',true);
}
//判断此时进度条是否按下
function progressbarMove(name){
  if($(name).find(".u-video-point").attr('data-press') == "true"){
     clickProgressBar(name);
  }
}
//按下音频进度条
function voiceMove(name){
  if($(name).find(".u-voice-point").attr('data-press') == "true"){
     clickMe(name);
  }
}
function toggleStop(myId){
  var id = "#" + $(myId).parent('.u-video').attr("id");
  toggleControls(id)
}

//移动端滑动事件
var slider = function(ev,conSlider) {
    console.log(ev)
    var param = {
        startX: 0,
        finishX: 0,
        startY: 0,
        finishY: 0,
        distance: 0
    }
    ev.addEventListener("touchstart",function(){
        param.startX = event.targetTouches[0].pageX;
        param.startY = event.targetTouches[0].pageY;
    });
    ev.addEventListener("touchmove",function(){
        param.finishX = event.targetTouches[0].pageX;
        param.finishY = event.targetTouches[0].pageY;
    });
    ev.addEventListener("touchend",function(){
        param.distance = Math.abs(param.startY - param.finishY);
        if(param.startX < param.finishX && param.finishX != 0  && param.distance < 100){
            conSlider('right',this);
        }else if(param.startX > param.finishX && param.finishX != 0  && param.distance < 100){
            conSlider('left',this);
        }
        param.startX = 0;
        param.finishX = 0;
        param.startY = 0;
        param.startX = 0;
        param.distance = 0;
    });
    
}

//图片浏览参数
function pictureBrowse(){
    var allImg = $("img[browse]");
    var imgMe = {
        imgSrc: [],
        index: 0,
        left: 0,
        top: 0,
        currentX: 0,
        currentY: 0,
        imgWidth: 0,
        imgHeight: 0,
        imgSize: 1,
        flag: false,
        stipu: true,
        leftArrow : function(da,parId){
          if(da.index > 0){
              da.index --;
          }else if(da.index == 0){
              da.index = da.imgSrc.length - 1;
          }
          parId.src = da.imgSrc[da.index];
        },
        rightArrow : function(da,parId){
          if(da.index < da.imgSrc.length - 1){
            da.index ++;
          }else if(da.index == da.imgSrc.length - 1){
              da.index = 0;
          }
          parId.src = da.imgSrc[da.index];
        },
        imgZoom : function(da,parId){
          $(parId).width(da.imgWidth * da.imgSize);
          $(parId).height(da.imgHeight * da.imgSize);
          
          if(da.imgSize!=1){
            $(parId).addClass("active");  
          }else{
            $(parId).removeClass("active");  
          }
        }
    }
    for (var i=0; i < allImg.length; i++){
       allImg[i].addEventListener('click',function(e){
           imgBrowse(imgMe,$(this).index());
       },false);
       imgMe.imgSrc.push(allImg[i].src);
    }
    
}
//图片浏览事件
var imgBrowse = function(da,index){
    da.index = index;
    var str = '<div class="u-pictureshade" id="pictureShade">'+
                 '<span class="u-imgBack-close" id="backClose"></span>'+
                 '<span class="icon-leftArrow" id="leftArrow"></span>'+
                 '<span class="icon-rightArrow" id="rightArrow"></span>'+
                 '<img src="'+ da.imgSrc[da.index] +'" id="imgId" draggable=false>'
              '</div>';
    $("body").append(str);
    // fullScreen();
    var parId = document.getElementById("imgId");
    var parClass = document.getElementById("backClose");
    var leftId = document.getElementById("leftArrow");
    var rightId = document.getElementById("rightArrow");
    da.imgWidth = parId.offsetWidth;
    da.imgHeight = parId.offsetHeight;
    parId.onmousedown = function(e) {
        da.flag = true;
        da.currentX = e.clientX - $(e.target).offset().left;
        da.currentY = e.clientY - $(e.target).offset().top;
    }
    parId.ondblclick = function(e) {
        $(parId).css({"left": "auto" ,"top": "auto"});
        if(da.imgSize == 1){
           da.imgSize = 2.2;
        }else{
           da.imgSize = 1;
        }
        da.imgZoom(da,parId);
    } 
    parClass.onclick = function() {
        da.left = 0;
        da.top = 0;
        da.currentX = 0;
        da.currentY = 0;
        da.imgWidth = 0;
        da.imgHeight = 0;
        da.imgSize = 1;
        $(".u-pictureshade").remove();
        // outFullScreen()
    }
    leftId.onclick = function() {
        da.leftArrow(da,parId);
    }
    rightId.onclick = function() {
       da.rightArrow(da,parId);
    }
    document.onmouseup = function() {
        da.flag = false;
    }
    document.onmousemove = function(e) {
        if(da.flag){
            da.left = e.clientX - da.currentX;
            da.top = e.clientY - da.currentY;
            $(parId).css({"left": da.left + "px","top": da.top + "px"});
        }
    }
    parId.onmousewheel = function(e) {
        if(event.deltaY < 1 && da.imgSize < 2){
            da.imgSize += 0.2;
        }else if(da.imgSize > 0.4 && event.deltaY > 1){
            da.imgSize -= 0.2;
        }
        da.imgZoom(da,parId);
    }
    slider(pictureShade,function(orien,ev){
       if(da.stipu){
          switch (orien) {
            case "left":
              da.leftArrow(da,parId);
              break;
            case "right":
              da.rightArrow(da,parId);
              break;
            default:
              console.log("出错");
              break;
          }
       }
    });
    mobileDouble(parId,function(ev){
        if(da.imgSize == 1){
           da.imgSize = 2.2;
           da.stipu = false;
        }else{
           da.imgSize = 1;
           da.stipu = true;
        }
        da.imgZoom(da,parId);
    });
}

//判断移动端双击事件
var mobileDouble = function(ev,MDouble) {
    var clickMobile = {
      timeA : "",
      timeB : "",
      clicked: 0
    }
    ev.addEventListener("touchstart",function(){
       clickMobile.clicked ++;
       if(clickMobile.clicked == 1){
          clickMobile.timeA = new Date().getTime();
       }else if(clickMobile.clicked == 2){
          clickMobile.timeB = new Date().getTime();
          clickMobile.clicked = 0;
       }
       if(Math.abs(clickMobile.timeB - clickMobile.timeA) < 400){
           MDouble(this)
       }
    })

}

function dynamicMenu() {
  var spans = $(".u-head-text").find("span");
  var w = 100 / spans.length;
  $(spans).css('width', w + '%');
  for(var i=0;i<spans.length;i++){
    if(!$(spans[i]).children("i").length){
       $(spans[i]).css('padding-right','6px');
    }
  }
}
dynamicMenu()

function textareaAdaption() {
    let dome = $(".u-textarea-adaption");
    for(let i = 0;i<dome.length;i++){
        if($(dome[0]).children().length == 1){
            $(dome).append('<pre></pre>');
        }
    }
    $('.u-textarea-adaption>textarea').bind('keyup',function () {
        $(this).scrollTop(0);
        $(this).siblings("pre").html($(this).val());
    });
}
textareaAdaption();