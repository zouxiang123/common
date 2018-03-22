"use strict";

Vue.component("vv-load", {
    template: "<div class=\"u-pageLoad\">\n                <div>\n                    <i class=\"icon-load\"></i>\n                    <p>\u6B63\u5728\u52A0\u8F7D\u4E2D....</p>\n                </div>\n            </div>"
});

Vue.component("vv-head-ipad", {
    template: '<div class="u-title">'+
				'<div class="left fs-18">'+
					'<label @click="historys">'+
					   '<slot name="left"><i class="icon-arrow-left mt-10"></i>返回</slot>'+
					'</label>'+
				'</div>'+
				'<div class="center fs-18"><slot name="conter"></slot></div>'+
				'<div class="right u-text-r"><slot name="right"></slot></div>'+
			  '</div>',
				
    methods: {
    	historys: function historys() {
    		history.go(-1);
    		setTimeout(function(){
    			store.commit('title', '宣教库');
    		}, 100)
    	}
    }
});

Vue.component("vv-confirm", {
    template: '<div class="dialog-mask">'+
			    '<div class="propaganda-dialog delete">'+
				    '<div class="title">{{title}}</div>'+
				    '<div class="content">'+
				        '<slot name="content"></slot>'+
				    '</div>'+
				    '<div class="footer">'+
				        '<button type="button" class="mr-10" @click="cancel">取消</button>'+
				        '<button type="button" class="u-btn-blue" fill @click="confirm">确认</button>'+
				    '</div>'+
				'</div>'+
			  '</div>',
	props:{
		title: {
            type: String,
            default: ""
        }
	},
    methods: {
        cancel: function cancel() {
            this.$emit("nn-cancel");
        },
        confirm: function confirm() {
            this.$emit("nn-confirm");
        }
    }

});

Vue.component("vv-delete", {
    template: "<div class=\"dialog-mask\">\n                <div class=\"propaganda-dialog delete\">\n                    <div class=\"title\">\n                        {{title}}\n                    </div>\n                    <div class=\"content\">\n                        <slot name=\"content\"></slot>\n                    </div>\n                    <div class=\"footer\">\n                        <button type=\"button\" class=\"mr-10\" @click=\"cancel\">\u53D6\u6D88</button>\n                        <button type=\"button\" class=\"u-btn-blue\" fill @click=\"confirm\">\u786E\u8BA4</button>\n                    </div>\n                </div>\n            </div>",
    props: {
        title: {
            type: String,
            default: ""
        },
        delete: {
            type: Array,
            default: []
        }
    },
    data: function data() {
        return {};
    },

    methods: {
        cancel: function cancel() {
            this.$emit("nn-cancel");
        },
        confirm: function confirm() {
            var th = this;
            var url = "/propaganda/resource/remove.shtml";
            var data = {
                id: th.$route.params.id
            };
            axiosGet(url, data, function (res) {
                th.$emit("nn-confirm");
                if (res.data.status == 1) {
                    th.$router.push({
                        path: "/",
                        name: "propaganda",
                        params: {
                            refresh: true
                        }
                    });
                    for (var item in store.state.menubar) {
                        if (store.state.menubar[item].title == th.delete.resourceTitle) {
                            store.state.menubar.splice(item, 1);
                        }
                    }
                    promptbox("删除成功", "promptbox-language");
                } else {
                    promptbox("删除失败", "promptbox-language");
                }
            });
        }
    }

});

Vue.component("vv-dialog", {
    template: '<div class="dialog-mask">'+
				    '<div class="propaganda-dialog">'+
					    '<div class="title">'+
					        '<slot name="title">'+
							    '<div class="left"></div>'+
						    	'<div class="center">{{title}}</div>'+
						    	'<div class="right"></div>'+
					    	'</slot>'+
					    '</div>'+
					    '<div class="content">'+
					        '<slot name="content"></slot>'+
					    '</div>'+
					    '<div class="footer">'+
					        '<slot name="footer">'+
					            '<button type="button" class="mr-10" @click="cancel">取消</button>'+
					            '<button type="button" class="u-btn-blue" fill @click="confirm">保存</button>'+
					        '</slot>'+
					    '</div>'+
					'</div>'+
			  '</div>',
    props: {
        title: {
            type: String,
            default: ""
        }
    },
    data: function data() {
        return {};
    },

    methods: {
        cancel: function cancel() {
            this.$emit("nn-cancel");
        },
        confirm: function confirm() {
            this.$emit("nn-confirm");
        }
    }

});

Vue.component("vv-dialog-screen", {
    template: "<div class=\"dialog-mask\">\n                <div class=\"propaganda-dialog screen\">\n                    <div class=\"title\">\n                        {{title}}\n                    </div>\n                    <div class=\"content\">\n                        <slot name=\"content\"></slot>\n                    </div>\n                    <div class=\"footer\">\n                        <slot name=\"footer\" >\n                            <button type=\"button\" class=\"mr-10\" @click=\"cancel\">\u53D6\u6D88</button>\n                            <button type=\"button\" class=\"u-btn-blue\" fill @click=\"confirm\">\u4FDD\u5B58</button>\n                        </slot>\n                    </div>\n                </div>\n            </div>",
    props: {
        title: {
            type: String,
            default: ""
        }
    },
    data: function data() {
        return {};
    },

    methods: {
        cancel: function cancel() {
            this.$emit("nn-cancel");
        },
        confirm: function confirm() {
            this.$emit("nn-confirm");
        }
    }

});

Vue.component("vv-cover", {
    template: "<div>\n                    <vv-dialog :title=\"title\">\n                        <div slot=\"content\">  \n                            <div class=\"cover-crop\">\n                                <div class=\"cover-crop-content\" id=\"clipArea\">\n                                    \u5C01 \u9762\n                                </div>\n                               \n                                <button type=\"button\" text>\u4E0A\u4F20\u5C01\u9762\n                                    <input type=\"file\" id=\"file\">\n                                </button>\n                                <p>\u5C01\u9762\u4E0A\u4F20\u5927\u5C0F\u4E0D\u80FD\u8D85\u8FC7(3M)</p>\n                           </div>\n                        </div>\n                        <div slot=\"footer\">\n                            <button type=\"button\" @click=\"cancel\">\u53D6\u6D88</button>\n                            <button type=\"button\" class=\"u-btn-blue\" fill id=\"clipBtn\">\u4FDD\u5B58</button>\n                        </div>\n                    </vv-dialog>\n                    \n\t          </div>\n              ",
    data: function data() {
        return {
            files: "",
            title: "上传封面"
        };
    },

    methods: {
        uploadFiles: function uploadFiles(e) {
            var th = this;
            var file = e.target.files[0];
            var reader = new FileReader();
            reader.onload = function (e) {
                th.files = e.target.result;
            };
            reader.readAsDataURL(file);
        },
        cancel: function cancel() {
            this.$emit('nn-cancel');
        }
    },
    mounted: function mounted() {
        var th = this;
        var clipArea = new bjj.PhotoClip("#clipArea", {
            size: [510, 280],
            outputSize: [530, 294],
            file: "#file",
            view: "#view",
            ok: "#clipBtn",
            // loadStart: function() {
            // console.log("照片读取中");
            // },
            // loadComplete: function() {
            // console.log("照片读取完成");
            // },
            clipFinish: function clipFinish(dataURL) {
                th.files = dataURL;
                th.$emit("nn-save", th.files);
            }
        });
    }
});

Vue.component("vv-Uimage", {
    template: "<div class=\"upload-image\" >\n                <label class=\"u-file mt-10\" >\n                    <i class=\"icon-add\"></i>\n                    <input type=\"file\" multiple @change=\"uploadimage\" v-model=\"inputs\">\n                </label>\n\n                <div v-for=\"item in images\">\n                    <i class=\"icon-close\" @click=\"deleteFun(item)\"></i>\n                    <img :src=\"store.state.head + '/health_propaganda' + item\" />\n                </div>\n            </div>\n              ",
    props: {
        images: {
            type: Array,
            default: []
        }
    },
    data: function data() {
        return {
            inputs: ""
        };
    },

    methods: {
        uploadimage: function uploadimage(e) {
            var th = this;
            var file = e.target.files;
            var formData = new FormData();
            formData.append("resourceType", "image");
            for (var item in file) {
                formData.append("files", file[item]);
            }
            var url = "/propaganda/resource/uploadFiles.shtml";
            axiosPost(url, formData, function (res) {
                if (res.data.status == 1) {
                    th.images = th.images.concat(res.data.list);
                    th.$emit("nn-upimage", th.images);
                    th.inputs = "";
                } else {
                    promptbox(res.data.errmsg, "promptbox-language");
                }
            });
            var urlp = store.state.head + url + '?token=' + localStorage.getItem("token");
            // progressfile({
            // url: urlp,
            // data: formData,
            // progress: function(ev) {

            // }
            // })
        },
        deleteFun: function deleteFun(img) {
            this.images.splice(this.images.indexOf(img), 1);
            this.$emit("nn-upimage", this.images);
        }
    }

});

Vue.component("vv-textarea", {
    template: "<div class=\"text-editbox mt-6\" >\n\t               <div class=\"text-editbox-title\">\n\t                  <button type=\"button\" text class=\"icon-bold\" onclick=\"document.execCommand('Bold')\"></button>\n\t                  <button type=\"button\" text class=\"icon-the-left\" onclick=\"document.execCommand('justifyLeft')\"></button>\n\t                  <button type=\"button\" text class=\"icon-center\" onclick=\"document.execCommand('justifyCenter')\"></button>\n\t                  <button type=\"button\" text class=\"icon-the-right\" onclick=\"document.execCommand('justifyRight')\"></button>\n\t                  <label class=\"u-select\" short>\n\t                      <select @change=\"fontSizeIn\" v-model=\"fontSize\">\u5B57\u4F53\u5927\u5C0F\n\t                        <option value=\"1\">12</option>\n\t                        <option value=\"2\">14</option>\n\t                        <option value=\"3\" selected >16</option>\n\t                        <option value=\"4\">18</option>\n\t                        <option value=\"5\">20</option>\n\t                        <option value=\"6\">22</option>\n\t                        <option value=\"7\">24</option>\n\t                      </select>\n\t                  </label>\n                      <button type=\"button\" class=\"u-file\">\n                            <input type=\"file\" name=\"file\" @change=\"insertImage\">\n                            <i class=\"icon-picture\"></i>\n                      </button>\n\t                  \n\t               </div>\n\t               <div class=\"text-editbox-content\" contentEditable= \"true\" ref=\"textcont\" v-html=\"textre\" id=\"textareId\">\n\t                \n\t               </div>\n\t          </div>",
    props: {
        textre: {
            type: String,
            default: ""
        }
    },
    data: function data() {
        return {
            fontSize: 2
        };
    },

    methods: {
        fontSizeIn: function fontSizeIn() {
            document.execCommand("fontSize", true, this.fontSize);
            var fontElements = document.getElementsByTagName("font");
            for (var i = 0; i < fontElements.length; ++i) {
                var label = fontElements[i];
                switch (label.size) {
                    case "1":
                        label.style.fontSize = "14px";
                        break;
                    case "2":
                        label.style.fontSize = "16px";
                        break;
                    case "3":
                        label.style.fontSize = "18px";
                        break;
                    case "4":
                        label.style.fontSize = "20px";
                        break;
                    case "5":
                        label.style.fontSize = "24px";
                        break;
                    case "6":
                        label.style.fontSize = "28px";
                        break;
                    case "7":
                        label.style.fontSize = "32px";
                        break;
                }
            }
        },
        insertImage: function insertImage(e) {
            var file = e.target.files[0];
            var formData = new FormData();
            var th = this;
            var url = "/propaganda/resource/uploadFiles.shtml";
            formData.append("resourceType", "text");
            formData.append("files", file);
            axiosPost(url, formData, function (res) {
                if (res.data.status == 1) {
                    var src = store.state.head + '/health_propaganda' + res.data.list;
                    var men = document.execCommand('insertImage', true, src);
                }
            });
        }
    }

});

Vue.component("vv-Uvideo", {
    template: "<div class=\"upload-video\">\n                <label class=\"u-file icon-upload\">\n                   <input type=\"file\" name=\"file\" value=\"\" @change=\"uploadvideo\">\n                   \u89C6\u5C4F\u4E0A\u4F20\n                </label>\n                <span class=\"fc-black_5\">\n           支持MP4(H264)、AVI(H264)格式，最多上传/导入1个视频，单个视频不超过500M；              </span>\n                <div v-show=\"progress\">\n                    <div class=\"video-title\">\n                        <span v-if=\"!name\">{{video.resourceTitle}}</span>\n                        <span v-if=\"name\">{{name}} ({{size}} M)</span>\n                    </div>\n                    <div class=\"layui-progress\">\n                      <div class=\"layui-progress-bar\" :style=\"{width: ress + '%'}\"></div>\n                    </div>\n                    <i class=\"icon-close\" @click=\"progressclose\" v-if=\"closes\"></i>\n                    <i class=\"icon-load\" v-if=\"loads\"></i>\n                </div>\n            </div>",
    props: {
        video: {
            type: Object,
            default: ""
        }
    },
    data: function data() {
        return {
            progress: false,
            closes: false,
            loads: false,
            ress: 0,
            size: 0,
            name: ""
        };
    },

    watch: {
        "video": function video() {
            if (this.video.resourceTitle) {
                this.progress = true;
                this.closes = false;
                this.loads = false;
                this.ress = 100;
                this.name = "";
            }
        }
    },
    methods: {
        uploadvideo: function uploadvideo(e) {
            this.closes = false;
            this.loads = true;
            var th = this;
            var file = e.target.files[0];
            var formData = new FormData();
            var url = "/propaganda/resource/uploadFiles.shtml";
            this.size = (file.size / 1024 / 1026).toFixed(2);
            this.name = file.name;
            this.progress = true;
            formData.append("resourceType", "video");
            formData.append("files", file);
            axiosPost(url, formData, function (res) {
                if (res.data.status == 1) {
                    th.$emit("nn-Uvideo", res.data.list[0]);
                    th.closes = true;
                    th.loads = false;
                }
            });
            // progressfile({
            // url: urlp,
            // data: formData,
            // progress: function(ev) {

            // }
            // })
            progressfile(url, formData, function (ev) {
                th.ress = ev.loaded / ev.total * 100;
            });
        },
        progressclose: function progressclose() {
            this.progress = false;
            this.closes = false;
            this.loads = false;
            this.video = {};
            this.size = 0;
            this.ress = 0;
            this.name = "";
        }
    }

});
Vue.component("vv-evaluate", {
    template:'<div>'+
                  '<vv-dialog class="ipad-dialog">'+
                        '<div slot="title">'+
	                        '<div class="left pl-18" style="margin-top:3px;">'+
		                        '<label @click="cancel" v-if="equipment">'+
			 					   '<slot name="left"><i class="icon-arrow-left fs-18 mt-10" style="position: relative;top:2px;left:-2px;"></i>返回</slot>'+
			 					'</label>'+
                        	'</div>'+
					    	'<div class="center">{{title}}</div>'+
					    	'<div class="right"></div>'+
                  		'</div>'+
                        '<div slot="content">'+
                            '<form id="educationEvaluation" method="post" enctype="multipart/form-data" accept-charset="utf-8">'+
                                '<div class="propaganda-evaluate fc-black pb-8">'+
                                    '<div class="u-list-text">'+
                                        '<div class="left fw-bold">宣教名称：</div>'+
                                        '<div class="right fw-bold" v-text="particulars.resourceTitle ? particulars.resourceTitle:store.state.titles"></div>'+
                                    '</div>'+
                                    '<div class="u-list-text">'+
                                        '<div class="left fw-bold">教育对象：</div>'+
                                        '<div class="right">'+
                                            '<label class="u-radio" v-for="item in formList.propagandaObject">'+
                                              '<input type="radio" name="propagandaObject" :value="item.itemCode" v-model="propagandaObject">'+
                                              '<span class="icon-radio"></span>{{item.name}}'+
                                            '</label>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="u-list-text">'+
	                                    '<div class="left fw-bold">宣教方式：</div>'+
	                                    '<div class="right">'+
	                                        '<label class="u-radio mr-10" v-for="item in formList.propagandaType" >'+
	                                          '<input type="radio" name="propagandaType" :value="item.itemCode" v-model="propagandaType">'+
	                                          '<span class="icon-radio"></span>{{item.name}}'+
											  '<label style="position:absolute" v-if="item.name == \'其他\'" >'+
											  	'<input type="text" class="ml-6" name="propagandaTypeOther" short v-if=" propagandaType == \'05\'">'+
	                                          '</label>'+
	                                        '</label>'+
	                                    '</div>'+
	                                '</div>'+
                                    '<div class="u-list-text">'+
                                        '<div class="left fw-bold">宣教评价：</div>'+
                                        '<div class="right">'+
                                            '<label class="u-radio mr-10" v-for="item in formList.assessment">'+
                                              '<input type="radio" name="assessment" :value="item.itemCode" v-model="propagandaEvaluation">'+
                                              '<span class="icon-radio"></span>{{item.name}}'+
                                            '</label>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="u-list-text">'+
                                        '<div class="left fw-bold">宣教地点：</div>'+
                                        '<div class="right">'+
                                            '<label class="u-radio mr-10" v-for="item in formList.place">'+
                                              '<input type="radio" name="place" :value="item.itemCode" v-model="propagandaSite">'+
                                              '<span class="icon-radio"></span>{{item.name}}'+
                                            '</label>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="u-list-text" v-if="!store.state.patientID">'+
                                        '<div class="left fw-bold">宣教患者：</div>'+
                                        '<div class="right">'+
                                         '<label class="u-select" long>'+
                                            '<select id="multipleSelectId2" multiple="multiple" style="width:100%;">'+
                                                '<option :value="item.id" :data-search="item.spellInitials" v-for="item in patient">{{ item.name }}</option>'+
                                            '</select>'+
                                         '</label>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="u-list-text">'+
                                        '<div class="left fw-bold">备注：</div>'+
                                        '<div class="right">'+
                                            '<textarea name="" class="mt-8" name="comment" v-model="remark" long></textarea>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="u-list-text-two">'+
                                        '<div class="left">'+
                                            '<div class="fw-bold">宣教人：</div>'+
                                            '<div>'+
                                                '<label class="u-select mt-2" long >'+
                                                  '<select name="operateId" v-model="propagandaPeople">'+
                                                    '<option :value="item.id" v-for="item in nurse">{{ item.name }}</option>'+
                                                  '</select>'+
                                                '</label>'+
                                            '</div>'+
                                        '</div>'+
                                        '<div class="right">'+
                                            '<div class="fw-bold">宣教日期：</div>'+
                                            '<div>'+
                                                '<input type="text" class="mt-2" long value="" name="propagandaDateShow" id="inputId" placeholder="选择日期" readonly>'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</div>'+
                            '</form>'+
                        '</div>'+
                        '<div slot="footer">'+
                            '<button type="button" class="mr-10 u-" @click="cancel">取消</button>'+
                            '<button type="button" class="u-btn-blue" fill @click="evaluateSave">保存</button>'+
                        '</div>'+
                    '</vv-dialog>'+
                    '<vv-load v-if="load"></vv-load>'+
              '</div>',
    props: {
        particulars: {
            type: Object,
            default: {}
        }
    },
    data: function data() {
        return {
            title: '宣教评价',
            patient: [],
            nurse: [],
            formList: {},
            load: false,
            equipment: false,
            propagandaObject: '00',
            propagandaType: '',
            propagandaEvaluation: '',
            propagandaSite: '',
            remark: '',
            propagandaPeople: ''
        };
    },

    methods: {
        evaluateSave: function evaluateSave() {
            this.load = false;
            var th = this;
            var patientS = $("#multipleSelectId2").val();
            var formdata = new FormData(educationEvaluation);
            formdata.append("fkPropagandaResourceId", th.$route.params.id);
            var url = "";
            if (store.state.patientID) {
                patientS = store.state.patientID;
                url = "/propaganda/assessment/save.shtml";
                formdata.append("fkPatientId", patientS);
            } else {
                url = '/propaganda/assessment/saveBatch.shtml';
                formdata.append("fkPatientIds", patientS);
            }
            if (!patientS) {
                promptbox("请选择宣教患者", "promptbox-language");
                return false;
            }
            axiosPost(url, formdata, function (res) {
                var rens = res.data;
                if (rens.status == 1) {
                    th.cancel();
                    promptbox("评价成功", "promptbox-language");
                    th.load = false;
                } else {
                    if (rens.errmsg) {
                        promptbox(rens.errmsg, "promptbox-language");
                    } else {
                        promptbox(rens.errmsg, "promptbox-language");
                    }
                    th.load = false;
                }
            });
        },
        allPeople: function allPeople() {
            var th = this;
            var data = {
                pageNo: 1,
                pageSize: 20
                // 患者API
            };var url1 = '/propaganda/bigscreen/getPatient.shtml';
            // 医护API
            var url2 = '/propaganda/bigscreen/getPropagandaPerson.shtml';
            axiosGet(url1, data, function (res) {
                var rens = res.data;
                if (rens.status == 1) {
                    th.patient = rens.list;
                }
            });
            axiosGet(url2, data, function (res) {
                var rens = res.data;
                if (rens.status == 1) {
                    th.nurse = rens.list;
                }
            });
        },
        evaluationList: function evaluationList() {
            //列表选项api
            var th = this;
            var url = '/propaganda/assessment/getFormData.shtml';
            axiosGet(url, {}, function (res) {
                if (res.data.status == 1) {
                    th.formList = res.data.rs;
                }
            });
        },
        reset: function reset() {
            this.propagandaObject = "00";
            this.propagandaEvaluation = '';
            this.propagandaSite = '';
            this.remark = '';
            this.propagandaPeople = '';
            $("#multipleSelectId2").val('');
            $("#multipleSelectId2").select2({
                tags: true
            });
        },
        cancel: function cancel() {
            this.$emit('nn-cancel');
            this.reset();
        }
    },
    mounted: function mounted() {
    	if(threeTerminal() == "ipad"){
    		this.equipment = true;
    	}
        this.propagandaPeople = store.state.loginUserId;
        $("#multipleSelectId2").select2({
            tags: true
        });
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#inputId',
                theme: '#31AAFF',
                btns: ['confirm'],
                value: new Date()
            });
        });
    },
    created: function created() {
        this.allPeople();
        this.evaluationList();
    }
});