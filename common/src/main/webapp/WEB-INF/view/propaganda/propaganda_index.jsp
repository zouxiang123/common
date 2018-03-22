<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>健康宣教</title>
<link rel="stylesheet" type="text/css" href="${COMMON_SERVER_ADDR}/framework/select2/select2.css">
<%@ include file="../common/head_standard.jsp"%>
<style type="text/css" media="screen">
</style>
</head>
<body style="overflow: hidden;">
    <div id="app">
            <div ref="roleshow" style="display: none;">
                <vv-head-ipad>
                    <div slot="conter">{{ store.state.names }}<i v-if="store.state.names">—</i>{{ store.state.titles }}</div>
                    <div slot="right" v-show="equipment">
                        <button type="button" class="u-btn-blue p-0 u-text-r" @touchstart="evaluate = true" v-if="store.state.titles != '宣教库' " style= " border:none;">评价</button>
                    </div>
                </vv-head-ipad>
            </div>
            <div class="u-menu mt-12 pl-12 pr-12">
                <div>
                    <router-link :to="{path: item.path}" tag="span" :class="{active: $route.path == item.path}" v-for="item in store.state.menubar">
                        <a href="javasrcipt" v-text="item.title"></a>
                        <i v-if="item.close" @click="closeFun(item.title)">×</i>
                    </router-link>
                </div>
            </div>
            <vv-evaluate v-if="evaluate" @nn-cancel="evaluate = false"></vv-evaluate>
            <div ref="assessdialog" style="display: none">
                <vv-dialog-screen :title="title" v-if="evaluation">
                    <div slot="content">
                        <form id="startMissionId" method="post" enctype="multipart/form-data" accept-charset="utf-8">
                            <div class="propaganda-assess fc-black pt-12">
                                <div class="u-list pl-30 u-border-b pt-6">
                                    <div class="u-xt-12">
                                        <div class="u-xt-4 fw-bold">姓名：{{patient.name}}</div>
                                        <div class="u-xt-4 fw-bold">性别：{{patient.sexShow}}</div>
                                        <div class="u-xt-4 fw-bold">年龄：{{patient.age}}</div>
                                    </div>                                    
                                </div>
                                <div class="u-list-text u-border-b dashed">
                                    <div class="left fw-bold">
                                     教育对象：
                                    </div>
                                    <div class="right">
                                        <label class="u-radio" v-for="item in formList.propagandaObject">
                                          <input type="radio" name="propagandaObject" :value="item.value" v-model="education">
                                          <span class="icon-radio"></span>{{item.name}}
                                        </label>
                                    </div>
                                </div>
                                <div class="u-list-text">
                                    <div class="left fw-bold">
                                     文化程度：
                                    </div>
                                    <div class="right">
                                        <label class="u-radio" v-for="item in formList.educationalLevel">
                                          <input type="radio" name="educationalLevel" :value="item.value" v-model="culture">
                                          <span class="icon-radio"></span>{{item.name}}
                                        </label>
                                    </div>
                                </div>
                                <div class="u-list-text">
                                    <div class="left fw-bold">
                                     语言种类：
                                    </div>
                                    <div class="right">
                                        <label class="u-radio" v-for="item in formList.languageType">
                                          <input type="radio" name="languageType" :value="item.value" v-model="languageTypes">
                                          <span class="icon-radio"></span>{{item.name}}
                                          <lable v-if="item.name == '其他'" >
                                              <input v-if="languageTypes == '03'" type="text" name="languageTypeOther" short v-model="languageTypesOther"
                                    style="position: absolute;" class="ml-10" >
                                          </label>
                                        </label>
                                    </div>
                                </div>
                                <div class="u-list-text">
                                    <div class="left fw-bold">
                                     语言障碍：
                                    </div>
                                    <div class="right">
                                        <label class="u-radio" v-for="item in formList.languageBarrier">
                                          <input type="radio" name="languageBarrier" :value="item.value" v-model="languageBarriers">
                                          <span class="icon-radio"></span>{{item.name}}
                                        </label>
                                    </div>
                                </div>
                                <div class="u-list-text">
                                    <div class="left fw-bold">
                                     认知状态：
                                    </div>
                                    <div class="right">
                                        对疾病相关知识理解：
                                        <label class="u-radio" v-for="item in formList.perceive">
                                          <input type="radio" name="perceive" :value="item.value" v-model="cognitive">
                                          <span class="icon-radio"></span>{{item.name}}
                                        </label>
                                    </div>
                                </div>
                                <div class="u-list-text">
                                    <div class="left fw-bold">
                                     学习动机：
                                    </div>
                                    <div class="right">
                                        对疾病知识学习欲望：
                                        <label class="u-radio" v-for="item in formList.learnDesire">
                                          <input type="radio" name="learnDesire" :value="item.value" v-model="learningMotivation">
                                          <span class="icon-radio"></span>{{item.name}}
                                        </label>
                                    </div>
                                </div>
                                <div class="u-list-text">
                                    <div class="left fw-bold">
                                     情感障碍：
                                    </div>
                                    <div class="right">
                                        <label class="u-radio" v-for="item in formList.emotionBarrier">
                                          <input type="radio" name="emotionBarrier" :value="item.value" v-model="emotionalDisorders">
                                          <span class="icon-radio"></span>{{item.name}}
                                          <lable v-if="item.name == '其他'" >
                                              <input v-if="emotionalDisorders == '04'" type="text" name="emotionBarrierOther" short
                                    v-model="emotionalDisordersOther" style="position: absolute;" class="ml-10">
                                          </label>
                                        </label>
                                    </div>
                                </div>
                                <div class="u-list-text">
                                    <div class="left fw-bold">
                                     学习障碍：
                                    </div>
                                    <div class="right">
                                        <label class="u-radio" v-for="item in formList.learnBarrier">
                                          <input type="radio" name="learnBarrier" :value="item.value" v-model="learningDisabilities">
                                          <span class="icon-radio"></span>{{item.name}}
                                          <lable v-if="item.name == '其他'" >
                                              <input v-if="learningDisabilities == '05'" type="text" name="learnBarrierOther" short
                                    v-model="learningDisabilitiesOther" style="position: absolute;" class="ml-10">
                                          </label>
                                        </label>
                                    </div>
                                </div>
                                <div class="u-list-text">
                                    <div class="left fw-bold">
                                     宗教信仰：
                                    </div>
                                    <div class="right">
                                        <label class="u-radio" v-for="item in formList.religion" style="margin-right:65px;">
                                          <input type="radio" name="religion" :value="item.value" v-model="religiousBeliefs">
                                          <span class="icon-radio"></span>{{item.name}}
                                          <lable v-if="item.name == '有'" >
                                              <input v-if="religiousBeliefs == '1'" type="text" name="religionOther" short v-model="religiousBeliefsOther"
                                    style="position: absolute;" class="ml-10">
                                          </label>
                                        </label>
                                        <span class="fw-bold">对学习的影响：</span>
                                        <label class="u-radio" v-for="item in formList.learnEffect">
                                          <input type="radio" name="learnEffect" :value="item.value" v-model="impactStudy">
                                          <span class="icon-radio"></span>{{item.name}}
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                     <div slot="footer">
                        <button type="button" class="mr-10" @click="cancel">取消</button>
                        <button type="button" class="u-btn-blue" fill @click="startMission">开始宣教</button>
                    </div>
                </vv-dialog-screen>
            </div>
            <keep-alive>
                <router-view></router-view>
            </keep-alive>
        </div>
    </body>
<script src="${ctx}/vue/framework/babel-polyfill/dist/polyfill.js"></script>
<script src="${ctx}/vue/framework/vue/dist/vue.min.js"></script>
<script src="${ctx}/vue/framework/vue-router/dist/vue-router.min.js"></script>
<script src="${ctx}/vue/framework/axios/dist/axios.min.js"></script>
<script src="${ctx}/vue/framework/vuex/dist/vuex.min.js"></script>
<script src="${ctx}/vue/framework/photoClip/iscroll-zoom.js"></script>
<script src="${ctx}/vue/framework/photoClip/hammer.js"></script>
<script src="${ctx}/vue/framework/photoClip/lrz.all.bundle.js"></script>
<script src="${ctx}/vue/framework/photoClip/jquery.photoClip.js"></script>
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
//let token = localStorage.getItem("token"); // token
new Vue({
    el: "#app",
    router: router,
    data: function data() {
        return {
            list: [],
            classify: [],
            evaluation: false,
            evaluate: false,
            equipment: false,
            title: '健康教育评估',
            patient: [],
            formList: {},
            education: "00", // 宣教对象
            culture: "", //文化程度
            languageTypes: "", //语言类型
            languageTypesOther: "", //其他语言类型
            languageBarriers: "", //语言障碍
            cognitive: "", //认知状态
            learningMotivation: "", //学习动机
            emotionalDisorders: "", //情感障碍
            emotionalDisordersOther: "", //其他情感障碍
            learningDisabilities: "", // 学习障碍
            learningDisabilitiesOther: "", //其他学习障碍
            religiousBeliefs: "", //宗教信仰
            religiousBeliefsOther: "", //其他宗教信仰
            impactStudy: "" //影响学习
        };
    },
    methods: {
        startMission: function startMission() {
            //健康教育评估保存api
            var th = this;
            var formdata = new FormData(startMissionId);
            formdata.append("fkPatientId", store.state.patientID);
            var url = '/propaganda/education/assessment/save.shtml';
            axiosPost(url, formdata, function (res) {
                var rens = res.data;
                if (rens.status == 1) {
                    th.evaluation = false;
                } else {
                    promptbox(rens.errmsg, "promptbox-language");
                }
            });
        },
        typeEducation: function typeEducation() {
            //教育对象切换api
            if (!store.state.patientID) {
                return false;
            }
            var th = this;
            var data = {
                'fkPatientId': store.state.patientID,
                'propagandaObject': this.education,
                'rand': new Date().getTime()
            };
            var url = '/propaganda/education/assessment/getByCondition.shtml';
            axiosGet(url, data, function (res) {
                var rens = res.data.rs;
                if (res.data.status == 1) {
                    th.patient = rens.patient;
                    store.commit('name',th.patient.name);
                    if (rens.entity) {
                        th.education = rens.entity.propagandaObject;
                        th.culture = rens.entity.educationalLevel;
                        th.languageTypes = rens.entity.languageType;
                        th.languageTypesOther = rens.entity.languageTypeOther;
                        if (rens.entity.languageBarrier != null) {
                            th.languageBarriers = rens.entity.languageBarrier ? 1 : 0;
                        } else {
                            th.languageBarriers = "";
                        }
                        if (rens.entity.perceive != null) {
                            th.cognitive = rens.entity.perceive ? 1 : 0;
                        } else {
                            th.cognitive = "";
                        }
                        if (rens.entity.learnDesire != null) {
                            th.learningMotivation = rens.entity.learnDesire ? 1 : 0;
                        } else {
                            th.learningMotivation = "";
                        }
                        if (rens.entity.learnEffect != null) {
                            th.impactStudy = rens.entity.learnEffect ? 1 : 0;
                        } else {
                            th.impactStudy = "";
                        }
                        if (rens.entity.religion != null) {
                            th.religiousBeliefs = rens.entity.religion ? 1 : 0;
                        } else {
                            th.religiousBeliefs = "";
                        }
                        th.emotionalDisorders = rens.entity.emotionBarrier;
                        th.emotionalDisordersOther = rens.entity.emotionBarrierOther;
                        th.learningDisabilities = rens.entity.learnBarrier;
                        th.learningDisabilitiesOther = rens.entity.learnBarrierOther;
                        th.religiousBeliefsOther = rens.entity.religionOther;
                    } else {
                        th.initialize();
                    }
                }
            });
        },
        evaluationList: function evaluationList() {
            //列表选项api
            var th = this;
            var $token = localStorage.getItem("token");
            var url = '/propaganda/assessment/getFormData.shtml';
            axiosGet(url, {}, function (res) {
                if (res.data.status == 1) {
                    th.formList = res.data.rs;
                }
            });
        },
        closeFun: function closeFun(name) {
            //删除head
            for (var item in store.state.menubar) {
                var contrast = store.state.menubar[item];
                if (contrast.title == name) {
                    if (contrast.path == this.$route.path) {
                        this.$router.push({ path: store.state.menubar[item - 1].path });
                    }
                    store.state.menubar.splice(item, 1);
                }
            } //关闭head
        },
        initialize: function initialize() {
            //初始化
            this.culture = "";
            this.languageTypes = "";
            this.languageTypesOther = "";
            this.languageBarriers = "";
            this.cognitive = "";
            this.learningMotivation = "";
            this.emotionalDisorders = "";
            this.emotionalDisordersOther = "";
            this.learningDisabilities = "";
            this.learningDisabilitiesOther = "";
            this.religiousBeliefs = "";
            this.religiousBeliefsOther = "";
            this.impactStudy = "";
        },
        cancel:function cancel(){
            if(threeTerminal() == "ipad" || store.state.roles == "1"){
                history.go(-1);
            }else{
                parent.removeActiveTab();
            }
        }
    },
    watch: {
        education: "typeEducation"
    },
    mounted: function mounted() {
        if (threeTerminal() == "ipad") {
            this.$refs.roleshow.style.display = "block";
            this.equipment = true;
            var links = document.createElement('link');
            links.setAttribute("rel", "stylesheet");
            links.setAttribute("type", "text/css");
            links.setAttribute("href", "${COMMON_SERVER_ADDR}/assets/css/standard/css/mobile.css");
            document.getElementsByTagName("head")[0].appendChild(links);
        }
        if(store.state.roles == "1"){
            this.$refs.roleshow.style.display = "block";
        }
        if (store.state.patientID) {
            this.$refs.assessdialog.style.display = "block";
            this.evaluation = true;
        }
    },
    created: function created() {
        this.evaluationList();
        this.typeEducation();
    }
});
    </script>
</html>