import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
	state: {
		hasLogin: false, //用户是否登录
		userInfo: {}, //存放用户账号数据
		baseIP: "jiehecloud.top",
		basePort: "443",
		phoneData: '',
		passData: '',
		projectName: "wms",
		// basePath: "https://jiehecloud.top:443",
		basePath: "https://jiehecloud.top:443",
		cookie: '',
		auths: []
	},
	mutations: { //全局方法
		login(state, provider) {
			state.hasLogin = true;
			state.userInfo = provider;
			uni.setStorage({
				key: "userInfo",
				data: provider
			})
			uni.setStorageSync("basePath", "https://jiehecloud.top:443");
			// uni.setStorageSync("basePath", "https://jiehecloud.top:443");
			console.log(state.userInfo);
		},
		//保存权限信息
		// saveAuths(state, arr, sessionID){
		saveAuths(state, params){
			state.auths = params.auths;
			state.cookie = params.cookie;
			state.phoneData = params.phoneData;
			state.passData = params.passData;
			uni.setStorage({
				key: "auths",
				data: params.auths
			})
			uni.setStorage({
				key: "cookie",
				data: params.cookie
			})
			uni.setStorage({
				key: "phoneData",
				data: params.phoneData
			})
			uni.setStorage({
				key: "passData",
				data: params.passData
			})
		},
		logout(state) {
			state.hasLogin = false;
			state.userInfo = {};
			uni.removeStorage({
				key: "userInfo"
			})
			uni.removeStorage({
				key: "auths"
			})
			uni.removeStorage({
				key: "cookie"
			})
			//推出登录不清理  默认登录名和密码
			// uni.removeStorageSync('phoneData')
			// uni.removeStorageSync('passData')
		},
		setup(state, setups) {
			state.baseIP = setups.baseIP;
			state.basePort = setups.basePort;
			// state.projectName = setups.projectName;
			// state.basePath = "http://" + state.baseIP + ":" + state.basePort + "/" + state.projectName + "";
			state.basePath = "https://" + state.baseIP + ":" + state.basePort;
			uni.setStorage({
				key: "baseIP",
				data: setups.baseIP
			});
			uni.setStorage({
				key: "basePort",
				data: state.basePort
			})
			uni.setStorage({
				key: "basePath",
				data: state.basePath
			})
			uni.setStorageSync("basePath", state.basePath);
		}
	}
})

export default store
