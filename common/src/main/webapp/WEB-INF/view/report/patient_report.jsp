<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../common/head_standard.jsp"%> 
    <title>患者信息统计</title>
</head>

<body style="padding-top: 0px;">
<jsp:include page="../common/report_datepick_new2.jsp" flush="true"></jsp:include>

<div class="xtt">
    <div class="bed-head pb-4 mt-18" style="border-bottom: 0px !important;" id="reportDateDiv">
         <div class="bb-line">
            <div class="tab-head-text tab-leftsetting"  id="span_tab">
                 <span id ="ageStatistic" data-value="1" class="active" onclick="showTab(this,1)">年龄段统计</span>
                <span id="sexStatistic" data-value="3" onclick="showTab(this,3)">性别统计</span>
                <span id="medicalStatistic" data-value="4" onclick="showTab(this,4)">医保信息</span>
            </div>
            <div class="tablesetting u-float-r">
                <button type="button"  class="open-show" data-show="#promptDialog">报表设置</button>
            </div>
            <div class="tablesetting u-float-r mr-10">
                <button type="button" class="" id="downloadPatientCensus">报表下载</button>
            </div>
            <c:if test="${reportType == 1}">
            <div class="tablesetting u-float-r mr-10">
                <button type="button" class="" id="downLoadPatientList">名单下载</button>
            </div>
            </c:if>
        </div>
    </div>
    
    <!-- 年龄统计 start-->
    <div id="div_age">
        <div class="table-bar">
            <label class="u-checkbox u-float-r" style="position: relative; top: 16px; margin-right: 34px; z-index: 10;"> <input
                    type="checkbox" name="checkAge" id="showNumAge" checked> <span class="icon-checkbox"></span> 显示数据
            </label>
            <div id="chartAge" style="top: -18px; min-height: 260px; height: 260px;"></div>
        </div>
        <div class="patientlist pl-12-i">平均年龄：<span class="fs-16" id="avgAge"></span></div>
        <div class="ml-12 mr-14 mb-8 mt-10">
            <table class="u-table u-table-bordered">
                <thead>
                <tr>
                    <th style="width: 35%">年龄段</th>
                    <th style="width: 35%">人数</th>
                    <th style="width: 30%;">占比</th>
                </tr>
                </thead>
                <tbody id="tableBodyAge">
                
                </tbody>
            </table>
        </div>
        </div>
<!-- 年龄统计 end-->

<!-- 性别统计start -->
    <div id="div_sex">
         <div class="table-bar">
                <label class="u-checkbox u-float-r" style="position: relative; top: 12px; margin-right: 34px; z-index: 10;"> <input
                        type="checkbox" name="checkSex" id="showNumSex" checked> <span class="icon-checkbox"></span> 显示数据
                </label>
                <div id="chartSex" style="top: -18px; min-height: 260px; height: 260px;"></div>
            </div>
            <div class="ml-12 mr-14 mb-8 mt-10">
                <table class="u-table u-table-bordered">
                    <thead>
                    <tr>
                        <th style="width: 35%">性别</th>
                        <th style="width: 35%">人数</th>
                        <th style="width: 30%;">占比</th>
                    </tr>
                    </thead>
                    <tbody id="tableBodySex">
                    </tbody>
                </table>
            </div>
        </div>
 
<!-- 性别统计end -->
    <!-- 医保信息统计 start -->
    <div id="div_medical">
        <div class="table-bar">
            <label class="u-checkbox u-float-r" style="position: relative; top: 12px; margin-right: 34px; z-index: 10;"> <input
                type="checkbox" name="checkMedical" id="showNumMedical" checked> <span class="icon-checkbox"></span> 显示数据
            </label>
            <div id="chartMedical" style="top: -18px; min-height: 260px; height: 260px;"></div>
        </div>
        <div class="ml-12 mr-14 mb-8 mt-10">
            <div class="u-table-fixed ">
                <div class="u-table-fixed-head">
                    <table class="u-table u-table-bordered">
                        <thead>
                        <tr>
                            <th style="width: 30%">透析号</th>
                            <th style="width: 30%">姓名</th>
                            <th style="width: 40%">费用类型</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="u-table-fixed-body " id="main">
                    <table class="u-table u-table-bordered">
                        <tbody id="tableBodyMedical">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
  </div>  
    <!-- 医保信息统计 end -->
</div>
    <input type="hidden" id="reportType" value="${reportType }">
</div>
<script src="${ctx }/framework/echarts/3.2.3/echarts_3.2.3_min.js"></script>
<script type="text/javascript" src="${ctx}/framework/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/framework/multiselect/js/bootstrap-multiselect.js"></script>
<script type="text/javascript">
    var isShow =true; //是否展示echart详情
    var mydata;//数据 为echart展示用
    var titleData;//标题
    var reportType=$("#reportType").val(); //1年龄段2透析龄3性别4医保信息
    $(function () {
            if(reportType == '1'){//年龄段
                $("#div_age").show();
                $("#div_dialysis_age").hide();
                $("#div_sex").hide();
                $("#div_medical").hide();
                tabColor(reportType);
                setReportDatePick($("#reportDateDiv"), {
                    dateType: 'nothing',
                    patientTemp: true,
                    ageInterval:true,
                    callback: function () {
                        if(!isEmpty(validaNumber(reportType,getAgeInterval()))){
                            showAlertAutoClose(validaNumber(reportType,getAgeInterval()));
                            return false;
                        }
                       countHistoryByDateType(reportType);
                    }
                });
                countHistoryByDateType(reportType);
            }
            if(reportType == '3'){ //性别
                $("#div_dialysis_age").hide();
                $("#div_sex").show();
                $("#div_age").hide();
                $("#div_medical").hide();
                tabColor(reportType);
                   setReportDatePick($("#reportDateDiv"), {
                       dateType: 'nothing',
                       patientTemp: true,
                       callback: function () {
                        countHistoryByDateType(reportType);
                       }
                   });  
                   countHistoryByDateType(reportType);
               }

         if(reportType == '4'){//医保信息
             var chageArray = [];
             var chageJson =""; 
             <c:forEach items="${chargeTypes}" var="result">
             chageJson= {
                "value":"${result.value}",
                "name":"${result.name}"
             };
             chageArray.push(chageJson);
            </c:forEach>
            countHistoryByDateType(reportType); 
             $("#div_dialysis_age").hide();
             $("#div_sex").hide();
             $("#div_age").hide();
             $("#div_medical").show();
                tabColor(reportType);
                setReportDatePick($("#reportDateDiv"), {
                    dateType: 'nothing',
                    chargTypeValue:chageArray,
                    patientTemp: true,
                    showPatientName:true,
                    showMedicalType:true,
                    callback: function () {
                      countHistoryByDateType(reportType);
                    }
                });  
                countHistoryByDateType(reportType);
            }
         
         $("#showNumAge").change(function () {
             if ($(this).is(':checked')) {
                 isShow= true;
                 setAgeOption(isShow,mydata);
             } else {
                 isShow= false;
                 setAgeOption(isShow,mydata);
             }
         });
        
         $("#showNumSex").change(function () {
             if ($(this).is(':checked')) {
                 isShow= true;
                 setSexOption(isShow,mydata);
             } else {
                 isShow= false;
                 setSexOption(isShow,mydata);
             }
         });
         
         $("#showNumDialysisAge").change(function () {
             if ($(this).is(':checked')) {
                 isShow= true;
                 setDialysisAgeOption(isShow,mydata);
             } else {
                 isShow= false;
                 setDialysisAgeOption(isShow,mydata);
             }
         });
         
         $("#showNumMedical").change(function () {
             if ($(this).is(':checked')) {
                 isShow= true;
                 setMedicalOption(isShow,mydata,titleData);
             } else {
                 isShow= false;
                 setMedicalOption(isShow,mydata,titleData);
             }
         });
    }); 
    
 // 关闭提示框
    function closetip(ev) {
        $(ev).hide();
    }
    
    /**点击tab显示不同类型下的报表数据*/
    function showTab(dom,type){
        if(type == 1){//年龄统计
            dateType = 1;
            skipPatientReport(type);
        }
        if(type == 3){//性别统计
            dateType = 3;
            skipPatientReport(type); 
        }
        if(type == 4){//医保信息统计
            dateType = 4;
            skipPatientReport(type);
        }
    }

    /*调转患者信息统计页面*/
    function skipPatientReport(type){
        window.location.href=ctx + "/report/patient/view.shtml?reportType="+type; 
    }
    
    /*点击tab 显示对应的背景色*/
    function tabColor(reportType){
        $("#span_tab").find("span[data-value='"+reportType+"'] ").addClass("active").siblings().removeClass("active");  
    }
    //ajax 异步获取所需数据
  function countHistoryByDateType(type) {
    $.ajax({
        url: ctx + "/report/patient/getReportData.shtml",
        type: "post",
        data: getMethod(type),
        dataType: "json",
        success: function (data) {
                    if(type == 1){
                        setAgeOption(isShow,data.ageRangeList);
                        showTable("tableBodyAge", data.ageRangeList,type);
                        $("#avgAge").html(data.avgAge);
                        mydata = data.ageRangeList;
                    }
                    if(type == 3){
                        setSexOption(isShow,data.sexList);
                        showTable("tableBodySex", data.sexList,type);
                        mydata = data.sexList;
                    }
                    if(type == 4){
                        setMedicalOption(isShow,data.medicalList,data.medicalTitleList);
                        showTable("tableBodyMedical", data.medicalList,type);
                        mydata = data.medicalList;
                        titleData = data.medicalTitleList;
                    }
         }
    });
}
    
    /**参数组装传入后台*/
    function getMethod(type){
        var method = "";
        if(type == 1){//年龄统计参数
            method="patientReportType="+type+"&ageRange=" + getAgeInterval()+getPatientTempValue();
        }
        if(type == 3){
            method="patientReportType="+type+getPatientTempValue();
        }
        if(type == 4){//医保信息
            method = "patientReportType="+type+"&patientName="+getPatientName()+getMedicalType()+getPatientTempValue();
        }
        return method;
    }
  
    /**验证年龄间隔是否为正整数*/
  function validaNumber(reportType,numberValue){
      var r = /^[0-9]*[1-9][0-9]*$/;
      var message = "";
      if(reportType == 1){
              if(isEmpty(numberValue) || !r.test(numberValue)){
                  message = "格式不正确！";
          }
      }
      return message;
  }
  
  //展示表格数据
  function showTable(id, data,type) {
      var bodyHtml = "";
      var total = 0;
      for (var i = 0; i < data.length; i++) {
          total += parseInt(data[i].value);
      }
      if(type == 1 || type == 3){
          for (var i = 0; i < data.length; i++) {
              var item = data[i];
              bodyHtml += "<tr class='table-default'>";
              bodyHtml += "<td>" + item.name + "</td>";
              bodyHtml += "<td>" + item.value + "</td>";
              bodyHtml += "<td>" + (parseInt(item.value) / total).toPercent() + "</td>";
              bodyHtml += "</tr>";
          }
          if (data.length > 0) {
              bodyHtml += "<tr class='table-default'>";
              bodyHtml += "<td style='color:#31AAFF !important;'>合计</td>";
              bodyHtml += "<td style='color:#31AAFF !important;'>" + total + "</td>";
              bodyHtml += "<td style='color:#31AAFF !important;'>100%</td>";
              bodyHtml += "</tr>";
          }
      }else{
          for (var i = 0; i < data.length; i++) {
              var item = data[i];
              bodyHtml += "<tr>";
              bodyHtml += "<td style='width: 30%'>" + convertEmpty(item.serialNum) + "</td>";
              bodyHtml += "<td style='width: 30%'>" + item.patientName + "</td>";
              bodyHtml += "<td style='width: 40%'>" + convertEmpty(item.chargeName) + "</td>";
              bodyHtml += "</tr>";
          }
          if (data.length > 0) {
              bodyHtml += "<tr class='table-default'>";
              bodyHtml += "<td>合计</td>";
              bodyHtml += "<td colspan='2' >" + data.length + "人</td>";
              bodyHtml += "</tr>";
          }
      }
      $("#" + id).html(bodyHtml);
  }
  
//组装饼图年龄段数据
  function setAgeOption(isShow,pieData) {
      if (isEmpty(pieData))
          return;
      var mychartAge = echarts.init(document.getElementById('chartAge'));
      var pieTitle = new Array();
      for (var i = 0; i < pieData.length; i++) {
          pieTitle.push(pieData[i].name);
      }
      var option = {
          title: {
              text: '',
              subtext: ''
          },
          tooltip: {
              trigger: 'item',
              formatter: "{b} : {c}人 ({d}%)"
          },
          toolbox: {
              show: true,
              feature: {
                  saveAsImage: {
                      show: true
                  }
              }
          },
          legend: {
              orient: 'vertical',
              x: '65%',
              y: 'center',
              data: pieTitle
          },
          series: [{
              name: '年龄段统计',
              type: 'pie',
              radius: '55%',
              center: ['38%', '50%'],
              data: pieData,
              itemStyle:{
                  normal:{
                      label:{
                          show: isShow,
                          formatter: '{b} : {c}人 ({d}%)'
                      },
                      labelLine :{show:isShow}
                  }
              }
          }]
      };
      // 为echarts对象加载数据
      mychartAge.setOption(option);
  } 

//setAgeOption(false);
//组装饼图性别数据
   function setSexOption(isShow,pieData) {
          if (isEmpty(pieData))
              return;
          var mychartSex = echarts.init(document.getElementById('chartSex'));

          var pieTitle = new Array();

        for (var i = 0; i < pieData.length; i++) {
            pieTitle.push(pieData[i].name);
        }
          var option = {
              title: {
                  text: ""
              },
              tooltip: {
                  trigger: 'item',
                  formatter: "{b} : {c}人 ({d}%)"
              },
              toolbox: {
                  show: true,
                  feature: {
                      saveAsImage: {
                          show: true
                      }
                  }
              },
              legend: {
                  orient: 'vertical',
                  x: '65%',
                  y: 'center',
                  data: pieTitle
              },
              calculable: false,
              series: [{
                  name: '性别统计',
                  type: 'pie',
                  radius: '55%',
                  center: ['38%', '50%'],
                  data: pieData,
                  itemStyle:{
                      normal:{
                          label:{
                              show: isShow,
                              formatter: '{b} : {c}人 ({d}%)'
                          },
                          labelLine :{show:isShow}
                      }
                  }
              }]
          };

          // 为echarts对象加载数据
          mychartSex.setOption(option);
      } 
      
// 组装饼图透析龄数据
   function setDialysisAgeOption(isShow,pieData) {
           var mychartDialysisAge = echarts.init(document.getElementById('chartDialysisAge'));
           var pieTitle = new Array();
           for (var i = 0; i < pieData.length; i++) {
               pieTitle.push(pieData[i].name);
           }
           var option = {
               title: {
                   text: ""
               },
               tooltip: {
                   trigger: 'item',
                   formatter: "{b} : {c}人 ({d}%)"
               },
               toolbox: {
                   show: true,
                   feature: {
                       saveAsImage: {
                           show: true
                       }
                   }
               },
               legend: {
                   orient: 'vertical',
                   x: '65%',
                   y: 'center',
                   data: pieTitle
               },
               calculable: false,
               series: [{
                   name: '透析龄统计',
                   type: 'pie',
                   radius: '55%',
                   center: ['38%', '50%'],
                   data: pieData,
                   itemStyle:{
                       normal:{
                           label:{
                               show: isShow,
                               formatter: '{b} : {c}人 ({d}%)'
                           },
                           labelLine :{show:isShow}
                       }
                   }
               }]
           };

        // 为echarts对象加载数据
        mychartDialysisAge.setOption(option);
    }

//组装饼图医保信息统计数据
   function setMedicalOption(isShow,pieData,medicalTitle) {
       if (isEmpty(pieData) || isEmpty(medicalTitle))
           return;
       var mychartMedical = echarts.init(document.getElementById('chartMedical'));
       var pieTitle = new Array();
       var data = [];
       for (var i = 0; i < medicalTitle.length; i++) {
           var count = 0;
           var dataValue = "";
           pieTitle.push(medicalTitle[i].name);
           for(var j= 0; j < pieData.length;j++){
               if(medicalTitle[i].name == pieData[j].chargeName){
                   count++;
               }
           }
           dataValue = {
                           "value":count,
                           "name":medicalTitle[i].name
           }
           data.push(dataValue);
       }
       var option = {
           title: {
               text: '',
               subtext: ''
           },
           tooltip: {
               trigger: 'item',
               formatter: "{b} : {c}人 ({d}%)"
           },
           toolbox: {
               show: true,
               feature: {
                   saveAsImage: {
                       show: true
                   }
               }
           },
           legend: {
               orient: 'vertical',
               x: '65%',
               y: 'center',
               data: pieTitle
           },
           series: [{
               name: '医保信息统计',
               type: 'pie',
               radius: '55%',
               center: ['38%', '50%'],
               data: data,
               itemStyle:{
                   normal:{
                       label:{
                           show: isShow,
                           formatter: '{b} : {c}人 ({d}%)'
                       },
                       labelLine :{show:isShow}
                   }
               }
           }]
       };
       // 为echarts对象加载数据
       mychartMedical.setOption(option);
   } 

    //根据类型下载报表
  $("#downloadPatientCensus").click(
            function () {
                var data = getMethod(reportType);
                window.location.href = ctx + "/report/patient/download.shtml?" + encodeURI(data);
            });

    //下载患者名单
    $("#downLoadPatientList").click(function () {
        window.location.href = ctx + "/report/patient/downLoadPatientsList.shtml?"+ getPatientTempValue();
    });
</script>
</body>
</html>
