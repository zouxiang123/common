"use strict";

//宣教内容
var Propaganda = Vue.component("vv-propaganda", {
    template: "<div>\n                <div class=\"dstyle-menu\">\n                    <div class=\"left\">\n                        <label class=\"icon-input\">\n                           <input type=\"text\" placeholder=\"\u641C\u7D22\u5185\u5BB9\" v-model=\"search\" style=\"line-height: 34px;\">\n                        </label>\n                    </div>\n                    <div class=\"center\">\n                        <span :class=\"{active: classA == undefined }\" @click=\"allPropaganda(undefined)\">\u5168\u90E8</span>\n                        <span :class=\"{active: classA == item.id }\" v-for=\"item in classify\" v-text=\"item.categoryName\" @click=\"allPropaganda(item.id)\"></span>\n                    </div>\n                    <div class=\"right\">\n                        <button type=\"button\" class=\"u-btn-blue\" fill @click=\"newadd(add)\">\u65B0\u589E</button>\n                    </div>\n                </div>\n                <div class=\"propaganda-content\" :style=\"{maxHeight:contentH + 'px'}\">\n                    <div @click=\"newadd(item)\" v-for=\"item in list\" class=\"data-card\" :style=\"{width:cardW,height: datacard*0.7 + 'px'}\">\n                        <div class=\"u-card-img\" :class=\"item.resourceType\">\n                            <img :src=\"store.state.head +'/health_propaganda'+ item.resourceCoverPath\">\n                        </div>\n                        <div class=\"u-card-text\">\n                            <h1 v-text=\"item.resourceTitle\" style=\"font-size: 20px;margin-top: 10px;\">\u672F\u540E\u521D\u671F\u7EF4\u62A4</h1>\n                        </div>\n                    </div>\n                </div>\n                <vv-load v-if=\"loads\"></vv-load>\n            </div>",
    data: function data() {
        return {
            list: [],
            classify: [],
            classA: undefined,
            search: "",
            cardW:"",
            add: {
                resourceTitle: "新增",
                resourceType: "newadd",
                id: "1"
            },
            loads: false
        };
    },

    watch: {
        search: function search() {
            this.allPropaganda('search');
        },
        "$route.params": function $routeParams() {
            if (this.$route.params.refresh) {
                this.allPropaganda();
            }
        }
    },
    computed: {
        datacard: function datacard() {
            // 卡片的高宽
            var w = void 0;
            if (window.innerWidth < 1400) {
                w = (window.innerWidth - 77) / 4;
                this.cardW = 'calc(25% - 12px)';
            } else {
                w = (window.innerWidth - 89) / 5;
                this.cardW = 'calc(20% - 12px)';
            }
            return w;
        },
        contentH: function contentH() {
            var h = window.innerHeight;
            var w = window.innerWidth;
            if (store.state.roles == 1 && threeTerminal() == "PC") {
                return h - 152;
            } else if(threeTerminal() == "ipad"){
            	return h - 100;
            } else {
                return h - 106;
            }
        }
    },
    methods: {
        allPropaganda: function allPropaganda(sole) {
            // 获取内容API
            this.loads = false;
            this.classA = sole || undefined;
            sole = sole || {};
            var th = this;
            var url = "/propaganda/resource/list.shtml";
            var data = {
                token: localStorage.getItem("token")
            };
            if (!isNaN(sole)) {
                data.categoryId = sole;
            }
            if (sole == "search") {
                data.cond = this.search;
                this.classA = undefined;
            }
            axiosGet(url, data, function (res) {
                if (res.data.status == 1) {
                    if (res.data.status == 1) {
                        th.list = res.data.list;
                        th.loads = false;
                    } else {
                        th.loads = false;
                    }
                }
            });
        },
        classifyapi: function classifyapi() {
            // 获取分类API
            var th = this;
            var data = {};
            var url = "/propaganda/category/list.shtml";
            axiosGet(url, data, function (res) {
                if (res.data.status == 1) {
                    th.classify = res.data.list;
                }
            });
        },
        newadd: function newadd(obj) {
            this.$router.push({
                path: obj.resourceType,
                name: obj.resourceType,
                params: {
                    id: obj.id
                }
            });
            for (var item in store.state.menubar) {
                var contrast = store.state.menubar[item];
                if (contrast.title == obj.resourceTitle) {
                    contrast.path = this.$route.path;
                    return false;
                }
            }
            store.state.menubar.push({
                close: true,
                title: obj.resourceTitle,
                path: "/" + obj.resourceType + "/" + obj.id
            });
        },
        login: function login() {
            var th = this;
            var url = store.state.head + "/loginSubmit.shtml";
            var data = {
                account: "admin",
                password: "123456",
                tenantId: "10101"
            };
            axios.get(url, {
                params: data
            }).then(function (res) {
                localStorage.setItem("token", res.data.cookie_token);
            }).catch(function (err) {
                console.log(err);
            });
        }
    },
    created: function created() {
        this.allPropaganda();
        this.classifyapi();
    }
});

// 新增
var Newadd = Vue.component("vv-newadd", {
    template: "<div class=\"frame-content\" :style=\"{height: bodyH + 'px'}\">   \n\t\t            <div class=\"u-list-text mt-10\">\n\t\t                <div class=\"left\">\n\t\t                 \u6807\u9898\u5185\u5BB9\uFF1A\n\t\t                </div>\n\t\t                <div class=\"right\">\n\t\t                    <input type=\"text\" name=\"title\" placeholder=\"\u8F93\u5165\u6807\u9898\" v-model=\"titleval\" style=\"width:500px;\">\n\t\t                </div>\n\t\t            </div>\n\t\t                <div class=\"u-list-text mt-10\">\n\t\t                <div class=\"left\">\n\t\t                 \u5BA3\u6559\u5206\u7C7B\uFF1A\n\t\t                </div>\n\t\t                <div class=\"right\">\n\t\t                    <label class=\"u-checkbox\" v-for=\"item in classify\">\n\t\t                        <input type=\"checkbox\" name=\"cha1\" :value=\"item.id\" v-model=\"classed\">\n\t\t                        <span class=\"icon-checkbox\"></span>{{ item.categoryName }}\n\t\t                    </label>\n\t\t                    <button type=\"button\" class=\"u-btn-blue\" text @click=\"dialog = true\">\u5206\u7C7B\u8BBE\u7F6E</button>\n\t\t                </div>\n\t\t            </div>\n\t\t            <div class=\"u-list-text mt-10\">\n\t\t                <div class=\"left\">\n\t\t                    \u4E0A\u4F20\u65B9\u5F0F\uFF1A\n\t\t                </div>\n\t\t                <div class=\"right\">\n\t\t                    <label class=\"u-radio\">\n\t\t                        <input type=\"radio\" name=\"rad1\" value=\"image\" v-model=\"way\">\n\t\t                        <span class=\"icon-radio\"></span>\n\t\t                        \u56FE\u7247\n\t\t                    </label>\n\t\t                    <label class=\"u-radio\">\n\t\t                        <input type=\"radio\" name=\"rad1\" value=\"text\" v-model=\"way\">\n\t\t                        <span class=\"icon-radio\"></span>\n\t\t                        \u6587\u672C\n\t\t                    </label>\n\t\t                    <label class=\"u-radio\">\n\t\t                        <input type=\"radio\" name=\"rad1\" value=\"video\" v-model=\"way\">\n\t\t                        <span class=\"icon-radio\"></span>\n\t\t                        \u89C6\u9891\n\t\t                    </label>\n\t\t                </div>\n\t\t            </div>\n\t\t            <div class=\"u-list-text mt-10\">\n\t\t                <div class=\"left\">\n\t\t                 \u4E0A\u4F20\u5C01\u9762\uFF1A\n\t\t                </div>\n\t\t                <div class=\"right\">\n\t\t                    <label class=\"u-file mt-10\" max @click=\"cover = true\">\n                                <img v-if=\"coverpreview\" :src=\"coverpreview\" style=\"height:100%;position:absolute;left:0px;top:0px;\">\n\t\t                        <i class=\"icon-add\"></i>\n\t\t                        \u5C01\u9762\n\t\t                    </label>\n\t\t                    <h6 class=\"fc-black_5 u-line-0\">支持jpg、gif、png格式1张，不超过3MB，建议尺寸256*144px;</h6>\n\t\t                </div>\n\t\t            </div>\n\t\t            <div class=\"u-list-text mt-30 upload-type\" v-if=\"way == 'image'\">\n\t\t                <div class=\"left\">\n\t\t                 \u4E0A\u4F20\u5185\u5BB9\uFF1A\n\t\t                </div>\n\t\t                <div class=\"right\">\n\t\t                    <vv-Uimage @nn-upimage=\"upimage\" :images= \"images\"></vv-Uimage>\n\t\t                </div>\n\t\t            </div>\n\t\t            <div class=\"u-list-text mt-30 upload-type\" v-if=\"way == 'text'\">\n\t\t                <div class=\"left\">\n\t\t                 \u4E0A\u4F20\u5185\u5BB9\uFF1A\n\t\t                </div>\n\t\t                <div class=\"right\">\n                            <vv-textarea :textre=\"textcontent\"></vv-textarea>\n\t\t                </div>\n\t\t            </div>\n\t\t            <div class=\"u-list-text mt-30 upload-type\" v-if=\"way == 'video'\">\n\t\t                <div class=\"left\">\n\t\t                 \u4E0A\u4F20\u5185\u5BB9\uFF1A\n\t\t                </div>\n\t\t                <div class=\"right\">\n\t\t                    <vv-Uvideo @nn-Uvideo=\"videoFun\" :video=\"videObj\"></vv-Uvideo>\n\t\t                </div>\n\t\t            </div>\n\t\t            <div class=\"release-propaganda\">\n\t\t                <button type=\"button\" class=\"mr-10\" @click=\"clearempty\">\u91CD\u7F6E</button>\n\t\t                <button type=\"button\" class=\"u-btn-blue\" fill @click=\"release\">\u53D1\u5E03</button>\n\t\t            </div>\n                    <vv-dialog :title=\"title\" @nn-cancel=\"cancel\" @nn-confirm=\"save\" v-if=\"dialog\">\n                        <div slot=\"content\">  \n                            <div class=\"classification-set\">\n                                <div class=\"u-list mt-10\">\n                                    <input type=\"text\" name=\"\" v-model=\"classval\" placeholder=\"\u65B0\u589E\u5206\u7C7B\" >\n                                    <button type=\"button\" class=\"u-btn-blue\" fill @click=\"newclassify\">\u65B0\u589E</button>\n                                    <span class=\"fc-grey\">\u8BF4\u660E\uFF1A\u6700\u591A\u652F\u6301\u516B\u7C7B\uFF1B</span>\n                                </div>\n                                <div class=\"classification-label\">\n                                    <span v-for=\"item in classify\">\n                                        <i @click=\"deleteclassify(item)\">\xD7</i>\n                                        {{ item.categoryName }}\n                                    </span>\n                                </div>\n                            </div>\n                        </div>\n                    </vv-dialog>\n                    <vv-cover @nn-cancel=\"cover=false\" @nn-save=\"saveCover\" v-if=\"cover\"></vv-cover>\n                    <vv-load v-if=\"load\"></vv-load>\n\t\t        </div>",
    data: function data() {
        return {
            dialog: false,
            load: false,
            title: "分类设置",
            title2: "上传封面",
            classify: [],
            classval: "",
            record: [],
            cover: false,
            classed: [],
            way: "image",
            titleval: "",
            images: [],
            textcontent: "",
            videos: "",
            videObj: {},
            coverpreview: ""
        };
    },

    watch: {
        "$route": "compilecon"
    },
    computed: {
        bodyH: function bodyH() {
            var h = window.innerHeight;
            var w = window.innerWidth;
            if (store.state.roles == 1 && w > 1024) {
                return h - 170;
            } else {
                return h - 110;
            }
        }
    },
    methods: {
        cancel: function cancel() {
            this.dialog = false;
            this.classifyapi();
        },
        saveCover: function saveCover(base64) {
            this.coverpreview = base64;
            this.cover = false;
        },
        upimage: function upimage(img) {
            // 上传内容
            this.images = img;
        },
        videoFun: function videoFun(video) {
            // 视屏内容
            this.videos = video;
            promptbox("上传成功", "promptbox-language");
        },
        deleteclassify: function deleteclassify(obj) {
            var men = this.classify.splice(this.classify.indexOf(obj), 1)[0];
            men.delFlag = 1;
            this.record.push(men);
        },
        newclassify: function newclassify() {
            if (this.classval.replace(/\s+/g, "") && this.classify.length < 8) {
                for (var item in this.classify) {
                    if (this.classify[item].categoryName == this.classval.replace(/\s+/g, "")) {
                        return false;
                    }
                }
                this.classify.push({
                    categoryName: this.classval
                });
                this.classval = "";
            }
        },
        clearempty: function clearempty() {
            this.titleval = "";
            this.classed = [];
            this.way = "image";
            this.coverpreview = "";
            this.images = [];
            this.textcontent = "";
            this.videos = "";
            this.videObj = {};
        },
        compilecon: function compilecon() {
            // 数据编辑
            var id = this.$route.params.id;
            if (this.$route.name == "newadd" && id != 1) {
                var th = this;
                if(!isEmpty(id)){
                    var url = "/propaganda/resource/getFullResource.shtml";
                    var data = {
                                    id: id
                    };
                    axiosGet(url, data, function (res) {
                        th.images = [];
                        var data = res.data.entity;
                        if (res.data.status == 1) {
                            th.titleval = data.resourceTitle;
                            if (data.categoryIds) {
                                th.classed = data.categoryIds.split(",");
                            } else {
                                th.classed = [];
                            }
                            th.way = data.resourceType;
                            th.coverpreview = store.state.head + '/health_propaganda' + data.resourceCoverPath;
                            if (th.way == "image") {
                                for (var item in data.resourcePathList) {
                                    th.images.push(data.resourcePathList[item].resourcePath);
                                }
                            } else if (th.way == "text") {
                                var reg = new RegExp("{replace}", "g");
                                th.textcontent = data.textContent.replace(reg, store.state.head + '/health_propaganda');
                            } else if (th.way == "video") {
                                th.videos = data.resourcePathList[0].resourcePath;
                                th.videObj = data;
                            }
                        }
                    });
                }
            } else if (id == 1) {
                this.clearempty();
            }
        },
        classifyapi: function classifyapi() {
            // 获取分类
            var th = this;
            var url = "/propaganda/category/list.shtml";
            var data = {};
            axiosGet(url, data, function (res) {
                if (res.data.status == 1) {
                    th.classify = res.data.list;
                }
            });
        },
        save: function save() {
            // 分类保存
            var concatstr = this.record.concat(this.classify);
            var th = this;
            var formData = new FormData();
            formData.append('categoryStr', JSON.stringify(concatstr));
            var url = "/propaganda/category/batchUpdate.shtml";
            axiosPost(url, formData, function (res) {
                var rens = res.data.status;
                if (rens == 1 || rens == 2) {
                    th.dialog = false;
                    promptbox("保存成功", "promptbox-language");
                } else {
                    promptbox("保存失败", "promptbox-language");
                }
            });
        },
        release: function release() {
            // 发布
            if (this.way == "text") {
                this.textcontent = document.getElementById("textareId").innerHTML;
            }
            if (verification({
                titleval: this.titleval,
                coverpreview: this.coverpreview,
                way: this.way,
                images: this.images,
                textcontent: this.textcontent,
                videopath: this.videos
            })) {
                return true;
            }
            this.load = true;
            var formData = new FormData();
            var reg = new RegExp(store.state.head + '/health_propaganda', "g");
            formData.append('resourceTitle', this.titleval);
            formData.append('categoryId', this.classed);
            formData.append('resourceType', this.way);
            formData.append('base64ImageData', this.coverpreview);
            if (this.way == "text") {
                formData.append('textContent', this.textcontent.replace(reg, '{replace}'));
            } else if (this.way == "image") {
                formData.append('uploadResourcePath', this.images);
            } else if (this.way == "video") {
                formData.append('uploadResourcePath', this.videos);
            }
            if (this.$route.params.id != 1) {
                formData.append('id', this.$route.params.id);
            }
            var th = this;
            var url = "/propaganda/resource/save.shtml";
            axiosPost(url, formData, function (res) {
                if (res.data.status == 1) {
                    th.$router.push({
                        path: "/",
                        name: "propaganda",
                        params: {
                            refresh: true
                        }
                    });
                    for (var item in store.state.menubar) {
                        if (store.state.menubar[item].title == "新增" || store.state.menubar[item].title == th.titleval) {
                            store.state.menubar.splice(item, 1);
                        }
                    }
                    promptbox("发布成功", "promptbox-language");
                    th.clearempty();
                    th.load = false;
                } else {
                    promptbox("发布失败", "promptbox-language");
                    th.load = false;
                }
            });
        }
    },
    created: function created() {
        this.classifyapi();
    },
    beforeMount: function beforeMount() {
        this.compilecon();
    }
});

// 图片详情
var Detailsimage = Vue.component("vv-image", {
    template: '<div>'+
				    '<div class="details-title">'+
					    '<div class="u-list u-xt-12 u-border-b">'+
					        '<div class="u-xt-6 fc-iocn"><h1 v-text="image.resourceTitle" class="fs-18"></h1></div>'+
					        '<div class="u-xt-6 u-text-r">'+
					            '<button type="button" text class="u-btn-red" @click="dialog = true">删除</button>'+
					            '<button type="button" text class="u-btn-blue ml-6" @click="compilecon(image)">编辑</button>'+
					            '<button type="button" text class="u-btn-blue ml-6" @click="evaluate = true">评价</button>'+
					        '</div>'+
					    '</div>'+
					'</div>'+
					'<div class="frame-content" style="padding-right: 0px;" :style="{ height: bodyH + \'px\' }">'+
					    '<div class="image-details-content" id="previewPictureId" style="padding-top:0px;">'+
					        '<img :src=" store.state.head + \'/health_propaganda\' + item.resourcePath" v-for="(item ,index) in image.resourcePathList" :index="index" @click="previewImg(event.target)" />'+
					    '</div>'+
					'</div>'+
					'<vv-delete :title="title" :delete="image" @nn-cancel="dialog = false" @nn-confirm="dialog = false" v-if="dialog">'+
					    '<div slot="content"> 您删除之后将不能恢复！</div>'+
					'</vv-delete>'+
					'<vv-evaluate v-if="evaluate" :particulars="image" @nn-cancel="evaluate = false"></vv-evaluate>'+
			'</div>',
    data: function data() {
        return {
            image: [],
            title: "确认删除",
            dialog: false,
            evaluate: false
        };
    },

    watch: {
        "$route.params.id": "imageapi"
    },
    computed: {
        bodyH: function bodyH() {
            var h = window.innerHeight;
            var w = window.innerWidth;
            if (store.state.roles == 1 && threeTerminal() == "PC") {
                return h - 170;
            } else if(threeTerminal() == "ipad"){
            	return h - 46;
            } else {
                return h - 100;
            }
        }
    },
    methods: {
        imageapi: function imageapi() {
            var th = this;
            var id = th.$route.params.id;
            if(!isEmpty(id)){
                this.image = [];
                var url = "/propaganda/resource/getFullResource.shtml";
                var data = {
                    id: id
                };
                axiosGet(url, data, function (res) {
                    if (res.data.status == 1) {
                        th.image = res.data.entity;
                        store.commit('title', th.image.resourceTitle);
                    }
                });
            }
        },
        compilecon: function compilecon(file) {
            this.$router.push({
                path: "/newadd",
                name: "newadd",
                params: {
                    id: file.id
                }
            });
            for (var item in store.state.menubar) {
                if (store.state.menubar[item].title == file.resourceTitle) {
                    store.state.menubar[item].path = "/newadd/" + file.id;
                }
            }
        },
        previewImg: function previewImg(ev){
            picture.Preview({
				elem: 'previewPictureId',
				th: ev,
				cycle: true,
				time: 4000
			})
        }
    },
    created: function created() {
        this.imageapi();
    }
});

// 文本详情
var Detailstext = Vue.component("vv-text", {
    template: "<div>\n                <div class=\"details-title\">\n                      <div class=\"u-list u-xt-12 u-border-b\">\n                        <div class=\"u-xt-6 fc-iocn\" ><h1 class=\"fs-18\" v-text=\"title.resourceTitle\"></h1>\n</div>\n                        <div class=\"u-xt-6 u-text-r\">\n                            <button type=\"button\" text class=\"u-btn-red\" @click=\"dialog = true\">\u5220\u9664</button>\n                            <button type=\"button\" text class=\"u-btn-blue ml-6\" @click=\"compilecon(title)\">\u7F16\u8F91</button>\n                            <button type=\"button\" text class=\"u-btn-blue ml-6\" @click=\"evaluate = true\">\u8BC4\u4EF7</button>\n                        </div>\n                    </div>\n                </div>\n                \n                <div class=\"frame-content u-clear-scroll\" style=\"padding-right: 0px;\" :style=\"{height: bodyH + 'px'}\">\n                    <div class=\"text-details-content\" v-html=\"text\">\n\t                    \n\t                </div>\n                </div>\n                <vv-delete :title=\"title2\" :delete=\"title\" @nn-cancel=\"dialog = false\" @nn-confirm=\"dialog = false\" v-if=\"dialog\">\n                    <div slot=\"content\">  \n                        \u60A8\u5220\u9664\u4E4B\u540E\u5C06\u4E0D\u80FD\u6062\u590D\uFF01\n                    </div>\n                </vv-delete>\n                <vv-evaluate v-if=\"evaluate\" :particulars=\"title\" @nn-cancel=\"evaluate = false\"></vv-evaluate>\n            </div>",
    data: function data() {
        return {
            title: [],
            text: "",
            title2: "确认删除",
            dialog: false,
            evaluate: false
        };
    },

    watch: {
        "$route.params.id": "textapi"
    },
    computed: {
        bodyH: function bodyH() {
            var h = window.innerHeight;
            var w = window.innerWidth;
            if (store.state.roles == 1 && threeTerminal() == "PC") {
                return h - 170;
            } else if(threeTerminal() == "ipad"){
            	return h - 46;
            } else {
                return h - 130;
            }
        }
    },
    methods: {
        textapi: function textapi() {
            var th = this;
            var id = th.$route.params.id;
            if(!isEmpty(id)){
                var url = "/propaganda/resource/getFullResource.shtml";
                var reg = new RegExp("{replace}", "g");
                var data = {
                                id: id
                };
                axiosGet(url, data, function (res) {
                    if (res.data.status == 1) {
                        th.title = res.data.entity;
                        th.text = res.data.entity.textContent.replace(reg, store.state.head + '/health_propaganda');
                        store.commit('title', th.title.resourceTitle);
                    }
                });
            }
        },
        compilecon: function compilecon(file) {
            this.$router.push({
                path: "/newadd",
                name: "newadd",
                params: {
                    id: file.id
                }
            });
            for (var item in store.state.menubar) {
                if (store.state.menubar[item].title == file.resourceTitle) {
                    store.state.menubar[item].path = "/newadd/" + file.id;
                }
            }
        }
    },
    created: function created() {
        this.textapi();
    }
});

// 视屏详情
var Detailsvideo = Vue.component("vv-video", {
    template: '<div>'+
                '<div class="details-title">'+
                    '<div class="u-list u-xt-12 u-border-b">'+
                        '<div class="u-xt-4 fc-iocn"><h1 v-text="video.resourceTitle"></h1></div>'+
                        '<div class="u-xt-8 u-text-r">'+
                            '<button type="button" text class="u-btn-red" @click="dialog = true">删除</button>'+
                            '<button type="button" text class="u-btn-blue" @click="compilecon(video)">编辑</button>'+
                            '<button type="button" text class="u-btn-blue" @click="evaluate = true">评价</button>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
                '<div class="frame-content u-clear-scroll" style="padding-right: 0px;" :style="{ height: bodyH + \'px\' }">'+
                    '<div class="propaganda-video">'+
                        '<video controls="controls" :src="store.state.head + \'/health_propaganda\' + paths"></video>'+
                    '</div>'+
                '</div>'+
                '<vv-delete :title="title2" :delete="video" @nn-cancel="dialog = false" @nn-confirm="dialog = false" v-if="dialog">'+
                    '<div slot="content">您删除之后将不能恢复！</div>'+
                '</vv-delete>'+
                '<vv-evaluate v-if="evaluate" :particulars="video" @nn-cancel="evaluate = false"></vv-evaluate>'+
            '</div>',
    data: function data() {
        return {
            video: [],
            paths: "",
            title2: "确认删除",
            dialog: false,
            evaluate: false
        };
    },

    watch: {
        "$route.params.id": "videoapi"
    },
    computed: {
        bodyH: function bodyH() {
            var h = window.innerHeight;
            var w = window.innerWidth;
            if (store.state.roles == 1 && threeTerminal() == "PC") {
                return h - 170;
            } else if(threeTerminal() == "ipad"){
            	return h - 46;
            } else {
                return h - 130;
            }
        }
    },
    methods: {
        videoapi: function videoapi() {
            var th = this;
            var id = th.$route.params.id;
            if(!isEmpty(id)){
                var url = "/propaganda/resource/getFullResource.shtml";
                var data = {
                                id: id
                };
                axiosGet(url, data, function (res) {
                    if (res.data.status == 1) {
                        th.video = res.data.entity;
                        th.paths = res.data.entity.resourcePathList[0].resourcePath;
                        store.commit('title', th.video.resourceTitle);
                    }
                });
            }
        },
        compilecon: function compilecon(file) {
            this.$router.push({
                path: "/newadd",
                name: "newadd",
                params: {
                    id: file.id
                }
            });
            for (var item in store.state.menubar) {
                if (store.state.menubar[item].title == file.resourceTitle) {
                    store.state.menubar[item].path = "/newadd/" + file.id;
                }
            }
        }
    },
    created: function created() {
        this.videoapi();
    }
});

// 宣教评估编辑
var EvaluationEditing = Vue.component("vv-evaluation-editing", {
    template: '<div>'+
			    '<vv-dialog-screen :title="title">'+
			    '<div slot="content">'+
			        '<div class="propaganda-assess fc-black">'+
			            '<form id="evaluationEditingId" method="post" enctype="multipart/form-data" accept-charset="utf-8">'+
			                '<div class="u-list pl-30 u-border-b mt-22">'+
			                    '<div class="u-xt-12">'+
			                        '<div class="u-xt-4">姓名：{{ listpatient.name }}</div>'+
			                        '<div class="u-xt-4">性别：{{ listpatient.sexShow }}</div>'+
			                        '<div class="u-xt-4">年龄：{{ listpatient.age }}岁</div>'+
			                    '</div>'+     
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">教育对象：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.propagandaObject">'+
			                          '<input type="radio" name="propagandaObject" :value="item.value" v-model="propagandaObject" @change="compile">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">文化程度：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.educationalLevel">'+
			                          '<input type="radio" name="educationAssessmentPO.educationalLevel" :value="item.value" v-model="educationalLevel">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">语言种类：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.languageType">'+
			                          '<input type="radio" name="educationAssessmentPO.languageType" :value="item.value" v-model="languageType">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                          '<lable v-if="item.name == \'其他\'" >'+
			                          	'<input v-if="languageType == \'03\'" type="text" name="educationAssessmentPO.languageTypeOther" short v-model="languageTypeOther" style="position: absolute;" class="ml-10">'+
			                          '</label>'+
			                         '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">语言障碍：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.languageBarrier">'+
			                          '<input type="radio" name="educationAssessmentPO.languageBarrier" :value="item.value" v-model="languageBarrier">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">认知状态：</div>'+
			                    '<div class="right">对疾病相关知识理解：'+
			                        '<label class="u-radio" v-for="item in formList.perceive">'+
			                          '<input type="radio" name="educationAssessmentPO.perceive" :value="item.value" v-model="perceive">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">学习动机：</div>'+
			                    '<div class="right">对疾病知识学习欲望：'+
			                        '<label class="u-radio" v-for="item in formList.learnDesire">'+
			                          '<input type="radio" name="educationAssessmentPO.learnDesire" :value="item.value" v-model="learnDesire">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                        '</label>'+
			                   '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">情感障碍：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.emotionBarrier">'+
			                          '<input type="radio" name="educationAssessmentPO.emotionBarrier" :value="item.value" v-model="emotionBarrier">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                          '<lable v-if="item.name == \'其他\'" >'+
			                          	'<input v-if="emotionBarrier == \'04\'" type="text" name="educationAssessmentPO.emotionBarrierOther" short v-model="emotionBarrierOther" style="position: absolute;" class="ml-10">'+
			                          '</label>'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">学习障碍：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.learnBarrier">'+
			                          '<input type="radio" name="educationAssessmentPO.learnBarrier" :value="item.value" v-model="learnBarrier">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                          '<lable v-if="item.name == \'其他\'" >'+
			                          	'<input v-if="learnBarrier == \'05\'" type="text" name="educationAssessmentPO.learnBarrierOther" short v-model="learnBarrierOther" style="position: absolute;" class="ml-10">'+
			                          '</label>'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">宗教信仰：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.religion" style="margin-right:65px;">'+
			                          '<input type="radio" name="educationAssessmentPO.religion" :value="item.value" v-model="religion">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                          '<lable v-if="item.name == \'有\'" >'+
			                          	'<input v-if="religion == \'1\'" type="text" name="educationAssessmentPO.religionOther" short v-model="religionOther" style="position: absolute;" class="ml-10">'+
			                          '</label>'+
			                        '</label>'+
			                        '<span>对学习的影响：</span>'+
			                        '<label class="u-radio" v-for="item in formList.learnEffect">'+
			                          '<input type="radio" name="educationAssessmentPO.learnEffect" :value="item.value" v-model="learnEffect">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-border-t mt-34 mb-26"></div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">宣教内容：</div>'+
			                    '<div class="right">'+
			                        '<span>标题名称：{{ listentity.resourceTitle }}</span>'+
			                        '<span class="ml-34">资料来源：{{ listentity.dataSource }}</span>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">宣教方式：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.propagandaType">'+
			                          '<input type="radio" name="propagandaType" :value="item.value" v-model="propagandaType">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                          '<lable v-if="item.name == \'其他\'" >'+
			                          	'<input v-if="propagandaType == \'05\'" type="text" name="propagandaTypeOther" short v-model="propagandaTypeOther" style="position: absolute;" class="ml-10">'+
			                          '</label>'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">宣教评价：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.assessment">'+
			                          '<input type="radio" name="assessment" :value="item.value" v-model="assessment">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">宣教地点：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-radio" v-for="item in formList.place">'+
			                          '<input type="radio" name="place" :value="item.value" v-model="place">'+
			                          '<span class="icon-radio"></span>{{item.name}}'+
			                        '</label>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text">'+
			                    '<div class="left">备注：</div>'+
			                    '<div class="right">'+
			                        '<textarea name="" class="mt-8" name="comment" v-model="comment" long></textarea>'+
			                    '</div>'+
			                '</div>'+
			                '<div class="u-list-text u-border-t pt-8">'+
			                    '<div class="left">宣教人：</div>'+
			                    '<div class="right">'+
			                        '<label class="u-select">'+
			                          '<select name="operateId" v-model="operateId">'+
			                            '<option :value="item.id" v-for="item in options">{{item.name}}</option>'+
			                          '</select>'+
			                        '</label>'+
			                        '<span class="ml-36">宣教日期：</span>'+
			                        '<input type="text" name="propagandaDateShow" v-model="propagandaDate" id="propagandaDateId" readonly />'+
			                    '</div>'+
			                '</div>'+
			            '</form>'+
			        '</div>'+
			    '</div>'+
			    '<div slot="footer">'+
			        '<button type="button" class="" @click="cancel">取消</button>'+
			        '<button type="button" class="u-btn-blue" fill @click="save">保存</button>'+
			    '</div>'+
			'</vv-dialog-screen>'+
			'</div>',
    props: {
        list: {
            type: Object,
            default: {}
        }
    },
    data: function data() {
        return {
            title: "健康教育评估编辑",
            listentity: {},
            listpatient: {},
            options: [],
            formList: {},
            propagandaObject: "", // 教育对象
            educationalLevel: "", // 文化程度
            languageType: "", // 语言种类
            languageTypeOther: "", // 语言种类 其他
            languageBarrier: "", // 语言障碍
            perceive: "", // 认知状态
            learnDesire: "", // 学习动机
            emotionBarrier: "", // 情感障碍
            emotionBarrierOther: "", // 情感障碍 其他
            learnBarrier: "", // 学习障碍
            learnBarrierOther: "", // 学习障碍 其他
            religion: "", // 宗教信仰
            religionOther: "", // 宗教信仰 其他
            learnEffect: "", // 学习影响
            assessment: "", // 宣教评价
            place: "", // 宣教地点
            placeOther: "", // 宣教地点（其他）
            propagandaDate: "", // 宣教日期
            comment: "", // 备注
            operateId: "", // 宣教人id
            propagandaType: "", //宣教方式
            propagandaTypeOther: "" // 宣教方式（其他）
        };
    },

    methods: {
        compile: function compile() {
            var th = this;
            // 评估编辑API
            var url = '/propaganda/assessment/getAssessmentDocumentEdit.shtml';
            // 获取医护API
            var url1 = '/propaganda/bigscreen/getPropagandaPerson.shtml';
            var propagandaObject = void 0;
            if (!this.once) {
                propagandaObject = this.list.propagandaObject;
                this.once = true;
            } else {
                propagandaObject = this.propagandaObject;
            }
            var data = {
                id: this.list.id,
                fkPropagandaResourceId: this.list.fkPropagandaResourceId,
                fkPatientId: store.state.patientID,
                propagandaObject: propagandaObject,
                rand: new Date().getTime()
            };
            axiosGet(url, data, function (res) {
                var rens = res.data.rs;
                if (res.data.status == 1) {
                    th.listentity = rens.entity;
                    th.listpatient = rens.patient;
                    var educationAssessmentPO = rens.entity.educationAssessmentPO;
                    if (educationAssessmentPO) {
                        th.propagandaObject = educationAssessmentPO.propagandaObject;
                        th.educationalLevel = educationAssessmentPO.educationalLevel;
                        th.languageType = educationAssessmentPO.languageType;
                        th.languageTypeOther = educationAssessmentPO.languageTypeOther;
                        if(educationAssessmentPO.languageBarrier != null){
                        	th.languageBarrier = educationAssessmentPO.languageBarrier ? 1 : 0;
                        }else{
                        	th.languageBarrier = "";
                        }
                        if(educationAssessmentPO.perceive != null){
                        	th.perceive = educationAssessmentPO.perceive ? 1 : 0;
                        }else{
                        	th.perceive = "";
                        }
                        if(educationAssessmentPO.learnDesire != null){
                        	th.learnDesire = educationAssessmentPO.learnDesire ? 1 : 0;
                        }else{
                        	th.learnDesire = "";
                        }
                        if(educationAssessmentPO.learnEffect != null){
                        	th.learnEffect = educationAssessmentPO.learnEffect ? 1 : 0;
                        }else{
                        	th.learnEffect = "";
                        }
                        if(educationAssessmentPO.religion != null){
                        	th.religion = educationAssessmentPO.religion ? 1 : 0;
                        }else{
                        	th.religion = "";
                        }
                        th.emotionBarrier = educationAssessmentPO.emotionBarrier;
                        th.emotionBarrierOther = educationAssessmentPO.emotionBarrierOther;
                        th.learnBarrier = educationAssessmentPO.learnBarrier;
                        th.learnBarrierOther = educationAssessmentPO.learnBarrierOther;
                        th.religionOther = educationAssessmentPO.religionOther;
                    } else {
                        th.propagandaObject = rens.entity.propagandaObject;
                        th.educationalLevel = "";
                        th.languageType = "";
                        th.languageTypeOther = "";
                        th.languageBarrier = "";
                        th.perceive = "";
                        th.learnDesire = "";
                        th.emotionBarrier = "";
                        th.emotionBarrierOther = "";
                        th.learnBarrier = "";
                        th.learnBarrierOther = "";
                        th.religion = "";
                        th.religionOther = "";
                        th.learnEffect = "";
                    }
                    th.assessment = rens.entity.assessment;
                    th.place = rens.entity.place;
                    th.placeOther = rens.entity.placeOther;
                    th.propagandaDate = rens.entity.propagandaDateShow;
                    th.comment = rens.entity.comment;
                    th.operateId = rens.entity.operateId || store.state.loginUserId;
                    th.fkPatientId = rens.entity.fkPatientId;
                    th.propagandaType = rens.entity.propagandaType;
                    th.propagandaTypeOther = rens.entity.propagandaTypeOther;
                }
            });
            axiosGet(url1, {}, function (res) {
                if (res.data.status == 1) {
                    th.options = res.data.list;
                }
            });
        },
        save: function save() {
            var th = this;
            var formdata = new FormData(evaluationEditingId);
            formdata.append("id", this.list.id);
            formdata.append("fkPatientId", store.state.patientID);
            formdata.append("fkPropagandaResourceId", this.list.fkPropagandaResourceId);
            var url = '/propaganda/assessment/saveAssessmentDocument.shtml';
            axiosPost(url, formdata, function (res) {
                if (res.data.status == 1) {
                    th.$emit('nn-save');
                    th.cancel();
                    promptbox("保存成功", "promptbox-language");
                } else {
                    promptbox(res.data.errmsg, "promptbox-language");
                }
            });
        },
        evaluationList: function evaluationList() {
            // 列表选项api
            var th = this;
            var $token = localStorage.getItem("token");
            var url = '/propaganda/assessment/getFormData.shtml';
            axiosGet(url, {rand: new Date().getTime()}, function (res) {
                if (res.data.status == 1) {
                    th.formList = res.data.rs;
                }
            });
        },
        cancel: function cancel() {
            // 取消弹框
            this.$emit('nn-cancel');
        }
    },
    mounted: function mounted() {
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#propagandaDateId',
                theme: '#31AAFF',
                btns: []
            });
        });
    },
    created: function created() {
        this.evaluationList();
        this.compile();
    }
});

// 宣教评估单
var EvaluationSingle = Vue.component("vv-evaluation-single", {
    template: '<div>'+
			    '<vv-dialog-screen :title="title">'+
			    '<div slot="content">'+
			       '<div class="propaganda-evaluation-list fc-black">'+
			            '<div class="u-list pl-30 u-border-b mt-22">'+
			                '<div class="u-xt-12">'+
			                    '<div class="u-xt-4">姓名：{{listpatient.name}}</div>'+
			                    '<div class="u-xt-4">性别：{{listpatient.sexShow }}</div>'+
			                    '<div class="u-xt-4">年龄：{{listpatient.age}} 岁</div>'+
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text-two">'+
			                '<div class="left">'+
			                    '<div>教育对象：</div>'+
			                    '<div>{{list.propagandaObjectName}}</div>'+
			                '</div>'+
			                '<div class="right">'+
			                    '<div>语言种类：</div>'+
			                    '<div>{{listentityPO.languageTypeOther ? listentityPO.languageTypeOther:listentityPO.languageTypeName}}</div>'+
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text-two">'+
			                '<div class="left">'+
			                    '<div>文化程度：</div>'+
			                    '<div>{{listentityPO.educationalLevelName}}</div>'+
			                '</div>'+
			                '<div class="right">'+
			                    '<div>语言障碍：</div>'+
			                    '<div>{{listentityPO.languageBarrierName}}</div>'+
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text-two">'+
			                '<div class="left">'+
			                    '<div>认知状态：</div>'+
			                    '<div>对疾病相关知识理解：{{listentityPO.perceiveName}}</div>'+
			                '</div>'+
			                '<div class="right">'+
			                    '<div>学习动机：</div>'+
			                    '<div>对疾病知识学习欲望：{{listentityPO.learnDesireName}} </div>'+
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text-two">'+
			                '<div class="left">'+
			                    '<div>情感障碍：</div>'+
			                    '<div>{{listentityPO.emotionBarrierOther ? listentityPO.emotionBarrierOther:listentityPO.emotionBarrierName}}</div>'+
			                '</div>'+
			                '<div class="right">'+
			                    '<div>学习障碍：</div>'+
			                    '<div>{{listentityPO.learnBarrierOther ?listentityPO.learnBarrierOther:listentityPO.learnBarrierName }}</div>'+
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text-two">'+
			                '<div class="left">'+
			                    '<div>宗教信仰：</div>'+
			                    '<div>{{listentityPO.religionOther ? listentityPO.religionOther:listentityPO.religionName}}</div>'+
			                '</div>'+
			                '<div class="right">'+
			                    '<div>对学习的影响：</div>'+
			                    '<div>{{listentityPO.learnEffectName}}</div>'+
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text">'+
			                '<div class="left">宣教内容：</div>'+
			                '<div class="right">'+
			                    '<span class="mr-40">标题名称：{{listentity.resourceTitle}}</span>'+
			                    '<span>资料来源：{{listentity.dataSource}}</span>'+
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text-two">'+
			                '<div class="left">'+
			                    '<div>宣教方式：</div>'+
			                    '<div>{{listentity.propagandaTypeOther ? listentity.propagandaTypeOther:listentity.propagandaTypeName }}</div>'+
			                '</div>'+
			                '<div class="right">'+
			                    '<div>宣教评价：</div>'+
			                    '<div>{{listentity.assessmentName}}</div>'+
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text-two">'+
				            '<div class="right">'+
			                    '<div>宣教地点：</div>'+
			                    '<div>{{listentity.placeOther ? listentity.placeOther:listentity.placeName}}</div>'+
			                '</div>'+
			                '<div class="left">'+
			                    
			                '</div>'+
			            '</div>'+
			            '<div class="u-list-text">'+
			                '<div class="left">备注：</div>'+
			                '<div class="right">{{listentity.comment}}</div>'+
			            '</div>'+
			            '<div class="u-list-text">'+
			                '<div class="left">宣教人：</div>'+
			                '<div class="right">{{listentity.operateName}}<span style="margin-left: 180px;">宣教日期：{{listentity.propagandaDateShow}}</span></div>'+
			            '</div>'+
			        '</div>'+
			    '</div>'+
			    '<div slot="footer">'+
			        '<button type="button" class="" @click="cancel">关闭</button>'+
			    '</div>'+
			'</vv-dialog-screen>'+
			'</div>',
    props: {
        list: {
            type: Object,
            list: {}
        }
    },
    data: function data() {
        return {
            title: "健康教育评估单",
            listentity: {},
            listentityPO: {},
            listpatient: {}
        };
    },

    methods: {
        compile: function compile() {
            var th = this;
            var $token = localStorage.getItem("token");
            var url = '/propaganda/assessment/getAssessmentDocumentShow.shtml';
            var data = {
                id: this.list.id,
                fkPropagandaResourceId: this.list.fkPropagandaResourceId,
                fkPatientId: store.state.patientID,
                propagandaObject: this.list.propagandaObject,
                rand: new Date().getTime()
            };
            axiosGet(url, data, function (res) {
                var rens = res.data.rs;
                if (res.data.status == 1) {
                    th.listentity = rens.entity;
                    th.listentityPO = rens.entity.educationAssessmentPO || {};
                    th.listpatient = rens.patient;
                }
            });
        },
        cancel: function cancel() {
            this.$emit('nn-cancel');
        }
    },
    mounted: function mounted() {},
    created: function created() {
        this.compile();
    }
});