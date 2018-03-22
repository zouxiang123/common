var store = new Vuex.Store({
	state: {
		head: "", //项目名
		menubar: [{ //菜单
			title: "健康宣教",
			close: false,
			path: "/"
		}],
		patientID: "", //患者ID
		loginUserId: "", //医护ID
		roles: "", //登陆角色
		titles: "宣教库",
		names: ""
	},
	mutations: {
		heads: function heads(state, head) {
			state.head = head;
		},
		entrance: function entrance(state, id) {
			state.patientID = id;
		},
		loginUser: function loginUser(state, id) {
			state.loginUserId = id;
		},
		role: function role(state, permissions) {
			state.roles = permissions;
		},
		title: function title(state, title){
			state.titles = title;
		},
		name: function name(state, name){
			state.names = name;
		}
	}
});