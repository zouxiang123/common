<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宣教信息</title>
<link rel="stylesheet" type="text/css" href="${COMMON_SERVER_ADDR}/framework/select2/select2.css">
<%@ include file="../common/head_standard.jsp"%>
<style type="text/css" media="screen">
.right {
    text-align: left;
}
</style>
</head>
<body style="background-color: white;">
    <div id="app">
        <div class="frame-content u-clear-scroll pl-16">
            <div class="u-list mt-8">
                <div class="u-xt-6">
                    <!-- <button type="button" class="u-btn-blue">宣教记录</button> -->
                    <!-- <button type="button">宣教计划</button> -->
                </div>
                <div class="u-xt-6 u-text-r">
                    <button type="button" @click="filter = true">筛选设置</button>
                </div>
                <div class="u-prompt-box" v-show="filter" top-right style="display: block; top: 42px; right: 0px;">
                    <div class="u-list-text">
                        <div class="left">宣教时间：</div>
                        <div class="right">
                            <input type="text" style="width: 110px;" id="startTime" value="" ref="startDate" placeholder="开始日期" readonly>
                            &nbsp;至&nbsp; <input type="text" style="width: 110px;" id="endTime" ref="endDate" placeholder="结束日期" readonly>
                        </div>
                    </div>
                    <div class="u-list-text mt-10">
                        <div class="left">宣教评价：</div>
                        <div class="right">
                            <label class="u-select"> <select v-model="assessment" style="width: 251px;">
                                    <option value="">全部</option>
                                    <option :value="item.value" v-for="item in selects.assessment">{{item.name}}</option>
                            </select>
                            </label>
                        </div>
                    </div>
                    <div class="u-list-text mt-10">
                        <div class="left">宣教对象：</div>
                        <div class="right">
                            <label class="u-select"> <select v-model="propagandaObject" style="width: 251px;">
                                    <option value="">全部</option>
                                    <option :value="item.value" v-for="item in selects.propagandaObject">{{item.name}}</option>
                            </select>
                            </label>
                        </div>
                    </div>
                    <div class="u-list u-border-t u-text-r pt-6 mt-6">
                        <button type="button" @click="filter = false">取消</button>
                        <button type="button" class="u-btn-blue" @click="recordList(true)">确定</button>
                    </div>
                </div>
            </div>
            <div class="u-table-fixed">
                <div class="u-table-fixed-head">
                    <table class="u-table u-table-bordered">
                        <thead>
                            <tr>
                                <th class="xtt-12">宣教时间</th>
                                <th>宣教内容</th>
                                <th class="xtt-12">宣教对象</th>
                                <th class="xtt-10">宣教医护</th>
                                <th class="xtt-9">宣教评价</th>
                                <th class="xtt-20">操作</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="u-table-fixed-body" :style="{maxHeight:tableH +'px'}">
                    <table class="u-table u-table-bordered">
                        <tbody>
                            <tr v-for="item in list">
                                <td class="xtt-12" v-text="item.propagandaDateShow"></td>
                                <td v-text="item.resourceTitle"></td>
                                <td class="xtt-12" v-text="item.propagandaObjectName"></td>
                                <td class="xtt-10" v-text="item.operateName"></td>
                                <td class="xtt-9" v-text="item.assessmentName"></td>
                                <td class="xtt-20">
                                    <button type="button" class="u-btn-red mr-10" text @click="confirmShow(item.id)">删除</button>
                                    <button type="button" class="u-btn-blue mr-10" text @click="compileFun(item,'compile')">编辑</button>
                                    <button type="button" class="u-btn-blue" text @click="compileFun(item,'documents')">评估单</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <vv-confirm v-if="confirm" @nn-cancel="confirm = false" @nn-confirm="deleteRecord(patientsId)" :title="title">
            <div slot="content">您删除之后将不能恢复！</div>
        </vv-confirm>
        <vv-evaluation-editing @nn-cancel=" compile = false" @nn-save="recordList" v-if=" compile " :list="detailList"></vv-evaluation-editing>
        <vv-evaluation-single @nn-cancel=" documents = false " v-if="documents" :list="detailList"></vv-evaluation-single>
        <vv-load v-if="load"></vv-load>
    </div>
</body>
<script src="${ctx}/vue/framework/babel-polyfill/dist/polyfill.js"></script>
<script src="${ctx}/vue/framework/vue/dist/vue.min.js"></script>
<script src="${ctx}/vue/framework/vue-router/dist/vue-router.min.js"></script>
<script src="${ctx}/vue/framework/axios/dist/axios.min.js"></script>
<script src="${ctx}/vue/framework/vuex/dist/vuex.min.js"></script>
<script src="${ctx}/vue/component/module.js"></script>
<script src="${ctx}/vue/component/component.js"></script>
<script src="${ctx}/vue/router/router.js"></script>
<script src="${ctx}/vue/store/store.js"></script>
<script src="${ctx}/vue/api/api.js"></script>
<script src="${COMMON_SERVER_ADDR}/framework/select2/select2.js"></script>
<script src="${ctx}/vue/js/vindex.js"></script>
<script>
'use strict';

store.commit('heads', '${ctx}'); // 地址头
store.commit('entrance', '${patientId}'); // 入口
store.commit('loginUser', '${loginUser.id}'); // 入口
store.commit('role', '${isNurse}'); // 入口
var head = store.state.head; // 请求头
new Vue({
    el: "#app",
    router: router,
    data: function data() {
        return {
            filter: false,
            compile: false,
            documents: false,
            load: false,
            confirm: false,
            patientsId: "",
            list: [],
            detailList: {},
            selects: {},
            assessment: '',
            propagandaObject: '',
            title: '确认删除'
        }
    },
    computed: {
        tableH: function tableH() {
            return window.innerHeight - 150;
        }
    },
    methods: {
        recordList: function recordList(ban) {
            //宣教记录
            if (!ban) {
                this.load = true;
            }
            if(this.$refs.startDate || this.$refs.endDate){
                var startDate = this.$refs.startDate.value;
                var endDate = this.$refs.endDate.value;
            }
            var th = this;
            var url = '/propaganda/assessment/list.shtml';
            var data = {
                fkPatientId: store.state.patientID,
                startDate: startDate,
                endDate: endDate,
                assessment: this.assessment,
                propagandaObject: this.propagandaObject
            };
            axiosGet(url, data, function (res) {
                var rens = res.data;
                if (rens.status == 1) {
                    th.list = rens.rs;
                    th.load = false;
                    th.filter = false;
                }
            });
        },
        deleteRecord: function deleteRecord(id) {
            //删除记录
            var th = this;
            var $token = localStorage.getItem("token");
            var url = '/propaganda/assessment/delete.shtml';
            var data = {
                id: id
            };
            axiosDelete(url, data, function (res) {
                if (res.data.status == 1) {
                    promptbox("删除成功", "promptbox-language");
                    th.confirm = false;
                    th.recordList(true);
                } else {
                    promptbox("删除失败", "promptbox-language");
                }
            });
        },
        screeningSelect: function screeningSelect() {
            //动态筛选
            var th = this;
            var $token = localStorage.getItem("token");
            var url = '/propaganda/assessment/getDropDownData.shtml';
            axiosGet(url, {}, function (res) {
                if (res.data.status == 1) {
                    th.selects = res.data.rs;
                }
            });
        },
        confirmShow: function confirmShow(id) {
            this.confirm = true;
            this.patientsId = id;
            console.log(this.patientsId);
        },
        compileFun: function compileFun(list, name) {
            //显示弹框并传值
            this[name] = true;
            this.detailList = list;
        }
    },
    mounted: function mounted() {
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#startTime',
                theme: '#31AAFF',
                showBottom: false
            });

            laydate.render({
                elem: '#endTime',
                theme: '#31AAFF',
                showBottom: false
            });
        });
    },
    created: function created() {
        this.screeningSelect();
        this.recordList();
    }
});
    </script>
</html>