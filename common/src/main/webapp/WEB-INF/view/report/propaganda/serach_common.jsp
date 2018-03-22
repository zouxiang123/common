<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../common/report_datepick_new2.jsp" flush="true"></jsp:include>
<div class="xtt">
<div class="bed-head pb-4" style="border-bottom: 0px !important;height: 50px;">
            <div class="bb-line" id="reportDateDiv" style="height: 50px;">
                <div class="tablesetting u-float-r">
                    <button type="button" id="reportDownload">报表下载</button>
                    <button type="button" class="open-show" data-show="#promptDialog">报表设置</button>
                </div>
                <div class="u-pop-up-from"></div>
                <form id="selectForm" action="#" style="display: none;">
                    <input type="hidden" id="searchCond" name="searchCond" value="${searchCond}"> <input type="hidden" id="primaryKey" name="primaryKey"
                value="${primaryKey}">
            <input name="reportDate" id="reportDate" type="hidden" value="">
                 <div class="u-list-text">
                    <div class="left" style="width:70px;">
                                                            宣教时间：
                    </div>
                    <div class="right" style="width:calc(100% - 70px)">
                        <input type="text" style="width: 110px;" id="startDate" name="startDate" value="${entity.startDate}"> &nbsp;至&nbsp; <input type="text" style="width: 110px;"
                            name="endDate" id="endDate" value="${entity.endDate}">
                    </div>
                </div>
                 <div class="u-list-text" id="assessmentDiv">
                    <div class="left" style="width:70px;">
                                                            宣教评价：
                    </div>
                    <div class="right" style="width:calc(100% - 70px)">
                        <label class="u-select">
                        <select name="assessment" style="width: 251px;">
                            <option value="">全部</option>
                            <c:forEach var="entity" items="${assessmentList}" >
                                <option value="${entity.itemCode}">${entity.itemName}</option>
                            </c:forEach>
                        </select>
                        </label>
                    </div>
                </div>
                 <div class="u-list-text" id="propagandaObjectDiv">
                    <div class="left" style="width:70px;">
                                                            宣教对象：
                    </div>
                    <div class="right" style="width:calc(100% - 70px)">
                        <label class="u-select"> 
                        <select name="propagandaObject" style="width: 251px;">
                            <option value="">全部</option>
                            <c:forEach var="entity" items="${propagandaObjectList}" >
                                <option value="${entity.itemCode}">${entity.itemName}</option>
                            </c:forEach>
                        </select>
                        </label>
                    </div>
                </div>
                </form>
            </div>
        </div>
        </div>