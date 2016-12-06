var pagination = {
	addPaging : function(param) {
		var p = {
			bodyId : null,
			btnId : null,
			callback : null,
			scrollEl : null,
			pageSize : 20
		};
		$.extend(p, param);
		if (isEmpty(p.bodyId))
			return false;
		var obj = {
			bodyId : null,
			btnId : null,
			pageNo : 1,
			pageSize : 20,
			totalPage : 0,
			defaultPageSize : 20,
			callback : null,
			scrollEl : null,
			stop : false,
			init : function(bodyId, btnId, scrollEl, callback, pageSize) {
				obj.bodyId = bodyId;
				obj.btnId = btnId;
				obj.callback = callback;
				obj.scrollEl = scrollEl;
				if (!isEmpty(pageSize)) {
					obj.pageSize = pageSize;
					obj.defaultPageSize = pageSize;
				}
				if (!isEmpty(scrollEl)) {// bind scroll event
					$(scrollEl).scroll(function() {
						if ($("#" + obj.bodyId).height() <= ($(this).height() + $(this).scrollTop() + 60)) {
							if (!obj.stop) {
								obj.stop = true;// 防止加载次数太多
								obj.loadMore();
							}
						}
					});
				}
			},
			resetPage : function() {
				obj.pageNo = 1;
				obj.pageSize = obj.defaultPageSize;
				obj.totalPage = 0;
				$("#" + obj.bodyId).html("");
				$("#" + obj.btnId).html("加载更多");
			},
			/** 设置数据加载状态 */
			setStatus : function(totalPage) {
				obj.totalPage = totalPage;
				var btnId = obj.btnId;
				if (obj.pageNo == totalPage) {
					$("#" + btnId).html("已全部加载");
					$("#" + btnId).off("click");// 解除绑定
					obj.stop = true;
				} else {
					obj.stop = false;
					obj.pageNo = obj.pageNo + 1;
					$("#" + btnId).html("加载更多");
					$("#" + btnId).off("click").on("click", obj.loadMore);
				}
			},
			loadMore : function() {
				$("#" + obj.btnId).html("加载中...");
				obj.callback();
			}
		};
		obj.init(p.bodyId, p.btnId, p.scrollEl, p.callback, p.pageSize);
		$("#" + p.bodyId).data("pagingobj", obj);
	},
	resetPaging : function(bodyId) {
		var obj = $("#" + bodyId).data("pagingobj");
		obj.resetPage();
	},
	setStatus : function(bodyId, totalPage) {
		var obj = $("#" + bodyId).data("pagingobj");
		obj.setStatus(totalPage);
	},
	getPagingData : function(bodyId) {
		var obj = $("#" + bodyId).data("pagingobj");
		var result = {
			pageNo : obj.pageNo,
			pageSize : obj.pageSize,
			totalPage : obj.totalPage,
			str : ("pageNo=" + obj.pageNo + "&pageSize=" + obj.pageSize + "&totalPage=" + obj.totalPage)
		};
		return result;
	},
	scroll : function(bodyId) {
		var obj = $("#" + bodyId).data("pagingobj");
		if (!isEmpty(obj.scrollEl)) {
			$(obj.scrollEl).scroll();
		}
	}
};